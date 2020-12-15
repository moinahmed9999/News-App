package com.moin.newsapp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.moin.newsapp.R;
import com.moin.newsapp.model.Response;

public class HeadlinesPagedListAdapter extends PagedListAdapter<Response.Article, HeadlinesPagedListAdapter.HeadlinesViewHolder> {
    private static final DiffUtil.ItemCallback<Response.Article> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Response.Article>() {
                @Override
                public boolean areItemsTheSame(@NonNull Response.Article oldItem, @NonNull Response.Article newItem) {
                    return oldItem.getUrl().equals(newItem.getUrl());
                }

                @Override
                public boolean areContentsTheSame(@NonNull Response.Article oldItem, @NonNull Response.Article newItem) {
                    return oldItem.equals(newItem);
                }
            };
    private final Context mContext;
    private OnHeadlineClickListener onHeadlineClickListener;

    public interface OnHeadlineClickListener {
        void onHeadlineClick(int position);
    }

    public void setOnHeadlineClickListener(OnHeadlineClickListener onHeadlineClickListener) {
        this.onHeadlineClickListener = onHeadlineClickListener;
    }

    public HeadlinesPagedListAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.mContext = context;
    }

    @NonNull
    @Override
    public HeadlinesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.headline, parent, false);
        return new HeadlinesViewHolder(view, onHeadlineClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull HeadlinesViewHolder holder, int position) {
        Response.Article article = getItem(position);

        if (article != null) {
            holder.title.setText(article.getTitle());
            holder.source.setText(article.getSource().getName());

            if (article.getUrlToImage() != null) {
                Glide.with(mContext)
                        .load(article.getUrlToImage())
                        .centerCrop()
                        .placeholder(R.drawable.ic_news)
                        .into(holder.image);
            } else {
                Glide.with(mContext)
                        .load(R.drawable.ic_news)
                        .centerCrop()
                        .into(holder.image);
            }
        } else {
            Toast.makeText(mContext, "Article is null", Toast.LENGTH_SHORT).show();
        }
    }

    public static class HeadlinesViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView source;
        ImageView image;

        public HeadlinesViewHolder(@NonNull View itemView, final OnHeadlineClickListener onHeadlineClickListener) {
            super(itemView);

            title = itemView.findViewById(R.id.tv_headline_title);
            source = itemView.findViewById(R.id.tv_headline_source);
            image = itemView.findViewById(R.id.iv_headline_image);

            itemView.setOnClickListener(v -> {
                if (onHeadlineClickListener != null) {
                    int position = getAdapterPosition();
                    if (position!=RecyclerView.NO_POSITION) {
                        onHeadlineClickListener.onHeadlineClick(position);
                    }
                }
            });
        }
    }
}
