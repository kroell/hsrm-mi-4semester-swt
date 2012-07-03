package userInterface;

import businesslogic.MyFileFilter;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javazoom.jlgui.basicplayer.BasicPlayerException;

/**
 * PlayPanel.java
 * 
 * 
 * Panel um das Spiel zu starten, mit der Moeglichkeit der Eingabe
 * des Spielernamens, laden eines Fotos aus dem FileSystem und 
 * erzeugen eines neuen Fotos mittels WebCam.
 * 
 * @author Soeren Kroell, Stephanie Scholl, Mario Sigel, Ersin Yilmiz
 *
 */
public class PlayPanel extends javax.swing.JPanel {

    private MainFrame mF;
    private File selectedFile = null;
    private JFileChooser fc;
    private IntroPanel introPanel;
    private String heroName = null;
    private String heroPicPath = null;

    public PlayPanel() {
        initComponents();
    }

    public PlayPanel(MainFrame mF) {
        initComponents();
        this.mF = mF;
        imgShadow.setVisible(false);
        fcPanel.setVisible (false);
        fcPanel.setOpaque(false);
    }

    public PlayPanel(MainFrame mF, IntroPanel introPanel) {
        initComponents();
        this.mF = mF;
        imgShadow.setVisible(false);
        fcPanel.setVisible (false);
        fcPanel.setOpaque(false);
        this.introPanel = introPanel;

        // Look & Feel Windows bzw. Linux Anpasssungen
        mainMenuButton.setOpaque(false);
        mainMenuButton.setBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0));
        mainMenuButton.setContentAreaFilled(false);
        mainMenuButton.setBackground(new Color(0, 0, 0, 0));

        playButton.setOpaque(false);
        playButton.setBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0));
        playButton.setContentAreaFilled(false);
        playButton.setBackground(new Color(0, 0, 0, 0));

        cameraButton.setOpaque(false);
        cameraButton.setBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0));
        cameraButton.setContentAreaFilled(false);
        cameraButton.setBackground(new Color(0, 0, 0, 0));

        plusButton.setOpaque(false);
        plusButton.setBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0));
        plusButton.setContentAreaFilled(false);
        plusButton.setBackground(new Color(0, 0, 0, 0));

    }

    private void initComponents() {

        mainMenuButton = new javax.swing.JButton();
        fcPanel = new javax.swing.JPanel();
        imageInputLabel = new javax.swing.JLabel();
        imgShadow = new javax.swing.JLabel();
        nameInput = new javax.swing.JTextField();
        cameraButton = new javax.swing.JButton();
        plusButton = new javax.swing.JButton();
        inputLabel = new javax.swing.JLabel();
        playButton = new javax.swing.JButton();
        panelBackground = new javax.swing.JLabel();

        setLayout(null);

        mainMenuButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/repository/images/play_mainmenu.png"))); // NOI18N
        mainMenuButton.setBorder(null);
        mainMenuButton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/repository/images/play_mainmenu_hover.png"))); // NOI18N
        mainMenuButton.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/repository/images/play_mainmenu_hover.png"))); // NOI18N
        mainMenuButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mainMenuButtonMouseEntered(evt);
            }
        });
        mainMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainMenuButtonActionPerformed(evt);
            }
        });
        add(mainMenuButton);
        mainMenuButton.setBounds(910, 670, 370, 51);

        org.jdesktop.layout.GroupLayout fcPanelLayout = new org.jdesktop.layout.GroupLayout(fcPanel);
        fcPanel.setLayout(fcPanelLayout);
        fcPanelLayout.setHorizontalGroup(
            fcPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 510, Short.MAX_VALUE)
        );
        fcPanelLayout.setVerticalGroup(
            fcPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 310, Short.MAX_VALUE)
        );

        add(fcPanel);
        fcPanel.setBounds(60, 430, 510, 310);
        //fcPanel.setOpaque(false);
        add(imageInputLabel);
        imageInputLabel.setBounds(940, 310, 180, 180);

        imgShadow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/repository/images/play_shadow3.png"))); // NOI18N
        add(imgShadow);
        imgShadow.setBounds(930, 290, 220, 220);

        nameInput.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        nameInput.setAlignmentX(0.2F);
        add(nameInput);
        nameInput.setBounds(940, 210, 330, 50);
        nameInput.setOpaque(false);

        nameInput.setBorder(BorderFactory.createLineBorder(Color.white, 0));
        nameInput.setBackground(null);

        cameraButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/repository/images/play_camera.png"))); // NOI18N
        cameraButton.setBorder(null);
        cameraButton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/repository/images/play_camera_hover.png"))); // NOI18N
        cameraButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cameraButtonActionPerformed(evt);
            }
        });
        add(cameraButton);
        cameraButton.setBounds(930, 500, 40, 30);

        plusButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/repository/images/play_plus.png"))); // NOI18N
        plusButton.setBorder(null);
        plusButton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/repository/images/play_plus_hover.png"))); // NOI18N
        plusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusButtonActionPerformed(evt);
            }
        });
        add(plusButton);
        plusButton.setBounds(1230, 500, 30, 27);

        inputLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/repository/images/play_input.png"))); // NOI18N
        add(inputLabel);
        inputLabel.setBounds(910, 180, 370, 365);

        playButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/repository/images/play_los.png"))); // NOI18N
        playButton.setBorder(null);
        playButton.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/repository/images/play_los_hover.png"))); // NOI18N
        playButton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/repository/images/play_los_hover.png"))); // NOI18N
        playButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                playButtonMouseEntered(evt);
            }
        });
        playButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playButtonActionPerformed(evt);
            }
        });
        add(playButton);
        playButton.setBounds(910, 730, 370, 50);

        panelBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/repository/images/play_background.png"))); // NOI18N
        add(panelBackground);
        panelBackground.setBounds(0, 0, 1280, 800);
    }

    /**
     * Beim Klick auf den MainMenu Button wird das StartPanel angezeigt und alle Eingaben zurueckgesetzt
     * @param evt
     */
    private void mainMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            mF.playSelectSound();
        } catch (BasicPlayerException ex) {
            Logger.getLogger(PlayPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        CardLayout cL = (CardLayout) mF.getContentPane().getLayout();
        cL.show(mF.getContentPane(), "startCard");

        // Name und Bild zuruecksetzen
        nameInput.setText(null);
        imageInputLabel.setIcon(null);
        imgShadow.setVisible(false);

    }

    /**
     * Abspielen des HoverSound wenn die Maus den Button entered
     * @param evt
     */
    private void mainMenuButtonMouseEntered(java.awt.event.MouseEvent evt) {
        try {
            mF.playHoverSound();
        } catch (BasicPlayerException ex) {
            Logger.getLogger(PlayPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * - Abspielen des SelectSound wenn der Button gedrueckt wird
     * - Setzen des Bildpfades und Spielername im Hero Objekt
     * - IntroPanels und die dazugehoerigen Timer starten
     * 
     * @param evt
     */
    private void playButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	try {
            mF.playSelectSound();
        } catch (BasicPlayerException ex) {
            Logger.getLogger(PlayPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    	
        // Name und Bildpfad des Spielers speichern
        heroName = nameInput.getText();
    	
        // Bild des Helden setzen, wenn ausgewaehlt
    	try{
            heroPicPath = selectedFile.toString();
            mF.getHero().setImageFile(heroPicPath);
            mF.getPanelTop().setAvatar(heroPicPath);
        }
    	catch(Exception ex){
    		System.out.println("Kein Bild ausgewählt");
    	}
    	
        // wenn der Name gesetzt ist mit dem Spiel starten
        if (!heroName.equals("")){
        	// Name des Helden setzen
        	mF.getHero().setName(heroName);
        	
            // Timer und somit starten der einzelnen Animationen zur Anzeige der verschiedenen JTextAreas im IntroScreen
        	introPanel.colorReset();
        	introPanel.allTextAreasNonVisible();
        	introPanel.start();
            // IntroScreen wird gestartet
            CardLayout cL = (CardLayout) mF.getContentPane().getLayout();
            cL.show(mF.getContentPane(), "introCard");
            // NameInput und Bild zuruecksetzen
            nameInput.setText("");
            imageInputLabel.setIcon(null);
            imgShadow.setVisible(false);
            
        } else {
            JOptionPane.showMessageDialog(null, "Bitte gib Deinen Namen ein, dann kanns losgehen!", "Ohooo...", JOptionPane.ERROR_MESSAGE,new ImageIcon("src/repository/images/icon.png"));
        }
    }

    /**
     * Abspielen des HoverSound wenn die Maus den Button entered
     * @param evt
     */
    private void playButtonMouseEntered(java.awt.event.MouseEvent evt) {
        try {
            mF.playHoverSound();
        } catch (BasicPlayerException ex) {
            Logger.getLogger(PlayPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Oeffnen des JFileChooser bei Klick auf das Plus Symbol
     * @param evt
     */
    private void plusButtonActionPerformed(java.awt.event.ActionEvent evt) {
        loadFile();
    }

    /**
     * Oeffnen der UseWebcam Klasse um ein neues Foto mittels Webcam aufzunehmen, sofern eine Webcam vorhanden ist und
     * die dazugehoerigen Treiber installiert sind.
     * 
     * @param evt
     */
    private void cameraButtonActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String[] nargs = {"-option", "Parameter"};
            Method methode = Class.forName("businesslogic.UseWebcam").getMethod("main", nargs.getClass());
            methode.invoke(null, new Object[]{nargs});
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(PlayPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(PlayPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PlayPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PlayPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(PlayPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(PlayPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Methode zum Einladen eines neuen Avatarbild mittels JFileChooser und einem ImageFilter
     * der die Erstauswahl auf .jpg Dateien festlegt
     */
    public void loadFile() {
        // Initialisieren des FileChoosers
        fc = new JFileChooser();
        // Filter erzeugen, so dass nur .jpg Files geladen werden koennen
        MyFileFilter imgFilter = new MyFileFilter("Bilder (.jpg)", new String[]{".jpg"});
        fc.setFileFilter(imgFilter);
        // nur eine Datei kann geladen werden
        fc.setMultiSelectionEnabled(false);
        // Aktueller Pfad festlegen
        fc.setCurrentDirectory(new File("/Users/"));

        int retVal = fc.showOpenDialog(panelBackground);
        if (retVal == JFileChooser.APPROVE_OPTION) {

            selectedFile = fc.getSelectedFile();

            // Neues Bild aus der selektierten Datei erhalten
            java.awt.Image image = java.awt.Toolkit.getDefaultToolkit().getDefaultToolkit().createImage(selectedFile.toString());

            // Bild zu 183 x 183 skalieren
            Image imageNewScale = image.getScaledInstance(183, 183, 183);
            ImageIcon avatar = new ImageIcon(imageNewScale);

            // ImageIcon in Label setzen
            imageInputLabel.setIcon(avatar);
            imgShadow.setVisible(true);
        }
    }

    public String getHeroName() {
        return heroName;
    }

    public String getHeroPicPath() {
        return heroPicPath;
    }
    
    private javax.swing.JButton cameraButton;
    private javax.swing.JPanel fcPanel;
    private javax.swing.JLabel imageInputLabel;
    private javax.swing.JLabel imgShadow;
    private javax.swing.JLabel inputLabel;
    private javax.swing.JButton mainMenuButton;
    private javax.swing.JTextField nameInput;
    private javax.swing.JLabel panelBackground;
    private javax.swing.JButton playButton;
    private javax.swing.JButton plusButton;
}
