package oopgroop.projects.boggIe.api;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WordListTest {
    @Test
    public void ShouldProperlyInputAWord() throws Exception {
        WordList wordlist = new WordList();
        wordlist.PopulateChildren();
        wordlist.AddWordsToWordList("cat", "dog", "door", "camel");

        Assert.assertEquals(8, wordlist.GetScoreForWord("cat"));
        Assert.assertEquals(12, wordlist.GetScoreForWord("dog"));
        Assert.assertEquals(10, wordlist.GetScoreForWord("door"));
        Assert.assertEquals(11, wordlist.GetScoreForWord("camel"));

        Assertions.assertThrows(Exception.class, () -> wordlist.GetScoreForWord("poop"));
    }

    @Test
    public void ShouldProperlyInputAWordWithCaseInsensitivity() throws Exception {
        WordList wordlist = new WordList();
        wordlist.PopulateChildren();
        wordlist.AddWordsToWordList("cat", "dog", "door", "camel");

        Assert.assertEquals(8, wordlist.GetScoreForWord("cAt"));
        Assert.assertEquals(12, wordlist.GetScoreForWord("doG"));
        Assert.assertEquals(10, wordlist.GetScoreForWord("DOOr"));
        Assert.assertEquals(11, wordlist.GetScoreForWord("caMel"));

        Assertions.assertThrows(Exception.class, () -> wordlist.GetScoreForWord("poop"));
    }
}