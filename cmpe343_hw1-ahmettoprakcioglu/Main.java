import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//-----------------------------------------------------
//Title: Main
//Author: Ahmet Kaan Toprakçıoğlu
//ID: 12742035240
//Section: 01
//Assignment: 1
//Description: This class defines a Main.
//-----------------------------------------------------

public class Main {
	public static SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<String, Integer>(1999);
	public static ArrayList<String> list2 = new ArrayList<String>();

	public static void sort(String[] arr) {
		// --------------------------------------------------------
		// Summary: This method pulls elements the size of the array specified in the
		// parameter. These elements are the most popular.
		// Precondition: There is no precondition.
		// Postcondition: return arraylist.
		// --------------------------------------------------------
		for (int i = 0; i < arr.length; i++) {
			arr[i] = list2.get(i);
		}

		for (int i = 0; i < list2.size(); i++) {
			String min = find_Min(arr);
			int index = find_Index(arr, min);
			if (st.get(list2.get(i)) > st.get(min)) {
				arr[index] = list2.get(i);
			}
		}
	}

	static String find_Min(String[] arr) {
		// Determines the index of the feature in the "sort" method.
		String min_key = arr[0];
		for (int j = 0; j < arr.length; j++) {
			for (int j2 = 0; j2 < arr.length; j2++) {
				if (st.get(arr[j]) <= st.get(min_key)) {
					min_key = arr[j];
				}
			}
		}
		return min_key;
	}

	static int find_Index(String[] arr, String min) {
		// This method determines the smallest element available in the array used in
		// the "find_Min" method.
		int j = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equalsIgnoreCase(min)) {
				j = i;
			}
		}
		return j;
	}

	public static void sort_arr(String[] arr) {
		// lists the most popular elements found in the array.
		int n = arr.length;
		for (int i = 1; i < n; ++i) {
			String key = arr[i];
			int j = i - 1;

			/*
			 * insertion sort
			 */
			while (j >= 0 && st.get(arr[j]) > st.get(key)) {
				arr[j + 1] = arr[j];
				j = j - 1;
			}
			arr[j + 1] = key;
		}

		for (int i = arr.length - 1; i >= 0; i--) {
			System.out.println(arr[i] + " " + st.get(arr[i]));
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/twitter.txt")));
		String str;

		int a = 0;
		while ((str = br.readLine()) != null) {
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) == '#') {
					String line2 = str.substring(i);
					if (line2.indexOf(' ') == -1) {
						if (1 != line2.length()) {
							String tag = line2.substring(0, line2.length()).toLowerCase();
							if (!tag.contains("@") && !tag.contains("!") && !tag.contains(",") && !tag.contains(".")
									&& !tag.contains("`") && !tag.contains("\"")) {
								if (st.contains(tag)) {
									st.put(tag, st.get(tag) + 1);

								} else {
									st.put(tag, 1);

								}
							}
						}
					} else {
						String tag = line2.substring(0, line2.indexOf(' ')).toLowerCase();
						if (!tag.contains("@") && !tag.contains("!") && !tag.contains(",") && !tag.contains(".")
								&& !tag.contains("`") && !tag.contains("\"")) {
							if (st.contains(tag)) {
								st.put(tag, st.get(tag) + 1);

							} else {
								st.put(tag, 1);

							}
						}
					}
				}
			}
		}
		list2 = st.searchKey();
		String[] arr = new String[10];
		sort(arr);
		sort_arr(arr);

		// st.calculateProbes();

	}

}
