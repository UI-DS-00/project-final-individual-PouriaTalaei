import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import java.util.*;

public class Graph {
    private String vertex;
    private LinkedList<String> adjacencyLists[];

    Map<Integer, Integer> scoreOfUser = new HashMap<>();
    LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();

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
        while ((queue.size() != 0) && (levelsOfUsers.get(s) < 5)) {
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

        for (int i = 0; i < users.size(); i++) {
            if (levelsOfUsers.get(i) == null)
                continue;
            switch (levelsOfUsers.get(i)) {
                case 1 -> scoreOfUser.put(i, 50);
                case 2 -> scoreOfUser.put(i, 40);
                case 3 -> scoreOfUser.put(i, 30);
                case 4 -> scoreOfUser.put(i, 20);
                case 5 -> scoreOfUser.put(i, 10);
            }
        }
        for (int i = 0; i < users.size(); i++) {
            if (scoreOfUser.get(i) == null)
                continue;
            if (Objects.equals(users.get(i).getUniversityLocation(), users.get(userId).getUniversityLocation()))
                scoreOfUser.replace(i, scoreOfUser.get(i), scoreOfUser.get(i) + 10);
            if (Objects.equals(users.get(i).getWorkplace(), users.get(userId).getWorkplace()))
                scoreOfUser.replace(i, scoreOfUser.get(i), scoreOfUser.get(i) + 10);

            for (int j = 0; j < users.get(i).getSpecialties().size(); j++) {
                for (int k = 0; k < users.get(userId).getSpecialties().size(); k++) {
                    if (Objects.equals(users.get(i).getSpecialties().get(j), users.get(userId).getSpecialties().get(k)))
                        scoreOfUser.replace(i, scoreOfUser.get(i), scoreOfUser.get(i) + 20);
                }
            }
        }
    }

    public void sortMap(ArrayList<User> users) {
        ArrayList<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : scoreOfUser.entrySet()) {
            list.add(entry.getValue());
        }
        Collections.sort(list);
        Collections.reverse(list);
        for (int num : list)
            for (Entry<Integer, Integer> entry : scoreOfUser.entrySet())
                if (entry.getValue().equals(num))
                    sortedMap.put(String.valueOf(entry.getKey()), num);

        int counter = 0;
        ArrayList<Map.Entry<String, Integer>> scores = new ArrayList<>();
        for (String key : sortedMap.keySet()) {
            scores.add(Map.entry(key, sortedMap.get(key)));
            counter++;
            if (counter == 20)
                break;
        }
        System.out.println(scores);
        for (int i = 0; i < scores.size(); i++) {
            printUser(Integer.parseInt(scores.get(i).getKey()), users);
        }
    }

    public static void printUser(int userId, ArrayList<User> users) {
        System.out.println("ID : " + users.get(userId).getId());
        System.out.println("Name : " + users.get(userId).getName());
        System.out.println("DateOfBirth : " + users.get(userId).getDateOfBirth());
        System.out.println("UniversityLocation : " + users.get(userId).getUniversityLocation());
        System.out.println("Field : " + users.get(userId).getField());
        System.out.println("Email : " + users.get(userId).getEmail());
        System.out.println("Workplace : " + users.get(userId).getWorkplace());
        System.out.println("Specialties : ");
        for (int i = 0; i < users.get(userId).getSpecialties().size(); i++) {
            System.out.println(users.get(userId).getSpecialties().get(i));
        }
        System.out.println("ConnectionId : ");
        for (int i = 0; i < users.get(userId).getConnectionId().size(); i++) {
            System.out.println(users.get(userId).getConnectionId().get(i));
        }
        System.out.println();
        System.out.println("----------------------------------------");
        System.out.println();
    }

    public void showListOfUsers(ArrayList<User> users) {
        for (int i = 0; i < users.size(); i++)
            System.out.println(users.get(i).getName());
    }

    public void searchByName(ArrayList<User> users, String name) {
        for (int i = 0; i < users.size(); i++)
            if (Objects.equals(users.get(i).getName(), name))
                printUser(i, users);
    }
}