package com.heitor.prova;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListaProdutosActivity extends AppCompatActivity {

    private RecyclerView recyclerViewProdutos;
    private Button btnVoltar;
    private ProdutoDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtos);

        recyclerViewProdutos = findViewById(R.id.recyclerViewProdutos);
        btnVoltar = findViewById(R.id.btnVoltar);

        recyclerViewProdutos.setLayoutManager(new LinearLayoutManager(this));

        database = ProdutoDatabase.getInstance(this);

        List<Produto> lista = database.produtoDao().getAllProdutos();
        ProdutoAdapter adapter = new ProdutoAdapter(lista);
        recyclerViewProdutos.setAdapter(adapter);

        btnVoltar.setOnClickListener(v -> finish());
    }
}