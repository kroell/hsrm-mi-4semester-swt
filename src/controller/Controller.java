package controller;

import java.awt.CardLayout;
import java.util.Observable;
import java.util.Observer;

import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import playground.MapBuilder;
import userInterface.MainFrame;
import userInterface.WorldCanvas;
import userInterface.WorldModel;
import businesslogic.GameEngine;
import businesslogic.Hero;
import businesslogic.MusicPlayer;

import com.sun.j3d.utils.universe.SimpleUniverse;

/**
 * Controller.java
 * 
 * The Fooog - Und der ist 30 tief (Adventure Game)
 * 
 * ENTWICKLER KOMMENTAR:
 * Dieses Spiel wurde unter Linux entwickelt und somit nicht an das
 * Lock and Feel anderer Betriebssysteme angepasst.
 * Volle Funktionsfaehigkeit und Aussehen ist daher nur unter Linux
 * 32-Bit sichergestellt!
 * 
 * 
 * The Fooog ist ein Adventure Spiel auf Basis von Java3D welches
 * im Rahmen des Modul Softwaretechnik des 4. Semester an der 
 * Hochschule Rhein-Main entwickelt wurde.
 * 
 * Ziel des Spiels ist es einen Bachelor Abschluss zu erlangen. 
 * Dies ist moeglich in dem man sich durch 6 Semester 'kaempft'
 * indem man Quests (Fragen beantwortet, Aufgaben erfuellen, etc)
 * erfuellt und damit jeweils ins naechste Semester aufsteigen 
 * kann.
 * 
 * Das Spiel beinhaltet folgende Mainfeatures:
 * - Hauptmenu mit Einstellungsmoeglichkeiten der Tastaturbelegung,
 *   eingabe des Spielernamen, erstellen eines Avatarfotos mittels
 *   WebCam oder einladen des Fotos vom lokalen System sowie starten
 *   des Spiels
 * - Einladen einer neuen Map im Einstellungsscreen
 * - Realisierung der GameGrafik mittels Java3D API
 * - Bewegen innerhalb der Spielwelt mittels Tastatur
 * - Interagieren mit Spielfiguren
 * - Aufnehmen/Ablegen/Verwenden von Gegenstaenden
 * - Transportieren von Gegenstaenden in einem Rucksack
 * - Schiessen
 * - Oeffnen des EinstellungsScreens und aendern der Tastatur-
 *   einstellungen im laufenden Spiel
 * - Abspielen von Musik zum unterstuetzen des SpielAmbiente
 *  
 * 
 * @author Soeren Kroell, Stephanie Scholl, Mario Sigel, Ersin Yilmiz
 * @version 27.06.2012
 * @music MainTheme: Time von Hans Zimmer (hanszimmer.com)
 *
 */

public class Controller implements Observer{

	private static final int CANVAS3D_WIDTH = 1290;
	private static final int CANVAS3D_HEIGHT = 610;
	private static WorldCanvas c3d;
	private static MainFrame frame;
	private static GameEngine gameEngine;
	private static BasicPlayer basicPlayer = new BasicPlayer();
    private static MusicPlayer themePlayer = new MusicPlayer(basicPlayer);

    /**
     * Erzeugen aller wichtigen Spielelemente sowie des HauptFrames
     */
	public Controller(){

		Hero hero = new Hero("TheHero", null);
		hero.addObserver(this);
		MapBuilder mapBuilder = new MapBuilder();

		gameEngine = new GameEngine(mapBuilder, hero);
		c3d = new WorldCanvas(SimpleUniverse.getPreferredConfiguration(), gameEngine);
		c3d.setSize(CANVAS3D_WIDTH, CANVAS3D_HEIGHT);
		
		try {
			frame = new MainFrame(gameEngine, c3d, this);
		} catch (BasicPlayerException e) {
			System.out.println("Fehler beim Start!");
		}
		
		WorldModel model = new WorldModel(c3d, frame, gameEngine, mapBuilder);
		c3d.setModel(model);
	}
	
	/**
	 * Zuruecksetzen der Spieleinstellungen, wenn das Spiel im laufenden Prozess beendet und
	 * somit neu gestartert wird
	 */
	public void reset(){
		Hero hero = new Hero("TheHero", null);
		hero.addObserver(this);		
		MapBuilder mapBuilder = new MapBuilder();
		
		gameEngine = new GameEngine(mapBuilder, hero);
		c3d = new WorldCanvas(SimpleUniverse.getPreferredConfiguration(),gameEngine);
		c3d.setSize(CANVAS3D_WIDTH, CANVAS3D_HEIGHT);
		
		WorldModel model = new WorldModel(c3d, frame, gameEngine, mapBuilder);
		c3d.setModel(model);
		
		frame.reset(gameEngine, c3d);
	}
	
	/**
	 * Neustart des Spiels, wenn im Einstellungsscreen eine neue Map geladen wurde
	 */
	public void reset(String newMapPath){
		System.out.println("Bin drin!\n");
		Hero hero = new Hero("TheHero", null);
		hero.addObserver(this);		
		MapBuilder mapBuilder = new MapBuilder(newMapPath);
		
		gameEngine = new GameEngine(mapBuilder, hero);
		c3d = new WorldCanvas(SimpleUniverse.getPreferredConfiguration(),gameEngine);
		c3d.setSize(CANVAS3D_WIDTH, CANVAS3D_HEIGHT);
		
		WorldModel model = new WorldModel(c3d, frame, gameEngine, mapBuilder);
		c3d.setModel(model);
		
		frame.reset(gameEngine, c3d);
	}

	/**
	 * Controller erzeugen und Frame sichtbar machen
	 * 
	 * @param args
	 */
	public static void main(String args[]) {

		Controller c = new Controller();

		try {
			// Hintergrundmusik starten
			themePlayer.playMainTheme();
			// Frame sichtbar schalten und Groesse festlegen
			frame.setSize(1280, 840);
			frame.setVisible(true);
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(Observable obj, Object arg) {
		if(arg instanceof String){
			// wenn alle 6 Semester erreicht wurden ist das Spiel vorbei und das MainMenu wieder gestartet
			if("Spielende".equals((String)arg)){
				
		    	reset();
				
				CardLayout cL = (CardLayout) frame.getContentPane().getLayout();
				cL.show(frame.getContentPane(), "startCard");
			}
		}	
	}
}
