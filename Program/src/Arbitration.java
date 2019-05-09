import Data.Currency;
import Data.Exchange;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class Arbitration {
    private Map<String, Currency> currencyMap;
    private Currency[] tablica;

    public Arbitration(Map<String, Currency> currencyMap) {
        this.currencyMap = currencyMap;
        this.tablica = new Currency[currencyMap.size()];

    }

    private void makeTablica(Currency from) {
        tablica[0] = from;
        int iterator = 1;
        Set keys = currencyMap.keySet();
        for (Object key : keys) {
            if (currencyMap.get(key).equals(from)) {
                continue;
            }
            tablica[iterator] = currencyMap.get(key);
            iterator++;
        }

    }

    private double makeConvert(double actual_money, double course, double percent, double constant) {
        double money_after_change = 0;
        if (percent != 0) {
            money_after_change = actual_money * course - ((actual_money * course) * percent);
        } else {
            money_after_change = actual_money * course - constant;
        }
        return money_after_change;
    }

    private void makePraceForCurrency(double money) {
        double moneyAfterChange = 0;
        Currency root = tablica[0];
        for (int i = 0; i < currencyMap.size() - 1; i++) {
            for (int iterator = 0; iterator < tablica.length; iterator++) {
                if (iterator == 0 && tablica[iterator].getPriceOfChange() == Currency.INFINITI) {
                    tablica[iterator].setStart(true);
                    tablica[iterator].addToPath(tablica[iterator]);
                }
                if (tablica[iterator].getPriceOfChange() == Currency.INFINITI && iterator > 0) {
                    continue;
                }
                for (int index = 0; index < tablica[iterator].gethowManyExchange(); index++) {
                    Exchange exchange = tablica[iterator].getExchange(index);
                    Currency currencyTo = exchange.getTo();
                    Currency currencyFrom = exchange.getFrom();
                    if (!currencyFrom.isCurrencyInPath(currencyTo) || currencyTo == root) {
                        if (tablica[iterator].getStart()) {
                            moneyAfterChange = makeConvert(money, exchange.getCourse(), exchange.getPercent(), exchange.getConstant());
                        } else {
                            moneyAfterChange = makeConvert(currencyFrom.getPriceOfChange(), exchange.getCourse(), exchange.getPercent(), exchange.getConstant());
                        }
                        if (currencyTo.getPriceOfChange() == Currency.INFINITI || moneyAfterChange > currencyTo.getPriceOfChange()) {
                            currencyTo.setPriceOfChange(moneyAfterChange);
                            currencyTo.setPath(currencyFrom.getPath());
                            currencyTo.addToPath(currencyTo);
                            if (currencyTo.equals(root)){
                                return;
                            }
                        }
                    }
                }
            }
        }
        tablica[0].setStart(false);
    }

    public boolean findArbitrage(double money) {
        Set keys = currencyMap.keySet();
        for (Object key : keys) {
            makeTablica(currencyMap.get(key));
            makePraceForCurrency(money);
            if (currencyMap.get(key).getPriceOfChange() != Currency.INFINITI) {
                ArrayList path = currencyMap.get(key).getPath();
                writeArbitrage(path,currencyMap.get(key));
                return true;
            } else {
                for (Object keyhelp : keys) {
                    currencyMap.get(keyhelp).setPriceOfChange(Currency.INFINITI);
                    currencyMap.get(keyhelp).getPath().clear();
                }
            }
        }
        return false;
    }
    private void writeArbitrage(ArrayList<Currency> path, Currency start){
        for (int iterator = 0; iterator < path.size() - 1; iterator++) {
            System.out.print(path.get(iterator).getAbbreviation() + " -> ");
        }

        System.out.println(start.getAbbreviation() + " -> " + start.getPriceOfChange()+" "+ start.getAbbreviation());

    }
}
