import java.util.Iterator;
import java.util.NoSuchElementException;

//-----------------------------------------------------
//Title: IndexMinPQ
//Author: Ahmet Kaan Toprakçıoğlu
//ID: 12742035240
//Section: 1
//Assignment: 4
//Description: This class defines an IndexMinPQ.
//-----------------------------------------------------
public class IndexMinPQ<Key extends Comparable<Key>> implements Iterable<Integer> {
	private int maxN; // maximum number of elements on PQ
	private int n; // number of elements on PQ
	private int[] pq; // binary heap using 1-based indexing
	private int[] qp; // inverse of pq - qp[pq[i]] = pq[qp[i]] = i
	private Key[] keys; // keys[i] = priority of i

	// --------------------------------------------------------------
	// Summary: Initializes an empty indexed priority queue with indices between
	// {@code 0} and {@code maxN - 1}.
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	public IndexMinPQ(int maxN) {
		if (maxN < 0)
			throw new IllegalArgumentException();
		this.maxN = maxN;
		n = 0;
		keys = (Key[]) new Comparable[maxN + 1]; // make this of length maxN??
		pq = new int[maxN + 1];
		qp = new int[maxN + 1]; // make this of length maxN??
		for (int i = 0; i <= maxN; i++)
			qp[i] = -1;
	}

	// --------------------------------------------------------------
	// Summary: Returns true if this priority queue is empty.
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	public boolean isEmpty() {
		return n == 0;
	}

	// --------------------------------------------------------------
	// Summary: Is {@code i} an index on this priority queue?
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	public boolean contains(int i) {
		validateIndex(i);
		return qp[i] != -1;
	}

	// --------------------------------------------------------------
	// Summary: Returns the number of keys on this priority queue.
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	public int size() {
		return n;
	}

	// --------------------------------------------------------------
	// Summary: Associates key with index.
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	public void insert(int i, Key key) {
		validateIndex(i);
		if (contains(i))
			throw new IllegalArgumentException("index is already in the priority queue");
		n++;
		qp[i] = n;
		pq[n] = i;
		keys[i] = key;
		swim(n);
	}

	// --------------------------------------------------------------
	// Summary:Returns an index associated with a minimum key. @return an index
	// associated with a minimum key
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	public int minIndex() {
		if (n == 0)
			throw new NoSuchElementException("Priority queue underflow");
		return pq[1];
	}

	// --------------------------------------------------------------
	// Summary:Returns a minimum key.
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	public Key minKey() {
		if (n == 0)
			throw new NoSuchElementException("Priority queue underflow");
		return keys[pq[1]];
	}

	// --------------------------------------------------------------
	// Summary: Removes a minimum key and returns its associated index.
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	public int delMin() {
		if (n == 0)
			throw new NoSuchElementException("Priority queue underflow");
		int min = pq[1];
		exch(1, n--);
		sink(1);
		assert min == pq[n + 1];
		qp[min] = -1; // delete
		keys[min] = null; // to help with garbage collection
		pq[n + 1] = -1; // not needed
		return min;
	}

	// --------------------------------------------------------------
	// Summary: Returns the key associated with index {@code i}.
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	public Key keyOf(int i) {
		validateIndex(i);
		if (!contains(i))
			throw new NoSuchElementException("index is not in the priority queue");
		else
			return keys[i];
	}

	// --------------------------------------------------------------
	// Summary: Change the key associated with index {@code i} to the specified
	// value.
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	public void changeKey(int i, Key key) {
		validateIndex(i);
		if (!contains(i))
			throw new NoSuchElementException("index is not in the priority queue");
		keys[i] = key;
		swim(qp[i]);
		sink(qp[i]);
	}

	// --------------------------------------------------------------
	// Summary: Change the key associated with index {@code i} to the specified
	// value.
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	@Deprecated
	public void change(int i, Key key) {
		changeKey(i, key);
	}

	// --------------------------------------------------------------
	// Summary: Decrease the key associated with index {@code i} to the specified
	// value. i the index of the key to decrease
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	public void decreaseKey(int i, Key key) {
		validateIndex(i);
		if (!contains(i))
			throw new NoSuchElementException("index is not in the priority queue");
		if (keys[i].compareTo(key) == 0)
			throw new IllegalArgumentException(
					"Calling decreaseKey() with a key equal to the key in the priority queue");
		if (keys[i].compareTo(key) < 0)
			throw new IllegalArgumentException(
					"Calling decreaseKey() with a key strictly greater than the key in the priority queue");
		keys[i] = key;
		swim(qp[i]);
	}

	// --------------------------------------------------------------
	// Summary: Increase the key associated with index {@code i} to the specified
	// value. i the index of the key to increase.
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	public void increaseKey(int i, Key key) {
		validateIndex(i);
		if (!contains(i))
			throw new NoSuchElementException("index is not in the priority queue");
		if (keys[i].compareTo(key) == 0)
			throw new IllegalArgumentException(
					"Calling increaseKey() with a key equal to the key in the priority queue");
		if (keys[i].compareTo(key) > 0)
			throw new IllegalArgumentException(
					"Calling increaseKey() with a key strictly less than the key in the priority queue");
		keys[i] = key;
		sink(qp[i]);
	}

	// --------------------------------------------------------------
	// Summary: Remove the key associated with index {@code i}. i the index of the
	// key to remove.
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	public void delete(int i) {
		validateIndex(i);
		if (!contains(i))
			throw new NoSuchElementException("index is not in the priority queue");
		int index = qp[i];
		exch(index, n--);
		swim(index);
		sink(index);
		keys[i] = null;
		qp[i] = -1;
	}

	// --------------------------------------------------------------
	// Summary: throw an IllegalArgumentException if i is an invalid index
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	private void validateIndex(int i) {
		if (i < 0)
			throw new IllegalArgumentException("index is negative: " + i);
		if (i >= maxN)
			throw new IllegalArgumentException("index >= capacity: " + i);
	}

	/***************************************************************************
	 * General helper functions.
	 ***************************************************************************/
	private boolean greater(int i, int j) {
		return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
	}

	private void exch(int i, int j) {
		int swap = pq[i];
		pq[i] = pq[j];
		pq[j] = swap;
		qp[pq[i]] = i;
		qp[pq[j]] = j;
	}

	/***************************************************************************
	 * Heap helper functions.
	 ***************************************************************************/
	private void swim(int k) {
		while (k > 1 && greater(k / 2, k)) {
			exch(k, k / 2);
			k = k / 2;
		}
	}

	private void sink(int k) {
		while (2 * k <= n) {
			int j = 2 * k;
			if (j < n && greater(j, j + 1))
				j++;
			if (!greater(k, j))
				break;
			exch(k, j);
			k = j;
		}
	}

	/***************************************************************************
	 * Iterators.
	 ***************************************************************************/

	// --------------------------------------------------------------
	// Summary: Returns an iterator that iterates over the keys on the priority
	// queue in ascending order.
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	public Iterator<Integer> iterator() {
		return new HeapIterator();
	}

	private class HeapIterator implements Iterator<Integer> {
		// create a new pq
		private IndexMinPQ<Key> copy;

		// add all elements to copy of heap
		// takes linear time since already in heap order so no keys move
		public HeapIterator() {
			copy = new IndexMinPQ<Key>(pq.length - 1);
			for (int i = 1; i <= n; i++)
				copy.insert(pq[i], keys[pq[i]]);
		}

		public boolean hasNext() {
			return !copy.isEmpty();
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Integer next() {
			if (!hasNext())
				throw new NoSuchElementException();
			return copy.delMin();
		}
	}

	/**
	 * Unit tests the {@code IndexMinPQ} data type.
	 *
	 * @param args the command-line arguments
	 */

}