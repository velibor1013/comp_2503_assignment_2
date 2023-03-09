package comp_2503_assignment_2;

import java.util.Comparator;

public class SLL <T extends Comparable<T>>{
	
	private Node<T> head, tail;
	private int size;
	private Comparator<T> comparator;
	
	/*
	 * private class Node { T value; Node next;
	 * 
	 * public Node(T value) { this.value = value; this.next = null; } }
	 */
	
	public SLL () {
		head = null;
		tail = null;
		size = 0;
		comparator = null;
	}
	
	public void nodeToAdd(T data) { //A2 call this method to add a Node with the AddToEnd method
		addToEnd(data);
	}
	
	public SLL(Comparator<T> externalComp) {
		head = null;
		tail = null;
		size = 0;
		comparator = externalComp;
	}

	//Getters and Setters
	public void setHead(Node<T> n) {
		head = n;
	}
	
	public void setTail(Node<T> n) {
		tail = n;
	}
	
	public Node<T> getHead() {
		return head;
	}
	
	public Node<T> getTail() {
		return tail;
	}
	
	public int size(){
		int count = 0;
		Node<T> mover = head;
		if(isEmpty())
			return 0;
		while(mover != null) {
			mover = mover.getNext();
			count++;
		}
		size = count;
		return size;
	}
	
	public int compare(T object1, T object2) {
		if (comparator == null)
			return object1.compareTo(object2);
		else
			return comparator.compare(object1, object2);
	}
	
	public boolean isEmpty(){
		return (head == null);
	}
	
	
	@SuppressWarnings("unused")
	private void addToStart(T data) { //this could be used if we wanted to add to the start of an SLL.
		Node<T> newNode = new Node<>(data);
		if(isEmpty())
			head = newNode;
		else {
			newNode.setNext(head);
			head = newNode;
		}
	}
	
	private void addToEnd(T data) {
		Node<T> newNode = new Node<>(data);
		if(head != null){
			Node<T> curr = head;
			while(curr.getNext() != null){
				curr = curr.getNext();
			}
			curr.setNext(newNode);
		}
		else
			head = newNode;
		}


	public void addInOrder(Node<T> n) {
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
	
	@SuppressWarnings("unlikely-arg-type")
	public Node<T> find(Node<T> key) {
		Node<T> currentNode = head;
		while (currentNode != null) {
			// Visit the node. In this case,
			// if the data in the currentNode is equal to the key,
			// return the reference to the node.
			if (currentNode.getData().equals(key))
				return currentNode;
			// else, move to the next node
			else
				currentNode = currentNode.getNext();
		}
		// Return null if it didn't find the node.
		return null;
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public boolean exists(Node<T> key) {
		Node<T> currentNode = head;
		while (currentNode != null) {
			// Visit the node. In this case,
			// if the data in the currentNode is equal to the key,
			// return the reference to the node.
			if (currentNode.getData().equals(key))
				return true;
			// else, move to the next node
			else
				currentNode = currentNode.getNext();
		}
		// Return null if it didn't find the node.
		return false;
	}
	
	public Node<T> delete(T o) {
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
