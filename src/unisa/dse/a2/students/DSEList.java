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

		for(int i = 0; i < index; i++) { // iterates to the point of the index
			current = current.next;
		}
		
		String removeData = current.getString();
		
		if(current == head) { // updates head to point to the next node if the head is to be deleted
			head = current.next;
		}
		if(current == tail) { // updates tail to point to the previous node if the tail is to be deleted
			tail = current.prev;
		}
		
		if(current.prev != null) { // updates the previous node to point to current's next (e.g. A->B->C->D, current = C, after this, B.next = D)
			current.prev.next = current.next;
		}
		if(current.next != null) { // updates the next node to point to current's previous (e.g. A->B->C->D, current = C, after this, D.prev would now equal B)
			current.next.prev = current.prev;
		}
		
		return removeData;

	}

	//returns the index of the String parameter 
	public int indexOf(String obj) {
		Node temp = head;
		int index = 0;
		while(temp != null) { // traverse the list to find the position of the string
			String string = temp.getString();
			if(string.equals(obj)) { // if the strings are equal 
				return index;
			}
			temp = temp.next; // move to next node
			index++; // increment index counter
		}
		return -1; // if string not found, -1 is returned
	}
	
	//returns String at parameter's index
	public String get(int index) {
		if(index < 0) { // if index is negative
			return null;
		}
		
		Node current = head;
		for(int i = 0; i < index; i++) { // traverses the list from the head node
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
			throw new IndexOutOfBoundsException(); // throws exception for invalid index
		}
		
		Node current = new Node(null, null, obj); // creates a new node with obj data
		
		if(index == 0) { // if index is at the beginning of the list
			current.next = head;
			if(head != null) {
				head.prev = current;
			}
			head = current; // head now points to the new node
			if(tail == null) {
				tail = current; // if the list is empty, the new node also becomes the tail
			}
		}
		else if(index == size()) { // if inserting at end of list
			return add(obj); // just calls the existing add method as that is designed to add to the end of the list
		}
		else { // if inserting somewhere within the middle of the list
			Node temp = head;
			for(int i = 0; i < index - 1; i++) {
				temp = temp.next; // iterate over the list to one before index, so we can correctly add to the list
			}
			
			current.next = temp.next; // sets the next pointer to where temp was pointing
			current.prev = temp; // sets the previous pointer to temp
			if(temp.next != null) {
				temp.next.prev = current; // update the prev pointer of temp's next to point to current
			}
			temp.next = current; // set temp's next to point to current
		
		}
		return true;
		
	}

	//searches list for parameter's String return true if found
	public boolean contains(String obj) {
		Node current = head;
		
		while(current != null) { // traverse the list until the end
			if(current.getString().equals(obj)) { // checks if obj matches the current's string
				return true;
			}
			current = current.next;
		}
		return false; // returns false if obj not found
	}

	//removes the parameter's String form the list
	public boolean remove(String obj) {
		Node current = head;
		
		while(current != null) { // traverse the list until end
			if(current.getString().equals(obj)) { // if current and obj are equal
				if(current == head) {
					head = current.next; // head now points to current's next, effectively removing it from the list
				}
				if(current == tail) {
					tail = current.prev; // similarly, tail is now set to current's previous, detaching the current node from the list
				}
				
				if(current.prev != null) { // if current has a previous node
					current.prev.next = current.next; // links the current's previous node to the current's next node, removing it from the list
				}
				if(current.next != null) { // if current has a next node
					current.next.prev = current.prev; // links the current's next node to current's previous node, removing it from the list
				}
				return true; // indicates successful removal
			}
			current = current.next; // move to next node
		}
		return false; // indicates unsuccessful removal
	}
	
	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public boolean equals(Object other) {
		if(this == other) { // if object is being compared to itself
			return true;
		}
		
		if(other == null || getClass() != other.getClass()) { // if object is null or belongs to another class
			return false;
		}
		
		DSEList otherList = (DSEList) other; // cast the "other" object to DSEList
		
		if(this.size() != otherList.size()) { // if the sizes of the lists are different
			return false;
		}
		
		Node current = this.head; // pointer to traverse the current list
		Node otherCurrent = otherList.head; // pointer to traverse the other list
		
		while(otherCurrent != null) { // loop that iterates over both lists
			if(!current.getString().equals(otherCurrent.getString())) { // if the string values are different
				return false;
			}
			
			// move to next node
			current = current.next;
			otherCurrent = otherCurrent.next;
		}
		
		
		return true; // if all elements are equal
	}
	
}
