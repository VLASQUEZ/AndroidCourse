package com.example.andresvelasquez.practica_9_fragments.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.andresvelasquez.practica_9_fragments.R;
import com.example.andresvelasquez.practica_9_fragments.fragments.MailDetailsFragment;
import com.example.andresvelasquez.practica_9_fragments.fragments.MailListFragment;
import com.example.andresvelasquez.practica_9_fragments.model.Mail;

public class MainActivity extends AppCompatActivity implements MailListFragment.MailListener {
    private boolean isMultiPanel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setMultiPanel();
    }

    @Override
    public void sendMail(Mail mail) {
        if(isMultiPanel){
            MailDetailsFragment fragment = (MailDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.mailDetailsFragment);
            fragment.setMail(mail);
        }else{
            Intent i = new Intent(this,DetailsActivity.class);
            i.putExtra("mail",mail);
            startActivity(i);
        }
    }

    private void setMultiPanel(){
        this.isMultiPanel = (getSupportFragmentManager().findFragmentById(R.id.mailDetailsFragment) != null);
    }
}
