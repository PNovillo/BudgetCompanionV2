package com.crittercorp.budgetcompanionv2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "budget_companion.db";
    private static final int DATABASE_VERSION = 1;

    // Tabla de productos
    public static final String TABLE_PRODUCTS = "products";
    public static final String COLUMN_PRODUCT_ID = "id";
    public static final String COLUMN_PRODUCT_NAME = "name";
    // Otros campos para productos

    // Tabla de servicios
    public static final String TABLE_SERVICES = "services";
    public static final String COLUMN_SERVICE_ID = "id";
    public static final String COLUMN_SERVICE_NAME = "name";
    // Otros campos para servicios

    // Tabla de usuarios
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USER_ID = "id";
    public static final String COLUMN_USER_USERNAME = "username";
    public static final String COLUMN_USER_PASSWORD = "password";
    // Otros campos para usuarios

    // Sentencias SQL para crear las tablas
    private static final String CREATE_TABLE_PRODUCTS = "CREATE TABLE " + TABLE_PRODUCTS + " (" +
            COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_PRODUCT_NAME + " TEXT);";

    private static final String CREATE_TABLE_SERVICES = "CREATE TABLE " + TABLE_SERVICES + " (" +
            COLUMN_SERVICE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_SERVICE_NAME + " TEXT);";

    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + " (" +
            COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_USER_USERNAME + " TEXT, " +
            COLUMN_USER_PASSWORD + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear las tablas
        db.execSQL(CREATE_TABLE_PRODUCTS);
        db.execSQL(CREATE_TABLE_SERVICES);
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Manejar actualizaciones de la base de datos si es necesario
    }
}

