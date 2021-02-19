package util;

import data.orderDetails;
import data.petDetails;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.specification.RequestSpecification;
import jodd.props.Props;
import org.testng.Assert;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import static io.restassured.RestAssured.given;

/*Common utility class*/

public class commonFunctions {
    public static Response operationResponse;
    public static RequestSpecification Request;

    //Method to convert petDetails Java Object to JSON.
    public static String convertPetDetailsJavaOBJtoJSONstring(petDetails petDetailsObject) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String strJson = mapper.writeValueAsString(petDetailsObject);
            strJson=strJson.replaceAll("strId","id");
            strJson=strJson.replaceAll("strName","name");
            strJson=strJson.replaceAll("strStatus","status");
            strJson=strJson.replaceAll("strPhoto_Url","photoUrls");
            strJson=strJson.replaceAll("arrTagDetails","tags");
            strJson=strJson.replaceAll("strTagId","id");
            strJson=strJson.replaceAll("strTagName","name");
            strJson=strJson.replaceAll("objCategoryDetails","category");
            strJson=strJson.replaceAll("strCategoryId","id");
            strJson=strJson.replaceAll("strCategoryName","name");
            //System.out.println("ResultingJSONstring = " + json);
            return strJson;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Method to convert orderDetails Java Object to JSON.
    public static String convertOrderDetailsJavaOBJtoJSONstring(orderDetails orderDetailsObject) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String strJson = mapper.writeValueAsString(orderDetailsObject);
            strJson=strJson.replaceAll("strId","id");
            strJson=strJson.replaceAll("strPetId","petId");
            strJson=strJson.replaceAll("strStatus","status");
            strJson=strJson.replaceAll("strComplete","complete");
            strJson=strJson.replaceAll("strShipDate","shipDate");
            strJson=strJson.replaceAll("iQuantity","quantity");
            //System.out.println("ResultingJSONstring = " + json);
            return strJson;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Method to convert orderDetails JSON response to Java Object.
    public static orderDetails convertOrderDetailsJSONstringToJavaOBJ(String json) {
        orderDetails objOrderDetails=null;

        json=json.replaceAll("id","strId");
        json=json.replaceAll("petId","strPetId");
        json=json.replaceAll("quantity","iQuantity");
        json=json.replaceAll("shipDate","strShipDate");
        json=json.replaceAll("status","strStatus");
        json=json.replaceAll("complete","strComplete");

        ObjectMapper mapper = new ObjectMapper();
        try {
            objOrderDetails = mapper.readValue(json, orderDetails.class);
            String prettyOrderDetails = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(objOrderDetails);
            //System.out.println(prettyOrderDetails);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return objOrderDetails;
    }

    /*Method performing GET operations*/
    public static Response performOperationGET
            (String strParam,Map<String,String> pathParam,String strUrl){
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri(strUrl);
        builder.setContentType(ContentType.JSON);
        var requestSpec=builder.build();

        Request = RestAssured.given().spec(requestSpec);
        Request.pathParams(pathParam);
        operationResponse = Request.get("/{"+strParam+"}");

        System.out.println("Get operation performed with Status Code:"+operationResponse.statusCode());
        Assert.assertTrue(Integer.toString(operationResponse.statusCode()).equals("200"),
                "Verifying post request returns status code 200");
        System.out.println("Get operation performed with Status Line:"+operationResponse.statusLine());
        Assert.assertTrue(operationResponse.statusLine().equals("HTTP/1.1 200 OK"),
                "Verifying post request returns status Line HTTP/1.1 200 OK");

        return operationResponse;
    }

    /*Method to verify the response when no records is fetched from GET operation*/
    public static Response performAndVerifyNotFoundWithOperationGET
            (Map<String,String> pathParam,String strUrl){
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri(strUrl);
        builder.setContentType(ContentType.JSON);
        var requestSpec=builder.build();

        Request = RestAssured.given().spec(requestSpec);
        Request.pathParams(pathParam);
        operationResponse = Request.get("/{id}");

        System.out.println("Get operation performed with Status Code:"+operationResponse.statusCode());
        Assert.assertTrue(Integer.toString(operationResponse.statusCode()).equals("404"),
                "Verifying post request returns status code 404");
        System.out.println("Get operation performed with Status Line:"+operationResponse.statusLine());
        Assert.assertTrue(operationResponse.statusLine().equals("HTTP/1.1 404 Not Found"),
                "Verifying post request returns status Line HTTP/1.1 404 Not Found");

        return operationResponse;
    }

    //Method to perform delete operation.
    public static Response performOperationDELETE
            (Map<String,String> pathParam,String strUrl){

        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri(strUrl);
        builder.setContentType(ContentType.JSON);
        var requestSpec=builder.build();

        Request = RestAssured.given().spec(requestSpec);
        Request.pathParams(pathParam);
        operationResponse = Request.delete("/{id}");

        System.out.println("Delete operation performed with Status Code:"+operationResponse.statusCode());
        Assert.assertTrue(Integer.toString(operationResponse.statusCode()).equals("200"),
                "Verifying delete request returns status code 200");
        System.out.println("Delete operation performed with Status Line:"+operationResponse.statusLine());
        Assert.assertTrue(operationResponse.statusLine().equals("HTTP/1.1 200 OK"),
                "Verifying delete request returns status Line HTTP/1.1 200 OK");

        return operationResponse;
    }

    /*Method performing POST operation*/
    public static Response performOperationPOST
    (String url,String strBody){
        operationResponse = given()
                .contentType(ContentType.JSON)
                .with()
                .body(strBody)
                .when()
                .post(url);
        System.out.println("Post operation performed with Status Code:"+operationResponse.statusCode());
        Assert.assertTrue(Integer.toString(operationResponse.statusCode()).equals("200"),
                "Verifying post request returns status code 200");
        System.out.println("Post operation performed with Status Line:"+operationResponse.statusLine());
        Assert.assertTrue(operationResponse.statusLine().equals("HTTP/1.1 200 OK"),
                "Verifying post request returns status Line HTTP/1.1 200 OK");

        return operationResponse;
    }

    //Method to fetch data form config.properties
    public static String getConfigPropertyAsString(String strPropertyName) {
        Props p = new Props();
        File configFile = new File("src\\test\\java\\resources\\config.properties");
        try {
            p.load(new File(configFile.getAbsolutePath()));
        }
        catch(Exception e) {
            //log.error("Error while accessing config.properties");
            e.printStackTrace();
        }
        return p.getValue(strPropertyName);
    }
}
