package byronajin.com.cloudvisionapi.model.request;

/**
 * Created by Byron on 11/1/2017.
 */

public class ImageRequest {
    String content;
    SourceRequest source;

    public ImageRequest(String content, SourceRequest source) {
        this.content = content;
        this.source = source;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
