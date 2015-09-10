package Models;

/**
 * Created by luppi on 9-9-2015.
 */
public class Bankcard extends Payment {

    public Bankcard(int id) {
        super(id);
        amount = requestAmount();
    }
}
