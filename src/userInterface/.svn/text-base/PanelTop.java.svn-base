package userInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import businesslogic.GameEngine;
import businesslogic.Hero;
import businesslogic.QuestHandler;

/**
 * PanelTop.java
 * 
 * 
 * Panel zum Darstellen des Spielerbilds, Spielernamen, Spielstand,
 * Punktestand, Questname und Kompass waehrend des laufenden Spiels
 * 
 * @author Soeren Kroell, Stephanie Scholl, Mario Sigel, Ersin Yilmiz
 */
public class PanelTop extends JPanel implements Observer {
	
	private BufferedImage navBuff = null;
	private BufferedImage kompassBuff = null;
	private BufferedImage nadelBuff = null;
	private BufferedImage destinationBI; 
	private BufferedImage avatar = null;
	private BufferedImage semesterCircle = null;
	private BufferedImage top = null;
	private BufferedImageOp bio;
	private double phi = 0;
	private GameEngine ge;
	private Hero hero;
	private String warning = "";
	private Color neonGreen = new Color(117f/255f, 221f/255f, 28f/255f, 1);
	private Color neonGreen2 = new Color(131f/255f, 224f/255f, 51f/255f, 1);

	/**
	 * Angepasster Konstruktor
	 * @param ge
	 */
	public PanelTop(GameEngine ge){
		super();
		this.ge = ge;
		this.hero = ge.getHero();
		ge.addObserver(this);
		hero.addObserver(this);
		// Laden der Standardbilder
		try {
			navBuff = ImageIO.read(new File("image/navTop.png"));
			kompassBuff = ImageIO.read(new File("image/kompass.png"));
			nadelBuff = ImageIO.read(new File("image/nadel.png"));
			semesterCircle = ImageIO.read(new File("image/semester_circle3.png"));
			top = ImageIO.read(new File("image/top5.png"));
			avatar = (hero.getImageFile()==null) ? ImageIO.read(new File("image/scrat.png")) : ImageIO.read(new File(hero.getImageFile()));
		} catch (IOException e) {
			e.printStackTrace();
		}	    
	}

	@Override
	public void paintComponent(Graphics g) {

		g.drawImage(navBuff, 0, 0, null);
		g.drawImage(kompassBuff, 1100, 0, null);
		g.drawImage(avatar.getScaledInstance(90, 90, Image.SCALE_SMOOTH), 40, 0, null);
		
		// Anzeige des Heldnamen
		g.setFont(new Font("Arial", Font.PLAIN, 15));
		g.setColor(Color.white);
		g.drawString(hero.getName(), 200, 43);
				
		AffineTransform at = new AffineTransform();		
		at.rotate(phi, 50 , 50 );
		at.translate(1100*Math.sin(phi+1.572), 1100*Math.cos(phi+1.572));    
	    bio = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
	    
		destinationBI = bio.filter(nadelBuff, null);
		g.drawImage(destinationBI, 0,	0, null);	
		
		// Anzeige Hintergrund hinter Punkestandlinie und Semesterzahlhintergrund
		g.drawImage(top, 138, 5, null);	
		
		int points = hero.getCreditPoints();

		// Anzeige Punktestand als Linie
		for(int i=0; i<865; i++ ){
			if(i<(float)points/50f*865f){
				g.setColor(neonGreen2);
				g.drawLine(150+i, 15, 150+i, 18);
			}
		}
		
		// Anzeige der Semesterzahl
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.PLAIN, 30));
		g.drawString(Integer.toString(hero.getSemester()), 158, 42);

		// Anzeige des Questnamen
		g.setColor(neonGreen);
		g.setFont(new Font("Arial", Font.BOLD, 17));
		
		int counter=0;
		for(QuestHandler questhandler : ge.getActiveQuests()){
			int y;
			int x;
			if(counter%2==0)
				y = 55;
			else
				y = 75;
			x = 450 + counter/2*150;
			g.drawString(questhandler.getQuest().getQuestName(), x, y);
			counter++;
		}
		
		g.setColor(new Color(255f/255f, 140f/255f, 0f/255f, 1));
		g.drawString(warning, 145, 75);
		
	}

	@Override
	public void update(Observable obj, Object arg) {
		if(arg instanceof String){
			if("rotate".equals((String)arg)){
				phi = ge.getOrientation();
				this.repaint();
			}
			if("points".equals((String)arg)){
				this.repaint();
			}
			if("quests".equals((String)arg)){
				this.repaint();
			}
			
			if("zuTief".equals((String)arg)){
				warning = "Das verstehst du noch nicht. Komm spaeter wieder.";
				this.repaint();
				final long time = System.currentTimeMillis() ;
				final PanelTop p = this;
				new Thread(){
					@Override
					public void run() {
						while(System.currentTimeMillis()-time<3500){
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						p.warning = " ";
						p.repaint();
					}
				}.start();	

			}
			if("zuHoch".equals((String)arg)){
				warning = "Du kannst hier nichts mehr lernen.";
				this.repaint();
				final long time = System.currentTimeMillis() ;
				final PanelTop p = this;
				new Thread(){
					@Override
					public void run() {
						while(System.currentTimeMillis()-time<3500){
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						p.warning = " ";
						p.repaint();
					}
				}.start();	

			}
		}
	}
	
	public void setAvatar(String pic) throws IOException{
		avatar = ImageIO.read(new File(pic));
	}
}