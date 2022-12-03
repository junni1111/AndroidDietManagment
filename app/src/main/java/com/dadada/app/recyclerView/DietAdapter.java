package com.dadada.app.recyclerView;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dadada.app.R;
import com.dadada.app.model.DietLog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DietAdapter extends RecyclerView.Adapter<DietAdapter.DietHolder> {
    private Context context;
    private List<DietLog> diets;
    private LayoutInflater layoutInflater;

    @Override
    public void onBindViewHolder(@NonNull DietHolder holder, int position) {
        DietLog diet = diets.get(position);
        holder.setDetail(diet);
    }

    @Override
    public int getItemCount() {
        return diets.size();
    }

    @NonNull
    @Override
    public DietHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.diet_layout_item
                , parent, false);
        return new DietHolder(view);


    }

    public DietAdapter(Context context) {
        diets = new ArrayList<>();
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<DietLog> newData) {
        if (diets != null) {
            /*PostDiffCallback postDiffCallback = new PostDiffCallback(data, newData);
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(postDiffCallback);*/

            diets.clear();
            diets.addAll(newData);
            notifyDataSetChanged();
            //diffResult.dispatchUpdatesTo(this);
        } else {
            // first initialization
            diets = newData;
        }
    }

    class DietHolder extends RecyclerView.ViewHolder {
        private ImageView foodImg;
        private TextView foodNameText, foodCountText, foodCalorieText, addressText, dateText;

        public DietHolder(@NonNull View itemView) {
            super(itemView);
            foodNameText = itemView.findViewById(R.id.TVfoodName);
            foodCountText = itemView.findViewById(R.id.TVfoodCount);
            foodCalorieText = itemView.findViewById(R.id.TVfoodCalorie);
            addressText = itemView.findViewById(R.id.TVaddress);
            dateText = itemView.findViewById(R.id.TVdate);
            foodImg = itemView.findViewById(R.id.IVfood);
        }

        void setDetail(DietLog diet) {
            foodNameText.setText(diet.getFoodName());
            foodCountText.setText(String.format("%d개", diet.getFoodCount()));
            foodCalorieText.setText(String.format("%dkcal", diet.getFoodCalorie()));
            addressText.setText(diet.getAddress());
            dateText.setText(diet.getDay());

            try {
                File file = new File(diet.getImagePath());
                Glide.with(context).load(Uri.fromFile(file)).into(foodImg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
