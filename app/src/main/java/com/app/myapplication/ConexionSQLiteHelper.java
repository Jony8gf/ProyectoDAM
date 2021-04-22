package com.app.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexionSQLiteHelper  extends SQLiteOpenHelper {

    final String CREAR_TABLA_FRASE = "CREATE TABLE frase (id Integer, nombre TEXT, tipo TEXT, idioma TEXT)";

    public ConexionSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREAR_TABLA_FRASE);

        //Insertar Frases Yo Nunca
        db.execSQL("Insert into frase values (1,'Yo nunca me ', 'YN', 'English')");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {

        db.execSQL("DROP TABLE IF EXISTS frase");
        onCreate(db);

    }

}
