/**
 * Class containing all the methods to play the audio file indicate a hit, sink, victory, and the song played during the game
 * 
 * @author (Isaac Duarte) 
 * @version (6/3/15)
 */
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Audio {
    Clip song;
    String path = "../Eclipsified/src/Sounds/";
    int count;
    public Audio(){
        try{
            song = AudioSystem.getClip();
            AudioInputStream battle = AudioSystem.getAudioInputStream(new File(path+"elevator.wav").getAbsoluteFile());
            //song.open(battle);
        } catch(Exception ex){System.out.println("Error with playing sound");}
    }

    public static void main(String[] args) {
        Audio a = new Audio();
        a.playHit();
        a.playFanfare();
        a.playSong();
        a.stopSong();
        a.playSunk();
    }

    public void playHit() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path+"damage.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    public void playFanfare(){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path+"fanfare.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    public void playSunk() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path+"sunk.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    public void playSong(){
        try{
            AudioInputStream battle = AudioSystem.getAudioInputStream(new File(path+"elevator.wav").getAbsoluteFile());
            if(count == 0){
                song.open(battle);
                count++;
            }
            song.loop(30);
        }
        catch(Exception ex){}
    } 

    public void stopSong(){
        song.stop();
        song.setFramePosition(0);
    }
}