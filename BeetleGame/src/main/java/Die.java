import java.util.Random;

public class Die {
	private int top;
	private Random gen ;

	public Die() {
		gen = new Random(System.currentTimeMillis());
		top = 1;
	}
	
	public int getTop() {
		return top;
	}
	
	public void roll() {
		 top = gen.nextInt(6)+1;
		
	}

}
