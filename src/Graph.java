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
        LinkedList<Integer> queue = new LinkedList<Integer>();
        Map<Integer, Integer> levelsOfUsers = new HashMap<>();//vertex & level
        visited[s] = true;

        queue.add(s);
        levelsOfUsers.put(s, 0);
        ArrayList<Integer> bfsTraversal = new ArrayList<>();
        while ((queue.size() != 0) && (levelsOfUsers.get(s) < 6)) {
            s = queue.poll();
            bfsTraversal.add(s);
            ListIterator<String> iterator = adjacencyLists[s].listIterator();
            while (iterator.hasNext()) {
                int n = Integer.parseInt(iterator.next());
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                    levelsOfUsers.put(n, levelsOfUsers.get(s) + 1);
                }
            }
        }
    }

//    public void scoringUsers() {
//        Map<Integer,Integer> scoreOfUser = new HashMap<>();
//
//
//
//    }


//        for (int i = 0; i < bfsTraversal.size() - 1; i++) {
//            System.out.println(bfsTraversal.get(i));
//        }
}

