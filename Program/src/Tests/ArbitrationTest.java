import Data.Currency;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ArbitrationTest {
    private static ReadFile readFile;
    private static Arbitration arbitration;
    private static Map<String, Currency> currencyMap;
    @BeforeAll
    public static void setUpClass() throws IOException {
        readFile= new ReadFile();
        readFile.readFile("Test.txt");
        currencyMap=readFile.getcurrencyMap();
        arbitration= new Arbitration(currencyMap);
    }

    @AfterAll
    public static void tearDownClass() {
        readFile=null;
        arbitration=null;
    }

    @Test
    void findArbitrage() {
        //Given
        double money=1000;
        //When
        boolean resualt =arbitration.findArbitrage(money);
        //Then
        assertTrue(resualt);
    }
}