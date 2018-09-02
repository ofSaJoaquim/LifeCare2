package com.example.casa.lifecare.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriarBanco extends SQLiteOpenHelper {

    private static final String dataBaseName="lifecareLocal.db";
    private static final String tabela ="usuario";
    private static final String Id ="id_usuario";
    private static final String nome ="nome_usuario";
    private static final String senha="senha_usuario";



    public CriarBanco(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
