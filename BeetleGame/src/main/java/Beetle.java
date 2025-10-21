public class Beetle {
	private boolean head;
	private boolean body;
	private int eyes;
	private int feelers;
	private int legs;
	private boolean tail;

	public Beetle() {
		head = body = tail = false;
		eyes=feelers = legs = 0;
	}
	
	//cant add a head unless there is a body
	public boolean addHead() {
		if (body && !head) {
			head = true;
			return true;
		}
		else {
			return false;
		}
	}
	
	//cant add a body if it already has one
	public boolean addBody() {
		if (body) {
			return false;
		}
		else {
			body = true;
			return true;
			
		}
		
	}
	
	
	//cant add eyes unless there is a head
	public boolean addEye() {
		if (head &&  (eyes <2)) {
			eyes++;
			return true;
		}
		else {
			return false;
		}
	}
	
	//cant add feelers unless there is a head
	public boolean addFeeler() {
		if (head && (feelers <2)) {
			feelers++;
			return true;
		}
		else {
			return false;
		}
	}
	
	//cant add legs unless there is a body
	public boolean addLeg() {
		if (body && (legs<6)) {
			legs++;
			return true;
		}
		else {
			return false;
		}
	}
	
	
	public boolean addTail() {
		if (body && !tail) {
			tail = true;
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isComplete() {
		return body && (eyes ==2) && (feelers ==2 ) && head
				&& (legs == 6) && tail;
	}
	
	public String toString() {
		if (body) { //must have a body to begin
			String result = "";
			if (feelers >0) {
				result += "\\";
				if (feelers == 2) {
					result += "/";
				}
				result += "\n";
			}
			if (head ) {
				if (eyes > 0) {
					result += "o";
				}
				else {
					result += " ";
				}
				result += "O";
				if (eyes == 2 ) {  //add a second eye
					result += "o";
				}
				result += "\n";
			}
			if (legs >0 ) {
				result += "-";
				
			}
			else {
				result += " ";
			}
			result += "#";
			if (legs > 1) {
				result += "-";
			}
			result += "\n";
			if (legs > 2) {
				result += "-";
			}
			else {
				result += " ";
			}
			result += "#";
			if (legs > 3) {
				result += " -";
			}
			result += "\n";
			if (legs >4) {
				result += "-";
			}
			else {
				result += " ";
			}
			result += "#";
			if (legs > 5) {
				result += "-";
			}
			
			if (tail) {
				result += "\n v";
			}
			return result;
		}
		else {
			return " ";
		}
		
	}

}
