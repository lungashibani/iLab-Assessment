package data;

/**
 * Class represents tag details for pet.
 */
public class tagDetails {
    private String strTagId,strTagName;

    public tagDetails(String strTagId,String strTagName){
        this.strTagId = strTagId;
        this.strTagName = strTagName;
    }

    public tagDetails(){
            super();
    }

    public String getStrTagId() {
        return strTagId;
    }

    public void setStrTagId(String strTagId) {
        this.strTagId = strTagId;
    }

    public String getStrTagName() {
        return strTagName;
    }

    public void setStrTagName(String strTagName) {
        this.strTagName = strTagName;
    }
}
