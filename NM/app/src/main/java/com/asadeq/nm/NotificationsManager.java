package com.asadeq.nm;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.format.DateUtils;
import android.widget.RemoteViews;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationCompatSideChannelService;
import androidx.core.app.NotificationManagerCompat;

import java.util.Map;

import static android.content.Context.NOTIFICATION_SERVICE;

public class NotificationsManager {

    private Activity mContext;
    public NotificationsManager(Activity context) {
        //super(context);
        mContext = context;
    }


    public void sendNotification(String messageTitle, String messageBody, Map<String, String> row) {

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext)
                // these are the three things a NotificationCompat.Builder object requires at a minimum
                .setSmallIcon(R.drawable.ic_small_icon)
                .setContentTitle(messageTitle)
                .setContentText(messageBody)
                // notification will be dismissed when tapped
                .setAutoCancel(true)
                // tapping notification will open MainActivity
                .setContentIntent(getPendingIntent(mContext, 0))
                // setting style to DecoratedCustomViewStyle() is necessary for custom views to display
                .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                // setting the custom collapsed and expanded views
                .setCustomContentView(getCustomContentView(mContext))
                .setCustomBigContentView(getCustomBigContentView(mContext, messageTitle))
                .setShowWhen(true)
                .setWhen(System.currentTimeMillis())
                .setPriority(NotificationManager.IMPORTANCE_DEFAULT);

        Notification notification = mBuilder.build();
        notify(notificationId, notification);
    }

    private RemoteViews getCustomBigContentView(Context mContext, String messageTitle) {
        RemoteViews expandedView = new RemoteViews(mContext.getPackageName(),
                R.layout.view_expanded_notification);
        expandedView.setTextViewText(R.id.timestamp, getDateTimeAsString(mContext));
        expandedView.setTextViewText(R.id.tv_content_title, messageTitle);
        return expandedView;
    }

    private RemoteViews getCustomContentView(Context mContext) {
        RemoteViews collapsedView = new RemoteViews(mContext.getPackageName(),
                R.layout.view_collapsed_notification);
        collapsedView.setTextViewText(R.id.timestamp, getDateTimeAsString(mContext));
        return collapsedView;
    }

    int notificationId = 0;
    public void sendNotification(String messageTitle, String messageBody) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification.Builder mBuilder = new Notification.Builder(mContext,"")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle(messageTitle)
                    .setContentText(messageBody)
                    .setShowWhen(true)
                    .setChannelId("APP_CHANNEL_ID");
                    //.setFullScreenIntent(getFullScreenIntent(0), false);
                    //.addAction(action);
            // Issue the notification.
            notifyCompat(notificationId, mBuilder.build());
        }
    }

    private PendingIntent getFullScreenIntent(int requestCode) {
        Intent intent = new Intent(mContext, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return PendingIntent.getActivity(mContext, requestCode, intent, 0);
    }

    private String getDateTimeAsString(Context mContext) {
        return DateUtils.formatDateTime(mContext,
                System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME);
    }

    private NotificationManagerCompat managerCompat;
    private NotificationManagerCompat getNotificationCompatManager() {
        if (managerCompat == null){
            managerCompat= NotificationManagerCompat.from(mContext);
            createNotificationChannel(managerCompat);
        }
        return managerCompat;
    }
    private NotificationManager manager;
    private NotificationManager getNotificationManager() {
        if (manager == null){
            manager= (NotificationManager) mContext.getSystemService(NOTIFICATION_SERVICE);
            createNotificationChannel(manager);
        }
        return manager;
    }
    private void notify(int notificationId,Notification notification) {
        getNotificationCompatManager().notify(notificationId, notification);
    }
    private void notifyCompat(int notificationId,Notification notification) {
        getNotificationCompatManager().notify(notificationId, notification);
    }

    private PendingIntent getPendingIntent(Context mContext, int requestCode) {
        Intent intent = new Intent(mContext, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return PendingIntent.getActivity(mContext, requestCode, intent, 0);

    }

    private void createNotificationChannel(NotificationManager  notificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name  = "channel_name";//getString(R.string.channel_name);
            String description = "channel_description";//getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("APP_CHANNEL_ID", name, importance);
            channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            channel.setDescription(description);

            notificationManager.createNotificationChannel(channel);
        }
    }
    private void createNotificationChannel(NotificationManagerCompat  notificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name  = "channel_name";//getString(R.string.channel_name);
            String description = "channel_description";//getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("APP_CHANNEL_ID", name, importance);
            channel.setDescription(description);
            notificationManager.createNotificationChannel(channel);
        }
    }

//    @Override
//    public void notify(String packageName, int id, String tag, Notification notification) {
//        getNotificationManager().notify();
//    }
//
//    @Override
//    public void cancel(String packageName, int id, String tag) {
//        getNotificationManager().cancel(tag, id);
//    }
//
//    @Override
//    public void cancelAll(String packageName) {
//        getNotificationManager().cancelAll();
//    }
}
