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
        String tbProdutos = "CREATE TABLE produtos ( " +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "data_criacao DATETIME DEFAULT CURRENT_TIMESTAMP, " +
            "data_modificacao DATETIME DEFAULT CURRENT_TIMESTAMP, " +
            "ativo INTEGER DEFAULT 1, " +
            "nome TEXT, " +
            "preco DECIMAL(20,2) " +
        ");";

        String tbPedidos = "CREATE TABLE pedidos ( " +
            "_id INTEGER  PRIMARY KEY AUTOINCREMENT, " +
            "date_created DATETIME DEFAULT (CURRENT_TIMESTAMP), " +
            "date_modified DATETIME DEFAULT (CURRENT_TIMESTAMP), " +
            "delivery BOOLEAN, " +
            "mesa_cliente TEXT, " +
            "status INTEGER DEFAULT (1)" +
        ");";

        String tbProdutosPedido = "CREATE TABLE produtos_pedido ( " +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "ativo BOOLEAN DEFAULT 1, " +
            "pedido_id INTEGER REFERENCES pedidos (_id), " +
            "produto_id INTEGER REFERENCES produtos (_id), " +
            "preco DOUBLE, " +
            "quantidade DOUBLE " +
        ");";

        sqLiteDatabase.execSQL(tbProdutos);
        sqLiteDatabase.execSQL(tbPedidos);
        sqLiteDatabase.execSQL(tbProdutosPedido);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS produtos;");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS pedidos;");

        onCreate(sqLiteDatabase);
    }
}
