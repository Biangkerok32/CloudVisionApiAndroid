package byronajin.com.cloudvisionapi.model.request;

/**
 * Created by Byron on 11/2/2017.
 */

public class SourceRequest {

    String imageUri;

    public SourceRequest(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
