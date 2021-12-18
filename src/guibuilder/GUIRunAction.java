package guibuilder;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUIRunAction {

	// Remove all panels from the frame
		public static GUIFrame removePanel(ArrayList<JPanel> tpGUIPanel, GUIFrame tfFrame) throws Exception {
			GUIFrame lfFrame = tfFrame;
			ArrayList<JPanel> lpGUIPanel = tpGUIPanel;
			
			for (int panelx = 0; panelx < lpGUIPanel.size(); panelx++) {
				lpGUIPanel.get(panelx).setVisible(false);
				lfFrame.remove(lpGUIPanel.get(panelx));
			}
			
			return lfFrame;
		}
		
		// Restore previous save file
		public static ArrayList<ArrayList<String>> restorePreviousSaveFile(ArrayList<ArrayList<String>> talPreviousSaveFile, ArrayList<ArrayList<String>> talImportedSaveFile) {
			ArrayList<ArrayList<String>> lalPreviousSaveFile = talPreviousSaveFile;
			ArrayList<ArrayList<String>> lalImportedSaveFile = talImportedSaveFile;
			lalImportedSaveFile = lalPreviousSaveFile;
			
			return lalImportedSaveFile;
		}
		
		// Remove all panels from the frame
		public static GUIFrame removeFrame(GUIFrame tfFrame) throws Exception {
			GUIFrame lfFrame = tfFrame;
			lfFrame.dispose();
			
			return lfFrame;
		}

}
