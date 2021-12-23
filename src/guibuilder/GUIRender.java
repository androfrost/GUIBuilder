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
			// Option for using different Data Control systems, more can be added later
			switch(nDataSource) {
			case 0:		// Normal GUI Data Control system using CSVs for storing.
				DataControl.guiSetup(GUISavePath, GUIFile);
				DataControl.guiSaveSetup(GUISavePath, GUISaveFile);
				DataControl.guiOptions();
				break;
			default:
				System.out.println("No Data Source type chosen!");
			}
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
	
	// Set Font value
	public void setFont(String tcFont) {
		cFont = tcFont;
	}
	
	// Get current Font value
	public String getFont() {
		return cFont;
	}

}
