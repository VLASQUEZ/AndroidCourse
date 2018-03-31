package com.example.andresvelasquez.practica_9_fragments.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.andresvelasquez.practica_9_fragments.R;
import com.example.andresvelasquez.practica_9_fragments.adapter.MailAdapter;
import com.example.andresvelasquez.practica_9_fragments.model.Mail;
import com.example.andresvelasquez.practica_9_fragments.util.Util;

import java.util.List;

import static android.support.v7.widget.LinearLayoutManager.HORIZONTAL;

/**
 * A simple {@link Fragment} subclass.
 */
public class MailListFragment extends Fragment {
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView recyclerView;
    List<Mail> mailList;


    public MailListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mailList = Util.getDummyData();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mail_list, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this.getContext());

        mAdapter = new MailAdapter(mailList, R.layout.mail_item, new MailAdapter.onMailClickListener() {
            @Override
            public void onMailClick(Mail mail, int position) {
                //TODO: Iniciar el fragment con los datos y enviar los datos
            }
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        return v;
    }

}
