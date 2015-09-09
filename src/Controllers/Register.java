package Controllers;

import Enums.Brand;
import Enums.ProductType;
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
    private ArrayList<Discount> discounts;
    private Sale sale;

    public Register() {
        inventory = new Inventory();
        discounts = new ArrayList<Discount>();

        addDummyData();
        newSale();
    }

    private void newSale(){
        sale = new Sale(discounts);
        checkForProducts();
    }

    private void checkForProducts(){
        Scanner scanner = new Scanner(System.in);
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
        sale.finish();
    }

    private void addDummyData(){
        HashMap map = new HashMap();
        map.put("Merk", Brand.Heineken);
        map.put("Type", ProductType.Beer);
        ProductSpec spec = new ProductSpec(map);
        Product beer = new Product(10, "Bier", spec, 123);
        inventory.addProduct(beer);

        discounts.add(new QuantityDiscount(beer, 2));
    }

    public static void main(String[] args){
        new Register();
    }
}
