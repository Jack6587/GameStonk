package unisa.dse.a2.students;

import unisa.dse.a2.interfaces.ListGeneric;

/**
 * @author JackK
 *
 */
public class DSEListGeneric<E> implements ListGeneric<E> {
	
	public NodeGeneric<E> head;
	private NodeGeneric<E> tail;

	public DSEListGeneric() {
		head = null;
		tail = null;
	}
	public DSEListGeneric(NodeGeneric<E> head_) {
		head = head_;
	}
	
	//Takes a list then adds each element into a new list
	public DSEListGeneric(DSEListGeneric<E> other) { // Copy constructor. 
		if(other.head == null) {
			head = null;
			tail = null;
			return;
		}
			
		head = new NodeGeneric<>(null, null, other.head.get());
		NodeGeneric<E> current = head;
		NodeGeneric<E> next = other.head.next;
		
		while(next != null) {
			NodeGeneric<E> newNode = new NodeGeneric<>(null, current, next.getString());
			current.next = newNode;
			current = newNode;
			next = next.next;
		}
		
		tail = current;
	}
	}

	//remove and return the item at the parameter's index
	public void remove(int index) {

	}

	//returns the index of the String parameter 
	public int indexOf(String obj) {
	}
	
	//returns item at parameter's index
	public void get(int index) {
	}

	//checks if there is a list
	public boolean isEmpty() {
	}

	//return the size of the list
	public int size() {
		NodeGeneric<E> temp = head;
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
		NodeGeneric<E> current = head;
		
		while(current != null) {
			sb.append(current.get());
			if(current.next != null) {
				sb.append(" ");
			}
			current = current.next;
		}
		return sb.toString();
	}

	//add the parameter item at of the end of the list
	public boolean add(E obj) {
		NodeGeneric<E> n = new NodeGeneric<>(null, tail, obj);
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

	//add item at parameter's index
	public boolean add(int index, Object obj) {
	}

	//searches list for parameter's String return true if found
	public boolean contains(Object obj) {
	}

	//removes the parameter's item form the list
	public boolean remove(Object obj) {
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
		
		DSEListGeneric<E> otherList = (DSEListGeneric<E>) other;
		
		if(this.size() != otherList.size()) {
			return false;
		}
		
		NodeGeneric<E> current = this.head;
		NodeGeneric<E> otherCurrent = otherList.head;
		
		while(otherCurrent != null) {
			if(!current.get().equals(otherCurrent.get())) {
				return false;
			}
			
			current = current.next;
			otherCurrent = otherCurrent.next;
		}
		
		return true;
	}
	
}
