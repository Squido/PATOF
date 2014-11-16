package cont;

import java.util.LinkedList;
import java.util.List;

import mod.*;

public class GameController extends Thread {
	private Player[] players;
	private Board board;
	private int turnCounter, activePlayer;
	private List<Activation> b_activations;
	private List<Activation> e_activations;
	private GUIController gui;
	// do testow tylko
	private Deck deck0;
	private Deck deck1;
	private Card card;

	public GameController(GUIController controller) {
		gui = controller;
		board = new Board();
		turnCounter = 0;
		activePlayer = 0;
		b_activations = new LinkedList<Activation>();
		e_activations = new LinkedList<Activation>();

		// test
		players = new Player[2];
		players[0] = new Player("Squid");
		players[1] = new Player("Kyuub");

		deck0 = new Deck(players[0]);
		deck1 = new Deck(players[1]);
		card = new MinionCard(0);
		for (int i = 0; i < 20; i++) {
			deck0.addCard(card);
			deck1.addCard(card);
		}
		// koniec testu
		players[0].drawStartingHand();
		players[1].drawStartingHand();
	}
	
	public void run(){
		beginTurn();
	}

	private synchronized void bothDraw() {
		players[0].draw();
		players[1].draw();
	}

	private synchronized void beginTurn() {
		turnCounter++;
		// beginning of turn activations
		for (Activation i : b_activations) {
			i.activate();
		}

		// draw
		bothDraw();
		placingPhase();
	}

	// placement
	private synchronized void placingPhase() {
		gui.setEndPlacement(false);
		boolean changePlayer = true;
		while (gui.isEndPlacement()) {
			addPlacementListeners();
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (isPlaceable(players[activePlayer].getHand().getCard(gui.getFrom_y()), activePlayer)) {
				gui.addPlacementCardToBoard();
				board.addCard(gui.getTo_x(), gui.getTo_y(), players[activePlayer].getHand().removeCard(gui.getFrom_y()));
				if (gui.isEndPlacement() && changePlayer) {
					changePlayer = false;
					gui.setEndPlacement(false);
				} 
				if(changePlayer){
					activePlayer = (activePlayer + 1) % 2;
					gui.changePlayer();
				}
			} else {
				gui.returnPlacementCardToHand();
			}
		}
		endPlacement();
	}

	private synchronized void addPlacementListeners() {
		int tmp = players[activePlayer].getHand().getSize();
		for (int i = 0; i < tmp; i++) {
			gui.addPlacingListenersInHand(i);
		}
	}

	private synchronized void endPlacement() {
		activePlayer = (activePlayer + 1) % 2;
		gui.changePlayer();
		fightPhase();
	}

	private synchronized boolean isPlaceable(Card card, int owner) {
		return players[owner].getHero().getMaxHp()
				- players[owner].getHero().getHp() > card.getCost();
	}

	// fight
	private synchronized void fightPhase() {
		endTurn();
	}

	private synchronized void attack(int x_att, int y_att, int x_def, int y_def) {
		board.getCard(x_att, y_att).attack(board.getCard(x_def, y_def));
		if (board.getCard(x_att, y_att).getHp() <= 0) {
			board.removeCard(x_att, y_att);
			gui.killCardFromBoard(x_att, y_att);
		}
		if (board.getCard(x_def, y_def).getHp() <= 0) {
			board.removeCard(x_def, y_def);
			gui.killCardFromBoard(x_def, y_def);
		}
	}

	private synchronized void endTurn() {
		// end turn activations
		for (Activation i : e_activations) {
			i.activate();
		}
		beginTurn();
	}

	public synchronized int getTurnNo() {
		return turnCounter;
	}

	public synchronized void draw(int player) {
		addCardToHand(player, players[player].draw());
	}

	private synchronized void addCardToHand(int player, Card card) {
		players[player].getHand().addCard(card);
	}

	public synchronized Hand getHand(int player) {
		return players[player].getHand();
	}

	public synchronized List<String> getImages(int player) {
		return players[player].getHand().getCardImages();
	}

	public synchronized Card removeCardFromBoard(int x, int y) {
		return board.removeCard(x, y);
	}

	public synchronized void addB(Activation a) {
		b_activations.add(a);
	}

	public synchronized void addE(Activation a) {
		e_activations.add(a);
	}

	public synchronized void removeActivation(Activation a) {
		b_activations.remove(a);
		e_activations.remove(a);
	}

	public synchronized int getActivePlayer() {
		return activePlayer;
	}

	public synchronized Board getBoard() {
		return board;
	}
}
