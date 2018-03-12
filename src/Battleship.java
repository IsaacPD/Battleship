import javax.swing.*;
public class Battleship
{
    public static void main(String[] args)
    {
        try {
            // Set cross-platform Java L&F (also called "Metal")
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } 
        catch (Exception e) {}
        Game game;
        int opt = JOptionPane.showConfirmDialog(null,"Would you like to play a click enabled version of the game?");

        if(opt == 0){
            game = new Test();
            game.play();
        }
        else if(opt == 1){
            game = new GUIGame();
            game.play();
        }
    }
}