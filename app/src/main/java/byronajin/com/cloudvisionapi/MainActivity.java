package byronajin.com.cloudvisionapi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import byronajin.com.cloudvisionapi.adapter.LabelAnnotationsAdapter;
import byronajin.com.cloudvisionapi.model.request.FeatureRequest;
import byronajin.com.cloudvisionapi.model.response.ImageFeatures;
import byronajin.com.cloudvisionapi.model.request.ImageFeaturesRequest;
import byronajin.com.cloudvisionapi.model.request.ImageRequest;
import byronajin.com.cloudvisionapi.model.request.Request;
import byronajin.com.cloudvisionapi.model.request.SourceRequest;
import byronajin.com.cloudvisionapi.model.response.LabelAnnotations;
import byronajin.com.cloudvisionapi.model.response.Responses;
import byronajin.com.cloudvisionapi.model.response.TextAnnotations;
import byronajin.com.cloudvisionapi.network.GetImageFeatures;
import byronajin.com.cloudvisionapi.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public final static int RESULT_LOAD_IMG = 1;
    private GetImageFeatures service;

    private RecyclerView recyclerView;
    private LabelAnnotationsAdapter labelAnnotationsAdapter;

    TextView textViewText;
    TextView textViewLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        labelAnnotationsAdapter = new LabelAnnotationsAdapter();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(labelAnnotationsAdapter);

        textViewText = (TextView) findViewById(R.id.textViewText);
        textViewLanguage = (TextView) findViewById(R.id.textViewLanguage);

        /*Create handle for the RetrofitInstance interface*/
        service = RetrofitInstance.getRetrofitInstance().create(GetImageFeatures.class);

    }

    public void getImageFromGalery(View view) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                ((ImageView)findViewById(R.id.previewImage)).setImageBitmap(selectedImage);

                String base64Bitmap = bitmapToBase64(selectedImage);
                doNetworkCall(base64Bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(getApplicationContext(), "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }

    private String bitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteStream);
        String base64Bitmap = Base64.encodeToString(byteStream.toByteArray(), Base64.URL_SAFE);

        return base64Bitmap;
    }

    private void doNetworkCall(String imageData) {

        String url = getResources().getString(R.string.cloudVisionApiKey);
        ImageFeaturesRequest body = getBodyObject(imageData);

        /*Call the method with parameter in the interface to get the employee data*/
        Call<ImageFeatures> call = service.getImageFeatures(url, body);

        /*Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<ImageFeatures>() {
            @Override
            public void onResponse(Call<ImageFeatures> call, Response<ImageFeatures> response) {
                ImageFeatures imageFeatures = response.body();
                Responses responses = imageFeatures.getResponses().get(0);
                updateDataOnAdapter(responses.getLabelAnnotations());

                if(responses.getTextAnnotations() != null)
                updateLabelText(responses.getTextAnnotations());
            }

            @Override
            public void onFailure(Call<ImageFeatures> call, Throwable t) {
                 Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateDataOnAdapter(List<LabelAnnotations> labelAnnotations){
        for(LabelAnnotations annotation: labelAnnotations) {
            labelAnnotationsAdapter.addAnnotation(annotation);
        }
        labelAnnotationsAdapter.notifyDataSetChanged();
    }

    private void updateLabelText(List<TextAnnotations> textAnnotationsList){
        TextAnnotations textAnnotations = textAnnotationsList.get(0);

        textViewText.setText(textAnnotations.getDescription());
        textViewLanguage.setText(textAnnotations.getLocale());
    }

    private ImageFeaturesRequest getBodyObject(String bodyValue) {

        ImageRequest imageRequest = new ImageRequest(bodyValue, null);

        ArrayList<FeatureRequest> featureRequests = new ArrayList<FeatureRequest>();
        featureRequests.add(new FeatureRequest("LABEL_DETECTION"));
        featureRequests.add(new FeatureRequest("TEXT_DETECTION"));
        featureRequests.add(new FeatureRequest("FACE_DETECTION"));

        ArrayList<Request> requests = new ArrayList<Request>();
        requests.add(new Request(imageRequest, featureRequests));

        return new ImageFeaturesRequest(requests);
    }
}
