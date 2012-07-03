package repository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import playground.Area;
import playground.HostileNPC;
import playground.Item;
import playground.NPC;
import playground.PleasantNPC;
import playground.Quest;
import playground.Map;
import playground.Wall;

/**
 * IOPlayground.java
 * 
 * Klasse zum Einlesen der Dateien map.dat, quest.dat und npc.dat. 
 * 
 * @author Soeren Kroell, Stephanie Scholl, Mario Sigel, Ersin Yilmaz
 */
public class IOPlayground {

	private String mapPath = "maps/map1/map.dat";
	private String npcPath = "maps/map1/npc.dat";
	private String questPath = "maps/map1/quest.dat";
	
	public IOPlayground(){
	}
	
	
	/**
	 * Parst eine Datei (quests.dat) und erstellt daraus entsprechende Quests-Objekte
	 * 
	 * @return allQuests Eine Liste mit allen gefuellten Quests
	 * */
	public HashMap<Integer, Quest>  readQuestFile(){

		HashMap<Integer, String> quests = null;
		HashMap<Integer, Quest> allQuests = new HashMap<Integer, Quest>();

		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(questPath));
			String line = null;
			
			int cId = 0;
			int cSem = 0;
			String cName = "";

			try {
				while((line = bufferedReader.readLine()) != null ){
					String[] qs = line.split(" ", 2);
					String[] idArray = qs[0].split(":");
					
					if(idArray.length==2){
						cId = Integer.parseInt(idArray[0]);
						cSem = Integer.parseInt(idArray[1]);
						cName = qs[1];
						quests = new HashMap<Integer, String>();
					}else if(idArray.length==3){
						quests.put(Integer.parseInt(idArray[2]), qs[1]) ;
					}
					
					if(qs[0].equals("-")){
						allQuests.put(cId, new Quest(cId, cSem, cName, quests));
					}
				}
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return allQuests;
		
	}
	
	/**
	 * Parst eine Datei (maps.dat) und erstellt daraus ein Map-Objekt
	 * 
	 * @param quests) Ein Quest-Objekt, die einer Map zugeordnet werden
	 * @return Ein gefuelltes Map-Objekt 
	 * */
	public Map readMapFile(HashMap<Integer, Quest> quests){
		
		Map map = new Map();
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(mapPath));
			String line = null;
			Wall wf = null;
			Wall wb = null;
			Wall wr = null;
			Wall wl = null;
			Wall wfloor = null;
			Wall wsky = null;	
			Quest quest = null;
			Item item = null;
				
			try {
				while((line = bufferedReader.readLine()) != null ){
					String[] lineSplit = line.split("%");
					
					if(lineSplit[0].equals("wf")){
						wf = new Wall((lineSplit[1].equals("null")) ? null : lineSplit[1], (lineSplit[2].equals("false")) ? false : true, (lineSplit[3].equals("false")) ? false : true);
					}
					if(lineSplit[0].equals("wb")){
						wb = new Wall((lineSplit[1].equals("null")) ? null : lineSplit[1], (lineSplit[2].equals("false")) ? false : true, (lineSplit[3].equals("false")) ? false : true);
					}					
					if(lineSplit[0].equals("wr")){
						wr = new Wall((lineSplit[1].equals("null")) ? null : lineSplit[1], (lineSplit[2].equals("false")) ? false : true, (lineSplit[3].equals("false")) ? false : true);
					}					
					if(lineSplit[0].equals("wl")){
						wl = new Wall((lineSplit[1].equals("null")) ? null : lineSplit[1], (lineSplit[2].equals("false")) ? false : true, (lineSplit[3].equals("false")) ? false : true);
					}					
					if(lineSplit[0].equals("wfloor")){
						wfloor = new Wall((lineSplit[1].equals("null")) ? null : lineSplit[1], (lineSplit[2].equals("false")) ? false : true, (lineSplit[3].equals("false")) ? false : true);
					}					
					if(lineSplit[0].equals("wsky")){
						wsky = new Wall((lineSplit[1].equals("null")) ? null : lineSplit[1], (lineSplit[2].equals("false")) ? false : true, (lineSplit[3].equals("false")) ? false : true);
					}
					if(lineSplit[0].equals("quest")){
						quest = (lineSplit[1].equals("null")) ? null : quests.get(Integer.parseInt(lineSplit[1]));
					}
					if(lineSplit[0].equals("item")){
						if(lineSplit.length==4){
							item = new Item(lineSplit[1], lineSplit[2], Float.parseFloat(lineSplit[3]));
						}else{
							item = null;
						}
					}
					if(lineSplit[0].equals("map")){
						Area area = new Area(wf, wb, wr, wl, wfloor, wsky, quest, item);
						map.setArea(area, Integer.parseInt(lineSplit[1]), Integer.parseInt(lineSplit[2]), Integer.parseInt(lineSplit[3]));
					}
				}
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
		
		return map;
		
	}
	
	/**
	 * Parst eine Datei (npc.dat) und erstellt daraus entsprechende NPC-Objekte
	 * 
	 * @return npc Ein gefuelltes NPC
	 * 
	 * */
	public NPC[] readNPCFile (){
		
		NPC[] npcs = new NPC[100];
		
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(npcPath));
			String line = null;
			int counter = 0;

			try {
				while((line = bufferedReader.readLine()) != null ){
				
					NPC newNPC = null;
					String[] ns = line.split("%");
					if(ns.length==5){
						if(ns[0].equals("H")){
							newNPC = new HostileNPC(Integer.parseInt(ns[1]), Integer.parseInt(ns[2]), Float.parseFloat(ns[3]), ns[4]);
						}else if(ns[0].equals("P")){
							newNPC = new PleasantNPC(Integer.parseInt(ns[1]), Integer.parseInt(ns[2]), Float.parseFloat(ns[3]), ns[4]);
						}
					}
					if(newNPC!=null){
						npcs[counter++] = newNPC;
					}					
				}
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
		return npcs;
	}
	
	public String getMapPath(){
		return mapPath;
	}
	
	/**
	 * Beim Laden einer neuen Map wird automatisch die npc.dat und quest.dat
	 * des gleichen Verzeichnis geladen
	 * @param newPath
	 */
	public void setMapPath(String newPath){
		this.mapPath = newPath;
		npcPath = newPath.replaceAll("map.dat", "npc.dat");
		questPath = newPath.replaceAll("map.dat", "quest.dat");
	}

}
