package Controllers;

import Utility.HttpRequest;
import Models.FidelityCard;
import Models.Product;
import Utility.SingleLog;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Remco on 8-9-2015.
 */
public class Register {

    private SingleLog log;
    private ArrayList<Discount> discounts;
    private Sale sale;
    private ArrayList<Sale> saleList;
    private Scanner scanner;
    private HttpRequest httpRequest;
    private Gson gson;

    public Register() {
        log = SingleLog.getLog();
        discounts = new ArrayList<Discount>();
        saleList = new ArrayList<Sale>();
        scanner = new Scanner(System.in);
        httpRequest = new HttpRequest();
        gson = new Gson();

        newSale();
    }

    private void newSale(){
        FidelityCard fidelityCard = checkCustomer();
        sale = new Sale(discounts, fidelityCard);
        checkForProducts();
    }

    private void checkForProducts(){
        System.out.println("Enter productcodes. Type checkout when done.");
        while (scanner.hasNextInt()){
            int input = scanner.nextInt();
            Product product = gson.fromJson(httpRequest.makeGetReqeust("http://localhost:8080/products/" + input), Product.class);
            if (product == null){
                System.out.println("Product could not be found");
            }
            else {
                sale.addProduct(product);
                String productinfo = product.getSpec().getSpec("Merk") + " " + product.getSpec().getSpec("Type") + " was added too the shopping list.";
                log.addInfo(productinfo);
            }
        }
        switch (scanner.next()){
            case "checkout" :
                sale.finish();
                saleList.add(sale);
                newSale();
                break;
            case "close" :
                System.out.println("Overview of Sales made today:");
                for(Sale sale : saleList){
                    printSaleList(sale);
                }
                break;

            case "end" :
                System.exit(0);
        }
    }

    private FidelityCard checkCustomer(){
        System.out.println("Please enter your customer code. Leave empty if not a customer");
        String input;
        if (!(input = scanner.nextLine()).isEmpty()){
            try {
                FidelityCard fidelityCard = gson.fromJson(httpRequest.makeGetReqeust("http://localhost:8080/fidelitycards/" + input), FidelityCard.class);
                if (fidelityCard != null) {
                    log.addInfo("Sale started with customer: " + fidelityCard.getCustomerName());
                    return fidelityCard;
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
            log.addInfo("Sale started with unknown customer");
            return null;
        }
    }

    private void addDummyData(){
//        HashMap map = new HashMap();
//        map.put("Merk", Brand.Heineken);
//        map.put("Type", ProductType.Beer);
//        ProductSpec spec = new ProductSpec(map);
//        Product beer = new Product(10, "Heineken Bier", spec, 123);
//        inventory.addProduct(beer);
//
//        discounts.add(new QuantityDiscount(beer, 2));

//        Customer customer = new Customer(123, "Laurens", "Oomen");
//        customers.addProduct(customer);
    }

    private void printSaleList(Sale sale){
        for(Map.Entry<Product, Integer> entry : sale.getOrder().entrySet()){

        }
        System.out.println();
    }
    public static void main(String[] args){
        new Register();
    }
}
