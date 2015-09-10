package Models;

/**
 * Created by luppi on 9-9-2015.
 */
public class Ewallet extends Payment {

    public Ewallet(int id) {
        super(id);
    }

    @Override
    public double handlePayment(){
        System.out.println("How much would you like to pay?");
        amount = scanner.nextDouble();
        return amount;
    }
}
