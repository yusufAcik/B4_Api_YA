package apiTest.day07;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class PostRequestDemo {

    @BeforeClass
    public void beforeClass() {
        baseURI="https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void postNewUser(){
        String jsonBody = "{\n" +
                "  \"name\": \"jackie\",\n" +
                "  \"email\": \"jack123@krafttechexlab.com\",\n" +
                "  \"password\": \"123467\",\n" +
                "  \"about\": \"About Me\",\n" +
                "  \"terms\": \"10\"\n" +
                "}";

        Response response = given().accept(ContentType.JSON) // hey api send me body as json format
                .and()
                .contentType(ContentType.JSON) // hey api I am sending json body
                .and()
                .body(jsonBody)
                .when()
                .post("/allusers/register");

        assertEquals(response.statusCode(),200);
        response.prettyPrint();
        assertTrue(response.body().asString().contains("token"));

    }

    @Test
    public void postNewUser2() {

        Map<String,Object> requestMap = new HashMap<>();

        requestMap.put("name","jackie");
        requestMap.put("email","jack1234@krafttechexlab.com");
        requestMap.put("password","123467");
        requestMap.put("about","About Me");
        requestMap.put("terms","10");

        Response response = given().accept(ContentType.JSON) // hey api send me body as json format
                .and()
                .contentType(ContentType.JSON) // hey api I am sending json body
                .and()
                .body(requestMap)
                .when()
                .post("/allusers/register");

        assertEquals(response.statusCode(),200);
        response.prettyPrint();
        assertTrue(response.body().asString().contains("token"));

    }

    @Test
    public void postNewUser3() {

        NewUserInfo newUserInfo = new NewUserInfo();
        newUserInfo.setName("jackie");
        newUserInfo.setEmail("jack12345@krafttechexlab.com");
        newUserInfo.setPassword("123467");
        newUserInfo.setAbout("About Me");
        newUserInfo.setTerms("6");

        Response response = given().accept(ContentType.JSON) // hey api send me body as json format
                .and()
                .contentType(ContentType.JSON) // hey api I am sending json body
                .and()
                .body(newUserInfo) // serialization
                .when()
                .post("/allusers/register");

        assertEquals(response.statusCode(),200);
        response.prettyPrint();
        assertTrue(response.body().asString().contains("token"));

    }

    @Test
    public void postNewUser4() {

        NewUserInfo newUserInfo = new NewUserInfo("jackie5","jacktj@krafttechexlab.com","123467","SDET","4");


        Response response = given().accept(ContentType.JSON) // hey api send me body as json format
                .and()
                .contentType(ContentType.JSON) // hey api I am sending json body
                .and()
                .body(newUserInfo) // serialization
                .when()
                .post("/allusers/register");

        assertEquals(response.statusCode(),200);
        response.prettyPrint();
        assertTrue(response.body().asString().contains("token"));

    }
}
