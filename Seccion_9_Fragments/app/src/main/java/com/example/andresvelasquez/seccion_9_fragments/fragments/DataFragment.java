package com.example.andresvelasquez.seccion_9_fragments.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.andresvelasquez.seccion_9_fragments.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DataFragment extends Fragment {
    Button btnSend;
    EditText etName;
    DataListener listener;

    public DataFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            listener =(DataListener) context;
            throw new ClassCastException(context.toString() + "Should implements DataListener");
        }
        catch (Exception e){

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_data,container,false);
        btnSend = (Button) v.findViewById(R.id.btnSend);
        etName = (EditText) v.findViewById(R.id.EtName);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendText(etName.getText().toString());
            }
        });
        return v;
    }

    private void sendText(String text){
        listener.sendData(text);
    }

    /**
     * Interface para comunicacion entre Fragment y Activity
     */
    public interface DataListener{
        void sendData(String text);
    }
}
