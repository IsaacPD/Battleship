/**
 * Write a description of class CPU here.
 * 
 * @author (Isaac Duarte) 
 * @version (6/3/15)
 */
import java.util.ArrayList;
import java.util.Random;
import java.awt.Point;
import java.awt.Component;
import javax.swing.JOptionPane;
public class CPU
{
    private int[][] cBoard = new int[10][10];
    private boolean turn = false;
    private int sunken;
    private ArrayList<Point> points = new ArrayList<Point>();
    private Audio sound = new Audio();
    
    /**
     * Constructs the 2D array representing the computers ships and initializes all of the points
     * the computer can guess
     */
    public CPU()
    {
        configCBoard();
        for(int x = 0; x < 10; x++)
            for(int y = 0; y < 10; y++)
                points.add(new Point(x, y));
        //printBoard();
    }
    
    /**
     * Randomly guesses a point to hit 
     * 
     @return The point that the computer guessed
     */
    public Point guess()
    {
        Random gen = new Random();
        changeTurn();
        return points.remove(gen.nextInt(points.size()));
    }
    
    /**
     @return True if it is the computer's turn, False otherwise
     */
    public boolean getTurn()
    {
        return turn;
    }
    
    /**
     @return Number of ships sunken for the computer
     */
    public int getSunken()
    {
        return sunken;
    }
    
    /**
     * Determines whether the guess hit or not and displays a corresponding message.
     * Calls sunk to determine if a ship has been sunken. If sunk returns the number of pieces corresponding to 
     * the size of the ship hit, sunken is incremented indicating the ship has sunk.
     * 
     @return True if a ship has been hit, False if not
     */
    public boolean hit(int x, int y, Component j)
    {
        if(cBoard[x][y] == 2 || cBoard[x][y] == 3 || cBoard[x][y] == 4 || cBoard[x][y] == 5){
            JOptionPane.showMessageDialog(j ,"AHH YOU GOT MY " + cBoard[x][y] + " PEG PIECE" );
            cBoard[x][y] -= 6;
            if(sunk(cBoard[x][y], x, y) == cBoard[x][y] + 6){
                //JOptionPane.showMessageDialog(j ,"YOU SUNK MY BATTLESHIP!!!");
                sound.playSunk();
                sunken++;
            }
            changeTurn();
            return true;
        }
        else{
            JOptionPane.showMessageDialog(j ,"NICE TRY GUY BUT YOU MISSED");
            changeTurn();
            return false;
        }
    }

    /**
     * Changes the turn to the opposite of the current value
     */
    private void changeTurn()
    {
        if(turn)
            turn = false;
        else
            turn = true;
    }
    
    /**
     @return The number of pieces of the same ship that have been hit
     */
    private int sunk(int ship, int row, int col)
    {
        int count = 0;
        if(row >= 0 && row <= 9 && col >= 0 && col <= 9){
            if(cBoard[row][col] == ship)
            {
                count++;
                cBoard[row][col] -= 7;
                count += sunk(ship, row - 1, col);
                count += sunk(ship, row + 1, col);
                count += sunk(ship, row, col - 1);
                count += sunk(ship, row, col + 1);
                cBoard[row][col] += 7;
            }
        }
        return count;
    }
    
    /**
     * Randomly places the ships of the computer in a valid configuration
     * However, it is subject to ship overlapping as is the player's but it is unlikely
     */
    private void configCBoard()
    {
        Random gen = new Random();
        int count = 0, ship = 0;//ship ensures there are two 3 peg ships
        for(int i = 2; i <= 5; i++)//Ship size/type
        {
            int x = gen.nextInt(10);//starting row
            int y = gen.nextInt(10);//starting column
            int pos = gen.nextInt(4);//0 is up, 1 is down, 2 is left, 3 is right
            while(cBoard[x][y] != 0){
                x = gen.nextInt(10);
                y = gen.nextInt(10);              
            }

            while(pos != -1){                
                pos = gen.nextInt(4);
                if(pos == 0){
                    if(x-(i-1) >= 0){
                        while(count < i){
                            cBoard[x - count][y] = i;
                            count++;
                        }
                        pos = -1;
                    }
                }
                if(pos == 1){
                    if(x+(i-1) < 10){
                        while(count < i){
                            cBoard[x + count][y] = i;
                            count++;
                        }
                        pos = -1;
                    }
                }
                if(pos == 2){
                    if(y-(i-1) >= 0){
                        while(count < i){
                            cBoard[x][y - count] = i;
                            count++;
                        }
                        pos = -1;
                    }
                }
                if(pos == 3){
                    if(y+(i-1) < 10){
                        while(count < i){
                            cBoard[x][y + count] = i;
                            count++;
                        }
                        pos = -1;
                    }
                }                
            }

            if(i == 3 && ship == 0){
                i = 2;
                ship++;
            }
            count = 0;
        }
    }
}
