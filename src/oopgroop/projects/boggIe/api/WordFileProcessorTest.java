package oopgroop.projects.boggIe.api;

import org.junit.Assert;
import org.junit.jupiter.api.*;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class WordFileProcessorTest {
    private final String fileContents =
            "apple banana candy \n" + "pants shirt person \n" + "freedom giraffe home";

    private final String fileName = "wordFile.txt";

    private File tmpFile;

    @BeforeEach
    void setUp() throws IOException {
        tmpFile = File.createTempFile("wordFile", ".tmp");
        FileWriter writer = new FileWriter(tmpFile);
        writer.write(fileContents);
        writer.close();
    }

    @Test
    public void ShouldParseAFilesContentsIntoAnArrayOfWords() throws FileNotFoundException {
        String[] wordList = WordFileProcessor.ConvertFileToWordList(tmpFile);
        Assert.assertEquals(9, wordList.length);
    }

    @AfterEach
    void tearDown() {
        tmpFile.delete();
    }
}