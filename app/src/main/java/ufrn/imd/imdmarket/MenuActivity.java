package ufrn.imd.imdmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        lerArquivoProdutos();

        Button btnCadastrar = findViewById(R.id.cadastrar);
        Button btnAlterar = findViewById(R.id.alterar);
        Button btnExcluir = findViewById(R.id.deletar);
        Button btnListar = findViewById(R.id.listar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, CadastroActivity.class);
                startActivity(intent);
            }
        });

        btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, AlteracaoActivity.class);
                startActivity(intent);
            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ExclusaoActivity.class);
                startActivity(intent);
            }
        });

        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ListagemActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        salvarArquivoProdutos();
    }

    private void lerArquivoProdutos() {
        try {
            InputStreamReader arquivo = new InputStreamReader(openFileInput("produtos"));
            BufferedReader bufferedReader = new BufferedReader(arquivo);
            String linha = bufferedReader.readLine();

            while (linha != null) {
                String[] dadosProduto = linha.split(";");

                if (dadosProduto.length >= 4) {
                    Produto produto = new Produto(
                            dadosProduto[0],
                            dadosProduto[1],
                            dadosProduto[2],
                            Integer.parseInt(dadosProduto[3])
                    );

                    ProdutoManager.getInstance().adicionarProduto(produto);
                    linha = bufferedReader.readLine();
                } else {
                    Toast.makeText(this, "Linha inválida: " + linha, Toast.LENGTH_SHORT).show();
                }
            }

            arquivo.close();
        } catch (IOException e) {
            Toast.makeText(this, "Não foi possível ler o arquivo de produtos", Toast.LENGTH_LONG).show();
        }
    }

    private void salvarArquivoProdutos() {
        try {
            FileOutputStream arquivo = openFileOutput("produtos", Context.MODE_PRIVATE);
            OutputStreamWriter escritor = new OutputStreamWriter(arquivo);

            List<Produto> listaProdutos = ProdutoManager.getListaProdutos();

            for (Produto produto : listaProdutos) {
                String linha = produto.getCodigoProduto() + ";" +
                        produto.getNomeProduto() + ";" +
                        produto.getDescricaoProduto() + ";" +
                        produto.getEstoque() + ";\n";
                escritor.write(linha);
            }

            escritor.close();
            arquivo.close();
        } catch (IOException e) {
            Toast.makeText(this, "Não foi possível salvar o arquivo de produtos", Toast.LENGTH_LONG).show();
        }
    }
}