package com.example.uma.fcmapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by uma on 11/10/16.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {



    private static final String TAG="MyFirebaseMessagingService";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d(TAG,"FROM : "+remoteMessage.getFrom());

        //Check if message contains data
        if(remoteMessage.getData().size()>0){
            Log.d(TAG,"Message Data : "+remoteMessage.getData());
        }

        //Check if message contains notification
        if(remoteMessage.getNotification()!=null){
            Log.d(TAG,"Message body : "+remoteMessage.getNotification().getBody());
            sendNotification(remoteMessage.getNotification().getBody());
        }
    }


    /** Display the notification
     *
     * @param body
     */
    private void sendNotification(String body) {

        Intent intent=new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent= PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        Uri notificationsound= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notifybuilder=new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.download)
                .setContentTitle("Firebase Cloud Messaging")
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(notificationsound)
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notifybuilder.build());
///


    }
}
