package guibuilder;
 
import javax.swing.SwingUtilities;

import guidatacontrol.DataControl;

public class GUIRender implements Runnable {

	static String GUIFile 		= "testGUI.csv";
	static String GUISaveFile 	= "testSave.csv";
	static String GUISavePath 	= "c:/GUIBuilder/";
	static String SavePath 		= "c:/GUIBuilder/";

	static String cFirstLine 	= "\"GUIID\",\"CONTAINERID\",\"PANELID\",\"FIELDTYPE\",\"TEXT\",\"WIDTH\",\"HEIGHT\",\"XLOC\",\"YLOC\",\"RELATEDID\",\"ARRAYID\",\"ACTION\",\"NEXTCONTAINERID\",\"LOADID\",\"KEY\"";
	static String cFont		 	= "Arial";
	static int nFontSize		= 16;
	static int nDataSource		= 0;

	// Sets files needed for the GUI and invokes the rendering of the GUI
	public void invokeGUI(String frameContentFile, String frameSaveFile) {
		if (!frameContentFile.isBlank())
			GUIFile = frameContentFile;
		if (!frameSaveFile.isBlank())
			GUISaveFile = frameSaveFile;
		SwingUtilities.invokeLater(new GUIRender());
	}

	@Override
	public void run() {

		DataControl.setFontSize(nFontSize);
		DataControl.setFont(cFont);
		
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
	
	
	/*
	 * Setters and Getters 
	 */
	
	// Set path of save file
	public void setSavePath(String tcSavePath) {
		SavePath = tcSavePath;
	}
	
	// Get Save path
	public String getSavePath() {
		return SavePath;
	}

	// Set path of GUI Save file
	public void setGUISavePath(String tcGUISavePath) {
		GUISavePath = tcGUISavePath;
	}
	
	// Get path of GUI Save file
	public String getGUISavePath() {
		return GUISavePath;
	}
	
	// Set Font value
	public void setFont(String tcFont) {
		cFont = tcFont;
	}
	
	// Get current Font value
	public String getFont() {
		return cFont;
	}
	
	// Set First Line value
	public void setFirstLine(String tcFirstLine) {
		cFirstLine = tcFirstLine;
	}
	
	// Get current First Line value
	public String getFirstLine() {
		return cFirstLine;
	}
	
	// Set Font Size value
	public void setFontSize(int tcFontSize) {
		nFontSize = tcFontSize;
	}
	
	// Get current Font Size value
	public int getFontSize() {
		return nFontSize;
	}
	
	// Set Data Source Identifier value
	public void setDataSource(int tcDataSource) {
		nDataSource = tcDataSource;
	}
	
	// Get current Data Source Identifier value
	public int getDataSource() {
		return nDataSource;
	}

}
