//-----------------------------------------------------
// Title: SequentialSearchST
// Author: Ahmet Kaan Toprakçıoğlu
// ID: 12742035240
// Section: 01
// Assignment: 1
// Description: This class defines a SequentialSearchST
//-----------------------------------------------------

import java.util.ArrayList;
import java.util.LinkedList;

public class SequentialSearchST<Key, Value> {
	private int n; // number of key-value pairs
	private Node first; // the linked list of key-value pairs

	// a helper linked list data type
	private class Node {
		// --------------------------------------------------------
		// Summary: This is an inner class
		// Precondition: Key and Value are generic.
		// Postcondition: There is no postcondition
		// --------------------------------------------------------
		private Key key;
		private Value val;
		private Node next;

		public Node(Key key, Value val, Node next) {
			// --------------------------------------------------------
			// Summary: This is a constructor method
			// Precondition: There is no precondition.
			// Postcondition: There are set values what have parameter in method.
			// --------------------------------------------------------
			this.key = key;
			this.val = val;
			this.next = next;
		}
	}

	/**
	 * Initializes an empty symbol table.
	 */
	public SequentialSearchST() {
	}

	/**
	 * Returns the number of key-value pairs in this symbol table.
	 * 
	 * @return the number of key-value pairs in this symbol table
	 */
	public int size() {
		// --------------------------------------------------------
		// Summary: This method return the size of each size hashtable of part.
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition.
		// --------------------------------------------------------
		return n;
	}

	/**
	 * Is this symbol table empty?
	 * 
	 * @return {@code true} if this symbol table is empty and {@code false}
	 *         otherwise
	 */
	public boolean isEmpty() {
		// --------------------------------------------------------
		// Summary: This method control empty that the SequentialSearchST class.
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition.
		// --------------------------------------------------------
		return size() == 0;
	}

	public boolean contains(Key key) {
		// --------------------------------------------------------
		// Summary: This method search and if it find or not find Key what defines in
		// the parameter return true or false .
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition.
		// --------------------------------------------------------
		return get(key) != null;
	}

	public Value get(Key key) {
		// --------------------------------------------------------
		// Summary: This method returns the value specified in the parameter.
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition.
		// --------------------------------------------------------
		for (Node x = first; x != null; x = x.next) {
			if (key.equals(x.key))
				return x.val;
		}
		return null;
	}

	public ArrayList<Key> findKey() {
		// --------------------------------------------------------
		// Summary: This method finds all Nodes's value and put the arraylist for
		// determine the most popular hashtag.
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition.
		// --------------------------------------------------------
		ArrayList<Key> value = new ArrayList<Key>();
		for (Node i = first; i != null; i = i.next) {
			if (i.key != null) {
				value.add(i.key);
			}
		}
		return value;
	}

	/**
	 * Inserts the key-value pair into the symbol table, overwriting the old value
	 * with the new value if the key is already in the symbol table. If the value is
	 * {@code null}, this effectively deletes the key from the symbol table.
	 * 
	 * @param key the key
	 * @param val the value
	 */

	public void put(Key key, Value val) {
		// --------------------------------------------------------
		// Summary: This method puts specified in the parameter.
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition.
		// --------------------------------------------------------
		if (val == null) {
			delete(key);
			return;
		}

		for (Node x = first; x != null; x = x.next) {
			if (key.equals(x.key)) {
				x.val = val;
				return;
			}
		}
		first = new Node(key, val, first);
		n++;
	}

	public void delete(Key key) {
		// --------------------------------------------------------
		// Summary: Removes the key and associated value from the symbol table. (if the
		// key is in the symbol table).
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition.
		// --------------------------------------------------------
		first = delete(first, key);
	}

	private Node delete(Node x, Key key) {
		// --------------------------------------------------------
		// Summary: delete key in linked list beginning at Node x
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition.
		// --------------------------------------------------------
		if (x == null)
			return null;
		if (key.equals(x.key)) {
			n--;
			return x.next;
		}
		x.next = delete(x.next, key);
		return x;
	}

	/**
	 * Returns all keys in the symbol table as an {@code Iterable}. To iterate over
	 * all of the keys in the symbol table named {@code st}, use the foreach
	 * notation: {@code for (Key key : st.keys())}.
	 * 
	 * @return all keys in the symbol table as an {@code Iterable}
	 */
	/*
	 * public Iterable<Key> keys() { Queue<Key> queue = new Queue<Key>(); for (Node
	 * x = first; x != null; x = x.next) queue.enqueue(x.key); return queue; }
	 */
}