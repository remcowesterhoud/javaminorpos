package Models;

/**
 * Created by luppi on 9-9-2015.
 */
public class Cheque extends Payment {

    public Cheque(int id){
        super(id);
    }

    @Override
    public double handlePayment() {
        return 0;
    }


}
