package com.example.administrator.shadowapplication.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import com.example.administrator.shadowapplication.R;

import java.text.DateFormat;
import java.util.Date;

import butterknife.OnClick;

/**
 * 测试通知
 */
public class NotificationActivity extends AppCompatActivity {

    Button buttonPanel,buttonPane4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        buttonPanel = (Button) findViewById(R.id.buttonPanel);
        buttonPane4 = findViewById(R.id.buttonPane4);
        buttonPane4.setOnClickListener(view -> {
            Notification.Builder builder = new Notification.Builder(this);
            Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.baidu.com"));
            PendingIntent pendingIntent  = PendingIntent.getActivity(this,0,intent,0);
            builder.setContentIntent(pendingIntent);
            builder.setSmallIcon(R.drawable.like);
            builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.hello));
            builder.setAutoCancel(true);
            builder.setContentTitle("普通通知");
            builder.setContentText("hello shadow");
            Notification notification = builder.build();
            RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.layout_notification_fold);
            notification.bigContentView =  remoteViews;
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(1,notification);
        });
        buttonPanel.setOnClickListener(v -> {
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(NotificationActivity.this);
            builder.setSmallIcon(R.drawable.ic_stars_black_24dp);
            builder.setContentTitle("hello shadow");
            builder.setContentText("this is notification test！！happy every day 。。。dear shadow ，you are so goodness");

            Intent intent = new Intent(NotificationActivity.this, MainActivity.class);
            TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(NotificationActivity.this);
            taskStackBuilder.addParentStack(MainActivity.class);
            taskStackBuilder.addNextIntent(intent);
            PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

            builder.setContentIntent(pendingIntent);
            Notification notification = builder.build();
            notificationManager.notify(1, notification);
        });

    }


    /**
     * This sample demonstrates notifications with custom content views.
     * <p>
     * <p>On API level 16 and above a big content view is also defined that is used for the
     * 'expanded' notification. The notification is created by the NotificationCompat.Builder.
     * The expanded content view is set directly on the {@link android.app.Notification} once it has been build.
     * (See {@link android.app.Notification#bigContentView}.) </p>
     * <p>
     * <p>The content views are inflated as {@link android.widget.RemoteViews} directly from their XML layout
     * definitions using {@link android.widget.RemoteViews#RemoteViews(String, int)}.</p>
     */
    private void createNotification() {
        // BEGIN_INCLUDE(notificationCompat)
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        // END_INCLUDE(notificationCompat)

        // BEGIN_INCLUDE(intent)
        //Create Intent to launch this Activity again if the notification is clicked.
        Intent i = new Intent(this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent intent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(intent);
        // END_INCLUDE(intent)

        // BEGIN_INCLUDE(ticker)
        // Sets the ticker text
        builder.setTicker("hello shadow");

        // Sets the small icon for the ticker
        builder.setSmallIcon(R.drawable.ic_stars_black_24dp);
        // END_INCLUDE(ticker)

        // BEGIN_INCLUDE(buildNotification)
        // Cancel the notification when clicked
        builder.setAutoCancel(true);

        // Build the notification
        Notification notification = builder.build();
        // END_INCLUDE(buildNotification)

        // BEGIN_INCLUDE(customLayout)
        // Inflate the notification layout as RemoteViews
        RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.notification);

        // Set text on a TextView in the RemoteViews programmatically.
        final String time = DateFormat.getTimeInstance().format(new Date()).toString();
        final String text = "hello shadow!自定义通知" + time;
        contentView.setTextViewText(R.id.textView, text);

        /* Workaround: Need to set the content view here directly on the notification.
         * NotificationCompatBuilder contains a bug that prevents this from working on platform
         * versions HoneyComb.
         * See https://code.google.com/p/android/issues/detail?id=30495
         */
        notification.contentView = contentView;

        // Add a big content view to the notification if supported.
        // Support for expanded notifications was added in API level 16.
        // (The normal contentView is shown when the notification is collapsed, when expanded the
        // big content view set here is displayed.)
        if (Build.VERSION.SDK_INT >= 16) {
            // Inflate and set the layout for the expanded notification view
            RemoteViews expandedView = new RemoteViews(getPackageName(), R.layout.notification);
            notification.bigContentView = expandedView;
        }
        // END_INCLUDE(customLayout)

        // START_INCLUDE(notify)
        // Use the NotificationManager to show the notification
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(0, notification);
        // END_INCLUDE(notify)
    }


    /**
     * Create and show a notification with a custom layout.
     * This callback is defined through the 'onClick' attribute of the
     * 'Show Notification' button in the XML layout.
     *
     * @param v
     */
    public void sendCustomNotification(View v) {
        createNotification();
    }

    /**
     * Send a sample notification using the NotificationCompat API.
     */
    public void sendStandardNotification(View view) {

        // BEGIN_INCLUDE(build_action)
        /** Create an intent that will be fired when the user clicks the notification.
         * The intent needs to be packaged into a {@link android.app.PendingIntent} so that the
         * notification service can fire it on our behalf.
         */
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://developer.android.com/reference/android/app/Notification.html"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        /**
         * Use NotificationCompat.Builder to set up our notification.
         */
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        /** Set the icon that will appear in the notification bar. This icon also appears
         * in the lower right hand corner of the notification itself.
         *
         * Important note: although you can use any drawable as the small icon, Android
         * design guidelines state that the icon should be simple and monochrome. Full-color
         * bitmaps or busy images don't render well on smaller screens and can end up
         * confusing the user.
         */
        builder.setSmallIcon(R.drawable.ic_stars_black_24dp);

        // Set the intent that will fire when the user taps the notification.
        builder.setContentIntent(pendingIntent);
        builder.setTicker("经典通知");

        // Set the notification to auto-cancel. This means that the notification will disappear
        // after the user taps it, rather than remaining until it's explicitly dismissed.
        builder.setAutoCancel(true);

        /**
         *Build the notification's appearance.
         * Set the large icon, which appears on the left of the notification. In this
         * sample we'll set the large icon to be the same as our app icon. The app icon is a
         * reasonable default if you don't have anything more compelling to use as an icon.
         */
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_stars_black_24dp));

        /**
         * Set the text of the notification. This sample sets the three most commononly used
         * text areas:
         * 1. The content title, which appears in large type at the top of the notification
         * 2. The content text, which appears in smaller text below the title
         * 3. The subtext, which appears under the text on newer devices. Devices running
         *    versions of Android prior to 4.2 will ignore this field, so don't use it for
         *    anything vital!
         */
        builder.setContentTitle("BasicNotifications Sample");
        builder.setContentText("Time to learn about notifications!");
        builder.setSubText("Tap to view documentation about notifications.");

        // END_INCLUDE (build_notification)

        // BEGIN_INCLUDE(send_notification)
        /**
         * Send the notification. This will immediately display the notification icon in the
         * notification bar.
         */
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
        // END_INCLUDE(send_notification)
    }



    @OnClick(R.id.buttonPane5)
    void btnPaneClick2() {

    }

    @OnClick(R.id.buttonPane6)
    void btnPaneClick3() {

    }


}
