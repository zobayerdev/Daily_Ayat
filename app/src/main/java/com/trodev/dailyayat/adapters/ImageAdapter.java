package com.trodev.dailyayat.adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;
import com.trodev.dailyayat.activities.FullDataActivity;
import com.trodev.dailyayat.R;
import com.trodev.dailyayat.models.UploadData;


import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private Context context;
    private List<UploadData> mUpload;

    public ImageAdapter(Context context, List<UploadData> mUpload) {
        this.context = context;
        this.mUpload = mUpload;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.image_layout, parent, false);

        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {

        UploadData uploadData = mUpload.get(position);

        holder.headTv.setText(uploadData.getmHead());
        holder.authorTv.setText(uploadData.getmAuthor());
        holder.dateTv.setText(uploadData.getmDate());
        Picasso.get()
                .load(uploadData.getmImageUrl())
                .placeholder(R.drawable.apps_icon)
                .fit()
                .centerCrop()
                .into(holder.ayatIv);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FullDataActivity.class);
                intent.putExtra("name", uploadData.getmName());
                intent.putExtra("headline", uploadData.getmHead());
                intent.putExtra("author", uploadData.getmAuthor());
                intent.putExtra("date", uploadData.getmDate());
                intent.putExtra("image", uploadData.getmImageUrl());
                context.startActivity(intent);
            }
        });

        /*animation recyclerview*/
        holder.cardView.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.slider));

    }

    @Override
    public int getItemCount() {
        return mUpload.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {

        public TextView headTv, authorTv, dateTv;
        public ImageView ayatIv, arrowIv;
        private MaterialCardView cardView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);


            headTv = itemView.findViewById(R.id.headTv);
            dateTv = itemView.findViewById(R.id.dateTv);
            authorTv = itemView.findViewById(R.id.authorTv);
            cardView = itemView.findViewById(R.id.cardView);
            ayatIv = itemView.findViewById(R.id.ayatIV);
            arrowIv = itemView.findViewById(R.id.arrow);

        }
    }

}
