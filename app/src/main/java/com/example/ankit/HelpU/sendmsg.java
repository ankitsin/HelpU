package com.example.ankit.HelpU;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ankit.HelpU.R;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class sendmsg extends AppCompatActivity {
    Toast address;
    String lattitude;
    String longitude;
    String[] nearby;
    String IP="http://10.42.0.1/";
    String flag1="notcancel";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendmsg);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (address != null)
            address.cancel();
        address= Toast.makeText(getApplicationContext(), "Help is on the way.", Toast.LENGTH_LONG);
        address.show();
        MyLocation.LocationResult locationResult = new MyLocation.LocationResult(){
            @Override
            public void gotLocation(Location location){

                lattitude=Double.toString(location.getLatitude());
                longitude=Double.toString(location.getLongitude());

                //Got the location!
            }
        };
        MyLocation myLocation = new MyLocation();
        myLocation.getLocation(this, locationResult);
//        startActivity(new Intent(getApplicationContext(), Activity2.class));
//        final ProgressDialog dialog = new ProgressDialog(MainActivity.this);
//        dialog.setTitle("Loading...");
//        dialog.setMessage("Please wait.");
//        dialog.setIndeterminate(true);
//        dialog.setCancelable(false);
//        dialog.show();
//
//        long delayInMillis = 20000;
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                dialog.dismiss();
//            }
//        }, delayInMillis);

        Handler myHandler = new Handler();
        myHandler.postDelayed(mMyRunnable, 15000);
    }
    public void buttonOnClickWcancel(View v) {
//        Button cancel = (Button) findViewById(R.id.button_wcancel);
        flag1="cancel";
        if (address != null)
            address.cancel();
        address= Toast.makeText(getApplicationContext(), "Help cancelled", Toast.LENGTH_LONG);
        address.show();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }


    private Runnable mMyRunnable = new Runnable()
    {
        @Override
        public void run()
        {
            sendSMSMessage();        }
    };
    protected void sendSMSMessage() {
//        Log.i("Send SMS", "");
        if(flag1.equals("cancel"))
        {
//            Toast.makeText(getApplicationContext(), "Message not sent", Toast.LENGTH_LONG).show();
            System.out.println("message not sent");
        }
        else
        {

            String file = "mydata";
            String temp = "";
            try {
                FileInputStream fin = openFileInput(file);
                int c;


                while ((c = fin.read()) != -1) {

                    temp = temp + Character.toString((char) c);
                }
                System.out.println(temp);
//            Toast.makeText(getBaseContext(),"file read", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
            }
            if(temp!="") {

                String[] con_num = temp.split("#");
//        temp;
                ArrayList<String> finalcontact = new ArrayList<String>();
                ArrayList<String> finalnumber = new ArrayList<String>();


                for (int i = 0; i < con_num.length; i++) {
                    String[] temp1 = con_num[i].split("_");
                    finalcontact.add(temp1[0].toString());
                    finalnumber.add(temp1[1].toString());
                    String phoneNo = temp1[1].toString();

                    String message = "Please help.   http://maps.google.com/?q="+ lattitude + "," + longitude;
                    System.out.println(message);
                    try {
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(phoneNo, null, message, null, null);
                        if (address != null)
                            address.cancel();
                        address= Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG);
                        address.show();
                    }
                    catch (Exception e) {
                        if (address != null)
                            address.cancel();

                        address=Toast.makeText(getApplicationContext(), "SMS faild, please try again.", Toast.LENGTH_LONG);
                        address.show();
                        e.printStackTrace();
                    }
                }

            }
            SharedPreferences settings = getSharedPreferences(Login.PREFS_NAME, 0);
//Get "hasLoggedIn" value. If the value doesn't exist yet false is returned
            String user= settings.getString("user", null);
//        SharedPreferences settings1 = getSharedPreferences(LOCATE, 0);
//Get "hasLoggedIn" value. If the value doesn't exist yet false is returned
//        System.out.println(lattitude);
//        System.out.println(longitude);
//        System.out.println(user);
            new LongOperation1().execute(user + "_" + lattitude + "_" + longitude + "_0");
        }
//        String[] near=nearby.toString().split("_");
//        System.out.println(nearby);
//        if(near.length>1) {
//        for (int i = 0; i < near.length-1; i++) {
////            String[] temp1 = con_num[i].split("_");
////            finalcontact.add(temp1[0].toString());
////            finalnumber.add(temp1[1].toString());
//            String phoneNo = near[i].toString();
//
//            String message = "Yo Bitchesss!!    http://maps.google.com/?q="
//                    + lattitude + "," + longitude;
//            System.out.println(message);
//            try {
//                SmsManager smsManager = SmsManager.getDefault();
//                smsManager.sendTextMessage(phoneNo, null, message, null, null);
//                Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
//            }
//            catch (Exception e) {
//                Toast.makeText(getApplicationContext(), "SMS faild, please try again.", Toast.LENGTH_LONG).show();
//                e.printStackTrace();
//            }
//
//        }
//        }
//        Location location = new Location();
//        location.setLatitude(latitude);
//        location.setLongitude(longitude);
//
//        Location locationFromDB = new Location();
//        locationFromDB.setLatitude(latitudeFromDB);
//        locationFromDB.setLongitude(longitudeFromDB);
//
//        float dist = location.distanceTo(locationFromDB);
//        if(dist<100f){
//            //do here your magic
//        }


//        System.out.println(finalcontact);

    }
    private class LongOperation1 extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try{
                System.out.println(params[0]);
//
                String name    = URLEncoder.encode(params[0], "UTF-8");

                HttpClient Client = new DefaultHttpClient();

                String URL = IP+"location.php?location="+name;
                System.out.println(URL);
                HttpGet httpget = new HttpGet(URL);

                String SetServerString = "";
                try
                {

                    ResponseHandler<String> responseHandler = new BasicResponseHandler();
                    SetServerString = Client.execute(httpget, responseHandler);
                    return  SetServerString;

                }
                catch(Exception ex)
                {
                    System.out.println(ex.toString());
                    return "Fail!";
                }

            }
            catch(UnsupportedEncodingException ex)
            {
                return "Fail2";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if(result.equals("Inserted"))
            {

                System.out.println("Inserted");

                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
            else if (result.equals("Altered"))
            {
                System.out.println("Altered");

                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
//                Toast.makeText(getApplicationContext(), "Account Exist", Toast.LENGTH_LONG).show();


            }
            else if (result.equals("Notinserted"))
            {
//                Toast.makeText(getApplicationContext(), "Account Exist", Toast.LENGTH_LONG).show();
                System.out.println("Notinserted");
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
            else if (result.equals("Notaltered"))
            {
//                Toast.makeText(getApplicationContext(), "Account Exist", Toast.LENGTH_LONG).show();
                System.out.println("Notaltered");
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
            else
            {
                nearby=result.toString().split("_");

                System.out.println("Yooooo");
                if(nearby.length>1)
                {

                    System.out.println(nearby[1]);
                    for (int i = 1; i < nearby.length; i++) {
//            String[] temp1 = con_num[i].split("_");
//            finalcontact.add(temp1[0].toString());
//            finalnumber.add(temp1[1].toString());
                        String phoneNo = nearby[i].toString();

                        String message = "Hi! Stranger, Please help me at   http://maps.google.com/?q="+ lattitude + "," + longitude;
                        System.out.println(message);
                        try {
                            SmsManager smsManager = SmsManager.getDefault();
                            smsManager.sendTextMessage(phoneNo, null, message, null, null);
                            if (address != null)
                                address.cancel();

                            address=Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG);
                            address.show();
                        }
                        catch (Exception e) {
                            if (address != null)
                                address.cancel();
                            address= Toast.makeText(getApplicationContext(), "SMS faild, please try again.", Toast.LENGTH_LONG);
                            address.show();
                            e.printStackTrace();
                        }
                    }
                }
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        }
//            textView1.setText(result);


    }
}
