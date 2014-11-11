package cont;

import mod.Player;

public class GameController {
	private static Turn turn;
	private static Player[] players;
	
	public GameController(){
		turn = new Turn();
		players = new Player[2];
		// nie mam ich deckow, wiec nie moge dodac
		//player[0] = new Player();
		//player[1] = new Player();
		turn.start();
		
	}
	public static Turn getTurn() {
		return turn;
	}
	public static Player getPlayer(int i){
		return players[i];
	}
}
