package Controllers;

import Models.Product;

/**
 * Created by Remco on 8-9-2015.
 */
public abstract class Discount {

    private Product product;

    public Discount(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}
