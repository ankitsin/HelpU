package com.example.ankit.HelpU;

import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Toast;

import com.example.ankit.HelpU.R;

import java.io.FileInputStream;
import java.util.ArrayList;

public class sendmsg extends AppCompatActivity {
    Toast address;
    String lattitude;
    String longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendmsg);
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
    private Runnable mMyRunnable = new Runnable()
    {
        @Override
        public void run()
        {
            sendSMSMessage();        }
    };
    protected void sendSMSMessage() {
//        Log.i("Send SMS", "");

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

                String message = "Msg Testing.    http://maps.google.com/?q="+ lattitude + "," + longitude;
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
//        SharedPreferences settings = getSharedPreferences(Login.PREFS_NAME, 0);
//Get "hasLoggedIn" value. If the value doesn't exist yet false is returned
//        user= settings.getString("user", null);
//        SharedPreferences settings1 = getSharedPreferences(LOCATE, 0);
//Get "hasLoggedIn" value. If the value doesn't exist yet false is returned
//        System.out.println(lattitude);
//        System.out.println(longitude);
//        System.out.println(user);
//        new LongOperation1().execute(user + "_" + lattitude + "_" + longitude + "_0");
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
}
