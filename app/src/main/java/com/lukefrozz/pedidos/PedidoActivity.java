package com.lukefrozz.pedidos;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.lukefrozz.pedidos.adapters.ItemPedidoAdapter;
import com.lukefrozz.pedidos.daos.ProdutoDAO;
import com.lukefrozz.pedidos.models.ItemPedido;
import com.lukefrozz.pedidos.models.Pedido;
import com.lukefrozz.pedidos.models.Produto;

import java.util.List;

public class PedidoActivity extends AppCompatActivity {

    private RecyclerView itensRecicler;
    private Pedido pedido;
    private List<ItemPedido> itens;
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        itensRecicler = (RecyclerView) findViewById(R.id.pedido_recycle_itens);
        itensRecicler.setHasFixedSize(true);
        itensRecicler.setLayoutManager(new LinearLayoutManager(this));
        itens = new ProdutoDAO(this).retrieveItens();
        pedido = new Pedido();

        ItemPedidoAdapter IPA = new ItemPedidoAdapter(itens);
        itensRecicler.setAdapter(IPA);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
