package userInterface;

import java.awt.CardLayout;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;

//import UserInterface.MainFrame;

import javazoom.jlgui.basicplayer.BasicPlayerException;

/**
 * StartPanel.java
 * 
 * 
 * Panel zum Darstellen der Startseite.
 * 
 * @author Soeren Kroell, Stephanie Scholl, Mario Sigel, Ersin Yilmiz
 */
public class StartPanel extends javax.swing.JPanel{

    private MainFrame mF;
    private SettingPanel sp;
   
    /**
     * Default Konstruktor
     */
    public StartPanel(){
        initComponents();
    }
    
    /**
     * Angepasster Konstruktor mit Mitgabe des MainFrame und SettingPanel
     * @param mF
     * @param sp
     */
    public StartPanel(MainFrame mF, SettingPanel sp) {
        initComponents(); 
        this.mF = mF;
        this.sp = sp;
        
        // Look & Feel Windows bzw. Linux Anpasssungen
        startButton.setOpaque(false);
        startButton.setBorder (new javax.swing.border.EmptyBorder(0,0,0,0));
        startButton.setContentAreaFilled(false);
        startButton.setBackground(new Color (0,0,0,0));
        
        settingButton.setOpaque(false);
        settingButton.setBorder (new javax.swing.border.EmptyBorder(0,0,0,0));
        settingButton.setContentAreaFilled(false);
        settingButton.setBackground(new Color (0,0,0,0));
        
        quitButton.setOpaque(false);
        quitButton.setBorder (new javax.swing.border.EmptyBorder(0,0,0,0));
        quitButton.setContentAreaFilled(false);
        quitButton.setBackground(new Color (0,0,0,0));   
    }
    
    /**
     * Komponenten initialisieren
     */
    private void initComponents() {

        startButton = new javax.swing.JButton();
        settingButton = new javax.swing.JButton();
        quitButton = new javax.swing.JButton();
        startPanel = new javax.swing.JLabel();

        setLayout(null);

        startButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/repository/images/Screen_Button_Start.png"))); // NOI18N
        startButton.setBorder(null);
        startButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        startButton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/repository/images/Screen_Button_Start_hover.png"))); // NOI18N
        startButton.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/repository/images/Screen_Button_Start_hover.png"))); // NOI18N
        startButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                startButtonMouseEntered(evt);
            }
        });
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });
        add(startButton);
        startButton.setBounds(910, 350, 370, 50);
        startButton.setOpaque(false);
        startButton.setBorder (new javax.swing.border.EmptyBorder(0,0,0,0));
        startButton.setContentAreaFilled(false);
        startButton.setBackground(new Color (0,0,0,0));

        settingButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/repository/images/Screen_Button_Settings.png"))); // NOI18N
        settingButton.setBorder(null);
        settingButton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/repository/images/Screen_Button_Settings_hover.png"))); // NOI18N
        settingButton.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/repository/images/Screen_Button_Settings_hover.png"))); // NOI18N
        settingButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                settingButtonMouseEntered(evt);
            }
        });
        settingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingButtonActionPerformed(evt);
            }
        });
        add(settingButton);
        settingButton.setBounds(910, 410, 370, 51);

        quitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/repository/images/Screen_Button_Quit.png"))); // NOI18N
        quitButton.setBorder(null);
        quitButton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/repository/images/Screen_Button_Quit_hover.png"))); // NOI18N
        quitButton.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/repository/images/Screen_Button_Quit_hover.png"))); // NOI18N
        quitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                quitButtonMouseEntered(evt);
            }
        });
        quitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitButtonActionPerformed(evt);
            }
        });
        add(quitButton);
        quitButton.setBounds(910, 470, 370, 50);

        startPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/repository/images/Screen_Main_Menu.png"))); // NOI18N
        startPanel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        add(startPanel);
        startPanel.setBounds(0, 0, 1280, 800);
    }

   
    /**
     * Beim Klick auf den Spielstart Button wird das PlayPanel angezeigt und der SelectSound abgespielt
     * @param evt
     */
    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {
       try {
            mF.playSelectSound();
        } catch (BasicPlayerException ex) {
            Logger.getLogger(StartPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
       CardLayout cL = (CardLayout) mF.getContentPane().getLayout();
       cL.show(mF.getContentPane(), "playCard");
    }

    /**
     * Abspielen des HoverSound wenn die Maus den Button entered
     * @param evt
     */
    private void startButtonMouseEntered(java.awt.event.MouseEvent evt) {
        try {
            mF.playHoverSound();
        } catch (BasicPlayerException ex) {
            Logger.getLogger(StartPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Abspielen des HoverSound wenn die Maus den Button entered
     * @param evt
     */
    private void settingButtonMouseEntered(java.awt.event.MouseEvent evt) {
        try {
            mF.playHoverSound();
        } catch (BasicPlayerException ex) {
            Logger.getLogger(StartPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Abspielen des HoverSound wenn die Maus den Button entered
     * @param evt
     */
    private void quitButtonMouseEntered(java.awt.event.MouseEvent evt) {
        try {
            mF.playHoverSound();
        } catch (BasicPlayerException ex) {
            Logger.getLogger(StartPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Beim Klick auf den Spiel Beenden Button wird der SelectSound abgespielt und Spiel beendet
     * @param evt
     */
    private void quitButtonActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            mF.playSelectSound();
            System.exit(0);
        } catch (BasicPlayerException ex) {
            Logger.getLogger(StartPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Beim Klick auf den Einstellungen Button wird das SettingPanel angezeigt, der SelectSound abgespielt
     * und die Tastatureinstellungen gestartet
     * @param evt
     */
    private void settingButtonActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            mF.playSelectSound();
        } catch (BasicPlayerException ex) {
            Logger.getLogger(StartPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sp.makeEntries();
        CardLayout cL = (CardLayout) mF.getContentPane().getLayout();
        cL.show(mF.getContentPane(), "settingCard");
    }

    private javax.swing.JButton quitButton;
    private javax.swing.JButton settingButton;
    private javax.swing.JButton startButton;
    private javax.swing.JLabel startPanel;
}
