package Controllers;


import Enums.PaymentType;
import Enums.ProductType;
import Models.*;

import java.lang.reflect.Array;
import java.util.*;


/**
 * Created by Remco on 8-9-2015.
 */
public class Sale {

    private HashMap<Product, Integer> order;
    private ArrayList<Discount> discounts;
    private Map<Payment, Integer> payments;
    private Customer customer;

    private static final double CUSTOMER_DISCOUNT = 5;

    public Sale(ArrayList<Discount> discounts, Customer customer) {
        order = new HashMap<Product, Integer>();
        payments = new HashMap<Payment, Integer>();
        this.discounts = discounts;
        this.customer = customer;
    }

    public void addProduct(Product product) {
        if (!order.containsKey(product)) {
            order.put(product, 1);
        } else {
            order.put(product, order.get(product) + 1);
        }
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
        double change = pay(total - productDiscount - customerDiscount);
        Receipt receipt = new Receipt(order, total, productDiscount, customerDiscount, change);
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

    public void addPayment(Payment payment) {
        payments.put(payment, payment.getId());
    }

    public double pay(double total) {
        int amountPayments = 1;

        while (total > 0) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("You still need to pay: $" + total);


            System.out.println("WHow would you like to pay?? Options are:");
            PaymentType[] types = PaymentType.values();
            int i = 0;
            for (PaymentType type : types){
                i++;
                System.out.println(i + ". for " + type);
            }

            PaymentType type = types[scanner.nextInt() - 1];

            Payment payment;
            switch (type) {
                case Cash:
                    payment = new Cash(amountPayments);
                    total -= payment.handlePayment();
                    break;
                case Bankcard:
                    payment = new Bankcard(amountPayments);
                    total -= payment.handlePayment();
                    break;
                case Cheque:
                    double chequeAmount = maxChequeValue(scanner);
                    payment = new Cheque(amountPayments);
                    double amountPayed = payment.handlePayment();
                    if (amountPayed > chequeAmount){
                        System.out.println("You do not have enough products of this type.");
                        continue;
                    }
                    else {
                        total -= amountPayed;
                        break;
                    }
                case Creditcard:
                    payment = new CreditCard(amountPayments);
                    total -= payment.handlePayment();
                    break;
                case Ewallet:
                    payment = new Ewallet(amountPayments);
                    total -= payment.handlePayment();
                    break;
                default:
                    continue;
            }
            amountPayments++;
        }
        return Math.abs(total);
    }

    private double maxChequeValue(Scanner scanner){
        double max = 0;

        System.out.println("What type of cheque do you have? Options are:");
        ProductType[] types = ProductType.values();
        int i = 0;
        for (ProductType type : types){
            i++;
            System.out.println(i + ". for " + type);
        }

        ProductType type = types[scanner.nextInt() - 1];
        for (Map.Entry<Product, Integer> entry : order.entrySet()){
            if (entry.getKey().getSpec().getSpec("Type") == type){
                max += entry.getKey().getPrice() * entry.getValue();
            }
        }
        return max;
    }
}
