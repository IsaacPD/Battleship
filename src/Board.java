/**
 * Represents a board of size 10*10 and is drawable
 * 
 * @author (Isaac Duarte) 
 * @version (6/3/15)
 */
import java.awt.*;
import javax.swing.*;
import java.awt.geom.Line2D;
public class Board
{
    private int x, y;
    
   /**
    @param a x coordinate of top left corner of the board
    @param b y coordinate of top left corner of the board
    */
    public Board(int a, int b)
    {
        x = a;
        y = b;
    }
    
    /**
     * Draws the board
     */
    public void draw(Graphics2D g)
    {
        Rectangle field = new Rectangle(x+5, y+5, 500, 500);
        Line2D.Double row, col;
        g.setColor(Color.cyan);
        g.fill(field);
        g.setColor(Color.blue);
        for(int a = 5; a <= 550; a+=50){
            row = new Line2D.Double(x+5, y+a, x+505, y+a);
            g.draw(row);
        }
        for(int a = 5; a <= 550; a+=50){
            col = new Line2D.Double(x+a, y+5, x+a, y+505);
            g.draw(col);
        }
        
        //Code to label grid 0-9
        g.setColor(Color.orange);
        
        int count = 0;
        char[] alphabet = new char[26];
        for (char c = 'a'; c <= 'z'; c++)
            alphabet[c - 'a'] = c;
            
        for(int a = 0; a < 500; a+=50){
            g.drawString("" + alphabet[count], x-5, (y-5)+a+30);
            count++;
        }
        count = 0;
        for(int a = 0; a < 500; a+= 50){
            g.drawString("" + count, (x-5)+a+30, y-5);
            count++;
        }
    }
}
