package stepDefinition;

import data.orderDetails;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Step definition class for all GET operations
public class getMethod {

    private Scenario scenario;
    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
    }

    @Then("I perform a GET operation with ID to verify the details are added correctly")
    public void iPerformAGETOperationWithIDToVerifyTheDetailsAreAddedCorrectly(DataTable dtPetDetails) {

        List<Map<String,String>> petDetails = dtPetDetails.asMaps(String.class,String.class);
        Map<String,String> pathParam = new HashMap<>();
        pathParam.put("id",petDetails.get(0).get("Id"));

        //Fetching pet url from config.properties
        String strPetUrl = util.commonFunctions.getConfigPropertyAsString("PetURL");

        Response getCallResponse = util.commonFunctions.performOperationGET("id",pathParam,strPetUrl);
        ResponseBody bodyOfResponse=getCallResponse.getBody();

        //Verifying details returned from GET operation.
        Assert.assertEquals(bodyOfResponse.jsonPath().getString("name"),petDetails.get(0).get("Name"));
        Assert.assertEquals(bodyOfResponse.jsonPath().getString("status"),petDetails.get(0).get("Status"));

        //Custom logging to cucumber report.
        scenario.write("Verified id:" +petDetails.get(0).get("Id")+ " is updated with values, name:"+
                        petDetails.get(0).get("Name") +" ,status:" +petDetails.get(0).get("Status"));
    }

    @Then("^I verify with ID to check data is removed.$")
    public void iVerifyWithIDToCheckDataIsRemoved(DataTable dtPetDetails) {
        List<Map<String,String>> petDetails = dtPetDetails.asMaps(String.class,String.class);
        Map<String,String> pathParam = new HashMap<>();
        pathParam.put("id",petDetails.get(0).get("Id"));

        //Fetching pet url from config.properties
        String strPetUrl = util.commonFunctions.getConfigPropertyAsString("PetURL");

        Response myResponse = util.commonFunctions.performAndVerifyNotFoundWithOperationGET(pathParam,strPetUrl);

        //404 returned when no records fetched.
        Assert.assertTrue(Integer.toString(myResponse.statusCode()).equals("404"),
                "Pet not retrieved.");

        //Custom logging to cucumber report.
        scenario.write("Pet with ID:"+petDetails.get(0).get("Id")+" is not present.");
    }

    /*Method uses POJO class to store the GET response and compare with expected*/
    @And("^I verify the status of the order.$")
    public void iVerifyTheStatusOfTheOrder(DataTable dtOrderDetails) {

        List<Map<String,String>> petDetails = dtOrderDetails.asMaps(String.class,String.class);
        Map<String,String> pathParam = new HashMap<>();

        //Fetching store url from config.properties
        String strStoreUrl = util.commonFunctions.getConfigPropertyAsString("StoreURL");
        pathParam.put("order",petDetails.get(0).get("Id"));

        Response getCallResponse = util.commonFunctions.performOperationGET("order",pathParam,strStoreUrl);
        ResponseBody bodyOfResponse=getCallResponse.getBody();

        //JSON string returned by GET operation is converted into orderDetails class object.
        orderDetails ObjOrderDetail=util.commonFunctions.convertOrderDetailsJSONstringToJavaOBJ(bodyOfResponse.asString());

        //Verifying status returned from GET operation with the datatable value.
        Assert.assertEquals(ObjOrderDetail.getStrStatus(),petDetails.get(0).get("Status"));

        //Custom logging to cucumber report.
        scenario.write("Status of order with ID:"+ObjOrderDetail.getStrId()+" is "+ObjOrderDetail.getStrStatus());
    }
}
