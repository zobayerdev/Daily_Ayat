package com.trodev.dailyayah;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.trodev.dailyayah.DetailsNotiActivity;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewAdapter> {

    private List<NotificationData> list;
    private Context context;
    private String category;

    public NotificationAdapter(List<NotificationData> list, Context context, String category) {
        this.list = list;
        this.context = context;
        this.category = category;
    }

    @NonNull
    @Override
    public NotificationViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.notification_list_item, parent, false);

        return new NotificationViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewAdapter holder, int position) {

        NotificationData item = list.get(position);

        holder.headline.setText(item.getTitle());
        holder.date.setText(item.getDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, DetailsNotiActivity.class);
                intent.putExtra("title", item.getTitle());
                intent.putExtra("body", item.getBody());
                intent.putExtra("date", item.getDate());
                context.startActivity(intent);

            }
        });

        /*animation view with slider*/
        holder.cardView.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.slider));


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NotificationViewAdapter extends RecyclerView.ViewHolder {

        private TextView headline, date;
        private MaterialCardView cardView;

        public NotificationViewAdapter(@NonNull View itemView) {
            super(itemView);

            headline = itemView.findViewById(R.id.headTv);
            date = itemView.findViewById(R.id.dateTv);

            cardView = itemView.findViewById(R.id.cardView);

        }
    }
}
