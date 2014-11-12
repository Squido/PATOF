package cont;

import GUI.GameWindow;
import GUI.MainMenu;
import GUI.OptionsWindow;

public class GUIController extends Thread {
	private GameWindow gameWindow;
	private MainMenu mainMenu;
	private OptionsWindow optionsWindow;
	private GameController game;
	
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
		game = new GameController();
		
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
	//TODO run()
}
