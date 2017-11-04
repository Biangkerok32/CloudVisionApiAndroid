package byronajin.com.cloudvisionapi.service;

import byronajin.com.cloudvisionapi.model.request.ImageFeaturesRequest;
import byronajin.com.cloudvisionapi.model.response.ImageFeatures;
import byronajin.com.cloudvisionapi.network.ImageFeaturesAPI;
import byronajin.com.cloudvisionapi.network.RetrofitInstance;
import retrofit2.Call;

/**
 * Created by Byron on 11/4/2017.
 */

public class ImageFeatureService {

    private ImageFeaturesAPI imageFeaturesAPI;

    public ImageFeatureService() {
        imageFeaturesAPI = RetrofitInstance.getRetrofitInstance().create(ImageFeaturesAPI.class);
    }

    public Call<ImageFeatures> getImageFeatures(String apiKey, ImageFeaturesRequest body){
        return imageFeaturesAPI.getImageFeatures(apiKey, body);
    }

}
