package ufrn.imd.imdmarket;

import java.util.ArrayList;
import java.util.List;

public class ProductManager {

    private static ProductManager instance;
    private static List<Product> productList;

    private ProductManager() {
        productList = new ArrayList<>();
    }

    public static synchronized ProductManager getInstance() {
        if (instance == null) {
            instance = new ProductManager();
        }
        return instance;
    }

    public static List<Product> getProductList() {
        return productList;
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public static Product getProductByCode(String productCode) {
        for (Product product : productList) {
            if (product.getProductCode().equals(productCode)) {
                return product;
            }
        }
        return null;
    }

    public static boolean removeProductByCode(String productCode) {
        for (Product product : productList) {
            if (product.getProductCode().equals(productCode)) {
                productList.remove(product);
                return true;
            }
        }
        return false;
    }
}
