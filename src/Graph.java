import java.util.*;

public class Graph {
    private String vertex;
    private LinkedList<String> adjacencyLists[];

    Graph(int vertex) {
        this.vertex = String.valueOf(vertex);
        adjacencyLists = new LinkedList[vertex];
        for (int i = 0; i < vertex; ++i)
            adjacencyLists[i] = new LinkedList();
    }

    void addEdge(int v, int w) {
        adjacencyLists[v].add(String.valueOf(w));
    }

    void BFS(int s) {

        boolean visited[] = new boolean[Integer.parseInt(vertex)];

        // Create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<Integer>();

        // Mark the current node as visited and enqueue it
        visited[s] = true;

        queue.add(s);

        while (queue.size() != 0) {

            // Dequeue a vertex from queue and print it

            s = queue.poll();

            System.out.print(s + " ");

            // Get all adjacent vertices of the dequeued

            // vertex s If a adjacent has not been visited,

            // then mark it visited and enqueue it

            ListIterator<String> i = adjacencyLists[s].listIterator();

            while (i.hasNext()) {
                int n = Integer.parseInt(i.next());

                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }

    // Driver method to
    public static void main(String args[]) {

        Graph g = new Graph(4);


        g.addEdge(0, 1);

        g.addEdge(0, 2);

        g.addEdge(1, 2);

        g.addEdge(2, 0);

        g.addEdge(2, 3);

        g.addEdge(3, 3);


        System.out.println(

                "Following is Breadth First Traversal "

                        + "(starting from vertex 2)");


        g.BFS(2);

    }

}

