package GUI;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class BigCardDisplayer extends JPanel{
	Image image;
	GameWindow game;
	public BigCardDisplayer(Image img,GameWindow window){
		super();
		image = img;
		setOpaque(false);
		setBounds(0,0,1024,768);
		game = window;
		game.getPane().add(this);
		game.getPane().moveToFront(this);
		addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				game.getPane().remove((BigCardDisplayer)e.getSource());
				game.getPane().repaint();
			}
		});
	}
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(image,362,164,null);
	}

}
