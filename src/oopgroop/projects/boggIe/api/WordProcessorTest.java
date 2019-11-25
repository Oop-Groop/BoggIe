package oopgroop.projects.boggIe.api;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.Buffer;

import static org.junit.jupiter.api.Assertions.*;

class WordProcessorTest {
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
        String[] wordList = WordProcessor.ConvertFileToWordList(tmpFile);
        Assert.assertEquals(9, wordList.length);
    }

    @AfterEach
    void tearDown() {
        tmpFile.delete();
    }
}