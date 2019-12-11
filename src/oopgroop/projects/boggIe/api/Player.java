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
	

	public Player(final String name) {
		this.name = name;
	}

	public void addScore(final int wordScore) {
		score += wordScore;
	}

	public Collection<String> getGuessedWords() {
		return Collections.unmodifiableCollection(guessedWords);
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	public boolean hasGuessedWord(final String word) {
		return guessedWords.contains(word);
	}
	
	public void addGuessedWord(String word) {
		this.guessedWords.add(word);
	}

	public void resetScore() {
		score = 0;
	}

}
