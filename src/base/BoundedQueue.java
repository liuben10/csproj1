package base;

public class BoundedQueue implements InstructuresDeque {
	
	public static void main(String...args) {
		BoundedQueue bq = new BoundedQueue(10);
		bq.addTop("foo");
		bq.addTop(1);
		bq.addTop(2);
		bq.addBottom(3);
		bq.addBottom(4);
		System.out.println(bq.forwardString());
	}
	
	int capacity;
	
	int size;
	
	Object[] data;
	
	int head = -1;
	int tail = -1;
	
	BoundedQueue(int capacity) {
		this.capacity = capacity;
		this.data = new Object[capacity];
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void addTop(Object element) {
		if (head == -1) {
			head = 0;
			tail = 0;
		} else {
			head = (head + capacity - 1) % capacity;
		}
		data[head] = element;
		size += 1;
	}

	@Override
	public void addBottom(Object element) {
		if (tail == -1) {
			tail = 0;
			head = 0;
		} else {
			tail = (tail + 1) % capacity;
		}
		data[tail] = element;
		size += 1;
	}
	
	public String forwardString() {
		int ptr = head;
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < size; i++) {
			sb.append(data[ptr] + ", ");
			ptr = (ptr + 1) % capacity;
		}
		return sb.toString();
	}
	
	public String backwardString() {
		int ptr = tail;
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < size; i++) {
			sb.append(data[ptr] + ", ");
			ptr = (ptr + capacity - 1) % capacity;
		}
		return sb.toString();
	}

	@Override
	public Object removeTop() {
		if (isEmpty()) {
			throw new IllegalArgumentException("Attempted to remove top from empty bounded queue");
		}
		Object element = data[head];
		head = (head + 1) % capacity;
		size -= 1;
		return element;
	}

	@Override
	public Object removeBottom() {
		if (isEmpty()) {
			throw new IllegalArgumentException("Attempted to remove top from empty bounded queue");
		}
		Object element = data[tail];
		tail = (tail + capacity - 1) % capacity;
		size -= 1;
		return element;
	}

	@Override
	public Object top() {
		if (isEmpty()) {
			return null;
		}
		return data[head];
	}

	@Override
	public Object bottom() {
		if (isEmpty()) {
			return null;
		}
		return data[tail];
	}

}
