package userInterface;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import businesslogic.GameEngine;
import businesslogic.Hero;
import playground.Item;


/**
 * PanelBottom.java
 * 
 * Panel zur Darstellung des Backpacks.
 * 
 * @author Soeren Kroell, Stephanie Scholl, Mario Sigel, Ersin Yilmiz
 *
 */
public class PanelBottom extends JPanel implements Observer {
	
	private BufferedImage navBuff = null;
	private BufferedImage auswahlBuff = null;
	private BufferedImage auswahlRotBuff = null;
	private GameEngine ge;
	private Hero hero;
	
	public PanelBottom(GameEngine ge){
		super();
		this.ge = ge;
		this.hero = ge.getHero();
		ge.addObserver(this);
		hero.addObserver(this);
		// Laden der Standardbilder
		try {
			navBuff = ImageIO.read(new File("image/navBottom.png"));
			auswahlBuff = ImageIO.read(new File("image/auswahl.png"));
			auswahlRotBuff = ImageIO.read(new File("image/auswahlGreen.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paintComponent(Graphics g) {

		g.drawImage(navBuff, 0, 0, null);
		
		int counter = 0;
		for(Item item : hero.getBackpack()){
			BufferedImage imageScaleBuff = new BufferedImage(80, 80, BufferedImage.TYPE_INT_ARGB);
			BufferedImage imageBuff = null;
			try {
				imageBuff = ImageIO.read(new File(item.getFile()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			float alpha = (ge.getInBackpack()) ? 1 : 0.3f;
			Composite translucent = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
			
			Graphics2D g2d = (Graphics2D)g;
			g2d.setComposite(translucent);
			
			if(hero.getAuswahl()==counter){
				
				g2d.drawImage(auswahlRotBuff, 45+(counter)*100,	5, this);		
			}else{
				g2d.drawImage(auswahlBuff, 45+(counter)*100,	5, this);
			}
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
			
			imageScaleBuff.getGraphics().drawImage(imageBuff, 0, 0,	80, 80, null);
			Graphics gBuff = imageScaleBuff.getGraphics();
			gBuff.setFont(new Font("Arial", Font.BOLD, 10));
			gBuff.setColor(Color.white);
			//gBuff.setColor(new Color(145f/255f, 15f/255f, 38f/255f, 1));
			gBuff.drawString(item.getName(), 5, 10);
			g.drawImage(imageScaleBuff, 50+(counter)*100,	10, this);		

			counter++;
		}	
	}

	@Override
	/**
	 * Aktualisieren des Panels wenn Aktionen mit dem Rucksack statt gefunden haben
	 */
	public void update(Observable obj, Object arg) {
		if(arg instanceof String){
			if(((String)arg).equals("addItem")){
				repaint();
			}
			if(((String)arg).equals("backpack")){
				repaint();
			}
			if(((String)arg).equals("delItem")){
				repaint();
			}
		}
	}
}





