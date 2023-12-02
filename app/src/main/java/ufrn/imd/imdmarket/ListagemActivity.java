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

public class ListagemActivity extends AppCompatActivity {

    private ArrayAdapter<Product> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem);

        ListView listaView = findViewById(R.id.lista);
        Button voltarButton = findViewById(R.id.voltarButton);

        List<Product> listaProdutos = ProductManager.getProductList();

        if (listaProdutos != null) {
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaProdutos);
            listaView.setAdapter(adapter);

            listaView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    Product produto = adapter.getItem(position);

                    if (produto != null) {
                        String mensagem = "Código: " + produto.getProductCode() + "\n" +
                                "Nome: " + produto.getProductName() + "\n" +
                                "Descrição: " + produto.getProductDescription() + "\n" +
                                "Estoque: " + produto.getProductStock();

                        AlertDialog.Builder builder = new AlertDialog.Builder(ListagemActivity.this);
                        builder.setMessage(mensagem)
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

        voltarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
