package byronajin.com.cloudvisionapi.model.request;

/**
 * Created by Byron on 11/1/2017.
 */

public class FeatureRequest {

    String type;

    public FeatureRequest(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
