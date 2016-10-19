package com.lukefrozz.pedidos.models;

import org.json.JSONObject;

import java.sql.Date;
import java.util.UUID;

/**
 * @author Luke F.
 * @version 1.0
 * @since 06/10/16
 */
public abstract class EntidadeComplexa extends EntidadeSimples {
    protected Date dataCriacao;
    protected Date dataModificacao;

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(Date dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    public EntidadeComplexa() {
    }

    public EntidadeComplexa(Long id, UUID uuid, Boolean ativo) {
        super(id, uuid, ativo);
    }

    public EntidadeComplexa(Date dataCriacao, Date dataModificacao) {
        this.dataCriacao = dataCriacao;
        this.dataModificacao = dataModificacao;
    }

    public EntidadeComplexa(Long id, UUID uuid, Boolean ativo, Date dataCriacao, Date dataModificacao) {
        super(id, uuid, ativo);
        this.dataCriacao = dataCriacao;
        this.dataModificacao = dataModificacao;
    }
}
