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
	private boolean BFS(Die starting, String word) {
		try {
			boolean found;
			int count = -1;
			char[] wordChars = convertWordtoChar(word);
			Queue<Die> q = new LinkedList<>();
			q.add(starting);
			while(!q.isEmpty()) {
				found  = false;
				Die current = q.remove();
				current.isVisited();
				count++;
				char currChar = wordChars[count];
				ArrayList<Die> surrounding = board.getSurrounding(current);
				for(Die d : surrounding) {
					Character dieChar = d.getLetter().charAt(0);
					if((dieChar.equals(currChar)) && (d.getVisited() == false) && !q.contains(d)) {
						q.add(d);
						found = true;
					}
				}
				if(!found && q.isEmpty()) {
					board.resetDie();
					return false;
				}
			}
		} catch(ArrayIndexOutOfBoundsException e) {}
		board.resetDie();
		return true;
	}
	
	private char[] convertWordtoChar(String word) {
		char[] wordChars = word.toCharArray();
		char[] newChars = new char[word.length()-1];
		for(int i = 1; i < wordChars.length; i++) {
			newChars[i-1] = wordChars[i];
		}
		return newChars;
	}
}
