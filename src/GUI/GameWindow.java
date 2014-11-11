package GUI;
import java.awt.*;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

import cont.GUIController;

@SuppressWarnings("serial")
public class GameWindow extends JFrame {
	GUIController controller;
	private JPanel board = new JPanel();
	private JPanel hand = new JPanel();
	private JPanel opphand = new JPanel();
	private JPanel cardsOnBoard[][];
	private JPanel oppCardsOnBoard[][];

	
	public GameWindow(GUIController gui){
		
		controller = gui;
		cardsOnBoard = new JPanel[2][5];
		oppCardsOnBoard = new JPanel[2][5];
		for(int i=0;i<2;i++){
			for(int j=0;j<5;j++){
				cardsOnBoard[i][j]=new JPanel();
				cardsOnBoard[i][j].setBounds(200+120*j,768/5+150*i,100,140);
				cardsOnBoard[i][j].setBackground(Color.WHITE);
				getContentPane().add(cardsOnBoard[i][j]);
			}
		}
		for(int i=0;i<2;i++){
			for(int j=0;j<5;j++){
				oppCardsOnBoard[i][j] = new JPanel();
				oppCardsOnBoard[i][j].setBounds(200+120*j,768/5+300+150*i,100,140);
				oppCardsOnBoard[i][j].setBackground(Color.WHITE);
				getContentPane().add(oppCardsOnBoard[i][j]);
			}
		}
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setBounds(0, 0, 1024, 768);
		this.setVisible(true);
		
		hand.setBackground(Color.GRAY);
		hand.setPreferredSize(new Dimension(1024, 768/5));
		board.setBackground(Color.LIGHT_GRAY);
		opphand.setBackground(Color.GRAY);
		opphand.setPreferredSize(new Dimension(1024, 768/5));
		
		
		this.add(opphand, BorderLayout.BEFORE_FIRST_LINE);
		this.add(hand, BorderLayout.AFTER_LAST_LINE);
		this.add(board);
		
	}
	
}
