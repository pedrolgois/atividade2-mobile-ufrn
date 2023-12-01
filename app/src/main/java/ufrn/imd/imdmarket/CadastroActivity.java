package ufrn.imd.imdmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroActivity extends AppCompatActivity {

    private EditText codigoEditText, nomeEditText, descricaoEditText, estoqueEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        codigoEditText = findViewById(R.id.codigo);
        nomeEditText = findViewById(R.id.nome);
        descricaoEditText = findViewById(R.id.descricao);
        estoqueEditText = findViewById(R.id.estoque);

        Button salvarButton = findViewById(R.id.salvarButton);
        Button limparButton = findViewById(R.id.limparButton);

        salvarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrarProduto();
            }
        });

        limparButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparCampos();
            }
        });
    }

    private void cadastrarProduto() {
        String codigo = codigoEditText.getText().toString();
        String nome = nomeEditText.getText().toString();
        String descricao = descricaoEditText.getText().toString();
        String estoqueStr = estoqueEditText.getText().toString();

        if (codigo.isEmpty() || nome.isEmpty() || descricao.isEmpty() || estoqueStr.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        int estoque = Integer.parseInt(estoqueStr);

        Produto produto = new Produto(codigo, nome, descricao, estoque);

        ProdutoManager.getInstance().adicionarProduto(produto);

        Toast.makeText(this, "Produto cadastrado com sucesso.", Toast.LENGTH_SHORT).show();

        limparCampos();
    }

    private void limparCampos() {
        codigoEditText.setText("");
        nomeEditText.setText("");
        descricaoEditText.setText("");
        estoqueEditText.setText("");
    }
}