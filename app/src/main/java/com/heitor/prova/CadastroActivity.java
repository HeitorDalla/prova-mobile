package com.heitor.prova;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class CadastroActivity extends AppCompatActivity {

    private EditText etNome, etCodigo, etPreco, etQuantidade;
    private Button btnSalvar, btnVerLista;
    private ProdutoDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        etNome = findViewById(R.id.etNome);
        etCodigo = findViewById(R.id.etCodigo);
        etPreco = findViewById(R.id.etPreco);
        etQuantidade = findViewById(R.id.etQuantidade);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnVerLista = findViewById(R.id.btnVerLista);

        database = ProdutoDatabase.getInstance(this);

        btnSalvar.setOnClickListener(v -> salvarProduto());

        btnVerLista.setOnClickListener(v -> {
            Intent intent = new Intent(CadastroActivity.this, ListaProdutosActivity.class);
            startActivity(intent);
        });
    }

    private void salvarProduto() {
        String nome = etNome.getText().toString().trim();
        String codigo = etCodigo.getText().toString().trim();
        String precoStr = etPreco.getText().toString().trim();
        String quantidadeStr = etQuantidade.getText().toString().trim();

        if (TextUtils.isEmpty(nome) || TextUtils.isEmpty(codigo)
                || TextUtils.isEmpty(precoStr) || TextUtils.isEmpty(quantidadeStr)) {
            Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        Pattern precoPattern = Pattern.compile("^\\d+(\\.\\d{1,2})?$");
        if (!precoPattern.matcher(precoStr).matches()) {
            Toast.makeText(this, "Preço inválido.", Toast.LENGTH_SHORT).show();
            return;
        }

        Pattern quantidadePattern = Pattern.compile("^[1-9]\\d*$");
        if (!quantidadePattern.matcher(quantidadeStr).matches()) {
            Toast.makeText(this, "Quantidade inválida.", Toast.LENGTH_SHORT).show();
            return;
        }

        double preco = Double.parseDouble(precoStr);
        int quantidade = Integer.parseInt(quantidadeStr);

        if (preco <= 0) {
            Toast.makeText(this, "O preço deve ser maior que zero.", Toast.LENGTH_SHORT).show();
            return;
        }

        Produto produto = new Produto(nome, codigo, preco, quantidade);
        database.produtoDao().insert(produto);

        Toast.makeText(this, "Produto cadastrado com sucesso!", Toast.LENGTH_SHORT).show();

        etNome.setText("");
        etCodigo.setText("");
        etPreco.setText("");
        etQuantidade.setText("");
        etNome.requestFocus();
    }
}