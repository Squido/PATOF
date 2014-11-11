package GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Card extends JPanel {
	Image image;
	public Card(){
		super();
		try{
			image = ImageIO.read(new File("graphics/reverse.jpg"));
		}catch(Exception e){}
		this.setPreferredSize(new Dimension(78, 110));
		repaint();
	}
	public void addCard(mod.Card card){
		//image=card.getImage();
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(image,0,0,null);
		
	}
}
