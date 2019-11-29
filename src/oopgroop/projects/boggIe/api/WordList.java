package oopgroop.projects.boggIe.api;

/**
 * Word list is a trie that contains all the words for the game as well as their
 * scores.
 *
 * Here is a link of what a Trie is
 *
 * https://www.hackerearth.com/practice/data-structures/advanced-data-structures/trie-keyword-tree/tutorial/
 */
public class WordList {
	private final WordList[] children = new WordList[26];
	private int wordValue = 0;
	public String letter;

	/**
	 * This will instantiate the WordList with a root node that will connect all of
	 * the paths together
	 */
	public WordList() {
		letter = "*";
	}

	/**
	 * How to use:
	 *
	 * WordList list = new WordList() list.PopulateChildren()
	 * list.AddWordsToList("cat", "dog", "door")
	 *
	 * // or
	 *
	 * String[] listOfWords = new String[]{ "cat", "dog", "door" };
	 * list.PopulateChildren() list.AddWordsToList(listOfWords)
	 */
	private WordList(final String letter) {
		this.letter = letter;
	}

	/**
	 * Adds all of the words given to the Word List
	 * 
	 * @param words words to add
	 */
	public void AddWordsToWordList(final String... words) {
		final String[] lowerCaseWords = convertWordsToLowerCase(words);

		for (final String word : lowerCaseWords)
			addWordToWordList(word, 0);
	}

	private void addWordToWordList(final String word, final int currentScore) {
		if (word.length() == 0) {
			wordValue = currentScore;
			return;
		}

		// Get the child that has that starting letter
		final char startingLetter = word.charAt(0);
		final WordList node = children[startingLetter - 97];
		node.PopulateChildren();

		node.addWordToWordList(word.substring(1), currentScore + LetterScores.GetScoreForLetter(word.substring(0, 1)));

	}

	private String[] convertWordsToLowerCase(final String... words) {
		if (words.length == 1) {
			final String[] lowerCaseWord = new String[] { words[0].toLowerCase() };

			return lowerCaseWord;
		}

		for (int i = 0; i < words.length - 1; i++)
			words[i] = words[i].toLowerCase();

		return words;
	}

	/**
	 * Will check for a word in the list, but will also return the score of the word
	 * that the user gives
	 *
	 * if word is not in list, will throw an exception that you must catch
	 *
	 * @param word the word the user gives
	 * @return the score of the word
	 */
	public int GetScoreForWord(final String word) throws Exception {
		final String lowerCaseWord = convertWordsToLowerCase(word)[0];
		final WordList startingNode = children[lowerCaseWord.charAt(0) - 97];

		return search(startingNode, lowerCaseWord.substring(1));
	}

	/**
	 * Populates the children of the current node
	 */
	public void PopulateChildren() {
		for (int i = 0; i < 26; i++) {
			final WordList currentChild = children[i];

			if (currentChild == null)
				children[i] = new WordList(String.valueOf((char) (i + 97)));
		}
	}

	private int search(final WordList node, final String word) throws Exception {
		if (word.length() == 0)
			return node.wordValue;

		WordList nextNode;
		try {
			nextNode = node.children[word.charAt(0) - 97];
		} catch (final NullPointerException e) {
			throw new Exception("Invalid Word");
		}

		return search(nextNode, word.substring(1));
	}
}
