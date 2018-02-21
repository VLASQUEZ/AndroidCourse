package com.example.andresvelasquez.practica_seccion_6.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andresvelasquez.practica_seccion_6.Model.Fruit;
import com.example.andresvelasquez.practica_seccion_6.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by andresvelasquez on 11/02/18.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context mContext;
    private int layout;
    private OnItemClickListener listener;
    private List<Fruit> fruits;

    public MyAdapter(Context mContext, int layout, OnItemClickListener listener, List<Fruit> fruits) {
        this.mContext = mContext;
        this.layout = layout;
        this.listener = listener;
        this.fruits = fruits;
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
        holder.bind(fruits.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return this.fruits.size();
    }


    /**
     * ViewHolder
     * */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        public ImageView imageViewFruit;
        public TextView textViewFruitName;
        public TextView textViewFruitDescription;
        public TextView textViewFruitQuantity;
        public ContextMenu contextMenu;
        public ViewHolder(View v){
            super(v);
            this.imageViewFruit = (ImageView) v.findViewById(R.id.imageViewFruit);
            this.textViewFruitName = (TextView) v.findViewById(R.id.textViewFruitName);
            this.textViewFruitQuantity = (TextView) v.findViewById(R.id.textViewFruitQuantity);
            this.textViewFruitDescription = (TextView) v.findViewById(R.id.textViewFruitDescription);

            itemView.setOnCreateContextMenuListener(this);

        }

        public void bind(final Fruit fruit, final OnItemClickListener listener){
            this.textViewFruitDescription.setText(fruit.getDescription());
            this.textViewFruitName.setText(fruit.getName());
            this.textViewFruitQuantity.setText(String.valueOf(fruit.getQuantity()));

            if(fruit.getQuantity() == Fruit.LIMIT_QUANTITY){
                textViewFruitQuantity.setTextColor(ContextCompat.getColor(mContext,R.color.colorAlert));
                textViewFruitQuantity.setTypeface(null, Typeface.BOLD);
            }
            else{
                textViewFruitQuantity.setTextColor(Color.WHITE);
                textViewFruitQuantity.setTypeface(null,Typeface.NORMAL);
            }
            Picasso.with(mContext).load(fruit.getImage()).fit().into(imageViewFruit);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemClick(fruit,getAdapterPosition());
                }
            });
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            Fruit fruitSelected = fruits.get(this.getAdapterPosition());

            contextMenu.setHeaderTitle(fruitSelected.getName());
            contextMenu.setHeaderIcon(fruitSelected.getImage());

            MenuInflater infa
        }
    }
    /**
     * OnClickListener
     * */
    public interface OnItemClickListener {
        void OnItemClick(Fruit fruit,int position);
    }
}

