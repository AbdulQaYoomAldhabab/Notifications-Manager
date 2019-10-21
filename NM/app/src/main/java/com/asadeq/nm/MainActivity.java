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

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = "MainActivity";

    final String messageTitle = "Notification Title";
    final String messageBody = "Notification Body \n NotificationNotificationNotificationNotificationNotificationNotificationNotificationNotificationNotificationNotificationNotificationNotificationNotificationNotificationNotificationNotificationNotificationNotificationNotificationNotification";
    final String CHANNEL_ID = "MyTestApp";

    NotificationsHandler notificationsHandler;
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
        findViewById(R.id.notificationDefault).setOnClickListener(this);
        findViewById(R.id.notificationCollapsed).setOnClickListener(this);
        findViewById(R.id.notificationExpanded).setOnClickListener(this);
        findViewById(R.id.notificationInbox).setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.notificationGroup:
                /*List<City> mCities = DummyData.getDummyData();
                for (City city: mCities) {
                    notificationsHandler.showDetailsNotificationWithAllCitiesAction(city);
                }*/
                //notificationsHandler.showBundleNotification(mCities.size());

                break;
            case R.id.notificationDefault:
                notificationsHandler.setNotification(
                        messageTitle,
                        messageBody,
                        R.drawable.ic_small_icon,
                        SplashActivity.class);
                break;
            case R.id.notificationCollapsed:
                notificationsHandler.setNotification(
                        messageTitle,
                        messageBody,
                        R.drawable.ic_small_icon,
                        R.drawable.collapsed_icon);
                break;
            case R.id.notificationExpanded:
                notificationsHandler.setNotification(
                        messageTitle,
                        messageBody,
                        R.drawable.ic_small_icon,
                        R.drawable.collapsed_icon,
                        R.drawable.amsterdam,
                        SplashActivity.class);
                break;
            case R.id.notificationInbox:
                notificationsHandler.setNotification(
                        messageTitle,
                        R.drawable.ic_small_icon,
                        messageBody);
                break;
        }
    }
}
