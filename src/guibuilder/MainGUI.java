package guibuilder;

import javax.swing.SwingUtilities;

public class MainGUI {

	public static String guiFilePath			= "";
	public static String guiSaveFile			= "";
	public static GUIRender guiRender			= new GUIRender();
	
	public static void main(String[] args) {
		String guiContentFile 		= "";
		
		guiContentFile			 	= "testGUI.csv";
		guiSaveFile					= "";
		guiFilePath					= "c:/GUIBuilder/";
		guiRender.setFontSize(14);
		guiRender.setFont("SanSerif");
		guiRender.invokeGUI(guiFilePath+guiContentFile, guiSaveFile);
	}
}
