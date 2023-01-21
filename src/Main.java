import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        ArrayList<User> users = new ArrayList<>();
        ScanJSONFile scanJSONFile = new ScanJSONFile();
        scanJSONFile.readJSONFile(users);
        Graph graph = new Graph(users.size());
        scanJSONFile.addUsersToGraph(users, graph);

        int id = input.nextInt() ;
        int idInUsers=-1;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(String.valueOf(id))){
                idInUsers = i;
                break;
            }
        }
        scanJSONFile.bfs5Level(Integer.parseInt(users.get(idInUsers).getId()),graph,users);
    }
}