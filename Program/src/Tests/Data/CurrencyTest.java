package Data;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyTest {
    private static Currency currency;
    @BeforeAll
    public static void setUpClass()  {
     currency= new Currency("USD","dollar Amerykański",1);
    }

    @AfterAll
    public static void tearDownClass() {
        currency=null;
    }
    @Test
    void isCurrencyInPath() {
        //Given
        Currency currencyForTest=new Currency("EUR","euro",2);
        //When
        boolean resualt = currency.isCurrencyInPath(currencyForTest);
        //Then
        assertFalse(resualt);
    }


    @Test
    void getId() {
        //When
        int expectedResult = 1;
        int result=currency.getId();
        //Then
        assertEquals(expectedResult,result);
    }

    @Test
    void getPath() {
        //When
        ArrayList<Currency> expectedResult = new ArrayList<>();
        ArrayList<Currency> result=currency.getPath();
        //Then
        assertEquals(expectedResult,result);
    }

    @Test
    void getExchangeToCurrency() {
        //Given
        Currency currencyForTest=new Currency("EUR","euro",2);
        Exchange exchangeForTest= new Exchange(currency,currencyForTest,231,23,0);
        //When
        currency.addExchange(exchangeForTest);
        Exchange expectedResult=exchangeForTest;
        Exchange result=currency.getExchangeToCurrency(currencyForTest);
        //Then
        assertEquals(expectedResult,result);
    }


}