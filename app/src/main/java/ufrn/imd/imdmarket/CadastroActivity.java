package ufrn.imd.imdmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroActivity extends AppCompatActivity {

    private EditText codeEditText, nameEditText, descriptionEditText, stockEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        codeEditText = findViewById(R.id.codigo);
        nameEditText = findViewById(R.id.nome);
        descriptionEditText = findViewById(R.id.descricao);
        stockEditText = findViewById(R.id.estoque);

        Button saveButton = findViewById(R.id.salvarButton);
        Button clearButton = findViewById(R.id.limparButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerProduct();
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFields();
            }
        });
    }

    private void registerProduct() {
        String code = codeEditText.getText().toString();
        String name = nameEditText.getText().toString();
        String description = descriptionEditText.getText().toString();
        String stock = stockEditText.getText().toString();

        if (code.isEmpty() || name.isEmpty() || description.isEmpty() || stock.isEmpty()) {
            Toast.makeText(this, "Complete todos os campos antes de cadastrar o produto.", Toast.LENGTH_SHORT).show();
            return;
        }

        Product produto = new Product(code, name, description, Integer.parseInt(stock));
        ProductManager.getInstance().addProduct(produto);

        clearFields();
        Toast.makeText(this, "Produto cadastrado.", Toast.LENGTH_SHORT).show();
    }

    private void clearFields() {
        codeEditText.setText("");
        nameEditText.setText("");
        descriptionEditText.setText("");
        stockEditText.setText("");
    }
}