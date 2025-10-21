public class Player {
	private String name;
	private Beetle beetle;

	public Player(String name) {
		this.name = name;
		beetle = new Beetle();
	}
	
	public boolean takeTurn(Die die) {
		//get another turn if you were able to add a part to the beetle
		die.roll();
		System.out.println(" Rolled die " + die.getTop());//for debugging
		switch (die.getTop()) {
		case 1: //add a body
			return beetle.addBody();
		case 2: //add head
			return beetle.addHead();
		case 3: //add leg
			return beetle.addLeg();
		case 4: //add eye
			return beetle.addEye();
		case 5: //add feeler
			return beetle.addFeeler();
		default: //add tail, will always be 6 
			return beetle.addTail();
			
		}
		
	}
	
	public void roll (Die die) {
		 die.roll();
	}
	
	public boolean isComplete() {
		return beetle.isComplete();
	}
	
	public String viewBeetle() {
		return beetle.toString();
	}
	
	public String toString() {
		return "Player " + name + "\n" + beetle.toString();
	}
	
	public String getName() {
		return name;
	}
      
}
