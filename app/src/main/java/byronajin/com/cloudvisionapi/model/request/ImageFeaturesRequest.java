package byronajin.com.cloudvisionapi.model.request;

import java.util.List;

/**
 * Created by Byron on 11/1/2017.
 */

public class ImageFeaturesRequest {
    List<Request> requests;

    public ImageFeaturesRequest(List<Request> requests) {
        this.requests = requests;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }
}
