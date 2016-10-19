package com.lukefrozz.pedidos.models;

import java.sql.Date;
import java.util.UUID;

/**
 * @author Luke F.
 * @version 1.0
 * @since 06/10/16
 */
public class Produto extends EntidadeComplexa {
    private String nome;
    private Double preco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Produto() {
    }

    public Produto(Long id, UUID uuid, Boolean ativo) {
        super(id, uuid, ativo);
    }

    public Produto(Date dataCriacao, Date dataModificacao) {
        super(dataCriacao, dataModificacao);
    }

    public Produto(Long id, UUID uuid, Boolean ativo, Date dataCriacao, Date dataModificacao) {
        super(id, uuid, ativo, dataCriacao, dataModificacao);
    }

    public Produto(String nome, Double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public Produto(Long id, UUID uuid, Boolean ativo, String nome, Double preco) {
        super(id, uuid, ativo);
        this.nome = nome;
        this.preco = preco;
    }

    public Produto(Date dataCriacao, Date dataModificacao, String nome, Double preco) {
        super(dataCriacao, dataModificacao);
        this.nome = nome;
        this.preco = preco;
    }

    public Produto(Long id, UUID uuid, Boolean ativo, Date dataCriacao, Date dataModificacao, String nome, Double preco) {
        super(id, uuid, ativo, dataCriacao, dataModificacao);
        this.nome = nome;
        this.preco = preco;
    }
}
