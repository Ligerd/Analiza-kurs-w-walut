import Data.Currency;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BestWayToConvertTest {
    private static ReadFile readFile;
    private static BestWayToConvert bestWayToConvert;
    private static  Map<String, Currency> currencyMap;
    @BeforeAll
    public static void setUpClass() throws IOException {
        readFile= new ReadFile();
        readFile.readFile("Test.txt");
        currencyMap=readFile.getcurrencyMap();
        bestWayToConvert = new BestWayToConvert(currencyMap);
    }

    @AfterAll
    public static void tearDownClass() {
        readFile=null;
        bestWayToConvert =null;
    }
    @Test
    void findBestWayToConvert() {
        //Given
        Currency fromChange=currencyMap.get("EUR");
        Currency toChange=currencyMap.get("USD");
        double money=1000;
        //When
        boolean resualt = bestWayToConvert.findBestWayToConvert(fromChange,toChange,money);
        //Then
        assertTrue(resualt);
    }

    @Test
    void findBestWayToConvertWrongData() {
        //Given
        Currency fromChange=currencyMap.get("EUR");
        Currency toChange=currencyMap.get("RUB");
        double money=1000;
        //When
        boolean resualt = bestWayToConvert.findBestWayToConvert(fromChange,toChange,money);
        //Then
        assertFalse(resualt);
    }

}