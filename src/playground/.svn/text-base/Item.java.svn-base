package playground;

import javax.media.j3d.Alpha;
import javax.media.j3d.PositionPathInterpolator;

/**
 * Item.java
 * 
 * Klasse zum Erstellen von einzelnen Gegenstaenden
 * 
 * @author Soeren Kroell, Stephanie Scholl, Mario Sigel, Ersin Yilmaz
 */
public class Item implements MapObject{

	private String file;
	private String name;
	private float rotation;
	private PositionPathInterpolator ppi;
	private Alpha alphaMapObject;
	
	public Item(String name, String file, float rotation){
		this.file = file;
		this.name = name;
		this.rotation = rotation; 
	}

	public void setAlphaMapObject(Alpha alphaMapObject){
		this.alphaMapObject = alphaMapObject;
	}
	public Alpha getAlphaMapObject(){
		return alphaMapObject;
	}
	
	public void setPositionPathInterpolator(PositionPathInterpolator ppi){
		this.ppi = ppi;
	}
	
	public PositionPathInterpolator getPositionPathInterpolator(){
		return ppi;
	}
	
	public String getFile() {
		return file;
	}

	public String getName() {
		return name;
	}
	
	public float getRotation(){
		return rotation;
	}
}

