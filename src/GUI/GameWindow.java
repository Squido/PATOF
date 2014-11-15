package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;

import cont.GUIController;

public class GameWindow extends JFrame {
	private JLayeredPane pane;
	private GUIController controller;
	private Field cardsOnBoard[][];
	private ArrayList<ArrayList<Card>> handCard = new ArrayList<ArrayList<Card>>();
	private JButton decks[];
	private JButton graveyards[];
	private JButton endTurn;

	public GameWindow(GUIController gui) {
		pane = getLayeredPane();
		setResizable(false);
		controller = gui;
		handCard.add(new ArrayList<Card>());
		handCard.add(new ArrayList<Card>());
		graveyards = new JButton[2];
		decks = new JButton[2];

		this.boardGenerator();
		setStartingHand();
		this.handGenerator(0);
		this.handGenerator(1);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setBounds(0, 0, 1024, 768);
		this.setVisible(true);
		setBackground(Color.LIGHT_GRAY);
		endTurn = new JButton("endTurn");
		endTurn.setBounds(900, 300, 100, 50);
		add(endTurn);
		endTurn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.setEndedTurn(controller.getActivePlayer(), true);
				if (controller.isEndedTurn(0) && controller.isEndedTurn(1)) {
					startAimingTurn();
				}
				changeActivePlayer();
			}

		});
	}

	private void boardGenerator() {
		cardsOnBoard = new Field[4][5];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 5; j++) {
				cardsOnBoard[i][j] = new Field();
				cardsOnBoard[i][j].setBounds(200 + 120 * j, 768 / 7 + 25 + 115
						* i, 78, 110);
				add(cardsOnBoard[i][j]);
			}
		}
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 5; j++) {
				cardsOnBoard[i + 2][j] = new Field();
				cardsOnBoard[i + 2][j].setBounds(200 + 120 * j, 768 / 7 + 260
						+ 115 * i, 78, 110);
				add(cardsOnBoard[i + 2][j]);
			}
		}
	}

	private void handGenerator(int player) {
		graveyards[player] = new JButton("");
		try {
			Image img = ImageIO.read(new File("graphics/reverse.jpg"));
			graveyards[player].setIcon(new ImageIcon(img));
		} catch (Exception e) {
		}
		graveyards[player].setBounds(920, 10 + 615 * ((player + 1) % 2), 78,
				110);
		this.add(graveyards[player]);

		decks[player] = new JButton("");
		try {
			Image img = ImageIO.read(new File("graphics/reverse.jpg"));
			decks[player].setIcon(new ImageIcon(img));
		} catch (Exception e) {
		}
		decks[player].setBounds(840, 10 + 615 * ((player + 1) % 2), 78, 110);
		this.add(decks[player]);
		generateHand(player);
	}

	private void generateHand(int player) {
		for (int i = 0; i < handCard.get(player).size(); i++) {
			pane.remove(handCard.get(player).get(i));
		}
		for (int i = 0; i < handCard.get(player).size(); i++) {
			handCard.get(player)
					.get(i)
					.setBounds(
							380 - ((handCard.get(player).size() - 1) * 40) + 80
									* i, 615 * ((player + 1) % 2) + 10, 78, 110);
			pane.add(handCard.get(player).get(i));
		}
	}

	public void addCard(Card card) {
		handCard.get(controller.getActivePlayer()).add(card);
		generateHand(controller.getActivePlayer());
		repaint();
	}

	public Card getCard(int index) {
		Card card = handCard.get(controller.getActivePlayer()).get(index)
				.getCard();
		getLayeredPane().remove(
				handCard.get(controller.getActivePlayer()).get(index));
		handCard.get(controller.getActivePlayer()).remove(index);
		repaint();
		return card;
	}

	public JButton getGraveyard(int player){
		return graveyards[player];
	}
	
	public boolean putCardOnField(int x, int y, Card card) {
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 5; j++) {
				if (x >= 200 + 120 * j
						&& x <= 200 + 120 * j + 78
						&& y >= 768 / 7 + 260 + 115 * i - 235
								* controller.getActivePlayer()
						&& y <= 768 / 7 + 260 + 115 * i + 110 - 235
								* controller.getActivePlayer()
						&& cardsOnBoard[i + 2 - 2
								* controller.getActivePlayer()][j].isEmpty()) {

					card.removePlacingListeners();
					cardsOnBoard[i + 2 - 2 * controller.getActivePlayer()][j]
							.putCard(card);
					card.setBounds(
							cardsOnBoard[i + 2 - 2
									* controller.getActivePlayer()][j].getX(),
							cardsOnBoard[i + 2 - 2
									* controller.getActivePlayer()][j].getY(),
							78, 110);
					controller.getGameController().addCardToBoard(
							i + 2 - 2 * controller.getActivePlayer(),
							j,
							controller.getGameController().removeCardFromHand(
									controller.getActivePlayer(),
									handCard.get(controller.getActivePlayer())
											.indexOf(card)));
					getCard(handCard.get(controller.getActivePlayer()).indexOf(
							card));
					generateHand(controller.getActivePlayer());
					changeActivePlayer();
					getLayeredPane().add(card);

					return true;
				}
			}
		}
		return false;
	}

	public ArrayList<Card> getMyHand() {
		return handCard.get(controller.getActivePlayer());
	}

	private void setStartingHand() {
		List<String> images = controller.getGameController().getImages(0);
		for (String img : images) {
			handCard.get(0).add(new Card(this, img));
		}
		images = controller.getGameController().getImages(1);
		for (String img : images) {
			Card c = new Card(this, img);
			c.changeActivePlayer();
			handCard.get(1).add(c);
		}
	}

	public JLayeredPane getPane() {
		return pane;
	}

	public void changeActivePlayer() {
		if (controller.isEndedTurn((controller.getActivePlayer() + 1) % 2))
			return;
		if (controller.isPlacementFlag())
			changePlacingPlayer();
		else if (controller.isAimingFlag())
			changeAimPlayer();
	}

	public void changeAimPlayer() {

	}

	public void changePlacingPlayer() {
		for (Card card : handCard.get(controller.getActivePlayer())) {
			card.reverse();
			card.removePlacingListeners();
		}
		controller.changeActivePlayer();
		for (Card card : handCard.get(controller.getActivePlayer())) {
			card.reverse();
			card.addPlacingListeners();
		}
	}

	//mniej wiecej takie funkcje powinny byc w gameControlerze 
	//i tylko wywolywac odpowiednie funkcje w GUIControlerze np addAimListener dla odpowiednich kart itp***
	public void startAimingTurn() {
		controller.setPlacementFlag(false);
		controller.setAimingFlag(true);
		controller.setEndedTurn(0, false);
		controller.setEndedTurn(1, false);
		int maxInit = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 5; j++) {
				maxInit = Math.max(controller.getGameController().getBoard()
						.getCard(i, j).getInit(), maxInit);
			}
		}
		nextAim(maxInit);
	}

	public void nextAim(int init) {
		if (controller.isEndedTurn(0) && controller.isEndedTurn(1)) {
			if (init != 0)
				init--;
			else {
				startPlacingTurn();
			}
		}
		int counter = 0;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 5; j++) {
				if (controller.getGameController().getBoard()
						.getCard(i + 2 - 2 * controller.getActivePlayer(), j)
						.getInit() == init) {
					cardsOnBoard[i + 2 - 2 * controller.getActivePlayer()][j]
							.getCard().addAimListener();
					counter++;
				}
			}
		}
		if (counter == 0) {
			controller.setEndedTurn(controller.getActivePlayer(), true);
			nextAim(init);
		}
	}

	public void startPlacingTurn() {
		
	}
	//***
	
	public Field[][] getBoard(){
		return cardsOnBoard;
	}
	
	public void removeListeners(){
		for (Card card : handCard.get(controller.getActivePlayer())) {
			card.removePlacingListeners();
			card.reverse();
		}
		for (Card card : handCard.get((controller.getActivePlayer()+1)%2)) {
			card.removePlacingListeners();
			card.reverse();
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 5; j++) {
				cardsOnBoard[i][j].getCard().removePlacingListeners();
				cardsOnBoard[i][j].getCard().removeAimListener();
			}
		}
	}
	
}
