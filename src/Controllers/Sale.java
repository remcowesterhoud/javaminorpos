package Controllers;

import Models.Bankcard;
import Models.Payment;
import Models.Product;
import Models.Receipt;

import java.util.*;


/**
 * Created by Remco on 8-9-2015.
 */
public class Sale {

    private HashMap<Product, Integer> order;
    private ArrayList<Discount> discounts;
    private Map<Payment,Integer> payments;

    public Sale(ArrayList<Discount> discounts) {
        order = new HashMap<Product, Integer>();
        payments = new HashMap<Payment, Integer>();
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
        double total = totalPrice();
        double change = pay(total);
        Receipt receipt = new Receipt(order, total, calculateDiscount(), change);
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

    public void addPayment(Payment payment){
        payments.put(payment, payment.getId());
    }
    public double pay(double total){
        int amountPayments = 0;

        while(total> 0){
            Scanner scanner = new Scanner(System.in);
            System.out.println("You still need to pay: $" + total);
            System.out.println("How would you like to pay? Choose 1. bankcard, 2. cheque, 3.creditcard or 4.ewallet");
            int paymentType = scanner.nextInt();

            Payment payment;
            switch (paymentType){
                case 1 :
                    payment = new Bankcard(amountPayments);
                    total -= payment.handlePayment();
            }
            amountPayments++;
        }
        return Math.abs(total);
    }
}
