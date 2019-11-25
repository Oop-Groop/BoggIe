package oopgroop.projects.boggIe.api;

/**
 * Word list is a trie that contains all the words for the
 * game as well as their scores.
 *
 * Here is a link of what a Trie is
 * https://www.hackerearth.com/practice/data-structures/advanced-data-structures/trie-keyword-tree/tutorial/
 */
public class WordList {
    private WordList[] children;
    private int wordValue = 0;

    // Accepts a list of words to put as children
    public WordList(String... words) {
        throw new RuntimeException("Not Implemented");
    }

    /**
     * Will check for a word in the list, but will also return the
     * score of the word that the user gives
     *
     * if word is not in list, will return 0
     *
     * @param word the word the user gives
     * @return the score of the word
     */
    public int IsWordInList(String word) {
        throw new RuntimeException("Not Implemented");
    }
}
