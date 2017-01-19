package com.example.uma.fcmapp;

import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
/**
 * Created by uma on 11/10/16.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG="MyFirebaseInstanceIDService";

    @Override
    public void onTokenRefresh() {
        //Get updated token
        String refreshedToken=FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG,"New Token:"+refreshedToken);
    }
}