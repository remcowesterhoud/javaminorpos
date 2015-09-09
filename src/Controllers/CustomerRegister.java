package Controllers;


import Models.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Laurens on 09/09/15.
 */
public class CustomerRegister {

    private List<Customer> customerList;

    public CustomerRegister() {
        customerList = new ArrayList<Customer>();
    }

    public void addProduct(Customer newCustomer){
        customerList.add(newCustomer);
    }

    public void deleteProduct(Customer customer){
        customerList.remove(customer);
    }

    public Customer searchCustomer(int customerCode){
        for (Customer customer : customerList){
            if (customer.getCustomerCode() == customerCode){
                return customer;
            }
        }
        return null;
    }
}
