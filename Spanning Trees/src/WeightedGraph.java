import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class WeightedGraph {

	private int maxVertices;
	private int[][] matrix;

	// Initialize an empty graph with a maximum of size vertices
	public WeightedGraph(int size) {
		this.maxVertices = size;
		matrix = new int[maxVertices][maxVertices];

	}

	// Returns the degree of vertex with id/index n
	public int degree(int n) {
		int degree = 0;
		int[] vectorN = matrix[n];
		for (int eadge : vectorN)
			if (eadge != 0)
				degree++;
		return degree;

	}

	// Returns the total number of edges in the graph
	public int edgeCount() {
		int count = 0;
		for (int i = 0; i < maxVertices; i++) {
			count = count + degree(i);
		}
		return count / 2;

	}

	// Returns the weight of the edge connecting vertices m and n. 
	// If the vertices are equal, return 0. 
	// If the vertices are not adjacent, return -1
	public int adjacent(int m, int n) {
		if (n == m)
			return 0;
		else if (matrix[m][n] == 0)
			return -1;
		return matrix[m][n];

	}

	// Return the minimum spanning tree of this WeightedGraph in the form of a new WeightedGraph
	public WeightedGraph minimumSpanningTree() {
		ArrayList<Integer> allAdjacent = null;
		int u = 0;
		int[] vector = new int[maxVertices];
		int miniWeight = -1;
		int miniU = -1;
		Stack<Integer> mstSet = new Stack<Integer>();
		mstSet.add(u);

		while (mstSet.size() == maxVertices) {
			for (int i = 1; i < maxVertices; i++)

				vector[i] = Integer.MAX_VALUE;
			do {
				u = getCadidate(mstSet, vector);
				allAdjacent = getAdjacent(u);
				allAdjacent.removeAll(mstSet);

			} while (allAdjacent.isEmpty());
			for (Integer v : allAdjacent) {

				int weight = adjacent(u, v);

				if (miniWeight == -1 || miniWeight > weight) {

					miniWeight = weight;
					miniU = v;
				}

				if (weight < vector[v])
					vector[v] = weight;

			}
			mstSet.add(miniU);
			u = miniU;
		}
		return null;

	}

	private int getCadidate(Stack<Integer> mstSet, int[] vector) {
		int cadidates = 0;
		for (int i = 0; i < maxVertices; i++)
			if (!mstSet.contains(vector[i]) && (cadidates == -1 || vector[cadidates] > vector[i]))
				cadidates = i;
		if (cadidates == -1)
			throw new RuntimeException("problem ");
		return cadidates;
	}

	private ArrayList<Integer> getAdjacent(int u) {
		ArrayList<Integer> adjacentList = new ArrayList<Integer>();
		for (int i = 0; i < maxVertices; i++) {
			int adjacent = adjacent(u, i);
			if (adjacent != 0 && adjacent != -1) {

				adjacentList.add(i);
			}

		}

		return adjacentList;

	}

	// If the weight w is 0, remove any edge between m and n (if any). 
	// Otherwise, add an edge between vertices m and n with weight w. 
	// If an edge already exists, replace the weight of the edge with the new weight. 
	// If the vertices do not exist or are equal, throw an exception.
	public void insert(int m, int n, int w) {

		matrix[m][n] = w;
		if (n > maxVertices || m > maxVertices || n == m || n < 0 || m < 0) {
			throw new RuntimeException("the vertices do not exist or are equal");
		}

	}

	// Give your submission a name here so you can recognise the results when posted (keep it civil though please :))
	public String getName() {
		return "my prims";
	}

}