package Models;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Remco on 8-9-2015.
 */
public class Receipt {

    private HashMap<Product, Integer> products;
    private double totalPrice, discount;

    public Receipt(HashMap<Product, Integer> products, double totalPrice, double discount) {
        this.products = products;
        this.totalPrice = totalPrice;
        this.discount = discount;
    }

    public void generateReceipt(){
        DecimalFormat df = new DecimalFormat("#0.00");

        System.out.println("Thank you for shopping with us!");
        System.out.println("------------------------------");
        for (Map.Entry<Product, Integer> entry : products.entrySet()){
            System.out.println(entry.getKey().getName() + " x" + entry.getValue() + "\t$" + df.format(entry.getKey().getPrice() * entry.getValue()));
        }
        System.out.println("Total:" + "\t\t\t\t$" + df.format(totalPrice));
        System.out.println("Discount:" + "\t\t\t$" + df.format(discount));
        System.out.println("New total:" + "\t\t\t$" + df.format(totalPrice - discount));
        System.out.println("------------------------------");
    }
}
