import Data.Currency;
import Data.Exchange;

import java.util.*;

public class BestWayToConvert {
    private Map<String, Currency> currencyMap;


    public BestWayToConvert(Map<String, Currency> currencyMap) {
        this.currencyMap = currencyMap;
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


    public boolean findBestWayToConvert(Currency from, Currency to, double money) {
        List<List<Currency>> results = getAllPaths(from, to);
        double bestmoney = 0;
        double currentwalue = 0;
        List<Currency> bestWayToConvert = null;
        if(results.size()==0){
            return false;
        }
        for (int i = 0; i < results.size(); i++) {
            List<Currency> path = results.get(i);
            for (int iterator = 0; iterator < path.size() - 1; iterator++) {
                if (iterator != path.size()) {
                    int nextelement = iterator;
                    nextelement++;
                    Currency fromw = path.get(iterator);
                    Currency to1 = path.get(nextelement);
                    Exchange exchange = fromw.getExchangeToCurrency(to1);
                    if (iterator == 0) {
                        currentwalue = makeConvert(money, exchange.getCourse(), exchange.getPercent(), exchange.getConstant());
                    } else {
                        currentwalue = makeConvert(currentwalue, exchange.getCourse(), exchange.getPercent(), exchange.getConstant());
                    }
                }
            }
            if (currentwalue > bestmoney) {
                bestmoney = currentwalue;
                bestWayToConvert = path;
            }
            currentwalue = 0;
        }
        writeToConsol((ArrayList<Currency>) bestWayToConvert,bestmoney);
        return true;
    }


    private void writeToConsol(ArrayList<Currency> path, double price){
        for (int x = 0; x < path.size(); x++) {
            System.out.print(path.get(x).getAbbreviation() + " -> ");
        }
        System.out.println( price+" " +path.get(path.size()-1).getAbbreviation());
    }

    private List<List<Currency>> getAllPaths(Currency from, Currency to) {
        List<List<Currency>> result = new ArrayList<List<Currency>>();
        if (from.equals(to)) {
            List<Currency> temp = new ArrayList<Currency>();
            temp.add(from);
            result.add(temp);
            return result;
        }
        boolean[] visited = new boolean[currencyMap.size()];
        Deque<Currency> path = new ArrayDeque<Currency>();
        getAllPathsDFS(from, to, visited, path, result);
        return result;
    }

    private void getAllPathsDFS(Currency from, Currency to, boolean[] visited, Deque<Currency> path, List<List<Currency>> result) {
        visited[from.getId()] = true;
        path.add(from);
        if (from.equals(to)) {
            result.add(new ArrayList<Currency>(path));
        } else {
            if (currencyMap.containsKey(from.getAbbreviation())) {
                for (int i = 0; i < currencyMap.get(from.getAbbreviation()).gethowManyExchange(); i++) {
                    {
                        if (!visited[from.getExchange(i).getTo().getId()]) {
                            getAllPathsDFS(from.getExchange(i).getTo(), to, visited, path, result);
                        }
                    }
                }
            }
        }
        path.removeLast();
        visited[from.getId()] = false;
    }

}
