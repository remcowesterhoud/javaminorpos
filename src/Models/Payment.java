package Models;

/**
 * Created by @author Matthijs van der Meijden on 9-9-2015.
 *
 */
public abstract class Payment {

    private int id;
    private int value;

    public Payment(int id, int value){
        this.id = id;
        this.value = value;
    }



    //getters and setters

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
