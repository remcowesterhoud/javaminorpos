package Controllers;

import Models.Customer;
import Models.Payment;
import Models.Product;
import Models.Receipt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Remco on 8-9-2015.
 */
public class Sale {

    private HashMap<Product, Integer> order;
    private ArrayList<Discount> discounts;
    private static final double CUSTOMER_DISCOUNT = 5;
    private Customer customer;
    private Map<Payment,Integer> payments;

    public Sale(ArrayList<Discount> discounts, Customer customer) {
        order = new HashMap<Product, Integer>();
        payments = new HashMap<Payment, Integer>();
        this.discounts = discounts;
        this.customer = customer;
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
        double totalPrice = totalPrice();
        Receipt receipt = new Receipt(order, totalPrice, calculateProductDiscount(), calculateCustomerDiscount(totalPrice));
        receipt.generateReceipt();
    }

    private double calculateProductDiscount(){
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

    private double calculateCustomerDiscount(double totalPrice){
        if (customer != null) {
            return totalPrice * (CUSTOMER_DISCOUNT / 100);
        }
        else{
            return 0;
        }
    }
}
