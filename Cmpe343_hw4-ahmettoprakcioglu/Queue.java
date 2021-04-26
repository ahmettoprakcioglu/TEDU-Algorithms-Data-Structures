import java.util.Iterator;
import java.util.NoSuchElementException;

//-----------------------------------------------------
//Title: Queue
//Author: Ahmet Kaan Toprakçıoğlu
//ID: 12742035240
//Section: 1
//Assignment: 4
//Description: This class defines a queue
//-----------------------------------------------------
public class Queue<Item> implements Iterable<Item> {
	private Node<Item> first; // beginning of queue
	private Node<Item> last; // end of queue
	private int n; // number of elements on queue

	private static class Node<Item> {
		private Item item;
		private Node<Item> next;
	}

	// --------------------------------------------------------------
	// Summary: Initializes an empty queue.
	// Precondition: first is a node and last is a node
	// Postcondition: The value of the variable is set.
	// --------------------------------------------------------------
	public Queue() {
		first = null;
		last = null;
		n = 0;
	}

	// --------------------------------------------------------------
	// Summary: Returns true if this queue is empty.
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	public boolean isEmpty() {
		return first == null;
	}

	// --------------------------------------------------------------
	// Summary: Returns the number of items in this queue.
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	public int size() {
		return n;
	}

	// --------------------------------------------------------------
	// Summary: Returns the item least recently added to this queue. The item least
	// recently added to this queue
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	public Item peek() {
		if (isEmpty())
			throw new NoSuchElementException("Queue underflow");
		return first.item;
	}

	// --------------------------------------------------------------
	// Summary: Adds the item to this queue.
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	public void enqueue(Item item) {
		Node<Item> oldlast = last;
		last = new Node<Item>();
		last.item = item;
		last.next = null;
		if (isEmpty())
			first = last;
		else
			oldlast.next = last;
		n++;
	}

	// --------------------------------------------------------------
	// Summary: Removes and returns the item on this queue that was least recently
	// added. the item on this queue that was least recently added
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	public Item dequeue() {
		if (isEmpty())
			throw new NoSuchElementException("Queue underflow");
		Item item = first.item;
		first = first.next;
		n--;
		if (isEmpty())
			last = null; // to avoid loitering
		return item;
	}

	// --------------------------------------------------------------
	// Summary: Returns a string representation of this queue.
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (Item item : this) {
			s.append(item);
			s.append(' ');
		}
		return s.toString();
	}

	// --------------------------------------------------------------
	// Summary: Returns an iterator that iterates over the items in this queue in
	// FIFO order.
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	public Iterator<Item> iterator() {
		return new LinkedIterator(first);
	}

	// --------------------------------------------------------------
	// Summary: An iterator, doesn't implement remove() since it's optional
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	private class LinkedIterator implements Iterator<Item> {
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