package oopgroop.projects.boggIe.api;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class WordValidatorTest {
    @Test
    public void ShouldReturnTrueForAnInvalidWordForDiagonals()
    {
        Board board = new Board(3, 3);
        ArrayList<int[]> coordinates = new ArrayList<>();
        coordinates.add(new int[]{0, 0});
        coordinates.add(new int[]{1, 1});
        coordinates.add(new int[]{2, 2});

        String testString = generateTestString(board, coordinates);

        WordValidator wv = new WordValidator(board);

        Assert.assertEquals(true, wv.isValidWord(testString));
    }

    @Test
    public void ShouldReturnTrueForAnInvalidWordForStraights()
    {
        Board board = new Board(3, 3);
        ArrayList<int[]> coordinates = new ArrayList<>();
        coordinates.add(new int[]{0, 0});
        coordinates.add(new int[]{0, 1});
        coordinates.add(new int[]{0, 2});

        String testString = generateTestString(board, coordinates);

        WordValidator wv = new WordValidator(board);

        Assert.assertEquals(true, wv.isValidWord(testString));
    }

    private String generateTestString(Board board, ArrayList<int[]> coordinates)
    {
        StringBuilder sb = new StringBuilder();

        for (int[] coordinate : coordinates)
        {
            Board.Die d = board.getDie(coordinate[0], coordinate[1]);
            sb.append(d.getLetter());
        }

        return sb.toString();
    }
}