package base;

public class UnboundedDeque implements InstructuresDeque{
	
	public static void main(String...args) {
		UnboundedDeque buf = new UnboundedDeque();
		buf.addBottom(3);
		buf.addTop("foo");
		buf.addBottom(5);
		buf.addBottom(6);
		System.out.println(buf.listAsStringForwards());
		System.out.println(buf.listAsStringBackwards());
	}
	
	private Node head = new Node(null, null, null);
	private Node tail = new Node(null, null, null);
	private int size = 0;
	
	public UnboundedDeque() {
		head.next = tail;
		tail.prev = head;
		this.size = 0;
	}
	
	@Override
	public void addTop(Object value) {
		Node oldNode = head.next;
		Node newNode = new Node(value, head, oldNode);
		head.next = newNode;
		oldNode.prev = newNode;
		size += 1;
	}

	@Override
	public void addBottom(Object value) {
		Node oldNode = tail.prev;
		Node newNode = new Node(value, oldNode, tail);
		tail.prev = newNode;
		oldNode.next = newNode;
		size += 1;
	}

	@Override
	public Object removeTop() {
		if (this.isEmpty())
			throw new IllegalArgumentException("Tried to remove top from an empty list");
		Node oldNode = head.next;
		head.next = oldNode.next;
		oldNode.next.prev = head;	
		size -= 1;
		return oldNode.value;
	}

	@Override
	public Object removeBottom() {
		if (this.isEmpty())
			throw new IllegalArgumentException("Tried to remove bottom from an empty list");
		Node oldNode = tail.prev;
		tail.prev = oldNode.prev;
		oldNode.prev.next = tail;	
		this.size -= 1;
		return oldNode.value;
	}
	
	public String listAsStringForwards() {
		Node iterator = head.next;
		StringBuilder result = new StringBuilder();
		while(iterator != tail) {
			result.append(iterator.toString() + ", ");
			iterator = iterator.next;
		}
		return result.toString();
	}
	
	public String listAsStringBackwards() {
		Node iterator = tail.prev;
		StringBuilder result = new StringBuilder();
		while(iterator != head) {
			result.append(iterator.toString() + ", ");
			iterator = iterator.prev;
		}
		return result.toString();
	}

	private class Node {
		public Object value = null;
		public Node prev = null;
		public Node next = null;
		
		public Node(Object value, Node prev, Node next) {
			this.value = value;
			this.prev = prev;
			this.next = next;
		}

		@Override
		public String toString() {
			return this.value.toString();
		}
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}
	

	@Override
	public Object top() {
		return this.head;
	}

	@Override
	public Object bottom() {
		return this.tail;
	}

}
