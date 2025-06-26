package com.carlosflores.respiapp1.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
     public static final String DB_name="RespiApp.db";
    public static final String TABLA_REGISTRO ="Tabla_Registro";
    private static  final int DBversion = 1;
     public DBHelper(Context context){
         super(context, DB_name, null, DBversion);

     }

    public void  onCreate(SQLiteDatabase sqLiteDatabase){
    sqLiteDatabase.execSQL( "CREATE TABLE IF NOT EXISTS " + TABLA_REGISTRO + "(" +
        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
        "nonmbre TEXT NOT NULL, " +
        "edad INTEGER NOT NULL )" );
    }
    public void onUpgrade( SQLiteDatabase sqLiteDatabase, int i, int i1){
         sqLiteDatabase.execSQL("DROP TABLE " + TABLA_REGISTRO);
         onCreate(sqLiteDatabase);
    }
}
