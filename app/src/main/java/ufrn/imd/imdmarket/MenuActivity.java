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

        try {
            InputStreamReader file = new InputStreamReader(openFileInput("produtos"));
            BufferedReader bufferedReader = new BufferedReader(file);
            String line = bufferedReader.readLine();

            while (line != null) {
                String[] productData = line.split(";");

                if (productData.length >= 4) {
                    Product produto = new Product(
                            productData[0],
                            productData[1],
                            productData[2],
                            Integer.parseInt(productData[3])
                    );

                    ProductManager.getInstance().addProduct(produto);
                    line = bufferedReader.readLine();
                } else {
                    Toast.makeText(this, "Linha incorreta: " + line, Toast.LENGTH_SHORT).show();
                }
            }

            file.close();

        } catch (IOException e) {
            Toast.makeText(this, "Erro ao ler o arquivo de produtos", Toast.LENGTH_LONG).show();
        }

        Button buttonCadastrarProduto = findViewById(R.id.cadastrar);
        Button buttonAlterarProdutos = findViewById(R.id.alterar);
        Button buttonDeletarProdutos = findViewById(R.id.deletar);
        Button buttonListarProdutos = findViewById(R.id.listar);

        buttonCadastrarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ProductCreateActivity.class);
                startActivity(intent);
            }
        });

        buttonAlterarProdutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ProductUpdateActivity.class);
                startActivity(intent);
            }
        });

        buttonDeletarProdutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ProductDeleteActivity.class);
                startActivity(intent);
            }
        });

        buttonListarProdutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ProductListActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            FileOutputStream arquivo = openFileOutput("produtos", Context.MODE_PRIVATE);
            OutputStreamWriter escritor = new OutputStreamWriter(arquivo);

            List<Product> listaProdutos = ProductManager.getProductList();

            for (Product produto : listaProdutos) {
                String linha = produto.getProductCode() + ";" +
                        produto.getProductName() + ";" +
                        produto.getProductDescription() + ";" +
                        produto.getProductStock() + ";\n";
                escritor.write(linha);
            }

            escritor.close();
            arquivo.close();
        } catch (IOException e) {
            Toast.makeText(this, "Não foi possível salvar o arquivo de produtos", Toast.LENGTH_LONG).show();
        }
    }
}