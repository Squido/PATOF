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
	private JPanel card; 
	private JPanel card2; 
	
	public GameWindow(GUIController gui){
		try{
		card = new Card(ImageIO.read(new File("graphics/s_border.jpg")));
		}catch(Exception e){}
		try{
		card2 = new Card(ImageIO.read(new File("graphics/s_border.jpg")));
		}catch(Exception e){}
		controller = gui;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBackground(Color.white);
		this.setLayout(new BorderLayout());
		this.setBounds(0, 0, 1024, 768);
		this.setVisible(true);
		
		hand.setBackground(Color.GRAY);
		hand.setPreferredSize(new Dimension(1024, 768/5));
		board.setBackground(Color.LIGHT_GRAY);
		opphand.setBackground(Color.GRAY);
		opphand.setPreferredSize(new Dimension(1024, 768/5));
		
		hand.add(card);
		hand.add(card2);
		
		this.add(opphand, BorderLayout.BEFORE_FIRST_LINE);
		this.add(hand, BorderLayout.AFTER_LAST_LINE);
		this.add(board);
		
	}
	
}
