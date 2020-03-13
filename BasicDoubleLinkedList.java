/**
 * BasicDoubleLinkedList is a generic class that implements Iterable<T>
 * @author Shenabeth Jenkins
 *
 * @param <T>
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class BasicDoubleLinkedList<T> implements Iterable<T> {
	//fields
	protected Node first;
	protected Node last;
	protected int size = 0;


	//constructor
	public BasicDoubleLinkedList() {
		first = null;
		last = null;
	}


	//methods
	/**
	 * @return the value of the instance variable you use to keep track of size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Adds an element to the end of the list
	 * @param data the data for the Node within the linked list
	 * @return reference to the current object
	 */
	public BasicDoubleLinkedList<T> addToEnd(T data) {
		//add to the last variable
		if (last == null) {
			last = new Node(data);
			first = last;
		}

		//otherwise create a space
		else {
			Node node2 = new Node(data);
			last.next = node2;
			node2.previous = last;
			last = node2;
		}

		//increase size by 1
		size++;

		//return
		return this;
	}
 
	/**
	 * Adds element to the front of the list
	 * @param data the data for the Node within the linked list
	 * @return reference to the current object
	 */
	public BasicDoubleLinkedList<T> addToFront(T data) {
		//add to the first variable
		if (first == null) {
			first = new Node(data);
			last = first;
		}

		//otherwise create a space
		else {
			Node node3 = new Node(data);
			first.previous = node3;
			node3.next = first;
			first = node3;
		}

		//increase size by 1
		size++;

		//return
		return this;
	}

	/**
	 * Returns but does not remove the first element from the list.
	 * If there are no elements the method returns null.
	 * @return the data element or null
	 */
	public T getFirst() {
		return first.data;
	}

	/**
	 * Returns but does not remove the last element from the list.
	 * If there are no elements the method returns null
	 * @return the data element or null
	 */
	public T getLast() {
		return last.data;
	}

	/**
	 * Removes and returns the first element from the list.
	 * If there are no elements the method returns null
	 * @return data element or null
	 */
	public T retrieveFirstElement() {
		//create variables
		Node element1 = first;
		
		//if there is nothing in the first node create and add it
		if (first != null) {
			//first is empty
			if (first.next != null) {
				first = first.next;
				first.previous = null;
			}
			
			//otherwise first and last will be null
			else {
				first = null;
				last = null;
			}
			
			//return the data element
			return element1.data;
		}
		
		//otherwise return null
		else { return null; }
	}

	/**Removes and returns the last element from the list.
	 * If there are no elements the method returns null
	 * @return data element or null
	 */
	public T retrieveLastElement() {
		//create variables
		Node element2 = last;

		//if there is nothing in the last node create and add it
		if (last != null) {
			//last is empty
			if (last.next != null) {
				last = last.next;
				last.previous = null;
			}

			//otherwise first and last will be null
			else {
				first = null;
				last = null;
			}

			//return the data element
			return element2.data;
		}

		//otherwise return null
		else { return null; }
	}

	/**
	 * Removes the first instance of the targetData from the list
	 * @param targetData the data element to be removed
	 * @param comparator the comparator to determine equality of data elements
	 * @return data element or null
	 */
	public BasicDoubleLinkedList<T> remove(T targetData, Comparator<T> comparator) {
		//first node
		Node firstNode = first;
		
		//while the first node has a value, compare (comparator)
		while(firstNode != null) {
			//comparator
			if (comparator.compare(firstNode.data, targetData) == 0) {
				//first node
				if (firstNode == first) {
					if (first.next != null) {
						first = first.next;
						first.previous = null;
					}
					
					else {
						first = null;
						last = null;
					}
				}
				
				//last node
				else if (firstNode == last) {
					if (last.previous != null) {
						last = last.previous;
						last.next = null;
					}
					
					else {
						first = null;
						last = null;
					}
				}
				
				//otherwise
				else {
					firstNode.previous.next = firstNode.next;
					firstNode.next.previous = firstNode.previous;
				}
				
				//break from loop
				break;
			}
			//change node
			firstNode = firstNode.next;
		}
		//subtract size by 1
		size--;
		
		//return
		return this;
	}


	//overridden methods
	/**
	 * defines the methods of hasNext(), next(), hasPrevious() and previous()
	 */
	@Override
	public ListIterator<T> iterator() {
		//new iterator
		MyListIterator iterator = new MyListIterator();
		
		//return iterator
        return iterator;
	}

	//array
	/**
	 * Returns an arraylist of the items in the list from head of list to tail of list
	 * @return an arraylist of the items in the list
	 */
	public ArrayList<T> toArrayList() {
		//create arraylist
		ArrayList<T> arrayList = new ArrayList<>();
		
		//node
		Node head = first;
		
		//while there are still elements, add to arraylist
		while(head != null) {
			arrayList.add(head.data);
			head = head.next;
		}
		
		//return
		return arrayList; 
	}

	
	
	
	
	
	/**
	 * Node class
	 * @author Shenabeth Jenkins
	 *
	 */
	protected class Node {
		//fields
		protected Node next;
		protected Node previous;
		protected T data;
		
		
		//constructor
		/**
		 * 1 arg constructor
		 * @param e element
		 */
		Node(T e) {
            this.next = null;
            this.previous = null;
            this.data = e;
		}
		
		/**
		 * 3 arg constructor
		 * @param previous previous element
		 * @param element current element
		 * @param next next element
		 */
		Node(Node previous, T element, Node next) {
			this.data = element;
			this.next = next;
			this.previous = previous;
		}
	}






	/**		
	 * This is an inner class that implements ListIterator (for the iterator method), head and tail references, 
	 * and an integer representing the list size. However only the hasNext(), next(), hasPrevious() and previous() 
	 * methods of ListIterator need to be implemented, all other methods can throw the UnsupportedOperationException
	 * @author Shenabeth Jenkins
	 *
	 */
	protected class MyListIterator implements ListIterator<T> {
		//fields
		Node point;

		
		//constructor
		/**
		 * no arg constructor sets the point element
		 */
		public MyListIterator() {
			//previous, element, next
            point = new Node(null, null, first);
        }


		//overridden methods
		/**
		 * checks is next element exists
		 */
		@Override
		public boolean hasNext() {
			return (point.next != null);
		}
		
		/**
		 * checks if previous element exists
		 */
		@Override
		public boolean hasPrevious() {
			return (point.previous != null);
		}

		/**
		 * return the next element
		 */
		@Override
		public T next() {
			//check if element exists, throw exception
			if (!hasNext()) {
                throw new NoSuchElementException("No next element");
            }
			
			//otherwise
			else {
                point.previous = point.next;
                point.next = point.previous.next;
                return point.previous.data;
            }     
		}
		
		/**
		 * return the previous element
		 */
		@Override
		public T previous() {
			//check if element exists, throw exception
			if (!hasPrevious()) {
                throw new NoSuchElementException("No previous element");
            }
			
			//otherwise the point exists
			else {
                point.next = point.previous;
                point.previous = point.next.previous;
                return point.next.data;            
            }
		}
		
		
		//exceptions
		/**
		 * @throws UnsupportedOperationException
		 */
		public void remove() throws UnsupportedOperationException {
			throw new UnsupportedOperationException("Not supported yet");
		}

		/**
		 * @throws UnsupportedOperationException
		 */
		@Override
		public void add(T e) throws UnsupportedOperationException {
			throw new UnsupportedOperationException("Not supported yet");
		}

		/**
		 * @throws UnsupportedOperationException
		 */
		@Override
		public int nextIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException("Not supported yet");
		}

		/**
		 * @throws UnsupportedOperationException
		 */
		@Override
		public int previousIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException("Not supported yet");
		}

		/**
		 * @throws UnsupportedOperationException
		 */
		@Override
		public void set(T e) throws UnsupportedOperationException {
			throw new UnsupportedOperationException("Not supported yet");
		}


	}



}