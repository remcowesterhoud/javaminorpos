package Models;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Remco on 8-9-2015.
 */
public class Receipt {

    private HashMap<Product, Integer> products;
    private double totalPrice, productDiscount, customerDiscount;

    public Receipt(HashMap<Product, Integer> products, double totalPrice, double productDiscount, double customerDiscount) {
        this.products = products;
        this.totalPrice = totalPrice;
        this.productDiscount = productDiscount;
        this.customerDiscount = customerDiscount;
    }

    public void generateReceipt(){
        DecimalFormat df = new DecimalFormat("#0.00");

        System.out.println("Thank you for shopping with us!");
        System.out.println("------------------------------");
        for (Map.Entry<Product, Integer> entry : products.entrySet()){
            System.out.println(entry.getKey().getName() + " x" + entry.getValue() + "\t$" + df.format(entry.getKey().getPrice() * entry.getValue()));
        }
        System.out.println("Total:" + "\t\t\t\t$" + df.format(totalPrice));
        System.out.println("Discount:" + "\t\t\t$" + df.format(productDiscount));
        System.out.println("Customer discount" + "\t$" + df.format(customerDiscount));
        System.out.println("New total:" + "\t\t\t$" + df.format(totalPrice - productDiscount - customerDiscount));
        System.out.println("------------------------------");
    }
}
