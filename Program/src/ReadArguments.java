import Data.Currency;

import java.io.IOException;
import java.util.Map;

public class ReadArguments {
    private String[] arguments;

    public ReadArguments (String[] arguments) {
        this.arguments = arguments;
    }

    public boolean readArguments() throws IOException {
        if (arguments.length == 3 || arguments.length == 5) {
            String filename = arguments[0];
            ReadFile readFile = new ReadFile();
            if (!readFile.readFile(filename)) {
                System.out.println("Wystąpił błąd podczas wczytywania pliku");
                return false;
            }
            Map<String, Currency> currencyMap = readFile.getcurrencyMap();
            BestWayToConvert bestWayToConvert = new BestWayToConvert(currencyMap);
            double money=0;
            try{
                money=Double.parseDouble(arguments[2]);
                if(money<0){
                    System.out.println("Kwota nie może być ujemna.");
                    return false;
                }
            }catch (NumberFormatException e) {
                System.out.println("Argument odpowiadający za wartość którą trzeba przekonwertować nie jest liczbą.");
                System.out.println("Kwota muszi być po argumencie decydującym o trybie dziłania programu.");
                System.out.println("Przykład: Test.txt bw 1000 USD EUR");
                return false;
            }
            if (arguments[1].equals("bw")) {
                if(arguments.length != 5){
                    System.out.println("Została podana nie poprawna ilość argumentów dla trybu wyszukiwania najkorzystniejszej ścieżki wymiany waluty.");
                    System.out.println("Przykładowe wywołanie dla danego trybu: Test.txt bw 1000 EUR USD");
                    return false;
                }
                Currency fromConvert=null;
                Currency toConvert=null;
                if(currencyMap.containsKey(arguments[3])){
                    fromConvert=currencyMap.get(arguments[3]);
                }else {
                    System.out.println("Zosatała podana waluta wejściowa której nie ma w pliku wyjściowym. Nazwa podanej waluty:"+" " + arguments[3]);
                    return false;
                }
                if(currencyMap.containsKey(arguments[4])){
                    toConvert=currencyMap.get(arguments[4]);
                }else {
                    System.out.println("Zosatała podana waluta wyjściowa której nie ma w pliku wyjściowym. Nazwa podanej waluty:"+ " "+ arguments[4]);
                    return false;
                }
                if(!bestWayToConvert.findBestWayToConvert(fromConvert,toConvert,money)){
                    System.out.println("Nie istnieje ścieżki wymian dla waluty wejściowej:"+fromConvert.getAbbreviation()+" i waluty wyjściowej:" + " "+ toConvert.getAbbreviation());
                }
            }
            else if(arguments[1].equals("ar")) {
                Arbitration arbitration = new Arbitration(currencyMap);
                if(!arbitration.findArbitrage(money)){
                    System.out.println("Nie udało się znaleźć arbitraż");
                }
            }else if( !arguments[1].equals("ar") || !arguments.equals("bw")){
                System.out.println("Argument decydujący o trybie działania programu jest nie poprawny");
                System.out.println("Możliwe tryby działania progragu:");
                System.out.println("1. Wyszukiwanie najkożystniejszej ścieżki wymiany waluty. Argument który trzeba podać: bw");
                System.out.println("1. Wyszukiwanie dowolnego arbitrażu. Argument który trzeba podać: ar");
                System.out.println("Przykładowe wywołanie dla danego trybu: Test.txt bw 1000 EUR USD");
                return false;
            }
        } else {
            System.out.println("Podana nie poprawna ilość argumentów. Proszę o sprawdzenie argumentów.");
            System.out.println("Aby program działał w trybie wyszukiwania najkożystniejszej ścieżki wymiany waluty trzeba podać następujące argumenty:");
            System.out.println("1.Nazwa pliku. Przykładowo: nazwa_pliku.txt");
            System.out.println("2. Argument decydujący o trybie dziłania programu. W tym przypadku: bw ");
            System.out.println("3. Kwotę którą trzeba przekonwertować. Przykładowo: 1000");
            System.out.println("4. Nazwa waluty wejściowa. Przykładowo: PNL");
            System.out.println("5. Nazwa waluty wyjściowej. Przykłądowo: EUR");
            System.out.println(" ");
            System.out.println("Aby program działał w trybie wyszukiwania dowolnego arbitrażu trzeba podać następujące argumenty:");
            System.out.println("1.Nazwa pliku. Przykładowo: nazwa_pliku.txt");
            System.out.println("Argument decydujący o trybie dziłania programu. W tym przypadku: ar ");
            System.out.println("3. Kwotę którą trzeba przekonwertować. Przykładowo: 1000");
            return false;
        }
        return true;
    }
}
