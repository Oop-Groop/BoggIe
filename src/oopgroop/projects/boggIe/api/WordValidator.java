package oopgroop.projects.boggIe.api;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import oopgroop.projects.boggIe.api.Board.Die;

public class WordValidator {
	private Board board;
	
	public WordValidator(Board board) {
		this.board = board;
	}
	public boolean isValidWord(String word) throws Exception {
		char currChar = word.charAt(0);
		ArrayList<Die> dice = board.getDie(currChar);
		if(dice.size() == 0) {
			throw new Exception("Character not in board");
		}
		boolean inBoard = false;
		for(Die d : dice) {
			if(BFS(d, word)) {
				inBoard = true;
			}
		}
		return inBoard;
	}
	@SuppressWarnings("unlikely-arg-type")
	private boolean BFS(Die starting, String word) {
		int count = 0;
		char[] wordChars = word.toCharArray();
		Queue<Die> q = new LinkedList<>();
		q.add(starting);
		while(!q.isEmpty()) {
			Die current = q.remove();
			current.isVisited();
			ArrayList<Die> surrounding = board.getSurrounding(current);
			for(Die d : surrounding) {
				if((d.getLetter().equals(String.valueOf(wordChars[count]))) && (d.getVisited() == false)) {
					q.add(d);
					count++;
				}
			}
		}
		return count == word.length();
	}
}
