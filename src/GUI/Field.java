package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class Field extends JPanel {
	private Card card;
//	private Image image;
	public Field(){
		super();
		this.setBackground(Color.GRAY);
		this.setPreferredSize(new Dimension(78, 110));
		repaint();
	}
	public Field(Card cd){
		this();
		card=cd;
	}
	public void putCard(Card cd){
		card=cd;
		//this.setVisible(false);
		card.setField(this);
//		image=card.getImage();
	}
	public Card pickCard(){
		Card c=card;
		card.setField(null);
		card=null;
		//this.setVisible(true);
		return c;
	}
	public Card getCard(){
		return card;
	}
	public boolean isEmpty(){
		return card==null;
	}
	
	
}
