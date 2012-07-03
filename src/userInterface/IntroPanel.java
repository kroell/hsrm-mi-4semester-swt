package userInterface;

import java.awt.CardLayout;
import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import javazoom.jlgui.basicplayer.BasicPlayerException;

import org.pushingpixels.trident.Timeline;

import businesslogic.GameEngine;

/**
 * IntroPanel.java
 * 
 * 
 * Panel um zum Darstellen des Introtextes um in das Spiel einzuleiten.
 * Die einzelnen Textabschnitte werden mittels Timer nacheinander
 * sichtbar gemacht und verblassen ebenfalls mittels Timer hellgrau.
 * 
 * @author Soeren Kroell, Stephanie Scholl, Mario Sigel, Ersin Yilmiz
 *
 */
public class IntroPanel extends javax.swing.JPanel {

    private MainFrame mF;
    private Timer timer;
    private Timer timer2;
    private Timer timer3;
    private Timer timer4;
    private Timer timer5;
    private Timer timer6;
    private Timer timerNextIntroPanel;
    private Timer timerGamePanel;
    private String nextPanel = "introCard2";
    private static final int CANVAS3D_WIDTH = 1210;
    private static final int CANVAS3D_HEIGHT = 600;
    private static WorldCanvas c3d;
    private GameEngine gameEngine;	       
    private JPanel gamePanel;

    /**
     * Default Konstruktor
     */
    public IntroPanel() {
        initComponents();
    }

    /**
     * Angepasster Konstruktor
     * @param mF
     * @param c3d
     * @param gamePanel
     */
    public IntroPanel(MainFrame mF,WorldCanvas c3d, JPanel gamePanel) {
        initComponents();
        this.c3d = c3d;
        this.gamePanel = gamePanel;
        
        //Transparenzen verschiedener JTextAreas
        jScrollPane1.setBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0));
        jScrollPane1.setOpaque(false);
        jScrollPane1.getViewport().setOpaque(false);
        introText1.setOpaque(false);
        introText1.setBorder(null);

        jScrollPane2.setBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0));
        jScrollPane2.setOpaque(false);
        jScrollPane2.getViewport().setOpaque(false);
        introText2.setOpaque(false);
        introText2.setBorder(null);

        jScrollPane3.setBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0));
        jScrollPane3.setOpaque(false);
        jScrollPane3.getViewport().setOpaque(false);
        introText3.setOpaque(false);
        introText3.setBorder(null);

        jScrollPane4.setBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0));
        jScrollPane4.setOpaque(false);
        jScrollPane4.getViewport().setOpaque(false);
        introText4.setOpaque(false);
        introText4.setBorder(null);

        jScrollPane5.setBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0));
        jScrollPane5.setOpaque(false);
        jScrollPane5.getViewport().setOpaque(false);
        introText5.setOpaque(false);
        introText5.setBorder(null);

        jScrollPane6.setBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0));
        jScrollPane6.setOpaque(false);
        jScrollPane6.getViewport().setOpaque(false);
        introText6.setOpaque(false);
        introText6.setBorder(null);
        
        // Look & Feel Windows bzw. Linux Anpasssungen
        skipIntroButton.setOpaque(false);
        skipIntroButton.setBorder (new javax.swing.border.EmptyBorder(0,0,0,0));
        skipIntroButton.setContentAreaFilled(false);
        skipIntroButton.setBackground(new Color (0,0,0,0));

        this.mF = mF;
    }

    /**
     * Komponenten initialisieren
     */
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        introText1 = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        introText2 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        introText3 = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        introText4 = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        introText5 = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        introText6 = new javax.swing.JTextArea();
        skipIntroButton = new javax.swing.JButton();
        introBackground = new javax.swing.JLabel();

        setLayout(null);

        introText1.setBackground(new java.awt.Color(0, 0, 0));
        introText1.setColumns(20);
        introText1.setEditable(false);
        introText1.setFont(new java.awt.Font("Verdana", 0, 18));
        introText1.setForeground(new java.awt.Color(255, 255, 255));
        introText1.setLineWrap(true);
        introText1.setRows(2);
        introText1.setText("Aber niemand wusste im vornherein, wie es an der Hochschule RheinMain wirklich zugeht...");
        introText1.setWrapStyleWord(true);
        introText1.setAutoscrolls(false);
        jScrollPane2.setViewportView(introText1);

        add(jScrollPane2);
        jScrollPane2.setBounds(330, 200, 730, 50);

        introText2.setBackground(new java.awt.Color(0, 0, 0));
        introText2.setColumns(20);
        introText2.setEditable(false);
        introText2.setFont(new java.awt.Font("Verdana", 0, 18));
        introText2.setForeground(new java.awt.Color(255, 255, 255));
        introText2.setLineWrap(true);
        introText2.setRows(3);
        introText2.setText("Rücke deine Hornbrille zurecht, desinfiziere deine klapprigen\nInformatikerhändchen und wische den letzten Rest Pizza aus deinem Gesicht,\ndenn jetzt gehts in den Fooog und der ist schließlich 30 tief!");
        introText2.setWrapStyleWord(true);
        introText2.setAutoscrolls(false);
        jScrollPane1.setViewportView(introText2);

        add(jScrollPane1);
        jScrollPane1.setBounds(330, 270, 730, 80);

        introText3.setBackground(new java.awt.Color(0, 0, 0));
        introText3.setColumns(20);
        introText3.setEditable(false);
        introText3.setFont(new java.awt.Font("Verdana", 0, 18));
        introText3.setForeground(new java.awt.Color(255, 255, 255));
        introText3.setLineWrap(true);
        introText3.setRows(2);
        introText3.setText("Kämpfe dich durch die einzelnen Semester, erfülle gestellte Quests,\nsammle wichtige Creditpoints und verfasse deine Bachelorarbeit. ");
        introText3.setWrapStyleWord(true);
        introText3.setAutoscrolls(false);
        jScrollPane3.setViewportView(introText3);

        add(jScrollPane3);
        jScrollPane3.setBounds(330, 370, 730, 50);

        introText4.setBackground(new java.awt.Color(0, 0, 0));
        introText4.setColumns(20);
        introText4.setEditable(false);
        introText4.setFont(new java.awt.Font("Verdana", 0, 18));
        introText4.setForeground(new java.awt.Color(255, 255, 255));
        introText4.setRows(3);
        introText4.setText("Aber pass auf: fällst du durch Klausuren, werden dir wichtige Creditpoints \ngestrichen. Fallen diese unter 0 wirst du exmatrikuliert! Dies beendet das \nSpiel vorzeitig. Und das lässt dein Stolz doch nicht zu, oder? ODER???");
        jScrollPane4.setViewportView(introText4);

        add(jScrollPane4);
        jScrollPane4.setBounds(330, 440, 730, 88);

        introText5.setBackground(new java.awt.Color(0, 0, 0));
        introText5.setColumns(20);
        introText5.setEditable(false);
        introText5.setFont(new java.awt.Font("Verdana", 0, 18));
        introText5.setForeground(new java.awt.Color(255, 255, 255));
        introText5.setLineWrap(true);
        introText5.setRows(2);
        introText5.setText("Deine größte Stärke und somit deine beste Waffe ist die Tastatur. Ist doch klar. Schließlich sind wir hier in der Informatik.");
        introText5.setWrapStyleWord(true);
        jScrollPane5.setViewportView(introText5);

        add(jScrollPane5);
        jScrollPane5.setBounds(330, 540, 730, 50);

        introText6.setBackground(new java.awt.Color(0, 0, 0));
        introText6.setColumns(20);
        introText6.setEditable(false);
        introText6.setFont(new java.awt.Font("Verdana", 1, 18));
        introText6.setForeground(new java.awt.Color(255, 255, 255));
        introText6.setLineWrap(true);
        introText6.setRows(2);
        introText6.setText("Und ein gut gemeinter Rat zum Schluss: Achte niemals auf Äußer-\nlichkeiten, niemals...!");
        introText6.setWrapStyleWord(true);
        introText6.setAutoscrolls(false);
        jScrollPane6.setViewportView(introText6);

        add(jScrollPane6);
        jScrollPane6.setBounds(330, 610, 730, 50);

        skipIntroButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/repository/images/intro_skip2.png"))); // NOI18N
        skipIntroButton.setBorder(null);
        skipIntroButton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/repository/images/intro_skip_hover2.png"))); // NOI18N
        skipIntroButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	skipIntroButtonMouseEntered(evt);
            }
        });
        skipIntroButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skipIntroButtonActionPerformed(evt);
            }
        });
        add(skipIntroButton);
        skipIntroButton.setBounds(1090, 710, 189, 51);

        introBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/repository/images/intro_screen_blank.png"))); // NOI18N
        add(introBackground);
        introBackground.setBounds(0, 0, 1280, 800);
    }

    private void skipIntroButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	
    	try {
            mF.playSelectSound();
        } catch (BasicPlayerException ex) {
            Logger.getLogger(PlayPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    	cancelAllTimer();
    	openGameCard();
    	
    }

    private void skipIntroButtonMouseEntered(java.awt.event.MouseEvent evt) {
        try {
            mF.playHoverSound();
        } catch (BasicPlayerException ex) {
            Logger.getLogger(PlayPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private javax.swing.JLabel introBackground;
    private javax.swing.JTextArea introText1;
    private javax.swing.JTextArea introText2;
    private javax.swing.JTextArea introText3;
    private javax.swing.JTextArea introText4;
    private javax.swing.JTextArea introText5;
    private javax.swing.JTextArea introText6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JButton skipIntroButton;

    public void start() {
    	System.out.println("TIMER START\n");
        
        // Timer zum Ein- und Ausblenden der einzelnen Textabschnitte
        timer = new Timer();
        SetIntroTextVisible setVisibleTask = new SetIntroTextVisible(introText1, timer);
        timer.schedule(setVisibleTask, 4000);

        timer = new Timer();
        StartAnimation startAnimationTask = new StartAnimation(introText1, timer);
        timer.schedule(startAnimationTask, 7000);

        timer2 = new Timer();
        SetIntroTextVisible setVisibleTask2 = new SetIntroTextVisible(introText2, timer2);
        timer2.schedule(setVisibleTask2, 9000);

        timer2 = new Timer();
        StartAnimation startAnimationTask2 = new StartAnimation(introText2, timer2);
        timer2.schedule(startAnimationTask2, 14000);

        timer3 = new Timer();
        SetIntroTextVisible setVisibleTask3 = new SetIntroTextVisible(introText3, timer3);
        timer3.schedule(setVisibleTask3, 16000);

        timer3 = new Timer();
        StartAnimation startAnimationTask3 = new StartAnimation(introText3, timer3);
        timer3.schedule(startAnimationTask3, 21000);

        timer4 = new Timer();
        SetIntroTextVisible setVisibleTask4 = new SetIntroTextVisible(introText4, timer4);
        timer4.schedule(setVisibleTask4, 23000);

        timer4 = new Timer();
        StartAnimation startAnimationTask4 = new StartAnimation(introText4, timer4);
        timer4.schedule(startAnimationTask4, 29000);

        timer5 = new Timer();
        SetIntroTextVisible setVisibleTask5 = new SetIntroTextVisible(introText5, timer5);
        timer5.schedule(setVisibleTask5, 31000);

        timer5 = new Timer();
        StartAnimation startAnimationTask5 = new StartAnimation(introText5, timer5);
        timer5.schedule(startAnimationTask5, 36000);

        timer6 = new Timer();
        SetIntroTextVisible setVisibleTask6 = new SetIntroTextVisible(introText6, timer6);
        timer6.schedule(setVisibleTask6, 38000);

        timer6 = new Timer();
        StartAnimation startAnimationTask6 = new StartAnimation(introText6, timer6);
        timer6.schedule(startAnimationTask6, 42000);


        // Automatisches weiterleiten auf den naechsten Intro Screen
        timerNextIntroPanel = new Timer();
        OpenNextIntroPanel openNextIntroPanel = new OpenNextIntroPanel();
        timerNextIntroPanel.schedule(openNextIntroPanel, 47000);
        
        // Automatisches weiterleiten auf den naechsten Intro Screen
        timerGamePanel = new Timer();
        OpenGamePanel openGamePanel = new OpenGamePanel();
        timerGamePanel.schedule(openGamePanel, 55000);
    }

    /**
     * Klasse zum Erstellen eines Tasks, welche das mitgegebenen
     * JTextArea im IntroScreen abhaengig vom Timer auf 
     * sichtbar setzt und den Timer danach beendet
     */
    private class SetIntroTextVisible extends TimerTask {

        private JTextArea introText;
        private Timer timer;

        public SetIntroTextVisible(JTextArea introText, Timer timer) {
            this.introText = introText;
            this.timer = timer;
        }

        public void run() {
            introText.setVisible(true);
            timer.cancel();
        }
    }

    /**
     * Klasse zum Erstellen eines Tasks, welche die Schriftfarbge der 
     * mitgegebenen JTextArea im IntroScreen abhaengig vom Timer von weiss
     * nach grau ausblenden laesst
     */
    private class StartAnimation extends TimerTask {

        private JTextArea introText;
        private Timer timer;

        public StartAnimation(JTextArea introText, Timer timer) {
            this.introText = introText;
            this.timer = timer;
        }
        public void run() {
            animation(introText);
            timer.cancel();
        }
    }

    /**
     * Klasse zum Erstellen eines Tasks, welche abhaengig vom Timer das
     * folgende Panel aufruft, so dass kein Aktion des Nutzers notwendig
     * ist
     */
    private class OpenNextIntroPanel extends TimerTask {

        public void run() {
            CardLayout cL = (CardLayout) mF.getContentPane().getLayout();
            cL.show(mF.getContentPane(), nextPanel);
        }
    }
    
    /**
     * Klasse zum Erstellen eines Tasks, welche die gameCard oeffnet und 
     * das PanelBottom repainted
     */
    private class OpenGamePanel extends TimerTask {
        public void run() {
        	openGameCard();
        	
        	// Repaint des Panel Bottom, da dieses sonst bei automatischen Skip nicht angezeigt wird
        	try {
    			Thread.sleep(100);
    		} catch (InterruptedException e) {
    			System.out.println("Thread konnte nicht 'schlafen' gelegt werden.\n");
    		}
            mF.getPanelBottom().repaint();
        }
    }
    
    /**
     * Oeffnen der gameCard zum Anzeigen des Spielfelds
     */
    private void openGameCard(){
    	cancelAllTimer();
        CardLayout cL = (CardLayout) mF.getContentPane().getLayout();
        cL.show(mF.getContentPane(), "gameCard");  
    }
    
    /**
     * Methode zum beenden aller Timer
     */
    public void cancelAllTimer(){
    	timer.cancel();
    	timer2.cancel();
    	timer3.cancel();
    	timer4.cancel();
    	timer5.cancel();
    	timer6.cancel();
    	timerNextIntroPanel.cancel();
    	timerGamePanel.cancel();
    }

    /**
     * Methode zum Erzeugen einer Textanimation, die den Text von seinem Ur-
     * sprungswert in grau faerbt
     */
    private void animation(JTextArea introText) {
        Timeline timeline = new Timeline(introText);
        timeline.setDuration(3500);

        timeline.addPropertyToInterpolate("foreground", introText.getForeground(), new Color(100, 100, 100));
        timeline.play();
    }
    
    /**
     * Alle JTextAreas ausblenden
     */
    public void allTextAreasNonVisible(){
        introText1.setVisible(false);
        introText2.setVisible(false);
        introText3.setVisible(false);
        introText4.setVisible(false);
        introText5.setVisible(false);
        introText6.setVisible(false);
    }
    
    /**
     * Zuruecksetzen der JTextAreas-Textfarbe
     */
    public void colorReset(){
    	 introText1.setForeground(Color.white);
         introText2.setForeground(Color.white);
         introText3.setForeground(Color.white);
         introText4.setForeground(Color.white);
         introText5.setForeground(Color.white);
         introText6.setForeground(Color.white);
    }
}
