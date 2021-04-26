
//-----------------------------------------------------
//Title: Edge
//Author: Ahmet Kaan Toprakçıoğlu
//ID: 12742035240
//Section: 1
//Assignment: 4
//Description: This class defines an Edge.
//-----------------------------------------------------

public class Edge implements Comparable<Edge> {

	private final int v;
	private final int w;
	private final int weight;

	// --------------------------------------------------------------
	// Summary: Initializes an edge between vertices {@code v} and {@code w} of the
	// given {@code weight}.
	// Precondition: v one vertex, w the other vertex, weight the weight of this
	// edge
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	public Edge(int v, int w, int weight) {
		if (v < 0)
			throw new IllegalArgumentException("vertex index must be a nonnegative integer");
		if (w < 0)
			throw new IllegalArgumentException("vertex index must be a nonnegative integer");
		if (Double.isNaN(weight))
			throw new IllegalArgumentException("Weight is NaN");
		this.v = v;
		this.w = w;
		this.weight = weight;
	}

	// --------------------------------------------------------------
	// Summary: Returns the weight of this edge.
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	public int weight() {
		return weight;
	}

	// --------------------------------------------------------------
	// Summary: Returns either endpoint of this edge.
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	public int either() {
		return v;
	}

	// --------------------------------------------------------------
	// Summary: Returns the endpoint of this edge that is different from the given
	// vertex.
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	public int other(int vertex) {
		if (vertex == v)
			return w;
		else if (vertex == w)
			return v;
		else
			throw new IllegalArgumentException("Illegal endpoint");
	}

	// --------------------------------------------------------------
	// Summary: Compares two edges by weight. Note that {@code compareTo()} is not
	// consistent with {@code equals()},
	// Precondition: There is no precondition.
	// Postcondition: There is no postcondition.
	// --------------------------------------------------------------
	@Override
	public int compareTo(Edge that) {
		return Integer.compare(this.weight, that.weight);
	}

	public String toString() {
		return String.format("%d-%d", v + 1, w + 1);
	}

}