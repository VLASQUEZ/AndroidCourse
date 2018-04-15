package com.example.andresvelasquez.seccion_19_notificaciones;

import android.app.Notification;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.example.andresvelasquez.seccion_19_notificaciones.notifications.NotificationHandler;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.et_title)
    EditText et_title;

    @BindView(R.id.et_message)
    EditText et_message;

    @BindView(R.id.sw_importance)
    Switch sw_importance;

    @BindString(R.string.switch_notification_on) String notificationOn;

    @BindString(R.string.switch_notification_off) String notificationOff;

    private boolean isChecked;
    private NotificationHandler notificationHandler;

    private int counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Binding with butterknife
        ButterKnife.bind(this);
        notificationHandler = new NotificationHandler(this);
    }

    @OnClick(R.id.btn_send)
    public void onClick(Button btn_send){
        sendNotification();
    }

    private void sendNotification(){
        String title = et_title.getText().toString();
        String message = et_message.getText().toString();

        if(!TextUtils.isEmpty(title)){
            Notification.Builder nb = notificationHandler.createNotification(title,message,isChecked);
            notificationHandler.getManager().notify(++counter,nb.build());
            notificationHandler.publishNotifiactionSummaryGroup(true);
        }
    }

    @OnCheckedChanged(R.id.sw_importance)
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked){
        this.isChecked = isChecked;
        sw_importance.setText((isChecked) ? notificationOn : notificationOff);
    }
}
