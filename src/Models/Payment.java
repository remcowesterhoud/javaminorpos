package Models;

import java.util.Scanner;

/**
 * Created by Remco on 10-9-2015.
 */
public abstract class Payment {
    protected Scanner scanner = new Scanner(System.in);
    protected double amount;
    protected int id;

    public Payment(int id){
        this.id = id;
    }

    public double getAmountPayed() {
        return amount;
    }

    public double requestAmount() {
        System.out.println("Enter the amount you'd like too pay.");
        if (scanner.hasNextDouble()){
            return scanner.nextDouble();
        }
        else{
            System.out.println("Please enter a valid amount.");
            return requestAmount();
        }
    }

    public int getId(){
        return id;
    }
}
