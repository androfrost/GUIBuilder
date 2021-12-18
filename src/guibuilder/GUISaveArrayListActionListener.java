package guibuilder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTextField;

import genericprocedures.Chronology;
import genericprocedures.Search;
import genericprocedures.StringHandling;

public class GUISaveArrayListActionListener implements ActionListener {
	
	private ArrayList<ArrayList<String>> lalSearchedList = new ArrayList<ArrayList<String>> ();
	private int liSearchColumn, liSaveColumn, liRow, liArrayID, liRelatedRowID;
	private String lcSearchValue, lcRelatedRowID, lcRowSaveValue, lcAction, lcNextPanel, lcFieldType, lcRunAction;
	private GUIRender render = new GUIRender();
	private GUIRunAction runAction = new GUIRunAction();
	
	public GUISaveArrayListActionListener(ArrayList<ArrayList<String>> talSearchedList, String tcSaveRow, int tiColumn, int tiSaveColumn, int tiArrayID, String tcAction, 
			String tcNextPanel, String tcFieldType) {
		lalSearchedList 				= talSearchedList;
		lcSearchValue 					= tcSaveRow;
		liSearchColumn 					= tiColumn;
		liSaveColumn 					= tiSaveColumn;
		lcFieldType						= tcFieldType;
		liArrayID						= tiArrayID;
		lcAction						= tcAction;
		lcNextPanel						= tcNextPanel;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (lcAction.replaceAll("\\s", "").length() != 0) {
			try {
				render.runAction(lcNextPanel, lcSearchValue, lcAction, lcFieldType);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else {
			holdInArrayList();
			
		}
	}
	
	public String getRowValue () {
		return lcRowSaveValue;
	}
	
	/*
	 * Runs an action for the GUI line being processed
	 * tcRunAction 		- Action to be run
	 */
//	public void runAction(String tcRunAction) throws Exception {
//		lcRunAction = tcRunAction;
//		
//		switch (lcRunAction){
//		case "CONT":
//			//render.removePanel();
//			render.addPanel(lcNextPanel);
//			break;
//		case "HOLD":
//			holdInArrayList();
//			break;
//		case "CANCEL":
//			//render.restorePreviousSaveFile();
//			//render.removeFrame();
//			break;
//		case "EXIT":
//			//render.restorePreviousSaveFile();
//			System.exit(0);
//			break;
//		case "SAVEEXIT":
//			render.saveToFile("");
//			System.exit(0);
//			break;
//		case "HOLDSAVEEXIT":
//			holdInArrayList();
//			render.saveToFile("");
//			System.exit(0);
//			break;
//		case "SAVE":
//			render.saveToFile("");
//			break;
//		case "CANCELCONT":
//			//render.removePanel();
//			render.addPanel(lcNextPanel);
//			break;
//		case "HOLDSAVE":
//			holdInArrayList();
//			render.saveToFile("");
//			break;
//		case "SAVECONT":
//			render.saveToFile("");
//			//render.removePanel();
//			render.addPanel(lcNextPanel);
//			break;
//		case "HOLDSAVECONT":
//			holdInArrayList();
//			render.saveToFile("");
//			//render.removePanel();
//			render.addPanel(lcNextPanel);
//			break;
//		case "SAVEALLCONT":
//			holdInArrayList();
//			int panelIDCnt = 1;
//			for (int allPanel = 0; allPanel < panelIDCnt; allPanel++) {
//				holdInArrayList();
//			}
//			render.saveToFile("");
//			//render.removePanel();
//			render.addPanel(lcNextPanel);
//			break;
//		case "DATE":
//			String testDate = "12/25/2021";
//			Chronology.isDateFormat(testDate);
//			break;
//		}
//	}
	
	/*
	 * Places information in the save array 
	 */
	public void holdInArrayList() {
		int liComponentIDColumn;		// ID for Component from searched list
		String lcArrayFieldText;		// New value from Component Array 
		int liArrayIDFound;
		
		liRow 							= Search.searchArrayList(lalSearchedList, lcSearchValue, liSearchColumn);
		if (liRow == -1) {
			
			liRow = Search.searchHighestValue(lalSearchedList, 0);

			lalSearchedList.add(new ArrayList<String>());
			lalSearchedList.get(liRow).add(String.valueOf(liRow));
			lalSearchedList.get(liRow).add(lcSearchValue);
			lalSearchedList.get(liRow).add(" ");
			liArrayIDFound = Integer.valueOf(render.guiFindSavedInImported(0, liRow, 1, 10));
			lalSearchedList.get(liRow).add(String.valueOf(liArrayIDFound));
			
		}
		lcRelatedRowID 					= lalSearchedList.get(liRow).get(liSearchColumn);
		lcRowSaveValue					= lalSearchedList.get(liRow).get(liSaveColumn);
		
		liRelatedRowID 					= Integer.valueOf(StringHandling.setStringValue(lcRelatedRowID));
		liComponentIDColumn				= 0;		//Integer.valueOf(lalSearchedList.get(liRow).get(3));
		lcArrayFieldText				= render.guiGetSaveValue(lcFieldType, liComponentIDColumn, liRow, true);

		lalSearchedList.get(liRow).set(liSaveColumn, lcArrayFieldText);

		render.setSearchedList(lalSearchedList);
		System.out.println(lalSearchedList.get(liRow).get(liSaveColumn));
	}
}
