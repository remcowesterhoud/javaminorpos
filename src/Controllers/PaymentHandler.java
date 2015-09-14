package Controllers;

import Utility.PaymentType;
import Models.Payment;
import Models.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Remco on 10-9-2015.
 */
public class PaymentHandler {

    private double change;
    private ArrayList<Payment> payments;
    private Scanner scanner;
    private DecimalFormat df;

    public PaymentHandler() {
        payments = new ArrayList<>();
        scanner = new Scanner(System.in);
        df = new DecimalFormat("#0.00");
    }

    public boolean completeTransaction(double total, HashMap<Product, Integer> order){
        boolean completed = false;

        while (total > 0){
            System.out.println("You still need to pay $" + df.format(total));
            PaymentType type = getPaymentMetod();
            total -= initiatePayment(type, order);
        }

        if (total == 0){
            completed = true;
        }
        else if(total < 0){
            change = Math.abs(total);
            completed = true;
        }
        return completed;
    }

    public PaymentType getPaymentMetod(){
        System.out.println("How would you like to pay?? Options are:");
        PaymentType[] types = PaymentType.values();
        int i = 0;
        for (PaymentType type : types){
            i++;
            System.out.println(i + ". for " + type);
        }

        return types[scanner.nextInt() - 1];
    }

    private double initiatePayment(PaymentType type, HashMap<Product, Integer> order){
        double amountPayed = 0;
        Payment payment;

        switch (type){
            case Cash:
                payment = new Cash(payments.size() + 1);
                amountPayed = payment.getAmountPayed();
                payments.add(payment);
                break;
            case Bankcard:
                payment = new Bankcard(payments.size() + 1);
                amountPayed = payment.getAmountPayed();
                payments.add(payment);
                break;
            case Cheque:
                payment = new Cheque(payments.size() + 1, order);
                amountPayed = payment.getAmountPayed();
                payments.add(payment);
                break;
            case Creditcard:
                payment = new CreditCard(payments.size() + 1);
                amountPayed = payment.getAmountPayed();
                payments.add(payment);
                break;
            case Ewallet:
                payment = new Ewallet(payments.size());
                amountPayed = payment.getAmountPayed();
                payments.add(payment);
                break;
        }
        return amountPayed;
    }

    public double getChange(){
        return change;
    }
}
