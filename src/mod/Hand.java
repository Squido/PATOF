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
	
	public Card removeCard(Card card){
		cards.remove(card);
		return card;
	}
	
	public Card removeCard(int id){
		return cards.remove(id);
	}

	public void addCards(List<Card> list) {
		for(Card i : list){
			cards.add(i);
		}
	}
	
	public int getSize(){
		return cards.size();
	}

	public List<String> getCardImages() {
		List<String> images = new ArrayList<String>();
		for(Card i : cards){
			images.add(i.getImgPath());
		}
		return images;
	}
}