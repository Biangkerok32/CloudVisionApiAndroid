package byronajin.com.cloudvisionapi.model.request;

import java.util.List;

import byronajin.com.cloudvisionapi.model.request.FeatureRequest;
import byronajin.com.cloudvisionapi.model.request.ImageRequest;

/**
 * Created by Byron on 11/1/2017.
 */

public class Request {

    ImageRequest image;
    List<FeatureRequest> features;

    public Request(ImageRequest image, List<FeatureRequest> features) {
        this.image = image;
        this.features = features;
    }

    public ImageRequest getImage() {
        return image;
    }

    public void setImage(ImageRequest image) {
        this.image = image;
    }
}
