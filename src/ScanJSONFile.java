import com.fasterxml.jackson.databind.ObjectMapper;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ScanJSONFile {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("users.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray employeeList = (JSONArray) obj;
            // System.out.println(employeeList);
            Iterator<JSONObject> jsonObjectIterator = employeeList.iterator();
            while (jsonObjectIterator.hasNext()) {
                JSONObject object = (JSONObject) jsonObjectIterator.next();
                String str1 = (String) object.get("name");
                String str = object.toString();
                System.out.println(str1);
            }
            //Iterate over employee array
            employeeList.forEach(emp -> parseEmployeeObject((JSONObject) emp));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void parseEmployeeObject(JSONObject employee) {
        //Get employee object within list
        JSONObject employeeObject = (JSONObject) employee.get("employee");

        //Get employee first name
        String firstName = (String) employeeObject.get("firstName");
        System.out.println(firstName);

        //Get employee last name
        String lastName = (String) employeeObject.get("lastName");
        System.out.println(lastName);

        //Get employee website name
        String website = (String) employeeObject.get("website");
        System.out.println(website);
    }
}

//    // Data data =
//
//    ObjectMapper objectMapper = new ObjectMapper();
//    Data data = objectMapper.readValue(new File("users.json"), Data.class);
//    //Data data = objectMapper.readValue(new File("F:\\IntellijCodes\\DataStructure\\LinkedinFinalProject\\project-final-individual-PouriaTalaei-main\\project-final-individual-PouriaTalaei\\untitled\\users.json"), Data.class);
//
//    public ScanJSONFile() throws IOException {
//    }
//}
//
//class Car {
//
//    private String color;
//    private String type;
//}
