import Data.Currency;
import Data.Exchange;

import java.io.*;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class ReadFile {
    private Map<String, Currency> currencyMap;
    private int currentLineNumber = 0;
    private int partsOfFile = 0;
    private int counterCurrents = 0;

    public ReadFile() {
        this.currencyMap = new TreeMap<>();
    }

    public boolean readFile(String filename) throws IOException {
        File file = new File(filename);
        String line;
        boolean result;
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            result = true;
        } catch (FileNotFoundException e) {
            System.out.println("Nie udało się otworzyć podany plik. Nazwa pliku" + " " + filename);
            return false;
        }


        while ((line = bufferedReader.readLine()) != null && result) {
            currentLineNumber++;
            if (line.isEmpty()) {
                System.out.println("Puste linije są nie dopuszczalne. Błąd formotowania pliku w linii:" + " " + currentLineNumber);
                return false;
            } else {
                result = analizLine(line, currentLineNumber);
            }
        }
        return result;
    }


    private boolean analizLine(String line, int numberofLine) {
        boolean result = true;
        if (numberofLine == 1) {
            if (line.charAt(1) == '#' || line.charAt(0) == '#') {
                result = true;
            } else {
                System.out.println("Został podany plik z błędnym formatowaniem. Aby rozpocząć definiowanie walut muszi być podana następująca linijka: # Waluty (id | symbol | pełna nazwa)");
                return false;
            }
        } else if (line.startsWith("#")) {
            partsOfFile++;
            result = true;

        } else if (numberofLine > 0 && partsOfFile == 0) {
            if (checkFormatCurrency(line)) {
                result = addCurency(line);;
            }else {
                return false;
            }
        } else if (numberofLine > 0 && partsOfFile == 1) {
            if (checkFormatExchenge(line)) {
                result = addExchenge(line);;
            }else {
                return false;
            }

        } else {
            return false;
        }
        return result;
    }

    private boolean checkFormatCurrency(String line) {
        String[] words = line.split("\\s");
        if (words.length < 3) {
            System.out.println("Błędne formatowanie pliku: " + currentLineNumber);
            System.out.println("Poprawnego formatowania pliku: id | symbol | pełna nazwa");
            System.out.println("Symbol | oznacza spację.");
            return false;
        }
        try {
            Integer.parseInt(words[0]);
        } catch (NumberFormatException e) {
            System.out.println("Podany id waluty jest nie poprawny w linii " + currentLineNumber);
            return false;
        }
        return true;
    }

    private boolean checkFormatExchenge(String line) {
        line = line.replace(",", ".");
        String[] words = line.split("\\s");

        int i = words.length;
        if (i != 6) {
            System.out.println("Błędne formatowanie pliku w linijce :" + " " + currentLineNumber);
            System.out.println("Poprawnego formatowania pliku: id | symbol (waluta wejściowa) | symbol (waluta wyjściowa) | kurs | typ opłaty | opłata");
            System.out.println("Symbol | oznacza spację.");
            return false;
        }
        try {
            Integer.parseInt(words[0]);
        } catch (NumberFormatException e) {

            System.out.println("Podany id wymiany jest nie poprawny w linii:" + currentLineNumber);
            return false;
        }
        try {
            Double.parseDouble(words[3]);
        } catch (NumberFormatException e) {
            System.out.println("Podany kurs wymiany jest nie poprawny w linii: " + currentLineNumber + " Proszę podać poprawną liczbę.");
            return false;
        }
        try {
            Double.parseDouble(words[5]);
        } catch (NumberFormatException e) {
            System.out.println("Podana wartość w linii " + currentLineNumber + " pliku jest nie poprawna. Proszę zmienić wartość " + words[5]+ " na liczbę.");
            return false;
        }
        return true;
    }

    private boolean addCurency(String line) {
        String abbreviation;
        String fullNameCurrency;
        Scanner scanner;
        Currency currency;
        scanner = new Scanner(line);
        scanner.next();
        abbreviation = scanner.next();
        scanner.useDelimiter("");
        scanner.next(" ");
        scanner.useDelimiter("$");
        fullNameCurrency = scanner.next();
        currency = new Currency(abbreviation, fullNameCurrency, counterCurrents);
        if (currencyMap.containsKey(abbreviation)) {
            System.out.println("Waluta już była" + " " + abbreviation + " nie jest możliwe dwa razy podawać tej samej ");
            return false;
        } else {
            currencyMap.put(abbreviation, currency);
            counterCurrents++;
        }
        return true;
    }

    private boolean addExchenge(String line) {
        line = line.replace(",", ".");
        Currency from;
        Currency to;
        double course = 0;
        double procent = 0;
        double constant = 0;
        Exchange exchange;
        String[] words = line.split("\\s");
        from = currencyMap.get(words[1]);
        to = currencyMap.get(words[2]);
        if (from == null) {
            System.out.println("Waluta o nazwie:" + " " + words[1] + " " + "nie była zdefiniowana wczęśniej błąd wystąpił w linijce:" + " " + currentLineNumber);
            return false;
        }
        if (to == null) {
            System.out.println("Waluta o nazwie:" + " " + words[2] + " " + "nie była zdefiniowana wczęśniej błąd wystąpił w linijce:" + " " + currentLineNumber);
            return false;
        }
        course = Double.parseDouble(words[3]);
        if (course < 0) {
            System.out.println("Został podany ujemny kurs wymiany  waluty:" + " " + course + " w linijce:" + " " + currentLineNumber);
            return false;
        }


        if (words[4].compareTo("PROC") == 0) {
            procent = Double.parseDouble(words[5]);
            if (procent < 0) {
                System.out.println("Został podany ujemny procent wymiany  waluty:" + " " + procent + " w linijce:" + " " + currentLineNumber);
                return false;
            }
        } else {
            constant = Double.parseDouble(words[5]);
            if (constant < 0) {
                System.out.println("Został podana ujemna stała pobierana przy wymiane:" + " " + constant + " w linijce:" + " " + currentLineNumber);
                return false;
            }
        }
        if (from.getExchangeToCurrency(to) != null) {
            System.out.println("Wymiana z" + " " + from.getAbbreviation() + " do " + to.getAbbreviation() + " już występowała nie istnije możliwości podowanie tej samej wymiany dwa razy.");
            System.out.println("Powtórzenie wystąpiło w linijce :" + currentLineNumber);
            return false;
        } else {
            exchange = new Exchange(from, to, course, procent, constant);
            try {
                currencyMap.get(words[1]).addExchange(exchange);
            } catch (NullPointerException e) {
                System.out.println("Waluta o nazwie:" + " " + words[1] + " " + "nie była zdefiniowana wczęśniej błąd wystąpił w linijce:" + " " + currentLineNumber);
                return false;
            }
        }
        return true;
    }

    public Map getcurrencyMap() {
        return currencyMap;
    }
}
