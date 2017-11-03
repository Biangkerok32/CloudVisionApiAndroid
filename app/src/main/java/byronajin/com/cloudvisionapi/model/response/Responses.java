package byronajin.com.cloudvisionapi.model.response;

import java.util.List;

import byronajin.com.cloudvisionapi.model.response.LabelAnnotations;

/**
 * Created by Byron on 11/2/2017.
 */

public class Responses {
    List<LabelAnnotations> labelAnnotations;
    List<TextAnnotations> textAnnotations;


    public List<LabelAnnotations> getLabelAnnotations() {
        return labelAnnotations;
    }

    public void setLabelAnnotations(List<LabelAnnotations> labelAnnotations) {
        this.labelAnnotations = labelAnnotations;
    }

    public List<TextAnnotations> getTextAnnotations() {
        return textAnnotations;
    }

    public void setTextAnnotations(List<TextAnnotations> textAnnotations) {
        this.textAnnotations = textAnnotations;
    }
}
