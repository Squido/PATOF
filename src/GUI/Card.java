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
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;

public class Card extends JPanel {
	private Image reverseImage;
	private Image image;
	private Image bigImage;
	private Image displayImage;
	private GameWindow gameWindow;
	private boolean inHand;
	private Field field;
	private boolean reversed;
	private boolean haveDragListeners;
	private boolean haveAimListeners;
	private MouseMotionAdapter MMAdrag;
	private MouseAdapter MAdrag;
	private MouseAdapter MAaim;
	private int owner;

	public Card(GameWindow gw) {
		super();
		gameWindow = gw;
		try {
			reverseImage = ImageIO.read(new File("graphics/reverse.jpg"));
			displayImage = reverseImage;
		} catch (Exception e) {
		}

		reversed = true;
		this.setPreferredSize(new Dimension(78, 110));
		generateListeners();
		//addPlacingListeners();
		repaint();
		inHand = true;
	}

	public Card(GameWindow gw, String img) {
		super();
		gameWindow = gw;

		try {
			image = ImageIO.read(new File(img));
			displayImage = image;
		} catch (Exception e) {
		}
		try {
			reverseImage = ImageIO.read(new File("graphics/reverse.jpg"));
		} catch (Exception e) {
		}

		reversed = false;
		this.setPreferredSize(new Dimension(78, 110));
		generateListeners();
		//addPlacingListeners();
		repaint();
		inHand = true;
	}

	public Image getImage() {
		return image;
	}

	public Card getCard() {
		return this;
	}

	private void generateListeners() {
		MMAdrag = new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				Card card = (Card) e.getSource();
				card.setBounds(card.getX() + e.getX() - 39,
						card.getY() + e.getY() - 55, card.getWidth(),
						card.getHeight());
				repaint();
			}
		};
		MAdrag = new MouseAdapter() {
			private int oldX, oldY, x, y;

			@Override
			public void mousePressed(MouseEvent e) {
				Card card = (Card) e.getSource();
				card.gameWindow.getPane().moveToFront(card);
				oldX = card.getX();
				oldY = card.getY();
				x = card.getLocationOnScreen().x;
				y = card.getLocationOnScreen().y;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				Card card = (Card) e.getSource();
				if (!gameWindow.putCardOnField(card.getX() + 39,
						card.getY() + 55, card)) {
					card.setBounds(oldX, oldY, card.getWidth(),
							card.getHeight());
				}

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				Card card = (Card) e.getSource();
				BigCardDisplayer big = new BigCardDisplayer(card.image,
						gameWindow);
			}
		};
		MAaim = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Card card = (Card) e.getSource();
				// zamiast tego mam od karty pobrac liste mozliwosci
				DefaultListModel<String> dLM = new DefaultListModel<String>();
				dLM.addElement("attack");
				dLM.addElement("skill1");
				dLM.addElement("skill2");
				dLM.addElement("defend");
				//
				SkillListDisplayer sLD = new SkillListDisplayer(card, dLM);
				gameWindow.getPane().add(sLD);
				sLD.setListBounds(card.getX() + 39, card.getY() + 55, 161,
						18 * dLM.size());
				JList list = sLD.getList();
				card.gameWindow.getPane().add(sLD);
				card.gameWindow.getPane().add(list);
				card.gameWindow.getPane().moveToFront(sLD);
				card.gameWindow.getPane().moveToFront(list);
			}
		};
	}

	public void addPlacingListeners() {
		haveDragListeners = true;

		addMouseMotionListener(MMAdrag);
		addMouseListener(MAdrag);
	}

	public void removePlacingListeners() {
		if (haveDragListeners) {
			removeMouseMotionListener(MMAdrag);
			removeMouseListener(MAdrag);
			haveDragListeners = false;
		}
	}

	public boolean isInHand() {
		return inHand;
	}

	public void setInHand(boolean h) {
		inHand = h;
	}

	public boolean isOnBoard() {
		return field != null;
	}

	public void setOnBoard(Field f) {
		field = f;
	}

	public Field getField() {
		return field;
	}

	public boolean isReversed() {
		return reversed;
	}

	public void reverse() {
		reversed = !reversed;
		if (reversed)
			displayImage = reverseImage;
		else
			displayImage = image;
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (displayImage != null)
			g.drawImage(displayImage, 0, 0, null);
	}

	public void changeActivePlayer() {
		reverse();
		removePlacingListeners();
	}

	public void addAimListener() {
		addMouseListener(MAaim);
		haveAimListeners=true;
	}
	
	public void removeAimListener(){
		if(haveAimListeners){
			removeMouseListener(MAaim);
			haveAimListeners=false;
		}
	}
	
	public GameWindow getGameWindow() {
		return gameWindow;
	}

	public int getOwner(){
		return owner;
	}
	
	public void setOwner(int player){
		owner = player;
	}
	
}
