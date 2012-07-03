package playground;

/**
 * Map.java
 * 
 * Klasse zum Erzeugen einer Map
 * 
 * @author Soeren Kroell, Stephanie Scholl, Mario Sigel, Ersin Yilmaz
 */
public class Map {
	private Area[][][] areas;
	
	public Map(){
		areas = new Area[200][2][200];
	}
	
	public Area[][][] getAreas(){
		return areas;
	}
	
	public void setArea(Area area, int x, int y, int z){
		this.areas[x+100][y][z+100] = area;
	}
	
	public Area getAreaByCoordinates(float x, float z){
		int tmpX = translateX(x);
		int tmpZ = translateZ(z);
		return areas[tmpX][0][tmpZ];	
	}
	public Area getAreaByIndices(int x, int y, int z){
		return areas[x+100][y][z+100];
	}
	
	public int translateX(float x){
		return (x>0) ? (int)(-(x+1)/2)+100 : (int)(-(x-1)/2)+100;
	}
	public int translateZ(float z){
		return (z>0) ? (int)((z+1)/2)+100 : (int)((z-1)/2)+100;
	}
}
