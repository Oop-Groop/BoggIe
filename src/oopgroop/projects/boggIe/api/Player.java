package oopgroop.projects.boggIe.api;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;

/*
 * This is the player class
 */
public class Player extends TimerTask {
	private int score;
	Set<String> guessedWords = new HashSet<>();
	
	//For the timer
	private Timer myTimer;
	
	public void Reminder (int seconds){
		myTimer.schedule(new Player(), seconds*1000);
	}
	
	public void run() {
		System.out.println("Time's up!");
		myTimer.cancel();
	}
	
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
	public void addWord(String word) {
		this.guessedWords.add(word);
	}
	public Set<String> getWords(){
		return this.guessedWords;
	}
	
	
}
