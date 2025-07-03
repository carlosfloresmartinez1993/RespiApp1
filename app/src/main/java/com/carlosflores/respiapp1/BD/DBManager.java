package com.carlosflores.respiapp1.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;

public class DBManager {
    public static final String TABLA_REGISTRO = "Tabla_Registro";
    Context context;
    public DBManager(Context context) {
        //super(context);
        this.context= context;
    }

    public  long insertar(String nombre, String curp){
        long id =0;
        try {
          SQLiteDatabase db = context.openOrCreateDatabase("RespiApp.db", Context.MODE_PRIVATE, null);

            // Asegúrate de que la tabla exista
            db.execSQL("CREATE TABLE IF NOT EXISTS TABLA_REGISTRO (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nombre TEXT NOT NULL, " +
                    "curp TEXT NOT NULL)");

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("curp", curp);

            id= db.insert(TABLA_REGISTRO, null,values);
            db.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return  id;
    }
}
