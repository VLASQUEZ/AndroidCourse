package com.example.andresvelasquez.seccion_7_realm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.andresvelasquez.seccion_7_realm.R;
import com.example.andresvelasquez.seccion_7_realm.models.Note;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by AndresVelasquez on 21/02/18.
 */

public class NoteAdapter extends BaseAdapter {
    private Context context;
    private List<Note> list;
    private int layout;

    public NoteAdapter(Context context, List<Note> list, int layout) {
        this.context = context;
        this.list = list;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Note getItem(int position) {
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
            vh.txtNoteDesc = (TextView) convertView.findViewById(R.id.txtNoteDesc);
            vh.txtCreateDate = (TextView) convertView.findViewById(R.id.txtNoteCreateAt);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }

        Note note = list.get(position);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String createdAt = df.format(note.getCreatedAt());
        vh.txtCreateDate.setText(createdAt);
        vh.txtNoteDesc.setText(note.getDescription());

        return convertView;
    }

    public class ViewHolder{
        TextView txtNoteDesc;
        TextView txtCreateDate;
    }
}
