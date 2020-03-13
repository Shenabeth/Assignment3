/**
 * SortedDoubleLinkedList is a generic class that extends BasicDoubleLinkedList<T>
 * @author Shenabeth Jenkins
 *
 * @param <T>
 */

import java.util.Comparator;
import java.util.ListIterator;

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {
	//fields
	private Comparator<T> comparator = null;


	//constructor
	/**
	 * 1 arg constructor for double linked list
	 * @param comparator
	 */
	public SortedDoubleLinkedList(Comparator<T> comparator2) {
		this.comparator = comparator2;

	}


	//methods
	/**
	 * Inserts the specified element at the correct position in the sorted list
	 * @param data - the data to be added to the list
	 * @return a reference to the current object
	 */
	public SortedDoubleLinkedList<T> add(T data) {
		//create variable
		Node node = new Node(data);

		//adding new node
		//equal to zero
		if (size == 0) {
			first = node;
			last = node;
		}

		//less than zero
		else if (comparator.compare(last.data, data) < 0) {
			last.next = node;
			node.previous = last;
			last = node;
		}

		//greater than zero
		else if (comparator.compare(first.data, data) > 0) {
			first.previous = node;
			node.next = first;
			first = node;
		}


		//otherwise
		else {
			//new node
			Node lookFor = first;
			
			//loop forever until it is empty
			while (lookFor != null) {
				//compare
				if (comparator.compare(lookFor.data, data) <= 0) {
					//nodes
					Node previousOne = lookFor;
					Node nextOne = lookFor.next;
					
					nextOne.previous = previousOne.next = node;
					node.next = nextOne;
					node.previous = previousOne;   
				}

				//next variable
				lookFor = lookFor.next;
			}
		}

		//add to size
		size++;

		//return
		return this;
	}

	/**
	 * Implements the iterator by calling the super class iterator method
	 */
	public ListIterator<T> iterator() {
		return super.iterator();
	}

	/**
	 * Implements the remove operation by calling the super class remove method
	 */
	public SortedDoubleLinkedList<T> remove(T data, Comparator<T> comparator) {
		super.remove(data, comparator);
		return this;
	}


	//overridden methods
	/**
	 * This operation is invalid for a sorted list
	 */
	@Override
	public BasicDoubleLinkedList<T> addToEnd(T data) {
		throw new UnsupportedOperationException("Invalid operation for sorted list.");
	}

	/**
	 * This operation is invalid for a sorted list
	 */
	@Override
	public BasicDoubleLinkedList<T> addToFront(T data) {
		throw new UnsupportedOperationException("Invalid operation for sorted list.");
	}

}
