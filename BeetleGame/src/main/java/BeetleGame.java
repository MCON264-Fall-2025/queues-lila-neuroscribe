import java.util.Scanner;

public class BeetleGame {
	private Player[] players;
	
	private Die die;
	private boolean hasWinner;

	public BeetleGame( String[] names) {
		if (names.length < 2) {
			throw new IllegalArgumentException();
		}
		else {
			players =new Player[names.length];
			for (int i=0;i<names.length;i++) {
				players[i]= new Player(names[i]);
			}
		}
		die = new Die();
		hasWinner = false;
	}
	
	public Player getWinner() {
		for (Player player : players) {
			if (player.isComplete()) {
				hasWinner = true;
				return player;
			}
		}
		return null;
	}
	public void playGame() {
		Player winner =null;
		Scanner pause = new Scanner (System.in);
		boolean anotherTurn = false;
		int currentPlayer =0;
		while (!hasWinner) {
			anotherTurn = players[currentPlayer].takeTurn(die);
			System.out.println(players[currentPlayer].toString() );
			while (anotherTurn  && !players[currentPlayer].isComplete()) {
				anotherTurn= players[currentPlayer].takeTurn(die);
				System.out.println(players[currentPlayer].toString() );
			}
			System.out.println(players[currentPlayer].getName() + "  your turn is over");
			winner = getWinner();
			if (winner != null) {
				System.out.println(" Winner is "+ winner.toString());
			}
			else {
				currentPlayer = (currentPlayer +1) % players.length;
				System.out.println("press key to continue...");
				pause.nextLine();
			}
		}
		System.out.println("Game is Over");
		
	}

}
