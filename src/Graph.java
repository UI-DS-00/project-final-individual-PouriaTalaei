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

    void BFS(int userId, ArrayList<User> users) {

        boolean visited[] = new boolean[Integer.parseInt(vertex)];
        LinkedList<Integer> queue = new LinkedList<Integer>();
        Map<Integer, Integer> levelsOfUsers = new HashMap<>();//vertex & level
        visited[userId] = true;

        queue.add(userId);
        levelsOfUsers.put(userId, 0);
        ArrayList<Integer> bfsTraversal = new ArrayList<>();
        int s = userId;
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


        Map<Integer, Integer> scoreOfUser = new HashMap<>();
        for (int i = 0; i < levelsOfUsers.size(); i++) {
            switch (levelsOfUsers.get(i)) {
                case 1:
                    scoreOfUser.put(i, 50);
                    break;
                case 2:
                    scoreOfUser.put(i, 40);
                    break;
                case 3:
                    scoreOfUser.put(i, 30);
                    break;
                case 4:
                    scoreOfUser.put(i, 20);
                    break;
                case 5:
                    scoreOfUser.put(i, 10);
                    break;
            }
        }
        for (int i = 0; i < bfsTraversal.size(); i++) {
            if(Objects.equals(users.get(i).getUniversityLocation(), users.get(userId).getUniversityLocation()))
                scoreOfUser.replace(i,scoreOfUser.get(i),scoreOfUser.get(i)+10);
            if (Objects.equals(users.get(i).getWorkplace(), users.get(userId).getWorkplace()))
                scoreOfUser.replace(i,scoreOfUser.get(i),scoreOfUser.get(i)+10);

            for (int j = 0; j < users.get(i).getSpecialties().size(); j++) {
                for (int k = 0; k <users.get(userId).getSpecialties().size(); k++) {
                    if (Objects.equals(users.get(i).getSpecialties().get(j), users.get(userId).getSpecialties().get(k)))
                        scoreOfUser.replace(i,scoreOfUser.get(i),scoreOfUser.get(i)+20);
                }
            }
        }
    }


//    public void scoringUsers() {
//        Map<Integer, Integer> scoreOfUser = new HashMap<>();
//
//
//    }


//        for (int i = 0; i < bfsTraversal.size() - 1; i++) {
//            System.out.println(bfsTraversal.get(i));
//        }
}

