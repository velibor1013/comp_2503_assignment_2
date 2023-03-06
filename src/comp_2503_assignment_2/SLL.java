package comp_2503_assignment_2;

import java.util.Comparator;

public class SLL <T extends Comparable<T>>{
	
	private Node<T> head, tail;
	private int size;
	private Comparator<T> comparator;

	
	public SLL () {
		head = null;
		size = 0;
		comparator = null;
	}
	
	public SLL(Comparator<T> externalComp) {
		head = null;
		size = 0;
		comparator = externalComp;
	}
	
	public  int size() {
		return size;
	}
	
	private int compare(T object1, T object2) {
		if (comparator == null)
			return object1.compareTo(object2);
		else
			return comparator.compare(object1, object2);
	}
	private void addInOrder(Node<T> n) {
		if (head == null) {
			head = n;
			tail = n;
		} else {
			if (n.getData().compareTo(head.getData()) <= 0) // less than first item, insert here.
				addHead(n);
			else {
				Node<T> currentNode = head;
				while (currentNode.getNext() != null && n.getData().compareTo(currentNode.getNext().getData()) > 0) {
					currentNode = currentNode.getNext();
				}
				if (currentNode.getNext() == null) { // did not find a place
					addTail(n);
				} else {
					n.setNext(currentNode.getNext());
					currentNode.setNext(n);
				}
			}
		}
	}
	private void addHead(Node<T> n) {
		if (head == null) {
			head = n;
			tail = n;
		} else {
			n.setNext(head);
			head = n;
		}
	}

	private void addTail(Node<T> n) {
		if (tail == null) { // list is empty
			head = n;
			tail = n;
		} else {
			tail.setNext(n);
			tail = n;
		}
	}
	
	private Node<T> delete(T o) {
		Node<T> currentNode = head;
		Node<T> previousNode = head;
		while (currentNode != null) {
			// Visit the node. In this case,
			// if the data in the currentNode is equal to the key,
			// remove the current node from the list by updating the reference
			// of the previous node to point to the node after the currentNode.
			if (currentNode.getData().equals(o)) {
				if (head == tail) {
					head = null;
					tail = null;
					return currentNode;
				}

				if (currentNode == head)
					head = currentNode.getNext();
				else {
					previousNode.setNext(currentNode.getNext());
				}
				if (currentNode == tail) {
					tail = previousNode;
				}

				return currentNode;
			}
			// else, move to the next node
			else {
				previousNode = currentNode;
				currentNode = currentNode.getNext();
			}
		}
		// Return null if it didn't find the node.
		return null;
	}
}
