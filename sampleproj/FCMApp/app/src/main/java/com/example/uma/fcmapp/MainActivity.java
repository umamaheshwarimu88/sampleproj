package com.example.uma.fcmapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="MainActivity";

    Button mBtnFcm,mBtnGrpFcm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnFcm=(Button) findViewById(R.id.btnFcm);
        mBtnGrpFcm=(Button) findViewById(R.id.btnGroupFcm);
        mBtnFcm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String token= FirebaseInstanceId.getInstance().getToken();
                Log.d(TAG,"Token:"+token);
                Toast.makeText(getApplicationContext(),"Token :"+token,Toast.LENGTH_LONG).show();
            }
        });
        mBtnGrpFcm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               new CreateNotificationKeyTask().execute("https://android.googleapis.com/gcm/notification");


            }
        });
    }
private static class CreateNotificationKeyTask  extends AsyncTask<String,Void,String> {


    @Override
    protected String doInBackground(String... urls) {
        try {


            URL url = new URL(urls[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);

            // HTTP request header
            con.setRequestProperty("project_id", "671669127332");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "key=AIzaSyAIlT8B0QcdZgwaWySK4cHbGhp0i2vSwXU");
            con.setRequestMethod("POST");
            con.connect();

            // HTTP request
            JSONObject data = new JSONObject();
            data.put("operation", "create");
            data.put("notification_key_name", "umamaheshwarimu.88@gmail.com");
            data.put("registration_ids", new JSONArray(Arrays.asList("e44JQ3rKQoE:APA91bHGlRAZZSh2sM2zPVdru_Ch7ENxoSnMncVr3koVw5iKGpIBnZpO-KAhqhGEphTZ_bKoY8SnSNOJDgzL-RAVLU6TSnvuMcEkrLxdW8nWbHLTRjYJbIUg3Eq-Ovqd7467rdPyMKUB","f19yTlowF-k:APA91bEVei3h3DE8QMABU9qTDZWfs30N5Dxy4rZsI1dzeZYzov8fKLcWo3ufqQBUyLN1qgiGLKe8YzpzODao4E2LjW1nSXS_34zULLZ-zNbz_CI7H5OPpJcQF4vkdhBgDZ79EhS9uGIb")));


            OutputStream os = con.getOutputStream();
            os.write(data.toString().getBytes("UTF-8"));
            os.close();

            // Read the response into a string
            InputStream is = con.getInputStream();
            String responseString = new Scanner(is, "UTF-8").useDelimiter("\\A").next();
            is.close();

            // Parse the JSON string and return the notification key
            JSONObject response = new JSONObject(responseString);
            return response.getString("notification_key");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.d(TAG,"Group Token: "+s);
    }
}


}
