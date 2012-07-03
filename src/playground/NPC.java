package playground;

import java.util.Observable;

import javax.media.j3d.Alpha;
import javax.media.j3d.PositionPathInterpolator;
import javax.media.j3d.RotationInterpolator;

/**
 * NPC.java
 * 
 * Klasse zum Erzeugen eines NPC
 * 
 * @author Soeren Kroell, Stephanie Scholl, Mario Sigel, Ersin Yilmiz
 */
public abstract class NPC extends Observable implements MapObject{
	protected String file;
	private boolean alive = true;
	private int posX;
	private int posZ;
	private float rotation;	//Ein Wert von 1.0f entspricht einer Drehung um PI/2.
	private RotationInterpolator rotator=null;
	private Alpha alpha;
	protected int points;
	private PositionPathInterpolator ppi;
	private Alpha alphaMapObject;
	
	public NPC(int posX, int posZ, float rotation){
		this.posX = posX;
		this.posZ = posZ;
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
	
	public String getFile(){
		return file;
	}
	
	public int getPosX(){
		return posX;
	}
	
	public int getPosZ(){
		return posZ;
	}
	
	public float getRotation(){
		return rotation;
	}
	
	public RotationInterpolator getRotator(){
		return rotator;
	}
	
	public void setRotator(RotationInterpolator rotator){
		this.rotator=rotator;
	}		
	public Alpha getAlpha(){
		return alpha;
	}
	
	public void setAlpha(Alpha alpha){
		this.alpha=alpha;
	}	
	
	public void setAlive(boolean alive){
		this.alive=alive;
		if(!alive){
			setChanged();
			notifyObservers(this);
		}
	}
	
	public boolean getAlive(){
		return alive;
	}
	
	public int getPoints(){
		return points;
	}
}
