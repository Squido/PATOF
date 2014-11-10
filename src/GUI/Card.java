package GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class Card extends JPanel {
	Image image;
	public Card(Image img){
		super();
		image=img;
		this.setPreferredSize(new Dimension(100, 140));
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(image,0,0,null);
		
	}
}
