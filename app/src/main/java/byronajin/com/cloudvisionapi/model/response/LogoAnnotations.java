package byronajin.com.cloudvisionapi.model.response;

/**
 * Created by Byron on 11/2/2017.
 */

public class LogoAnnotations {
    String mid;
    String description;
    Double score;

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
