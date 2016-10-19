package com.lukefrozz.pedidos.models;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.UUID;

/**
 * Classe representativa de uma Entidade Simples
 * @author Luke F.
 * @version 1.0
 * @since 05/10/16
 */
public abstract class EntidadeSimples implements Serializable {
    protected Long id;
    protected UUID uuid;
    protected Boolean ativo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUUID() {
        return uuid;
    }

    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public EntidadeSimples() {
        this.ativo = true;
    }

    public EntidadeSimples(Long id, UUID uuid, Boolean ativo) {
        this.id = id;
        this.uuid = uuid;
        this.ativo = ativo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntidadeSimples)) return false;

        EntidadeSimples that = (EntidadeSimples) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }
}
