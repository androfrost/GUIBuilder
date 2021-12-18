package guibuilder;

import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.InputMap;
import javax.swing.KeyStroke;

public class GUISaveArrayListKeyListener implements KeyListener{

	String lcKeyPressed, lcComponent = "Not Given";
	KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
	private String lcSearchValue, lcRelatedRowID, lcRowSaveValue, lcAction, lcNextPanel, lcFieldType, lcRunAction;
	private GUIRender render = new GUIRender();
	
	public GUISaveArrayListKeyListener(){
	}

	public GUISaveArrayListKeyListener(String tcComponent){
		lcComponent = tcComponent;
	}
	
	public GUISaveArrayListKeyListener(String tcComponent, ArrayList<ArrayList<String>> talSearchedList, String tcSaveRow, int tiColumn, int tiSaveColumn, int tiArrayID, String tcAction, 
			String tcNextPanel, String tcFieldType) {
		lcComponent						= tcComponent;
		//lalSearchedList 				= talSearchedList;
		lcSearchValue 					= tcSaveRow;
		//liSearchColumn 					= tiColumn;
		//liSaveColumn 					= tiSaveColumn;
		lcFieldType						= tcFieldType;
		//liArrayID						= tiArrayID;
		lcAction						= tcAction;
		lcNextPanel						= tcNextPanel;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		try {
			runActionPressed(e);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	public void runActionPressed(KeyEvent e) throws Exception {
		int keyCode = e.getKeyCode();
		char c = e.getKeyChar();
		setKeyPressed(keyCode);
		
		if (keyCode == KeyEvent.VK_TAB) {
			manager.focusNextComponent();
			System.out.println(String.valueOf((int) c) + " " + lcKeyPressed);
		}
		switch (lcKeyPressed) {
		case "Enter":
			System.out.println(String.valueOf((int) c) + " " + lcKeyPressed);
			switch (lcComponent) {
			case "JTEXTAREA":
				manager.focusNextComponent();
				break;
			case "JTEXTFIELD":
				manager.focusNextComponent();
				break;
			case "JPASSWORDFIELD":
				manager.focusNextComponent();
				break;
			case "JBUTTON":
				render.runAction(lcNextPanel, lcSearchValue, lcAction, lcFieldType);
			}
		}
	}
	
	public void setKeyPressed(int tiKeyCode) {
		lcKeyPressed = KeyEvent.getKeyText(tiKeyCode);
	}
	
	public String getKeyPressed() {
		String lcReturn;
		
		lcReturn = lcKeyPressed;
		
		return lcReturn;
	}
	
	public InputMap disableKeysGrpA(InputMap timInputMap){
		String[] lasKeyList = {"Enter", "Tab"};
		timInputMap = disableKeys(timInputMap, lasKeyList);
		
		return timInputMap;
	}
	
	private InputMap disableKeys(InputMap timInputMap, String[] tacDisableKeys) {
        String[] lacKeys = tacDisableKeys;
        for (String key : lacKeys) {
        	timInputMap.put(KeyStroke.getKeyStroke(key), "none");
        }
        
        return timInputMap;
    }

}
