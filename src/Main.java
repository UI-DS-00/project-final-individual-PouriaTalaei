import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        ArrayList<User> users = new ArrayList<>();
        ArrayList<User> sortedUser = new ArrayList<>();
        ScanJSONFile scanJSONFile = new ScanJSONFile();

        scanJSONFile.readJSONFile(users, sortedUser);
        Graph graph = new Graph(sortedUser.size());
        scanJSONFile.addUsersToGraph(sortedUser, graph);


        boolean exit = false;
        while (!exit) {
            System.out.println("""
                    1.ShowProfileOfUser & Suggest20SimilarUser
                    2.ShowListOfUsers
                    3.SearchByName
                    4.SearchById
                    5.eXit""");

            switch (input.next()) {
                case "1"://profile
                    int id = input.nextInt();
                    Graph.printUser(id, users);
                    scanJSONFile.bfs5Level((Integer.parseInt(sortedUser.get(id).getId()) - 1), graph, sortedUser);
                    break;

                case "2"://listOfUsers
                    graph.showListOfUsers(users);
                    break;

                case "3"://search by name
                    input.nextLine();
                    String name = input.nextLine();
                    graph.searchByName(users, name);
                    break;

                case "4"://search by id
                    int userId = input.nextInt();
                    Graph.printUser(userId, users);
                    break;

                case "5":
                    exit = true;
                    break;
            }
        }
    }
}