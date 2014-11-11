package mod;

public class Player {
	private String name;
	private Hand hand;
	private Deck deck;
	
	public Player(String name, Hand hand, Deck deck){
		this.setName(name);
		this.setHand(hand);
		this.setDeck(deck);
	}
	
	public void draw(){
		hand.addCard(deck.draw());
	}

	public Hand getHand() {
		return hand;
	}

	public void setHand(Hand hand) {
		this.hand = hand;
	}

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
