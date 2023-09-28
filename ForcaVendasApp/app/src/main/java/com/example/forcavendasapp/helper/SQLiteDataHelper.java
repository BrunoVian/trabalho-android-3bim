package com.example.forcavendasapp.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteDataHelper extends SQLiteOpenHelper {

    public SQLiteDataHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE CLIENTE (CODIGO INTEGER, NOME VARCHAR(100), CPF VARCHAR(100), DTNASCIMENTO VARCHAR(50), CODENDERECO INTEGER)");
        sqLiteDatabase.execSQL("CREATE TABLE ENDERECO (CODIGO INTEGER, LOGRADOURO VARCHAR(100), NUMERO INTEGER, BAIRRO VARCHAR(100), CIDADE VARCHAR(100), UF VARCHAR(2))");
        sqLiteDatabase.execSQL("CREATE TABLE ITEM (CODIGO INTEGER, DESCRICAO VARCHAR(100), VLRUNIT INTEGER, UNMEDIDA VARCHAR(50))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
