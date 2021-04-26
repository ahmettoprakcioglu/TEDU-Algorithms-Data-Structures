import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

//Title: Test class
//Author: Ahmet Kaan Toprakçıoğlu
//ID: 12742035240
//Section: 1
//Assignment: 3
//Description: This class is a Test class.
//-----------------------------------------------------
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {

		System.out.println("PART I\n");
		BufferedReader br_part1 = new BufferedReader(
				new InputStreamReader(new FileInputStream("src/sample_input1.txt")));
		BufferedReader add_part1 = new BufferedReader(
				new InputStreamReader(new FileInputStream("src/sample_input1.txt")));

		Digraph graph_part1 = new Digraph(Integer.parseInt(br_part1.readLine()), Integer.parseInt(br_part1.readLine()),
				br_part1, add_part1);
		String line_part1;
		String arr_part1[];

		ArrayList<Boolean> checkcycle = new ArrayList<Boolean>();
		while ((line_part1 = add_part1.readLine()) != null) {
			arr_part1 = line_part1.split(" ");
			graph_part1.addEdge(graph_part1.cities.indexOf(arr_part1[0]) + 1,
					graph_part1.cities.indexOf(arr_part1[1]) + 1);
			if (graph_part1.isCyclic()) {
				graph_part1.ignore_Edge(graph_part1.cities.indexOf(arr_part1[0]) + 1,
						graph_part1.cities.indexOf(arr_part1[1]) + 1);
			}

		}
		System.out.println("0 0\n");

		System.out.println("--------------------------------\n");

		///////////////////////////////////////////////////////////////////////////////
		System.out.println("PART II\n");
		BufferedReader br_part2 = new BufferedReader(
				new InputStreamReader(new FileInputStream("src/sample_input2.txt"))); // read text input
		String array[];
		array = br_part2.readLine().split(" ");
		Digraph graph = new Digraph(Integer.parseInt(array[0]), Integer.parseInt(array[1]));// create object

		String[] arr; // arr for bag of elements.
		String[] last = { "0", "0" };

		boolean exit = true; // check while parameters.
		while (exit) {
			String line = br_part2.readLine(); // Read line.
			if (Arrays.deepEquals(line.split(" "), last)) {
				exit = false;
			} else {
				arr = line.split(" ");
				graph.addEdge(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
			}

		}
		graph.topologicalSort();

	}

}
