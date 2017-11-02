package byronajin.com.cloudvisionapi.model.response;

import java.util.List;

import byronajin.com.cloudvisionapi.model.response.LabelAnnotations;

/**
 * Created by Byron on 11/2/2017.
 */

public class Responses {
    List<LabelAnnotations> labelAnnotations;
    List<LogoAnnotations> logoAnnotations;

    public List<LabelAnnotations> getLabelAnnotations() {
        return labelAnnotations;
    }

    public void setLabelAnnotations(List<LabelAnnotations> labelAnnotations) {
        this.labelAnnotations = labelAnnotations;
    }

    public List<LogoAnnotations> getLogoAnnotations() {
        return logoAnnotations;
    }

    public void setLogoAnnotations(List<LogoAnnotations> logoAnnotations) {
        this.logoAnnotations = logoAnnotations;
    }
}
