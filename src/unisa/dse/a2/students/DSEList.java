package unisa.dse.a2.students;

import org.junit.jupiter.params.shadow.com.univocity.parsers.common.processor.core.AbstractMasterDetailListProcessor;

import unisa.dse.a2.interfaces.List;

/**
 * @author JackK
 *
 */
public class DSEList implements List {
	
	public Node head;
	private Node tail;

	public DSEList() {
		head = null;
		tail = null;
	}
	public DSEList(Node head_) {
		head = head_;
	}
	
	//Takes a list then adds each element into a new list
	public DSEList(DSEList other) { // Copy constructor. 
		if(other.head == null) {
			head = null;
			tail = null;
			return;
		}
			
		head = new Node(null, null, other.head.getString());
		Node current = head;
		Node next = other.head.next;
		
		while(next != null) {
			Node newNode = new Node(null, current, next.getString());
			current.next = newNode;
			current = newNode;
			next = next.next;
		}
		
		tail = current;
	}

	//remove the String at the parameter's index
	public String remove(int index) {

	}

	//returns the index of the String parameter 
	public int indexOf(String obj) {
	}
	
	//returns String at parameter's index
	public String get(int index) {
	}

	//checks if there is a list
	public boolean isEmpty() {
		return head == null;
	}

	//return the size of the list
	public int size() {
		Node temp = head;
		int count = 0;
		while(temp != null) {
			count++;
			temp = temp.next;
		}
		return count;
	}
	
	//Take each element of the list a writes them to a string 
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Node current = head;
		
		while(current != null) {
			sb.append(current.getString());
			if(current.next != null) {
				sb.append(" ");
			}
			current = current.next;
		}
		return sb.toString();
	}

	//add the parameter String at of the end of the list
	public boolean add(String obj) {
		Node n = new Node(null, tail, obj);
		n.next = null;
		
		if(head == null) {
			n.prev = null;
			head = n;
			tail = n;
		} else {
			tail.next = n;
			n.prev = tail;
			tail = n;
		}
		
		return true;
	}

	//add String at parameter's index
	public boolean add(int index, String obj) {
	}

	//searches list for parameter's String return true if found
	public boolean contains(String obj) {
	}

	//removes the parameter's String form the list
	public boolean remove(String obj) {
	}
	
	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public boolean equals(Object other) {
		if(this == other) {
			return true;
		}
		
		if(other == null || getClass() != other.getClass()) {
			return false;
		}
		
		DSEList otherList = (DSEList) other;
		
		if(this.size() != otherList.size()) {
			return false;
		}
		
		return true;
	}
	
}
