package base;

public class UnboundedDeque implements InstructuresDeque{
	
	public static void main(String...args) {
		UnboundedDeque unbounded = new UnboundedDeque();
		unbounded.addBottom(3);
		unbounded.addTop("foo");
		unbounded.addBottom(5);
		unbounded.addBottom(6);
		System.out.println(unbounded.listAsStringForwards());
		System.out.println(unbounded.listAsStringBackwards());
	}
	
	private Node head;
	private Node tail;
	private int size = 0;
	
	public UnboundedDeque(Object value) {
		this.setHead(new Node(value));
		this.setTail(this.getHead());
		this.size = 1;
	}
	
	public UnboundedDeque() {
		this.setHead(null);
		this.setTail(null);
		this.size = 0;
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
		private Object value;
		private Node previous;
		private Node next;
		
		public Node() {
		}
		public Node(Object value) {
			this.setValue(value);
			this.setPrevious(null);
			this.setNext(null);
		}

		public Object getValue() {
			return value;
		}

		public void setValue(Object value) {
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

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}
	
	@Override
	public void addTop(Object value) {
		Node oldHead = this.getHead();
		Node newHead = new Node(value);
		this.setHead(newHead);
		newHead.setNext(oldHead);
		if (isEmpty()) {
			this.setTail(newHead);
		} else {
			newHead.setNext(oldHead);
			oldHead.setPrevious(newHead);
		}
		this.size += 1;
	}

	@Override
	public void addBottom(Object element) {
		Node oldTail = this.getTail();
		Node newTail = new Node(element);
		this.setTail(newTail);
		newTail.setPrevious(oldTail);
		if (isEmpty()) {
			this.setHead(newTail);
		} else {
			newTail.setPrevious(oldTail);
			oldTail.setNext(newTail);
		}
		this.size += 1;
	}

	@Override
	public Object removeTop() {
		Node topNode = this.head;
		if (!this.isEmpty()) {
			this.head = topNode.getNext();
			if (this.head != null) {
				this.head.setPrevious(null);	
			} else {
				this.tail = null;
			}
			this.size -= 1;
			return topNode.getValue();
		} else {
			throw new IllegalArgumentException("Tried to remove top from an empty list");
		}
	}

	@Override
	public Object removeBottom() {
		Node bottomNode = this.tail;
		if (!isEmpty()) {
			this.tail = bottomNode.getPrevious();
			if (this.tail != null) {
				this.tail.setNext(null);
			} else {
				this.head = null;
			}
			this.size -= 1;
			return bottomNode.getValue();
		} else {
			throw new IllegalArgumentException("Tried to remove bottom from an empty list");
		}
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
