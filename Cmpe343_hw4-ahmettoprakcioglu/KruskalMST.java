//-----------------------------------------------------
//Title: KruskalMST
//Author: Ahmet Kaan Toprakçıoğlu
//ID: 12742035240
//Section: 1
//Assignment: 4
//Description: This class defines a KruskalMST.
//-----------------------------------------------------
public class KruskalMST {
	private static final double FLOATING_POINT_EPSILON = 1E-12;

	private double weight; // weight of MST
	private Queue<Edge> mst = new Queue<Edge>(); // edges in MST

	// --------------------------------------------------------------
	// Summary: Compute a minimum spanning tree (or forest) of an edge-weighted
	// graph.
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	public KruskalMST(EdgeWeightedGraph G) {
		// more efficient to build heap by passing array of edges
		MinPQ<Edge> pq = new MinPQ<Edge>();
		for (Edge e : G.edges()) {
			pq.insert(e);
		}

		// run greedy algorithm
		UF uf = new UF(G.V());
		while (!pq.isEmpty() && mst.size() < G.V() - 1) {
			Edge e = pq.delMin();
			int v = e.either();
			int w = e.other(v);
			if (uf.find(v) != uf.find(w)) { // v-w does not create a cycle
				uf.union(v, w); // merge v and w components
				mst.enqueue(e); // add edge e to mst
				weight += e.weight();
			}
		}

		// check optimality conditions
		assert check(G);
	}

	// --------------------------------------------------------------
	// Summary: Returns the edges in a minimum spanning tree (or forest). @return
	// the edges in a minimum spanning tree (or forest) as an iterable of edges.
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	public Iterable<Edge> edges() {
		return mst;
	}

	public String print() {
		String a = "";
		for (Edge e : edges()) {
			a += e.toString() + ",";
		}
		return a.substring(0, a.length() - 1);
	}

	// --------------------------------------------------------------
	// Summary: Returns the sum of the edge weights in a minimum spanning tree (or
	// forest).
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	public double weight() {
		return weight;
	}

	// --------------------------------------------------------------
	// Summary: check optimality conditions (takes time proportional to E V lg* V)
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	private boolean check(EdgeWeightedGraph G) {

		// check total weight
		double total = 0.0;
		for (Edge e : edges()) {
			total += e.weight();
		}
		if (Math.abs(total - weight()) > FLOATING_POINT_EPSILON) {
			System.err.printf("Weight of edges does not equal weight(): %f vs. %f\n", total, weight());
			return false;
		}

		// check that it is acyclic
		UF uf = new UF(G.V());
		for (Edge e : edges()) {
			int v = e.either(), w = e.other(v);
			if (uf.find(v) == uf.find(w)) {
				System.err.println("Not a forest");
				return false;
			}
			uf.union(v, w);
		}

		// check that it is a spanning forest
		for (Edge e : G.edges()) {
			int v = e.either(), w = e.other(v);
			if (uf.find(v) != uf.find(w)) {
				System.err.println("Not a spanning forest");
				return false;
			}
		}

		// check that it is a minimal spanning forest (cut optimality conditions)
		for (Edge e : edges()) {

			// all edges in MST except e
			uf = new UF(G.V());
			for (Edge f : mst) {
				int x = f.either(), y = f.other(x);
				if (f != e)
					uf.union(x, y);
			}

			// check that e is min weight edge in crossing cut
			for (Edge f : G.edges()) {
				int x = f.either(), y = f.other(x);
				if (uf.find(x) != uf.find(y)) {
					if (f.weight() < e.weight()) {
						System.err.println("Edge " + f + " violates cut optimality conditions");
						return false;
					}
				}
			}

		}

		return true;
	}

}
