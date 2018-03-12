/**
 * The class that represents the player's board and is used to compute the result.
 * 
 * @author (Isaac Duarte) 
 * @version (6/3/15)
 */
import javax.swing.*;
import java.awt.Component;
public class PlayerGUI
{
    private int[][] board = new int[10][10];
    private int[][] gBoard = new int[10][10];
    private int sunken, ships;

    public PlayerGUI(){ }
    
    /**
     * Determines whether the guess hit or not and displays a corresponding message.
     * Calls sunk to determine if a ship has been sunken. If sunk returns the number of pieces corresponding to 
     * the size of the ship hit, sunken is incremented indicating the ship has sunk.
     * 
     @return True if a ship has been hit, False if not
     */
    public boolean hit(int x, int y, Component j){
        if(board[x][y] == 2 || board[x][y] == 3 || board[x][y] == 4 || board[x][y] == 5){
            JOptionPane.showMessageDialog(j ,"Your " + board[x][y] + " piece has been hit" );
            board[x][y] -= 6;
            if(sunk(board[x][y], x, y) == board[x][y] + 6){
                JOptionPane.showMessageDialog(j ,"Your battleship has sunk!!!");
                sunken++;
            }
            return true;
        }
        else{
            JOptionPane.showMessageDialog(j ,"The CPU missed");
            return false;
        }
    }

    /**
     * Updates the 2D array of integers representing the player board with a ship starting at board[x][y]
     * with a length of size in the direction pos.
     * Size indicates the value of the ship in the 2D array as well
     */
    public void updateBoard(int x, int y, int size, String pos){
        int count = 0;
        if(pos.equals("Up")){
            while(count < size){
                board[x - count][y] = size;
                count++;
            }
        }
        if(pos.equals("Down")){
            while(count < size){
                board[x + count][y] = size;
                count++;
            }
        }
        if(pos.equals("Left")){
            while(count < size){
                board[x][y - count] = size;
                count++;
            }
        }
        if(pos.equals("Right")){
            while(count < size){
                board[x][y + count] = size;
                count++;
            }
        }                
    }
    
    /**
     @return The number of sunken ships for the player
     */
    public int getSunken(){
        return sunken;
    }
    
    /**
     @return The number of pieces of the same ship that have been hit
     */
    private int sunk(int ship, int row, int col)
    {
        int count = 0;
        if(row >= 0 && row <= 9 && col >= 0 && col <= 9){
            if(board[row][col] == ship)
            {
                count++;
                board[row][col] -= 7;
                count += sunk(ship, row - 1, col);
                count += sunk(ship, row + 1, col);
                count += sunk(ship, row, col - 1);
                count += sunk(ship, row, col + 1);
                board[row][col] += 7;
            }
        }
        return count;
    }

}
