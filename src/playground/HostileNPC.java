package playground;

/**
 * HostileNPC.java
 * 
 * Klasse zum Erstellen eines NPC, der keine weitere Funktion hat
 * 
 * @author Soeren Kroell, Stephanie Scholl, Mario Sigel, Ersin Yilmaz
 */
public class HostileNPC extends NPC{

	public HostileNPC(int posX, int posZ, float rotation, String file){
		super(posX, posZ, rotation);
		this.file = file;
	}
}
