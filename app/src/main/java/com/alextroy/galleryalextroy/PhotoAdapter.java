package com.alextroy.galleryalextroy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.alextroy.galleryalextroy.Model.PhotoItem;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.NewsViewHolder> {

    private List<PhotoItem> list;
    private Context context;

    public PhotoAdapter(List<PhotoItem> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_photo, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        final PhotoItem result = list.get(position);
        Glide.with(context)
                .load(result.getPhotoPageUri())
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.DATA))
                .into(holder.imageView);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoDetail.startPhotoDeatil(context, result.getPhotoPageUri());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private CardView cardView;

        public NewsViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.photo);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }

    public void setData(List<PhotoItem> list) {
        this.list.addAll(list);
        if (this.list.isEmpty()) {
            Toast.makeText(context, "No news", Toast.LENGTH_SHORT).show();
        }
        notifyDataSetChanged();
    }
}
