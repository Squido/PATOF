package GUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;

import cont.GUIController;

public class GameWindow extends JFrame {
	private JLayeredPane pane;
	private GUIController controller;
	private Field CardsOnBoard[][];
	private ArrayList<ArrayList<Card>> handCard = new ArrayList<ArrayList<Card>>();
	private JButton myGraveyard;
	private JButton myDeck;
	private JButton oppGraveyard;
	private JButton oppDeck;
	private JButton endTurn;
	private int activePlayer;

	
	public GameWindow(GUIController gui){
		activePlayer=0;
		pane = getLayeredPane();
		setResizable(false);
		controller = gui;
		handCard.add(new ArrayList<Card>());
		handCard.add(new ArrayList<Card>());		
		this.boardGenerator();
		setStartingHand();
		this.myHandGenerator();
		this.oppHandGenerator();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setBounds(0, 0, 1024, 768);
		this.setVisible(true);
		setBackground(Color.LIGHT_GRAY);
		endTurn = new JButton("endTurn");
		endTurn.setBounds(900, 300, 100, 50);
		add(endTurn);
	}
	
	private void boardGenerator(){
		CardsOnBoard = new Field[4][5];
		for(int i=0;i<2;i++){
			for(int j=0;j<5;j++){
				CardsOnBoard[i][j]=new Field();
				CardsOnBoard[i][j].setBounds(200+120*j,768/7+25+115*i,78,110);
				add(CardsOnBoard[i][j]);
			}
		}
		for(int i=0;i<2;i++){
			for(int j=0;j<5;j++){
				CardsOnBoard[i+2][j] = new Field();
				CardsOnBoard[i+2][j].setBounds(200+120*j,768/7+260+115*i,78,110);
				add(CardsOnBoard[i+2][j]);
			}
		}
	}
	private void myHandGenerator(){
		myGraveyard = new JButton("");
		try{
			Image img = ImageIO.read(new File("graphics/reverse.jpg"));
			myGraveyard.setIcon(new ImageIcon(img));
		}catch(Exception e){}
		myGraveyard.setBounds(920,625,78,110);
		this.add(myGraveyard);
		
		myDeck = new JButton("");
		try{
			Image img = ImageIO.read(new File("graphics/reverse.jpg"));
			myDeck.setIcon(new ImageIcon(img));
		}catch(Exception e){}
		myDeck.setBounds(840,625,78,110);
		this.add(myDeck);
		generateHand(0);
	}
	private void oppHandGenerator(){
		oppGraveyard = new JButton("");
		try{
			Image img = ImageIO.read(new File("graphics/reverse.jpg"));
			oppGraveyard.setIcon(new ImageIcon(img));
		}catch(Exception e){}
		oppGraveyard.setBounds(920,10,78,110);
		this.add(oppGraveyard);
		
		oppDeck = new JButton("");
		try{
			Image img = ImageIO.read(new File("graphics/reverse.jpg"));
			oppDeck.setIcon(new ImageIcon(img));
		}catch(Exception e){}
		oppDeck.setBounds(840,10,78,110);
		this.add(oppDeck);
		generateHand(1);
	}
	
	private void generateHand(int player){
		for(int i=0;i<handCard.size();i++){
			pane.remove(handCard.get(player).get(i));
		}
		for(int i=0;i<handCard.get(player).size();i++){
			handCard.get(player).get(i).setBounds(380-((handCard.get(player).size()-1)*40)+80*i,615*((player+1)%2)+10,78,110);
			pane.add(handCard.get(player).get(i));
		}
	}

	
	public void addCard(Card card){
		handCard.get(activePlayer).add(card);
		generateHand(activePlayer);
		repaint();
	}
	
	
	public Card getCard(int index){
		Card card = handCard.get(activePlayer).get(index).getCard();
		getLayeredPane().remove(handCard.get(activePlayer).get(index));
		handCard.get(activePlayer).remove(index);
		repaint();
		return card;
	}
	
	
	public boolean putCardOnField(int x,int y,Card card){
		for(int i=0;i<2;i++){
			for(int j=0;j<5;j++){
				if(x>=200+120*j && x<=200+120*j+78 && y>=768/7+260+115*i && y<=768/7+260+115*i+110 && CardsOnBoard[i+2][j].isEmpty()){
					if(card.isInField()){
						card.getField().pickCard();
					}
					card.removeListeners();
					CardsOnBoard[i+2][j].putCard(card);
					card.setBounds(CardsOnBoard[i+2][j].getX(),CardsOnBoard[i+2][j].getY(),78,110);
					if(card.isInHand()){
						controller.getGameController().removeCardFromHand(activePlayer, handCard.get(activePlayer).indexOf(card));
						getCard(handCard.get(activePlayer).indexOf(card));
						changeActivePlayer();
						getLayeredPane().add(card);
						card.setInHand(false);
					}
					return true;
				}
			}
		}
		return false;
	}
	
	public ArrayList<Card> getMyHand(){
		return handCard.get(activePlayer);
	}
	
	private void setStartingHand(){
		List<String> images = controller.getGameController().getImages(0);
		for(String img : images){
			System.out.println(img);
			handCard.get(0).add(new Card(this,img));
		}
		images = controller.getGameController().getImages(1);
		for(String img : images){
			Card c = new Card(this,img);
			c.changeActivePlayer();
			handCard.get(1).add(c);
		}
	}
	public JLayeredPane getPane(){
		return pane;
	}
	public void changeActivePlayer(){
		for(Card card : handCard.get(activePlayer)){
			card.removeListeners();
		}
		activePlayer=(activePlayer+1)%2;
		for(Card card : handCard.get(activePlayer)){
			card.addListeners();
		}
	}
	
}
