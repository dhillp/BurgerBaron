import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Driver program that prints out the orders from 0-99 and tests the
 * Burger and MyStack classes.
 * 
 * @author Pamaldeep Dhillon
 * @version 1.0
 */
public class Main {
	
	private static ArrayList<String> myIngredients;
	private static ArrayList<String> myCategories;
	private static int myOrderCount;
	
	/**
	 * @param args
	 */
	public static void main(final String[] theArgs) {
		setUpArrayLists();
		Scanner input = null;
		try {
			input = new Scanner(new File("customer.txt"));
		} catch (Exception e) {
			System.out.println("Couldn't open file! " + e);
			System.exit(1);
		}
		/* Instructions say orders from 0 - 99 so need to stop reading input when order
		 * number hits 99.
		 */
		while (input.hasNextLine() && myOrderCount < 100) {
			parseLine(input.nextLine());
		}
		testMyStack();
		testBurger();
	}
	
	private static void parseLine(final String theLine) {
		String[] order = theLine.split(" ");
		int pattyCount = 1;
		boolean baron = false;
		boolean omission = theLine.contains("with no");
		boolean addition = false;
		if (!omission) {
			addition = theLine.contains("with");
		}
		if (order[0].equals("Double")) {
			pattyCount = 2;
		} else if (order[0].equals("Triple")) {
			pattyCount = 3;
		}
		if (theLine.contains("Baron Burger")) {
			baron = true;
		}
		Burger b = new Burger(baron);
		
		for (int i = 0; i < order.length; i++) {
			if (order[i].equals("Chicken")) {
				b.changePatties("Chicken");;
			} else if (order[i].equals("Veggie")) {
				b.changePatties("Veggie");
			} else if (omission && order[i].equals("no")) {
				for (int j = i; j < order.length; j++) {
					if (order[j].equals("but")) {
						for (int k = j; k < order.length; k++) {
							if (myIngredients.contains(order[k])) {
								b.addIngredient(order[k]);
							}
						}
						break;
					} else if (myIngredients.contains(order[j])) {
						b.removeIngredient(order[j]);
					} else if (myCategories.contains(order[j])) {
						b.removeCategory(order[j]);
					}
				}
			} else if (addition && order[i].equals("with")) {
				for (int j = i; j < order.length; j++) {
					if (order[j].equals("but")) {
						for (int k = j; k < order.length; k++) {
							if (myIngredients.contains(order[k])) {
								b.removeIngredient(order[k]);
							}
						}
						break;
					} else if (myIngredients.contains(order[j])) {
						b.addIngredient(order[j]);
					} else if (myCategories.contains(order[j])) {
						b.addCategory(order[j]);
					}
				}
			}
		}
		while (pattyCount > 1) {
			b.addPatty();
			pattyCount--;
		}
		/* Doesn't print out an order if the inputed line doesn't contain burger in it.
		 * So random input such as Apple will not result in a burger being printed.
		 */
		if (theLine.contains("Burger")) {
			System.out.println("Order " + myOrderCount++ + ": " + b.toString());
		}
	}
	
	private static void testMyStack() {
		MyStack<String> test1 = new MyStack<String>();
		// Test if it can hold something besides strings.
		MyStack<Integer> test2 = new MyStack<Integer>();
		boolean passed = test1.size() == 0;
		passed = "".equals(test1.toString());
		passed = test1.isEmpty();
		test1.push("A");
		passed = test1.size() == 1;
		passed = "[A]".equals(test1.toString());
		passed = !test1.isEmpty();
		test1.push("B");
		passed = test1.size() == 2;
		passed = "[B, A]".equals(test1.toString());
		passed = !test1.isEmpty();
		passed = "B".equals(test1.peek());
		test2.push(1);
		test2.push(2);
		test2.push(100);
		passed = "[100, 2, 1]".equals(test2.toString());
		test2.pop();
		passed = "[2, 1]".equals(test2.toString());
		passed = test2.size() == 2;;
		System.out.println("MyStack - all tests passed: 		" + passed);
	}
	
	private static void testBurger() {
		Burger test1 = new Burger(true);
		Burger test2 = new Burger(false);
		test1.removeCategory("Sauce");
		System.out.println("Burger  - removeCategory test passed: 	"
				+ test1.toString().equals("[Pickle, Bun, Lettuce, "
				+ "Tomato, Onions, Pepperjack, Mozzarella, Cheddar, Beef, "
				+ "Mushrooms, Bun]"));
		test1.addIngredient("Ketchup");
		System.out.println("Burger  - addIngredient test passed: 	"
				+ test1.toString().equals("[Pickle, Bun, Lettuce, "
				+ "Tomato, Onions, Pepperjack, Mozzarella, Cheddar, Beef, "
				+ "Mushrooms, Ketchup, Bun]"));
		test1.addPatty();
		System.out.println("Burger  - addPatty test passed: 	"
				+ test1.toString().equals("[Pickle, Bun, Lettuce, "
				+ "Tomato, Onions, Beef, Pepperjack, Mozzarella, Cheddar, Beef, "
				+ "Mushrooms, Ketchup, Bun]"));
		test1.changePatties("Chicken");
		System.out.println("Burger  - changePatties test passed: 	"
				+ test1.toString().equals("[Pickle, Bun, Lettuce, "
				+ "Tomato, Onions, Chicken, Pepperjack, Mozzarella, Cheddar, Chicken, "
				+ "Mushrooms, Ketchup, Bun]"));
		test1.removePatty();
		System.out.println("Burger  - removePatty test passed: 	"
				+ test1.toString().equals("[Pickle, Bun, Lettuce, "
				+ "Tomato, Onions, Pepperjack, Mozzarella, Cheddar, Chicken, "
				+ "Mushrooms, Ketchup, Bun]"));
		test2.addCategory("Cheese");
		System.out.println("Burger  - addCategory test passed: 	"
				+ test2.toString().equals("[Bun, Pepperjack, Mozzarella, Cheddar,"
						+ " Beef, Bun]"));
		test2.removeIngredient("Mozzarella");
		System.out.println("Burger  - removeIngredient test passed: "
				+ test2.toString().equals("[Bun, Pepperjack, Cheddar, Beef, Bun]"));
	}
	
	private static void setUpArrayLists() {
		myIngredients = new ArrayList<String>();
		myIngredients.add("Cheddar");
		myIngredients.add("Mozzarella");
		myIngredients.add("Pepperjack");
		myIngredients.add("Lettuce");
		myIngredients.add("Tomato");
		myIngredients.add("Onions");
		myIngredients.add("Pickle");
		myIngredients.add("Mushrooms");
		myIngredients.add("Ketchup");
		myIngredients.add("Mustard");
		myIngredients.add("Mayonnaise");
		myIngredients.add("Baron-Sauce");
		
		myCategories = new ArrayList<String>();
		myCategories.add("Cheese");
		myCategories.add("Sauce");
		myCategories.add("Veggies");
		
		myOrderCount = 0;
	}

}
