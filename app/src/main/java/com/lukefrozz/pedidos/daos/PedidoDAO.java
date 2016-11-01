package com.lukefrozz.pedidos.daos;

import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

import com.lukefrozz.pedidos.models.ItemPedido;
import com.lukefrozz.pedidos.models.Pedido;
import com.lukefrozz.pedidos.models.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Luke F.
 * @version 1.0
 * @since 20/10/16
 */
public class PedidoDAO extends GenericDAO<Pedido> {

    private ProdutosPedidoDAO produtosPedidoDAO;

    @Override
    public Pedido insert(Pedido pedido) {
        openWrite();
        ContentValues values = new ContentValues();

        values.put("delivery", pedido.isDelivery() ? 1 : 0);
        values.put("mesa_cliente", pedido.getCliente());

        pedido.setId(db.insert("pedidos", null, values));
        close();

        for (ItemPedido item: pedido.getItens())
            if (item.getQuantidade() > 0)
                produtosPedidoDAO.insert(item, pedido.getId());

        return pedido;
    }

    @Override
    public List<Pedido> retrieveDigest() { return null; }

    public List<Pedido> retrieveDigest(Integer... statuses) {
        openRead();
        List<Pedido> pedidos = new ArrayList<>();
        String query = "SELECT " +
            "_id as id, " +
            "delivery as delivery, " +
            "mesa_cliente as mesa_cliente " +
            "FROM pedidos " +
            "WHERE status IN (%s);";

        StringBuilder params = new StringBuilder();
        for (Integer status : statuses)
            if (params.length() > 0)
                params.append(String.format(", %d", status));
            else
                params.append(status.toString());
        query = String.format(query, params);
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            Pedido pedido = new Pedido();

            pedido.setItens(produtosPedidoDAO.retrieveDigest(cursor.getLong(0), false));
            pedido.setDelivery(Boolean.parseBoolean(cursor.getString(1)));
            pedido.setCliente(cursor.getString(2));

            pedidos.add(pedido);
        }

        cursor.close();
        close();
        return pedidos;
    }

    @Override
    public Pedido retrieveById(Long id) {
        return null;
    }

    @Override
    public Pedido retrieveByUUID(String uuid) {
        return null;
    }

    @Override
    public Pedido update(Pedido pedido) {
        openWrite();
        ContentValues values = new ContentValues();

        values.put("mesa_cliente", pedido.getCliente().toString());
        values.put("delivery", pedido.isDelivery() ? 1 : 0);
        values.put("status", pedido.getStatus());
//        values.put("data_modificacao", "CURRENT_TIMESTAMP");

        for (ItemPedido item : pedido.getItens())
            if (item.getId() != null)
                produtosPedidoDAO.update(item);
            else
                produtosPedidoDAO.insert(item, pedido.getId());

        int i = db.update("produtos", values, String.format("_id = %s", pedido.getId()), null);
        close();
        return pedido;
    }

    @Override
    public Pedido delete(Pedido pedido) {
        return null;
    }

    public PedidoDAO(Context context) {
        super(context);
        produtosPedidoDAO = new ProdutosPedidoDAO(context);
    }
}
