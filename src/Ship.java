/**
 * A secret ship.
 * 
 * @author (Isaac Duarte) 
 * @version (6/3/15)
 */
import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

public class Ship extends Rectangle
{
    int y, x;
    final int SCALE = 10;
    public Ship(int dx, int dy){
        x = dx;
        y = dy;
    }

    public void draw(Graphics2D g){
        Arc2D.Double back = new Arc2D.Double(x, y, SCALE*3, SCALE*9, 90, 180, Arc2D.OPEN);
        Arc2D.Double front = new Arc2D.Double(x+(SCALE*20), y, SCALE*3, SCALE*9, 90, -180, Arc2D.OPEN);
        Ellipse2D.Double windows = new Ellipse2D.Double(x+SCALE, y+SCALE*3, SCALE*2, SCALE*2);
        Rectangle hull = new Rectangle(x+(SCALE*3/2), y, SCALE, SCALE*9);
        Rectangle lens = new Rectangle(x+SCALE*12, y-SCALE*7, SCALE, SCALE*3);
        
        g.setColor(Color.pink);
        g.fill(back);
        g.fill(front);       
        g.fill(lens);
        for(int i = 0; i < 20; i++){
            g.fill(hull);
            hull.translate(SCALE, 0);
        }
        
        Rectangle r1 = new Rectangle(x+SCALE*4, y-SCALE*2, SCALE*12, SCALE*2);
        Rectangle r2 = new Rectangle(x+SCALE*7, y-SCALE*6, SCALE*2, SCALE*4);
        Rectangle r3 = new Rectangle(x+SCALE*9, y-SCALE*6, SCALE*3, SCALE);
        
        g.setColor(Color.pink);
        g.fill(r1);
        g.fill(r2);
        g.fill(r3);
        
        for(int i = 0; i < 10; i+=2){
            g.setColor(Color.magenta);
            Line2D.Double line = new Line2D.Double(x+SCALE*4, y+(i*SCALE), x+SCALE*4, y+SCALE+i*SCALE);
            g.draw(line);
            line = new Line2D.Double(x+SCALE*16, y+(i*SCALE), x+SCALE*16, y+SCALE+i*SCALE);
            g.draw(line);
        }
        
        g.setColor(Color.white);
        g.fill(windows);
        windows = new Ellipse2D.Double(x+SCALE*9, y+SCALE*3, SCALE*2, SCALE*2);
        g.fill(windows);
        windows = new Ellipse2D.Double(x+SCALE*17, y+SCALE*3, SCALE*2, SCALE*2);
        g.fill(windows);
        
        Line2D.Double line = new Line2D.Double(x+SCALE*6, y+SCALE*5, x+SCALE*4, y+SCALE*10);
        g.setColor(Color.orange);
        g.fill(line);
    }

    public void translate(int dx, int dy){
        x+=dx;
        y+=dy;
    }
    
    public int getXPos(){
        return x;
    }
}