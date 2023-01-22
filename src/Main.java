import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        ArrayList<User> users = new ArrayList<>(999);
        ArrayList<User> sortedUser = new ArrayList<>();
        ScanJSONFile scanJSONFile = new ScanJSONFile();
        scanJSONFile.readJSONFile(users,sortedUser);
        Graph graph = new Graph(sortedUser.size());
        scanJSONFile.addUsersToGraph(sortedUser, graph);

        int id = input.nextInt();
//        int idInUsers = -1;
//        for (int i = 0; i < users.size(); i++) {
//            if (sortedUserusers.get(i).getId().equals(String.valueOf(id))) {
//                idInUsers = i;
//                break;
//            }
//        }
        scanJSONFile.bfs5Level((Integer.parseInt(sortedUser.get(id).getId())-1), graph, sortedUser);


    }
}