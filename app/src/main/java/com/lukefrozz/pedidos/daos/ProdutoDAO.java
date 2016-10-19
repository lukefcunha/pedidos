package com.lukefrozz.pedidos.daos;

import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.lukefrozz.pedidos.models.ItemPedido;
import com.lukefrozz.pedidos.models.Produto;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Luke F.
 * @version 1.0
 * @since 06/10/16
 */
public class ProdutoDAO extends GenericDAO<Produto> {

    @Override
    public Produto insert(Produto produto) {
        openWrite();
        ContentValues values = new ContentValues();
//        produto.setUUID(UUID.randomUUID());

//        values.put("uuid", produto.getUUID().toString());
        values.put("nome", produto.getNome());
        values.put("preco", produto.getPreco());

        produto.setId(db.insert("produtos", null, values));
        close();
        return produto;
    }

    public List<Produto> retrieveDigest() {
        openRead();
        List<Produto> produtos = new ArrayList<>();
        String query = "SELECT " +
//                "uuid as uuid, " +
                "nome as nome, " +
                "preco as preco " +
                "FROM produtos " +
                "WHERE ativo = 1 " +
                "ORDER BY nome;";
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            Produto produto = new Produto();
//            produto.setUUID(UUID.fromString(cursor.getString(0).split("'")[1]));
            produto.setNome(cursor.getString(1));
            produto.setPreco(cursor.getDouble(2));
            produtos.add(produto);
        }

        cursor.close();
        close();
        return produtos;
    }

    public List<ItemPedido> retrieveItens() {
        List<ItemPedido> itens = new ArrayList<>();

        for (Produto produto : retrieveDigest()) itens.add(new ItemPedido(produto, 0.0));

        return itens;
    }

    @Override
    public Produto retrieveById(Long id) {
        openRead();
        Produto produto = new Produto();
        List<Produto> produtos = new ArrayList<>();
        String query = "SELECT " +
                "_id as id, " +
//                "uuid as uuid, " +
                "nome as nome, " +
                "preco as preco " +
                "FROM produtos " +
                "WHERE ativo = 1 AND id = ? " +
                "ORDER BY nome;";
        Cursor cursor = db.rawQuery(query, new String[]{id.toString()});

        if (cursor.moveToFirst()) {
//            produto.setUUID(UUID.fromString(cursor.getString(0).split("'")[1]));
            produto.setNome(cursor.getString(1));
            produto.setPreco(cursor.getDouble(2));
        } else
            produto = null;

        cursor.close();
        close();
        return produto;
    }

    @Override
    public Produto retrieveByUUID(String uuid) {
        openRead();
        Produto produto = new Produto();
        List<Produto> produtos = new ArrayList<>();
        String query = "SELECT " +
                "_id as id, " +
//                "uuid as uuid, " +
                "nome as nome, " +
                "preco as preco " +
                "FROM produtos " +
                "WHERE ativo = 1 AND uuid = ? " +
                "ORDER BY nome;";
        Cursor cursor = db.rawQuery(query, new String[]{String.format("'%s'", uuid)});

        if (cursor.moveToFirst()) {
//            produto.setUUID(UUID.fromString(cursor.getString(0).split("'")[1]));
            produto.setNome(cursor.getString(1));
            produto.setPreco(cursor.getDouble(2));
        } else
            produto = null;

        cursor.close();
        close();
        return produto;
    }

    @Override
    public Produto update(Produto produto) {
        openWrite();
        ContentValues values = new ContentValues();

        values.put("nome", produto.getNome());
        values.put("preco", produto.getPreco());
        values.put("data_modificacao", "CURRENT_TIMESTAMP");

        int i = db.update("produtos", values, ("_id = "+produto.getId().toString()), null);
        close();
        return produto;
    }

    @Override
    public Produto delete(Produto produto) {
        openWrite();
        ContentValues values = new ContentValues();

        values.put("ativo", 0);
        values.put("data_modificacao", "CURRENT_TIMESTAMP");

        int i = db.update("produtos", values, "uuid LIKE '%"+produto.getUUID().toString()+"%'", null);
        close();
        return produto;
    }

    public ProdutoDAO(Context context) {
        super(context);
    }
}
