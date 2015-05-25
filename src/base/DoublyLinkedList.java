package base;

public class DoublyLinkedList<T> {
	
	public static void main(String...args) {
		DoublyLinkedList<Integer> doublyLinkedList = new DoublyLinkedList<Integer>(3);
		doublyLinkedList.addToHead(4);
		System.out.println(doublyLinkedList.listAsStringForwards());
		System.out.println(doublyLinkedList.listAsStringBackwards());
	}
	
	private Node head;
	private Node tail;
	
	public DoublyLinkedList(T value) {
		this.setHead(new Node(value));
		this.setTail(this.getHead());
	}
	
	public DoublyLinkedList() {
		this.setHead(null);
		this.setTail(null);
	}
	
	public void addToHead(T value) {
		Node newNext = this.getHead();
		this.setHead(new Node(value));
		this.getHead().setNext(newNext);
		if (newNext == null) {
			this.getHead().setPrevious(null);
			this.setTail(this.getHead());
		} else {
			this.getHead().setPrevious(newNext.getPrevious());
			newNext.setPrevious(this.getHead());
		}
	}
	
	public String listAsStringBackwards() {
		Node iterator = getTail();
		StringBuilder result = new StringBuilder();
		while(iterator != null) {
			result.append(iterator.toString() + ", ");
			iterator=iterator.getPrevious();
		}
		return result.toString();
	}
	
	
	public String listAsStringForwards() {
		Node iterator = getHead();
		StringBuilder result = new StringBuilder();
		while(iterator != null) {
			result.append(iterator.toString() + ", ");
			iterator=iterator.getNext();
		}
		return result.toString();
	}
	
	public Node getHead() {
		return head;
	}

	public void setHead(Node head) {
		this.head = head;
	}

	public Node getTail() {
		return tail;
	}

	public void setTail(Node tail) {
		this.tail = tail;
	}

	private class Node {
		private T value;
		private Node previous;
		private Node next;
		
		public Node() {
		}
		public Node(T value) {
			this.setValue(value);
			this.setPrevious(null);
			this.setNext(null);
		}

		public T getValue() {
			return value;
		}

		public void setValue(T value) {
			this.value = value;
		}

		public Node getPrevious() {
			return previous;
		}

		public void setPrevious(Node previous) {
			this.previous = previous;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}
		
		@Override
		public String toString() {
			return value.toString();
		}
	}

}
