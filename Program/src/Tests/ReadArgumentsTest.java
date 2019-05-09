import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ReadArgumentsTest {
    private static ReadArguments readArguments;
    private static String[] test1 = {"nie", "poprawne"};
    private static String[] test2 = {"Test.txt", "bw", "1000", "EUR", "USD"};
    private static String[] test3 = {"Test.txt", "ar", "1000"};
    private static String[] test4 = {"Tet.s", "bw", "1000", "EUR", "USD"};
    private static String[] test5 = {"Test.txt", "f", "1000", "EUR", "USD"};
    private static String[] test6 = {"Test.txt", "bw", "-1000", "EUR", "USD"};

    @BeforeAll
    public static void setUpClass() {

    }

    @AfterAll
    public static void tearDownClass() {
        test1=null;
        test2=null;
        test3=null;
        test4=null;
        test5=null;
        test6=null;
        readArguments=null;
    }

    @Test
    void readArgumentsTest1() throws IOException {
        //Given
        readArguments=new ReadArguments(test1);
        //When
        boolean resualt =readArguments.readArguments();
        //Then
        assertFalse(resualt);
    }
    @Test
    void readArgumentsTest2() throws IOException {
        //Given
        readArguments=new ReadArguments(test2);
        //When
        boolean resualt =readArguments.readArguments();
        //Then
        assertTrue(resualt);
    }
    @Test
    void readArgumentsTest3() throws IOException {
        //Given
        readArguments=new ReadArguments(test3);
        //When
        boolean resualt =readArguments.readArguments();
        //Then
        assertTrue(resualt);
    }
    @Test
    void readArgumentsTest4() throws IOException {
        //Given
        readArguments=new ReadArguments(test4);
        //When
        boolean resualt =readArguments.readArguments();
        //Then
        assertFalse(resualt);
    }
    @Test
    void readArgumentsTest5() throws IOException {
        //Given
        readArguments=new ReadArguments(test5);
        //When
        boolean resualt =readArguments.readArguments();
        //Then
        assertFalse(resualt);
    }
    @Test
    void readArgumentsTest6() throws IOException {
        //Given
        readArguments=new ReadArguments(test6);
        //When
        boolean resualt =readArguments.readArguments();
        //Then
        assertFalse(resualt);
    }
}