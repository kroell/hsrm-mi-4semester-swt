package userInterface;

import java.awt.CardLayout;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javazoom.jlgui.basicplayer.BasicPlayerException;

/**
 * SettingPanelGame.java
 * 
 * Panel zum Aendern der Tastaturbelegungen im laufendem Spiel
 * 
 * @author Soeren Kroell, Stephanie Scholl, Mario Sigel, Ersin Yilmiz
 */
public class SettingPanelGame extends javax.swing.JPanel {

    private MainFrame mF;
    
    /**
     * Default Konstruktor
     */
    public SettingPanelGame() {
        initComponents();
    }
    
    /**
     * Angepasster Konstruktor mit Mitgabe des MainFrame
     * @param mF
     */
    public SettingPanelGame(MainFrame mF) {
        initComponents();
        this.mF = mF;
        
        // Look & Feel Windows bzw. Linux Anpasssungen
        mainMenuButton.setOpaque(false);
        mainMenuButton.setBorder (new javax.swing.border.EmptyBorder(0,0,0,0));
        mainMenuButton.setContentAreaFilled(false);
        mainMenuButton.setBackground(new Color (0,0,0,0));
        
        saveButton.setOpaque(false);
        saveButton.setBorder (new javax.swing.border.EmptyBorder(0,0,0,0));
        saveButton.setContentAreaFilled(false);
        saveButton.setBackground(new Color (0,0,0,0));
    }

    /**
     * Komponenten initialisieren
     */
    private void initComponents() {
        newForwardInput = new javax.swing.JTextField();
        newRightInput = new javax.swing.JTextField();
        newBackInput = new javax.swing.JTextField();
        newInteractionInput = new javax.swing.JTextField();
        newShootInput = new javax.swing.JTextField();
        newLeftInput = new javax.swing.JTextField();
        newQuitInput = new javax.swing.JTextField();
        newLayDownItem = new javax.swing.JTextField();
        newTakeItem = new javax.swing.JTextField();
        newSettingInput = new javax.swing.JTextField();
        newActiveItemInBackpack = new javax.swing.JTextField();
        newSkipItemInBackpack = new javax.swing.JTextField();
        messageLabel = new javax.swing.JLabel();
        saveButton = new javax.swing.JButton();
        mainMenuButton = new javax.swing.JButton();
        settingBackground = new javax.swing.JLabel();

        setLayout(null);
        add(newForwardInput);
        newForwardInput.setBounds(750, 450, 40, 30);
        newForwardInput.setOpaque(false);
        newForwardInput.setBorder(BorderFactory.createLineBorder(Color.white, 0));
        newForwardInput.setBackground(null);
        add(newRightInput);
        newRightInput.setBounds(750, 550, 40, 40);
        newRightInput.setOpaque(false);
        newRightInput.setBorder(BorderFactory.createLineBorder(Color.white, 0));
        newRightInput.setBackground(null);
        add(newBackInput);
        newBackInput.setBounds(750, 610, 40, 30);
        newBackInput.setOpaque(false);
        newBackInput.setBorder(BorderFactory.createLineBorder(Color.white, 0));
        newBackInput.setBackground(null);
        add(newInteractionInput);
        newInteractionInput.setBounds(1070, 450, 40, 30);
        newInteractionInput.setOpaque(false);
        newInteractionInput.setBorder(BorderFactory.createLineBorder(Color.white, 0));
        newInteractionInput.setBackground(null);
        add(newShootInput);
        newShootInput.setBounds(1070, 498, 40, 40);
        newShootInput.setOpaque(false);
        newShootInput.setBorder(BorderFactory.createLineBorder(Color.white, 0));
        newShootInput.setBackground(null);

        newLeftInput.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        add(newLeftInput);
        newLeftInput.setBounds(750, 500, 40, 40);
        newLeftInput.setOpaque(false);
        newLeftInput.setBorder(BorderFactory.createLineBorder(Color.white, 0));
        newLeftInput.setBackground(null);
        add(newQuitInput);
        newQuitInput.setBounds(1070, 610, 40, 30);
        newQuitInput.setOpaque(false);
        newQuitInput.setBorder(BorderFactory.createLineBorder(Color.white, 0));
        newQuitInput.setBackground(null);
        add(newLayDownItem);
        newLayDownItem.setBounds(880, 500, 40, 40);
        newLayDownItem.setOpaque(false);
        newLayDownItem.setBorder(BorderFactory.createLineBorder(Color.white, 0));
        newLayDownItem.setBackground(null);
        add(newTakeItem);
        newTakeItem.setBounds(880, 450, 40, 30);
        newTakeItem.setOpaque(false);
        newTakeItem.setBorder(BorderFactory.createLineBorder(Color.white, 0));
        newTakeItem.setBackground(null);
        add(newSettingInput);
        newSettingInput.setBounds(1070, 550, 40, 40);
        newSettingInput.setOpaque(false);
        newSettingInput.setBorder(BorderFactory.createLineBorder(Color.white, 0));
        newSettingInput.setBackground(null);

        newActiveItemInBackpack.setBorder(null);
        add(newActiveItemInBackpack);
        newActiveItemInBackpack.setBounds(880, 550, 40, 40);
        newActiveItemInBackpack.setOpaque(false);
        newActiveItemInBackpack.setBorder(BorderFactory.createLineBorder(Color.white, 0));
        newActiveItemInBackpack.setBackground(null);

        newSkipItemInBackpack.setBorder(null);
        add(newSkipItemInBackpack);
        newSkipItemInBackpack.setBounds(880, 610, 40, 30);
        newSkipItemInBackpack.setOpaque(false);
        newSkipItemInBackpack.setBorder(BorderFactory.createLineBorder(Color.white, 0));
        newSkipItemInBackpack.setBackground(null);

        messageLabel.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        messageLabel.setForeground(new java.awt.Color(255, 51, 51));
        add(messageLabel);
        messageLabel.setBounds(50, 730, 650, 30);

        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/repository/images/setting_save.png"))); // NOI18N
        saveButton.setBorder(null);
        saveButton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/repository/images/setting_save_hover.png"))); // NOI18N
        saveButton.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/repository/images/setting_save_hover.png"))); // NOI18N
        saveButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                saveButtonMouseEntered(evt);
            }
        });
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        add(saveButton);
        saveButton.setBounds(910, 730, 370, 50);

        mainMenuButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/repository/images/settingGame_zurueck.png"))); // NOI18N
        mainMenuButton.setBorder(null);
        mainMenuButton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/repository/images/settingGame_zurueck_hover.png"))); // NOI18N
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

        settingBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/repository/images/setting_background_final4.png"))); // NOI18N
        add(settingBackground);
        settingBackground.setBounds(0, 0, 1280, 800);
    }

    /**
     * Beim Klick auf den MainMenu Button wird das StartPanel angezeigt und das MessageLabel zurueckgesetzt
     * @param evt
     */
    private void mainMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        
        try {
            mF.playSelectSound();
        } catch (BasicPlayerException ex) {
            Logger.getLogger(PlayPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
       messageLabel.setText("");
       CardLayout cL = (CardLayout) mF.getContentPane().getLayout();
       cL.show(mF.getContentPane(), "gameCard");
        
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
     * Abspielen des HoverSound wenn die Maus den Button entered
     * @param evt
     */
    private void saveButtonMouseEntered(java.awt.event.MouseEvent evt) {
        try {
            mF.playHoverSound();
        } catch (BasicPlayerException ex) {
            Logger.getLogger(PlayPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Neue Tasturbelegung im SettingPanel anzeigen
     */
    public void makeEntries(){
        newRightInput.setText(String.valueOf(mF.getCommandKeys()[0]));
        newLeftInput.setText(String.valueOf(mF.getCommandKeys()[1]));
        newForwardInput.setText(String.valueOf(mF.getCommandKeys()[2]));   
        newBackInput.setText(String.valueOf(mF.getCommandKeys()[3]));
        
        if(mF.getCommandKeys()[4] == ' '){
        	newShootInput.setText(" space");
        }else{
        	newShootInput.setText(String.valueOf(mF.getCommandKeys()[4]));
        } 
        newInteractionInput.setText(String.valueOf(mF.getCommandKeys()[5]));
        newTakeItem.setText(String.valueOf(mF.getCommandKeys()[6]));
        newActiveItemInBackpack.setText(String.valueOf(mF.getCommandKeys()[7]));
        newLayDownItem.setText(String.valueOf(mF.getCommandKeys()[8]));
        newSkipItemInBackpack.setText(String.valueOf(mF.getCommandKeys()[9]));
        newQuitInput.setText(String.valueOf(mF.getCommandKeys()[13]));
        newSettingInput.setText(String.valueOf(mF.getCommandKeys()[14]));
    }
    
    /**
     * Beim Klick auf den Speichern Button werden die neuen Tastaturbelegung in einem
     * charArray gespeichert und Erfolg/Misserfolg im messageLabel angezeigt
     * @param evt
     */
    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {
        
    	char [] newKeySettings = new char[15];
    	 
        if (newRightInput.getText() != null){
        	newKeySettings[0] = newRightInput.getText().toCharArray()[0];
        }      
        if (newLeftInput.getText() != null){
        	newKeySettings[1] = newLeftInput.getText().toCharArray()[0];
        }        
        if (newForwardInput.getText() != null){
        	newKeySettings[2] = newForwardInput.getText().toCharArray()[0];
        }    
        if (newBackInput.getText() != null){
        	newKeySettings[3] = newBackInput.getText().toCharArray()[0];
        }
        if (newShootInput.getText() != null){
        	newKeySettings[4] = newShootInput.getText().toCharArray()[0];
        }        
        if (newInteractionInput.getText() != null){
        	newKeySettings[5] = newInteractionInput.getText().toCharArray()[0];
        }
        if (newTakeItem.getText() != null){
        	newKeySettings[6] = newTakeItem.getText().toCharArray()[0];
        }
        if (newActiveItemInBackpack.getText() != null){
        	newKeySettings[7] = newActiveItemInBackpack.getText().toCharArray()[0];
        }
        if (newLayDownItem.getText() != null){
        	newKeySettings[8] = newLayDownItem.getText().toCharArray()[0];
        }        
        if (newSkipItemInBackpack.getText() != null){
        	newKeySettings[9] = newSkipItemInBackpack.getText().toCharArray()[0];
        }
        
        newKeySettings[10] = '1';
        newKeySettings[11] = '2';		
        newKeySettings[12] = '3';
        
        if (newQuitInput.getText() != null){
        	newKeySettings[13] = newQuitInput.getText().toCharArray()[0];
        }      
        if (newSettingInput.getText() != null){
        	newKeySettings[14] = newSettingInput.getText().toCharArray()[0];
        }        

        boolean ok = true;
        
        // Abgleich ob doppelte Tasten ausgewaehlt wurden
        for(int i=0; i<15; i++){
        	for(int j=0; j<15; j++){
        		if(i!=j){
        			if(newKeySettings[i]==newKeySettings[j]){
        				ok = false;
        			}
        		}
        	}	
        }
        	
        if(ok){
	        messageLabel.setForeground(Color.green);
	        messageLabel.setText("Einstellungen gespeichert!");
	        mF.setCommandKeys(newKeySettings);
        }else{
            messageLabel.setForeground(Color.red);
            messageLabel.setText("Fehler beim Speichern! - Doppelte Belegung. ");        	
        }
    }

    private javax.swing.JButton mainMenuButton;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JTextField newActiveItemInBackpack;
    private javax.swing.JTextField newBackInput;
    private javax.swing.JTextField newForwardInput;
    private javax.swing.JTextField newInteractionInput;
    private javax.swing.JTextField newLayDownItem;
    private javax.swing.JTextField newLeftInput;
    private javax.swing.JTextField newQuitInput;
    private javax.swing.JTextField newRightInput;
    private javax.swing.JTextField newSettingInput;
    private javax.swing.JTextField newShootInput;
    private javax.swing.JTextField newSkipItemInBackpack;
    private javax.swing.JTextField newTakeItem;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel settingBackground;
}
