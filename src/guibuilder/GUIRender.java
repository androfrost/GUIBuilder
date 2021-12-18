package guibuilder;
 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import fileprocessor.GeneralFileAction;
import fileprocessor.delimitedFileProcessor;
import genericprocedures.ArrayListGP;
import genericprocedures.Chronology;
import genericprocedures.Search;
import genericprocedures.Sort;
import genericprocedures.StringHandling;
import guidatacontrol.DataControl;

public class GUIRender implements Runnable {

	static String GUIFile 		= "testGUI.csv";
	static String GUISaveFile 	= "testSave.csv";
	static String GUISavePath 	= "c:/GUIBuilder/";
	static String SavePath 		= "c:/GUIBuilder/";

	static String cFont		 	= "Arial";
	static int nFontSize		= 16;
	static int nDataSource		= 0;

//	static GUIFrame gFrame; // Main JFrame
//	public static ArrayList<ArrayList<String>> importedGUIFile = new ArrayList<ArrayList<String>>();
//	public static ArrayList<ArrayList<String>> importedSaveFile;
//	public static ArrayList<ArrayList<String>> previousSaveFile;
//	public static ArrayList<JPanel> gGUIPanel = new ArrayList<JPanel>(); // Array of Labels to add to Panel
//	public static ArrayList<String> gGUIPanelContainerID = new ArrayList<String>(); // Array of Panels added
//	public static ArrayList<JTextField> gGUITextField = new ArrayList<JTextField>(); // Array of Text Fields to add to Panel
//	public static ArrayList<JFormattedTextField> gGUIDateTextField = new ArrayList<JFormattedTextField>(); // Array of Date Formatted Text Fields to
//																											// add to Panel
//	public static ArrayList<JFormattedTextField> gGUITimeTextField = new ArrayList<JFormattedTextField>(); // Array of Time Formatted Text Fields to
//																											// add to Panel
//	public static ArrayList<JTextArea> gGUITextArea = new ArrayList<JTextArea>(); // Array of Text Areas to add to Panel
//	public static ArrayList<String> gGUIText = new ArrayList<String>(); // Array of Text values in fields
//	public static ArrayList<JButton> gGUIButton = new ArrayList<JButton>(); // Array of Buttons to add to Panel
//	public static ArrayList<JComboBox> gGUIComboBox = new ArrayList<JComboBox>(); // Array of Combo Boxes to add to
//																					// Panel
//	public static ArrayList<JRadioButton> gGUIRadioButton = new ArrayList<JRadioButton>(); // Array of Combo Boxes to add to Panel
//	static String lcContainerIDRunning = "0";

	public void invokeGUI(String frameContentFile, String frameSaveFile) {
		if (!frameContentFile.isBlank())
			GUIFile = frameContentFile;
		if (!frameSaveFile.isBlank())
			GUISaveFile = frameSaveFile;
		SwingUtilities.invokeLater(new GUIRender());
	}

	@Override
	public void run() {

		try {
			//importedFile();
			DataControl.guiSetup(GUISavePath, GUIFile);
			DataControl.guiSaveSetup(GUISavePath, GUISaveFile);
			DataControl.guiOptions();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public void setSavePath(String tcSavePath) {
		SavePath = tcSavePath;
	}

	public void setGUISavePath(String tcGUISavePath) {
		GUISavePath = tcGUISavePath;
	}

//	public void importedFile() throws Exception {
//		// guiSetup(GUISavePath, GUIFile);
//		GeneralFileAction.createDirectory(GUISavePath);
//		GeneralFileAction.createFile(GUIFile,
//				"\"GUIID\",\"CONTAINERID\",\"PANELID\",\"FIELDTYPE\",\"TEXT\",\"WIDTH\",\"HEIGHT\",\"XLOC\",\"YLOC\",\"RELATEDID\",\"ARRAYID\",\"ACTION\",\"NEXTCONTAINERID\",\"LOADID\"");
//		importedGUIFile 	= delimitedFileProcessor.commaDelimitedFileReader(GUIFile);
//		importedGUIFile 	= Sort.arrayListSort(importedGUIFile, 0);
//		importedSaveFile 	= new ArrayList<ArrayList<String>>();
//		// End guiSetup
//
//		// guiSaveSetup(SavePath, GUISavePath)
//		try {
//			GeneralFileAction.createDirectory(SavePath);
//			GeneralFileAction.createFile(GUISaveFile, "\"SAVEID\",\"RELATEDID\",\"SAVEDATA\",\"ARRAYID\",\"GUIID\"");
//			importedSaveFile = delimitedFileProcessor.commaDelimitedFileReader(GUISaveFile);
//			delimitedFileProcessor.commaDelimitedFileWriter(GUISaveFile, importedSaveFile);
//			// importedSaveFile = Sort.arrayListAsciiSort(importedSaveFile, 0);
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		// End guiSaveSetup
//
//	}

//	// Types of GUI components
//	public void guiOptions() throws Exception {
//
//		gGUIPanel 				= new ArrayList<JPanel>(); // Array of Labels to add to Panel
//		gGUIPanelContainerID 	= new ArrayList<String>(); // Array of Panels added
//		gGUITextField 			= new ArrayList<JTextField>(); // Array of Text Fields to add to Panel
//		gGUIDateTextField 		= new ArrayList<JFormattedTextField>(); // Array of Text Fields to add to Panel
//		gGUITimeTextField 		= new ArrayList<JFormattedTextField>(); // Array of Text Fields to add to Panel
//		gGUITextArea 			= new ArrayList<JTextArea>(); // Array of Text Areas to add to Panel
//		gGUIText 				= new ArrayList<String>(); // Array of Text values in fields
//		gGUIButton 				= new ArrayList<JButton>(); // Array of Buttons to add to Panel
//		gGUIComboBox 			= new ArrayList<JComboBox>(); // Array of Combo Boxes to add to Panel
//		gGUIRadioButton 		= new ArrayList<JRadioButton>(); // Array of Radio Buttons to add to Panel
//
//		String lcAction = "";
//		String lcNextContainerID = "0";
//		String lcLoadID = "";
//		String lcWindow = "";
//
//		if (importedGUIFile.size() <= 1)
//			System.exit(0);
//
//		previousSaveFile = importedSaveFile;
//
//		for (int linex = 0; linex < importedGUIFile.size(); linex++) {
//			String lcContainerID 	= importedGUIFile.get(linex).get(1);
//			String lcPanelID 		= importedGUIFile.get(linex).get(2);
//			String lcFieldType 		= importedGUIFile.get(linex).get(3);
//			String lcText 			= importedGUIFile.get(linex).get(4);
//			String lcWidth 			= importedGUIFile.get(linex).get(5);
//			String lcHeight 		= importedGUIFile.get(linex).get(6);
//			String lcXLoc 			= importedGUIFile.get(linex).get(7);
//			String lcYLoc 			= importedGUIFile.get(linex).get(8);
//			String lcRelatedID 		= importedGUIFile.get(linex).get(9);
//			String lcArrayID 		= importedGUIFile.get(linex).get(10);
//
//			if (importedGUIFile.get(linex).size() >= 12)
//				lcAction 			= importedGUIFile.get(linex).get(11);
//			if (importedGUIFile.get(linex).size() >= 13)
//				lcNextContainerID 	= importedGUIFile.get(linex).get(12);
//			if (importedGUIFile.get(linex).size() >= 14)
//				lcLoadID 			= importedGUIFile.get(linex).get(13);
//			if (importedGUIFile.get(linex).size() >= 15)
//				lcWindow 			= importedGUIFile.get(linex).get(14);
//
//			// Checking to see if we need to load the value for a component from the save file
//			if (lcFieldType.contentEquals("JTEXTFIELD") || lcFieldType.contentEquals("JTEXTAREA") || lcFieldType.contentEquals("JDATETEXTFIELD")) { // ||
//																										// lcFieldType.contentEquals("JCOMBOBOX"))
//																										// {
//				int lnIsFound = Search.searchArrayList(importedSaveFile, lcArrayID, 3);
//				if (lnIsFound > 0) {
//					// lcText = guiGetSaveValue(lcFieldType,
//					// Integer.valueOf(StringHandling.setStringValue(lcArrayID)), 3);
//					// String lcSaveRelatedID = importedSaveFile.get(lnIsFound).get(1);
//					lcText = guiGetSaveValue(lcFieldType, 0, lnIsFound, false);
//				}
//			}
//			
//			// Sets up each element of the GUI depending on what is needed
//			if (lcContainerID.equals(lcContainerIDRunning)) {
//				switch (lcFieldType) {
//				case "JFRAME":
//					lcContainerIDRunning = lcContainerID;
//					guiJFrameOpt(lcText, lcWidth, lcHeight, lcXLoc, lcYLoc, lcRelatedID, lcArrayID);
//					lcContainerIDRunning = lcNextContainerID;
//					break;
//				case "JPANEL":
//					guiPanelOpt(lcPanelID, lcText, lcWidth, lcHeight, lcXLoc, lcYLoc); // , lcRelatedID, lcArrayID);
//					break;
//				case "JLABEL":
//					guiJLabelOpt(lcPanelID, lcText, lcWidth, lcHeight, lcRelatedID, lcNextContainerID);
//					break;
//				case "JTEXTFIELD":
//					guiJTextFieldOpt(lcPanelID, lcText, lcWidth, lcHeight, lcFieldType);
//					break;
//				case "JPASSWORDFIELD":
//					guiJPasswordFieldOpt(lcPanelID, lcText, lcWidth, lcHeight, lcFieldType);
//					break;
//				case "JDATETEXTFIELD": // Text field that uses a date format
//					guiDateJTextFieldOpt(lcPanelID, lcText, lcWidth, lcHeight, lcRelatedID, lcArrayID, lcAction,
//							lcNextContainerID, importedSaveFile, lcFieldType);
//					break;
//				case "JTIMETEXTFIELD": // Text field that uses a time format
//					guiTimeJTextFieldOpt(lcPanelID, lcText, lcWidth, lcHeight, lcRelatedID, lcArrayID, lcAction,
//							lcNextContainerID, importedSaveFile, lcFieldType);
//					break;
//				case "JTEXTAREA":
//					guiJTextAreaOpt(lcPanelID, lcText, lcWidth, lcHeight, lcFieldType);
//					break;
//				case "JBUTTON":
//					guiJButtonOpt(lcPanelID, lcText, lcWidth, lcHeight, lcRelatedID, lcArrayID, lcAction,
//							lcNextContainerID, importedSaveFile, lcFieldType);
//					break;
//				case "JCOMBOBOX":
//					guiJComboBoxOpt(lcPanelID, lcText, lcWidth, lcHeight, lcRelatedID, lcArrayID, lcLoadID, lcAction,
//							lcNextContainerID, importedSaveFile, lcFieldType);
//					break;
//				case "JHYPERBUTTON":
//					guiJHyperButtonOpt(lcPanelID, lcText, lcWidth, lcHeight, lcRelatedID, lcArrayID, lcAction,
//						lcNextContainerID, importedSaveFile, lcFieldType);
//					break;
//				}
//			}
//		}
//	}
//
//	public void guiJFrameOpt(String tcTitle, String tcWidth, String tcHeight, String tcX, String tcY,
//			String tcRelatedID, String tcContainerID) {
//
//		int tiHeight = Integer.valueOf(StringHandling.setStringValue(tcHeight));
//		int tiWidth = Integer.valueOf(StringHandling.setStringValue(tcWidth));
//		int tiXLoc = Integer.valueOf(StringHandling.setStringValue(tcX));
//		int tiYLoc = Integer.valueOf(StringHandling.setStringValue(tcY));
//
//		try {
//			gFrame = new GUIFrame(tcTitle, tiWidth, tiHeight, tiXLoc, tiYLoc);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	// Loads a new panel from given information
//	// tcPanelID -		Panel name to load
//	// tcTitle - 		Title of Frame for new panel
//	// tcWidth - 		Width of panel
//	// tcHeight - 		Height of panel
//	// tcX -	 		X location of panel
//	// tcY -	 		Y location of panel
//	public void guiPanelOpt(String tcPanelID, String tcTitle, String tcWidth, String tcHeight, String tcX, String tcY) {
//
//		int tiHeight = Integer.valueOf(StringHandling.setStringValue(tcHeight));
//		int tiWidth = Integer.valueOf(StringHandling.setStringValue(tcWidth));
//		int tiXLoc = Integer.valueOf(StringHandling.setStringValue(tcX));
//		int tiYLoc = Integer.valueOf(StringHandling.setStringValue(tcY));
//
//		try {
//			JPanel gPanel = new GUIPanel(tcTitle, tiWidth, tiHeight, tiXLoc, tiYLoc);
//			gPanel.setBackground(Color.LIGHT_GRAY);
//			gGUIPanel.add(gPanel);
//			int liID = Search.searchArrayList(gGUIPanelContainerID, tcPanelID);
//			if (liID == -1) {
//				gGUIPanelContainerID.add(tcPanelID);
//				liID = gGUIPanel.size() - 1;
//			}
//			gFrame.setTitle(tcTitle);  			//Gives Frame a new title based on new panel
//			gFrame.add(gGUIPanel.get(liID));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	public void guiJLabelOpt(String tcPanelID, String tcLabelText, String tcWidth, String tcHeight, String tcRelatedID,
//			String tcNextPanelID) {
//
//		int tiHeight = Integer.valueOf(StringHandling.setStringValue(tcHeight));
//		int tiWidth = Integer.valueOf(StringHandling.setStringValue(tcWidth));
//		int tiContainerID = Search.searchArrayList(gGUIPanelContainerID, tcPanelID);
//
//		JLabel gLabel = new JLabel(tcLabelText);
//		gLabel.setPreferredSize(new Dimension(tiWidth, tiHeight));
//		gLabel.setHorizontalTextPosition(gLabel.CENTER);
//		gLabel.setFont(new Font(cFont, Font.PLAIN, nFontSize));
//		if (tiContainerID >= gGUIPanel.size() - 1) {
//			gGUIPanel.get(tiContainerID).add(gLabel);
//		} else {
//			// gGUIPanel.get(tiContainerID).set();
//		}
//	}
//
//	public void guiJTextFieldOpt(String tcPanelID, String tcLabelText, String tcWidth, String tcHeight,
//			String lcFieldType) {
//
//		int tiHeight = Integer.valueOf(StringHandling.setStringValue(tcHeight));
//		int tiWidth = Integer.valueOf(StringHandling.setStringValue(tcWidth));
//		int tiContainerID = Search.searchArrayList(gGUIPanelContainerID, tcPanelID);
//
//		JTextField gTextField = new JTextField(tcLabelText);
//		gTextField.setPreferredSize(new Dimension(tiWidth, tiHeight));
//		gTextField.addKeyListener(new GUISaveArrayListKeyListener(lcFieldType));
//		gGUITextField.add(gTextField);
//		gGUIPanel.get(tiContainerID).add(gGUITextField.get(gGUITextField.size() - 1));
//	}
//	
//	public void guiJPasswordFieldOpt(String tcPanelID, String tcLabelText, String tcWidth, String tcHeight,
//			String lcFieldType) {
//
//		int tiHeight = Integer.valueOf(StringHandling.setStringValue(tcHeight));
//		int tiWidth = Integer.valueOf(StringHandling.setStringValue(tcWidth));
//		int tiContainerID = Search.searchArrayList(gGUIPanelContainerID, tcPanelID);
//
//		JPasswordField gPasswordField = new JPasswordField(tcLabelText);
//		gPasswordField.setEchoChar('*');
//		gPasswordField.setPreferredSize(new Dimension(tiWidth, tiHeight));
//		gPasswordField.addKeyListener(new GUISaveArrayListKeyListener(lcFieldType));
//		gGUITextField.add(gPasswordField);
//		gGUIPanel.get(tiContainerID).add(gGUITextField.get(gGUITextField.size() - 1));
//	}
//
//	public void guiDateJTextFieldOpt(String tcPanelID, String tcLabelText, String tcWidth, String tcHeight,
//			String tcSaveRow, String tcArrayID, String tcAction, String tcNextPanel,
//			ArrayList<ArrayList<String>> tasSaveFile, String tcFieldType) {
//
//		int tiHeight = Integer.valueOf(StringHandling.setStringValue(tcHeight));
//		int tiWidth = Integer.valueOf(StringHandling.setStringValue(tcWidth));
//		int tiContainerID = Search.searchArrayList(gGUIPanelContainerID, tcPanelID);
//		int liArrayID = Integer.valueOf(StringHandling.setStringValue(tcArrayID));
//		String lcNextPanel = StringHandling.setStringValue(tcNextPanel);
//		String lcFieldType = tcFieldType;
//		Date date = new Date();
//		DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
//		tcLabelText = format.format(date);
//		JFormattedTextField gFormTextField = new JFormattedTextField(tcLabelText);
//
//		gFormTextField.setPreferredSize(new Dimension(tiWidth, tiHeight));
//		//gFormTextField.setValue(new SimpleDateFormat("MM/dd/yyyy"));
//		//gFormTextField.setColumns(6);
//		gFormTextField.addInputMethodListener(null);
//		gFormTextField.addKeyListener(new GUISaveArrayListKeyListener(lcFieldType));
//		gFormTextField.addActionListener(new GUISaveArrayListActionListener(tasSaveFile, tcSaveRow, 1, 2, liArrayID,
//				tcAction, lcNextPanel, tcFieldType));
//		gGUIDateTextField.add(gFormTextField);
//		gGUIPanel.get(tiContainerID).add(gGUIDateTextField.get(gGUIDateTextField.size() - 1));
//	}
//	
//	public void guiTimeJTextFieldOpt(String tcPanelID, String tcLabelText, String tcWidth, String tcHeight,
//			String tcSaveRow, String tcArrayID, String tcAction, String tcNextPanel,
//			ArrayList<ArrayList<String>> tasSaveFile, String tcFieldType) {
//
//		int tiHeight = Integer.valueOf(StringHandling.setStringValue(tcHeight));
//		int tiWidth = Integer.valueOf(StringHandling.setStringValue(tcWidth));
//		int tiContainerID = Search.searchArrayList(gGUIPanelContainerID, tcPanelID);
//		int liArrayID = Integer.valueOf(StringHandling.setStringValue(tcArrayID));
//		String lcNextPanel = StringHandling.setStringValue(tcNextPanel);
//		Date date = new Date();
//		DateFormat format = new SimpleDateFormat("hh:mm");
//		tcLabelText = format.format(date);
//		JFormattedTextField gFormTextField = new JFormattedTextField(tcLabelText);
//
//		gFormTextField.setPreferredSize(new Dimension(tiWidth, tiHeight));
//		//gFormTextField.setColumns(5);
//		gFormTextField.addKeyListener(new GUISaveArrayListKeyListener());
//		gFormTextField.addActionListener(new GUISaveArrayListActionListener(tasSaveFile, tcSaveRow, 1, 2, liArrayID,
//				tcAction, lcNextPanel, tcFieldType));
//		gGUITimeTextField.add(gFormTextField);
//		gGUIPanel.get(tiContainerID).add(gGUITimeTextField.get(gGUITimeTextField.size() - 1));
//	}
//
//	public void guiJTextAreaOpt(String tcPanelID, String tcLabelText, String tcWidth, String tcHeight,
//			String lcFieldType) {
//
//		int tiHeight = Integer.valueOf(StringHandling.setStringValue(tcHeight));
//		int tiWidth = Integer.valueOf(StringHandling.setStringValue(tcWidth));
//		int tiContainerID = Search.searchArrayList(gGUIPanelContainerID, tcPanelID);
//		String tcFieldType = lcFieldType;
//		JTextArea gTextArea = new JTextArea(tcLabelText);
//
//		gTextArea.setPreferredSize(new Dimension(tiWidth, tiHeight));
//		gTextArea.setLineWrap(true);
//		gTextArea.setWrapStyleWord(true);
//		GUISaveArrayListKeyListener GUISaveKey = new GUISaveArrayListKeyListener(tcFieldType);
//		gTextArea.addKeyListener(GUISaveKey);
//		gTextArea.setInputMap(JTextArea.WHEN_FOCUSED, GUISaveKey.disableKeysGrpA(gTextArea.getInputMap()));
//		gGUITextArea.add(gTextArea);
//		gGUIPanel.get(tiContainerID).add(gGUITextArea.get(gGUITextArea.size() - 1));
//	}
//
//	// Creates a Button and controls how it functions
//	public void guiJButtonOpt(String tcPanelID, String tcLabelText, String tcWidth, String tcHeight, String tcSaveRow,
//			String tcArrayID, String tcAction, String tcNextPanel, ArrayList<ArrayList<String>> tasSaveFile,
//			String tcFieldType) {
//
//		int tiHeight = Integer.valueOf(StringHandling.setStringValue(tcHeight));
//		int tiWidth = Integer.valueOf(StringHandling.setStringValue(tcWidth));
//		int tiContainerID = Search.searchArrayList(gGUIPanelContainerID, tcPanelID);
//		int liArrayID = Integer.valueOf(StringHandling.setStringValue(tcArrayID));
//		String lcNextPanel = StringHandling.setStringValue(tcNextPanel);
//
//		JButton gButton = new JButton(tcLabelText);
//		gButton.setPreferredSize(new Dimension(tiWidth, tiHeight));
//		gButton.addActionListener(new GUISaveArrayListActionListener(tasSaveFile, tcSaveRow, 1, 2, liArrayID, tcAction,
//				lcNextPanel, tcFieldType));
//		gButton.addKeyListener(new GUISaveArrayListKeyListener(tcFieldType, tasSaveFile, tcSaveRow, 1, 2, liArrayID, tcAction,
//				lcNextPanel, tcFieldType));
//		gGUIButton.add(gButton);
//		gGUIPanel.get(tiContainerID).add(gGUIButton.get(gGUIButton.size() - 1));
//	}
//
//	// Creates a Combo Box and controls how it functions
//	public void guiJComboBoxOpt(String tcPanelID, String tcDropDownFile, String tcWidth, String tcHeight,
//			String tcSaveRow, String tcArrayID, String lcLoadID, String tcAction, String tcNextPanel,
//			ArrayList<ArrayList<String>> tasSaveFile, String tcFieldType) throws Exception {
//
//		int tiHeight = Integer.valueOf(StringHandling.setStringValue(tcHeight));
//		int tiWidth = Integer.valueOf(StringHandling.setStringValue(tcWidth));
//		int tiContainerID = Search.searchArrayList(gGUIPanelContainerID, tcPanelID);
//		int liRelatedID = Integer.valueOf(StringHandling.setStringValue(tcSaveRow));
//		int liArrayID = Integer.valueOf(StringHandling.setStringValueSetMin(tcArrayID, "1"));  	// Sets the min, but no lower than 1
//		int liSelectedIndex = Search.searchArrayList(importedSaveFile, tcSaveRow, 1);
//		int liLoadID = Integer.valueOf(StringHandling.setStringValueSetMin(lcLoadID, "1"));
//		String lcNextPanel = StringHandling.setStringValue(tcNextPanel);
//
//		// gButton.addActionListener( new GUISaveArrayListActionListener( tasSaveFile,
//		// tcSaveRow, 1, 2, liArrayID, tcAction, lcNextPanel, tcFieldType ) );
//
//		if (liSelectedIndex < 0) {
//			liSelectedIndex = Search.searchHighestValue(importedSaveFile, 0);
//
//			importedSaveFile.add(new ArrayList<String>());
//
//			importedSaveFile.get(liSelectedIndex).add(String.valueOf(liSelectedIndex));
//			importedSaveFile.get(liSelectedIndex).add(tcSaveRow);
//			importedSaveFile.get(liSelectedIndex).add("0");
//			if (tcArrayID.isEmpty())
//				importedSaveFile.get(liSelectedIndex).add("0");
//			else
//				importedSaveFile.get(liSelectedIndex).add(tcArrayID);
//		}
//
//		String[] lcDropList = ArrayListGP.dropDownAL(GUISavePath + tcDropDownFile, liSelectedIndex, liLoadID);
//
//		JComboBox gComboBox = new JComboBox<String>(lcDropList);
//		gComboBox.setPreferredSize(new Dimension(tiWidth, tiHeight));
//		gComboBox.addKeyListener(new GUISaveArrayListKeyListener());
//		gComboBox.addActionListener(new GUISaveArrayListActionListener(tasSaveFile, tcSaveRow, 1, 2, liArrayID,
//				tcAction, lcNextPanel, tcFieldType));
//		// gComboBox.setSelectedIndex(liDropDownIndex);
//
//		gGUIComboBox.add(gComboBox);
//		gGUIPanel.get(tiContainerID).add(gGUIComboBox.get(gGUIComboBox.size() - 1));
//	}
//	
//	// Creates a button that looks similar to a HyperLink
//	public void guiJHyperButtonOpt(String tcPanelID, String tcLabelText, String tcWidth, String tcHeight, String tcSaveRow,
//			String tcArrayID, String tcAction, String tcNextPanel, ArrayList<ArrayList<String>> tasSaveFile,
//			String tcFieldType) {
//
//		int tiHeight = Integer.valueOf(StringHandling.setStringValue(tcHeight));
//		int tiWidth = Integer.valueOf(StringHandling.setStringValue(tcWidth));
//		int tiContainerID = Search.searchArrayList(gGUIPanelContainerID, tcPanelID);
//		int liArrayID = Integer.valueOf(StringHandling.setStringValue(tcArrayID));
//		String lcNextPanel = StringHandling.setStringValue(tcNextPanel);
//
//		JButton gButton = new JButton(tcLabelText);
//		gButton.setPreferredSize(new Dimension(tiWidth, tiHeight));
//		gButton.setBorderPainted(false);
//		gButton.setOpaque(false);
//		gButton.setBackground(Color.WHITE);
//		gButton.setForeground(Color.BLUE);
//		gButton.addActionListener(new GUISaveArrayListActionListener(tasSaveFile, tcSaveRow, 1, 2, liArrayID, tcAction,
//				lcNextPanel, tcFieldType));
//		gButton.addKeyListener(new GUISaveArrayListKeyListener(tcFieldType, tasSaveFile, tcSaveRow, 1, 2, liArrayID, tcAction,
//				lcNextPanel, tcFieldType));
//		gGUIButton.add(gButton);
//		gGUIPanel.get(tiContainerID).add(gGUIButton.get(gGUIButton.size() - 1));
//	}
//
//	  /*
//	  Runs an action for the GUI line being processed tcRunAction - Action to be
//	  run
//	  */
//	  
//	public void runAction(String tcNextPanel, String tcSearchValue, String tcRunAction, String tcArrayFieldText) throws Exception { 
//		String lcNextPanel = tcNextPanel;		// If process continues to a new panel, this references the new panel
//		String lcSearchValue = tcSearchValue, lcRunAction = tcRunAction, lcArrayFieldText = tcArrayFieldText;
//	  
//		switch (lcRunAction){ 
//		case "CONT": 
//			gFrame = GUIRunAction.removePanel(gGUIPanel, gFrame); 
//			addPanel(lcNextPanel);
//			break; 
//		case "HOLD":
//			holdInArrayList(lcSearchValue,1,2,lcRunAction);
//			break;
//		case "CANCEL":
//			importedSaveFile = GUIRunAction.restorePreviousSaveFile(previousSaveFile, importedSaveFile);
//			gFrame = GUIRunAction.removeFrame(gFrame);
//			break;
//		case "EXIT":
//			importedSaveFile = GUIRunAction.restorePreviousSaveFile(previousSaveFile, importedSaveFile);
//			System.exit(0);
//			break;
//		case "SAVEEXIT":
//			saveToFile(""); 
//			System.exit(0); 
//			break; 
//		case "HOLDSAVEEXIT":
//			holdInArrayList(lcSearchValue,1,2,lcRunAction);
//			saveToFile("");
//			System.exit(0);
//			break;
//		case "SAVE":
//			saveToFile("");
//			break;
//		case "CANCELCONT":
//			gFrame = GUIRunAction.removePanel(gGUIPanel, gFrame); 
//			addPanel(lcNextPanel);
//			break;
//		case "HOLDSAVE":
//			holdInArrayList(lcSearchValue,1,2,lcRunAction);
//			saveToFile("");
//			break;
//		case "SAVECONT":
//			saveToFile("");
//			gFrame = GUIRunAction.removePanel(gGUIPanel, gFrame); 
//			addPanel(lcNextPanel);
//			break;
//		case "HOLDSAVECONT":
//			holdInArrayList(lcSearchValue,1,2,lcRunAction);
//			saveToFile("");
//			gFrame = GUIRunAction.removePanel(gGUIPanel, gFrame); 
//			addPanel(lcNextPanel);
//			break;
//		case "SAVEALLCONT":
//			int nCnt = importedSaveFile.size()-1;
//			String lcGUIID;
//			String lcArrayID;
//			String lcFieldType;
//			for (int nLineX = 0; nLineX < importedGUIFile.size(); nLineX++) {
//				// Test to see if array line is part of the panel and the array ID is something that can be tested against (not blank)
//				lcArrayID = importedGUIFile.get(nLineX).get(10);
//				if (lcContainerIDRunning.contentEquals(importedGUIFile.get(nLineX).get(2)) && StringHandling.setStringValue(lcArrayID) != "0") {
//					nCnt 			= nCnt + 1;
//					//int nArrayID2 = Integer.valueOf(importedGUIFile.get(nLineX).get(9));
//					lcGUIID 		= importedGUIFile.get(nLineX).get(0);
//					lcFieldType 	= importedGUIFile.get(nLineX).get(3);
//					String lcValueHold = guiGetSaveValue(lcFieldType, Integer.valueOf(lcArrayID), 0, true);
//					importedSaveFile = setSaveFileValue(importedSaveFile, nCnt, " ", lcValueHold, lcGUIID);
//				}
//			}
//			saveToFile("");
//			gFrame = GUIRunAction.removePanel(gGUIPanel, gFrame); 
//			addPanel(lcNextPanel); 
//		case "DATE": 
//			String testDate = "12/25/2021";
//			Chronology.isDateFormat(testDate); 
//		} 
//	}
//	  
//	  /*
//	  Places information in the save array
//	  */
//	  
//	//GUISaveArrayListActionListener(tasSaveFile, tcSaveRow, 1, 2, liArrayID, tcAction, lcNextPanel, tcFieldType)
//	public void holdInArrayList(String tcSearchValue, int tiSearchColumn, int tiSaveColumn, String tcFieldType) { 
//		int liComponentIDColumn; 					// ID for Component from searched list 
//		int liSearchColumn = tiSearchColumn; 		// Column of array to search
//		int liSaveColumn = tiSaveColumn;			// Column of array to hold the value to save in
//		String lcSearchValue = tcSearchValue; 		// Value to search for in array 
//		String lcArrayFieldText;	// = tcArrayFieldText;	// New value from Component Array
//		String lcFieldType = tcFieldType;
//		int liArrayIDFound, liRelatedRowID;
//		String lcRelatedRowID, lcRowSaveValue, lcSavedImport;
//		boolean lIsBlank = false;
//		
//		int liRow = Search.searchArrayList(importedSaveFile, lcSearchValue, liSearchColumn);
//		if (liRow == -1) {		  
//			liRow = Search.searchHighestValue(importedSaveFile, 0);	
//		
//			importedSaveFile = setSaveFileValue(importedSaveFile, liRow, lcSearchValue, " ", " ");
//			
//			lcSavedImport = guiFindSavedInImported(0, liRow, 1, 10);
//			if (!(lcSavedImport.contentEquals("") || lcSavedImport.contentEquals(" "))) {
//				lIsBlank = true;
//				liArrayIDFound = Integer.valueOf(lcSavedImport);
//				importedSaveFile.get(liRow).set(3,String.valueOf(liArrayIDFound));
//			}
//		}
//		if (lIsBlank = false) {
//			lcRelatedRowID = importedSaveFile.get(liRow).get(liSearchColumn);
//			lcRowSaveValue = importedSaveFile.get(liRow).get(liSaveColumn);
//			  
//			liRelatedRowID = Integer.valueOf(StringHandling.setStringValue(lcRelatedRowID));
//			liComponentIDColumn = 0;
//			lcArrayFieldText = guiGetSaveValue(lcFieldType, liComponentIDColumn, liRow, true);
//			  
//			importedSaveFile.get(liRow).set(liSaveColumn, lcArrayFieldText);
//			  
//			setSearchedList(importedSaveFile);
//			System.out.println(importedSaveFile.get(liRow).get(liSaveColumn));
//		}
//	}
//	
//	public ArrayList<ArrayList<String>> setSaveFileValue (ArrayList<ArrayList<String>> alImportedSaveFile, int tiRow, String tcSearchValue, 
//			String tcSaveValue, String tcGUIID){
//		int liRow = tiRow;
//		String lcSearchValue = tcSearchValue, lcSaveValue = tcSaveValue;
//		
//		alImportedSaveFile.add(new ArrayList<String>());
//		alImportedSaveFile.get(liRow).add(String.valueOf(liRow));
//		alImportedSaveFile.get(liRow).add(lcSearchValue);
//		alImportedSaveFile.get(liRow).add(lcSaveValue);
//		alImportedSaveFile.get(liRow).add(" ");
//		alImportedSaveFile.get(liRow).add(tcGUIID);
//		
//		return alImportedSaveFile;
//	}
//
//	// Remove all panels from the frame
//	//public void removePanel() throws Exception {
//	//	for (int panelx = 0; panelx < gGUIPanel.size(); panelx++) {
//	//		gGUIPanel.get(panelx).setVisible(false);
//	//		gFrame.remove(gGUIPanel.get(panelx));
//	//	}
//	//}
//
//	// Restore previous save file
//	//public void restorePreviousSaveFile() {
//	//	importedSaveFile = previousSaveFile;
//	//}
//
//	// Remove all panels from the frame
//	//public void removeFrame() throws Exception {
//	//	gFrame.dispose();
//	//}
//
//	public void saveToFile(String tcSaveWriter) throws Exception {
//		String lcSaveWriter;
//		if (tcSaveWriter.contentEquals(""))
//			lcSaveWriter = GUISavePath + GUISaveFile;
//		else
//			lcSaveWriter = tcSaveWriter;
//
//		delimitedFileProcessor.delimitedFileWriter(lcSaveWriter, importedSaveFile, ",", "\"");
//	}
//
//	// Set next panel IDs to run and run the panels
//	public void addPanel(String tcNextPanelID) throws Exception {
//		lcContainerIDRunning = tcNextPanelID;
//		guiOptions();
//	}
//
//	public int determineArrayType() {
//		int liReturn = 0;
//
//		return liReturn;
//	}
//
//	// Function to get text that needs to be saved from
//	public String guiGetSaveValue(String tcFieldType, int tiComponentIDColumn, int tiSearchValue, boolean isText ) {
//		String lcText = "";
//		String lcFieldType = tcFieldType;
//
//		// If component has a related ID find out what it is linked to and save based on
//		// that instead
//		if (tiSearchValue > 0) {
//			lcFieldType = guiFindSavedInImported(tiComponentIDColumn, tiSearchValue, 1, 3);
//		}
//		switch (lcFieldType) {
//		case "JTEXTFIELD" :
//			if (isText)
//				lcText = StringHandling.strtran(gGUITextField.get(tiComponentIDColumn-1).getText(), ",", "");
//			else
//				lcText = importedSaveFile.get(tiSearchValue).get(2);
//			break;
//		case "JPASSWORDFIELD" :
//			if (isText)
//				lcText = StringHandling.strtran(gGUITextField.get(tiComponentIDColumn-1).getText(), ",", "");
//			else
//				lcText = importedSaveFile.get(tiSearchValue).get(2);
//			break;
//		case "JTEXTAREA":
//			if (isText)
//				lcText = StringHandling.strtran(gGUITextArea.get(tiComponentIDColumn-1).getText(), ",", "");
//			else
//				lcText = importedSaveFile.get(tiSearchValue).get(2);
//			break;
//		case "JCOMBOBOX":
//			if (isText)
//				lcText = String.valueOf(gGUIComboBox.get(tiComponentIDColumn-1).getSelectedIndex()); // getSelectedItem().toString();
//			else
//				lcText = importedSaveFile.get(tiSearchValue).get(2);
//			break;
//		case "JDATETEXTFIELD":
//			if (isText)
//				lcText = StringHandling.strtran(gGUIDateTextField.get(tiComponentIDColumn-1).getText(), ",", "");
//			else
//				lcText = importedSaveFile.get(tiSearchValue).get(2);
//			break;
//		case "JTIMETEXTFIELD":
//			if (isText)
//				lcText = StringHandling.strtran(gGUITimeTextField.get(tiComponentIDColumn-1).getText(), ",", "");
//			else
//				lcText = importedSaveFile.get(tiSearchValue).get(2);
//			break;
//		default:
//			if (isText)
//				lcText = "Nothing There, Code 231";
//		}
//
//		return lcText;
//	}
//
//	// Moves to row of the importedGUIFile where we need to process values based on
//	// the ArrayID of another row
//	public String guiFindSavedInImported(int tiComponentIDColumn, int tiSearchValue, int tiSaveColumn,
//			int tiImportedColumn) {
//		String lcRelatedID = ""; 		// Related ID from Save File to match to imported GUI File
//		int tiComponentGUIIDRow = 0;	// Row value searched from imported GUI File
//		String lcIDFound = ""; 			// Return ID Based on search information
//
//		//lcRelatedID = importedSaveFile.get(tiSaveRow).get(tiSaveColumn);
//		lcRelatedID = String.valueOf(tiSearchValue);
//		tiComponentGUIIDRow = Search.searchArrayList(importedGUIFile, lcRelatedID, tiComponentIDColumn);
//		if (tiComponentGUIIDRow > 0) {
//			lcIDFound = importedGUIFile.get(tiComponentGUIIDRow).get(tiImportedColumn);
//		}
//		return lcIDFound;
//	}
//
//	public void setSearchedList(ArrayList<ArrayList<String>> talSearchedList) {
//		importedSaveFile = talSearchedList;
//	}
//
}
