package apiTest.day06;

import apiPOJOTemplates.PetStoreUser;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class POJO_DeSerialization {
    @BeforeClass
    public void beforeClass(){
        baseURI="https://petstore.swagger.io/v2";
    }

    @Test
    public void oneUserPetStore(){
        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("username", "jack")
                .when()
                .get("user/{username}");

        System.out.println("response.statusCode() = " + response.statusCode());

        //JSON to our petStore object
        PetStoreUser oneUser = response.body().as(PetStoreUser.class);
        //print all information
        System.out.println("oneUser.getId() = " + oneUser.getId());
        System.out.println("oneUser.getUserStatus() = " + oneUser.getUserStatus());
        System.out.println("oneUser.getFirstName() = " + oneUser.getFirstName());
        System.out.println("oneUser.getEmail() = " + oneUser.getEmail());
        System.out.println("oneUser.getLastName() = " + oneUser.getLastName());
        System.out.println("oneUser.getPhone() = " + oneUser.getPhone());

        //verify all information
        Assert.assertEquals(oneUser.getId(),9223372036854762600d);
        Assert.assertEquals(oneUser.getEmail(),"jack@gmail.com");
        Assert.assertEquals(oneUser.getPhone(),"5554442211");
        Assert.assertEquals(oneUser.getFirstName(),"jackie");
        Assert.assertEquals(oneUser.getLastName(),"jo");


    }
}
