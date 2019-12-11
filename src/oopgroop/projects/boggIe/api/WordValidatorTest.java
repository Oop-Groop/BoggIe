package oopgroop.projects.boggIe.api;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordValidatorTest {
    @Test
    public void ShouldReturnTrueForAnInvalidWord()
    {
        Board board = new Board(3, 3);
        Board.Die d1 = board.getDie(0, 0);
        Board.Die d2 = board.getDie(1, 1);
        Board.Die d3 = board.getDie(2, 2);
        String testString = d1.getLetter() + d2.getLetter() + d3.getLetter();

        System.out.println(testString);

        WordValidator wv = new WordValidator(board);

        Assert.assertEquals(true, wv.IsValidWord(testString));
    }
}