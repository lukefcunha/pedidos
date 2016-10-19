package com.lukefrozz.pedidos;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.lukefrozz.pedidos.daos.ProdutoDAO;
import com.lukefrozz.pedidos.models.Produto;

public class ProdutoActivity extends AppCompatActivity {

    private EditText nome, preco;
    private Produto produto;
    private ProdutoDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        dao = new ProdutoDAO(this);
//        produto = new Produto();
        produto = dao.retrieveById(intent.getStringExtra("id") == null ? 0 : Long.parseLong(intent.getStringExtra("id")));

        nome = (EditText) findViewById(R.id.produto_ed_nome);
        preco = (EditText) findViewById(R.id.produto_ed_preco);
        if (produto != null) {
            nome.setText(produto.getNome());
            preco.setText(produto.getPreco().toString());
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.produto_fab_salvar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nome.getText().toString().isEmpty() || preco.getText().toString().isEmpty())
                    Snackbar.make(view, "Nome/preço obrigatórios", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
                else {
                    Intent intent = new Intent(view.getContext(), ProdutosActivity.class);
                    if (produto == null) {
                        produto = new Produto();
                        produto.setNome(nome.getText().toString());
                        produto.setPreco(Double.parseDouble(preco.getText().toString()));
                        dao.insert(produto);
                    } else {
                        produto.setNome(nome.getText().toString());
                        produto.setPreco(Double.parseDouble(preco.getText().toString()));
                        dao.update(produto);
                    }
                    view.getContext().startActivity(intent);
                }
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_produto, menu);
        MenuItem item = (MenuItem) menu.findItem(R.id.acao_excluir);
        if (produto == null) item.setVisible(false);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.acao_excluir:
                dao.delete(produto);
                startActivity(new Intent(this, ProdutosActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
