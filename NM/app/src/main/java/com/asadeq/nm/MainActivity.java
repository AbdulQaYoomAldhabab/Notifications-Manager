package com.asadeq.nm;

import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = "MainActivity";

    final String messageTitle = "Notification Title";
    final String messageBody = "Notification Body \n NotificationNotificationNotificationNotificationNotificationNotificationNotificationNotificationNotificationNotificationNotificationNotificationNotificationNotificationNotificationNotificationNotificationNotificationNotificationNotification";
    final String bigText = "Notification bigText \n  bigText bigText bigText bigText bigText bigText bigText bigText bigText bigText bigText bigText bigText bigText bigText bigText bigText bigText";
    final String CHANNEL_ID = "MyTestApp";

    NotificationsHandler notificationsHandler;
    private String summaryText = "Grand Technology";

    //Map<String, String> row = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            //To do//
                            return;
                        }
                        // Get the Instance ID token//
                        String token = task.getResult().getToken();
                        Toast.makeText(MainActivity.this, "Token : "+ token, Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "Token :" + token);

                    }
                });
        findViewById(R.id.notificationGroup).setOnClickListener(this);
        findViewById(R.id.Notification).setOnClickListener(this);
        findViewById(R.id.NotificationWithLandingClass).setOnClickListener(this);
        findViewById(R.id.NotificationWithLargeIcon).setOnClickListener(this);
        findViewById(R.id.NotificationWithAction).setOnClickListener(this);
        findViewById(R.id.NotificationWithLargeIconAndAction).setOnClickListener(this);
        findViewById(R.id.NotificationWithBodyTextAndActionAndLandingActivity).setOnClickListener(this);
        findViewById(R.id.NotificationWithLargeIconAndActionAndLandingActivity).setOnClickListener(this);
        findViewById(R.id.NotificationWithBigPicture).setOnClickListener(this);
        findViewById(R.id.NotificationWithBigPictureAndActionAndLanding).setOnClickListener(this);
        findViewById(R.id.NotificationWithBigPictureAndLandingActivity).setOnClickListener(this);
        findViewById(R.id.NotificationWithBigText).setOnClickListener(this);
        findViewById(R.id.NotificationWithBigTextAndLargeIcon).setOnClickListener(this);
        findViewById(R.id.NotificationWithBigTextAndLargeIconAndLandingActivity).setOnClickListener(this);
        findViewById(R.id.NotificationInboxWithMessage).setOnClickListener(this);
        findViewById(R.id.NotificationInboxWithMessageAndBigContent).setOnClickListener(this);
        findViewById(R.id.NotificationInboxWithMessageAndBigContentAndSummaryText).setOnClickListener(this);
        findViewById(R.id.NotificationWithTextAndBigText).setOnClickListener(this);
        findViewById(R.id.NotificationWithTextAndBigTextAndBigContentTitle).setOnClickListener(this);
        findViewById(R.id.NotificationWithTextAndBigTextAndBigContentTitleSummaryText).setOnClickListener(this);

        notificationsHandler = new NotificationsHandler(this);

        //final NotificationsManager manager = new NotificationsManager(MainActivity.this);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "^_^", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static int notificationCount = 0;
    @Override
    public void onClick(View v) {
        notificationCount = notificationCount+1;
        switch (v.getId()){
            case R.id.notificationGroup:
                /*List<City> mCities = DummyData.getDummyData();
                for (City city: mCities) {
                    notificationsHandler.showDetailsNotificationWithAllCitiesAction(city);
                }*/
                //notificationsHandler.showBundleNotification(mCities.size());

                break;
            case R.id.Notification:
                notificationsHandler.setNotification(
                        messageTitle,
                        messageBody,
                        R.drawable.ic_small_icon);
                break;
            case R.id.NotificationWithLandingClass:
                notificationsHandler.setNotificationWithLandingClass(
                        messageTitle,
                        messageBody,
                        R.drawable.ic_small_icon,
                        SplashActivity.class);
                break;
            case R.id.NotificationWithLargeIcon:
                notificationsHandler.setNotificationWithLargeIcon(
                        messageTitle,
                        messageBody,
                        R.drawable.ic_small_icon,
                        R.drawable.expanded_big_icon);
                break;
            case R.id.NotificationWithAction:
                notificationsHandler.setNotificationWithAction(
                        messageTitle,
                        messageBody,
                        R.drawable.ic_small_icon,
                        notificationsHandler.getAction(
                                R.drawable.ic_small_icon
                                ,"Action"
                                , SplashActivity.class));
                break;
            case R.id.NotificationWithLargeIconAndAction:
                notificationsHandler.setNotificationWithLargeIcon(
                        messageTitle,
                        messageBody,
                        R.drawable.ic_small_icon,
                        R.drawable.expanded_big_icon);
                break;
            case R.id.NotificationWithBodyTextAndActionAndLandingActivity:
                notificationsHandler.setNotificationWithBodyTextAndActionAndLandingActivity(
                        messageTitle,
                        messageBody,
                        R.drawable.ic_small_icon
                        ,notificationsHandler.getAction(
                                R.drawable.ic_small_icon
                                , "ActionTitle",
                                SplashActivity.class),
                        SplashActivity.class);
                break;
            case R.id.NotificationWithLargeIconAndActionAndLandingActivity:
                notificationsHandler.setNotificationWithLargeIconAndActionAndLandingActivity(
                        messageTitle,
                        messageBody,
                        R.drawable.ic_small_icon,
                        R.drawable.collapsed_icon,
                        notificationsHandler.getAction(
                                R.drawable.ic_small_icon
                                , "ActionTitle",
                                SplashActivity.class),
                        SplashActivity.class);
                break;
            case R.id.NotificationWithBigPicture:
                notificationsHandler.setNotificationWithBigPicture(
                        messageTitle,
                        messageBody,
                        R.drawable.ic_small_icon,
                        R.drawable.collapsed_icon,
                        R.drawable.amsterdam);
                break;
            case R.id.NotificationWithBigPictureAndActionAndLanding:
                notificationsHandler.setNotificationWithBigPictureAndActionAndLanding(
                        messageTitle,
                        messageBody,
                        R.drawable.ic_small_icon,
                        R.drawable.collapsed_icon,
                        R.drawable.amsterdam,
                        notificationsHandler.getAction(
                                R.drawable.ic_small_icon
                                , "ActionTitle",
                                SplashActivity.class),
                        SplashActivity.class);
                break;
            case R.id.NotificationWithBigPictureAndLandingActivity:
                notificationsHandler.setNotificationWithBigPictureAndLandingActivity(
                        messageTitle,
                        messageBody,
                        R.drawable.ic_small_icon,
                        R.drawable.collapsed_icon,
                        R.drawable.amsterdam,
                        SplashActivity.class);
                break;
            case R.id.NotificationWithBigText:
                notificationsHandler.setNotificationWithBigText(
                        messageTitle,
                        messageBody,
                        R.drawable.ic_small_icon,
                        bigText);
                break;
            case R.id.NotificationWithBigTextAndLargeIcon:
                notificationsHandler.setNotificationWithBigTextAndLargeIcon(
                        messageTitle,
                        messageBody,
                        R.drawable.ic_small_icon,
                        R.drawable.collapsed_icon,
                        bigText);
                break;
            case R.id.NotificationWithBigTextAndLargeIconAndLandingActivity:
                notificationsHandler.setNotificationWithBigTextAndLargeIconAndLandingActivity(
                        messageTitle,
                        messageBody,
                        R.drawable.ic_small_icon,
                        R.drawable.collapsed_icon,
                        bigText,
                        SplashActivity.class);
                break;
            case R.id.NotificationInboxWithMessage:
                notificationsHandler.setNotificationInboxWithMessage(
                        messageTitle,
                        R.drawable.ic_small_icon,
                        messageBody);
                break;
            case R.id.NotificationInboxWithMessageAndBigContent:
                notificationsHandler.setNotificationInboxWithMessageAndBigContent(
                        messageTitle,
                        R.drawable.ic_small_icon,
                        messageBody,
                        bigText);
                break;
            case R.id.NotificationInboxWithMessageAndBigContentAndSummaryText:
                notificationsHandler.setNotificationInboxWithMessageAndBigContentAndSummaryText(
                        messageTitle,
                        R.drawable.ic_small_icon,
                        messageBody,
                        bigText,
                        summaryText);
                break;
            case R.id.NotificationWithTextAndBigText:
                notificationsHandler.setNotificationWithTextAndBigText(
                        messageTitle,
                        R.drawable.ic_small_icon,
                        bigText);
                break;
            case R.id.NotificationWithTextAndBigTextAndBigContentTitle:
                notificationsHandler.setNotificationWithTextAndBigTextAndBigContentTitle(
                        messageTitle,
                        R.drawable.ic_small_icon,
                        bigText,
                        messageTitle);
                break;
            case R.id.NotificationWithTextAndBigTextAndBigContentTitleSummaryText:
                notificationsHandler.setNotificationWithTextAndBigTextAndBigContentTitleSummaryText(
                        messageTitle,
                        R.drawable.ic_small_icon,
                        bigText,
                        messageTitle,
                        summaryText
                );
                break;
        }
        //notificationsHandler.showBundleNotification(notificationCount);
        notificationCount = 0;
    }
}
