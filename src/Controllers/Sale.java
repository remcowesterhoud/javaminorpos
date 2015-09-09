package Controllers;

import Models.Product;
import Models.Receipt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Remco on 8-9-2015.
 */
public class Sale {

    private HashMap<Product, Integer> order;
    private ArrayList<Discount> discounts;

    public Sale(ArrayList<Discount> discounts) {
        order = new HashMap<Product, Integer>();
        this.discounts = discounts;
    }

    public void addProduct(Product product){
        if (!order.containsKey(product)){
            order.put(product, 1);
        }
        else{
            order.put(product, order.get(product) + 1);
        }
    }

    public void deleteProduct(Product product){
        if (order.get(product) > 1){
            order.put(product, order.get(product) - 1);
        }
        else{
            order.remove(product);
        }
    }

    private double totalPrice(){
        double total = 0;
        for (Map.Entry<Product, Integer> entry : order.entrySet()){
            Product product = entry.getKey();
            int quantity = entry.getValue();
            total += product.getPrice() * quantity;
        }
        return total;
    }

    public void finish(){
        Receipt receipt = new Receipt(order, totalPrice(), calculateDiscount());
        receipt.generateReceipt();
    }

    private double calculateDiscount(){
        double totalDiscount = 0;

        for (Map.Entry<Product, Integer> entry : order.entrySet()){
            for (Discount discount : discounts){
                if (entry.getKey() == discount.getProduct()){
                    totalDiscount += discount.getDiscount(entry.getValue());
                }
                else{
                    continue;
                }
            }
        }

        return totalDiscount;
    }
}
