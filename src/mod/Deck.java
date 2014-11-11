package mod;

import java.util.*;

public class Deck {
	private List<Card> cards;
	private Player owner;

	public Deck(List<Card> cards, Player owner) {
		this.owner = owner;
		this.cards = cards;
		shuffle();
	}

	private void shuffle() {
		int size = cards.size();
		List<Card> tmp = new ArrayList<Card>();
		for (int i = 0; i < size; i++) {
			int rnd_index = (int) (Math.random() * cards.size());
			tmp.add(cards.get(rnd_index));
			cards.remove(cards.get(rnd_index));
		}
		cards = tmp;
	}

	public Card draw() {
		return cards.remove(0);
	}

	public List<Card> draw(int x) {
		if (x > (7 - owner.getHand().getSize())) {
			x = (7 - owner.getHand().getSize());
		}
		List<Card> tmp = new ArrayList<Card>();
		for (int i = 0; i < x; i++) {
			tmp.add(cards.remove(0));
		}
		return tmp;
	}

	public void addCard(Card card) {
		cards.add(card);
		shuffle();
	}
}
