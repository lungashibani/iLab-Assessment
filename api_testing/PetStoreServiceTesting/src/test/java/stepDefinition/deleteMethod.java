package stepDefinition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.testng.Assert;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Step definition class for all DELETE operations
public class deleteMethod {

    private Scenario scenario;
    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
    }

    @Then("^I perform a DELETE operation with ID and verify the data is removed.$")
    public void iPerformADELETEOperationWithIDAndVerifyTheDataIsRemoved(DataTable dtPetDetails) {

        List<Map<String,String>> petDetails = dtPetDetails.asMaps(String.class,String.class);
        Map<String,String> pathParam = new HashMap<>();
        pathParam.put("id",petDetails.get(0).get("Id"));
        String strPetUrl = util.commonFunctions.getConfigPropertyAsString("PetURL");
        Response myResponse = util.commonFunctions.performOperationDELETE(pathParam,strPetUrl);

        Assert.assertTrue(Integer.toString(myResponse.statusCode()).equals("200"),
                "Pet deleted Successfully");

        scenario.write("Pet with ID:"+petDetails.get(0).get("Id")+" deleted Successfully");
    }
}
