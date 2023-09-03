package apiTest.day07;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.*;

public class PostEducation  {

    @BeforeClass
    public void beforeClass() {
        baseURI="https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void postNewUser4_withEducation() {

        NewUserInfo newUserInfo = new NewUserInfo("jackie1","jacktm@krafttechexlab.com","123468","SDET","4");


        Response response = given().accept(ContentType.JSON) // hey api send me body as json format
                .and()
                .contentType(ContentType.JSON) // hey api I am sending json body
                .and()
                .body(newUserInfo) // serialization
                .when()
                .post("/allusers/register");

        assertEquals(response.statusCode(),200);
        response.prettyPrint();
        String user_token = response.path("token");

        String educationBody = "{\n" +
                "    \"id\": 1681,\n" +
                "    \"userid\": 2121,\n" +
                "    \"school\": \"Kraft\",\n" +
                "    \"degree\": \"good\",\n" +
                "    \"study\": \"SDET\",\n" +
                "    \"description\": \"Description\",\n" +
                "    \"fromdate\": \"2020-11-22\",\n" +
                "    \"todate\": \"2021-11-22\"\n" +
                "}";

               response = given().accept(ContentType.JSON)
                        .and()
                        .contentType(ContentType.JSON)
                        .and()
                        .body(educationBody)
                        .and()
                        .header("token",user_token)
                        .when()
                        .post("/education/add");

                 response.prettyPrint();
               assertEquals(response.statusCode(),200);

    }

    @Test
    public void postNewUserAndVerify(){

        String name = "jackie";
        String email = "jackdd@krafttechexlab.com";
        String password = "test1234";
        String about = "SDET";
        String terms = "8";


        Map<String,Object> requestMap = new HashMap<>();

        requestMap.put("name",name);
        requestMap.put("email",email);
        requestMap.put("password",password);
        requestMap.put("about",about);
        requestMap.put("terms",terms);

        Response response = given().accept(ContentType.JSON) // hey api send me body as json format
                .and()
                .contentType(ContentType.JSON) // hey api I am sending json body
                .and()
                .body(requestMap)
                .when()
                .post("/allusers/register");

        assertEquals(response.statusCode(),200);
        response.prettyPrint();
        String user_token = response.path("token");
       int userID = response.path("id");

        Map<String,Object> educationBody = new HashMap<>();
        educationBody.put("school","Krafttech");
        educationBody.put("degree","BootCamp");
        educationBody.put("study","SDET");
        educationBody.put("fromdate","2021-01-01");
        educationBody.put("todate","2021-04-04");
        educationBody.put("current","false");
        educationBody.put("description","Desc");

        response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(educationBody)
                .and()
                .header("token",user_token)
                .when()
                .post("/education/add");

        assertEquals(response.statusCode(),200);
        response.prettyPrint();

        String user_school=response.path("school");

        // verify body
        int edu_id = response.path("id");

        response=given().accept(ContentType.JSON)
                .and()
                .header("token",user_token)
                .and()
                .pathParam("id",edu_id)
                .when()
                .get("education/getbyid/{id}");
        response.prettyPrint();

        // verify with path
        assertEquals((int)response.path("userid"),userID);
        assertEquals(response.path("school"),user_school);

        // verify using hamcrest matcher

        given().accept(ContentType.JSON)
                .and()
                .header("token",user_token)
                .and()
                .pathParam("id",edu_id)
                .when()
                .get("education/getbyid/{id}")
                .then()
                .assertThat()
                .body("school",equalTo(user_school),
                        "userid",equalTo(userID),
                        "study",equalTo("SDET")).log().all();


    }
}
