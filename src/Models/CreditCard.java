package Models;

/**
 * Created by luppi on 9-9-2015.
 */
public class CreditCard extends Payment {

    public CreditCard(int id) {
        super(id);
        amount = requestAmount();
    }
}
