package stepDefinition;

import data.orderDetails;
import data.petDetails;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.Assert;

//Step definition class for all POST operations
public class postMethods {
    private Scenario scenario;
    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
    }

    /*Pet details from datable is stored to petDetails/categoryDetails/tagDetails POJO class, and then converted to JSON string.*/
    @Given("^I perform POST operation to add a new pet with body$")
    public void iPerformPOSTOperationToAddANewPetForWithBody(DataTable dtPetDetails) {

        petDetails data = new petDetails(dtPetDetails);

        //Fetching pet url from config.properties
        String strPetUrl = util.commonFunctions.getConfigPropertyAsString("PetURL");

        //petDetails Object is converted to JSON string and passed as body to POST operation.
        Response postCallResponse = util.commonFunctions.performOperationPOST(strPetUrl,util.commonFunctions.convertPetDetailsJavaOBJtoJSONstring(data));
        ResponseBody bodyOfResponse=postCallResponse.getBody();

        Assert.assertTrue(Integer.toString(postCallResponse.statusCode()).equals("200"),
                "Pet added Successfully");

        //Custom logging to cucumber report.
        scenario.write("Pet with ID:"+bodyOfResponse.jsonPath().getString("id")+" created.");
    }

    /*Order details from datatable is stored into orderDetails POJO class, and then converted to JSON string.*/
    @Given("^I perform POST operation to place an order for pet.$")
    public void iPerformPOSTOperationToPlaceAnOrderForPet(DataTable dtOrderDetails) {
        orderDetails ObjOrderDetails = new orderDetails(dtOrderDetails);
        //Fetching pet url from config.properties
        String strStoreUrl = util.commonFunctions.getConfigPropertyAsString("StoreURL");

        //orderDetails Object is converted to JSON string and passed as body to POST operation.
        Response postCallResponse = util.commonFunctions.
                performOperationPOST(strStoreUrl,util.commonFunctions.convertOrderDetailsJavaOBJtoJSONstring(ObjOrderDetails));
        ResponseBody bodyOfResponse=postCallResponse.getBody();

        Assert.assertTrue(Integer.toString(postCallResponse.statusCode()).equals("200"),
                "Order placed Successfully");

        //Custom logging to cucumber report.
        scenario.write("Order with ID:"+bodyOfResponse.jsonPath().getString("id")+" created.");
    }
}
