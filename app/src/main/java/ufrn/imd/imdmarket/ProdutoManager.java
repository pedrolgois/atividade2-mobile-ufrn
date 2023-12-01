package ufrn.imd.imdmarket;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProdutoManager {

    private static ProdutoManager instance;
    private static List<Produto> listaProdutos;

    private ProdutoManager() {
        listaProdutos = new ArrayList<>();
    }

    public static synchronized ProdutoManager getInstance() {
        if (instance == null) {
            instance = new ProdutoManager();
        }
        return instance;
    }

    public static List<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public void adicionarProduto(Produto produto) {
        listaProdutos.add(produto);
    }

    public static Produto getProdutoByCodigo(String codigoProduto) {
        for (Produto produto : listaProdutos) {
            if (produto.getCodigoProduto().equals(codigoProduto)) {
                return produto;
            }
        }
        return null;
    }

    public static boolean excluirProdutoByCodigo(String codigoProduto) {
        for (Produto produto : listaProdutos) {
            if (produto.getCodigoProduto().equals(codigoProduto)) {
                listaProdutos.remove(produto);
                return true;
            }
        }
        return false;
    }
}
