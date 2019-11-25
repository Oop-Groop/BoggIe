package oopgroop.projects.boggIe.api;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/*
 * This is the player class
 */
public class Player {
	private int score;
	private final Set<String> guessedWords = new HashSet<>();
	private final String name;
	// For the timer

	public String getName() {
		return name;
	}

	public boolean hasGuessedWord(String word) {
		return guessedWords.contains(word);
	}

	public Collection<String> getGuessedWords() {
		return Collections.unmodifiableCollection(guessedWords);
	}

	public Player(String name) {
		this.name = name;
	}

	public void resetScore() {
		score = 0;
	}

	public int getScore() {
		return score;
	}

	public void addScore(int wordScore) {
		score += wordScore;
	}

}
