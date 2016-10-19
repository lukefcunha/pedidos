package com.lukefrozz.pedidos.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lukefrozz.pedidos.R;
import com.lukefrozz.pedidos.models.Produto;

import java.util.List;

/**
 * @author Luke F.
 * @version 1.0
 * @since 06/10/16
 */
public class ProdutoAdapter extends ArrayAdapter<Produto> {
    private static class ViewHolder {
        TextView nome;
        TextView preco;
    }

    public ProdutoAdapter(Context context, List<Produto> produtos) {
        super(context, R.layout.item_produto, produtos);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Produto produto = getItem(position);

        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_produto, parent, false);
            viewHolder.nome = (TextView) convertView.findViewById(R.id.item_produto_nome);
            viewHolder.preco = (TextView) convertView.findViewById(R.id.item_produto_preco);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.nome.setText(produto.getNome());
        viewHolder.preco.setText(String.format("R$ %.2f", produto.getPreco()));
        return convertView;
    }

}
