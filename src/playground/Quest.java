package playground;

import java.util.HashMap;

/**
 * Quest.java
 * 
 * Klasse zum Erstellen eines einzelnen Quests
 * 
 * @author Soeren Kroell, Stephanie Scholl, Mario Sigel, Ersin Yilmiz
 */
public class Quest {

	private int questID;
	private String questName;
	private boolean eventLocked = false;
	private int semester; 
	private HashMap<Integer, String> actions;
	
	public Quest(int questID, int semester, String questName, HashMap<Integer, String> actions){
		this.questID = questID;
		this.questName = questName;
		this.actions = actions;
		this.semester = semester;
	}
	
	public String getQuestName(){
		return questName;
	}
	
	public int getSemester(){
		return semester;
	}

	public String getAction(int id) {
		return actions.get(id);
	}

	public HashMap<Integer, String>  getActions() {
		return actions;
	}
	
	public void setEventLocked(boolean eventLocked){
		this.eventLocked = eventLocked;
	}
	
	public boolean getEventLocked(){
		return eventLocked;
	}
	
	public int getQuestID(){
		return questID;
	}
}
