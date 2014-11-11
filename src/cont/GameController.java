package cont;

import mod.Player;

public class GameController {
	private static Turn turn;
	private static Player[] player;
	
	public GameController(){
		turn = new Turn();
		player = new Player[2];
		//player[0] = new Player();
		//player[1] = new Player();
		
	}
	public static Turn getTurn() {
		return turn;
	}
}
