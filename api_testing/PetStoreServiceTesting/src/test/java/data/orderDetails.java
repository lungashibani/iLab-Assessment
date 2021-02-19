package data;

import io.cucumber.datatable.DataTable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Class represents order details.
 */
public class orderDetails {
    private String strId,strPetId,strStatus,strComplete,strShipDate;
    private int iQuantity;

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();

    //Constructor initializing the variables with datatable values from feature file.
    public orderDetails(DataTable dtOrderDetails){
        Date date = new Date();
        List<Map<String,String>> orderDetails = dtOrderDetails.asMaps(String.class,String.class);
        this.strId = orderDetails.get(0).get("Id");
        this.strPetId = orderDetails.get(0).get("PetId");
        this.iQuantity = Integer.parseInt(orderDetails.get(0).get("Quantity").trim());
        this.strStatus = "placed";
        this.strComplete = "true";
        this.strShipDate = formatter.format(date).toString();
    }

    public orderDetails() {
        super();
    }

    public String getStrId() {
        return strId;
    }

    public void setStrId(String strId) {
        this.strId = strId;
    }

    public String getStrPetId() {
        return strPetId;
    }

    public void setStrPetId(String strPetId) {
        this.strPetId = strPetId;
    }

    public String getStrStatus() {
        return strStatus;
    }

    public void setStrStatus(String strStatus) {
        this.strStatus = strStatus;
    }

    public String getStrComplete() {
        return strComplete;
    }

    public void setStrComplete(String strComplete) {
        this.strComplete = strComplete;
    }

    public String getStrShipDate() {
        return strShipDate;
    }

    public void setStrShipDate(String strShipDate) {
        this.strShipDate = strShipDate;
    }

    public int getiQuantity() {
        return iQuantity;
    }

    public void setiQuantity(int iQuantity) {
        this.iQuantity = iQuantity;
    }
}
