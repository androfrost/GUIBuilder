package guibuilder;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class GUIFrame extends JFrame {

	public GUIFrame (String tcTitle, int tiHeight, int tiWidth, int tiXLoc, int tiYLoc) throws Exception {
		
		pack();
		setVisible(true);
		
		setTitle(tcTitle);
		setSize(tiHeight, tiWidth);
		setResizable(false);
		if (tiXLoc > 0 && tiYLoc > 0) {
			setLocation(tiXLoc,tiYLoc);
		}
		else {
			setLocationRelativeTo(null);		// Center
		}
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	

}
