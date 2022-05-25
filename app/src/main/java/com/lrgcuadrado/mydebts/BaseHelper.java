package com.lrgcuadrado.mydebts;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

// Este sirve para poder crear la base
public class BaseHelper extends SQLiteOpenHelper {

    String tabla="CREATE TABLE debts(id INTEGER PRIMARY KEY, persona TEXT, razon TEXT, dinero TEXT)";
    public BaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Le digo que cree mi tabla
        db.execSQL(tabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE debts");
        db.execSQL(tabla);
    }

}
