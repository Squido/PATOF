package cont;

import java.util.List;

import GUI.GameWindow;
import GUI.MainMenu;
import GUI.OptionsWindow;

public class GUIController {
	private GameWindow gameWindow;
	private MainMenu mainMenu;
	private OptionsWindow optionsWindow;
	private GameController game;
	private static boolean placementFlag;
	
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
		game = new GameController();
		gameWindow = new GameWindow(this);
		gameWindow.setVisible(true);
		
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
	public List<String> getCardsImage(int player){
		return game.getImages(player);
	}
	public GameController getGameController(){
		return game;
	}
	public boolean isPlacementFlag() {
		return placementFlag;
	}
	public static void setPlacementFlag(boolean f) {
		placementFlag = f;
	}
}
