package GUI;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;
import java.util.List;

public class Hand extends JPanel {
	private JButton deck;
	private JButton graveyard;
	private List<Card> cardOnHand;
	private Image img;
	private boolean isMyHand;
	public Hand(boolean isMy,List<Card> cards){
		setLayout(null);
		isMyHand=isMy;
		//
		cards.add(new Card());
		cards.add(new Card());
		cards.add(new Card());
		cards.add(new Card());
		cards.add(new Card());
		cards.add(new Card());
		cards.add(new Card());
		cards.add(new Card());
		cards.add(new Card());
		cards.add(new Card());
		//
		cardOnHand=cards;
		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(1024, 768/7+20));
		generateHand();
		graveyard = new JButton("");
		try{
			img = ImageIO.read(new File("graphics/reverse.jpg"));
			graveyard.setIcon(new ImageIcon(img));
		}catch(Exception e){}
		graveyard.setBounds(920,10,78,110);
		graveyard.setMargin(new Insets(0, 0, 0, 0));
		this.add(graveyard);
		
		deck = new JButton("");
		try{
			img = ImageIO.read(new File("graphics/reverse.jpg"));
			deck.setIcon(new ImageIcon(img));
		}catch(Exception e){}
		deck.setBounds(840,10,78,110);
		deck.setMargin(new Insets(0, 0, 0, 0));
		this.add(deck);
	}
	public void generateHand(){
		for(int i=0;i<cardOnHand.size();i++){
			cardOnHand.get(i).setBounds(380-((cardOnHand.size()-1)*40)+80*i,10,78,110);
			this.add(cardOnHand.get(i));
		}
	}
	public void addCard(Card card){
		cardOnHand.add(card);
		generateHand();
		repaint();
	}
	public void removeCard(int index){
		cardOnHand.remove(index);
		generateHand();
		repaint();
	}
	
}
