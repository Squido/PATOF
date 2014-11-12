package GUI;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import cont.GUIController;

public class GameWindow extends JFrame {
	private GUIController controller;
	Field myCardsOnBoard[][];
	Field oppCardsOnBoard[][];
	private ArrayList<Card> myHandCard = new ArrayList<Card>();
	private ArrayList<Card> oppHandCard = new ArrayList<Card>();;
	private JButton graveyard;
	private JButton deck;

	
	public GameWindow(GUIController gui){
		setResizable(false);
		controller = gui;
		this.boardGenerator();
		this.HandsGenerator(true);
		this.HandsGenerator(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setBounds(0, 0, 1024, 768);
		this.setVisible(true);
		
		setBackground(Color.LIGHT_GRAY);
		
		addCard(new Card(this));
		addCard(new Card(this));
		addCard(new Card(this));
		addCard(new Card(this));
		addCard(new Card(this));
		
	}
	
	
	private void boardGenerator(){
		myCardsOnBoard = new Field[2][5];
		oppCardsOnBoard = new Field[2][5];
		for(int i=0;i<2;i++){
			for(int j=0;j<5;j++){
				oppCardsOnBoard[i][j]=new Field();
				oppCardsOnBoard[i][j].setBounds(200+120*j,768/7+25+115*i,78,110);
				add(oppCardsOnBoard[i][j]);
			}
		}
		for(int i=0;i<2;i++){
			for(int j=0;j<5;j++){
				myCardsOnBoard[i][j] = new Field();
				myCardsOnBoard[i][j].setBounds(200+120*j,768/7+260+115*i,78,110);
				add(myCardsOnBoard[i][j]);
			}
		}
	}
	private void HandsGenerator(boolean isMy){
		int y=0;
		ArrayList<Card> cards = oppHandCard;
		if(isMy){
			y+=615;
			cards = myHandCard;
		}
		graveyard = new JButton("");
		try{
			Image img = ImageIO.read(new File("graphics/reverse.jpg"));
			graveyard.setIcon(new ImageIcon(img));
		}catch(Exception e){}
		graveyard.setBounds(920,y+10,78,110);
		graveyard.setMargin(new Insets(0, 0, 0, 0));
		this.add(graveyard);
		
		deck = new JButton("");
		try{
			Image img = ImageIO.read(new File("graphics/reverse.jpg"));
			deck.setIcon(new ImageIcon(img));
		}catch(Exception e){}
		deck.setBounds(840,y+10,78,110);
		deck.setMargin(new Insets(0, 0, 0, 0));
		this.add(deck);
		generateHand();
		
	}
	private void generateHand(){
		for(int i=0;i<myHandCard.size();i++){
			this.remove(myHandCard.get(i));
		}
		for(int i=0;i<myHandCard.size();i++){
			myHandCard.get(i).setBounds(380-((myHandCard.size()-1)*40)+80*i,615+10,78,110);
			this.add(myHandCard.get(i));
		}
	}
	public void addCard(Card card){
		myHandCard.add(card);
		generateHand();
		repaint();
	}
	
	
	public Card getCard(int index){
		Card card = myHandCard.get(index).getCard();
		this.remove(myHandCard.get(index));
		myHandCard.remove(index);
		generateHand();
		repaint();
		return card;
	}
	
	
	public boolean putCardOnField(int x,int y,Card card){
		for(int i=0;i<2;i++){
			for(int j=0;j<5;j++){
				if(x>=200+120*j && x<=200+120*j+78 && y>=768/7+260+115*i && y<=768/7+260+115*i+110 && myCardsOnBoard[i][j].isEmpty()){
					if(card.isInField()){
						card.getField().pickCard();
					}
					myCardsOnBoard[i][j].putCard(card);
					card.setBounds(myCardsOnBoard[i][j].getX(),myCardsOnBoard[i][j].getY(),78,110);
					if(card.isInHand()){
						getCard(myHandCard.indexOf(card));
						getContentPane().add(card);
						card.setInHand(false);
					}
					return true;
				}
			}
		}
		return false;
	}
	
	public ArrayList<Card> getMyHand(){
		return myHandCard;
	}
	
}
