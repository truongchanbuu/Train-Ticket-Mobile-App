package com.example.trainticketproject.utils;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationPublisher extends BroadcastReceiver {
    private static final int REQUEST_PERMISSION = 1;
    public static String NOTIFICATION_ID = "notification_id";
    public static String NOTIFICATION_TITLE = "notification_title";
    public static String NOTIFICATION_CONTENT = "notification_content";

    @Override
    public void onReceive(Context context, Intent intent) {
        int notificationId = intent.getIntExtra(NOTIFICATION_ID, 0);
        String title = intent.getStringExtra(NOTIFICATION_TITLE);
        String content = intent.getStringExtra(NOTIFICATION_CONTENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "channel_id")
//                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(title)
                .setContentText(content)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[] { Manifest.permission.INTERNET }, REQUEST_PERMISSION);
            return;
        }
        notificationManager.notify(notificationId, builder.build());
    }
}
