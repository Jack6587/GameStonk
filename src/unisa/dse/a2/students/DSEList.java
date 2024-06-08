package unisa.dse.a2.students;

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
	public DSEList(DSEList other) { // Copy constructor 
		if(other.head == null) { // in the case the original list is empty
			head = null;
			tail = null; // head and tail set to null
			return;
		}
			
		head = new Node(null, null, other.head.getString()); // creates a new head node with parameter's string value/data
		Node current = head; // current points to head (previously created)
		Node next = other.head.next; // next points to the next node of the head or the original list
		
		while(next != null) { // iterates over list
			Node newNode = new Node(null, current, next.getString()); // creates a new node each iteration with same data as original list
			current.next = newNode; // next pointer is set to newly created node
			current = newNode; // current now set to newNode
			next = next.next; // set next to point to the next node in the list
		}
		
		tail = current; // when iteration is complete, the last node (current) is set to tail
	}

	//remove the String at the parameter's index
	public String remove(int index) {
		Node current = head;

		for(int i = 0; i < index; i++) {
			current = current.next;
		}
		
		String removeData = current.getString();
		
		if(current == head) {
			head = current.next;
		}
		if(current == tail) {
			tail = current.prev;
		}
		
		if(current.prev != null) {
			current.prev.next = current.next;
		}
		if(current.next != null) {
			current.next.prev = current.prev;
		}
		
		return removeData;

	}

	//returns the index of the String parameter 
	public int indexOf(String obj) {
		Node temp = head;
		int index = 0;
		while(temp != null) {
			String string = temp.getString();
			if(string.equals(obj)) {
				return index;
			}
			temp = temp.next;
			index++;
		}
		return -1;
	}
	
	//returns String at parameter's index
	public String get(int index) {
		if(index < 0) {
			return null;
		}
		
		Node current = head;
		for(int i = 0; i < index; i++) {
			if(current == null) {
				return null;
			}
			
			current = current.next;
		}
		
		return current.getString();
	}

	//checks if there is a list
	public boolean isEmpty() {
		return head == null; // returns true if the list's head is null, demonstrating that the list is empty
	}

	//return the size of the list
	public int size() {
		Node temp = head; // temp node created to traverse the list, starts with head
		int count = 0; // counter that counts number of nodes
		while(temp != null) { // loop that traverses the list
			count++; // increments count
			temp = temp.next; // goes to next node
		}
		return count; // return total count
	}
	
	//Take each element of the list a writes them to a string 
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(); // initialise a new StringBuilder
		Node current = head; // node created to traverse the list, starts at head
		
		while(current != null) { // loop that traverses the list
			sb.append(current.getString()); // appends string representation to the StringBuilder
			if(current.next != null) {
				sb.append(" "); // adds a space in the string as long as there is an item present in next, so each value is spaced out but no trailing white space remains at end of string
			}
			current = current.next; // points to next node and repeats
		}
		return sb.toString(); // returns string representation
	}

	//add the parameter String at of the end of the list
	public boolean add(String obj) {
		Node n = new Node(null, tail, obj);	// create new node. Next is null and previous is tail as this node is the new tail
		if(head == null) { // if list is empty
			n.prev = null; // set previous to null
			head = n;
			tail = n; // this makes the node the first in the list as it becomes both head and tail
		} else { // if list is not empty
			tail.next = n; // sets the end of the list's next to be the new node
			n.prev = tail; // new node's previous pointer now points to the list's tail
			tail = n; // new node becomes the tail
		}
		return true;
	}

	//add String at parameter's index
	public boolean add(int index, String obj) {
		if(index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		
		Node current = new Node(null, null, obj);
		
		if(index == 0) {
			current.next = head;
			if(head != null) {
				head.prev = current;
			}
			head = current;
			if(tail == null) {
				tail = current;
			}
		}
		else if(index == size()) {
			return add(obj);
		}
		else {
			Node temp = head;
			for(int i = 0; i < index - 1; i++) {
				temp = temp.next;
			}
			
			current.next = temp.next;
			current.prev = temp;
			if(temp.next != null) {
				temp.next.prev = current;
			}
			temp.next = current;
		
		}
		return true;
		
	}

	//searches list for parameter's String return true if found
	public boolean contains(String obj) {
		Node current = head;
		
		while(current != null) {
			if(current.getString().equals(obj)) {
				return true;
			}
			current = current.next;
		}
		return false;
	}

	//removes the parameter's String form the list
	public boolean remove(String obj) {
		Node current = head;
		
		while(current != null) {
			if(current.getString().equals(obj)) {				
				if(current == head) {
					head = current.next;
				}
				if(current == tail) {
					tail = current.prev;
				}
				
				if(current.prev != null) {
					current.prev.next = current.next;
				}
				if(current.next != null) {
					current.next.prev = current.prev;
				}
				return true;
			}
			current = current.next;
		}
		return false;
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
		
		Node current = this.head;
		Node otherCurrent = otherList.head;
		
		while(otherCurrent != null) {
			if(!current.getString().equals(otherCurrent.getString())) {
				return false;
			}
			
			current = current.next;
			otherCurrent = otherCurrent.next;
		}
		
		return true;
	}
	
}
