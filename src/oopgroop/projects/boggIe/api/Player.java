package oopgroop.projects.boggIe.api;
import java.util.*;

/*
 * This is the player class
 */
public class Player {
	private int score;
	Set<String> guessedWords = new HashSet<>();
	
	public Player() {
		this.score = 0;
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
