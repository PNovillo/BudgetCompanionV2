package com.crittercorp.budgetcompanionv2;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AgregarServicioActivity extends AppCompatActivity {

    private EditText editTextNombreServicio;
    private Button btnGuardarServicio;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_servicio);

        // Inicializar vistas y base de datos
        editTextNombreServicio = findViewById(R.id.editTextNombreServicio);
        btnGuardarServicio = findViewById(R.id.btnGuardarServicio);
        ImageView iconoSuperiorIzquierdo = findViewById(R.id.iconoSuperiorIzquierdo);
        dbHelper = new DatabaseHelper(this);

        // OnClickListener botón "Guardar"
        btnGuardarServicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lógica para guardar el servicio
                String nombreServicio = editTextNombreServicio.getText().toString();

                // Guardar el servicio en la base de datos
                guardarServicio(nombreServicio);

                // Mensaje para ver si el botón funciona
                String mensaje = "Nombre del Servicio: " + nombreServicio;

                // Muestra el mensaje
                Toast.makeText(AgregarServicioActivity.this, mensaje, Toast.LENGTH_SHORT).show();
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

    // Método para guardar un servicio en la base de datos
    private void guardarServicio(String nombreServicio) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_SERVICE_NAME, nombreServicio);

        long newRowId = db.insert(DatabaseHelper.TABLE_SERVICES, null, values);

        if (newRowId != -1) {
            Toast.makeText(this, "Servicio guardado correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al guardar el servicio", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }
}

