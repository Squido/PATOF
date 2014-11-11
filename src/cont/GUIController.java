package cont;

import GUI.GameWindow;
import GUI.MainMenu;
import GUI.OptionsWindow;

public class GUIController {
	GameWindow gameWindow;
	MainMenu mainMenu;
	OptionsWindow optionsWindow;
	private static Turn turn;
	
	public GUIController(){
		showNewMainMenu();
	}
	
	void showNewMainMenu(){
		mainMenu = new MainMenu(this);
		mainMenu.setVisible(true);
	}
	public void hideMenu(){
		mainMenu.setVisible(false);
	}
	public void showMenu(){
		mainMenu.setVisible(true);
	}
	
	public void showNewGameWindow(){
		gameWindow = new GameWindow(this);
		gameWindow.setVisible(true);
		setTurn(new Turn());
	}
	public void closeGameWindow(){
		gameWindow.setVisible(false);
		gameWindow=null;
	}
	public void showNewOptionsWindow(){
		optionsWindow = new OptionsWindow(this);
		optionsWindow.setVisible(true);
	}
	public void closeOptionsWindow(){
		optionsWindow.setVisible(false);
		optionsWindow = null;
	}

	public static Turn getTurn() {
		return turn;
	}

	public static void setTurn(Turn turn) {
		GUIController.turn = turn;
	}
}
