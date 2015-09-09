package Controllers;

import Models.Product;

/**
 * Created by Remco on 8-9-2015.
 */
public class QuantityDiscount extends Discount{

    private int quantity;

    public QuantityDiscount(Product product, int quantity) {
        super(product);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getDiscount(int quantityPurchased) {
        int amount = quantityPurchased / (quantity + 1);
        return amount * product.getPrice();
    }
}
