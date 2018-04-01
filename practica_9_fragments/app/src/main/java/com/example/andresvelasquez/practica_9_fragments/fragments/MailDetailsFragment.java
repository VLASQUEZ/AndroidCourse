package com.example.andresvelasquez.practica_9_fragments.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.andresvelasquez.practica_9_fragments.R;
import com.example.andresvelasquez.practica_9_fragments.model.Mail;

/**
 * A simple {@link Fragment} subclass.
 */
public class MailDetailsFragment extends Fragment {
    TextView txtSubject;
    TextView txtFrom;
    TextView txtMessage;
    LinearLayout llMainContent;

    public MailDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_mail_details, container, false);
        txtSubject = (TextView) v.findViewById(R.id.txtSubject);
        txtFrom = (TextView) v.findViewById(R.id.txtFrom);
        txtMessage = (TextView) v.findViewById(R.id.txtMessage);
        llMainContent = (LinearLayout) v.findViewById(R.id.detailsLayout);

        return v;
    }

    public void setMail(Mail mail){
        llMainContent.setVisibility(View.VISIBLE);
        txtSubject.setText(mail.getSubject());
        txtFrom.setText(mail.getSenderName());
        txtMessage.setText(mail.getMessage());
    }

}
