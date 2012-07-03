package businesslogic;

import java.util.LinkedList;
import java.util.Observable;

import javax.media.j3d.Alpha;

import playground.*;

/**
 * GameEngine.java
 * 
 * Klasse zur Verwaltung der Ablaufe in der Spielwelt, welche in erster 
 * Linie durch den KeyListener in der MainFrame-Klasse angestossen werden. 
 * Aktuelle Position und Orientierung in der Welt werden hier ermittelt, 
 * waehrend Aufgaben die Quests oder den Hero betreffend an QuestHandler 
 * oder die Hero-Klasse delegiert werden. 
 * 
 * @author Soeren Kroell, Stephanie Scholl, Mario Sigel, Ersin Yilmaz
 */
public class GameEngine extends Observable{

	private Map map;
	private NPC[] npcs;
	private float posX;
	private float posZ;
	private float orientation;
	final float DELTAPOS = 0.2f;
	final float DELTAPHI= 0.1f;	
	final int ALPHA_DURATION_TRANS = 60;
	final int ALPHA_DURATION_ROT = 120;
	final int ALPHA_DURATION_SHOOT = 1500;
	final int ALPHA_DURATION_FALLING = 400;
	final int DELAY =100;	
	private Alpha alphaTrans  = new Alpha(1 , ALPHA_DURATION_TRANS);
	private Alpha alpha = new Alpha(1, ALPHA_DURATION_ROT);
	private Alpha alphaShoot = new Alpha(1, ALPHA_DURATION_SHOOT);
	private boolean moving = false;
	private boolean rotating =false;
	private float posXSphere;
	private float posZSphere;
	private Hero hero;
	private LinkedList<QuestHandler> activeQuests = new LinkedList<QuestHandler>();
	private boolean inDialog=false;
	private Area curArea = null;
	private Thread moveThread;
	private Thread rotateThread;
	private boolean busy;
	private boolean busyRotation;
	private boolean inBackpack;
	private NPC[] npcsNeu;
	private MapBuilder mapBuilder;


	public GameEngine(MapBuilder mb, Hero hero){
		this.map = mb.getMap();
		this.npcs = mb.getNPCs();
		this.hero = hero;
		curArea = map.getAreaByIndices(0, 0, 0);
		this.mapBuilder = mb;
	}

	public MapBuilder getMapBuilder() {
		return mapBuilder;
	}
	
	public NPC[] getNpcs(){
		return npcs;
	}
	
	public LinkedList<QuestHandler> getActiveQuests(){
		return activeQuests;
	}
	
	public boolean getInBackpack(){
		return inBackpack;
	}
	
	public Hero getHero(){
		return hero;
	}
	
	public Map getMap(){
		return map;
	}
	
	public Area getCurArea(){
		return curArea;
	}
	
	public void setInDialog(boolean inDialog){
		this.inDialog = inDialog;
	}
	
	
	public float getPosX(){
		return posX;
	}
	
	public float getPosZ(){
		return posZ;
	}

	public float getPosXSphere(){
		return posXSphere;
	}
	
	public float getPosZSphere(){
		return posZSphere;
	}
	
	public float getOrientation(){
		return orientation;
	}

	public void setRotating(boolean rotating){
		this.rotating = rotating;
	}
	
	public void setMoving(boolean moving){
		this.moving = moving;
		
	}
	
	/**
	 * Berechnung der neuen Position.
	 * koeff ist 1 bei Vorwaertsbewegung, -1 rueckwaerts
	 * 
	 * @param koeff
	 */
	public void move(final int koeff){
		
		
		if(!inDialog){		
			synchronized (this) {
				
				
				if(!busy){
					busy = true;
						moveThread = new Thread() {
							@Override
							public void run() {
								moving = true;

								while (moving) {

										boolean stopX=false;
										boolean stopZ=false;
			
										if (map.getAreaByCoordinates(posX, posZ)!=null){
											if (map.getAreaByCoordinates(posX, posZ).getRight().isSolid()){
												//Stoppen an Wand rechts vorw�rts
												if(posX<0 && 0.7f < Math.abs(posX%2) && Math.abs(posX%2) < 1.0f  && koeff==1 && ((0 < orientation && orientation < Math.PI) || (-Math.PI * 2.0 < orientation && orientation < -Math.PI))){
													stopX=true;
												}
												if(posX>0 && 1.0f < Math.abs(posX%2) && Math.abs(posX%2) < 1.3f  && koeff==1 && ((0 < orientation && orientation < Math.PI) || (-Math.PI * 2.0 < orientation && orientation < -Math.PI))){
													stopX=true;
												}
												//Stoppen an Wand rechts r�ckw�rts
												if(posX<0 && 0.7f < Math.abs(posX%2) && Math.abs(posX%2) < 1.0f && koeff==-1 && !((0 < orientation && orientation < Math.PI) || (-Math.PI * 2.0 < orientation && orientation < -Math.PI))){
													stopX=true;
												}
												if(posX>0 && 1.0f < Math.abs(posX%2) && Math.abs(posX%2) < 1.3f && koeff==-1 && !((0 < orientation && orientation < Math.PI) || (-Math.PI * 2.0 < orientation && orientation < -Math.PI))){
													stopX=true;
												}
											}
										}
			
										if (map.getAreaByCoordinates(posX, posZ)!=null){
											if (map.getAreaByCoordinates(posX, posZ).getLeft().isSolid()){
												//Stoppen an Wand links vorw�rts			
												if(posX>0 && 0.7f < Math.abs(posX%2) && Math.abs(posX%2) < 1.0f  && koeff==1 && !((0 < orientation && orientation < Math.PI) || (-Math.PI * 2.0 < orientation && orientation < -Math.PI))){
													stopX=true;
												}
												if(posX<0 && 1.0f < Math.abs(posX%2) && Math.abs(posX%2) < 1.3f  && koeff==1 && !((0 < orientation && orientation < Math.PI) || (-Math.PI * 2.0 < orientation && orientation < -Math.PI))){
													stopX=true;
												}
												//Stoppen an Wand links r�ckw�rts
												if(posX>0 && 0.7f < Math.abs(posX%2) && Math.abs(posX%2) < 1.0f && koeff==-1 && ((0 < orientation && orientation < Math.PI) || (-Math.PI * 2.0 < orientation && orientation < -Math.PI))){
													stopX=true;
												}
												if(posX<0 && 1.0f < Math.abs(posX%2) && Math.abs(posX%2) < 1.3f && koeff==-1 && ((0 < orientation && orientation < Math.PI) || (-Math.PI * 2.0 < orientation && orientation < -Math.PI))){
													stopX=true;
												}
											}
										}
			
										if (map.getAreaByCoordinates(posX, posZ)!=null){
											if (map.getAreaByCoordinates(posX, posZ).getFront().isSolid()){
												//Stoppen an Wand front vorw�rts
												if(posZ>0 && 0.7f < Math.abs(posZ%2) && Math.abs(posZ%2) < 1.0f  && koeff==1 && ((-Math.PI/2 < orientation && orientation < Math.PI/2) || (Math.PI*3/2 < orientation || orientation < -Math.PI*3/2))){
													stopZ=true;
												}
												if(posZ<0 && 1.0f < Math.abs(posZ%2) && Math.abs(posZ%2) < 1.3f  && koeff==1 && ((-Math.PI/2 < orientation && orientation < Math.PI/2) || (Math.PI*3/2 < orientation || orientation < -Math.PI*3/2))){
													stopZ=true;
												}
												//Stoppen an Wand front r�ckw�rts
												if(posZ>0 && 0.7f < Math.abs(posZ%2) && Math.abs(posZ%2) < 1.0f && koeff==-1 && !((-Math.PI/2 < orientation && orientation < Math.PI/2) || (Math.PI*3/2 < orientation || orientation < -Math.PI*3/2))){
													stopZ=true;
												}
												if(posZ<0 && 1.0f < Math.abs(posZ%2) && Math.abs(posZ%2) < 1.3f && koeff==-1 && !((-Math.PI/2 < orientation && orientation < Math.PI/2) || (Math.PI*3/2 < orientation || orientation < -Math.PI*3/2))){
													stopZ=true;
												}
											}
										}
										
										if (map.getAreaByCoordinates(posX, posZ)!=null){
											if (map.getAreaByCoordinates(posX, posZ).getBack().isSolid()){
												//Stoppen an Wand back vorw�rts			
												if(posZ<0 && 0.7f < Math.abs(posZ%2) && Math.abs(posZ%2) < 1.0f  && koeff==1 && !((-Math.PI/2 < orientation && orientation < Math.PI/2) || (Math.PI*3/2 < orientation || orientation < -Math.PI*3/2))){
													stopZ=true;
												}
												if(posZ>0 && 1.0f < Math.abs(posZ%2) && Math.abs(posZ%2) < 1.3f  && koeff==1 && !((-Math.PI/2 < orientation && orientation < Math.PI/2) || (Math.PI*3/2 < orientation || orientation < -Math.PI*3/2))){
													stopZ=true;
												}
												//Stoppen an Wand back r�ckw�rts
												if(posZ<0 && 0.7f < Math.abs(posZ%2) && Math.abs(posZ%2) < 1.0f && koeff==-1 && ((-Math.PI/2 < orientation && orientation < Math.PI/2) || (Math.PI*3/2 < orientation || orientation < -Math.PI*3/2))){
													stopZ=true;
												}
												if(posZ>0 && 1.0f < Math.abs(posZ%2) && Math.abs(posZ%2) < 1.3f && koeff==-1 && ((-Math.PI/2 < orientation && orientation < Math.PI/2) || (Math.PI*3/2 < orientation || orientation < -Math.PI*3/2))){
													stopZ=true;
												}
											}
										}
										
										if(!stopX){
											posX = posX - (float)koeff*DELTAPOS*(float)Math.sin(orientation);		
										}
										if(!stopZ){
											posZ = posZ + (float)koeff*DELTAPOS*(float)Math.cos(orientation);	
										}
										if(!stopX || !stopZ){
		
											Area tmpArea = map.getAreaByCoordinates(posX, posZ);
											if(tmpArea==null){
												curArea = new Area(new Wall(null, false, false), new Wall(null, false, false), new Wall(null, false, false), new Wall(null, false, false), new Wall(null, false, false), new Wall(null, false, false), null, null);
												map.setArea(curArea, map.translateX(posX)-100, 0, map.translateZ(posZ)-100);
											}
											
											
											
											if(tmpArea!=null){
												if(!tmpArea.equals(curArea)){
													curArea = tmpArea;

													for(QuestHandler cHandler : activeQuests){
														cHandler.getNextActionByEnteringArea(curArea);
													}
									
												}
											}
												
							                setChanged();
							                notifyObservers("move");
							                
										}

										try {
											Thread.sleep(60);
										} catch (InterruptedException e) {
											e.printStackTrace();
										
									}
								}
								
								
								moveThread = null;
				                busy = false;
	
								
							}
						};
						moveThread.start();
				}
			}
			}
		
	}

	
	/**
	 * Drehung um die eigene Achse(y-Achse durch Punkt posX, posZ).
	 * koeff==1 ist Rechtsdrehung, -1 Linksdrehung. 
	 * 
	 * @param koeff
	 */
	public void rotate(final int koeff) {
		if (!inDialog) {
			
			synchronized (this) {
				if (!busyRotation) {
					busyRotation = true;
					rotateThread = new Thread() {
						@Override
						public void run() {

							rotating = true;
							while (rotating) {
								float orientationNew = orientation
										+ (float) ((float) koeff * Math.PI * DELTAPHI);

								if ((int) (orientationNew * 100)
										% (int) (Math.PI * 2.0f * 100) == 0)
									orientation = 0;
								else
									orientation = orientationNew;
								setChanged();
								notifyObservers("rotate");

								try {
									Thread.sleep(120);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}

							}
							rotateThread = null;
							busyRotation = false;
						}
					};
					rotateThread.start();
				}
			}
		}
	}
	
	/**
	 * Simuliert das Schiessen.
	 * Ein Projektil in Form einer Kugel wird ausgehend von der aktuellen Position des Spielers
	 * 'abgeschossen'. Das heisst es wird eine Bewegung in Richtung der Orientation zum Schiesszeitpunkt
	 * extrapoliert. Erreicht das Projektil eine neuen Area in der ein feindlich gesinnter NPC platziert ist, 
	 * so wird dieser erschossen (er faellt um). 
	 */
	public void shoot(){
		if(!inDialog){
			if((int)(alphaShoot.value()*100)>=DELAY){
	
				posXSphere = -posX+30.0f*(float)Math.sin(orientation);
				posZSphere = -posZ-30.0f*(float)Math.cos(orientation);
	
				final float cOrientation = orientation;
				
				setChanged();
				notifyObservers("shoot");
				
				new Thread() {
	
					@Override
					public void run() {
						alphaShoot.setStartTime(System.currentTimeMillis());
						boolean stop=false;
						while((int)(alphaShoot.value()*100)!=100){
							
							float cPosXSphere = posX-30*(float)alphaShoot.value()*(float)Math.sin(orientation);
							float cPosZSphere = posZ+30*(float)alphaShoot.value()*(float)Math.cos(orientation);
	
							if(!stop){
								for(NPC npc : npcs){
									if(npc instanceof HostileNPC){
										if(map.translateX(cPosXSphere)-100==npc.getPosX() && map.translateZ(cPosZSphere)-100==npc.getPosZ() && npc.getAlive()){
											npc.setAlive(false);
											
											if(npc.getFile().equals("image/bowser.png")){

												for(QuestHandler cHandler : activeQuests){
													cHandler.getNextActionByShootingBarth();
												}
											}
											
											if(npc.getFile().equals("image/jawa.png")){

												for(QuestHandler cHandler : activeQuests){
													cHandler.getNextActionByShooting();

												}
											}
										}
									}
								}
							}
							
							if (map.getAreaByCoordinates(cPosXSphere, cPosZSphere)!=null){
								if (map.getAreaByCoordinates(cPosXSphere, cPosZSphere).getRight().isSolid()){
									//Stoppen an Wand rechts vorw�rts
									if(cPosXSphere<0 && 0.7f < Math.abs(cPosXSphere%2) && Math.abs(cPosXSphere%2) < 1.0f  && ((0 < cOrientation && cOrientation < Math.PI) || (-Math.PI * 2.0 < cOrientation && cOrientation < -Math.PI))){
										stop=true;
									}
									if(cPosXSphere>0 && 1.0f < Math.abs(cPosXSphere%2) && Math.abs(cPosXSphere%2) < 1.3f  && ((0 < cOrientation && cOrientation < Math.PI) || (-Math.PI * 2.0 < cOrientation && cOrientation < -Math.PI))){
										stop=true;
									}
								}
							}
	
							if (map.getAreaByCoordinates(cPosXSphere, cPosZSphere)!=null){
								if (map.getAreaByCoordinates(cPosXSphere, cPosZSphere).getLeft().isSolid()){
									//Stoppen an Wand links vorw�rts			
									if(cPosXSphere>0 && 0.7f < Math.abs(cPosXSphere%2) && Math.abs(cPosXSphere%2) < 1.0f  && !((0 < cOrientation && cOrientation < Math.PI) || (-Math.PI * 2.0 < cOrientation && cOrientation < -Math.PI))){
										stop=true;
									}
									if(cPosXSphere<0 && 1.0f < Math.abs(cPosXSphere%2) && Math.abs(cPosXSphere%2) < 1.3f  && !((0 < cOrientation && cOrientation < Math.PI) || (-Math.PI * 2.0 < cOrientation && cOrientation < -Math.PI))){
										stop=true;
									}
								}
							}
	
							if (map.getAreaByCoordinates(cPosXSphere, cPosZSphere)!=null){
								if (map.getAreaByCoordinates(cPosXSphere, cPosZSphere).getFront().isSolid()){
									//Stoppen an Wand front vorw�rts
									if(cPosZSphere>0 && 0.7f < Math.abs(cPosZSphere%2) && Math.abs(cPosZSphere%2) < 1.0f  && ((-Math.PI/2 < cOrientation && cOrientation < Math.PI/2) || (Math.PI*3/2 < cOrientation || cOrientation < -Math.PI*3/2))){
										stop=true;
									}
									if(cPosZSphere<0 && 1.0f < Math.abs(cPosZSphere%2) && Math.abs(cPosZSphere%2) < 1.3f  && ((-Math.PI/2 < cOrientation && cOrientation < Math.PI/2) || (Math.PI*3/2 < cOrientation || cOrientation < -Math.PI*3/2))){
										stop=true;
									}
								}
							}
							
							if (map.getAreaByCoordinates(cPosXSphere, cPosZSphere)!=null){
								if (map.getAreaByCoordinates(cPosXSphere, cPosZSphere).getBack().isSolid()){
									//Stoppen an Wand back vorw�rts			
									if(cPosZSphere<0 && 0.7f < Math.abs(cPosZSphere%2) && Math.abs(cPosZSphere%2) < 1.0f  && !((-Math.PI/2 < cOrientation && cOrientation < Math.PI/2) || (Math.PI*3/2 < cOrientation || cOrientation < -Math.PI*3/2))){
										stop=true;
									}
									if(cPosZSphere>0 && 1.0f < Math.abs(cPosZSphere%2) && Math.abs(cPosZSphere%2) < 1.3f  && !((-Math.PI/2 < cOrientation && cOrientation < Math.PI/2) || (Math.PI*3/2 < cOrientation || cOrientation < -Math.PI*3/2))){
										stop=true;
									}
								}
							}
//							if(stop){
//								setChanged();
//								notifyObservers("shootStop");
//							}
						}
					}
				}.start();			
			}
		}
	}

	/**
	 * Interaktion mit einem (freundlich gesinnten) NPC.
	 * Starten einer Quest.
	 */
	public void interact(){	
		
		if(map.getAreaByCoordinates(posX, posZ)!=null){
			Quest cQuest = map.getAreaByCoordinates(posX, posZ).getQuest();
			if(cQuest!=null){
				if(hero.getSemester()==cQuest.getSemester()){
					if(!cQuest.getEventLocked()){
						activeQuests.add(new QuestHandler(cQuest, this));
	
						setChanged();
						notifyObservers(activeQuests.getLast());
						activeQuests.getLast().start();
	
					}
				}else{
					if(hero.getSemester()<cQuest.getSemester()){
						setChanged();
						notifyObservers("zuTief");	
					}else{
						setChanged();
						notifyObservers("zuHoch");						
					}
				}
			}
		}
		setChanged();
		notifyObservers("quests");
	}
	
	/**
	 * Item auf der aktuellen Position des Spielers zum Backpack hinzufuegen.
	 */
	public void itemToBackpack(){

		
		Area area = map.getAreaByCoordinates(posX, posZ);
		MapObject mapObject;
		
		if (area != null){
			mapObject = map.getAreaByCoordinates(posX, posZ).getMapObject();
		}else{
			mapObject=null;
		}
		
		if(mapObject != null){			
			if (mapObject instanceof Item){
				Item item = (Item) mapObject;
				
				boolean collected = hero.addItemToBackpack(item);


								
				if(collected){
					
					setChanged();
					notifyObservers(area);
					
					for(QuestHandler cHandler : activeQuests){
						cHandler.getNextActionByCollectingItem(item);

					}

					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					area.setMapObject(null);
				}
			}
		}
	}
	
	/**
	 * Bekommt man in einer Quest den Auftrag ein Item zu finden, so wird gecheckt, ob dieses 
	 * bereits im Inventar des Players ist, also Aufgabe bereits erfuellt ist.
	 * 
	 * @param questHandler
	 */
	public void checkBackpack(QuestHandler questHandler){
		for(Item item : hero.getBackpack()){
			if(item!=null){
				boolean finish = questHandler.getNextActionByCollectingItem(item);
				if(finish)
					questHandler = null;				
			}
		}
	}
	
	/**
	 * Antworten waehrend Dialogen.
	 * num lann 1, 2 oder 3 sein.
	 * 
	 * 
	 * @param num
	 */
	public void reply(int num){
		for(QuestHandler cHandler : activeQuests){
			cHandler.getNextActionByDialog(num);
		}
	}

	private boolean keyLock = false;
	public void accessBackpack(){
		
		if (!keyLock) {
			keyLock = true;

			if (!inBackpack) {
				boolean empty = hero.setAuswahl(0);
				if (empty)
					inBackpack = false;
				else
					inBackpack = true;
			} else {
				keyLock = true;
				hero.setAuswahl(-1);
				inBackpack = false;
			}
			new Thread() {

				public void run() {
					try {
						Thread.sleep(150);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					keyLock = false;
				}
			}.start();
		}
		

		
	}
	
	/**
	 * Skip zum naechsen Item im Inventar, wenn mehrere Items vorhanden und
	 * Backpack aktiv. 
	 */
	public void nextItem() {
		if (!keyLock) {
			keyLock = true;
			if (inBackpack) {
				hero.nextItem();
			}
			new Thread() {

				public void run() {
					try {
						Thread.sleep(150);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					keyLock = false;
				}
			}.start();
		}
	}
	
	/**
	 * Aktuell ausgewaehltes Item ablegen.
	 */
	public void dropItem(){
		if (!keyLock) {
			keyLock = true;
			if (inBackpack) {
				boolean empty = hero.drop(curArea);
				if (empty) {
					accessBackpack();
				}
			}

			new Thread() {

				public void run() {
					try {
						Thread.sleep(150);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					keyLock = false;
				}
			}.start();

		}

	}
	
	/**
	 * Besiegte Jawas stehen wieder auf.  
	 */
	public void wakeJawas(){
		setChanged();
		notifyObservers("wakeJawas");		
	}
	
	/**
	 * QuestHandler loeschen.
	 * 
	 * @param qh
	 */
	public void delQuestHandler(final QuestHandler qh){
		new Thread(){
			@Override
			public void run() {
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				activeQuests.remove(qh);
				setChanged();
				notifyObservers("quests");				
			}
		}.start();
	}
	
	
	/**
	 * Neue NPCs werden unter Beobachtung gestellt.
	 * Wird in der finalen Quest benoetigt. 
	 * 
	 * @param npcsNeu
	 */
	public void addNPCtoObserver(NPC[] npcsNeu){
		this.npcsNeu = npcsNeu;
		setChanged();
		notifyObservers(npcsNeu);	
		
	}

	
}