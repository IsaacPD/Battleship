/**
 * The main game field for the Battleship game that is not click enabled.
 * 
 * @author (Isaac Duarte) 
 * @version (6/3/15)
 */
import java.awt.*;
import javax.swing.JComponent;
import java.util.ArrayList;
public class BoardComponent extends JComponent
{
    private Rectangle d = new Rectangle(0, 0, 0, 0);
    private Rectangle ca = new Rectangle(0, 0, 0, 0);
    private Rectangle ba = new Rectangle(0, 0, 0, 0);
    private Rectangle s = new Rectangle(0, 0, 0, 0);
    private Rectangle cr = new Rectangle(0, 0, 0, 0);
    private ArrayList<Guess> gu = new ArrayList<Guess>();
    private int count, ships;
    final int BOARD = 50;
    final int SHIPSIZE = 49;
    private Color color = Color.green;
    private Ship sh = null;
    
    /**
     * Draws two boards representing the players and the board where they will guess
     * Draws all the ships, and guesses as well
     */
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        Board b = new Board(BOARD, BOARD);
        b.draw(g2);
        b = new Board(BOARD+550, BOARD);
        b.draw(g2);

        g2.setColor(Color.blue);
        g2.setFont(new Font("timesnewroman",0,30));
        g2.drawString("BATTLESHIP", 500, 30);
        g2.setFont(new Font("timesnewroman",0,15));
        g2.setColor(Color.black);
        g2.drawString("Player Board", BOARD+250, BOARD+550);
        g2.drawString("Guess Board", BOARD+800, BOARD+550);

        g2.setColor(color);
        g2.fill(d);
        g2.fill(ca);
        g2.fill(ba);
        g2.fill(s);
        g2.fill(cr);
        for(int x = 0; x < gu.size(); x++)
        {
            if(gu.get(x).getHit() == 1){
                g2.setColor(Color.red);
            }
            else
                g2.setColor(Color.gray);

            g2.fill(gu.get(x));
        }

        if(sh != null)
            sh.draw(g2);
    }
    
    /**
     * Places a ship of length size starting at x, y in the direction specified
    @param x The starting x coordinate of the ship
    @param y The starting y coordinate of the ship
    @param size The size of the ship
    @param direction The direction the ship is place
    */
    public void addShip(int x, int y, int size, String direction){
        x++;
        y++;
        if(direction.equals("Right")){
            if(size == 2){
                d = new Rectangle(x+5+BOARD, y+5+BOARD, size*SHIPSIZE, SHIPSIZE);}
            else if(size == 3 && count == 0){
                cr = new Rectangle(x+5+BOARD, y+5+BOARD, size*SHIPSIZE, SHIPSIZE);
                count++;
            }
            else if(size == 3 && count == 1)
                s = new Rectangle(x+5+BOARD, y+5+BOARD, size*SHIPSIZE, SHIPSIZE);
            else if(size == 4)
                ba = new Rectangle(x+5+BOARD, y+5+BOARD, size*SHIPSIZE, SHIPSIZE);
            else if(size == 5)
                ca = new Rectangle(x+5+BOARD, y+5+BOARD, size*SHIPSIZE, SHIPSIZE);
            repaint();
        }
        else if(direction.equals("Left")){
            if(size == 2){
                d = new Rectangle((x-(size-1)*50)+5+BOARD, y+5+BOARD, size*SHIPSIZE, SHIPSIZE);}
            else if(size == 3 && count == 0){
                cr = new Rectangle((x-(size-1)*50)+5+BOARD, y+5+BOARD, size*SHIPSIZE, SHIPSIZE);
                count++;
            }
            else if(size == 3 && count == 1)
                s = new Rectangle((x-(size-1)*50)+5+BOARD, y+5+BOARD, size*SHIPSIZE, SHIPSIZE);
            else if(size == 4)
                ba = new Rectangle((x-(size-1)*50)+5+BOARD, y+5+BOARD, size*SHIPSIZE, SHIPSIZE);
            else if(size == 5)
                ca = new Rectangle((x-(size-1)*50)+5+BOARD, y+5+BOARD, size*SHIPSIZE, SHIPSIZE);
            repaint();
        }
        else if(direction.equals("Up")){
            if(size == 2){
                d = new Rectangle(x+5+BOARD, (y-(size-1)*50)+5+BOARD, SHIPSIZE, size*SHIPSIZE);}
            else if(size == 3 && count == 0){
                cr = new Rectangle(x+5+BOARD, (y-(size-1)*50)+5+BOARD, SHIPSIZE, size*SHIPSIZE);
                count++;
            }
            else if(size == 3 && count == 1)
                s = new Rectangle(x+5+BOARD, (y-(size-1)*50)+5+BOARD, SHIPSIZE, size*SHIPSIZE);
            else if(size == 4)
                ba = new Rectangle(x+5+BOARD, (y-(size-1)*50)+5+BOARD, SHIPSIZE, size*SHIPSIZE);
            else if(size == 5)
                ca = new Rectangle(x+5+BOARD, (y-(size-1)*50)+5+BOARD, SHIPSIZE, size*SHIPSIZE);
            repaint();
        }
        else if(direction.equals("Down")){
            if(size == 2){
                d = new Rectangle(x+5+BOARD, y+5+BOARD, SHIPSIZE, size*SHIPSIZE);}
            else if(size == 3 && count == 0){
                cr = new Rectangle(x+5+BOARD, y+5+BOARD, SHIPSIZE, size*SHIPSIZE);
                count++;
            }
            else if(size == 3 && count == 1)
                s = new Rectangle(x+5+BOARD, y+5+BOARD, SHIPSIZE, size*SHIPSIZE);
            else if(size == 4)
                ba = new Rectangle(x+5+BOARD, y+5+BOARD, SHIPSIZE, size*SHIPSIZE);
            else if(size == 5)
                ca = new Rectangle(x+5+BOARD, y+5+BOARD, SHIPSIZE, size*SHIPSIZE);
            repaint();
        }
    }
    
    /**
     * Adds a new guess to the arraylist indicating the position and result to visually update
     * the result of the player's guess
     @param x The x position of the player's guess
     @param y The y position of the player's guess
     @param hit Whether the player hit or not (1 is hit 0 is miss)
     */
    public void updateGuess(int x, int y, int hit){
        gu.add(new Guess((x*50)+555+BOARD, (y*50)+5+BOARD, 50, 50, hit));
        repaint();
    }

    /**
     * Adds a new guess to the arraylist indicating the position and result to visually update
     * the result of the computer's guess
     @param x The x position of the computer's guess
     @param y The y position of the computer's guess
     @param hit Whether the computer hit or not (1 is hit 0 is miss)
     */
    public void updateCGuess(int x, int y, int hit){
        gu.add(new Guess((x*50)+5+BOARD, (y*50)+5+BOARD, 50, 50, hit));
        repaint();
    }
    
    /**
     * Changes the color of the player ship pieces
     */
    public void changeShipColor(Color c){
        color = c;
        repaint();
    }
    
    /**
     * Moves the secret ship
     */
    public void move(){
        sh.translate(1, 0);
        repaint();
    }
    
    /**
     * Returns the secret ship to its original position
     */
    public void reset(){
        sh = new Ship(-350, 500);
        repaint();
    }
    
    /**
     * Reveals the secret ship
     */
    public void reveal(){
        sh = new Ship(-350, 500);
        repaint();
    }
    
    /**
     @return The position of the secret ship
     */
    public int getShipX(){
        return sh.getXPos();
    }
}
