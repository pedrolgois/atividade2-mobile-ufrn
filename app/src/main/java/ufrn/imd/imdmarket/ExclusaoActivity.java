package ufrn.imd.imdmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ExclusaoActivity extends AppCompatActivity {

    private EditText codigoEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exclusao);

        codigoEditText = findViewById(R.id.codigo);
        Button deletarButton = findViewById(R.id.deletarButton);
        Button limparButton = findViewById(R.id.limparButton);

        deletarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletarProduto();
            }
        });

        limparButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparCampos();
            }
        });
    }

    private void deletarProduto() {
        String codigoProduto = codigoEditText.getText().toString().trim();

        if (!codigoProduto.isEmpty()) {
            boolean produtoExcluido = ProdutoManager.excluirProdutoByCodigo(codigoProduto);

            if (produtoExcluido) {
                Toast.makeText(this, "Produto excluído com sucesso!", Toast.LENGTH_SHORT).show();
                limparCampos();
            } else {
                Toast.makeText(this, "Produto não encontrado. Verifique o código.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Digite o código do produto para excluí-lo.", Toast.LENGTH_SHORT).show();
        }
    }

    private void limparCampos() {
        codigoEditText.setText("");
    }
}