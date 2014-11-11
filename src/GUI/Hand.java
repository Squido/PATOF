package GUI;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

public class Hand extends JPanel {
	JButton deck;
	JButton graveyard;
	List<Card> cardOnHand;
	boolean isMyHand;
	public Hand(boolean isMy,List<Card> cards){
		isMyHand=isMy;
		cards.add(new Card());
		cards.add(new Card());
		cardOnHand=cards;
		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(1024, 768/7+20));
		for(Card card : cardOnHand)this.add(card);
		graveyard = new JButton("grave");
		graveyard.setBounds(800, 0, 78, 110);
		this.add(graveyard);
		
		
	}
	
}
