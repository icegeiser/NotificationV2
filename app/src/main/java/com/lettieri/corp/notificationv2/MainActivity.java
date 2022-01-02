package com.lettieri.corp.notificationv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private final String CHANNEL_ID = "personal_notifications";
    private final int NOTIFICATION_ID = 001;
    private Context сontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void displayNotification(View view) {
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // The id of the channel.
        String id = "my_channel_01";
        // The user-visible name of the channel.
        CharSequence name = getString(R.string.channel_name);
        // The user-visible description of the channel.
        String description = getString(R.string.channel_description);
        int importance = NotificationManager.IMPORTANCE_LOW;
        NotificationChannel mChannel = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            mChannel = new NotificationChannel(id, name,importance);
            // Configure the notification channel.
            mChannel.setDescription(description);
            mChannel.enableLights(true);
            // Sets the notification light color for notifications posted to this
            // channel, if the device supports this feature.
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mNotificationManager.createNotificationChannel(mChannel);

            mNotificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

            // Sets an ID for the notification, so it can be updated.
            int notifyID = 1;

            // The id of the channel.
            String CHANNEL_ID = "my_channel_01";

            // Create a notification and set the notification channel.
            Notification notification = new Notification.Builder(MainActivity.this)
                    .setContentTitle("Unlock Warning")
                    .setContentText("Seu celular foi desbloqueado.")
                    .setSmallIcon(R.drawable.ic_sms_notification)
                    .setChannelId(CHANNEL_ID)
                    .build();

            // Issue the notification.
            mNotificationManager.notify(NOTIFICATION_ID, notification);

            //KeyguardManager myKM = (KeyguardManager) сontext.getSystemService(Context.KEYGUARD_SERVICE);
            //if( myKM.inKeyguardRestrictedInputMode() ) {
            //    // it is locked
            //} else {
            //    //it is not locked
            //}
        }

    }
     

}