package oopgroop.projects.boggIe.api;

import java.util.Random;

public class Dice
{
    private char[] letters;
    private String letter;

    public Dice(String s, int x, int y)
    {
            this.letters = s.toCharArray();
    }

    public Dice(Dice c)    
    {
        this.letter = c.getLetter();
        this.letters = c.getLetters();
    }
    public String rollDice() {
        Random rand = new Random();
        char letter = this.letters[rand.nextInt(6)];
        if (letter == 'Q')
        {
            this.letter = "Qu";
            return "Qu";
        }
        this.letter = String.valueOf(letter);
        return this.letter;
    }
    
    public String getLetter() {
        return letter;
    }
    
    public char[] getLetters(){
        return letters;
    }
    
}
