package com.olimpiait.seccion_6;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by AndresVelasquez on 8/02/18.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<String> names;
    private int layout;
    private OnItemClickListener listener;

    public MyAdapter(List<String> names,int layout, OnItemClickListener listener){
        this.names = names;
        this.layout = layout;
        this.listener = listener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
     View v = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
     ViewHolder vh = new ViewHolder(v);
     return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(names.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return this.names.size();
    }

    /**
     * ViewHolder
     * */

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewName;

        public ViewHolder(View v){
            super(v);
            this.textViewName =(TextView) v.findViewById(R.id.textViewName);
        }

        public void bind(final String name, final OnItemClickListener listener){
            this.textViewName.setText(name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemClick(name,getAdapterPosition());
                }
            });
        }
    }

    /**
     * OnClickListener
     * */

    public interface OnItemClickListener{
        void OnItemClick(String name,int position);
    }
}
