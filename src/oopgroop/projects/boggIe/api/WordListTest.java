package oopgroop.projects.boggIe.api;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

class WordListTest {
    @Test
    public void ShouldProperlyInputAWord() throws Exception {
        WordList wordlist = new WordList();
        wordlist.PopulateChildren();
        wordlist.AddWordsToWordList("cat", "dog", "door", "camel");

        Assert.assertEquals(8, wordlist.IsWordInList("cat"));
        Assert.assertEquals(12, wordlist.IsWordInList("dog"));
        Assert.assertEquals(10, wordlist.IsWordInList("door"));
        Assert.assertEquals(11, wordlist.IsWordInList("camel"));

        Assertions.assertThrows(Exception.class, () -> wordlist.IsWordInList("poop"));
    }

    @Test
    public void ShouldProperlyInputAWordWithCaseInsensitivity() throws Exception {
        WordList wordlist = new WordList();
        wordlist.PopulateChildren();
        wordlist.AddWordsToWordList("cat", "dog", "door", "camel");

        Assert.assertEquals(8, wordlist.IsWordInList("cAt"));
        Assert.assertEquals(12, wordlist.IsWordInList("doG"));
        Assert.assertEquals(10, wordlist.IsWordInList("DOOr"));
        Assert.assertEquals(11, wordlist.IsWordInList("caMel"));

        Assertions.assertThrows(Exception.class, () -> wordlist.IsWordInList("poop"));
    }
}