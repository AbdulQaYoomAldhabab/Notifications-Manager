package com.asadeq.nm.firebase;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.widget.RemoteViews;
import androidx.core.app.NotificationCompat;

import com.asadeq.nm.MainActivity;
import com.asadeq.nm.R;
import java.util.Map;

import static android.content.Context.NOTIFICATION_SERVICE;


public class FirebaseBroadcastReceiver extends BroadcastReceiver {

    private static final String CHANNEL_ID ="CHANNEL_ID" ;
    private Context mContext;
    @Override
    public void onReceive(Context mContext, Intent intent) {

      this.mContext=mContext;
        Bundle bundle=intent.getExtras();


    }

    private void sendNotification(String messageTitle, String messageBody, Map<String, String> row) {

        RemoteViews expandedView = new RemoteViews(mContext.getPackageName(), R.layout.view_expanded_notification);
        expandedView.setTextViewText(R.id.timestamp, DateUtils.formatDateTime(mContext, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME));
        expandedView.setTextViewText(R.id.tv_content_title, messageTitle);

        RemoteViews collapsedView = new RemoteViews(mContext.getPackageName(), R.layout.view_collapsed_notification);
        collapsedView.setTextViewText(R.id.timestamp, DateUtils.formatDateTime(mContext, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME));

        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext)
                // these are the three things a NotificationCompat.Builder object requires at a minimum
                .setSmallIcon(R.drawable.ic_small_icon)
                .setContentTitle(messageTitle)
                .setContentText(messageBody)
                // notification will be dismissed when tapped
                .setAutoCancel(true)
                // tapping notification will open MainActivity
                .setContentIntent(PendingIntent.getActivity(mContext, 0, new Intent(mContext, MainActivity.class), 0))
                // setting the custom collapsed and expanded views
                .setCustomContentView(collapsedView)
                .setCustomBigContentView(expandedView)
                // setting style to DecoratedCustomViewStyle() is necessary for custom views to display
                .setStyle(new NotificationCompat.DecoratedCustomViewStyle());

        // retrieves android.app.NotificationManager
        NotificationManager notificationManager= (NotificationManager) mContext.getSystemService(NOTIFICATION_SERVICE);
        createNotificationChannel(notificationManager);
        notificationManager.notify(0, builder.build());
    }
    private void createNotificationChannel(NotificationManager  notificationManager) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "channel_name";//mContext.getString(R.string.channel_name);
            String description = "channel_description";//mContext.getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            notificationManager = mContext.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


}
