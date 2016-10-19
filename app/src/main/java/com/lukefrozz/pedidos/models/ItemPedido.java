package com.lukefrozz.pedidos.models;

import java.sql.Date;
import java.util.UUID;

/**
 * @author Luke F.
 * @version 1.0
 * @since 08/10/16
 */
public class ItemPedido extends EntidadeComplexa {
    private Produto produto;
    private Double quantidade;

    public Double subTotal() {
        return quantidade * produto.getPreco();
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public ItemPedido() {
        this.quantidade = 0.0;
    }

    public ItemPedido(Long id, UUID uuid, Boolean ativo) {
        super(id, uuid, ativo);
    }

    public ItemPedido(Date dataCriacao, Date dataModificacao) {
        super(dataCriacao, dataModificacao);
    }

    public ItemPedido(Long id, UUID uuid, Boolean ativo, Date dataCriacao, Date dataModificacao) {
        super(id, uuid, ativo, dataCriacao, dataModificacao);
    }

    public ItemPedido(Produto produto, Double quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public ItemPedido(Long id, UUID uuid, Boolean ativo, Produto produto, Double quantidade) {
        super(id, uuid, ativo);
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public ItemPedido(Date dataCriacao, Date dataModificacao, Produto produto, Double quantidade) {
        super(dataCriacao, dataModificacao);
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public ItemPedido(Long id, UUID uuid, Boolean ativo, Date dataCriacao, Date dataModificacao, Produto produto, Double quantidade) {
        super(id, uuid, ativo, dataCriacao, dataModificacao);
        this.produto = produto;
        this.quantidade = quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemPedido)) return false;
        if (!super.equals(o)) return false;

        ItemPedido that = (ItemPedido) o;

        return produto.equals(that.produto);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + produto.hashCode();
        result = 31 * result + quantidade.hashCode();
        return result;
    }
}
