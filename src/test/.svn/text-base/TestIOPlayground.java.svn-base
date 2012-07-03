package test;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import playground.Map;
import playground.NPC;
import playground.Quest;

import repository.IOPlayground;

/**
 * TestIOPlayground.java
 * 
 * @author Soeren Kroell, Stephanie Scholl, Mario Sigel, Ersin Yilmiz
 */
public class TestIOPlayground {

	IOPlayground io = new IOPlayground();
	
	@Test
	public void test() {
		io.setMapPath("maps/map2/map.dat");

		HashMap<Integer, Quest> quests = io.readQuestFile();
		NPC[] creatures = io.readNPCFile();
		Map map = io.readMapFile(quests);
		
		assertTrue(quests.size()>0);
		assertTrue(creatures.length>0);
		assertTrue(map!=null);
	}

}
