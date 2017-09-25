package com.example.administrator.shadowapplication.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.shadowapplication.R;

public class NotificationActivity extends AppCompatActivity {

    Button buttonPanel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        buttonPanel = (Button) findViewById(R.id.buttonPanel);
        buttonPanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(NotificationActivity.this);
                builder.setSmallIcon(R.drawable.ic_stars_black_24dp);
                builder.setContentTitle("hello shadow");
                builder.setContentText("this is notification test！！happy every day 。。。dear shadow ，you are so goodness");

                Intent intent = new Intent(NotificationActivity.this,MainActivity.class);
                TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(NotificationActivity.this);
                taskStackBuilder.addParentStack(MainActivity.class);
                taskStackBuilder.addNextIntent(intent);
                PendingIntent pendingIntent =taskStackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);

                builder.setContentIntent(pendingIntent);
                Notification notification = builder.build();
                notificationManager.notify(1,notification);
            }
        });

    }


}
