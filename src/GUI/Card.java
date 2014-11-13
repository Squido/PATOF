package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Card extends JPanel {
	private Image image;
	private GameWindow gameWindow;
	private boolean inHand;
	private Field field;
	public Card(GameWindow gw){
		super();
		gameWindow=gw;
		try{
			image = ImageIO.read(new File("graphics/reverse.jpg"));
		}catch(Exception e){}
		this.setPreferredSize(new Dimension(78, 110));
		addListeners();
		repaint();
		inHand=true;
	}
	public Card(GameWindow gw,Image img){
		super();
		gameWindow=gw;
		image = img;
		this.setPreferredSize(new Dimension(78, 110));
		addListeners();
		repaint();
		inHand=true;
	}
	
	public Image getImage(){
		return image;
	}
	
	public Card getCard(){
		return this;
	}
	
	
	private void addListeners(){
		addMouseMotionListener(new MouseMotionAdapter(){
			@Override
			public void mouseDragged(MouseEvent e){
				Card card = (Card)e.getSource();
                card.setBounds(card.getX()+e.getX()-39,card.getY()+e.getY()-55, card.getWidth(), card.getHeight());
                repaint();
			}
		});
		addMouseListener(new MouseAdapter(){
			private int oldX,oldY,x,y;
			@Override
			public void mousePressed(MouseEvent e){
				Card card = (Card)e.getSource();
				card.gameWindow.pane.moveToFront(card);
				oldX=card.getX();
				oldY=card.getY();
				x=card.getLocationOnScreen().x;
				y=card.getLocationOnScreen().y;
			}
			@Override
			public void mouseReleased(MouseEvent e){
				if(field==null)System.out.println(inHand);
				Card card = (Card)e.getSource();
				if(!gameWindow.putCardOnField(card.getX()+39,card.getY()+55, card)){
					card.setBounds(oldX,oldY, card.getWidth(), card.getHeight());
				}
					
			}
		});
	}
	
	public boolean isInHand(){
		return inHand;
	}
	public void setInHand(boolean h){
		inHand = h;
	}
	public boolean isInField(){
		return field!=null;
	}
	public void setField(Field f){
		field = f;
	}
	public Field getField(){
		return field;
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(image,0,0,null);
	}
}
