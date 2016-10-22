package com.lukefrozz.pedidos.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lukefrozz.pedidos.R;
import com.lukefrozz.pedidos.models.Pedido;

import java.util.List;

/**
 * @author Luke F.
 * @version 1.0
 * @since 22/10/16
 */
public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.ViewHolder> {
    private List<Pedido> pedidos;

    public PedidoAdapter(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView mesaCliente, total;

        public ViewHolder(View view) {
            super(view);
            this.cv = (CardView) view.findViewById(R.id.card_pedido);
            this.mesaCliente = (TextView) cv.findViewById(R.id.card_pedido_mesa_cliente);
            this.total = (TextView) cv.findViewById(R.id.card_pedido_total);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PedidoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PedidoAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_pedido, parent, false));
    }

    public void onBindViewHolder(final PedidoAdapter.ViewHolder holder, final int position) {
        try {
            Integer mesa = Integer.parseInt(pedidos.get(position).getCliente());
            holder.mesaCliente.setText(String.format("Mesa %s", mesa));
        } catch (Exception e) {
            holder.mesaCliente.setText(pedidos.get(position).getCliente());
        }
        holder.total.setText(String.format("Total: R$ %.2f", pedidos.get(position).total()));
    }

    @Override
    public int getItemCount() {
        return pedidos.size();
    }
}
