package com.example.ankit.HelpU;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Login extends AppCompatActivity {
    String number1;
    String password1;
    String IP="http://10.42.0.1/";
    public static final String PREFS_NAME = "MyPrefsFile";
//    public static final String LOGIN_NAME = "MyPrefsFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SharedPreferences settings = getSharedPreferences(Login.PREFS_NAME, 0);
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);

//Get "hasLoggedIn" value. If the value doesn't exist yet false is returned
        boolean hasLoggedIn = settings.getBoolean("hasLoggedIn", false);

        if(hasLoggedIn)
        {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            Intent intent = new Intent();
            intent.setClass(Login.this, MainActivity.class);
            startActivity(intent);
            Login.this.finish();
        }

    }
    public void buttonOnClicklogin(View v) {
       EditText number=(EditText) findViewById(R.id.textlogin);
        EditText password= (EditText) findViewById(R.id.textlogpass);
        number1=number.getText().toString();
        password1=password.getText().toString();
//        contacts = (Button) findViewById(R.id.button_contacts);



        new LongOperation().execute(number1 + "_" + password1);


    }
    public void buttonOnClicksignup(View v) {
//        contacts = (Button) findViewById(R.id.button_contacts);
        EditText number=(EditText) findViewById(R.id.textlogin);
        EditText password= (EditText) findViewById(R.id.textlogpass);
        number1=number.getText().toString();
        password1=password.getText().toString();
//        contacts = (Button) findViewById(R.id.button_contacts);
        new LongOperation1().execute(number1 + "_" + password1);
//        startActivity(new Intent(getApplicationContext(), Signup.class));
    }
    private class LongOperation extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try{
                System.out.println(params[0]);
//
                String name    = URLEncoder.encode(params[0], "UTF-8");

                HttpClient Client = new DefaultHttpClient();

                String URL = IP+"login.php?auth="+name;
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
            if(result.equals("Yes"))
            {
                Toast.makeText(getApplicationContext(), "Logged In", Toast.LENGTH_LONG).show();
                SharedPreferences settings = getSharedPreferences(Login.PREFS_NAME, 0); // 0 - for private mode
                SharedPreferences.Editor editor = settings.edit();
//                SharedPreferences.Editor login = getSharedPreferences(LOGIN_NAME, MODE_PRIVATE).edit();
//                login.putString("user", number1);

//Set "hasLoggedIn" to true
                editor.putBoolean("hasLoggedIn", true);
                editor.putString("user",number1);
                editor.putString("first","Yes");

// Commit the edits!
                editor.commit();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_LONG).show();

            }
            System.out.println(result.getClass().getName());
        }
//            textView1.setText(result);


        }
    private class LongOperation1 extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try{
                System.out.println(params[0]);
//
                String name    = URLEncoder.encode(params[0], "UTF-8");

                HttpClient Client = new DefaultHttpClient();

                String URL = IP+"signup.php?auth="+name;
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
            if(result.equals("Yes"))
            {
                Toast.makeText(getApplicationContext(), "Signed Up", Toast.LENGTH_LONG).show();
                SharedPreferences settings = getSharedPreferences(Login.PREFS_NAME, 0); // 0 - for private mode
                SharedPreferences.Editor editor = settings.edit();

//Set "hasLoggedIn" to true
                editor.putBoolean("hasLoggedIn", true);
                editor.putString("user",number1);

// Commit the edits!
                editor.commit();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
            else if(result.equals(""))
            {
                Toast.makeText(getApplicationContext(), "Account Exist", Toast.LENGTH_LONG).show();

            }
            else
            {
                Toast.makeText(getApplicationContext(), "Invalid Mobile Number", Toast.LENGTH_LONG).show();

            }
            System.out.println(result.getClass().getName());
        }
//            textView1.setText(result);


    }
}
