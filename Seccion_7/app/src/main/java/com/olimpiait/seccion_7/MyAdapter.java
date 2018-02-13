package com.olimpiait.seccion_7;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by AndresVelasquez on 12/02/18.
 */

public class MyAdapter extends BaseAdapter {
    private List<Car> cars;
    private int layout;
    private Context mContext;

    public MyAdapter(List<Car> cars, int layout, Context mContext) {
        this.cars = cars;
        this.layout = layout;
        this.mContext = mContext;
    }

    @Override

    public int getCount() {
    return cars.size();    }

    @Override
    public Car getItem(int position) {
        return cars.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if(convertView ==null){
            convertView = LayoutInflater.from(mContext).inflate(layout,null);
            vh = new ViewHolder();
            vh.txtColor = (TextView) convertView.findViewById(R.id.txtColor);
            vh.txtName = (TextView) convertView.findViewById(R.id.txtName);
            convertView.setTag(vh);
        }
        else{
            vh = (ViewHolder) convertView.getTag();
        }
        String name = cars.get(position).getName();
        String color = cars.get(position).getColor();
        vh.txtName.setText(name);
        vh.txtColor.setText(color);
        return convertView;
    }
    public class ViewHolder{
        public TextView txtName;
        public TextView txtColor;
    }
}
