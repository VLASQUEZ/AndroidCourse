package com.example.andresvelasquez.seccion_19_notificaciones.notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.os.Build;

import com.example.andresvelasquez.seccion_19_notificaciones.DetailsActivity;
import com.example.andresvelasquez.seccion_19_notificaciones.R;

import java.util.ArrayList;


public class NotificationHandler extends ContextWrapper {

    private NotificationManager manager;
    /**
     * Channel HIGH Importance
     */
    public static final String CHANNEL_HIGH_ID = "1";
    private final String CHANNEL_HIGH_NAME = "HIGH CHANNEL";
    /**
     * Channel LOW Importance
     */
    public static final String CHANNEL_LOW_ID = "2";
    private final String CHANNEL_LOW_NAME = "LOW CHANNEL";
    /**
     * Notification Grouping
     */
    private final int SUMMARY_GROUP_ID = 1001;
    private final String SUMMARY_GROUP_NAME = "NOTIFICATION_GROUP";
    public NotificationHandler(Context context) {
        super(context);
        createChannels();
    }

    public NotificationManager getManager() {
        if(manager == null){
            manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return manager;
    }

    private void createChannels(){
        if(Build.VERSION.SDK_INT >= 26){
            //Create High Channel
            NotificationChannel highChannel = new NotificationChannel(CHANNEL_HIGH_ID,CHANNEL_HIGH_NAME,NotificationManager.IMPORTANCE_HIGH);

            //Create Low Channel
            NotificationChannel lowChannel = new NotificationChannel(CHANNEL_LOW_ID,CHANNEL_LOW_NAME,NotificationManager.IMPORTANCE_LOW);

            ArrayList<NotificationChannel> channelList = new ArrayList<>();
            channelList.add(highChannel);
            channelList.add(lowChannel);


            //Extra configs
            highChannel.enableLights(true);
            highChannel.setLightColor(Color.GREEN);
            highChannel.setShowBadge(true);
            highChannel.enableVibration(true);
            highChannel.setVibrationPattern(new long[]{100,200,300,400,500,400,300,200,400});
            highChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);

            lowChannel.enableLights(true);
            lowChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            getManager().createNotificationChannels(channelList);
        }
    }

    public Notification.Builder createNotification(String title, String message, boolean isHighImportance){
        if(Build.VERSION.SDK_INT >= 26){
            if(isHighImportance){
                return this.createNotificationWithChannel(title,message,CHANNEL_HIGH_ID);
            }
            else{
                return this.createNotificationWithChannel(title,message,CHANNEL_LOW_ID);
            }
        }
        else{
            return createNotificationWithoutChannel(title,message);
        }
    }

    private Notification.Builder createNotificationWithChannel(String title, String message, String channelId){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            Intent i = new Intent(this, DetailsActivity.class);
            i.putExtra("title",title);
            i.putExtra("message",message);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            //Intent que se lanza con la notificacion, se queda pendiente del evento del usuario
            PendingIntent pendingIntent = PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_CANCEL_CURRENT);

            Notification.Action action = new Notification.Action.Builder(
                    Icon.createWithResource(this,android.R.drawable.ic_menu_send),
                        "See Details",
                        pendingIntent).build();



            return new Notification.Builder(getApplicationContext(),channelId)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setSmallIcon(android.R.drawable.stat_notify_chat)
                    //.setContentIntent(pendingIntent)
                    .setGroup(SUMMARY_GROUP_NAME)
                    .addAction(action)
                    .setAutoCancel(true);
        }
        return null;
    }

    private Notification.Builder createNotificationWithoutChannel(String title, String message){
        return new Notification.Builder(getApplicationContext())
                .setContentTitle(title)
                .setContentText(message)
                .setColor(getColor(R.color.colorPrimary))
                .setSmallIcon(android.R.drawable.stat_notify_chat)
                .setVibrate(new long[]{100,200,300,400,500,400,300,200,400})
                .setAutoCancel(true);
    }

    public void publishNotifiactionSummaryGroup(boolean isHighImportance){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            String channelId = (isHighImportance) ? CHANNEL_LOW_ID : CHANNEL_LOW_ID;
            Notification summaryNotifiaction = new Notification.Builder(getApplicationContext(),channelId)
                    .setSmallIcon(android.R.drawable.stat_notify_call_mute)
                    .setGroup(SUMMARY_GROUP_NAME)
                    .setGroupSummary(true)
                    .build();

            getManager().notify(SUMMARY_GROUP_ID,summaryNotifiaction);

        }
    }
}
