package com.example.andresvelasquez.seccion_9_fragments.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.andresvelasquez.seccion_9_fragments.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {

    TextView details;
    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_details,container,false);
        details = (TextView) v.findViewById(R.id.txtDetails);
        return v;
    }
    public void setText(String text){
        details.setText(text);
    }
    @Override
    public void onDetach() {
        super.onDetach();
    }
}
