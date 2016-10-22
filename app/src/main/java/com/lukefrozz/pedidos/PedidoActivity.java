package com.lukefrozz.pedidos;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.lukefrozz.pedidos.adapters.ItemPedidoAdapter;
import com.lukefrozz.pedidos.daos.PedidoDAO;
import com.lukefrozz.pedidos.daos.ProdutoDAO;
import com.lukefrozz.pedidos.models.ItemPedido;
import com.lukefrozz.pedidos.models.Pedido;
import com.lukefrozz.pedidos.models.Produto;

import java.util.List;

public class PedidoActivity extends AppCompatActivity {

    private RecyclerView itensRecicler;
    private EditText clienteMesa;
    private CheckBox delivery;
    private PedidoDAO pedidoDAO;
    private Pedido pedido;
    private List<ItemPedido> itens;
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        pedidoDAO = new PedidoDAO(this);

        delivery = (CheckBox) findViewById(R.id.pedido_checkbox_delivery);
        clienteMesa = (EditText) findViewById(R.id.pedido_ed_cliente_mesa);
        itensRecicler = (RecyclerView) findViewById(R.id.pedido_recycle_itens);
        itensRecicler.setHasFixedSize(true);
        itensRecicler.setLayoutManager(new LinearLayoutManager(this));
        itens = new ProdutoDAO(this).retrieveItens();
        pedido = new Pedido();

        ItemPedidoAdapter IPA = new ItemPedidoAdapter(itens, ((TextView) findViewById(R.id.total_pedido)));
        itensRecicler.setAdapter(IPA);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pedido.setDelivery(delivery.isChecked());
                pedido.setCliente(clienteMesa.getText().toString());
                pedido.setItens(itens);
                pedidoDAO.insert(pedido);
                startActivity(new Intent(view.getContext(), MainActivity.class));
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
