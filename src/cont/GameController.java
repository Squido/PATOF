package cont;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import mod.*;

public class GameController {
	private Player[] players;
	private Board board;
	private int turnCounter;
	private List<Activation> b_activations;
	private List<Activation> e_activations;
	private GUIController gui;
	private int activePlayer;
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

	}

	private void bothDraw() {
		players[0].draw();
		players[1].draw();
	}

	public void beginTurn() {
		turnCounter++;
		// beginning of turn activations
		for (Activation i : b_activations) {
			i.activate();
		}

		// draw
		bothDraw();
		gui.setPlacementFlag(true);
	}

	// placement
	public void addCardToBoard(int x, int y, Card card) {
		board.addCard(x, y, card);
	}

	public boolean isPlaceable(Card card, int owner) {
		return players[owner].getHero().getMaxHp()
				- players[owner].getHero().getHp() > card.getCost();
	}

	// fight
	public void attack(int x_att, int y_att, int x_def, int y_def) {
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

	public void endTurn() {
		// end turn activations
		for (Activation i : e_activations) {
			i.activate();
		}
	}

	public int getTurnNo() {
		return turnCounter;
	}

	public void draw(int player) {
		players[player].draw();
	}

	public Card removeCardFromHand(int player, int card) {
		return players[player].getHand().removeCard(card);
	}

	public void addCardToHand(int player, Card card) {
		players[player].getHand().addCard(card);
	}

	public Hand getHand(int player) {
		return players[player].getHand();
	}

	public List<String> getImages(int player) {
		return players[player].getHand().getCardImages();
	}

	public Card removeCardFromBoard(int x, int y) {
		return board.removeCard(x, y);
	}

	public void addB(Activation a) {
		b_activations.add(a);
	}

	public void addE(Activation a) {
		e_activations.add(a);
	}

	public void removeActivation(Activation a) {
		b_activations.remove(a);
		e_activations.remove(a);
	}

	public int getActivePlayer() {
		return activePlayer;
	}

	public void changeActivePlayer() {
		activePlayer = (activePlayer + 1) % 2;
	}
}
