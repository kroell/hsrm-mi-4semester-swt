package businesslogic;

import java.io.File;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import javazoom.jlgui.basicplayer.BasicPlayerListener;

/**
 * MusicPlayer.java
 * 
 * 
 * Klasse zum Oeffnen, Abspielen, Loopen und Stoppen des MainTheme
 * 
 * @author Soeren Kroell, Stephanie Scholl, Mario Sigel, Ersin Yilmiz
 *
 */
public class MusicPlayer implements BasicPlayerListener{
    
    private BasicPlayer musicPlayer;
    private int basicPlayerStopped = 8;
    private String mainThemePath = "src/repository/music/MainTheme.mp3";
    
    public MusicPlayer(BasicPlayer myPlayer){
        this.musicPlayer = myPlayer;
        myPlayer.addBasicPlayerListener(this);
    }
    
    /**
     * Oeffnen und Abspielen des MainTheme
     */
    public void playMainTheme() throws BasicPlayerException{
        try{
            musicPlayer.open(new File (mainThemePath));
            musicPlayer.play();      
        }
        catch (Exception ex){
            System.out.print("FEHLER BEIM ABSPIELEN DES MAIN THEME!\n");
        }            
    }
        
    /**
     * MusicPlayer stoppen
     */
    public void stop() throws BasicPlayerException{
        musicPlayer.stop();
    }
    

    @Override
    public void opened(Object o, Map map) {
    }

    @Override
    public void progress(int i, long l, byte[] bytes, Map map) {
    }

    @Override
    /**
     * Loopen des MainTheme
     */
    public void stateUpdated(BasicPlayerEvent bpe) {
        
    	// Wenn Player gestoppt hat, MainTheme erneut abspielen
        if (bpe.getCode() == basicPlayerStopped){
            try {
                playMainTheme();
            } catch (BasicPlayerException ex) {
                Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }     
        }
    }

    @Override
    public void setController(BasicController bc) {
    }   
}