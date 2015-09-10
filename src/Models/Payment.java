package Models;

import java.util.Scanner;

/**
 * Created by @author Matthijs van der Meijden on 9-9-2015.
 *
 */
public abstract class Payment {

    private int id;
    protected double amount;
    public Scanner scanner;

    public Payment(int id){
        this.id = id;
        this.amount = 0;
        scanner = new Scanner(System.in);
    }

    public abstract double handlePayment();

    //getters and setters

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
