import java.util.Stack;

//-----------------------------------------------------
//Title: DijkstraUndirectedSP
//Author: Ahmet Kaan Toprakçıoğlu
//ID: 12742035240
//Section: 1
//Assignment: 4
//Description: This class defines an DijkstraUndirectedSP.
//-----------------------------------------------------
public class DijkstraUndirectedSP {
	private double[] distTo; // distTo[v] = distance of shortest s->v path
	private Edge[] edgeTo; // edgeTo[v] = last edge on shortest s->v path
	private IndexMinPQ<Double> pq; // priority queue of vertices

	// --------------------------------------------------------------
	// Summary: Computes a shortest-paths tree from the source vertex {@code s} to
	// every other vertex in the edge-weighted graph {@code G}.
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	public DijkstraUndirectedSP(EdgeWeightedGraph G, int s) {
		for (Edge e : G.edges()) {
			if (e.weight() < 0)
				throw new IllegalArgumentException("edge " + e + " has negative weight");
		}

		distTo = new double[G.V()];
		edgeTo = new Edge[G.V()];

		validateVertex(s);

		for (int v = 0; v < G.V(); v++)
			distTo[v] = Double.POSITIVE_INFINITY;
		distTo[s] = 0.0;

		// relax vertices in order of distance from s
		pq = new IndexMinPQ<Double>(G.V());
		pq.insert(s, distTo[s]);
		while (!pq.isEmpty()) {
			int v = pq.delMin();
			for (Edge e : G.adj(v))
				relax(e, v);
		}

		// check optimality conditions
		assert check(G, s);
	}

	// --------------------------------------------------------------
	// Summary: relax edge e and update pq if changed
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	private void relax(Edge e, int v) {
		int w = e.other(v);
		if (distTo[w] > distTo[v] + e.weight()) {
			distTo[w] = distTo[v] + e.weight();
			edgeTo[w] = e;
			if (pq.contains(w))
				pq.decreaseKey(w, distTo[w]);
			else
				pq.insert(w, distTo[w]);
		}
	}

	// --------------------------------------------------------------
	// Summary: Returns the length of a shortest path between the source vertex
	// {@code s} and vertex {@code v}.
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	public double distTo(int v) {
		validateVertex(v);
		return distTo[v];
	}

	// --------------------------------------------------------------
	// Summary: Returns true if there is a path between the source vertex {@code s}
	// and vertex {@code v}.
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	public boolean hasPathTo(int v) {
		validateVertex(v);
		return distTo[v] < Double.POSITIVE_INFINITY;
	}

	// --------------------------------------------------------------
	// Summary: Returns a shortest path between the source vertex {@code s} and
	// vertex {@code v}.v the destination vertex
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	public Iterable<Edge> pathTo(int v) {
		validateVertex(v);
		if (!hasPathTo(v))
			return null;
		Stack<Edge> path = new Stack<Edge>();
		int x = v;
		for (Edge e = edgeTo[v]; e != null; e = edgeTo[x]) {
			path.push(e);
			x = e.other(x);
		}
		return path;
	}

	// --------------------------------------------------------------
	// Summary: check optimality conditions:
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	//
	// (i) for all edges e = v-w: distTo[w] <= distTo[v] + e.weight()
	// (ii) for all edge e = v-w on the SPT: distTo[w] == distTo[v] + e.weight()
	private boolean check(EdgeWeightedGraph G, int s) {

		// check that edge weights are nonnegative
		for (Edge e : G.edges()) {
			if (e.weight() < 0) {
				System.err.println("negative edge weight detected");
				return false;
			}
		}

		// check that distTo[v] and edgeTo[v] are consistent
		if (distTo[s] != 0.0 || edgeTo[s] != null) {
			System.err.println("distTo[s] and edgeTo[s] inconsistent");
			return false;
		}
		for (int v = 0; v < G.V(); v++) {
			if (v == s)
				continue;
			if (edgeTo[v] == null && distTo[v] != Double.POSITIVE_INFINITY) {
				System.err.println("distTo[] and edgeTo[] inconsistent");
				return false;
			}
		}

		// check that all edges e = v-w satisfy distTo[w] <= distTo[v] + e.weight()
		for (int v = 0; v < G.V(); v++) {
			for (Edge e : G.adj(v)) {
				int w = e.other(v);
				if (distTo[v] + e.weight() < distTo[w]) {
					System.err.println("edge " + e + " not relaxed");
					return false;
				}
			}
		}

		// check that all edges e = v-w on SPT satisfy distTo[w] == distTo[v] +
		// e.weight()
		for (int w = 0; w < G.V(); w++) {
			if (edgeTo[w] == null)
				continue;
			Edge e = edgeTo[w];
			if (w != e.either() && w != e.other(e.either()))
				return false;
			int v = e.other(w);
			if (distTo[v] + e.weight() != distTo[w]) {
				System.err.println("edge " + e + " on shortest path not tight");
				return false;
			}
		}
		return true;
	}

	// --------------------------------------------------------------
	// Summary: throw an IllegalArgumentException unless {@code 0 <= v < V}
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	private void validateVertex(int v) {
		int V = distTo.length;
		if (v < 0 || v >= V)
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
	}

}