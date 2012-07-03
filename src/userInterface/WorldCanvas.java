package userInterface;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.beans.*;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;
import java.awt.image.*;

import javax.imageio.ImageIO;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.J3DGraphics2D;

import businesslogic.GameEngine;
import businesslogic.QuestHandler;

import playground.NPC;

/**
 * WorldCanvas.java
 * 
 * Klasse zur Darstellung der Welt und Dialoge waehrend einer Quests.
 * Erbt von Canvas3D.
 * Das WorldModel wird automatisch ueber j3D gerendert. 
 * In der PostRender-Methode werden darueber die Dialoge gezeichnet/angezeigt. 
 * 
 * @author Soeren Kroell, Stephanie Scholl, Mario Sigel, Ersin Yilmiz
 */
public class WorldCanvas extends Canvas3D implements Observer{
	
	private String info = " ";
	private String[] splitString = {" "};
	private GameEngine gameEngine;
	private long time = 0;
	private boolean end = true;
	private BufferedImage blauBuff;
	private WorldModel wm;

	public WorldCanvas(java.awt.GraphicsConfiguration graphicsConfiguration, GameEngine gameEngine) {
		super(graphicsConfiguration);
		this.gameEngine = gameEngine;
		this.gameEngine.addObserver(this);
	
		try {
			blauBuff = ImageIO.read(new File("image/navDialog.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setModel(WorldModel wm){
		this.wm = wm;
		wm.addObserver(this);		
	}
	
	/** 
	 * Anzeige der Dialoge. 
	 * 
	 */
	public void postRender(){
		
		super.postRender();
		
		J3DGraphics2D g = getGraphics2D();

			float alpha;
			if(end){
				alpha = ((System.currentTimeMillis()) -time>3500) ? 0 : (4000f-(float)((System.currentTimeMillis()) -time))/4000f;
			}else{
				alpha = 1;
			}
			BufferedImage bi;
			bi = new BufferedImage(getSize().width,200, BufferedImage.TYPE_INT_ARGB);

			Graphics2D gBuff = bi.createGraphics();
			
			Composite translucent = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
			bi.getGraphics().drawImage(blauBuff, 0, 0, null);
			gBuff.setColor(Color.white);
			gBuff.setComposite(AlphaComposite.Src);
			gBuff.setFont(new Font("Arial",Font.PLAIN,  20));

			int counter = 0;
			for (String s : splitString){
				gBuff.drawString(s, 20, 50+35*counter);
				counter++;
			}
			
			g.setComposite(translucent);
			g.drawImage(bi, null, 0, getSize().height-200);
			g.flush(false);

	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof QuestHandler){

			((QuestHandler)arg).addPropertyChangeListener("infoString",
						new PropertyChangeListener() {
				@Override
				public void propertyChange(PropertyChangeEvent evt) {
					if (evt.getPropertyName().equalsIgnoreCase("infoString")) {			
						
						info = (String)evt.getNewValue();
						time = System.currentTimeMillis() ;
						
						final String tmpString = (String)evt.getOldValue();
						end = false; 
						if (tmpString.equals("end"))
							end = true;
						
						splitString = info.split("§");
						
						postRender();	
						
						wm.busyWorld();

					}
				}
			});
		}	
	}
}
