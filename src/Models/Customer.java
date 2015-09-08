package Models;

/**
 * Created by Remco on 8-9-2015.
 */
public class Customer {

    private int customerCode;
    private String fName, sName;

    public Customer(int customerCode, String fName, String sName) {
        this.customerCode = customerCode;
        this.fName = fName;
        this.sName = sName;
    }

    public int getCustomerCode() {

        return customerCode;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }
}
