import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ScanJSONFile {
    @SuppressWarnings("unchecked")
    public void readJSONFile(ArrayList<User> users, ArrayList<User> sortedUser) throws FileNotFoundException {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("users1.json")) {
            Object obj = jsonParser.parse(reader);
            JSONArray employeeList = (JSONArray) obj;
            Iterator<JSONObject> jsonObjectIterator = employeeList.iterator();
            while (jsonObjectIterator.hasNext()) {
                JSONObject jsonObject = (JSONObject) jsonObjectIterator.next();
                User user = new User();
                user.setName((String) jsonObject.get("name"));
                user.setId((String) jsonObject.get("id"));
                user.setDateOfBirth((String) jsonObject.get("dateOfBirth"));
                user.setUniversityLocation((String) jsonObject.get("universityLocation"));
                user.setEmail((String) jsonObject.get("email"));
                user.setField((String) jsonObject.get("field"));
                user.setWorkplace((String) jsonObject.get("workplace"));
                user.setSpecialties((ArrayList<String>) jsonObject.get("specialties"));
                user.setConnectionId((ArrayList<String>) jsonObject.get("connectionId"));
                users.add(user);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < users.size(); i++) {
            for (int j = 0; j < users.size(); j++) {
                if ((Integer.parseInt(users.get(j).getId()) - 1) == i) {
                    sortedUser.add(users.get(j));
                    break;
                }
            }
        }
    }

    public void addUsersToGraph(ArrayList<User> users, Graph graph) {
        for (int i = 0; i < users.size(); i++) {
            for (int j = 0; j < users.get(i).getConnectionId().size(); j++) {
                graph.addEdge(Integer.parseInt(users.get(i).getId()) - 1, Integer.parseInt(users.get(i).getConnectionId().get(j)) - 1);
            }
        }
    }

    public void bfs5Level(int id, Graph graph, ArrayList<User> users) {
        graph.BFS(id, users);
        graph.sortMap(users);
    }

}