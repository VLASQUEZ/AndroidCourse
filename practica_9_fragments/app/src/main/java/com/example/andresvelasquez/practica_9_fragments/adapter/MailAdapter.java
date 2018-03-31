package com.example.andresvelasquez.practica_9_fragments.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.andresvelasquez.practica_9_fragments.R;
import com.example.andresvelasquez.practica_9_fragments.model.Mail;

import java.util.List;

public class MailAdapter extends RecyclerView.Adapter<MailAdapter.ViewHolder> {
    private onMailClickListener listener;
    private List<Mail>mail;
    private int layout;
    private Context context;

    public MailAdapter(List<Mail> mail, int layout,onMailClickListener listener) {
        this.listener = listener;
        this.mail = mail;
        this.layout = layout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        context = parent.getContext();
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mail.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return this.mail.size();
    }

    /**
     * Class ViewHolder
     */
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtSubject;
        public TextView txtMessage;
        public TextView txtSender;
        private int SUBJECT_MAX_LENGHT = 40;
        private int MESSAGE_MAX_LENGHT = 80;
        public ViewHolder(View v){
            super(v);
            this.txtMessage = (TextView) v.findViewById(R.id.txtViewMessage);
            this.txtSubject = (TextView) v.findViewById(R.id.txtViewSubject);
            this.txtSender = (TextView) v.findViewById(R.id.txtsenderLetter);
        }

        public void bind(final Mail mail, final onMailClickListener listener){

            String shortSubject;
            if (mail.getSubject().length() > SUBJECT_MAX_LENGHT) {
                shortSubject = mail.getSubject().substring(0, SUBJECT_MAX_LENGHT) + "...";
            } else {
                shortSubject = mail.getSubject();
            }

            this.txtSubject.setText(shortSubject);

            String shortMessage;
            if (mail.getMessage().length() > MESSAGE_MAX_LENGHT) {
                shortMessage = mail.getMessage().substring(0, MESSAGE_MAX_LENGHT) + "...";
            } else {
                shortMessage = mail.getMessage();
            }
            this.txtMessage.setText(shortMessage);
            // Cogemos sólo la primera letra del correo
            this.txtSender.setText(mail.getSenderName().substring(0, 1));
            // Obtenemos el backround que es un drawable, y le cambiamos el color con el random color
            this.txtSender.getBackground().setColorFilter(Color.parseColor("#" + mail.getColor()), PorterDuff.Mode.SRC);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onMailClick(mail,getAdapterPosition());
                }
            });
        }
    }

    /**
     * onMailClickListener
     */
    public interface onMailClickListener{
        void onMailClick(Mail mail, int position);
    }
}
