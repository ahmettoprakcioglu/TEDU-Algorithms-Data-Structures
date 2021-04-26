import java.util.*;

//Title: BreadthFirstPaths class
//Author: Ahmet Kaan Toprakçıoğlu
//ID: 12742035240
//Section: 1
//Assignment: 2
//Description: This class is a BreadthFirstPaths class.
//-----------------------------------------------------
public class BreadthFirstPaths {
	private static final int INFINITY = Integer.MAX_VALUE;
	private boolean[] marked; // marked[v] = is there an s-v path
	private int[] edgeTo; // edgeTo[v] = previous edge on shortest s-v path
	private int[] distTo; // distTo[v] = number of edges shortest s-v path

	/**
	 * 
	 * G the graph s the source vertex IllegalArgumentException unless
	 * {@code 0 <= s < V}
	 */
	public BreadthFirstPaths(Graph G, int s) {
		// -----------------------------------------------------
		// Summary: omputes the shortest path between the source vertex and every other
		// vertex in the graph {@code G}.
		// Precondition: varName is a char and varValue is an
		// Postcondition: The value of the variable is set.
		// -----------------------------------------------------
		marked = new boolean[G.V()];
		distTo = new int[G.V()];
		edgeTo = new int[G.V()];
		validateVertex(s);
		bfs(G, s);

		assert check(G, s);
	}

	private void bfs(Graph G, int s) {
		// -----------------------------------------------------
		// Summary: breadth-first search from a single source
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition.
		// -----------------------------------------------------
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
		for (int v = 0; v < G.V(); v++)
			distTo[v] = INFINITY;
		distTo[s] = 0;
		marked[s] = true;
		queue.add(s);

		while (!queue.isEmpty()) {
			int v = queue.remove();
			for (int w : G.adj(v)) {
				if (!marked[w]) {
					edgeTo[w] = v;
					distTo[w] = distTo[v] + 1;
					marked[w] = true;
					queue.add(w);
				}
			}
		}
	}

	public boolean hasPathTo(int v) {
		// -----------------------------------------------------
		// Summary: Is there a path between the source vertex (or sources) and vertex?
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition.
		// -----------------------------------------------------
		validateVertex(v);
		return marked[v];
	}

	public int distTo(int v) {
		// -----------------------------------------------------
		// Summary: Returns the number of edges in a shortest path between the source
		// vertex and vertex.
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition.
		// -----------------------------------------------------
		validateVertex(v);
		return distTo[v];
	}

	private boolean check(Graph G, int s) {
		// -----------------------------------------------------
		// Summary: Returns a shortest path between the source vertex (or sources) and,
		// or if no such path. Check optimality conditions for single source
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition.
		// -----------------------------------------------------
		// check that the distance of s = 0
		if (distTo[s] != 0) {
			System.out.println("distance of source " + s + " to itself = " + distTo[s]);
			return false;
		}

		// check that for each edge v-w dist[w] <= dist[v] + 1
		// provided v is reachable from s
		for (int v = 0; v < G.V(); v++) {
			for (int w : G.adj(v)) {
				if (hasPathTo(v) != hasPathTo(w)) {
					System.out.println("edge " + v + "-" + w);
					System.out.println("hasPathTo(" + v + ") = " + hasPathTo(v));
					System.out.println("hasPathTo(" + w + ") = " + hasPathTo(w));
					return false;
				}
				if (hasPathTo(v) && (distTo[w] > distTo[v] + 1)) {
					System.out.println("edge " + v + "-" + w);
					System.out.println("distTo[" + v + "] = " + distTo[v]);
					System.out.println("distTo[" + w + "] = " + distTo[w]);
					return false;
				}
			}
		}

		// check that v = edgeTo[w] satisfies distTo[w] = distTo[v] + 1
		// provided v is reachable from s
		for (int w = 0; w < G.V(); w++) {
			if (!hasPathTo(w) || w == s)
				continue;
			int v = edgeTo[w];
			if (distTo[w] != distTo[v] + 1) {
				System.out.println("shortest path edge " + v + "-" + w);
				System.out.println("distTo[" + v + "] = " + distTo[v]);
				System.out.println("distTo[" + w + "] = " + distTo[w]);
				return false;
			}
		}

		return true;
	}

	public Iterable<Integer> pathTo(int v) {
		// -----------------------------------------------------
		// Summary: Returns a shortest path between the source vertex (or sources)and,
		// or {@code null} if no such path.
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition.
		// -----------------------------------------------------
		validateVertex(v);
		if (!hasPathTo(v))
			return null;
		LinkedList<Integer> path = new LinkedList<Integer>();
		int x;
		for (x = v; distTo[x] != 0; x = edgeTo[x])
			path.push(x);
		path.push(x);
		return path;
	}

	private void validateVertices(Iterable<Integer> vertices) {
		if (vertices == null) {
			throw new IllegalArgumentException("argument is null");
		}
		for (Integer v : vertices) {
			if (v == null) {
				throw new IllegalArgumentException("vertex is null");
			}
			validateVertex(v);
		}
	}

	// throw an IllegalArgumentException unless {@code 0 <= v < V}
	private void validateVertex(int v) {
		// -----------------------------------------------------
		// Summary: Throw an IllegalArgumentException unless {@code 0 <= v < V}
		// Precondition: There is no precondition.
		// Postcondition: There is no postcondition.
		// -----------------------------------------------------
		int V = marked.length;
		if (v < 0 || v >= V)
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
	}

}