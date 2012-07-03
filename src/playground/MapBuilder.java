package playground;

import java.util.HashMap;

import repository.IOPlayground;

/**
 * MapBuiler.java
 * 
 * Klasse zum Erstellen eines MapBuilders und somit zum Erstellen von Maps fuer das Spiel
 * 
 */
public class MapBuilder {
	
	private Map map=new Map();
	private NPC[] creatures= new NPC[100];
	private IOPlayground io = new IOPlayground();
	private String newMapPath;
	
	public MapBuilder(){
		HashMap<Integer, Quest> quests = io.readQuestFile();
		creatures = io.readNPCFile();
		map = io.readMapFile(quests);
	}
	
	/**
	 * Konstruktor beim Neustart eines Spiels mit einer neuen Map
	 * @param newMapPath
	 */
	public MapBuilder(String newMapPath){
		io.setMapPath(newMapPath);
		HashMap<Integer, Quest> quests = io.readQuestFile();
		creatures = io.readNPCFile();
		map = io.readMapFile(quests);
	}
	
	public Map getMap(){
		return map;
	}
	public NPC[] getNPCs(){
		return creatures;
	}
	
	public IOPlayground getIO(){
		return io;
	}
}
