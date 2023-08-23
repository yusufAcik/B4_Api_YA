package apiTest.day03;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.testng.Assert.*;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;

public class PetStoreWithPathParam {

    @BeforeClass
    public void beforeClass() {
        baseURI = "https://petstore.swagger.io/v2";
    }

    @Test
    public void petStoreFindPathByID() {
        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get("pet/445");
        response.prettyPrint();

        assertEquals(response.statusCode(), 200);

    }

    @Test
    public void petStoreFindPathByID2() {
        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("petID",445)
                .when()
                .get("pet/{petID}");
            response.prettyPrint();
            assertEquals(response.statusCode(),200);

    }
    @Test
    public void petStoreFindPathByID3() {
        int petID=445;
        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id",petID)
                .when()
                .get("pet/{id}");
        response.prettyPrint();
        assertEquals(response.statusCode(),200);

    }
}
