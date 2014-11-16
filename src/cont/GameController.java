package cont;

import java.util.ArrayList;
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
	private boolean placementFlag, fightFlag;
	// do testow tylko
	private Deck deck0;
	private Deck deck1;
	private Card card;

	public GameController(GUIController controller) {
		gui = controller;
		board = new Board();
		turnCounter = 0;
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
		int whoEnded = -1, tmp = 0;
		placementFlag = true;
		while (placementFlag) {
			for (int i = 0; i < 20; i++) {
				if (board.getCard(i / 5, i % 5) != null) {
					tmp++;// liczenie kart na stole
				}
			}
			addPlacementListeners();
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < 20; i++) {
				if (board.getCard(i / 5, i % 5) != null) {
					tmp--;// jak ¿adna nie dosz³a, to koniec dla 1 gracza
				}
			}
			if (tmp == 0 && whoEnded == -1) {
				activePlayer = (activePlayer + 1) % 2;
				gui.changePlayer();
			} else {
				break;
			}
		}
		while (placementFlag) {
			tmp = 0;
			if (whoEnded == -1) {
				whoEnded = activePlayer;
			}
			for (int i = 0; i < 20; i++) {
				if (board.getCard(i / 5, i % 5) != null) {
					tmp++; // liczenie kart na stole
				}
			}
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < 20; i++) {
				if (board.getCard(i / 5, i % 5) != null) {
					tmp--;
				}
			}
			if (tmp != 0) { // jak ¿adna nie dosz³a, to koniec
				endPlacement();
			}
		}
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
		placementFlag = false;
		fightPhase();
	}

	public synchronized void addCardToBoard(int x, int y, Card card) {
		board.addCard(x, y, card);
	}

	public synchronized boolean isPlaceable(Card card, int owner) {
		return players[owner].getHero().getMaxHp()
				- players[owner].getHero().getHp() > card.getCost();
	}

	// fight
	private synchronized void fightPhase(){
		
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
	}

	public synchronized int getTurnNo() {
		return turnCounter;
	}

	public synchronized void draw(int player) {
		addCardToHand(player, players[player].draw());
	}

	private synchronized Card removeCardFromHand(int player, int card) {
		return players[player].getHand().removeCard(card);
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
