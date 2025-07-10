package com.carlosflores.respiapp1.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;

public class DBManager {
    //public static final String TABLA_REGISTRO = "Tabla_Registro";
   // public static final String RESP_DIAFRAGMATICA= "Tabla_Resp_diafragmatica";
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

            id= db.insert("TABLA_REGISTRO", null,values);
            db.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return  id;
    }

    public  long insertar_Resp_diafragmatica(String tiempo){
        long id =0;
        try {
            SQLiteDatabase db = context.openOrCreateDatabase("RespiApp.db", Context.MODE_PRIVATE, null);

            // Asegúrate de que la tabla exista
            db.execSQL("CREATE TABLE IF NOT EXISTS RESP_DIAFRAGMATICA (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "tiempo TEXT NOT NULL, " +
                    "fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");

            ContentValues values = new ContentValues();
            values.put("tiempo", tiempo);
            id= db.insert("RESP_DIAFRAGMATICA", null,values);
            db.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return  id;
    }

    public  long insertar_Resp_mov_brazos(String tiempo){
        long id =0;
        try {
            SQLiteDatabase db = context.openOrCreateDatabase("RespiApp.db", Context.MODE_PRIVATE, null);

            // Asegúrate de que la tabla exista
            db.execSQL("CREATE TABLE IF NOT EXISTS RESP_MOVBRAZOS (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "tiempo TEXT NOT NULL, " +
                    "fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");

            ContentValues values = new ContentValues();
            values.put("tiempo", tiempo);
            id= db.insert("RESP_MOVBRAZOS", null,values);
            db.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return  id;
    }
    public  long insertar_Resp_popote(String tiempo){
        long id =0;
        try {
            SQLiteDatabase db = context.openOrCreateDatabase("RespiApp.db", Context.MODE_PRIVATE, null);

            // Asegúrate de que la tabla exista
            db.execSQL("CREATE TABLE IF NOT EXISTS RESP_POPOTE (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "tiempo TEXT NOT NULL, " +
                    "fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");

            ContentValues values = new ContentValues();
            values.put("tiempo", tiempo);
            id= db.insert("RESP_MOVBRAZOS", null,values);
            db.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return  id;
    }
    public  long insertar_Resp_cuadrada(String tiempo){
        long id =0;
        try {
            SQLiteDatabase db = context.openOrCreateDatabase("RespiApp.db", Context.MODE_PRIVATE, null);

            // Asegúrate de que la tabla exista
            db.execSQL("CREATE TABLE IF NOT EXISTS RESP_CUADRADA (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "tiempo TEXT NOT NULL, " +
                    "fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");

            ContentValues values = new ContentValues();
            values.put("tiempo", tiempo);
            id= db.insert("RESP_MOVBRAZOS", null,values);
            db.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return  id;
    }
}
