package com.example.andresvelasquez.practica_9_fragments.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.andresvelasquez.practica_9_fragments.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MailDetailsFragment extends Fragment {


    public MailDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mail_details, container, false);
    }

}
