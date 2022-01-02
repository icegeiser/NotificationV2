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
import android.os.Handler;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //---------------------------------------------------------------------------------------------
    // Create the Handler
    // O handler é o modo para realizar uma tarefa repetidamente.
    // Essa tarefa será monitorar o estados do telefone se está bloqueado ou não.
    // Quando ele estiver bloqueado, não acontece nada, quando ele está desbloqueado, manda uma
    // mensagem para o celular avisando que o telefone foi desbloqueado.
    private int iteration = 0;
    private Handler handler = new Handler();

    // Define the code block to be executed
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {

            boolean phoneLocked;
            Context context = null;
            //phoneLocked = isPhoneIsLockedOrNot(context);
            phoneLocked = checkPhoneScreenLocked();

            if (phoneLocked == true){
                //phoneLocked
            } else {
                iteration++;
                //phoneUnlocked
                //----------------------------------------------------------------------------------
                // Specify the layout you are using.
                setContentView(R.layout.activity_main);

                // Load and use views afterwards
                TextView txtView = (TextView)findViewById(R.id.textView2);
                txtView.setText("Hello" +String.valueOf(iteration));
            }
            handler.postDelayed(runnable, 2000);
        }
    };
    //---------------------------------------------------------------------------------------------

    //---------------------------------------------------------------------------------------------
    // Criando as mensagens que serão enviadas ao celular, essas mensagens são as Notification bar
    private final String CHANNEL_ID = "personal_notifications";
    private final int NOTIFICATION_ID = 001;
    private Context сontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void displayNotification(View view) throws InterruptedException {

        // Start the Runnable immediately
        handler.post(runnable);

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


            Notification notification = new Notification.Builder(MainActivity.this)
               .setContentTitle("Unlock Warning")
               .setContentText("Seu celular foi desbloqueado.")
               .setSmallIcon(R.drawable.ic_sms_notification)
               .setChannelId(CHANNEL_ID)
               .build();

            // Issue the notification.
            mNotificationManager.notify(NOTIFICATION_ID, notification);
        }
    }
    //---------------------------------------------------------------------------------------------

    //---------------------------------------------------------------------------------------------
    // Método para verificar se o celular está bloqueado ou não, o retorno é true para bloqueado
    // e false para desbloqueado.
    private boolean isPhoneIsLockedOrNot(Context context) {
        boolean isPhoneLock = false;
        if (context != null) {
            KeyguardManager myKM = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
            if (myKM != null && myKM.isKeyguardLocked()) {
                isPhoneLock = true;
            }
        }
        return isPhoneLock;
    }
    //---------------------------------------------------------------------------------------------

    private boolean checkPhoneScreenLocked(){
        boolean isPhoneLock = false;
        KeyguardManager myKM = (KeyguardManager) getApplicationContext().getSystemService(Context.KEYGUARD_SERVICE);
        if( myKM.isKeyguardLocked()) {
            System.out.println("Phone is locked");
            isPhoneLock = true;
        } else {
            isPhoneLock = false;
            System.out.println("Phone is not locked");
        }
        return isPhoneLock;
    }

}

