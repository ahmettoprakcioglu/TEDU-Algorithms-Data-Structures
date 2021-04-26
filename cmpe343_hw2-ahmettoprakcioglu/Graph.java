import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

//Title: Graph class
//Author: Ahmet Kaan Toprakçıoğlu
//ID: 12742035240
//Section: 1
//Assignment: 2
//Description: This class is a Graph class.
//-----------------------------------------------------
public class Graph {

	private static final String NEWLINE = System.getProperty("line.separator");

	private final int V;
	private int E;
	public Bag<Integer>[] adj;

	public Graph(int V, BufferedReader br) throws IOException {
		// -----------------------------------------------------
		// Summary: It determines how nodes are connected to each other according to the
		// values in the parameter.
		// V the number of vertices
		// Precondition: There is no precondition.
		// Postcondition: The value of the variable is set.
		// -----------------------------------------------------
		if (V < 0)
			throw new IllegalArgumentException("Number of vertices must be nonnegative");
		this.V = V;
		this.E = 0;
		adj = (Bag<Integer>[]) new Bag[V];

		for (int v = 0; v < V; v++) {
			String arr_line = br.readLine();
			String[] splitStrings = arr_line.split(" ");
			String name = splitStrings[0];
			int x = Integer.parseInt(splitStrings[1]);
			int y = Integer.parseInt(splitStrings[2]);

			adj[v] = new Bag<Integer>(name, x, y);
		}
	}

	public int V() {
		// -----------------------------------------------------
		// Summary: Returns the number of vertices in this graph.
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition.
		// -----------------------------------------------------
		return V;
	}

	public int E() {
		// -----------------------------------------------------
		// Summary: Returns the number of edges in this graph.
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition.
		// -----------------------------------------------------
		return E;
	}

	public int lastPos() {
		// -----------------------------------------------------
		// Summary: Returns the last position node in graph.
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition.
		// -----------------------------------------------------
		return V() - 1;
	}

	public int firstPos() {
		// -----------------------------------------------------
		// Summary: Returns the first position node in graph.
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition.
		// -----------------------------------------------------
		return 0;
	}

	private void validateVertex(int v) {
		// -----------------------------------------------------
		// Summary: Throw an IllegalArgumentException unless {@code 0 <= v < V}
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition.
		// -----------------------------------------------------
		if (v < 0 || v >= V)
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
	}

	public void addEdge(int v, int w) {
		validateVertex(v);
		validateVertex(w);
		E++;
		adj[v].add(w);
		adj[w].add(v);
	}

	public Iterable<Integer> adj(int v) {
		// -----------------------------------------------------
		// Summary: Returns the vertices adjacent to vertex.
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition.
		// -----------------------------------------------------
		validateVertex(v);
		return adj[v];
	}

	public int degree(int v) {
		// -----------------------------------------------------
		// Summary: Returns the degree of vertex.
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition.
		// -----------------------------------------------------
		validateVertex(v);
		return adj[v].size();
	}

	public void BFS(int s) {
		// -----------------------------------------------------
		// Summary: Returns the degree of vertex.
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition.
		// -----------------------------------------------------

		LinkedList<Integer> list_int = new LinkedList<Integer>();
		// Mark all the vertices as not visited(By default
		// set as false)
		boolean visit[] = new boolean[V];

		// Create a queue for BFS

		// Mark the current node as visited and enqueue it
		visit[s] = true;
		list_int.add(s);

		while (list_int.size() != 0) {
			// Dequeue a vertex from queue and print it
			s = list_int.poll();
			System.out.print(adj[s].getName() + " ");

			// Get all adjacent vertices of the dequeued vertex s
			// If a adjacent has not been visited, then mark it
			// visited and enqueue it

			for (int w : adj[s]) {

				int n = w;
				if (!visit[n]) {
					visit[n] = true;
					list_int.add(n);
				}
			}

		}
	}

	public void printGraph() {
		// -----------------------------------------------------
		// Summary: Print all in graph.
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition.
		// -----------------------------------------------------
		for (int v = 0; v < V; v++) {
			for (int w : adj[v]) {
				if (w >= v) {
					System.out.println(adj[v].getName() + " (" + adj[v].getX() + ", " + adj[v].getY() + ")" + " ---> "
							+ adj[w].getName() + " (" + adj[w].getX() + ", " + adj[w].getY() + ")");

				}
			}
		}
	}

}