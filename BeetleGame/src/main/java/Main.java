import java.util.Scanner;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		System.out.println("How many Players wish to play the game");
		int numPlayers = input.nextInt();
		String[] names = new String[numPlayers];
		input.nextLine();  //flush the endline out of the buffer
		for (int i=0;i<numPlayers;i++) {
			System.out.println("Player " + (i+1) + " enter your name");
			names[i]= input.nextLine();
		}
		
		BeetleGame game = new BeetleGame(names);
		game.playGame();

	}

}
