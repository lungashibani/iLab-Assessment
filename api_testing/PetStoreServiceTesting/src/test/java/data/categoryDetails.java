package data;

/**
 * Class represents category details for pet.
 */
public class categoryDetails {
    private String strCategoryId,strCategoryName;

    public categoryDetails(String strCategoryId,String strCategoryName){
        this.strCategoryId=strCategoryId;
        this.strCategoryName=strCategoryName;
    }

    public categoryDetails(){
        super();
    }

    public String getStrCategoryId() {
        return strCategoryId;
    }

    public void setStrCategoryId(String strCategoryId) {
        this.strCategoryId = strCategoryId;
    }

    public String getStrCategoryName() {
        return strCategoryName;
    }

    public void setStrCategoryName(String strCategoryName) {
        this.strCategoryName = strCategoryName;
    }
}