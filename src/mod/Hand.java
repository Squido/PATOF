package mod;

import java.util.*;

public class Hand {
	private List<Card> cards;
	
	public Hand(){
		cards = new ArrayList<Card>();
	}
	
	public void addCard(Card card){
		cards.add(card);
	}
	
	public void removeCard(Card card){
		cards.remove(card);
	}
	
	public void removeCard(int id){
		cards.remove(id);
	}
}
