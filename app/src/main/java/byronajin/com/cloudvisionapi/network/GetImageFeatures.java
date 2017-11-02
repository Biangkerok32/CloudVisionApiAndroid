package byronajin.com.cloudvisionapi.network;

import byronajin.com.cloudvisionapi.model.response.ImageFeatures;
import byronajin.com.cloudvisionapi.model.request.ImageFeaturesRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Byron on 11/1/2017.
 */

public interface GetImageFeatures {

    @POST("v1/images:annotate?")
    Call<ImageFeatures> getImageFeatures(@Query("key") String key, @Body ImageFeaturesRequest body);
}
