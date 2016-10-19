package com.lukefrozz.pedidos.models;

import android.content.ClipData;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Luke F.
 * @version 1.0
 * @since 08/10/16
 */
public class Pedido extends EntidadeComplexa {
    private String cliente;
    private boolean delivery;
    private List<ItemPedido> itens;

    public Double total() {
        Double total = 0.0;

        for (ItemPedido itemPedido : itens) total += itemPedido.subTotal();

        return total;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public void addItem(ItemPedido item) {
        this.itens.add(item);
    }

    public Pedido() {
        itens = new ArrayList<>();
    }

    public Pedido(Long id, UUID uuid, Boolean ativo) {
        super(id, uuid, ativo);
        itens = new ArrayList<>();
    }

    public Pedido(Date dataCriacao, Date dataModificacao) {
        super(dataCriacao, dataModificacao);
    }

    public Pedido(Long id, UUID uuid, Boolean ativo, Date dataCriacao, Date dataModificacao) {
        super(id, uuid, ativo, dataCriacao, dataModificacao);
    }

    public Pedido(String cliente, boolean delivery, List<ItemPedido> itens) {
        this.cliente = cliente;
        this.delivery = delivery;
        this.itens = itens;
    }

    public Pedido(Long id, UUID uuid, Boolean ativo, String cliente, boolean delivery, List<ItemPedido> itens) {
        super(id, uuid, ativo);
        this.cliente = cliente;
        this.delivery = delivery;
        this.itens = itens;
    }

    public Pedido(Date dataCriacao, Date dataModificacao, String cliente, boolean delivery, List<ItemPedido> itens) {
        super(dataCriacao, dataModificacao);
        this.cliente = cliente;
        this.delivery = delivery;
        this.itens = itens;
    }

    public Pedido(Long id, UUID uuid, Boolean ativo, Date dataCriacao, Date dataModificacao, String cliente, boolean delivery, List<ItemPedido> itens) {
        super(id, uuid, ativo, dataCriacao, dataModificacao);
        this.cliente = cliente;
        this.delivery = delivery;
        this.itens = itens;
    }
}
