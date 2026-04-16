package com.heitor.prova;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
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

        // Filtro para permitir apenas 2 casas decimais e aceitar ponto ou vírgula
        etPreco.setFilters(new InputFilter[]{new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String replacement = source.subSequence(start, end).toString();
                String newVal = dest.subSequence(0, dstart).toString() + replacement + dest.subSequence(dend, dest.length());
                
                // Regex: permite números opcionais, seguidos de UM ponto ou vírgula opcional, e até 2 números depois
                if (!newVal.matches("^\\d*([.,]\\d{0,2})?$")) {
                    return "";
                }
                return null;
            }
        }});

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

        // Converte vírgula para ponto para que o Double.parseDouble funcione
        precoStr = precoStr.replace(",", ".");

        try {
            double preco = Double.parseDouble(precoStr);
            int quantidade = Integer.parseInt(quantidadeStr);

            if (preco <= 0) {
                Toast.makeText(this, "O preço deve ser maior que zero.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (quantidade <= 0) {
                Toast.makeText(this, "A quantidade deve ser maior que zero.", Toast.LENGTH_SHORT).show();
                return;
            }

            Produto produto = new Produto(nome, codigo, preco, quantidade);
            database.produtoDao().insert(produto);

            Toast.makeText(this, "Produto cadastrado com sucesso!", Toast.LENGTH_SHORT).show();

            limparCampos();

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Erro no formato dos números.", Toast.LENGTH_SHORT).show();
        }
    }

    private void limparCampos() {
        etNome.setText("");
        etCodigo.setText("");
        etPreco.setText("");
        etQuantidade.setText("");
        etNome.requestFocus();
    }
}