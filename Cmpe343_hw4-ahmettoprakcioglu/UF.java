//-----------------------------------------------------
//Title: UF
//Author: Ahmet Kaan Toprakçıoğlu
//ID: 12742035240
//Section: 1
//Assignment: 4
//Description: This class defines an UF.
//-----------------------------------------------------
public class UF {

	private int[] parent; // parent[i] = parent of i
	private byte[] rank; // rank[i] = rank of subtree rooted at i (never more than 31)
	private int count; // number of components

	// --------------------------------------------------------------
	// Summary: Initializes an empty union-find data structure with elements {@code
	// 0} through {@code n-1}.
	// Precondition: There is no precondition.
	// Postcondition: Initially, each elements is in its own set.
	// --------------------------------------------------------------
	public UF(int n) {
		if (n < 0)
			throw new IllegalArgumentException();
		count = n;
		parent = new int[n];
		rank = new byte[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
			rank[i] = 0;
		}
	}

	// --------------------------------------------------------------
	// Summary: Returns the canonical element of the set containing element {@code
	// p}.
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	public int find(int p) {
		validate(p);
		while (p != parent[p]) {
			parent[p] = parent[parent[p]]; // path compression by halving
			p = parent[p];
		}
		return p;
	}

	// --------------------------------------------------------------
	// Summary: Returns the number of sets.
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	public int count() {
		return count;
	}

	// --------------------------------------------------------------
	// Summary: Returns true if the two elements are in the same set.
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	@Deprecated
	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}

	// --------------------------------------------------------------
	// Summary: Merges the set containing element {@code p} with the the set
	// containing element {@code q}.
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	public void union(int p, int q) {
		int rootP = find(p);
		int rootQ = find(q);
		if (rootP == rootQ)
			return;

		// make root of smaller rank point to root of larger rank
		if (rank[rootP] < rank[rootQ])
			parent[rootP] = rootQ;
		else if (rank[rootP] > rank[rootQ])
			parent[rootQ] = rootP;
		else {
			parent[rootQ] = rootP;
			rank[rootP]++;
		}
		count--;
	}

	// --------------------------------------------------------------
	// Summary: Validate that p is a valid index
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	private void validate(int p) {
		int n = parent.length;
		if (p < 0 || p >= n) {
			throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
		}
	}

}