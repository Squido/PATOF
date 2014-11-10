package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import cont.GUIController;

public class OptionsWindow extends JFrame {
	GUIController controller;
	
	public OptionsWindow(GUIController gui){
		controller = gui;
		this.setTitle("PATOF - Options");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBackground(Color.white);
		this.setBounds(0, 0, 1024, 768);
		
		JButton saveButton = new JButton("Save");
		JButton cancelButton = new JButton("Cancel");
		saveButton.setBounds(200,600,200,50);
		cancelButton.setBounds(624,600,200,50);
		
		saveButton.addActionListener(saveAction());
		cancelButton.addActionListener(cancelAction());
		
		this.getContentPane().setLayout(null);
		this.getContentPane().add(saveButton);
		this.getContentPane().add(cancelButton);
		
	}
	
	private ActionListener saveAction(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.showMenu();
				controller.closeOptionsWindow();
			}
		};
	}
	
	private ActionListener cancelAction(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.showMenu();
				controller.closeOptionsWindow();
			}
		};
	}
}
