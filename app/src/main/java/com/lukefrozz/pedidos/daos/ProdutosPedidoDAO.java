package com.lukefrozz.pedidos.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.lukefrozz.pedidos.models.ItemPedido;
import com.lukefrozz.pedidos.models.Produto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Luke F.
 * @version 1.0
 * @since 20/10/16
 */
public class ProdutosPedidoDAO extends GenericDAO<ItemPedido> {

    public List<Produto> produtos() {
        openRead();
        List<Produto> produtos = new ArrayList<>();
        String query = "SELECT " +
                "_id as id, " +
                "nome as nome, " +
                "preco as preco, " +
                "ativo as ativo " +
                "FROM produtos " +
                "ORDER BY nome;";
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            Produto produto = new Produto();
            produto.setId(cursor.getLong(0));
            produto.setNome(cursor.getString(1));
            produto.setPreco(cursor.getDouble(2));
            produto.setAtivo(Boolean.parseBoolean(cursor.getString(3)));
            produtos.add(produto);
        }

        cursor.close();
        close();
        return produtos;
    }

    public ItemPedido insert(ItemPedido itemPedido, Long pedidoId) {
        openWrite();
        ContentValues values = new ContentValues();

        values.put("produto_id", itemPedido.getProduto().getId());
        values.put("quantidade", itemPedido.getQuantidade());
        values.put("preco", itemPedido.getProduto().getPreco());
        values.put("pedido_id", pedidoId);

        itemPedido.setId(db.insert("produtos_pedido", null, values));
        close();
        return itemPedido;
    }

    @Override
    public ItemPedido insert(ItemPedido itemPedido) {
        return null;
    }

    @Override
    public List<ItemPedido> retrieveDigest() { return null; }

    public List<ItemPedido> retrieveDigest(Long id, boolean kind) {
        openRead();
        List<ItemPedido> itens = new ArrayList<>();
        String query = "SELECT " +
            "pr_p._id as _id, " +
            "pr_p.produto_id as produto_id, " +
            "pr.nome as nome, " +
            "pr_p.preco as preco, " +
            "pr_p.quantidade as quantidade " +
            "FROM produtos_pedido AS pr_p " +
            "INNER JOIN produtos AS pr ON pr._id = pr_p.produto_id " +
            "WHERE pr_p.pedido_id = %s " +
            "ORDER BY pr.nome";
        query = String.format(query, id);
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            ItemPedido item = new ItemPedido();

            item.setId(cursor.getLong(0));
            Produto produto = new Produto();
            produto.setId(cursor.getLong(1));
            produto.setNome(cursor.getString(2));
            produto.setPreco(cursor.getDouble(3));
            item.setProduto(produto);
            item.setQuantidade(cursor.getDouble(4));

            itens.add(item);
        }

        if (kind) {
            for (Produto produto: produtos())
                if (!itens.contains(produto) && produto.getAtivo()) {
                    ItemPedido item = new ItemPedido();
                    item.setProduto(produto);
                    item.setQuantidade(0.0);
                    itens.add(item);
                }
            Collections.sort(itens, ItemPedido.ItemPedidoNomeProdutoComparator);
        }

        cursor.close();
        close();
        return itens;
    }

    @Override
    public ItemPedido retrieveById(Long id) {
        return null;
    }

    @Override
    public ItemPedido retrieveByUUID(String uuid) {
        return null;
    }

    @Override
    public ItemPedido update(ItemPedido itemPedido) {
        openWrite();
        ContentValues values = new ContentValues();

        values.put("quantidade", itemPedido.getQuantidade());
        values.put("preco", itemPedido.getProduto().getPreco());
//        values.put("produto_id", itemPedido.getProduto().getId());
//        values.put("data_modificacao", "CURRENT_TIMESTAMP");

        int i = db.update("produtos_pedido", values, String.format("_id = %s",itemPedido.getId()), null);
        close();
        return itemPedido;
    }

    @Override
    public ItemPedido delete(ItemPedido itemPedido) {
        return null;
    }

    public ProdutosPedidoDAO(Context context) {
        super(context);
    }

}
