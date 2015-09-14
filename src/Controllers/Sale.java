package Controllers;


import Models.*;

import java.util.*;


/**
 * Created by Remco on 8-9-2015.
 */
public class Sale {

    private HashMap<Product, Integer> order;
    private ArrayList<Discount> discounts;
    private Customer customer;
    private PaymentHandler paymentHandler;

    private static final double CUSTOMER_DISCOUNT = 5;

    public Sale(ArrayList<Discount> discounts, Customer customer) {
        order = new HashMap<Product, Integer>();
        this.discounts = discounts;
        this.customer = customer;
        paymentHandler = new PaymentHandler();
    }

    public void addProduct(Product product) {
        if (!order.containsKey(product)) {
            order.put(product, 1);
        } else {
            order.put(product, order.get(product) + 1);
        }
    }
    public HashMap<Product, Integer> getOrder(){
        return order;
    }
    public void deleteProduct(Product product) {
        if (order.get(product) > 1) {
            order.put(product, order.get(product) - 1);
        } else {
            order.remove(product);
        }
    }

    private double totalPrice() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : order.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            total += product.getPrice() * quantity;
        }
        return total;
    }

    public void finish() {
        double total = totalPrice();
        double productDiscount = calculateProductDiscount();
        double customerDiscount = calculateCustomerDiscount(total);
        paymentHandler.completeTransaction(total - productDiscount - customerDiscount, order);
        Receipt receipt = new Receipt(order, total, productDiscount, customerDiscount, paymentHandler.getChange());
        receipt.generateReceipt();
    }

    private double calculateProductDiscount() {
        double totalDiscount = 0;

        for (Map.Entry<Product, Integer> entry : order.entrySet()) {
            for (Discount discount : discounts) {
                if (entry.getKey() == discount.getProduct()) {
                    totalDiscount += discount.getDiscount(entry.getValue());
                } else {
                    continue;
                }
            }
        }

        return totalDiscount;
    }

    private double calculateCustomerDiscount(double totalPrice) {
        if (customer != null) {
            return totalPrice * (CUSTOMER_DISCOUNT / 100);
        } else {
            return 0;
        }
    }
}
