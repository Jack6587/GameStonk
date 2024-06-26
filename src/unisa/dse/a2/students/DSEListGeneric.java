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
		NodeGeneric<E> next = other.head.next; // at every point where the Node class was in DSEList, NodeGeneric is there instead
		
		while(next != null) {
			NodeGeneric<E> newNode = new NodeGeneric<>(null, current, next.get()); // generic implementation of node
			current.next = newNode;
			current = newNode;
			next = next.next; // please note that the DSEList and generic class are almost identical, so detailed comments for each method can be found in DSEList class
		}
		
		tail = current;
	}

	//remove and return the item at the parameter's index
	public E remove(int index) {
		NodeGeneric<E> current = head; // note that E is the placeholder for element type

		for(int i = 0; i < index; i++) {
			current = current.next;
		}
		
		E removeData = current.get();
		
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
	public int indexOf(E obj) {
		NodeGeneric<E> temp = head;
		int index = 0;
		while(temp != null) {
			if(temp.get().equals(obj)) {
				return index;
			}
			temp = temp.next;
			index++;
		}
		return -1;
	}
	
	//returns item at parameter's index
	public E get(int index) {
		if(index < 0) {
			return null;
		}
		NodeGeneric<E> current = head;
		for(int i = 0; i < index; i++) {
			if(current == null) {
				return null;
			}
			
			current = current.next;
		}
		
		return current.get();
	}
	

	//checks if there is a list
	public boolean isEmpty() {
		return head == null;
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
	public boolean add(int index, E obj) {
		NodeGeneric<E> current = new NodeGeneric<E>(null, null, obj);
		
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
		} else {
			NodeGeneric<E> temp = head;
			for(int i = 0; i < index - 1; i++) {
				temp = temp.next;
			}
			
			current.next = temp.next;
			current.prev = temp;
			if(temp.prev != null) {
				temp.next.prev = current;
			}
			temp.next = current;
		}
		return true;
	}

	//searches list for parameter's String return true if found
	public boolean contains(E obj) {
		NodeGeneric<E> current = head;
		
		while(current != null) {
			if(current.get().equals(obj)) { // in DSEList, I used .getString. Here, that doesn't make sense as this is a generic class, so just .get() is used
				return true;
			}
			current = current.next;
		}
		return false;
	}

	//removes the parameter's item form the list
	public boolean remove(E obj) {
		NodeGeneric<E> current = head;
		
		while(current != null) {
			if(current.get().equals(obj)) {				
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
