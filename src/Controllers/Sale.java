package Controllers;

import Models.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Remco on 8-9-2015.
 */
public class Sale {

    private List<Product> order;
    private ArrayList<Discount> discounts;

    public Sale(ArrayList<Discount> discounts) {
        order = new ArrayList<Product>();
        this.discounts = discounts;
    }

    public void addProduct(Product product){
        order.add(product);
    }

    public void deleteProduct(Product product){
        order.remove(product);
    }

    private double totalPrice(){
        double total = 0;
        for (Product product : order){
            total += product.getPrice();
        }
        return total;
    }

    public void finish(){
        System.out.println(calculateDiscount());
    }

    //TODO Shit method
    private double calculateDiscount(){
        double totalDiscount = 0;
        for (Product product : order){
            for (Discount discount : discounts){
                if (discount.getProduct().getProductCode() == product.getProductCode()){
                    if(discount instanceof PercentageDiscount){
                        double d = product.getPrice() * (((PercentageDiscount) discount).getPercentage() / 100);
                        totalDiscount += d;
                    }
                    else if (discount instanceof QuantityDiscount){
                        //TODO How do I do this?
                    }
                }
                else{
                    continue;
                }
            }
        }
        return totalDiscount;
    }
}
