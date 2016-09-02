package com.example.ankit.HelpU;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.FileInputStream;
import java.util.ArrayList;

public class Activity2 extends AppCompatActivity {
    Button addcontacts;
    ListView lv;
    ArrayList<String> finalcontact = new ArrayList<String>();
    ArrayList<String> finalnumber = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);

        setSupportActionBar(toolbar);
        String file = "mydata";
        String temp="";
        try{
            FileInputStream fin = openFileInput(file);
            int c;


            while( (c = fin.read()) != -1){

                temp = temp + Character.toString((char)c);
            }
            System.out.println("in activity2"+temp);
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

                String[] temp1=con_num[i].split("_");
                if(temp1.length > 1)
                {

                    finalcontact.add(temp1[0].toString());
                    finalnumber.add(temp1[1].toString());
                }

            }

//        }

        System.out.println("zero conatct"+con_num[0]);
        System.out.println("final conatct"+finalcontact);
        String[] stockArr = new String[finalcontact.size()];
        stockArr = finalcontact.toArray(stockArr);
        ArrayAdapter<String> myAdapter=new
                ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                stockArr);
        ListView myList=
                (ListView) findViewById(R.id.list_added);
        myList.setAdapter(myAdapter);

//        new LongOperation().execute("yeah");
//        String[] array=finalcontacts.split("_");
//        System.out.println(array[0]);
//        finalcontacts=loadArray("contact_name",this);
//        System.out.println(finalcontacts.length);

    }
    public String[] loadArray(String arrayName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("preferencename", 0);
        int size = prefs.getInt(arrayName + "_size", 0);
        String array[] = new String[size];
        for(int i=0;i<size;i++)
            array[i] = prefs.getString(arrayName + "_" + i, null);
        return array;
    }
    public void buttonOnClickadd(View v)
    {
        addcontacts=(Button)findViewById(R.id.button_add);
        startActivity(new Intent(getApplicationContext(), Activity3.class));
        finish();
    }
    public void buttonOnClickdelete(View v)
    {
        addcontacts=(Button)findViewById(R.id.button_delete);
        startActivity(new Intent(getApplicationContext(), Activity4.class));
        finish();
    }

    public void buttonOnClickbacktohelp(View v)
    {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();

    }




}
