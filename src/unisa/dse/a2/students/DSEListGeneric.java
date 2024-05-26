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
			
		head = new NodeGeneric<>(null, null, other.head.getString());
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
	}
	
	//Take each element of the list a writes them to a string 
	@Override
	public String toString() {
	}

	//add the parameter item at of the end of the list
	public boolean add(Object obj) {
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
		return true;
	}
	
}
