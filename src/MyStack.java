/**
 * Linked Structure that represents a last-in-first-out stack of objects.
 * 
 * @author Pamaldeep Dhillon
 * @version 1.0
 */
public class MyStack<Type> {
	
	private Node<Type> myHead;
	
	private int myListSize;
	
	public MyStack() {
		myHead = null;
		myListSize = 0;
	}
	
	public MyStack(MyStack<Type> theStack) {
		myHead = theStack.myHead;
		myListSize = theStack.myListSize;
	}
	
	public boolean isEmpty() {
		return (myHead == null);
	}
	
	public void push(Type theItem) {
		Node<Type> current = myHead;
		myHead = new Node<Type>(theItem, current);
		myListSize++;
	}
	
	public Type pop() {
		if (isEmpty()) {
			throw new RuntimeException("Stack is empty");
		}
		Type item = myHead.data;
		myHead = myHead.next;
		myListSize--;
		return item;
	}
	
	public Type peek() {
		if (isEmpty()) {
			throw new RuntimeException("Stack is empty");
		}
		return myHead.data;
	}
	
	public int size() {
		return myListSize;
	}
	
	public String toString() {
		Node<Type> temp = myHead;
		String out = "";
		if (size() > 0) {
			out = "[" + temp.data.toString();
			temp = temp.next;
			while (temp != null) {
				out += ", " + temp.data.toString();
				temp = temp.next;
			}
			out += "]";
		}
		return out;
	}
	
	/* used linked list class from 
	 * www.csie.ntu.edu.tw/~cyy/courses/oop/13summer/chap15java5th.pdf
	 * as example to set up Node class.
	 */
	private class Node<T> {
		private T data;
		private Node<T> next;
		
		public Node(T newData, Node<T> nextValue) {
			data = newData;
			next = nextValue;
		}
	}
}
