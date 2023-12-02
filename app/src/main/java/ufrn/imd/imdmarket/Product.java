package ufrn.imd.imdmarket;

import androidx.annotation.NonNull;

public class Product {
    private String productCode, productName, productDescription;
    private int productStock;

    public Product(String code, String name, String description, int stock) {
        this.productCode = code;
        this.productName = name;
        this.productDescription = description;
        this.productStock = stock;
    }

    public String getProductCode() {
        return productCode;
    }
    public String getProductName() {
        return productName;
    }
    public String getProductDescription() {
        return productDescription;
    }
    public int getProductStock() {
        return productStock;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }

    @NonNull
    @Override
    public String toString() {
        return productName;
    }
}
