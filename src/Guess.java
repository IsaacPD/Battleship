/**
 * A class representing a guess in the game 
 * 
 * @author (Isaac Duarte) 
 * @version (6/3/15)
 */
import java.awt.*;
import javax.swing.*;
public class Guess extends Rectangle
{
    int hit;
    /**
     * Constructs a rectangle indicating a guess point
     */
    public Guess(int col, int row, int width, int height, int guess){
        super(col, row, width, height);
        hit = guess;
    }
    
    /**
     @return The value of hit (1 or 0)
     */
    public int getHit(){
        return hit;
    }
}
