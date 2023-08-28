package apiTest.day06;

import apiPOJOTemplates.PetStoreUser;
import com.google.gson.Gson;
import org.testng.annotations.Test;

import java.util.Map;

public class GSON_Test {

    /*
    {
  "id": 9223372036854762000,
  "username": "jack",
  "firstName": "jackie",
  "lastName": "jo",
  "email": "jack@gmail.com",
  "password": "test1234",
  "phone": "5554442211",
  "userStatus": 0
}

     */

    @Test
    public void jsonToMap() {

        Gson gson = new Gson();
        String myJsonBody="{\n" +
                "  \"id\": 9223372036854762000,\n" +
                "  \"username\": \"jack\",\n" +
                "  \"firstName\": \"jackie\",\n" +
                "  \"lastName\": \"jo\",\n" +
                "  \"email\": \"jack@gmail.com\",\n" +
                "  \"password\": \"test1234\",\n" +
                "  \"phone\": \"5554442211\",\n" +
                "  \"userStatus\": 0\n" +
                "}";
        System.out.println("myJsonBody = " + myJsonBody);
        //gson to converting to map
        Map<String,Object> dataMap = gson.fromJson(myJsonBody, Map.class);
        System.out.println("dataMap = " + dataMap);

        //gson converting to object class
        PetStoreUser oneUser = gson.fromJson(myJsonBody,PetStoreUser.class);
        System.out.println("oneUser = " + oneUser);

        //Serialization
        //Java collection or POJO to JSON
        PetStoreUser petStoreUser=new PetStoreUser(111,"mic","michael","master","mich@test.com","test1234","5553332221",33);
        String jsonUser=gson.toJson(petStoreUser);
        System.out.println("jsonUser = " + jsonUser);
    }
}
