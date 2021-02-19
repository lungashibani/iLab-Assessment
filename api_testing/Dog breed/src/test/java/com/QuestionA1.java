package com;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;

public class QuestionA1 {

    final static String url = "https://dog.ceo/api/breeds/image/random";

    public static void main(String args[]) {

        getResponseBody();
        getResponseStatus();

        ; }

    //This will fetch the response body as is and log it. given and when are optional here
    public static void getResponseBody(){
        given().when().get(url).then().log()
                .all();

    }

    public static void getResponseStatus(){
        int statusCode= given()
                .when().get("https://dog.ceo/api/breed/").getStatusCode();
        System.out.println("The response status is "+statusCode);

        given().when().get(url).then().assertThat().statusCode(200)
                .assertThat().body("https://dog.ceo/dog-api/breeds-list", contains("Bull Mastif"));
    }



}
