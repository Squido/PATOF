package cont;

import mod.Card;
import mod.Deck;
import mod.MinionCard;
import mod.Player;

public class GameController {
	private static Turn turn;
	private static Player[] players;

	// do testow tylko
	private Deck deck0;
	private Deck deck1;
	private Card card;

	public GameController() {
		turn = new Turn();
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
		//koniec testu
		players[0].drawStartingHand();
		players[1].drawStartingHand();
		turn.start();

	}

	public static Turn getTurn() {
		return turn;
	}

	public static Player getPlayer(int i) {
		return players[i];
	}
}
