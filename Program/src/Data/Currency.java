package Data;

import java.util.ArrayList;

public class Currency {
    public static final double INFINITI =-1;
    private int id;
    private String abbreviation;
    private String fullNameCurrency;
    private ArrayList<Exchange> exchanges;
    private ArrayList<Currency> path;
    private double priceOfChange;
    private boolean isStart;

    public Currency(String abbreviation, String fullNameCurrency, int id) {
        this.abbreviation = abbreviation;
        this.fullNameCurrency = fullNameCurrency;
        this.priceOfChange =INFINITI;
        this.isStart = false;
        this.exchanges = new ArrayList<>();
        this.path = new ArrayList<>();
        this.id = id;
    }

    public boolean isCurrencyInPath(Currency currency) {
        for(int i=0;i<path.size()-1;i++){
            if(path.get(i).equals(currency)){
                return true;
            }
        }
        return false;
    }


    public void addToPath(Currency currency) {
        path.add(currency);
    }

    public int getId() {
        return id;
    }


    public ArrayList getPath() {
        return path;
    }

    public void setPath(ArrayList<Currency> path) {
        this.path = new ArrayList<>(path);
    }

    public Exchange getExchangeToCurrency(Currency to) {
        Exchange result = null;
        for (int i = 0; i < exchanges.size(); i++) {
            if (exchanges.get(i).getTo().equals(to)) {
                result = exchanges.get(i);
            }
        }
        if (result != null) {
            return result;
        } else {
            return null;
        }
    }
    public void setStart(boolean isStart) {
        this.isStart = isStart;
    }

    public boolean getStart() {
        return isStart;
    }

    public void addExchange(Exchange exchange) {
        exchanges.add(exchange);
    }

    public void setPriceOfChange(double priceOfChange) {
        this.priceOfChange = priceOfChange;
    }

    public double getPriceOfChange() {
        return priceOfChange;
    }


    public Exchange getExchange(int i) {
        return exchanges.get(i);
    }

    public int gethowManyExchange() {
        return exchanges.size();
    }

    public String getAbbreviation() {
        return abbreviation;
    }

}
