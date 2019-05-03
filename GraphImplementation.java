import java.util.*;

public class GraphImplementation implements Graph {
	int[][] adjMatrix;
    int vertices;

	public GraphImplementation(int vertexes){
        vertices = vertexes;
		adjMatrix = new int[vertexes][vertexes];
	}

	public int[] neighbors(int vertex) {
		int edges = 0;
		for(int i = 0; i < vertices; i++) {
			if(adjMatrix[vertex][i] > 0) {
				edges++;
			} // counts up how many edges there are (ie, how many actual numbers there are in the adjMatrix)
		}
		int[] neighbs = new int[edges]; // create array with however many edges there are
		for (int j = 0, x = 0; j < vertices; j++) {
			if (adjMatrix[vertex][j] > 0) {
				neighbs[x] = j;
				x++;
			} // go through the adjMatrix column wise and adds the indexes to neighbors array
		}
		return neighbs; // return the neighbors array
	}

    public void addEdge(int src, int tar) {
        adjMatrix[src][tar] = 1; // 
    }

	public List<Integer> topologicalSort() {
		int[] incident = new int[vertices];
        List<Integer> schedule = new LinkedList<>();

		for(int x = 0; x < vertices; x++){ // row
			for(int y = 0; y < vertices; y++){ // col
				incident[y] += adjMatrix[x][y]; // update numbers column-wise, putting them into incident array
			}
		}

		int[] neighbors;
		for (int a = 0; a < vertices; a++) { // row
			for (int b = 0; b < vertices; b++) { // column
				if (incident[b] == 0) {
					neighbors = neighbors(b); // add the value to the new sorted array
					for(int x = 0; x < neighbors.length; x++) {
						incident[neighbors[x]] -= 1; // subtract one from the value at the incident array until less than length of neighbors
					}
					schedule.add(b); // add this child to the parent, index to schedule
					incident[b] = -1; // // set the value of the incidentarray[index] to -1, to show that the index is not part of any array, it is open to be taken as a child/parent of a tree
				}
			}
		}
		return schedule;
	}
}