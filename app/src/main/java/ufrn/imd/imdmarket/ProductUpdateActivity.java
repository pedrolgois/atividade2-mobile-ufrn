package ufrn.imd.imdmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProductUpdateActivity extends AppCompatActivity {

    private EditText codeEditText, nameEditText, descriptionEditText, stockEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alteracao);

        codeEditText = findViewById(R.id.codigo);
        nameEditText = findViewById(R.id.nome);
        descriptionEditText = findViewById(R.id.descricao);
        stockEditText = findViewById(R.id.estoque);

        Button saveButton = findViewById(R.id.salvarButton);
        Button clearButton = findViewById(R.id.limparButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProduct();
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFields();
            }
        });
    }

    private void updateProduct() {
        String code = codeEditText.getText().toString();
        String name = nameEditText.getText().toString();
        String description = descriptionEditText.getText().toString();
        String stock = stockEditText.getText().toString();

        if (code.isEmpty()) {
            Toast.makeText(this, "Preencha o campo de código do produto", Toast.LENGTH_SHORT).show();
            return;
        }

        Product product = ProductManager.getProductByCode(code);

        if (product != null) {
            if (!name.isEmpty()) {
                product.setProductName(name);
            }

            if (!description.isEmpty()) {
                product.setProductDescription(description);
            }

            if (!stock.isEmpty()) {
                product.setProductStock(Integer.parseInt(stock));
            }

            Toast.makeText(this, "Produto alterado com sucesso.", Toast.LENGTH_SHORT).show();

            clearFields();
        } else {
            Toast.makeText(this, "Produto não encontrado.", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearFields() {
        codeEditText.setText("");
        nameEditText.setText("");
        descriptionEditText.setText("");
        stockEditText.setText("");
    }
}