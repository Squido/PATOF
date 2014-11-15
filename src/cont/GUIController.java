package cont;

import java.util.List;

import javax.swing.ImageIcon;

import GUI.GameWindow;
import GUI.MainMenu;
import GUI.OptionsWindow;

public class GUIController {
	private GameWindow gameWindow;
	private MainMenu mainMenu;
	private OptionsWindow optionsWindow;
	private GameController game;
	private boolean placementFlag;
	private boolean aimingFlag;
	private boolean endedTurn[];

	public GUIController() {
		showNewMainMenu();
	}

	void showNewMainMenu() {
		mainMenu = new MainMenu(this);
		mainMenu.setVisible(true);
	}

	public void hideMenu() {
		mainMenu.setVisible(false);
	}

	public void showMenu() {
		mainMenu.setVisible(true);
	}

	public void showNewGameWindow() {
		game = new GameController(this);
		gameWindow = new GameWindow(this);
		gameWindow.setVisible(true);
		placementFlag = true;
		aimingFlag = false;
		endedTurn = new boolean[2];
		endedTurn[0] = false;
		endedTurn[1] = false;
	}

	public void closeGameWindow() {
		gameWindow.setVisible(false);
		gameWindow = null;
	}

	public void showNewOptionsWindow() {
		optionsWindow = new OptionsWindow(this);
		optionsWindow.setVisible(true);
	}

	public void closeOptionsWindow() {
		optionsWindow.setVisible(false);
		optionsWindow = null;
	}

	public List<String> getCardsImage(int player) {
		return game.getImages(player);
	}

	public GameController getGameController() {
		return game;
	}

	public boolean isPlacementFlag() {
		return placementFlag;
	}

	public void setPlacementFlag(boolean f) {
		placementFlag = f;
	}

	public boolean isAimingFlag() {
		return aimingFlag;
	}

	public void setAimingFlag(boolean f) {
		aimingFlag = f;
	}

	public boolean isEndedTurn(int player) {
		return endedTurn[player];
	}

	public void setEndedTurn(int player, boolean f) {
		endedTurn[player] = f;
	}

	public int getActivePlayer() {
		return game.getActivePlayer();
	}

	public void changeActivePlayer() {
		game.changeActivePlayer();
	}

	public void removeCardFromBoard(int x, int y) {
		gameWindow.getPane().remove(gameWindow.getBoard()[x][y].getCard());
		gameWindow.repaint();
	}

	public void killCardFromBoard(int x, int y) {
		// przesun¹æ na graveyard -> zrób sobie liste kart na cmentarzu w modelu bo zapewne sie przyda...
		//np jak jakis spell czy cus bedzie pozwalal przywrocic karte z cmentarza
		//tak ja tez sobie zrobie tak¹ liste bo zapewne bede chcial wyswietlac karty z cmentarza po kliknieciu go ale juz teraz mi sie nie chce
		gameWindow.getGraveyard(gameWindow.getBoard()[x][y].getCard().getOwner()).setIcon(new ImageIcon(gameWindow.getBoard()[x][y].getCard().getImage()));
		removeCardFromBoard(x, y);
		
	}
	
	public void addPlacingListenersInHand(int index){
		//dodaje mozliwosc polozenia karty z reki na stol		
		gameWindow.getCard(index).addPlacingListeners();
	}
	public void addPlacingListenerOnBoard(int x,int y){
		//dodaje mozliwosc przesuniecia karty bedacej na stole
		gameWindow.getBoard()[x][y].getCard().addPlacingListeners();
	}
	public void addAimListener(int x,int y){
		//dodaje mozliwosc wybrania karty ktora chcemy atakowac
		//i w zasadzie zamiast tych funkcji mozna zrobic funkcje biorace listy
		//ale w javie nie ma klasy pair i nie chcialo mi sie jej pisac		
		gameWindow.getBoard()[x][y].getCard().addAimListener();
	}
	public void changePlayer(){
		//usuwa wszystkie listenery i odkrywa/zakrywa karty odpowiednich graczy
		gameWindow.removeListeners();
	}

}
