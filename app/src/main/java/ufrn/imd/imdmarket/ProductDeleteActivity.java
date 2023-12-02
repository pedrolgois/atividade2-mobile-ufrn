package ufrn.imd.imdmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProductDeleteActivity extends AppCompatActivity {

    private EditText codeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exclusao);

        codeEditText = findViewById(R.id.codigo);
        Button deleteButton = findViewById(R.id.deletarButton);
        Button clearButton = findViewById(R.id.limparButton);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteProduct();
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codeEditText.setText("");
            }
        });
    }

    private void deleteProduct() {
        String productCode = codeEditText.getText().toString().trim();

        if (!productCode.isEmpty()) {
            boolean deletedProduct = ProductManager.removeProductByCode(productCode);

            if (deletedProduct) {
                Toast.makeText(this, "Produto removido com sucesso!", Toast.LENGTH_SHORT).show();
                codeEditText.setText("");
            } else {
                Toast.makeText(this, "Produto não encontrado.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Digite o código do produto.", Toast.LENGTH_SHORT).show();
        }
    }
}