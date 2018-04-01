package com.example.andresvelasquez.practica_9_fragments.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.andresvelasquez.practica_9_fragments.R;
import com.example.andresvelasquez.practica_9_fragments.fragments.MailDetailsFragment;
import com.example.andresvelasquez.practica_9_fragments.model.Mail;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Mail mail = (Mail) getIntent().getExtras().getSerializable("mail");
        if(mail != null){
            MailDetailsFragment fragment = (MailDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.mailDetailsFragment);
            fragment.setMail(mail);
        }
    }
}
