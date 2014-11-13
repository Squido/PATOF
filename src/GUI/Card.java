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
	private Image reverseImage;
	private Image image;
	private GameWindow gameWindow;
	private boolean inHand;
	private Field field;
	private boolean reversed;
	private boolean isActivePlayerCard;
	private boolean haveListeners;
	private MouseMotionAdapter MMA;
	private MouseAdapter MA;
	public Card(GameWindow gw){
		super();
		gameWindow=gw;
		isActivePlayerCard=true;
		try{
			reverseImage = ImageIO.read(new File("graphics/reverse.jpg"));
		}catch(Exception e){}
		
		reversed = true;
		this.setPreferredSize(new Dimension(78, 110));
		addListeners();
		repaint();
		inHand=true;
	}
	public Card(GameWindow gw,String img){
		super();
		gameWindow=gw;
		isActivePlayerCard=true;
		
		try{
		image = ImageIO.read(new File(img));
		}catch(Exception e){}
		try{
			reverseImage = ImageIO.read(new File("graphics/reverse.jpg"));
		}catch(Exception e){}
		
		reversed = false;
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
	
	
	public void addListeners(){
		haveListeners=true;
		if(MMA==null)
		MMA=new MouseMotionAdapter(){
			@Override
			public void mouseDragged(MouseEvent e){
				Card card = (Card)e.getSource();
                card.setBounds(card.getX()+e.getX()-39,card.getY()+e.getY()-55, card.getWidth(), card.getHeight());
                repaint();
			}
		};
		addMouseMotionListener(MMA);
		if(MA==null)
		MA = new MouseAdapter(){
			private int oldX,oldY,x,y;
			@Override
			public void mousePressed(MouseEvent e){
				Card card = (Card)e.getSource();
				card.gameWindow.getPane().moveToFront(card);
				oldX=card.getX();
				oldY=card.getY();
				x=card.getLocationOnScreen().x;
				y=card.getLocationOnScreen().y;
			}
			@Override
			public void mouseReleased(MouseEvent e){
				Card card = (Card)e.getSource();
				if(!gameWindow.putCardOnField(card.getX()+39,card.getY()+55, card)){
					card.setBounds(oldX,oldY, card.getWidth(), card.getHeight());
				}
					
			}
		};
		addMouseListener(MA);
	}
	public void removeListeners(){
		if(haveListeners){
			removeMouseMotionListener(MMA);
			removeMouseListener(MA);
			haveListeners=false;
		}
	}
	public void changeListeners(){
		if(haveListeners){
			removeMouseMotionListener(MMA);
			removeMouseListener(MA);
			haveListeners=false;
		}else{
			addMouseListener(MA);
			addMouseMotionListener(MMA);
			haveListeners=true;
		}
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
	public boolean isReversed(){
		return reversed;
	}
	public void reverse(){
		reversed=!reversed;
		repaint();
	}
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		if(reversed) g.drawImage(reverseImage,0,0,null);
		else g.drawImage(image,0,0,null);
	}
	
	public void changeActivePlayer(){
		isActivePlayerCard=!isActivePlayerCard;
		reverse();
		removeListeners();
	}
}
