package apiTest.day04;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.AccessibleObject;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.baseURI;

public class KraftExlabJsonPathMethod {
    /*
TASK
When user sends a GET request to /allusers/getbyid/{id}
Given accept type is json
And Path param user id is 111
Then the status Code should be 200
And Content type json should be "application/json; charset=UTF-8"
And user's name should be Thomas Eduson
And user's id should be 111
And user's email should be thomas@test.com
*/

    @BeforeClass
    public void beforeClass() {
        baseURI="https://www.krafttechexlab.com/sw/api/v1";
    }
    @Test
    public void getUserAndVerifyItWithJsonPath(){
        Response response = RestAssured
                .given()
                .accept(ContentType.JSON)
                .pathParam("id", 111)
                .when()
                .get("https://www.krafttechexlab.com/sw/api/v1/allusers/getbyid/{id}");

        // VERIFICATIONS WITH PATH METHOD
        //verify status code
        Assert.assertEquals(response.statusCode(),200);

        //verify content type
        Assert.assertEquals(response.contentType(),"application/json; charset=UTF-8");

        //verify id
        int expectedId = 111;
        int actualId = response.path("id[0]");
        Assert.assertEquals(actualId,expectedId);

        //verify name
        String expectedName = "Thomas Eduson";
        String actualName = response.path("name[0]");
        Assert.assertEquals(actualName,expectedName);

        //verify email
        String expectedEmail ="thomas@test.com";
        String actualEmail = response.path("email[0]");
        Assert.assertEquals(actualEmail,expectedEmail);

        // VERIFICATION WITH JSONPATH
        JsonPath jsonPath = response.jsonPath();

        // verify id
        int actualIdWithJsonPath = jsonPath.getInt("id[0]");
        Assert.assertEquals(actualIdWithJsonPath,expectedId);

        //verify name
        String actualNameWithJsonPath = jsonPath.getString("name[0]");
        Assert.assertEquals(actualNameWithJsonPath,expectedName);

        //verify email
        String actulaEmailWithJsonPath = jsonPath.getString("email[0]");
        Assert.assertEquals(actulaEmailWithJsonPath,expectedEmail);
    }


    /*
    TASK
    Given accept type is json
    query parameters:
    page -> 1
    pagesize -> 5
    When user sends a GET request to /allusers/alluser
    Then the status Code should be 200
    And Content type json should be "application/json; charset=UTF-8"
    Make several verification with JsonPath
    */

    @Test
    public void getAllUsersWithJsonPath(){
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .queryParam("page", 1)
                .queryParam("pagesize", 5)
                .when()
                .get("allusers/alluser");

        // verify status code
        Assert.assertEquals(response.statusCode(),200);

        //verify content type
        Assert.assertEquals(response.contentType(),"application/json; charset=UTF-8");

        //create a jsonpath object
        JsonPath jsonPath = response.jsonPath();

        //verify that first id is equal to 1
        int expectedId = 1;
        int actualId = jsonPath.getInt("id[0]");
        Assert.assertEquals(actualId,expectedId);

        //verify that 5th id is equal to 33
        int fifthExpectedId = 33;
        int fifthActualId = jsonPath.getInt("id[4]");
        Assert.assertEquals(fifthActualId,fifthExpectedId);

        // verify that fifth name is "Sebastian"
        String fifthExpectedName = "Sebastian";
        String fifthActualName= jsonPath.getString("name[4]");
        Assert.assertEquals(fifthActualName,fifthExpectedName);

        //verify that all ids are 1,5,24,29,33
        List<Integer> expectedIds = new ArrayList<>();
        expectedIds.add(1);
        expectedIds.add(5);
        expectedIds.add(24);
        expectedIds.add(29);
        expectedIds.add(33);
        List<Integer> actualIds = jsonPath.getList("id");
        Assert.assertEquals(actualIds,expectedIds);

        // verify that first skill of first user is PHP

        //first way
        String expectedSkill ="PHP";
        String actualSkill = jsonPath.getString("skills[0][0]");
        Assert.assertEquals(actualSkill,expectedSkill);

        //second way
        List<String> list = jsonPath.getList("skills[0]");
        String actualSkills2=list.get(0);
        Assert.assertEquals(actualSkills2,expectedSkill);

    }



}
