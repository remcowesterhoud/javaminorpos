package Controllers;

import Models.Product;

/**
 * Created by Remco on 8-9-2015.
 */
public class PercentageDiscount extends Discount{

    private double percentage;

    public PercentageDiscount(Product product, double percentage) {
        super(product);
        this.percentage = percentage;
    }

    public double getPercentage() {
        return percentage;
    }
}
