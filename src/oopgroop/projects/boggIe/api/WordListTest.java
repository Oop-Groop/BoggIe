package oopgroop.projects.boggIe.api;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordListTest {
    @Test
    public void ShouldProperlyInputAWord() {
        WordList wordlist = new WordList("cat", "dog", "door", "camel");

        Assert.assertEquals(8, wordlist.IsWordInList("cat"));
        Assert.assertEquals(12, wordlist.IsWordInList("dog"));
        Assert.assertEquals(10, wordlist.IsWordInList("door"));
        Assert.assertEquals(11, wordlist.IsWordInList("camel"));

        Assert.assertEquals(0, wordlist.IsWordInList("poop"));
    }

    @Test
    public void ShouldProperlyInputAWordWithCaseInsensitivity() {
        WordList wordlist = new WordList("cat", "dog", "door", "camel");

        Assert.assertEquals(8, wordlist.IsWordInList("cAt"));
        Assert.assertEquals(12, wordlist.IsWordInList("doG"));
        Assert.assertEquals(10, wordlist.IsWordInList("DOOr"));
        Assert.assertEquals(11, wordlist.IsWordInList("caMel"));

        Assert.assertEquals(0, wordlist.IsWordInList("Poop"));
    }
}