package userInterface;

import businesslogic.GameEngine;
import businesslogic.Hero;
import businesslogic.SoundPlayer;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.Controller;

import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

/**
 * MainFrame.java
 * 
 * Klasse zum Erstellen und Anzeigen der Game-GUI
 * 
 * @author Soeren Kroell, Stephanie Scholl, Mario Sigel, Ersin Yilmiz
 * @version: 27.06.2012
 *
 */
@SuppressWarnings("serial")
public class MainFrame extends javax.swing.JFrame {

    private BasicPlayer soundBasicPlayer = new BasicPlayer();
    private SoundPlayer soundsPlayer = new SoundPlayer(soundBasicPlayer);
    private GameEngine gameEngine;
    private userInterface.IntroPanel introPanel1;
    private userInterface.IntroPanel_2 introPanel_21;
    private userInterface.PlayPanel playPanel1;
    private userInterface.SettingPanel settingPanel1;
    private userInterface.SettingPanelGame settingPanelGame1;
    private userInterface.StartPanel startPanel1;
    private PanelTop panelTop;
    private PanelBottom panelBottom;
    private char[] commandKeys = new char [15];
    private JPanel gamePanel;
    private Controller controller;    
   
    /**
     * Konstruktor zum Erzeugen des MainFrame inkl. festlegen der Standard-Tastaturbelegung
     * 
     * @param gameEngine
     * @param c3d
     * @param controller
     * @throws BasicPlayerException
     */
    public MainFrame (GameEngine gameEngine, WorldCanvas c3d, Controller controller) throws BasicPlayerException{
    	initComponents(gameEngine, c3d);
    	this.controller = controller;
    	
    	commandKeys[0] = 'd';
    	commandKeys[1] = 'a';    	
    	commandKeys[2] = 'w';    	
    	commandKeys[3] = 's';
    	commandKeys[4] = ' ';
    	commandKeys[5] = 'i';
    	commandKeys[6] = 'e';    	
    	commandKeys[7] = 'r';    	
    	commandKeys[8] = 't';
    	commandKeys[9] = 'f';  
    	commandKeys[10] = '1';
    	commandKeys[11] = '2';    	
    	commandKeys[12] = '3';    	
    	commandKeys[13] = 'q';
    	commandKeys[14] = 'p';  
    }
    
    public void setCommandKeys(char[] commandKeys){
    	this.commandKeys = commandKeys;
    }
    
    public char[] getCommandKeys(){
    	return commandKeys;
    }
    
    /**
     * Abspielen des HoverSounds, wenn man mit der Maus ueber einen 
     * JButton faehrt
     * 
     * @throws BasicPlayerException
     */
    public void playHoverSound() throws BasicPlayerException{
        soundsPlayer.playHoverSound();
    }

    /**
     * Abspielen des SelectSounds, wenn ein JButton mit der Maus
     * geklickt wird
     * 
     * @throws BasicPlayerException
     */
    public void playSelectSound() throws BasicPlayerException{
        soundsPlayer.playSelectSound();
    }
    
    public void stop() throws BasicPlayerException{
        soundsPlayer.stop();
    }
    
    /**
     * Hauptkomponenten initialisieren
     * 
     * @param gameEngine
     * @param c3d
     */
    private void initComponents(GameEngine gameEngine, final WorldCanvas c3d) {

    	this.gameEngine = gameEngine;
    	gamePanel = initGamePanels(gameEngine, c3d);
    	
    	// MAIN MENU Panels initialisieren
    	settingPanel1 = new userInterface.SettingPanel(this);
        startPanel1 = new userInterface.StartPanel(this, settingPanel1);
        settingPanelGame1 = new userInterface.SettingPanelGame(this);
        introPanel1 = new userInterface.IntroPanel(this, c3d, gamePanel);
        introPanel_21 = new userInterface.IntroPanel_2();
        playPanel1 = new userInterface.PlayPanel(this,introPanel1);
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("THE FOOOG");
        setName("mainFrame"); 
        
        // Panels zu CardLayout hinzufuegen
        getContentPane().setLayout(new java.awt.CardLayout());
        getContentPane().add(startPanel1, "startCard");
        getContentPane().add(settingPanel1, "settingCard");
        getContentPane().add(settingPanelGame1, "settingGameCard");
        getContentPane().add(introPanel1, "introCard");
        getContentPane().add(introPanel_21, "introCard2");
        getContentPane().add(playPanel1, "playCard");
        getContentPane().add(gamePanel, "gameCard");

        pack();
    }

    /**
     * Panels erzeugen um die Spielwelt zu initialisieren
     * 
     * @param gameEngine
     * @param c3d
     * @return gamePanel
     */
    private JPanel initGamePanels(GameEngine gameEngine, final WorldCanvas c3d){
    	 
		JPanel gamePanel = new JPanel();
		gamePanel.setBackground(new Color(80, 100, 140));
		
		// Beinhaltet die eigentlich Interaktionsflaeche
		JPanel c3dPanel = new JPanel();
		c3dPanel.add(c3d);
		c3dPanel.setBounds(0, 90, 1290, 610);
		
		gamePanel.setLayout(null);
		gamePanel.add(c3dPanel);		

		// PanelTop beinhaltet Spielerbild, -Name, Semester- und Punktestand
		// sowie aktuell offene Quests
		panelTop = new PanelTop(gameEngine);
		panelTop.setBounds(0, 0, 1280, 100);
		gamePanel.add(panelTop);

		// PanelBottom beinhaltet das MessageDialogFenster sowie die Anzeige
		// des Rucksacks
		panelBottom = new PanelBottom(gameEngine);
		panelBottom.setBounds(0, 700, 1280, 100);
		gamePanel.add(panelBottom);

		// Hinzufuegen des KeyListeners
		c3d.addKeyListener(new MyKeyListener());
        gamePanel.setFocusable(true);
        c3d.setFocusable(true);
        
        return gamePanel;
    }
    
    /**
     * Neu Initialisieren des Spiels wenn es bereits ausgefuehrt und
     * beendet wurde
     * 
     * @param gameEngine
     * @param c3d
     */
    public void reset(GameEngine gameEngine, final WorldCanvas c3d){
    	System.out.println("RESET\n");
    	this.gameEngine = gameEngine;
    	
    	//introPanel1 = new userInterface.IntroPanel(this, c3d, gamePanel);
        //introPanel_21 = new userInterface.IntroPanel_2();
    	
    	getContentPane().add(introPanel1, "introCard");
        getContentPane().add(introPanel_21, "introCard2");
        
    	gamePanel = initGamePanels(gameEngine, c3d);
        getContentPane().add(gamePanel, "gameCard");   
    }
    
    /**
     * Interne KeyListener Klasse zum Ansteuern der entsprechenden
     * Funktion wenn waehrend des Spiels eine Taste gedrueckt wird
     *
     */
    private class MyKeyListener implements KeyListener{
		@Override
		public void keyPressed(KeyEvent e) {
			char cChar = e.getKeyChar();
			
			if(cChar==commandKeys[0])
				gameEngine.rotate(1);
			else if(cChar==commandKeys[1])
				gameEngine.rotate(-1);
			else if(cChar==commandKeys[2])
				gameEngine.move(1);
			else if(cChar==commandKeys[3])
				gameEngine.move(-1);
			else if(cChar==commandKeys[4])
				gameEngine.shoot();
			else if(cChar==commandKeys[5])
				gameEngine.interact();		
			else if(cChar==commandKeys[6])
				gameEngine.itemToBackpack();		
			else if(cChar==commandKeys[7])
				gameEngine.accessBackpack();	
			else if(cChar==commandKeys[8])
				gameEngine.dropItem();		
			else if(cChar==commandKeys[9])
				gameEngine.nextItem();	
			else if(cChar==commandKeys[10])
				gameEngine.reply(1);	
			else if(cChar==commandKeys[11])
				gameEngine.reply(2);	
			else if(cChar==commandKeys[12])
				gameEngine.reply(3);
			else if(cChar==commandKeys[13])
				showMainMenu();
			else if(cChar==commandKeys[14])
				showSettingGamePanel();		
		}
		@Override
		public void keyReleased(KeyEvent e) {
			char cChar = e.getKeyChar();

			if(cChar==commandKeys[0])
				gameEngine.setRotating(false);
			else if(cChar==commandKeys[1])
				gameEngine.setRotating(false);
			else if(cChar==commandKeys[2])
				gameEngine.setMoving(false);
			else if(cChar==commandKeys[3])
				gameEngine.setMoving(false);
		}
		@Override
		public void keyTyped(KeyEvent e) {
		}
	}
    
    public Hero getHero(){
    	return gameEngine.getHero();
    }
    
    public PanelTop getPanelTop(){
    	return panelTop;
    }
    
    public PanelBottom getPanelBottom(){
    	return panelBottom;
    }
    
    /**
     * Zum Anzeigen des MainMenu aus dem Spiel heraus mit vorheriger Abfrage ob das Spiel
     * wirklich beendet werden soll.    
     */
    private void showMainMenu(){
    	
    	int stop = 0;
    	int answer = 0;
    	Object[] options = {"Ja, kein Nerv mehr", "Nein, war ein Fehler"};
    	
    	// Dialog ob das Spiel wirklich beendet werden soll
    	answer = JOptionPane.showOptionDialog(this, "Willst du das Spiel wirklich beenden?", "Dein Ernst?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon("src/repository/images/icon.png"), options, options[1]);
    	
    	if (answer == stop){
	    	controller.reset();
	    	
	    	CardLayout cL = (CardLayout) this.getContentPane().getLayout();
	    	cL.show(this.getContentPane(), "startCard");
    	}
    }
    
    /**
     * Zum Anzeigen des SettingGamePanel mit den Spieleinstellungen waehrend des 
     * laufenden Spiels
     */
    private void showSettingGamePanel(){
    	settingPanelGame1.makeEntries();
    	CardLayout cL = (CardLayout) this.getContentPane().getLayout();
    	cL.show(this.getContentPane(), "settingGameCard");
    }
    
    public GameEngine getGameEngine(){
    	return gameEngine;
    }
   
    public Controller getController(){
    	return controller;
    }
    
}
