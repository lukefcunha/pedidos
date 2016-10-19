package com.lukefrozz.pedidos.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Luke F.
 * @version 1.0
 * @since 06/10/16
 */
public class DBOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "pedidos.db3";

    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String tbProduto = "CREATE TABLE produtos ( " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                "uuid TEXT, " +
                "data_criacao DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                "data_modificacao DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                "ativo INTEGER DEFAULT 1, " +
                "nome TEXT, " +
                "preco DOUBLE " +
            ");";

        sqLiteDatabase.execSQL(tbProduto);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS produtos;");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS pedidos;");

        onCreate(sqLiteDatabase);
    }
}
