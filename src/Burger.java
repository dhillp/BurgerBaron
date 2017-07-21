/**
 * Creates a burger for an order.
 * 
 * @author Pamaldeep Dhillon
 * @version 1.0
 */
public class Burger {
	
	private MyStack<String> myBaronBurger;
	
	private MyStack<String> myBurger;
	
	private String myPatty;
	
	private int myPattyCount;
	
	public Burger(final boolean theWorks) {
		myPatty = "Beef";
		createBaronBurger(myPatty);
		if (theWorks) {
			myBurger = new MyStack<String>(myBaronBurger);
			myPattyCount = 1;
		} else {
			myBurger = new MyStack<String>();
			myBurger.push("Bun");
			myBurger.push("Beef");
			myBurger.push("Bun");
			myPattyCount = 1;
		}
	}
	
	private void createBaronBurger(final String thePatty) {
		myBaronBurger = new MyStack<String>();
		myBaronBurger.push("Bun");
		myBaronBurger.push("Ketchup");
		myBaronBurger.push("Mustard");
		myBaronBurger.push("Mushrooms");
		myBaronBurger.push(thePatty);
		myBaronBurger.push("Cheddar");
		myBaronBurger.push("Mozzarella");
		myBaronBurger.push("Pepperjack");
		myBaronBurger.push("Onions");
		myBaronBurger.push("Tomato");
		myBaronBurger.push("Lettuce");
		myBaronBurger.push("Baron-Sauce");
		myBaronBurger.push("Mayonnaise");
		myBaronBurger.push("Bun");
		myBaronBurger.push("Pickle");
	}
	
	public void changePatties(final String thePattyType) {
		int num = myPattyCount;
		MyStack<String> tempStack = new MyStack<String>();
		if (thePattyType.equals("Chicken") || thePattyType.equals("Veggie")
										   || thePattyType.equals("Beef")) {
			while (num > 0) {
				String temp = "";
				while (!temp.equals(myPatty)) {
					temp = myBurger.pop();
					tempStack.push(temp);
				}
				tempStack.pop();
				myBurger.push(thePattyType);
				num--;
			}
			while (!tempStack.isEmpty()) {
				myBurger.push(tempStack.pop());
			}
			myPatty = thePattyType;
			createBaronBurger(thePattyType);
		}
	}
	
	public void addPatty() {
		if (myPattyCount < 3) {
			MyStack<String> tempStack = new MyStack<String>();
			String temp = "";
			while (!temp.equals(myPatty)) {
				temp = myBurger.pop();
				tempStack.push(temp);
			}
			myBurger.push(tempStack.pop());
			temp = tempStack.peek();
			if (!temp.equals("Pepperjack") && !temp.equals("Mozzarella")
			    && !temp.equals("Cheddar")) {
				myBurger.push(myPatty);
			} else {
				while (temp.equals("Pepperjack") || temp.equals("Mozzarella")
					   || temp.equals("Cheddar")) {
					temp = tempStack.pop();
					myBurger.push(temp);
					temp = tempStack.peek();
				}
				myBurger.push(myPatty);
			}
			while (tempStack.size() > 0) {
				myBurger.push(tempStack.pop());
			}
			myPattyCount++;
		}
	}
	
	public void removePatty() {
		if (myPattyCount > 1) {
			MyStack<String> tempStack = new MyStack<String>();
			String temp = "";
			while (!temp.equals(myPatty)) {
				temp = myBurger.pop();
				tempStack.push(temp);
			}
			tempStack.pop();
			while (tempStack.size() > 0) {
				myBurger.push(tempStack.pop());
			}
			myPattyCount--;
		}
	}
	
	public void addCategory(final String theType) {
		if (theType.equals("Cheese")) {
			addIngredient("Cheddar");
			addIngredient("Mozzarella");
			addIngredient("Pepperjack");
		} else if (theType.equals("Sauce")) {
			addIngredient("Ketchup");
			addIngredient("Mustard");
			addIngredient("Mayonnaise");
			addIngredient("Baron-Sauce");
		} else if (theType.equals("Veggies")) {
			addIngredient("Lettuce");
			addIngredient("Tomato");
			addIngredient("Onions");
			addIngredient("Pickle");
			addIngredient("Mushrooms");
		}
	}
	
	public void removeCategory(final String theType) {
		if (theType.equals("Cheese")) {
			removeIngredient("Cheddar");
			removeIngredient("Mozzarella");
			removeIngredient("Pepperjack");
		} else if (theType.equals("Sauce")) {
			removeIngredient("Ketchup");
			removeIngredient("Mustard");
			removeIngredient("Mayonnaise");
			removeIngredient("Baron-Sauce");
		} else if (theType.equals("Veggies")) {
			removeIngredient("Lettuce");
			removeIngredient("Tomato");
			removeIngredient("Onions");
			removeIngredient("Pickle");
			removeIngredient("Mushrooms");
		}
	}
	
	public void addIngredient(final String theType) {
		String temp1 = "";
		MyStack<String> tempStack1 = new MyStack<String>();
		MyStack<String> tempStack2 = new MyStack<String>();
		while (!temp1.equals(theType) && !myBurger.isEmpty()) {
			temp1 = myBaronBurger.pop();
			if (myBurger.peek().equals(temp1)) {
				tempStack2.push(myBurger.pop());
			}
			tempStack1.push(temp1);
		}
		myBurger.push(theType);
		while (!tempStack1.isEmpty()) {
			myBaronBurger.push(tempStack1.pop());
		}
		while (!tempStack2.isEmpty()) {
			myBurger.push(tempStack2.pop());
		}
	}
	
	public void removeIngredient(final String theType) {
		MyStack<String> tempStack = new MyStack<String>();
		String temp = "";
		while (!temp.equals(theType) && !myBurger.isEmpty()) {
			temp = myBurger.pop();
			tempStack.push(temp);
		}
		if (tempStack.peek().equals(theType)) {
			tempStack.pop();
		}
		while (!tempStack.isEmpty()) {
			myBurger.push(tempStack.pop());
		}
	}
	
	public String toString() {
		return myBurger.toString();
	}
}
