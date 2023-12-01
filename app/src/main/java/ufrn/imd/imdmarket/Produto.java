package ufrn.imd.imdmarket;

import androidx.annotation.NonNull;

public class Produto {
    private String codigoProduto;
    private String nomeProduto;
    private String descricaoProduto;
    private int estoque;

    public Produto(String codigo, String nome, String descricao, int estoque) {
        this.codigoProduto = codigo;
        this.nomeProduto = nome;
        this.descricaoProduto = descricao;
        this.estoque = estoque;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigo_produto) {
        this.codigoProduto = codigo_produto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nome_produto) {
        this.nomeProduto = nome_produto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricao_produto) {
        this.descricaoProduto = descricao_produto;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    @NonNull
    @Override
    public String toString() {
        return nomeProduto;
    }
}
