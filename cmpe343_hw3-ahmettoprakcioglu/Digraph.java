import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

//Title: Digraph class
//Author: Ahmet Kaan Toprakçıoğlu
//ID: 12742035240
//Section: 1
//Assignment: 3
//Description: This class is a Digraph class.
//-----------------------------------------------------
public class Digraph {

	private static final String NEWLINE = System.getProperty("line.separator");

	private final int V;
	private int E;
	public Bag<Integer>[] adj;

	public Digraph(int V, int E) throws IOException {
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
		this.E = E;
		adj = (Bag<Integer>[]) new Bag[V];

		for (int v = 0; v < V; v++) {

			adj[v] = new Bag<Integer>(v + 1);
		}
	}

	ArrayList<String> cities = new ArrayList<String>();

	public Digraph(int V, int E, BufferedReader br, BufferedReader add) throws IOException {
		// --------------------------------------------------------
		// Summary: Constructor an empty bag.
		// Precondition: int V, BufferedReader br
		// Postcondition: Initializes an empty graph with {@code V} vertices and 0
		// edges.
		// param V the number of vertices.
		// --------------------------------------------------------

		String line;
		String arr[];

		while ((line = br.readLine()) != null) {
			arr = line.split(" ");

			if (!cities.contains(arr[0])) {
				cities.add(arr[0]);
			}
			if (!cities.contains(arr[1])) {
				cities.add(arr[1]);
			}

		}
		add.readLine();
		add.readLine();

		this.V = V;
		if (V < 0)
			throw new IllegalArgumentException("Number of vertices must be nonnegative");
		this.E = E;
		adj = (Bag<Integer>[]) new Bag[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new Bag<Integer>(cities.get(v), v + 1);
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
		// --------------------------------------------------------
		// Summary:get Adds the directed edge v→w to this digraph.
		// Precondition: int v, int w.
		// Postcondition: add edge v-w to this graph.
		// --------------------------------------------------------
		validateVertex(v - 1);
		validateVertex(w - 1);
		adj[v - 1].add(w - 1);
	}

	public void ignore_Edge(int v, int w) {
		// --------------------------------------------------------
		// Summary:get Ignore the edge that given in parameter.
		// Precondition: There is no precondition
		// Postcondition: There is no postcondition.
		// --------------------------------------------------------

		validateVertex(v - 1);
		validateVertex(w - 1);
		adj[v - 1].remove();
		System.out.println(adj[v - 1].getName() + " " + adj[w - 1].getName());

	}
	// --------------------------------------------------------
	// Summary:get Create a array to store indegrees of all vertices. Initialize all
	// indegrees as 0.
	// Precondition: There is no precondition
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------

	public void topologicalSort() {
		// Create a array to store
		// indegrees of all
		// vertices. Initialize all
		// indegrees as 0.
		int indegree[] = new int[V];

		// Traverse adjacency lists
		// to fill indegrees of
		// vertices. This step takes
		// O(V+E) time

		for (int i = 0; i < V; i++) {
			// ArrayList<Integer> temp = (ArrayList<Integer>)adj[i];

			for (int node : adj[i]) {
				indegree[node]++;
			}
		}

		// Create a queue and enqueue
		// all vertices with indegree 0
		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 0; i < V; i++) {
			if (indegree[i] == 0)
				q.add(i);
		}

		// Initialize count of visited vertices
		int cnt = 0;

		// Create a vector to store result
		// (A topological ordering of the vertices)
		Vector<Integer> topOrder = new Vector<Integer>();
		while (!q.isEmpty()) {
			// Extract front of queue
			// (or perform dequeue)
			// and add it to topological order
			int u = q.poll();
			topOrder.add(u);

			// Iterate through all its
			// neighbouring nodes
			// of dequeued node u and
			// decrease their in-degree
			// by 1
			for (int node : adj[u]) {
				// If in-degree becomes zero,
				// add it to queue
				if (--indegree[node] == 0)
					q.add(node);
			}
			cnt++;
		}

		// Check if there was a cycle
		if (cnt != V) {
			System.out.println("There exists a cycle in the graph");
			return;
		}

		// Print topological order
		for (int i : topOrder) {
			System.out.print(adj[i].ID + " ");
		}
	}

	public boolean isCyclicUtil(int i, boolean[] visited, boolean[] recStack) {

		// Mark the current node as visited and
		// part of recursion stack
		if (recStack[i])
			return true;

		if (visited[i])
			return false;

		visited[i] = true;

		recStack[i] = true;

		for (Integer c : adj[i])
			if (isCyclicUtil(c, visited, recStack))
				return true;

		recStack[i] = false;

		return false;
	}

	// Returns true if the graph contains a
	// cycle, else false.
	// This function is a variation of DFS() in

	public boolean isCyclic() {

		// Mark all the vertices as not visited and
		// not part of recursion stack
		boolean[] visited = new boolean[V];
		boolean[] recStack = new boolean[V];

		// Call the recursive helper function to
		// detect cycle in different DFS trees
		for (int i = 0; i < V; i++)
			if (isCyclicUtil(i, visited, recStack))
				return true;

		return false;

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

	public void printGraph() {
		// -----------------------------------------------------
		// Summary: Print all in graph.
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition.
		// -----------------------------------------------------
		for (int v = 0; v < V; v++) {
			for (int w : adj[v]) {
				System.out.println(adj[v].getName() + " ---> " + adj[w].getName());

			}
		}
	}

}