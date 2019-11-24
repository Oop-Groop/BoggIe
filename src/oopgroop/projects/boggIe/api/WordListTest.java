package oopgroop.projects.boggIe.api;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordListTest {
    @Test
    public void ShouldProperlyInputAWord() {
        WordList wordlist = new WordList("cat", "dog", "door", "camel");

        Assert.assertEquals(true, wordlist.IsWordInList("cat"));
        Assert.assertEquals(true, wordlist.IsWordInList("dog"));
        Assert.assertEquals(true, wordlist.IsWordInList("door"));
        Assert.assertEquals(true, wordlist.IsWordInList("camel"));

        Assert.assertEquals(false, wordlist.IsWordInList("poop"));
    }

    @Test
    public void ShouldProperlyInputAWordWithCaseInsensitivity() {
        WordList wordlist = new WordList("cat", "dog", "door", "camel");

        Assert.assertEquals(true, wordlist.IsWordInList("cAt"));
        Assert.assertEquals(true, wordlist.IsWordInList("doG"));
        Assert.assertEquals(true, wordlist.IsWordInList("DOOr"));
        Assert.assertEquals(true, wordlist.IsWordInList("caMel"));

        Assert.assertEquals(false, wordlist.IsWordInList("Poop"));
    }
}