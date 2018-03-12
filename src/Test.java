import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.*;
import java.io.*;

public class Test  extends Game implements ActionListener
{
    int count;
    String path = "../Eclipsified/src/Icons/";    
    Point cGuess;
    PlayerGUI player = new PlayerGUI();
    CPU opponent = new CPU();
    Timer t = new Timer(10, this);
    Audio sound = new Audio();

    JMenuBar menuBar;
    JMenuItem popGuess, popPlace, on = new JMenuItem("On   "), off = new JMenuItem("Off   ");
    JFrame frame = new JFrame(), opt = new JFrame();
    JPanel panel = new JPanel();
    JButton placeShip = new JButton("Place a ship"), guess = new JButton("Make Guess");

    ArrayList<String> sizes = new ArrayList<String>();
    ArrayList<String> direc = new ArrayList<String>();

    BoardTest b = new BoardTest();

    public Test(){
        int y = 0;
        for(int x = 2; x <= 5; x++){
            sizes.add("" + x);
            if(y == 0 && x == 3){
                x--;
                y++;
            }
        }

        direc.add("Up");
        direc.add("Down");
        direc.add("Left");
        direc.add("Right");
    }

    /**
     * Constructs the frame and action listeners
     */
    public void play()
    {            

        //createPopupMenu();
        generateMenu();
        frame.setJMenuBar(menuBar);

        sound.playSong();

        guess.setEnabled(false);
        guess.setActionCommand("guess");

        placeShip.setActionCommand("place");

        frame.setTitle("Battleship");
        frame.setSize(1450, 670);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        opt.setTitle("Choose");
        opt.setSize(240, 80);
        opt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel.add(placeShip);
        panel.add(guess);
        opt.add(panel);

        frame.getContentPane().setBackground(new Color(34,139,34));
        frame.add(b);

        try{
            frame.setIconImage(ImageIO.read(new File(path + "icon.png")));
        }
        catch(Exception ex){}

        MouseListener m = new MouseEnterListener();
        b.addMouseListener(m);
        placeShip.addActionListener(this);
        guess.addActionListener(this);

        frame.setLocation(25, 25);
        opt.setLocationRelativeTo(frame);

        frame.setVisible(true);
        //opt.setVisible(true);
    }

    /**
     * Constructs the Menu Bar for the game
     */
    private void generateMenu(){
        menuBar = new JMenuBar();

        JMenu file = new JMenu("File");
        JMenu tools = new JMenu("Tools");
        JMenu help = new JMenu("Help");
        JMenu preferences = new JMenu("Preferences   ");
        JMenu audio = new JMenu("Sound   ");
        JMenu color = new JMenu("Color   "); 

        JMenuItem open = new JMenuItem("Open   ");
        JMenuItem save = new JMenuItem("Save   ");
        JMenuItem exit = new JMenuItem("Exit   ");      
        JMenuItem about = new JMenuItem("Instructions   ");
        JMenuItem magenta = new JMenuItem("Magenta   ");
        JMenuItem orange = new JMenuItem("Orange   ");      
        JMenuItem yellow = new JMenuItem("Yellow   ");
        JMenuItem gray = new JMenuItem("Gray   ");
        JMenuItem green = new JMenuItem("Green   ");
        JMenuItem secret = new JMenuItem("Secret   ");

        secret.setActionCommand("secret");
        secret.addActionListener(this);      
        green.setActionCommand("green");
        green.addActionListener(this);
        orange.setActionCommand("orange");
        orange.addActionListener(this);
        magenta.setActionCommand("magenta");
        magenta.addActionListener(this);
        yellow.setActionCommand("yellow");
        yellow.addActionListener(this);
        gray.setActionCommand("gray");
        gray.addActionListener(this);
        on.setActionCommand("on");
        on.addActionListener(this);
        on.setEnabled(false);
        off.setActionCommand("off");
        off.addActionListener(this);
        about.setActionCommand("about");
        about.addActionListener(this);

        color.add(green);
        color.add(orange);
        color.add(yellow);
        color.add(magenta);
        color.add(gray);
        audio.add(off);
        audio.add(on);
        preferences.add(color);
        preferences.add(audio);
        file.add(open);
        file.add(save);
        file.addSeparator();
        file.add(exit);
        tools.add(preferences);
        help.add(about);
        help.add(secret);

        menuBar.add(file);
        menuBar.add(tools);
        menuBar.add(help);
    }

    public void createPopupMenu() {

        //Create the popup menu.
        JPopupMenu popup = new JPopupMenu();
        popGuess = new JMenuItem("Guess");
        popGuess.setActionCommand("guess");
        popGuess.addActionListener(this);
        popGuess.setEnabled(false);
        popup.add(popGuess);
        popPlace = new JMenuItem("Place Ship");
        popPlace.setActionCommand("place");
        popPlace.addActionListener(this);
        popup.add(popPlace);

        //Add listener to the text area so the popup menu can come up.
        MouseListener popupListener = new PopupListener(popup);
        b.addMouseListener(popupListener);
    }

    /**
     * Determines the correct action to perform, based on the Action Command set for acion listening variables/objects
     */
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand() != null){
            if(e.getActionCommand().equals("about")){
                String line = "";
                String temp;
                try{
                    BufferedReader in = new BufferedReader(new FileReader(path + "README.TXT"));
                    while((temp = in.readLine()) != null)
                    {
                        line += temp + "\n";
                    }
                    in.close();
                }
                catch(Exception ex){}

                JOptionPane.showMessageDialog(b, line);
            }
            else if(e.getActionCommand().equals("off")){
                sound.stopSong();
                off.setEnabled(false);
                on.setEnabled(true);
            }
            else if(e.getActionCommand().equals("on")){
                sound.playSong();
                off.setEnabled(true);
                on.setEnabled(false);
            }
            else if(e.getActionCommand().equals("secret")){
                String input = JOptionPane.showInputDialog(frame, "Enter the Secret Code", "Secret");
                try{
                    if(input.equalsIgnoreCase("Computer Science is the Bomb")){
                        b.reveal();
                        t.start();
                    }
                }
                catch(Exception ex){}
            }
            else if(e.getActionCommand().equals("green")){b.changeShipColor(Color.green);}
            else if(e.getActionCommand().equals("yellow")){b.changeShipColor(Color.yellow);}
            else if(e.getActionCommand().equals("magenta")){b.changeShipColor(Color.magenta);}
            else if(e.getActionCommand().equals("gray")){b.changeShipColor(Color.gray);}
            else if(e.getActionCommand().equals("orange")){b.changeShipColor(Color.orange);}
        }

        try{
            if(b.getShipX() >= 1300)
                b.reset();
            b.move();}
        catch(Exception ex){}
    }

    public static void main(String[] args){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } 
        catch (Exception e) {}
        Test game = new Test();
        game.play();
    }

    class MouseEnterListener implements MouseListener{
        int size, two, three, four, five, count;
        
        /**
         * Listens for where the mouse was pressed and if in a valid position sets the size of the ship to be placed
         */
        public void mousePressed(MouseEvent event){
            if(event.getX() >= 1100 && event.getX() <= 1100 + b.SHIPSIZE*5){
                if(event.getY() >= b.BOARD && event.getY() <= b.BOARD + b.SHIPSIZE && two == 0){
                    size = 2;
                    two++;
                }
                else if(event.getY() >= b.BOARD+90 && event.getY() <= b.BOARD+90 + b.SHIPSIZE && three <2){
                    size = 3;
                    three++;
                }
                else if(event.getY() >= b.BOARD+180 && event.getY() <= b.BOARD+180 + b.SHIPSIZE && three <2){
                    size = 3;
                    three++;
                }
                else if(event.getY() >= b.BOARD+270 && event.getY() <= b.BOARD+270 + b.SHIPSIZE && four == 0){
                    size = 4;
                    four++;
                }
                else if(event.getY() >= b.BOARD+360 && event.getY() <= b.BOARD+360 + b.SHIPSIZE && five == 0){
                    size = 5;
                    five++;
                }
            }
        }
        
        /**
         * Listens for where the mouse is released and if in a valid position places a ship of length size
         * If all the ships have been placed listens for a guess to place
         * If the player or opponent's ships have all sunk indicates that the game is over
         */
        public void mouseReleased(MouseEvent event){
            if(opponent.getSunken() == 5 || player.getSunken() == 5){
                sound.playFanfare();
                JOptionPane.showMessageDialog(frame, "The game is over" + ((opponent.getSunken() == 5) ? "\nPlayer wins" : "\nThe computer wins"));
            }
            else if(event.getX() >= b.BOARD && event.getX() <= b.BOARD +500 && 
            event.getY() >= b.BOARD && event.getY() <= b.BOARD +500 && count <= 4){
                String direction =(String)JOptionPane.showInputDialog(frame, "What Direction will you place it?", "Place Ship", JOptionPane.PLAIN_MESSAGE,
                        null, direc.toArray(), direc.toArray()[0]);
                if(direction != null){
                    int xCor = (event.getX()-b.BOARD)/50;
                    int yCor = (event.getY()-b.BOARD)/50;

                    b.addShip(xCor*50, yCor*50, size, direction);
                    player.updateBoard(yCor, xCor, size, direction);

                    if(size != 0)
                        count++;
                    size = 0;
                }
            }
            else if(event.getX() >= b.BOARD+550 && event.getX() <= b.BOARD +1050 && 
            event.getY() >= b.BOARD && event.getY() <= b.BOARD +500 && count == 5){
                if(opponent.getTurn()){
                    cGuess = opponent.guess();
                    boolean k = player.hit((int)(cGuess.getX()),(int)(cGuess.getY()), frame);
                    if(k){//Code to update b(board)
                        b.updateCGuess((int)cGuess.getY(),(int)cGuess.getX(), 1);
                        sound.playHit();
                    }
                    else
                        b.updateCGuess((int)cGuess.getY(),(int)cGuess.getX(), 0);
                }
                else{
                    int xCor = (event.getX() - b.BOARD - 550)/50;
                    int yCor = (event.getY() - b.BOARD)/50;

                    if(opponent.hit(yCor, xCor, frame)){
                        b.updateGuess(xCor, yCor, 1);
                        sound.playHit();
                    }
                    else
                        b.updateGuess(xCor, yCor, 0);
                }
            }
        }

        public void mouseClicked(MouseEvent event){}

        public void mouseEntered(MouseEvent event){}

        public void mouseExited(MouseEvent event){}
    }

    class PopupListener extends MouseAdapter {
        JPopupMenu popup;

        PopupListener(JPopupMenu popupMenu) {
            popup = popupMenu;
        }

        public void mousePressed(MouseEvent e) {
            maybeShowPopup(e);
        }

        public void mouseReleased(MouseEvent e) {
            maybeShowPopup(e);
        }

        private void maybeShowPopup(MouseEvent e) {
            if (e.isPopupTrigger()) {
                popup.show(e.getComponent(),
                    e.getX(), e.getY());
            }
        }
    }    
}
