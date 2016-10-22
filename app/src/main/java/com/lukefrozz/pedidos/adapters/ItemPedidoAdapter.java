package com.lukefrozz.pedidos.adapters;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.lukefrozz.pedidos.R;
import com.lukefrozz.pedidos.models.ItemPedido;
import com.lukefrozz.pedidos.models.Pedido;

import java.util.List;

/**
 * @author Luke F.
 * @version 1.0
 * @since 08/10/16
 */
public class ItemPedidoAdapter extends RecyclerView.Adapter<ItemPedidoAdapter.ViewHolder> {

    List<ItemPedido> itens;
    TextView total;
    private Pedido pedido;

    public ItemPedidoAdapter(List<ItemPedido> itens, TextView total) {
        this.itens = itens;
        this.total = total;
        this.pedido = new Pedido();
        this.pedido.setItens(this.itens);
        this.total.setText(String.format("Total: R$ %.2f", pedido.total()));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        ImageButton add, del;
        TextView nome, preco, qtd;
        public ViewHolder(View view) {
            super(view);
            cv = (CardView) view.findViewById(R.id.card_item_pedido);
            add = (ImageButton) cv.findViewById(R.id.add_qtd);
            del = (ImageButton) cv.findViewById(R.id.del_qtd);
            nome = (TextView) cv.findViewById(R.id.produto_nome);
            preco = (TextView) cv.findViewById(R.id.produto_preco);
            qtd = (TextView) cv.findViewById(R.id.item_qtd);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
            .inflate(R.layout.card_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.nome.setText(itens.get(position).getProduto().getNome());
        holder.preco.setText(String.format("R$ %.2f", itens.get(position).getProduto().getPreco()));
        holder.qtd.setText(String.format("%.0f", itens.get(position).getQuantidade()));
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itens.get(position).setQuantidade(itens.get(position).getQuantidade()+1);
                holder.qtd.setText(String.format("%.0f", itens.get(position).getQuantidade()));
                total.setText(String.format("Total: R$ %.2f", pedido.total()));
            }
        });

        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itens.get(position).getQuantidade() > 0) {
                    itens.get(position).setQuantidade(itens.get(position).getQuantidade()-1);
                    holder.qtd.setText(String.format("%.0f", itens.get(position).getQuantidade()));
                    total.setText(String.format("Total: R$ %.2f", pedido.total()));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return itens.size();
    }
}
