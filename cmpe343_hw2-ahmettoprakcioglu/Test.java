import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//Title: Test class
//Author: Ahmet Kaan Toprakçıoğlu
//ID: 12742035240
//Section: 1
//Assignment: 2
//Description: This class is a Test class.
//-----------------------------------------------------
public class Test {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/InputMaze.txt"))); // read
																													// file

		Graph graph = new Graph(Integer.parseInt(br.readLine()), br);// create object

		String[] arr; // arr for bag of elements.
		String[] last = { "-1", "-1" };

		boolean exit = true; // check while parameters.
		while (exit) {
			String line = br.readLine(); // Read line.
			if (Arrays.deepEquals(line.split(" "), last)) {
				exit = false;
			} else {
				arr = line.split(" ");
				graph.addEdge(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
			}

		}

		int source_vertex = 0;
		graph.printGraph();
		System.out.println();
		BreadthFirstPaths breadFS = new BreadthFirstPaths(graph, source_vertex);

		for (int i : breadFS.pathTo(graph.lastPos())) {
			if (i != graph.lastPos()) {
				System.out.print(graph.adj[i].getName() + "->");
			} else {
				System.out.print(graph.adj[i].getName());
			}
		}

	}

}
