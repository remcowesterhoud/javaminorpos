package Controllers;

import Enums.Brand;
import Enums.ProductType;
import Models.Customer;
import Models.Product;
import Models.ProductSpec;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Remco on 8-9-2015.
 */
public class Register {

    private Inventory inventory;
    private CustomerRegister customers;
    private ArrayList<Discount> discounts;
    private Sale sale;
    private Scanner scanner = new Scanner(System.in);

    public Register() {
        inventory = new Inventory();
        customers = new CustomerRegister();
        discounts = new ArrayList<Discount>();

        addDummyData();
        newSale();
    }

    private void newSale(){
        Customer customer = checkCustomer();
        sale = new Sale(discounts, customer);
        checkForProducts();
    }

    private void checkForProducts(){
        System.out.println("Enter productcodes. Type checkout when done.");
        while (scanner.hasNextInt()){
            int input = scanner.nextInt();
            Product product = inventory.searchProduct(input);
            if (product == null){
                System.out.println("Product could not be found");
            }
            else {
                sale.addProduct(product);
                System.out.println(product.getSpec().getSpec("Merk") + " " + product.getSpec().getSpec("Type") + " was added too the shopping list.");
            }
        }
        switch (scanner.next()){
            case "checkout" :
                sale.finish();
                newSale();
            case "end" :
                System.exit(0);
        }
    }

    private Customer checkCustomer(){
        System.out.println("Please enter your customer code. Leave empty if not a customer");
        String input;
        if (!(input = scanner.nextLine()).isEmpty()){
            try {
                Customer customer = customers.searchCustomer(Integer.parseInt(input));
                if (customer != null) {
                    System.out.println("Welcome " + customer.getfName() + " " + customer.getsName() + ". Thank you for shopping with us again.");
                    return customer;
                } else {
                    System.out.println("Customer could not be found.");
                    return checkCustomer();
                }
            }
            catch (NumberFormatException ex){
                System.out.println("Please enter a valid customer code.");
                return checkCustomer();
            }
        }
        else{
            return null;
        }
    }

    private void addDummyData(){
        HashMap map = new HashMap();
        map.put("Merk", Brand.Heineken);
        map.put("Type", ProductType.Beer);
        ProductSpec spec = new ProductSpec(map);
        Product beer = new Product(10, "Heineken Bier", spec, 123);
        inventory.addProduct(beer);

        discounts.add(new QuantityDiscount(beer, 2));

        Customer customer = new Customer(123, "Laurens", "Oomen");
        customers.addProduct(customer);
    }

    public static void main(String[] args){
        new Register();
    }
}
