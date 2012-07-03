package test;

import static org.junit.Assert.*;

import java.beans.*;

import org.junit.Test;

import playground.MapBuilder;
import businesslogic.GameEngine;
import businesslogic.Hero;
import businesslogic.QuestHandler;


/**
 * TestQuestHandler.java
 * 
 * @author Soeren Kroell, Stephanie Scholl, Mario Sigel, Ersin Yilmiz
 */
public class TestQuestHandler {

	Hero hero = new Hero("TheHero", null);

	MapBuilder mapBuilder = new MapBuilder();

	GameEngine gameEngine = new GameEngine(mapBuilder, hero);

	QuestHandler qh = new QuestHandler(gameEngine.getMap()
			.getAreaByCoordinates(-2, 12).getQuest(), gameEngine);

	String infoString = "";

	@Test
	public void NextActionByDialogTest() {

		qh.addPropertyChangeListener("infoString",
				new PropertyChangeListener() {
					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						if (evt.getPropertyName()
								.equalsIgnoreCase("infoString")) {
							infoString = (String) evt.getNewValue();
						}
					}
				});
		
		qh.start();
		
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		assertEquals("Hallo. §1: Quest starten §2: Bloedes Wetter heute, ich muss gehen!", infoString);
		
		qh.getNextActionByDialog(1);
		
		try {
			Thread.sleep(160);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		assertEquals("Geek hat ein Vorstellungsgespraech. Als er am Dienstagmorgen nach einer durchzechten Nacht aufwacht, sieht er, §dass die Cola neben dem Bett umgefallen und ueber den Rechner geflossen ist. Somit kann er leider nicht auf seine §Termine zugreifen. Er kann sich allerdings erinnern, dass sein Termin zwei Tage nach dem Tag vor §dem Tag nach morgen ist. §1: Freitag  2:Donnerstag  3: Raus hier", infoString);		
		
		qh.getNextActionByDialog(2);
		
		try {
			Thread.sleep(160);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals("Das war die falsche Antwort. versuch es nochmal. ", infoString);					

	}
}
