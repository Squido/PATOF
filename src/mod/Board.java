package mod;

public class Board {
	private Card cards[][];

	Board() {
		cards = new Card[4][5];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 5; j++) {
				cards[i][j] = null;
			}
		}
	}

	public void addCard(int x, int y, Card card) {
		cards[x][y] = card;
	}

	public Card removeCard(int x, int y) {
		Card card = cards[x][y];
		cards[x][y] = null;
		return card;
	}
}
