package Models;

/**
 * Created by luppi on 9-9-2015.
 */
public class Cheque extends Payment {

    public Cheque(int id){
        super(id);
    }

    public double handlePayment() {
        System.out.println("How much would you like to pay?");
        amount = scanner.nextDouble();
        return amount;
    }
}
