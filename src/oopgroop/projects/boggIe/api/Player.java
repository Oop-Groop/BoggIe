package oopgroop.projects.boggIe.api;

import java.util.*;
import java.util.Timer;
import java.util.TimerTask;

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
		this.score = 0;
	}

	public int getScore() {
		return this.score;
	}

	public void addScore(int wordScore) {
		this.score += wordScore;
	}

}
