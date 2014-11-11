package GUI;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;

import cont.GUIController;


public class MainMenu extends JFrame{
	GUIController controller;
	public MainMenu(GUIController gui){
		controller=gui;
		this.setTitle("PATOF - Menu");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBackground(Color.white);
		this.setBounds(0, 0, 1024, 768);
				
		JButton newGameButton = new JButton("New Game");
		JButton closeButton = new JButton("Close");
		JButton optionsButton = new JButton("Options");
		newGameButton.setBounds(1024*3/4,50,200,50);
		optionsButton.setBounds(1024*3/4,100,200,50);
		closeButton.setBounds(1024*3/4,150,200,50);
		
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		newGameButton.addActionListener(newGameAction());
		optionsButton.addActionListener(optionsAction());
		
		this.getContentPane().setLayout(null);
		this.getContentPane().add(optionsButton);
		this.getContentPane().add(newGameButton);
		this.getContentPane().add(closeButton);	
	}
	
	
	private ActionListener newGameAction(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.showNewGameWindow();
				controller.hideMenu();
			}
		};
	}
	
	private ActionListener optionsAction(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.showNewOptionsWindow();
				controller.hideMenu();
			}
		};
	}
}