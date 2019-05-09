

import Data.Currency;

import java.io.IOException;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
           ReadArguments readArguments = new ReadArguments(args);
        try {
            readArguments.readArguments();
        } catch (IOException e) {
            System.out.println("Nie znaleziono podanego pliku");
            System.exit(0);
        }
    }
}
