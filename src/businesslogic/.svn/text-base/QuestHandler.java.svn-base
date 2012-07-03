package businesslogic;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Observable;

import playground.HostileNPC;
import playground.Item;
import playground.NPC;
import playground.Quest;
import playground.Area;

import java.util.HashMap;

/**
 * QuestHandler.java
 * 
 * Klasse zur Abwicklung der Dialoge und Aktionen eines Quests. 
 * 
 * @author Soeren Kroell, Stephanie Scholl, Mario Sigel, Ersin Yilmiz
 */
public class QuestHandler extends Observable{

	private Quest quest;
	private int currentID;
	private final PropertyChangeSupport propertyChangeSupport;
	private String infoString = "";
	private boolean dialogBlocked = false;
	private GameEngine gameEngine; 
	private HashMap<String, Boolean> itemsToFind = new HashMap<String, Boolean>(); 
	private Area areaToGo;

	private int jawasToShoot=0;
	private int shootedJawas=0;
	private boolean shootRunning=false;
	private boolean shootBarth=false;
	
	public QuestHandler(Quest quest, GameEngine gameEngine){
		this.gameEngine = gameEngine;
		propertyChangeSupport = new PropertyChangeSupport(this);
		this.quest = quest;
		quest.setEventLocked(true);
		this.currentID = 1;
		
		this.infoString = quest.getAction(1).split("#")[0];

	}
	
	public Quest getQuest(){
		return quest;
	}
	
	public void start(){
		//System.out.println("start");
		propertyChangeSupport.firePropertyChange("infoString", "neue Quest", infoString);
		gameEngine.setInDialog(true);
	}

	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}
	
	/**
	 * Methode setzt ID der Dialogzeile je nach dem Wert von num weiter.
	 * num ist 1, 2 oder 3. Besondere Aktionen am Ende des Dialogs (nach '#') werden 
	 * hier ausgewertet.  
	 * 
	 * 
	 * @param num
	 * @return
	 */
	public boolean getNextActionByDialog(int num){
		
		if(!dialogBlocked){
			dialogBlocked = true;
			
			new Thread(){
				
				@Override
				public void run() {
					try {
						Thread.sleep(150);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					dialogBlocked = false;
				}
			}.start();
			
			int tmpID = currentID*10+num;
			
			String tmpString = quest.getAction(tmpID);
			
			
			if(tmpString!=null){
				currentID = tmpID;

				String[] tmpArray = tmpString.split("#");
				infoString = tmpArray[0];

				if(tmpArray.length>1){
					String[] commandArray = tmpArray[1].split("%");

					if (commandArray[0].equals("END")){		//    #END%10
						finalizeQuest(Integer.parseInt(commandArray[1]));
						propertyChangeSupport.firePropertyChange("infoString", "end", infoString);
						
						int points = Integer.parseInt(commandArray[1]);
						gameEngine.getHero().addPoints(points);
						
						return true;
					}else if(commandArray[0].equals("ITEM")){			//     #ITEM%Block/Stift
						
						//System.out.println("hierITEM");
						
						String[] itemsArray = commandArray[1].split("/");
						for (String item : itemsArray){
							itemsToFind.put(item, false);

						}

						propertyChangeSupport.firePropertyChange("infoString", "end", infoString);
						
						final QuestHandler tmp = this;
						
						new Thread() {
							
							@Override
							public void run() {
								try {
									Thread.sleep(160);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								gameEngine.setInDialog(false);
								dialogBlocked=true; 
							}
						}.start();						
						
						
						new Thread() {
							
							@Override
							public void run() {
								try {
									Thread.sleep(1500);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								gameEngine.checkBackpack(tmp);
							}
						}.start();
						
						
					}else if(commandArray[0].equals("AREA")){		//	#AREA%0/0/0
						String[] locationArray = commandArray[1].split("/");
						
						//System.out.println("hierAREA");
						
						areaToGo = gameEngine.getMap().getAreaByIndices(Integer.parseInt(locationArray[0]), Integer.parseInt(locationArray[1]), Integer.parseInt(locationArray[2]));
	
						propertyChangeSupport.firePropertyChange("infoString", "end", infoString);
						gameEngine.setInDialog(false);		
						
						new Thread() {
							
							@Override
							public void run() {
								try {
									Thread.sleep(1500);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
											
								dialogBlocked=true;
								getNextActionByEnteringArea(gameEngine.getCurArea());
							}
						}.start();
 					
					
					}else if(commandArray[0].equals("SHOOT")){			//      #SHOOT%20/2			in 20s 2 Jawas
						String[] dataArray = commandArray[1].split("/");
						final int shootTime = Integer.parseInt(dataArray[0]);
						jawasToShoot = Integer.parseInt(dataArray[1]);
						shootedJawas = 0;
						
						gameEngine.wakeJawas();
						
						propertyChangeSupport.firePropertyChange("infoString", "end", infoString);
						
						new Thread() {
							
							@Override
							public void run() {
								try {
									Thread.sleep(160);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								dialogBlocked=true; 
							}
						}.start();
						
						gameEngine.setInDialog(false);
						
						shootRunning = true;

						Thread threadJawaTime = new Thread(){
							@Override
							public void run() {
								long time = System.currentTimeMillis() ;
								while(System.currentTimeMillis()-time<shootTime*1000 && shootRunning){
									try {
										Thread.sleep(100);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
									//System.out.println((System.currentTimeMillis()-time)/1000);
								}
								if(shootRunning){
									jawasToShoot = -1;
									dialogBlocked = false;
									gameEngine.setInDialog(true);
									getNextActionByDialog(2);
								}
							}							
						};
						threadJawaTime.start();

						
					}else if(commandArray[0].equals("BARTH")){		//	#BARTH
					
						NPC[] npcs = gameEngine.getNpcs();
						NPC[] npcsNeu = new NPC[100];
						for (int i=0; i<npcs.length; i++){
							if(npcs[i]!=null){
								
								if(npcs[i].getFile().equals("image/bowser.png")){
									
									NPC barth = npcs[i];
									HostileNPC newBarth = new HostileNPC(barth.getPosX(), barth.getPosZ(), barth.getRotation(), barth.getFile());
									newBarth.setAlpha(barth.getAlpha());
									newBarth.setAlphaMapObject(barth.getAlphaMapObject());
									newBarth.setPositionPathInterpolator(barth.getPositionPathInterpolator());
									newBarth.setRotator(barth.getRotator());	
									npcs[i] = newBarth;
									npcsNeu[i] = newBarth;
								}
							}	
						}
						gameEngine.addNPCtoObserver(npcsNeu);
						propertyChangeSupport.firePropertyChange("infoString", "end", infoString);

						new Thread() {
							
							@Override
							public void run() {
								try {
									Thread.sleep(160);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								dialogBlocked=true; 
								gameEngine.setInDialog(false);		
								shootBarth = true;
							}
						}.start();	
				
					}else{
					
						propertyChangeSupport.firePropertyChange("infoString", " ", infoString);
					}
				}else{
					propertyChangeSupport.firePropertyChange("infoString", " ", infoString);
				}
			}
		}
		

		
		return false;
	}
	
	/**
	 * Wenn aufgenommenes Item zur Quest passt, dann reagieren. 
	 * 
	 * @param item
	 * @return
	 */
	public boolean getNextActionByCollectingItem(Item item){
		//System.out.println(item.getName());
		boolean foundAll = true;
		if(dialogBlocked){
			for (String itemString : itemsToFind.keySet()){
				//System.out.println(itemString);
				if(item.getName().equals(itemString)){
					itemsToFind.put(itemString, true);
				}
				if(foundAll){
					foundAll=itemsToFind.get(itemString);
				}
			}
			if(foundAll){
				dialogBlocked = false;
				gameEngine.setInDialog(true);
				itemsToFind.clear();
				boolean fertig = getNextActionByDialog(1);
				if (fertig){
					finalizeQuest(1000);
					return true;	
				}
			}
		}
		return false;
	}	


	/**
	 * Wenn betretene Area erreicht werden soll, dann reagiere.  
	 * 
	 * @param area
	 * @return
	 */
	public boolean getNextActionByEnteringArea(Area area){
		if (area.equals(areaToGo)){
			dialogBlocked = false;
			gameEngine.setInDialog(true);
			areaToGo=null;
			boolean fertig = getNextActionByDialog(1);
			if (fertig){
				finalizeQuest(1000);
				return true;	
			}
		}
		return false;
	}	
	
	/**
	 * Wenn genug Jawas besiegt dann naechste Aktion. 
	 * 
	 * @return
	 */
	public boolean getNextActionByShooting(){
		shootedJawas++;
		if(shootedJawas==jawasToShoot){
			shootRunning = false;
			dialogBlocked = false;
			gameEngine.setInDialog(true);		
			boolean fertig = getNextActionByDialog(1);
			if (fertig){
				finalizeQuest(1000);
				return true;	
			}
		}
		
	return false;	
	}
	
	/**
	 * 
	 * Finale Quest.
	 * 
	 * @return
	 */
	public boolean getNextActionByShootingBarth(){
		
		if(shootBarth){
			shootBarth = false;
			dialogBlocked = false;
			gameEngine.setInDialog(true);		
			boolean fertig = getNextActionByDialog(1);
			if (fertig){
				finalizeQuest(1000);
				return true;	
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Aufraeumen, wenn Quest abgeschlossen. 
	 * 
	 * @param cp
	 */
	private void finalizeQuest(int cp){
		quest.setEventLocked(false);
		gameEngine.setInDialog(false);
		gameEngine.delQuestHandler(this);
	}
	
}
