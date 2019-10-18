package com.asadeq.nm;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.text.format.DateUtils;

import androidx.core.app.NotificationCompat;

import java.util.UUID;

import static android.content.Context.NOTIFICATION_SERVICE;

public class NotificationsHandler {

    private Activity mContext;
    public static String CHANNEL_ID = null;
    public final static int NOTIFICATION_DEFAULT = 0;
    public final static int NOTIFICATION_COLLAPSED = 1;
    public final static int NOTIFICATION_EXPANDED = 2;

    public NotificationsHandler(Activity mContext) {
        this.mContext = mContext;
        this.CHANNEL_ID = getNotificationChannel();
    }


    public void setNotification(String title, String description, int notificationType/*, int notificationId*/){
        //int notificationId = (int) getNotificationId();
        int notificationId = notificationType;
        switch (notificationType){
            case NOTIFICATION_DEFAULT:
                notify(notificationId, getNotificationBuilder1(title, description));
                break;
            case NOTIFICATION_COLLAPSED:
                notify(notificationId, getCollapsedNotificationBuilder(title, description));
                break;
            case NOTIFICATION_EXPANDED:
                notify(notificationId, getExpandedNotificationBuilder(title, description));
                break;
        }
    }

    private long getNotificationId() {
        UUID uid = UUID.randomUUID();
        return  Math.abs(uid.getMostSignificantBits());
    }

    private Notification getExpandedNotificationBuilder(String title, String description){
        return new NotificationCompat.Builder(mContext, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(description)
                .setSmallIcon(R.drawable.ic_small_icon)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setLargeIcon(getBitMapImage(R.drawable.ic_small_icon))
                .setStyle(getNotificationIconStyle(R.drawable.ic_small_icon))
                .build();
    }
    private Notification getCollapsedNotificationBuilder(String title, String description){
        return new NotificationCompat.Builder(mContext, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(description)
                .setSmallIcon(R.drawable.ic_small_icon)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setLargeIcon(getBitMapImage(R.drawable.ic_small_icon))
                .setStyle(getNotificationIconStyle(R.drawable.ic_small_icon))
                .build();
    }
    private Notification getNotificationBuilder1(String title, String description){
        return new NotificationCompat.Builder(mContext, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(description)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_small_icon)
                .setLargeIcon(getBitMapImage(R.drawable.collapsed_icon))
                .setPriority(Notification.PRIORITY_DEFAULT)
                //.setStyle(getNotificationIconStyle(R.drawable.expanded_big_icon, R.drawable.collapsed_icon))
                .build();
    }
    private PendingIntent getPendingIntent(int requestCode) {
        Intent intent = new Intent(mContext, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return PendingIntent.getActivity(mContext, requestCode, intent, 0);
    }
    private NotificationCompat.Style getNotificationIconStyle(int bigPicture) {
        return new NotificationCompat.BigPictureStyle()
                .bigPicture(getBitMapImage(bigPicture));
    }
    private NotificationCompat.Style getNotificationIconStyle(int bigPicture, int largeIcon) {
        return new NotificationCompat.BigPictureStyle()
                .bigPicture(getBitMapImage(bigPicture));
    }


    private Notification getNotificationBuilder1(String title, String description, String channelId, String bigText){
        return new NotificationCompat.Builder(mContext, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(description)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_small_icon)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setLargeIcon(getBitMapImage(R.drawable.collapsed_icon))
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setStyle(getNotificationTextStyle(bigText))
                .build();
    }

    private NotificationCompat.Style getNotificationTextStyle(String bigText) {
        return new NotificationCompat.BigTextStyle()
                .bigText(bigText);
    }

    private NotificationManager notificationManager;
    private NotificationManager getNotificationManager() {
        if (notificationManager == null){
            //notificationManager= NotificationManagerCompat.from(mContext);
            notificationManager= (NotificationManager) mContext.getSystemService(NOTIFICATION_SERVICE);
        }
        return notificationManager;
    }
    private void notify(int notificationId, Notification notification) {
        getNotificationManager().notify(notificationId, notification);
    }
    private String getNotificationChannel(/*String channelId, String channelName*/) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name  = "channel_name";//getString(R.string.channel_name);
            String description = "channel_description";//getString(R.string.channel_description);
            String channelId = BuildConfig.APPLICATION_ID.substring(BuildConfig.APPLICATION_ID.lastIndexOf("."));
            NotificationChannel channel = new NotificationChannel(channelId, channelId, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            channel.setDescription(description);
            getNotificationManager().createNotificationChannel(channel);
            return channel.getId();
        }
        return null;
    }
    private Bitmap getBitMapImage(int resImage){
        return BitmapFactory.decodeResource(mContext.getResources(),resImage);
    }

    private String getDateTimeAsString() {
        return DateUtils.formatDateTime(mContext,
                System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME);
    }
}
