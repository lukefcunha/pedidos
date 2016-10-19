package com.lukefrozz.pedidos.daos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.lukefrozz.pedidos.helpers.DBOpenHelper;
import com.lukefrozz.pedidos.models.Produto;

import java.util.List;

/**
 * @author Luke F.
 * @version 1.0
 * @since 06/10/16
 */
public abstract class GenericDAO<T> {
    protected SQLiteDatabase db;
    protected DBOpenHelper openHelper;

    public GenericDAO(Context context) {
        openHelper = new DBOpenHelper(context);
    }

    public void openWrite() { db = openHelper.getWritableDatabase(); }
    public void openRead() { db = openHelper.getReadableDatabase(); }
    public void close() { db.close(); }

    public abstract T insert(T t);
    public abstract List<T> retrieveDigest();
    public abstract T retrieveById(Long id);
    public abstract T retrieveByUUID(String uuid);
    public abstract T update(T t);
    public abstract T delete(T t);

}
