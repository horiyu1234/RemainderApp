package com.example.myapplication;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.*;

public class AlarmReceiver extends BroadcastReceiver {
    public static String NOTIFICATION_ID = "notificationId";
    public static String NOTIFICATION_CONTENT = "content";

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent i = new Intent(context, CallFragment.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);

        System.out.println("スケジュールテスト4");
        // Notification　Channel 設定
        //String channelId = "default";
        String title = context.getString(R.string.app_name);
        String message = "10秒後に届く通知です";

        NotificationChannel channel = new NotificationChannel(
                NOTIFICATION_ID, title , NotificationManager.IMPORTANCE_DEFAULT);
        channel.setDescription(message);
        channel.enableVibration(true);
        channel.canShowBadge();
        channel.enableLights(true);
        channel.setLightColor(Color.BLUE);
        // the channel appears on the lockscreen
        /*
        channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        channel.setSound(defaultSoundUri, null);
        channel.setShowBadge(true);
        */

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);


        int id = intent.getIntExtra(NOTIFICATION_ID, 0);
        String content = intent.getStringExtra(NOTIFICATION_CONTENT);

        try {
            notificationManager.notify(id, buildNotification(context, NOTIFICATION_ID));
            System.out.println("スケジュールテスト5");
        }
        catch (Exception e){
            System.out.println("Error is" + e);
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private Notification buildNotification(Context context, String content) {
        System.out.println("スケジュールテスト");
        Notification.Builder builder = new Notification.Builder(context,NOTIFICATION_ID);
        builder.setContentTitle("Notification!!")
                .setContentText(content)
                .setSmallIcon(android.R.drawable.ic_lock_idle_alarm);
        return builder.build();
    }
}
