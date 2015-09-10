package Interfaces;

import java.util.Scanner;

/**
 * Created by Remco on 10-9-2015.
 */
public interface Payment {
    Scanner scanner = new Scanner(System.in);

    public double getAmountPayed();
    public double requestAmount();
}
