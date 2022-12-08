package com.dadada.app.recyclerView;

import android.annotation.SuppressLint;
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_main
                , parent, false);
        return new DietHolder(view);


    }

    public DietAdapter(Context context) {
        diets = new ArrayList<>();
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @SuppressLint("NotifyDataSetChanged")
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

        private ImageView itemImg, heartBtn;
        private TextView dietTitle, dietDateTime;

        public DietHolder(@NonNull View itemView) {
            super(itemView);
            itemImg = itemView.findViewById(R.id.itemImg);
            heartBtn = itemView.findViewById(R.id.heartBtn);
            dietTitle = itemView.findViewById(R.id.dietTitle);
            dietDateTime = itemView.findViewById(R.id.dietDateTime);

        }

        void setDetail(DietLog diet) {
            String date = diet.getDay() + " ";
            int hour = Integer.parseInt(diet.getTime().split(":")[0]);
            int minute = Integer.parseInt(diet.getTime().split(":")[1]);
            if (hour < 12) {
                if (hour == 0) hour += 12;
                date += "오전 " + "" + hour + ":" + minute;
            } else {
                if (hour != 12) hour -= 12;
                date += "오후 " + "" + hour + ":" + minute;
            }

            dietTitle.setText(diet.getFoodName());
            dietDateTime.setText(date);

            try {
                File file = new File(diet.getImagePath());
                Glide.with(context).load(Uri.fromFile(file)).into(itemImg);
            } catch (Exception e) {
                e.printStackTrace();
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }
    }
}
