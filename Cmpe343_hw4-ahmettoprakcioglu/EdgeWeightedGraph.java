
import java.util.NoSuchElementException;

//-----------------------------------------------------
//Title: EdgeWeightedGraph
//Author: Ahmet Kaan Toprakçıoğlu
//ID: 12742035240
//Section: 1
//Assignment: 4
//Description: This class defines an EdgeWeightedGraph.
//-----------------------------------------------------
public class EdgeWeightedGraph {
	private static final String NEWLINE = System.getProperty("line.separator");

	private final int V;
	private int E;
	private Bag<Edge>[] adj;

	// --------------------------------------------------------------
	// Summary: Initializes an empty edge-weighted graph with {@code V} vertices and
	// 0 edges.
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	public EdgeWeightedGraph(int V, int E) {
		if (V < 0)
			throw new IllegalArgumentException("Number of vertices must be nonnegative");
		this.V = V;
		this.E = E;
		adj = (Bag<Edge>[]) new Bag[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new Bag<Edge>(v + 1);
		}
	}

	// --------------------------------------------------------------
	// Summary: Returns the number of vertices in this edge-weighted graph.
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	public int V() {
		return V;
	}

	// --------------------------------------------------------------
	// Summary: Returns the number of edges in this edge-weighted graph.
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	public int getE() {
		return E;
	}

	// --------------------------------------------------------------
	// Summary: throw an IllegalArgumentException unless {@code 0 <= v < V}
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	private void validateVertex(int v) {
		if (v < 0 || v >= V)
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
	}

	// --------------------------------------------------------------
	// Summary: Adds the undirected edge {@code e} to this edge-weighted graph.
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	public void addEdge(Edge e) {
		int v = e.either();
		int w = e.other(v);
		validateVertex(v);
		validateVertex(w);
		adj[v].add(e);
		adj[w].add(e);

	}

	// --------------------------------------------------------------
	// Summary: Returns the edges incident on vertex {@code v}.
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	public Iterable<Edge> adj(int v) {
		validateVertex(v);
		return adj[v];
	}

	// --------------------------------------------------------------
	// Summary: Returns the degree of vertex {@code v}.
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	public int degree(int v) {
		validateVertex(v);
		return adj[v].size();
	}

	// --------------------------------------------------------------
	// Summary: Returns all edges in this edge-weighted graph.
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	public Iterable<Edge> edges() {
		Bag<Edge> list = new Bag<Edge>();
		for (int v = 0; v < V; v++) {
			int selfLoops = 0;
			for (Edge e : adj(v)) {
				if (e.other(v) > v) {
					list.add(e);
				}
				// add only one copy of each self loop (self loops will be consecutive)
				else if (e.other(v) == v) {
					if (selfLoops % 2 == 0)
						list.add(e);
					selfLoops++;
				}
			}
		}
		return list;
	}

	// --------------------------------------------------------------
	// Summary: Returns a string representation of the edge-weighted graph.
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(V + " " + E + NEWLINE);
		for (int v = 0; v < V; v++) {
			s.append(v + 1 + ": ");
			for (Edge e : adj[v]) {
				s.append(e + "  ");
			}
			s.append(NEWLINE);
		}
		return s.toString();
	}

}
