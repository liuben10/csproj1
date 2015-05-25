package base;

public class UnboundedDeque implements InstructuresDeque{
	
	public static void main(String...args) {
		UnboundedDeque unboudnedDeque = new UnboundedDeque(3);
		unboudnedDeque.addBottom(4);
		System.out.println(unboudnedDeque.listAsStringForwards());
		System.out.println(unboudnedDeque.listAsStringBackwards());
	}
	
	private Node head;
	private Node tail;
	private int size = 0;
	
	public UnboundedDeque(Object value) {
		this.setHead(new Node(value));
		this.setTail(this.getHead());
		this.size = 0;
	}
	
	public UnboundedDeque() {
		this.setHead(null);
		this.setTail(null);
		this.size = 0;
	}

	
	public void addTop(Object value) {
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
		this.size += 1;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void addBottom(Object element) {
		Node newTail = new Node(element);
		Node oldTail = this.tail;
		newTail.setPrevious(oldTail);
		newTail.setNext(null);
		if (oldTail != null) {
			oldTail.setNext(newTail);
		}
		this.tail = newTail;
	}

	@Override
	public Object removeTop() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object removeBottom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object top() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object bottom() {
		// TODO Auto-generated method stub
		return null;
	}

}
