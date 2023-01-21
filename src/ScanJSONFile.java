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
    public void readJSONFile() throws FileNotFoundException {
        JSONParser jsonParser = new JSONParser();
        ArrayList<User> users = new ArrayList<>();
        try (FileReader reader = new FileReader("users.json")) {
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
    }
}