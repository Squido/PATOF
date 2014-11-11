package GUI;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import cont.GUIController;

public class GameWindow extends JFrame {
	GUIController controller;
	private JPanel board = new JPanel();
	private Hand myHand;
	private Hand oppHand;
	private Card myCardsOnBoard[][];
	private Card oppCardsOnBoard[][];

	
	public GameWindow(GUIController gui){
		
		controller = gui;
		this.boardGenerator();
		myHand = new Hand(true,new ArrayList<Card>());
		oppHand = new Hand(true,new ArrayList<Card>());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setBounds(0, 0, 1024, 768);
		this.setVisible(true);
		
		
		board.setBackground(Color.LIGHT_GRAY);
		oppHand.setBackground(Color.GRAY);
		oppHand.setPreferredSize(new Dimension(1024, 768/7+20));
		
		
		this.add(oppHand, BorderLayout.BEFORE_FIRST_LINE);
		this.add(myHand, BorderLayout.AFTER_LAST_LINE);
		this.add(board);
		
	}
	private void boardGenerator(){
		myCardsOnBoard = new Card[2][5];
		oppCardsOnBoard = new Card[2][5];
		for(int i=0;i<2;i++){
			for(int j=0;j<5;j++){
				myCardsOnBoard[i][j]=new Card();
				myCardsOnBoard[i][j].setBounds(200+120*j,768/7+25+115*i,78,110);
				myCardsOnBoard[i][j].setBackground(Color.WHITE);
				getContentPane().add(myCardsOnBoard[i][j]);
			}
		}
		for(int i=0;i<2;i++){
			for(int j=0;j<5;j++){
				oppCardsOnBoard[i][j] = new Card();
				oppCardsOnBoard[i][j].setBounds(200+120*j,768/7+260+115*i,78,110);
				oppCardsOnBoard[i][j].setBackground(Color.WHITE);
				getContentPane().add(oppCardsOnBoard[i][j]);
			}
		}
	}
	
}
