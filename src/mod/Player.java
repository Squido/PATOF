package mod;

public class Player {
	private String name;
	private Hand hand;
	private Deck deck;

	public Player(String name) {
		this.name = name;
	}

	public void addDeck(Deck deck) {
		this.deck = deck;
	}
	
	public void drawStartingHand(){
		hand = new Hand();
		hand.addCards(deck.draw(7));
	}
	public void draw() {
		hand.addCard(deck.draw());
	}

	public Hand getHand() {
		return hand;
	}

	public Deck getDeck() {
		return deck;
	}

	public String getName() {
		return name;
	}
}
