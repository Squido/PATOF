package cont;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import mod.*;

public class GameController {
	private static Player[] players;
	private static Board board;
	private static int turnCounter;
	private static List<Activation> b_activations;
	private static List<Activation> e_activations;

	// do testow tylko
	private Deck deck0;
	private Deck deck1;
	private Card card;

	public GameController() {
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

	private static void bothDraw() {
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
		GUIController.setPlacementFlag(true);
	}

	// placement
	public void addCardToBoard(int x, int y, Card card) {
		board.addCard(x, y, card);
	}

	// fight
	public void attack(int x_att, int y_att, int x_def, int y_def) {
		if(board.getCard(x_att, y_att).attack(board.getCard(x_def, y_def))){
			board.removeCard(x_def, y_def);
		}
	}

	public void endTurn() {
		// end turn activations
		for (Activation i : e_activations) {
			i.activate();
		}
	}

	public static int getTurnNo() {
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

	public static void addB(Activation a) {
		b_activations.add(a);
	}

	public static void addE(Activation a) {
		e_activations.add(a);
	}

	public static void removeActivation(Activation a) {
		b_activations.remove(a);
		e_activations.remove(a);
	}
}
