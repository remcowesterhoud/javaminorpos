package Models;

import Interfaces.Payment;

/**
 * Created by luppi on 9-9-2015.
 */
public class CreditCard implements Payment {

    private int id;
    private double amount;

    public CreditCard(int id) {
        this.id = id;
        amount = requestAmount();
    }

    @Override
    public double requestAmount() {
        System.out.println("Enter the amount you'd like too pay.");
        if (scanner.hasNextInt()){
            return scanner.nextInt();
        }
        else{
            System.out.println("Please enter a valid amount.");
            return requestAmount();
        }
    }

    @Override
    public double getAmountPayed() {
        return amount;
    }

    public int getId() {
        return id;
    }
}
