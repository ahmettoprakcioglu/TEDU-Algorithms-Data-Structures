import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bag<Item> implements Iterable<Item> {

	// -----------------------------------------------------
	// Title:Bag<Item>
	// Author: Ahmet Kaan Toprakçıoğlu
	// ID: 12742035240
	// Section: 1
	// Assignment: 3
	// Description: This class define Bag Object
	// -----------------------------------------------------

	public Node<Item> first;
	public Node<Item> tail;
	public int n;
	public String name;
	public int ID;
	
	
	

	public static class Node<Item> {

		public Item item;
		public Node<Item> next;
		
	}

	public Bag(String name, int ID) {
		// --------------------------------------------------------
		// Summary: Constructor an empty bag.
		// Precondition: String name, int ID
		// Postcondition: Constructor of an empty bag.
		// --------------------------------------------------------
		
		this.name = name;
		first = null;
		n = 0;
	}

	public Bag(int ID) {
		// --------------------------------------------------------
		// Summary: Initializes an empty bag.
		// Precondition: int ID
		// Postcondition: Initializes of an empty bag.
		// --------------------------------------------------------
		this.ID = ID;
		first = null;
		n = 0;
	}

	public boolean isEmpty() {
		// --------------------------------------------------------
		// Summary: check is empty bag.
		// Precondition: There is no precondition.
		// Postcondition: return true if its empty.
		// --------------------------------------------------------
		return first == null;
	}

	public int size() {
		// --------------------------------------------------------
		// Summary: The number of items in this bag..
		// Precondition: There is no precondition.
		// Postcondition: Returns the number of items in this bag..
		// --------------------------------------------------------
		return n;
	}

	

	public String getName() {
		// --------------------------------------------------------
		// Summary: get Name cordinates of a bag
		// Precondition: There is no precondition.
		// Postcondition: Returns Name cordinate of a bag..
		// --------------------------------------------------------
		return name;
	}

	

	public void add(Item item) {
		Node<Item> newItem = new Node<Item>();
		// --------------------------------------------------------
		// Summary: Adds the item to this bag
		// Precondition: Item item.
		// Postcondition: Adds the item to this bag..
		// --------------------------------------------------------
		if (isEmpty()) {
			first = newItem;
			first.item = item;
			first.next = null;
			
			
			tail = newItem;
			tail.item = item;
			
			
			n++;
		}
		 
		else {
			tail.next = newItem;
			newItem.item = item;
			newItem.next = null;
			tail = newItem;
			n++;
		}
		
		
	}
	public void remove() {
		// --------------------------------------------------------
		// Summary: Remove item to this bag
				// Precondition: Item item.
				// Postcondition: Adds the item to this bag..
				// --------------------------------------------------------
		Node<Item> finger = new Node<Item>();
		 
		if (!isEmpty()) {
			
			
			if (tail == first) {
				tail = null;
				first = null;
			}
			else {
				finger = first;
				while(finger.next !=tail) {
					finger = finger.next;
				}
				finger.next = null;
				tail = finger;
			}
			
			
			n--;
		}

	}


	public Iterator<Item> iterator() {
		// --------------------------------------------------------
		// Summary: Iterator
		// Precondition: Item item.
		// Postcondition: Returns an iterator that iterates over the items in this bag
		// in arbitrary order.
		// --------------------------------------------------------
		return new LinkedIterator(first);
	}

	private class LinkedIterator implements Iterator<Item> {
		private Node<Item> current;

		public LinkedIterator(Node<Item> first) {
			current = first;
		}

		public boolean hasNext() {
			// return true if it has next.
			return current != null;
		}

		public void remove() {
			// an iterator, doesn't implement remove() since it's optional
			throw new UnsupportedOperationException();
		}

		public Item next() {
			// return item's next
			if (!hasNext())
				throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
	}
}