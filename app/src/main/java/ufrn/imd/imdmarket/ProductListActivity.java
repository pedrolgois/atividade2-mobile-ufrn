package ufrn.imd.imdmarket;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.app.AlertDialog;
import android.content.DialogInterface;

import java.util.List;

public class ProductListActivity extends AppCompatActivity {

    private ArrayAdapter<Product> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem);

        ListView listView = findViewById(R.id.lista);
        Button goBackButton = findViewById(R.id.voltarButton);

        List<Product> productList = ProductManager.getProductList();

        if (productList != null) {
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, productList);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    Product product = adapter.getItem(position);

                    if (product != null) {
                        String message = "Código: " + product.getProductCode() + "\n" +
                                "Nome: " + product.getProductName() + "\n" +
                                "Descrição: " + product.getProductDescription() + "\n" +
                                "Estoque: " + product.getProductStock();

                        AlertDialog.Builder builder = new AlertDialog.Builder(ProductListActivity.this);
                        builder.setMessage(message)
                                .setTitle("Detalhes do Produto")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                    }
                                });
                        builder.create().show();
                    }
                }
            });
        }

        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
