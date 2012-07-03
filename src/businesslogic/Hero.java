package businesslogic;

import java.util.LinkedList;
import java.util.Observable;

import playground.*;

/**
 * Hero.java
 * 
 * Klasse zum Erstellen eines Hero
 * 
 * @author Soeren Kroell, Stephanie Scholl, Mario Sigel, Ersin Yilmaz
 */
public class Hero extends Observable{

	final private int BACKPACK_SIZE = 10;
	private LinkedList<Item> backpack = new LinkedList<Item>();
	private String imageFile;
	private int creditPoints = 0;
	private int semester = 1;
	private String name;
	private int auswahl = -1;

	public Hero(String name, String imageFile){
		this.name = name;
		this.imageFile = imageFile;
	}
	
	public int getCreditPoints(){
		return creditPoints;
	}
	
	public int getSemester(){
		return semester;
	}

	public String getImageFile(){
		return imageFile;
	}
	public void setImageFile(String imageFile){
		this.imageFile = imageFile;
	}
	
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	
	public LinkedList<Item> getBackpack(){
		return backpack;
	}
	
	public boolean setAuswahl(int auswahl){
		if(backpack.size()>0){
			this.auswahl = auswahl;
			setChanged();
			notifyObservers("backpack");	
			return false;
		}
		return true;
	}
	
	public int getAuswahl(){
		return auswahl;
	}

	/**
	 * Semesterpunktezahl erhoehen
	 * @param points
	 */
	public void addPoints(int points){
		creditPoints += points;

		if (creditPoints<0 && semester==1){
			creditPoints = 0;
			semester = 1;
		}else if(creditPoints<0){
			creditPoints = 50+creditPoints;
			semester -= 1;
		}else if (creditPoints>=50){
			creditPoints = creditPoints%50;
			semester += 1;
		}
		
		setChanged();
		notifyObservers("points");	
		
		if(semester>6){
			/////////Spielende
			new Thread() {
				
				@Override
				public void run() {
					try {
						Thread.sleep(4000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					setChanged();
					notifyObservers("Spielende");	
				}
			}.start();
		}
	}
	
	/**
	 * Methode zum Ablegen eines Gegenstands auf einer Area
	 * @param area
	 * @return
	 */
	public boolean drop(Area area){
		if(backpack.size()>0 && area.getMapObject()==null){
			setChanged();
			notifyObservers("delItem");	
			setChanged();
			notifyObservers(backpack.get(auswahl));
			
			area.setMapObject(backpack.get(auswahl));
			backpack.remove(auswahl);
		}
		auswahl = 0;
		if(backpack.size()==0){
			return true;
		}else{
			return false;
		}
		
	}
	
	/**
	 * Methode zum Hinzufuegen eines Gegenstands zum Rucksack
	 * @param newItem
	 * @return
	 */
	public boolean addItemToBackpack(Item newItem) {
		if (backpack.size()<BACKPACK_SIZE){
			backpack.add(newItem);
			setChanged();
			notifyObservers("addItem");		
			return true; 
		}else{
			return false;
		}
	}

	/**
	 * Methode zum erhalten des naechsten Gegenstands im Rucksack
	 */
	public void nextItem(){
		if(auswahl<backpack.size()-1){
			auswahl++;	
		}else{
			auswahl = 0;
		}
		setChanged();
		notifyObservers("addItem");	
	}

	public void editPointsAndSemester(int cp, int semeseter) {
		
	}
}
