package com.trodev.dailyayat.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.trodev.dailyayat.activities.DetailsAyatActivity;
import com.trodev.dailyayat.R;
import com.trodev.dailyayat.models.UploadAllAyats;

import java.util.List;

public class AyatAdapter extends RecyclerView.Adapter<AyatAdapter.AyatViewAdapter> {

    private List<UploadAllAyats> list;
    private Context context;
    private String category;

    public AyatAdapter(List<UploadAllAyats> list, Context context, String category) {
        this.list = list;
        this.context = context;
        this.category = category;
    }

    @NonNull
    @Override
    public AyatViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.ayat_list_item, parent, false);

        return new AyatViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AyatViewAdapter holder, int position) {

        UploadAllAyats item = list.get(position);

        holder.headTv.setText(item.getHeadline());
        holder.authorTv.setText(item.getAuthor());
        holder.dateTv.setText(item.getDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsAyatActivity.class);
                intent.putExtra("headline", item.getHeadline());
                intent.putExtra("ayat", item.getAyat());
                intent.putExtra("author", item.getAuthor());
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

    public class AyatViewAdapter extends RecyclerView.ViewHolder {

        private TextView headTv, authorTv, dateTv;
        private CardView cardView;

        public AyatViewAdapter(@NonNull View itemView) {
            super(itemView);

            headTv = itemView.findViewById(R.id.headTv);
            authorTv = itemView.findViewById(R.id.authorTv);
            dateTv = itemView.findViewById(R.id.dateTv);

            cardView = itemView.findViewById(R.id.cardView);

        }
    }
}
