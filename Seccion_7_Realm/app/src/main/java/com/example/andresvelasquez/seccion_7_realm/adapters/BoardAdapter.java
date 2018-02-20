package com.example.andresvelasquez.seccion_7_realm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.andresvelasquez.seccion_7_realm.R;
import com.example.andresvelasquez.seccion_7_realm.models.Board;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by andresvelasquez on 19/02/18.
 */

public class BoardAdapter extends BaseAdapter {
    private Context context;
    private List<Board> list;
    private int layout;

    public BoardAdapter(Context context, List<Board> list, int layout) {
        this.context = context;
        this.list = list;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(layout,null);
            vh = new ViewHolder();
            vh.txtTitle = (TextView) convertView.findViewById(R.id.txtBoardTitle);
            vh.txtBoardNotes = (TextView) convertView.findViewById(R.id.txtBoardNotes);
            vh.txtCreateDate = (TextView) convertView.findViewById(R.id.txtBoardCreatedDate);
            convertView.setTag(vh);
        }
        else{
            vh = (ViewHolder) convertView.getTag();
        }
        Board board = list.get(position);
        vh.txtTitle.setText(board.getTitle());

        int numbNotes = board.getNotes().size();
        String textForNotes = (numbNotes == 1) ? numbNotes + " Note" : numbNotes + " Notes";
        vh.txtBoardNotes.setText(textForNotes);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String createdAt = df.format(board.getCreatedAt());
        vh.txtCreateDate.setText(createdAt);
        return convertView;
    }

    public class ViewHolder{
        TextView txtTitle;
        TextView txtBoardNotes;
        TextView txtCreateDate;
    }
}
