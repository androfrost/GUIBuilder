package guibuilder;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUIPanel extends JPanel {

	public GUIPanel (String tcTitle, int tiHeight, int tiWidth, int tiXLoc, int tiYLoc) throws Exception {
		
		setVisible(true);

		setSize(tiHeight, tiWidth);
		setLocation(tiXLoc,tiYLoc);
		//setBackground(getHSBColor(40, 240, 188));
	}
}