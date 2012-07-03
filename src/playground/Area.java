package playground;

/**
 * Area.java
 * 
 * Klasse zum Erstellen einer einzelnen Area
 * 
 * @author Soeren Kroell, Stephanie Scholl, Mario Sigel, Ersin Yilmaz
 */
public class Area {

	private Wall front;
	private Wall back;
	private Wall right;
	private Wall left;
	private Wall floor;
	private Wall sky;
	private Quest quest;
	private MapObject mapObject= null;

	/**
	 * Angepasster Konstruktor
	 * @param front
	 * @param back
	 * @param right
	 * @param left
	 * @param floor
	 * @param sky
	 * @param quest
	 * @param mapObject
	 */
	public Area(Wall front, Wall back, Wall right, Wall left, Wall floor, Wall sky, Quest quest, MapObject mapObject){
		this.front=front;
		this.back=back;
		this.right=right;
		this.left=left;
		this.floor=floor;
		this.sky=sky;
		this.quest=quest;
		this.mapObject = mapObject;
	}
	
	public Wall getFront(){
		return front;
	}

	public Wall getBack(){
		return back;
	}

	public Wall getRight(){
		return right;
	}

	public Wall getLeft(){
		return left;
	}

	public Wall getFloor(){
		return floor;
	}

	public Wall getSky(){
		return sky;
	}
	
	public Quest getQuest(){
		return quest;
	}
	
	public MapObject getMapObject(){
		return mapObject;
	}
	
	public void setMapObject(MapObject mapObject){
		this.mapObject = mapObject;
	}
}
