import java.util.Iterator;
import java.util.NoSuchElementException;

//Title: Bag class
//Author: Ahmet Kaan Toprakçıoğlu
//ID: 12742035240
//Section: 1
//Assignment: 4
//Description: This class is a bag class.
//-----------------------------------------------------
public class Bag<Item> implements Iterable<Item> {

	private Node<Item> first; // beginning of bag
	private int n;

	private int Id; // cordinates

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	private static class Node<Item> {
		// -----------------------------------------------------
		// Summary: This class is an inner class.
		// Precondition: item must be generic generic.
		// Postcondition: There is no postcondition
		// -----------------------------------------------------
		private Item item;
		private Node<Item> next;
	}

	public Bag(int Id) {
		// -----------------------------------------------------
		// Summary: This method is contructor set elements from parameters.
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition
		// -----------------------------------------------------
		this.Id = Id;
		first = null;
		n = 0;
	}

	public Bag() {
		// -----------------------------------------------------
		// Summary: This method is contructor define the firt.
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition
		// -----------------------------------------------------
		first = null;
		n = 0;
	}

	public boolean isEmpty() {
		// -----------------------------------------------------
		// Summary: This method check whether it is empty.
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition
		// -----------------------------------------------------
		return first == null;
	}

	public int size() {
		// -----------------------------------------------------
		// Summary: This method return size of bag.
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition
		// -----------------------------------------------------
		return n;
	}

	public void add(Item item) {
		// -----------------------------------------------------
		// Summary: This method add elements what in parameter to bag.
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition
		// -----------------------------------------------------
		Node<Item> oldfirst = first;
		first = new Node<Item>();
		first.item = item;
		first.next = oldfirst;
		n++;
	}

	public Iterator<Item> iterator() {
		// -----------------------------------------------------
		// Summary: Returns an iterator that iterates over the items in this bag in
		// arbitrary order.
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition
		// -----------------------------------------------------
		return new LinkedIterator(first);
	}

	private class LinkedIterator implements Iterator<Item> {
		// -----------------------------------------------------
		// Summary: An iterator, doesn't implement remove() since it's optional
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition
		// -----------------------------------------------------
		private Node<Item> current;

		public LinkedIterator(Node<Item> first) {
			current = first;
		}

		public boolean hasNext() {
			return current != null;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
	}
}