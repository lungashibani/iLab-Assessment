package data;

import io.cucumber.datatable.DataTable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class represents pet details.
 */

public class petDetails {
    private String strId,strName,strStatus;
    private String[] strPhoto_Url;
    private List<tagDetails> arrTagDetails = new ArrayList<>();
    private categoryDetails objCategoryDetails;

    //Constructor initializing the variables with datatable values from feature file.
    public petDetails(DataTable dtPetDetails){
        List<Map<String,String>> petDetails = dtPetDetails.asMaps(String.class,String.class);
        this.strId = petDetails.get(0).get("Id");
        this.strName = petDetails.get(0).get("Name");
        this.strStatus = petDetails.get(0).get("Status");
        this.strPhoto_Url = petDetails.get(0).get("Photo_Url").split(";");

        //Creating array of tagDetails object.
        for (int iCountTagsId =0; iCountTagsId < petDetails.get(0).get("Tags_Id").split(";").length;iCountTagsId++){
            tagDetails tagDetailsObj = new tagDetails((petDetails.get(0).get("Tags_Id").split(";"))[iCountTagsId],
                    (petDetails.get(0).get("Tags_Name").split(";")[iCountTagsId]));
            this.arrTagDetails.add(tagDetailsObj);
        }

        this.objCategoryDetails =new categoryDetails(petDetails.get(0).get("Category_Id"),petDetails.get(0).get("Category_Name"));
    }

    public petDetails() {
        super();
    }

    public String getStrId() {
        return strId;
    }

    public void setStrId(String strId) {
        this.strId = strId;
    }

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }

    public String getStrStatus() {
        return strStatus;
    }

    public void setStrStatus(String strStatus) {
        this.strStatus = strStatus;
    }

    public String[] getStrPhoto_Url() {
        return strPhoto_Url;
    }

    public void setStrPhoto_Url(String[] strPhoto_Url) {
        this.strPhoto_Url = strPhoto_Url;
    }

    public List<tagDetails> getArrTagDetails() {
        return arrTagDetails;
    }

    public void setArrTagDetails(List<tagDetails> arrTagDetails) {
        this.arrTagDetails = arrTagDetails;
    }

    public categoryDetails getObjCategoryDetails() {
        return objCategoryDetails;
    }

    public void setObjCategoryDetails(categoryDetails objCategoryDetails) {
        this.objCategoryDetails = objCategoryDetails;
    }
}
