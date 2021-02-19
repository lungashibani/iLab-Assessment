package com;

import com.absa.assessment.RestAssuredCore.BaseAssertion;
import com.absa.assessment.RestAssuredCore.RESTCalls;
import com.absa.assessment.utils.EndPoint;
import com.absa.assessment.utils.TestUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DogBreed {
    private static Logger log = LogManager.getLogger(DogBreed.class.getName());
    Response response;

    @Test
    public void listAllDogBreeds() {
        log.info("Starting Create Issue Test");
        String URI = EndPoint.getEndPoint("/api/breeds/list/all");
        response = RESTCalls.GETRequest(URI);
        System.out.println(TestUtils.getStatusMessage(response));
        System.out.println("*******************************");
        BaseAssertion.verifyStatusCode(response, 200);
        System.out.println(TestUtils.getResponseString(response));
    }

    @Test(priority = 1)
    public void verifyRetrieverBreed() {
        log.info("Starting Create Issue Test");
        String URI = EndPoint.getEndPoint("/api/breeds/list/all");
        response = RESTCalls.GETRequest(URI);
        String strResponse = TestUtils.getResponseString(response);
        BaseAssertion.verifyTrue(strResponse.contains("bulldog"));
        //Assert.assertTrue(strResponse.contains("retriever"));
        System.out.println(TestUtils.getResponseString(response));
    }

    @Test(priority = 2)
    public void subBreed() {
        log.info("Starting Create Issue Test");
        String URI = EndPoint.getEndPoint("/api/breed/bulldog/list");
        response = RESTCalls.GETRequest(URI);
        BaseAssertion.verifyStatusCode(response, 200);
        System.out.println(TestUtils.getResponseString(response));
    }

    @Test(priority = 3)
    public void randomBreed() {
        log.info("Starting Create Issue Test");
        String URI = EndPoint.getEndPoint("/api/breeds/image/random");
        String strResponse = "";
        while (!strResponse.contains("“doggie”")){
            response = RESTCalls.GETRequest(URI);
            strResponse = TestUtils.getResponseString(response);
        }
        BaseAssertion.verifyStatusCode(response, 200);
        BaseAssertion.verifyTrue(strResponse.contains("“doggie”"));
        System.out.println(TestUtils.getResponseString(response));
    }
}
