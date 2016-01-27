package com.example.ankit.interiit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;
import android.app.ListActivity;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

//import com.google.gson.Gson;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Set;

public class Activity4 extends ListActivity {
    String phoneNumber;
    //    ListView lv;
    ArrayList<String> finalcontact= new ArrayList<String>();
    ArrayList<String> finalnumber= new ArrayList<String>();

    ArrayList<String> added_contacts= new ArrayList<String>();
    ArrayList<String> added_numbers= new ArrayList<String>();

    //
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        // -- Display mode of the ListView


        String file = "mydata";
        String temp="";
        try{
            FileInputStream fin = openFileInput(file);
            int c;


            while( (c = fin.read()) != -1){

                temp = temp + Character.toString((char)c);
            }
            System.out.println("in activity4"+temp);
//            Toast.makeText(getBaseContext(),"file read", Toast.LENGTH_SHORT).show();
        }
        catch(Exception e){
        }
        String [] con_num=temp.split("#");
//        temp;
//        if (con_num.length>1)
//        {
        for(int i=0;i<con_num.length;i++)
        {
//            System.out.println(con_num[i]);
            String[] temp1=con_num[i].split("_");
            if(temp1.length > 1)
            {
                System.out.println(con_num[i]);
                finalcontact.add(temp1[0].toString());
                finalnumber.add(temp1[1].toString());
            }

        }


        added_contacts=finalcontact;
        added_numbers=finalnumber;
        System.out.println(finalcontact);
        String[] contacts = new String[finalcontact.size()];
        contacts = finalcontact.toArray(contacts);

        System.out.println(contacts[0]);



        ListView listview= getListView();
        listview.setChoiceMode(listview.CHOICE_MODE_MULTIPLE);

        //--	text filtering
        listview.setTextFilterEnabled(true);
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_checked, contacts));
    }

    public void onListItemClick(ListView parent, View v,int position,long id){
        CheckedTextView item = (CheckedTextView) v;
        String[] contacts = new String[finalcontact.size()];
        contacts = finalcontact.toArray(contacts);
        String[] numbers = new String[finalnumber.size()];
        numbers = finalnumber.toArray(numbers);
        if( item.isChecked())
        {
            added_contacts.remove(contacts[position]);
            added_numbers.remove(numbers[position]);
            System.out.println("-------------------------------------------"+added_contacts+"................."+added_numbers);
        }
        if(!item.isChecked())
        {
            if(position>added_contacts.size()-1)
            {
                added_contacts.add(contacts[position-1]);
                added_numbers.add(numbers[position-1]);

            }
            else {
                added_contacts.add(contacts[position]);
                added_numbers.add(numbers[position]);
            }
        }
//        Toast.makeText(this, contacts[position] + " checked : " + item.isChecked(), Toast.LENGTH_SHORT).show();
    }
    //

    public void buttonOnClickremove(View v)
    {
        String[] numbers = new String[added_numbers.size()];
        numbers = added_numbers.toArray(numbers);
        String[] contacts = new String[added_contacts.size()];
        contacts = added_contacts.toArray(contacts);
        String data="array";
        String file = "mydata";
        try {
            FileOutputStream fOut = openFileOutput(file,MODE_WORLD_READABLE);

//            FileOutputStream fOut = openFileOutput(file,Context.MODE_APPEND);
            for(int i=0;i<contacts.length;i++)
            {
                System.out.println(contacts[i]);
                fOut.write((contacts[i]+"_"+numbers[i]+"#").getBytes());
            }
            fOut.close();
//            Toast.makeText(getBaseContext(),"file saved",Toast.LENGTH_SHORT).show();
        }

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



        back=(Button)findViewById(R.id.button_remove);
        startActivity(new Intent(getApplicationContext(), Activity2.class));
        finish();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), Activity2.class));
        finish();

    }
}
