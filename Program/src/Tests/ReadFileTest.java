
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ReadFileTest {
    private static ReadFile readFile;
    private static String nameFile;
    private static String nameFileWront;
    private static String nameFileEmptyLine;
    private static String nameFileWrongFormat;

    @BeforeAll
    public static void setUpClass() {
        readFile=new ReadFile();
        nameFileWront="Wrong.txt";
        nameFileEmptyLine="EmptyLine.txt";
        nameFileWrongFormat="WrongFormat.txt";
    }

    @AfterAll
    public static void tearDownClass() {
        readFile=null;
        nameFileWrongFormat=null;
        nameFileWront=null;
        nameFileEmptyLine=null;
    }
    @Test
    void readFileFilenotExist() throws IOException {
        //When
        boolean resualt = readFile.readFile(nameFileWront);
        //Then
        assertFalse(resualt);
    }
    @Test
    void readFileEmptyLine() throws IOException {
        //When
        boolean resualt = readFile.readFile(nameFileEmptyLine);
        //Then
        assertFalse(resualt);
    }
    @Test
    void readFileWrongFormat() throws IOException {
        //When
        boolean resualt = readFile.readFile(nameFileWrongFormat);
        //Then
        assertFalse(resualt);
    }
}