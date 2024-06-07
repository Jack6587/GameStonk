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
