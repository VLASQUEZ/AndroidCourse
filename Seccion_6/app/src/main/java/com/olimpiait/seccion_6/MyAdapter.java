package com.olimpiait.seccion_6;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by AndresVelasquez on 8/02/18.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Movie> movies;
    private int layout;
    private OnItemClickListener listener;
    private Context mContext;
    public MyAdapter(List<Movie> movies,int layout, OnItemClickListener listener){
        this.movies = movies;
        this.layout = layout;
        this.listener = listener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
     View v = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
     mContext = parent.getContext();
     ViewHolder vh = new ViewHolder(v);
     return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(movies.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return this.movies.size();
    }

    /**
     * ViewHolder
     * */

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewName;
        public ImageView    imageViewPoster;
        public ViewHolder(View v){
            super(v);
            this.textViewName = (TextView) v.findViewById(R.id.textViewTitle);
            this.imageViewPoster = (ImageView) v.findViewById(R.id.imageViewMovie);
        }

        public void bind(final Movie movie, final OnItemClickListener listener){
            //this.textViewName.setText(name);
            this.textViewName.setText(movie.getName());
            Picasso.with(mContext).load(movie.getPoster()).fit().into(imageViewPoster);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemClick(movie,getAdapterPosition());
                }
            });
        }
    }

    /**
     * OnClickListener
     * */

    public interface OnItemClickListener{
        void OnItemClick(Movie movie,int position);
    }
}
