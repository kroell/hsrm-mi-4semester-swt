package businesslogic;

import java.io.File;

import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

/**
 * SoundPlayer.java
 * 
 * 
 * Klasse zum Oeffnen, Abspielen, Loopen und Stoppen von Sound Files
 * 
 * @author Soeren Kroell, Stephanie Scholl, Mario Sigel, Ersin Yilmiz
 *
 */
public class SoundPlayer {
	
	private BasicPlayer soundPlayer;
	private String hoverSound = "src/repository/music/hover_sound.mp3";
	private String selectSound = "src/repository/music/select_sound.mp3";
	
	public SoundPlayer (BasicPlayer soundPlayer){
		this.soundPlayer = soundPlayer;
	}
	
	/**
	 * Oeffnen und Abspielen des HoverSounds
	 */
	public void playHoverSound() throws BasicPlayerException{
        try{
            soundPlayer.open(new File (hoverSound));
            soundPlayer.play(); 
        }
        catch (Exception ex){
            System.out.print("FEHLER BEIM ABSPIELEN DES AUDIO FILES!\n");
        }
    }
    
	/**
	 * Oeffnen und Abspielen des SelectSounds
	 */
    public void playSelectSound() throws BasicPlayerException{
        try{
        	soundPlayer.open(new File (selectSound));
        	soundPlayer.play();            
        }
        catch (Exception ex){
            System.out.print("FEHLER BEIM ABSPIELEN DES AUDIO FILES!\n");
        }
    }
    
    /**
     * SoundPlayer stoppen
     */
    public void stop() throws BasicPlayerException{
    	soundPlayer.stop();
    }

}
