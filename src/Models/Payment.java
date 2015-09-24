package Models;

import java.util.Scanner;

/**
 * Created by Remco on 10-9-2015.
 */
public abstract class Payment {
    protected Scanner scanner = new Scanner(System.in);
    protected double amount;
    protected int id;

    public Payment(int id, double amount){
        this.id = id;
        this.amount = amount;
    }

    public double getAmountPayed() {
        return amount;
    }

    public int getId(){
        return id;
    }
}
