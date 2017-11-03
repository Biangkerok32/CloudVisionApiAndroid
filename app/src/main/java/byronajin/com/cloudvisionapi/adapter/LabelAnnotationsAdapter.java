package byronajin.com.cloudvisionapi.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import byronajin.com.cloudvisionapi.R;
import byronajin.com.cloudvisionapi.model.response.LabelAnnotations;

/**
 * Created by Byron on 11/2/2017.
 */

public class LabelAnnotationsAdapter extends RecyclerView.Adapter<LabelAnnotationsAdapter.LabelAnnotationsViewHolder> {

    private ArrayList<LabelAnnotations> labelAnnotations;

    public LabelAnnotationsAdapter() {
        labelAnnotations = new ArrayList<>();
    }

    @Override
    public LabelAnnotationsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.label_annotations_row, parent, false);

        return new LabelAnnotationsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(LabelAnnotationsViewHolder holder, int position) {
        LabelAnnotations annotation = labelAnnotations.get(position);

        Double toPorcentage = (annotation.getScore()*100)/1;
        String porcentageString = toPorcentage.intValue() + "%";

        holder.score.setText(porcentageString);
        holder.description.setText(annotation.getDescription());
    }

    @Override
    public int getItemCount() {
        return labelAnnotations.size();
    }

    public class LabelAnnotationsViewHolder extends RecyclerView.ViewHolder {
        public TextView score, description;

        public LabelAnnotationsViewHolder(View view) {
            super(view);
            score = (TextView) view.findViewById(R.id.score);
            description = (TextView) view.findViewById(R.id.description);
        }
    }

    public void addAnnotation(LabelAnnotations labelAnnotation) {
        labelAnnotations.add(labelAnnotation);
    }
}
