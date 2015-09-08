package Controllers;

import Models.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Remco on 8-9-2015.
 */
public class Inventory {

    private List<Product> productList;

    public Inventory() {
        productList = new ArrayList<Product>();
    }

    public void addProduct(Product newProduct){
        productList.add(newProduct);
    }

    public void deleteProduct(Product product){
        productList.remove(product);
    }

    public Product searchProduct(int productCode){
        for (Product product : productList){
            if (product.getProductCode() == productCode){
                return product;
            }
        }
        return null;
    }
}
