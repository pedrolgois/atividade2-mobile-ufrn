package ufrn.imd.imdmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AlteracaoActivity extends AppCompatActivity {

    private EditText codigoEditText, nomeEditText, descricaoEditText, estoqueEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alteracao);

        codigoEditText = findViewById(R.id.codigo);
        nomeEditText = findViewById(R.id.nome);
        descricaoEditText = findViewById(R.id.descricao);
        estoqueEditText = findViewById(R.id.estoque);

        Button salvarButton = findViewById(R.id.salvarButton);
        Button limparButton = findViewById(R.id.limparButton);

        salvarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alterarProduto();
            }
        });

        limparButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparCampos();
            }
        });
    }

    private void alterarProduto() {
        String codigo = codigoEditText.getText().toString();
        String nome = nomeEditText.getText().toString();
        String descricao = descricaoEditText.getText().toString();
        String estoqueStr = estoqueEditText.getText().toString();

        if (codigo.isEmpty()) {
            Toast.makeText(this, "Preencha o campo de código do produto", Toast.LENGTH_SHORT).show();
            return;
        }

        Produto produto = ProdutoManager.getProdutoByCodigo(codigo);

        if (produto != null) {
            if (!nome.isEmpty()) {
                produto.setNomeProduto(nome);
            }

            if (!descricao.isEmpty()) {
                produto.setDescricaoProduto(descricao);
            }

            if (!estoqueStr.isEmpty()) {
                int estoque = Integer.parseInt(estoqueStr);
                produto.setEstoque(estoque);
            }

            Toast.makeText(this, "Produto alterado com sucesso.", Toast.LENGTH_SHORT).show();

            limparCampos();
        } else {
            Toast.makeText(this, "Produto não encontrado.", Toast.LENGTH_SHORT).show();
        }
    }

    private void limparCampos() {
        codigoEditText.setText("");
        nomeEditText.setText("");
        descricaoEditText.setText("");
        estoqueEditText.setText("");
    }
}