package Models;

/**
 * Created by luppi on 9-9-2015.
 */
public class Cash extends Payment {


    public Cash(int id) {
        super(id);

    }

    @Override
    public double handlePayment() {
        return 0;
    }
}
