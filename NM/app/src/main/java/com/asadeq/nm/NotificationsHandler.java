package com.asadeq.nm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.text.format.DateUtils;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static android.content.Context.LAUNCHER_APPS_SERVICE;
import static android.content.Context.NOTIFICATION_SERVICE;

public class NotificationsHandler {

    private Context mContext;
    private final static String APP_CHANNEL_ID = BuildConfig.APPLICATION_ID.substring(BuildConfig.APPLICATION_ID.lastIndexOf("."));
    private final static String GROUP_CHANNEL_ID = APP_CHANNEL_ID.concat("_GROUP_NOTIFICATIONS");
    private final static String GROUP_KEY = APP_CHANNEL_ID.concat("_GROUP_KEY");
    private final static String EXTRA_NOTIFICATION_ID = "notification_id";
    private final long BASE_NOTIFICATION_ID = 100L;
//    public final static int NOTIFICATION_DEFAULT = 0;
//    public final static int NOTIFICATION_COLLAPSED_ICON = 1;
//    public final static int NOTIFICATION_COLLAPSED_TEXT = 2;
//    public final static int NOTIFICATION_EXPANDED_ICON = 3;
//    public final static int NOTIFICATION_EXPANDED_TEXT = 4;

    public NotificationsHandler(@NonNull Context mContext) {
        this.mContext = mContext;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            generateNotificationChannels();
        }
    }

    /**
     * getNotification Manager as singleTone
     * @return
     */
    private NotificationManager notificationManager;
    private NotificationManager getNotificationManager() {
        if (notificationManager == null){
            if (mContext == null){
                throwArgumentException("Context Can't be Empty !!");
                return null;
            }
            notificationManager= (NotificationManager) mContext.getSystemService(NOTIFICATION_SERVICE);
        }
        return notificationManager;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void generateNotificationChannels() {
        final List<NotificationChannel> channels = new ArrayList<>();
        channels.add(createAppNotificationChanel(
                APP_CHANNEL_ID,
                "Channel_1",
                "Description_1",
                NotificationManagerCompat.IMPORTANCE_DEFAULT));

        /*channels.add(createAppNotificationChanel(
                APP_CHANNEL_ID.concat("_2"),
                "Channel_2",
                "Description_2",
                NotificationManagerCompat.IMPORTANCE_HIGH));*/
        createNotificationChannels(channels);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannels(List<NotificationChannel> channels) {
        if (getNotificationManager() != null) {
            getNotificationManager().createNotificationChannels(channels);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private NotificationChannel createAppNotificationChanel(@NonNull final String channelId,
                                                            @NonNull final String channelName,
                                                            @NonNull final String channelDescription,
                                                            @NonNull final int channelImportance) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, channelImportance);
        channel.setDescription(channelDescription);
        return channel;
    }


    // create group  notification
//    public void showDetailsNotificationWithAllCitiesAction(final @NonNull City city) {
//        final Intent allCitiesIntent = new Intent(mContext, MainActivity.class);
//        final int notificationId = (int) (BASE_NOTIFICATION_ID + city.getId());
//
//        allCitiesIntent.putExtra(EXTRA_NOTIFICATION_ID, notificationId);
//
//        final PendingIntent allCitiesPendingIntent = PendingIntent.getActivity(
//                mContext,
//                notificationId,
//                allCitiesIntent,
//                PendingIntent.FLAG_UPDATE_CURRENT);
//
//        final Intent detailCityIntent = new Intent(mContext, MainActivity.class);
//        detailCityIntent.putExtra("CITY_ID", city.getId());
//
//        PendingIntent detailPendingIntent = PendingIntent.getActivity(
//                mContext,
//                notificationId,
//                detailCityIntent,
//                PendingIntent.FLAG_UPDATE_CURRENT);
//
//        final NotificationCompat.Action allCitiesAction = new NotificationCompat.Action(
//                R.drawable.ic_small_icon,
//                "notification_action_all_cities",
//                allCitiesPendingIntent);
//
//        final Notification notification = createCustomNotification(
//                allCitiesAction,
//                city.getDescription(),
//                detailPendingIntent);
//
//        getNotificationManager().notify(notificationId, notification);
//    }
//    private Notification createCustomNotification(final NotificationCompat.Action action,
//                                                  final String message,
//                                                  final PendingIntent contentIntent) {
//        Notification builder = new NotificationCompat.Builder(mContext, GROUP_CHANNEL_ID)
//                .setSmallIcon(R.drawable.ic_small_icon)
//                .setContentTitle("notification_title")
//                .setContentText(message)
//                .setAutoCancel(true)
//                .setContentIntent(contentIntent)
//                .addAction(action)
//                .setGroup(GROUP_KEY)
//                .build();
//
//        return builder;
//    }

   /* public void setNotification(@NonNull String title, @NonNull String bodyText, @NonNull int smallIcon){
        notify(getNotificationBuilder(title, bodyText, smallIcon));
    }
    public void setNotification(@NonNull String title, @NonNull String bodyText, @NonNull int smallIcon, @NonNull Class landingActivityClass){
        notify(getNotificationBuilder(title, bodyText, smallIcon, landingActivityClass));
    }
    public void setNotification(@NonNull String title, @NonNull String bodyText, @NonNull int smallIcon, @NonNull int largIcon,
                                @NonNull Class landingActivityClass){
        notify(getNotificationBuilder(title, bodyText, smallIcon,largIcon));
    }
    public void setNotification(@NonNull String title, @NonNull String bodyText, @NonNull int smallIcon, @NonNull int largeIcon){
        notify(getNotificationBuilder(title, bodyText, smallIcon, largeIcon));
    }
    public void setNotification(@NonNull String title, @NonNull String bodyText, @NonNull int smallIcon, @NonNull int largeIcon,
                                @NonNull NotificationCompat.Action action1){
        notify(getNotificationBuilder(title, bodyText, smallIcon, largeIcon, action1));
    }
    public void setNotification(@NonNull String title, @NonNull String bodyText, @NonNull int smallIcon, @NonNull int largeIcon,
                                @NonNull NotificationCompat.Action action1, @NonNull Class landingActivityClass){
        notify(getNotificationBuilder(title, bodyText, smallIcon, largeIcon, action1,landingActivityClass));
    }
    public void setNotification(@NonNull String title, @NonNull String bodyText, @NonNull int smallIcon, @NonNull int largeIcon,
                                @NonNull int bigPicture){
        notify(getNotificationBuilder(title, bodyText, smallIcon, largeIcon, bigPicture));
    }
    public void setNotification(@NonNull String title, @NonNull String bodyText, @NonNull int smallIcon, @NonNull int largeIcon,
                                @NonNull int bigPicture, @NonNull Class landingActivityClass){
        notify(getNotificationBuilder(title, bodyText, smallIcon, largeIcon, bigPicture, landingActivityClass));
    }
    public void setNotification(@NonNull String title, @NonNull String bodyText, @NonNull int smallIcon, @NonNull int largeIcon,
                                @NonNull int bigPicture, @NonNull NotificationCompat.Action action1, @NonNull Class landingActivityClass){
        notify(getNotificationBuilder(title, bodyText, smallIcon, largeIcon, bigPicture, action1,landingActivityClass));
    }
    public void setNotification(@NonNull String title, @NonNull String bodyText, @NonNull int smallIcon, @NonNull int largeIcon,
                                @NonNull String bigText){
        notify(getNotificationBuilder(title, bodyText, smallIcon, largeIcon, bigText));
    }
    public void setNotification(@NonNull String title, @NonNull String bodyText, @NonNull int smallIcon,
                                @NonNull NotificationCompat.Action action1){
        notify(getNotificationBuilder(title, bodyText, smallIcon, action1));
    }

    public void setNotification(@NonNull String title, @NonNull int smallIcon, @NonNull String message){
        notify(getNotificationBuilder(title, smallIcon, message));
    }
    public void setNotification(@NonNull String title,  @NonNull int smallIcon, @NonNull String message){
        notify(getNotificationBuilder(title, smallIcon, message));
    }
    public void setNotification(@NonNull String title,  @NonNull int smallIcon, @NonNull String message,
                                @NonNull String bigContent){
        notify(getNotificationBuilder(title, smallIcon, message, bigContent));
    }
    public void setNotification(@NonNull String title,  @NonNull int smallIcon, @NonNull String message,
                                @NonNull String bigContent, @NonNull String summaryText){
        notify(getNotificationBuilder(title, smallIcon, message, bigContent, summaryText));
    }*/

    /**
     * Default Notification Has only title and text
     * @param title
     * @param bodyText
     * @param SmallIcon
     * @return
     */
    public void setNotification(@NonNull String title,
                                                @NonNull String bodyText,
                                                @NonNull int SmallIcon){
        Notification builder = new NotificationCompat.Builder(mContext, APP_CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(bodyText)
                .setAutoCancel(true)
                .setGroup(GROUP_KEY)
                .setGroupSummary(true)
                .setSmallIcon(SmallIcon)
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setContentIntent(getPendingIntent())
                .build();
        notify(builder);
    }
    /**
     * Default Notification Has only title and text
     * @param title
     * @param bodyText
     * @param SmallIcon
     * @param landingActivityClass
     * @return
     */
    public void setNotification(@NonNull String title,
                                                @NonNull String bodyText,
                                                @NonNull int SmallIcon,
                                                @NonNull Class landingActivityClass){
        Notification builder = new NotificationCompat.Builder(mContext, APP_CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(bodyText)
                .setAutoCancel(true)
                .setGroup(GROUP_KEY)
                .setGroupSummary(true)
                .setSmallIcon(SmallIcon)
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setContentIntent(getPendingIntent(landingActivityClass))
                .build();
        notify(builder);
    }

    /**
     * Default Notification Has only title, text and large Icon
     * @param title
     * @param bodyText
     * @param SmallIcon
     * @param largeIcon
     * @return
     */
    public void setNotification(@NonNull String title,
                                                @NonNull String bodyText,
                                                @NonNull int SmallIcon,
                                                @NonNull int largeIcon){
        Notification builder = new NotificationCompat.Builder(mContext, APP_CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(bodyText)
                .setAutoCancel(true)
                .setGroup(GROUP_KEY)
                .setSmallIcon(SmallIcon)
                .setLargeIcon(getBitMapImage(largeIcon))
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setContentIntent(getPendingIntent())
                .build();
        notify(builder);
    }
    /**
     * Default Notification Has only title, text, large Icon and action
     * @param title
     * @param bodyText
     * @param SmallIcon
     * @param action1
     * @return
     */
    public void setNotification(@NonNull String title,
                                                @NonNull String bodyText,
                                                @NonNull int SmallIcon,
                                                @NonNull NotificationCompat.Action action1){
        Notification builder = new NotificationCompat.Builder(mContext, APP_CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(bodyText)
                .setAutoCancel(true)
                .setGroup(GROUP_KEY)
                .setSmallIcon(SmallIcon)
                .setPriority(Notification.PRIORITY_DEFAULT)
                .addAction(action1)
                .setContentIntent(getPendingIntent())
                .build();
        notify(builder);
    }
    /**
     * Default Notification Has only title, text, large Icon and action
     * @param title
     * @param bodyText
     * @param SmallIcon
     * @param largeIcon
     * @param action1
     * @return
     */
    public void setNotification(@NonNull String title,
                                                @NonNull String bodyText,
                                                @NonNull int SmallIcon,
                                                @NonNull int largeIcon,
                                                @NonNull NotificationCompat.Action action1){
        Notification builder = new NotificationCompat.Builder(mContext, APP_CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(bodyText)
                .setAutoCancel(true)
                .setGroup(GROUP_KEY)
                .setSmallIcon(SmallIcon)
                .setLargeIcon(getBitMapImage(largeIcon))
                .setPriority(Notification.PRIORITY_DEFAULT)
                .addAction(action1)
                .setContentIntent(getPendingIntent())
                .build();
        notify(builder);
    }
    /**
     * Default Notification with action and Landing Activity
     * @param title
     * @param bodyText
     * @param SmallIcon
     * @param action1
     * @param landingActivityClass
     * @return
     */
    public void setNotification(@NonNull String title,
                                                @NonNull String bodyText,
                                                @NonNull int SmallIcon,
                                                @NonNull NotificationCompat.Action action1,
                                                @NonNull Class landingActivityClass){
        Notification builder = new NotificationCompat.Builder(mContext, APP_CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(bodyText)
                .setAutoCancel(true)
                .setGroup(GROUP_KEY)
                .setSmallIcon(SmallIcon)
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setContentIntent(getPendingIntent(landingActivityClass))
                .addAction(action1)
                .build();
        notify(builder);
    }
    /**
     * Default Notification with largeIcon, action and Landing Activity
     * @param title
     * @param bodyText
     * @param SmallIcon
     * @param largeIcon
     * @param action1
     * @param landingActivityClass
     * @return
     */
    public void setNotification(@NonNull String title,
                                                @NonNull String bodyText,
                                                @NonNull int SmallIcon,
                                                @NonNull int largeIcon,
                                                @NonNull NotificationCompat.Action action1,
                                                @NonNull Class landingActivityClass){
        Notification builder = new NotificationCompat.Builder(mContext, APP_CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(bodyText)
                .setAutoCancel(true)
                .setGroup(GROUP_KEY)
                .setSmallIcon(SmallIcon)
                .setLargeIcon(getBitMapImage(largeIcon))
                .setPriority(Notification.PRIORITY_DEFAULT)
                .addAction(action1)
                .setContentIntent(getPendingIntent(landingActivityClass))
                .build();
        notify(builder);
    }
    /**
     *  Default Notification style with bigPicture
     * @param title
     * @param bodyText
     * @param SmallIcon
     * @param largeIcon
     * @param bigPicture
     * @return
     */
    public void setNotification(@NonNull String title,
                                                @NonNull String bodyText,
                                                @NonNull int SmallIcon,
                                                @NonNull int largeIcon,
                                                @NonNull int bigPicture){
        Notification builder = new NotificationCompat.Builder(mContext, APP_CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(bodyText)
                .setGroup(GROUP_KEY)
                .setSmallIcon(SmallIcon)
                .setLargeIcon(getBitMapImage(largeIcon))
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setContentIntent(getPendingIntent())
                .setStyle(getNotificationIconStyle(bigPicture))
                .build();
        notify(builder);
    }

    /**
     * Default Notification style with bigPicture, action and landing Activity
     * @param title
     * @param bodyText
     * @param SmallIcon
     * @param largeIcon
     * @param bigPicture
     * @param landingActivityClass
     * @return
     */
    public void setNotification(@NonNull String title,
                                                @NonNull String bodyText,
                                                @NonNull int SmallIcon,
                                                @NonNull int largeIcon,
                                                @NonNull int bigPicture,
                                                @NonNull NotificationCompat.Action action1,
                                                @NonNull Class landingActivityClass){
        Notification builder = new NotificationCompat.Builder(mContext, APP_CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(bodyText)
                .setGroup(GROUP_KEY)
                .setSmallIcon(SmallIcon)
                .setLargeIcon(getBitMapImage(largeIcon))
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setContentIntent(getPendingIntent(landingActivityClass))
                .setStyle(getNotificationIconStyle(bigPicture))
                .addAction(action1)
                .build();
        notify(builder);
    }
    /**
     * Default Notification style with bigPicture and landing Activity
     * @param title
     * @param bodyText
     * @param SmallIcon
     * @param largeIcon
     * @param bigPicture
     * @param landingActivityClass
     * @return
     */
    public void setNotification(@NonNull String title,
                                                @NonNull String bodyText,
                                                @NonNull int SmallIcon,
                                                @NonNull int largeIcon,
                                                @NonNull int bigPicture,
                                                @NonNull Class landingActivityClass){
        Notification builder = new NotificationCompat.Builder(mContext, APP_CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(bodyText)
                .setGroup(GROUP_KEY)
                .setSmallIcon(SmallIcon)
                .setLargeIcon(getBitMapImage(largeIcon))
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setContentIntent(getPendingIntent(landingActivityClass))
                .setStyle(getNotificationIconStyle(bigPicture))
                .build();
        notify(builder);
    }
    /**
     * Default Notification style with bigText and landing Activity
     * @param title
     * @param bodyText
     * @param SmallIcon
     * @param bigText
     * @return
     */
    public void setNotification(@NonNull String title,
                                                @NonNull String bodyText,
                                                @NonNull int SmallIcon,
                                                @NonNull String bigText){
        Notification builder = new NotificationCompat.Builder(mContext, APP_CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(bodyText)
                .setGroup(GROUP_KEY)
                .setSmallIcon(SmallIcon)
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setContentIntent(getPendingIntent())
                .setStyle(getNotificationTextStyle(bigText))
                .build();
        notify(builder);
    }
    /**
     * Default Notification style with bigText and landing Activity
     * @param title
     * @param bodyText
     * @param SmallIcon
     * @param largeIcon
     * @param bigText
     * @return
     */
    public void setNotification(@NonNull String title,
                                                @NonNull String bodyText,
                                                @NonNull int SmallIcon,
                                                @NonNull int largeIcon,
                                                @NonNull String bigText){
        Notification builder = new NotificationCompat.Builder(mContext, APP_CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(bodyText)
                .setGroup(GROUP_KEY)
                .setSmallIcon(SmallIcon)
                .setLargeIcon(getBitMapImage(largeIcon))
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setContentIntent(getPendingIntent())
                .setStyle(getNotificationTextStyle(bigText))
                .setStyle(getNotificationTextStyle(bigText))
                .build();
        notify(builder);
    }
    /**
     * Default Notification style with bigText and landing Activity
     * @param title
     * @param bodyText
     * @param SmallIcon
     * @param largeIcon
     * @param bigText
     * @return
     */
    public void setNotification(@NonNull String title,
                                                @NonNull String bodyText,
                                                @NonNull int SmallIcon,
                                                @NonNull int largeIcon,
                                                @NonNull String bigText,
                                                @NonNull Class landingActivityClass){
        Notification builder = new NotificationCompat.Builder(mContext, APP_CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(bodyText)
                .setGroup(GROUP_KEY)
                .setSmallIcon(SmallIcon)
                .setLargeIcon(getBitMapImage(largeIcon))
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setContentIntent(getPendingIntent(landingActivityClass))
                .setStyle(getNotificationTextStyle(bigText))
                .build();
        notify(builder);
    }

    /**
     * Default Inbox Notification with message
     * @param SmallIcon
     * @param message
     * @return
     */
    public void setNotificationInboxBuilder(@NonNull String title,
                                                @NonNull int SmallIcon,
                                                @NonNull String message){
        Notification builder = new NotificationCompat.Builder(mContext, APP_CHANNEL_ID)
                .setContentTitle(title)
                .setAutoCancel(true)
                .setGroup(GROUP_KEY)
                .setGroupSummary(true)
                .setSmallIcon(SmallIcon)
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setContentIntent(getPendingIntent())
                .setStyle(getNotificationInboxStyle(message))
                .build();
        notify(builder);
    }

    /**
     * Default Inbox Notification with message and bigContent
     * @param title
     * @param SmallIcon
     * @param message
     * @param bigContent
     * @return
     */
    public void setNotificationInboxStyle(@NonNull String title,
                                                @NonNull int SmallIcon,
                                                @NonNull String message,
                                                @NonNull String bigContent){
        Notification builder = new NotificationCompat.Builder(mContext, APP_CHANNEL_ID)
                .setContentTitle(title)
                .setAutoCancel(true)
                .setGroup(GROUP_KEY)
                .setGroupSummary(true)
                .setSmallIcon(SmallIcon)
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setContentIntent(getPendingIntent())
                .setStyle(getNotificationInboxStyle(message, bigContent))
                .build();
        notify(builder);
    }
    public void setNotificationInboxStyle(@NonNull String title,
                                          @NonNull int SmallIcon,
                                          @NonNull String message,
                                          @NonNull String bigContent,
                                          @NonNull String summaryText){
        Notification builder = new NotificationCompat.Builder(mContext, APP_CHANNEL_ID)
                .setContentTitle(title)
                .setAutoCancel(true)
                .setGroup(GROUP_KEY)
                .setGroupSummary(true)
                .setSmallIcon(SmallIcon)
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setContentIntent(getPendingIntent())
                .setStyle(getNotificationInboxStyle(message, bigContent, summaryText))
                .build();
        notify(builder);
    }

    /**
     * Default Inbox Notification with message, bigContent and bigText
     * @param title
     * @param SmallIcon
     * @param bigText
     * @return
     */
    public void setNotification(@NonNull String title,
                                @NonNull int SmallIcon,
                                @NonNull String bigText){
        Notification builder = new NotificationCompat.Builder(mContext, APP_CHANNEL_ID)
                .setContentTitle(title)
                .setAutoCancel(true)
                .setGroup(GROUP_KEY)
                .setGroupSummary(true)
                .setSmallIcon(SmallIcon)
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setContentIntent(getPendingIntent())
                .setStyle(getNotificationTextStyle(bigText))
                .build();
        notify(builder);
    }

    /**
     * Default Inbox Notification with message, bigContent, bigText and bigContentTitle
     * @param title
     * @param SmallIcon
     * @param bigText
     * @param bigContentTitle
     * @return
     */
    public void setNotification(@NonNull String title,
                                                @NonNull int SmallIcon,
                                                @NonNull String bigText,
                                                @NonNull String bigContentTitle){
        Notification builder = new NotificationCompat.Builder(mContext, APP_CHANNEL_ID)
                .setContentTitle(title)
                .setAutoCancel(true)
                .setGroup(GROUP_KEY)
                .setGroupSummary(true)
                .setSmallIcon(SmallIcon)
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setContentIntent(getPendingIntent())
                .setStyle(getNotificationTextStyle(bigText, bigContentTitle))
                .build();
        notify(builder);
    }

    /**
     *
     * Default Inbox Notification with message, bigContent, bigText, bigContentTitle and summaryText
     * @param title
     * @param SmallIcon
     * @param bigText
     * @param bigContentTitle
     * @param summaryText
     * @return
     */
    public void setNotification(@NonNull String title,
                                                @NonNull int SmallIcon,
                                                @NonNull String bigText,
                                                @NonNull String bigContentTitle,
                                                @NonNull String summaryText){
        Notification builder = new NotificationCompat.Builder(mContext, APP_CHANNEL_ID)
                .setContentTitle(title)
                .setAutoCancel(true)
                .setGroup(GROUP_KEY)
                .setGroupSummary(true)
                .setSmallIcon(SmallIcon)
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setContentIntent(getPendingIntent())
                .setStyle(getNotificationTextStyle(bigText, bigContentTitle, summaryText))
                .build();
        notify(builder);
    }


    /**
     *  Create explicit Intent with Landing Activity
     * @param landingActivityClass
     * @return
     */
    private PendingIntent getPendingIntent(@NonNull Class landingActivityClass) {
        Intent intent = new Intent(mContext, landingActivityClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);

        return PendingIntent.getActivity(mContext
                , getNotificationId(), intent
                , PendingIntent.FLAG_CANCEL_CURRENT);
    }

    /**
     *  create implicit intent
     * @return
     */
    private PendingIntent getPendingIntent() {
        return PendingIntent.getActivity(
                  mContext
                , getNotificationId()
                , new Intent("android.intent.action.LANDING_ACTIVITY")
                        .addCategory(Intent.CATEGORY_DEFAULT)
                , PendingIntent.FLAG_CANCEL_CURRENT);
    }

    /**
     * Create Notification style with bigPicture
     * @param bigPicture
     * @return
     */
    private NotificationCompat.Style getNotificationIconStyle(@NonNull int bigPicture) {
        return new NotificationCompat.BigPictureStyle()
                .bigLargeIcon(null)
                .bigPicture(getBitMapImage(bigPicture));
    }

    /**
     * Create Notification style bigPicture and bigLargeIcon
     * @param bigPicture
     * @param bigLargeIcon
     * @return
     */
    private NotificationCompat.Style getNotificationIconStyle(@NonNull int bigPicture, @NonNull int bigLargeIcon) {
        NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle();
        style.bigPicture(getBitMapImage(bigPicture));
        style.bigLargeIcon(getBitMapImage(bigLargeIcon));
        return style;
    }


    //region Notification Styles
    /**
     * generate style with big text
     * @param bigText
     * @return
     */
    private NotificationCompat.Style getNotificationTextStyle(@NonNull String bigText) {
        return new NotificationCompat.BigTextStyle()
                .bigText(bigText);
    }
    private NotificationCompat.Style getNotificationTextStyle(@NonNull String bigText,
                                                              @NonNull String bigContentTitle) {
        return new NotificationCompat.BigTextStyle()
                .setBigContentTitle(bigContentTitle)
                .bigText(bigText);
    }
    private NotificationCompat.Style getNotificationTextStyle(@NonNull String bigText,
                                                              @NonNull String bigContentTitle,
                                                              @NonNull String summaryText) {
        return new NotificationCompat.BigTextStyle()
                .setBigContentTitle(bigContentTitle)
                .setSummaryText(summaryText)
                .bigText(bigText);
    }
    private NotificationCompat.Style getNotificationInboxStyle(@NonNull String message1) {
        return new NotificationCompat.InboxStyle()
                .addLine(message1);
    }
    private NotificationCompat.Style getNotificationInboxStyle(@NonNull String message1,
                                                               @NonNull String bigContentTitle) {
        return new NotificationCompat.InboxStyle()
                .setBigContentTitle(bigContentTitle)
                .addLine(message1);
    }
    private NotificationCompat.Style getNotificationInboxStyle(@NonNull String message1,
                                                               @NonNull String bigContentTitle,
                                                               @NonNull String summaryText) {
        return new NotificationCompat.InboxStyle()
                .setBigContentTitle(bigContentTitle)
                .setSummaryText(summaryText)
                .addLine(message1);
    }

    private NotificationCompat.Style getNotificationMessagingStyle(@NonNull String userDisplayName,
                                                                   @NonNull String messagingText,
                                                                   @NonNull long messagingTime,
                                                                   @NonNull String messagingSender) {

        NotificationCompat.MessagingStyle.Message message = new NotificationCompat.MessagingStyle
                .Message(messagingText, messagingTime, messagingSender);
//        return  new NotificationCompat.MessagingStyle(userDisplayName)
//                .addMessage(message.getText(), message.getTimestamp(), message.getSender());
        return  new NotificationCompat.MessagingStyle(userDisplayName)
                .addMessage(message);
    }
    //endregion

    /**
     * generate action for notification
     * @param actionIcon
     * @param actionTitle
     * @param landingActivityClass
     * @return
     */
    private NotificationCompat.Action getAction(
             @NonNull int actionIcon
            ,@NonNull String actionTitle
            ,@NonNull Class landingActivityClass) {
        return new NotificationCompat.Action(actionIcon
                , actionTitle
                , getPendingIntent(landingActivityClass));
    }


    /**
     * Display Notifications
     * @param notification
     */
    private void notify(@NonNull Notification notification) {
        getNotificationManager().notify(
                getNotificationId(),
                notification);
    }
    private void throwArgumentException(@NonNull String message) {
        throw new IllegalArgumentException(message);
        //throw new IllegalStateException(message);
    }

    /**
     * Convert any resource Image To Bitmap Image
     * @param resImage
     * @return
     */
    private Bitmap getBitMapImage(@NonNull int resImage){
        return BitmapFactory.decodeResource(
                mContext.getResources(),
                resImage);
    }

    /**
     * Generate Unique Id in each call
     * @return
     */
    private int getNotificationId() {
        return  (int) Math.abs(UUID.randomUUID().getMostSignificantBits());
    }

    /**
     * Get Notification Display Time For Custom View
     * @return
     */
    private String getDateTimeAsString() {
        return DateUtils.formatDateTime(
                  mContext
                , System.currentTimeMillis()
                , DateUtils.FORMAT_SHOW_TIME);
    }
}
