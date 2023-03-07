package comp_2503_assignment_2;

public class Node <T extends Comparable<T>>{

	private T data;
	private Node<T> next;
	
	public Node(T d) {
		data = d;
		next = null;
	}
	
	public T getData() {
		return data;
	}

	public void setData(T o) {
		data = o;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> n) {
		next = n;
	}

	public String toString() {
		return "Node: " + getData().toString();
	}
	public boolean equals(Object obj) {
	    if (obj == null || !(obj instanceof Node)) {
	        return false;
	    }
	    Node<T> other = (Node<T>) obj;
	    return this.data.equals(other.data);
	}

	public int hashCode() {
	    return data.hashCode();
	}
}
