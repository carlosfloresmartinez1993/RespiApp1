package com.carlosflores.respiapp1.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    public  long insertar_Palmopercusion(String tiempo){
        long id =0;
        try {
            SQLiteDatabase db = context.openOrCreateDatabase("RespiApp.db", Context.MODE_PRIVATE, null);

            // Asegúrate de que la tabla exista
            db.execSQL("CREATE TABLE IF NOT EXISTS PALMOPERCUSION (" +
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

    public Cursor obtenerPrimerUsuario() {
        SQLiteDatabase db = context.openOrCreateDatabase("RespiApp.db", Context.MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM TABLA_REGISTRO ORDER BY id ASC LIMIT 1", null);
        return cursor;
    }
    public Cursor obtenerUltimoRegistro(String nombreTabla) {
        SQLiteDatabase db = context.openOrCreateDatabase("RespiApp.db", Context.MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM " + nombreTabla + " ORDER BY id DESC LIMIT 1", null);
        return cursor;
    }

    public String valores() {
        String diafragma = "", brazos = "", popote = "", cuadrada = "", palmo = "", usuario = "";

        Cursor cursor = obtenerUltimoRegistro("RESP_DIAFRAGMATICA");
        if (cursor != null && cursor.moveToFirst()) {
            diafragma = cursor.getString(cursor.getColumnIndexOrThrow("tiempo"));
            cursor.close();
        }

        Cursor cursor1 = obtenerUltimoRegistro("RESP_MOVBRAZOS");
        if (cursor1 != null && cursor1.moveToFirst()) {
            brazos = cursor1.getString(cursor1.getColumnIndexOrThrow("tiempo"));
            cursor1.close();
        }

        Cursor cursor2 = obtenerUltimoRegistro("RESP_POPOTE");
        if (cursor2 != null && cursor2.moveToFirst()) {
            popote = cursor2.getString(cursor2.getColumnIndexOrThrow("tiempo"));
            cursor2.close();
        }

        Cursor cursor3 = obtenerUltimoRegistro("RESP_CUADRADA");
        if (cursor3 != null && cursor3.moveToFirst()) {
            cuadrada = cursor3.getString(cursor3.getColumnIndexOrThrow("tiempo"));
            cursor3.close();
        }

        Cursor cursor4 = obtenerUltimoRegistro("PALMOPERCUSION");
        if (cursor4 != null && cursor4.moveToFirst()) {
            palmo = cursor4.getString(cursor4.getColumnIndexOrThrow("tiempo"));
            cursor4.close();
        }

        Cursor cursor5 = obtenerPrimerUsuario();
        if (cursor5 != null && cursor5.moveToFirst()) {
            usuario = cursor5.getString(cursor5.getColumnIndexOrThrow("nombre"));
            cursor5.close();
        }

        return "Hola " + usuario + ", este es tu resumen semanal:\n\n" +
                "• Respiración diafragmática: " + diafragma + "\n" +
                "• Movimiento de brazos: " + brazos + "\n" +
                "• Respiración con popote: " + popote + "\n" +
                "• Respiración cuadrada: " + cuadrada + "\n" +
                "• Palmopercusión: " + palmo;
    }

}
