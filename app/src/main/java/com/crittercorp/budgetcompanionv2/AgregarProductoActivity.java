package com.crittercorp.budgetcompanionv2;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AgregarProductoActivity extends AppCompatActivity {

    private EditText editTextNombreProducto;
    private EditText editTextCantidad;
    private EditText editTextPrecio;
    private TextView textViewDetalle;
    private Button btnGuardarProducto;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto);

        // Inicializar vistas y base de datos
        editTextNombreProducto = findViewById(R.id.editTextNombreProducto);
        editTextCantidad = findViewById(R.id.editTextCantidad);
        editTextPrecio = findViewById(R.id.editTextPrecio);
        textViewDetalle = findViewById(R.id.textViewDetalle);
        btnGuardarProducto = findViewById(R.id.btnGuardarProducto);
        ImageView iconoSuperiorIzquierdo = findViewById(R.id.iconoSuperiorIzquierdo);
        dbHelper = new DatabaseHelper(this);

        // OnClickListener botón "Guardar"
        btnGuardarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener valores de los campos de texto
                String nombreProducto = editTextNombreProducto.getText().toString();
                String cantidad = editTextCantidad.getText().toString();
                String precio = editTextPrecio.getText().toString();
                String detalle = textViewDetalle.getText().toString();

                // Guardar el producto en la base de datos
                guardarProducto(nombreProducto, cantidad, precio, detalle);

                // Mensaje para ver si el botón funciona
                String mensaje = "Nombre: " + nombreProducto + "\nCantidad: " + cantidad + "\nPrecio: " + precio + "\nDetalle: " + detalle;

                // Muestra el mensaje
                Toast.makeText(AgregarProductoActivity.this, mensaje, Toast.LENGTH_SHORT).show();
            }
        });

        // Configurar clic del icono
        iconoSuperiorIzquierdo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    // Método para guardar un producto en la base de datos
    private void guardarProducto(String nombreProducto, String cantidad, String precio, String detalle) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_PRODUCT_NAME, nombreProducto);
        // Agregar otros valores como cantidad, precio y detalle si es necesario

        long newRowId = db.insert(DatabaseHelper.TABLE_PRODUCTS, null, values);

        if (newRowId != -1) {
            Toast.makeText(this, "Producto guardado correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al guardar el producto", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }
}

