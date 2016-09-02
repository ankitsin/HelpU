package com.example.ankit.HelpU;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.app.ListActivity;

//import com.google.gson.Gson;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class Activity3 extends ListActivity {
    String phoneNumber;
//    ListView lv;
    ArrayList<String> all_contacts= new ArrayList<String>();
    ArrayList<String> all_numbers= new ArrayList<String>();

    ArrayList<String> added_contacts= new ArrayList<String>();
    ArrayList<String> added_numbers= new ArrayList<String>();
    ArrayList<String> finalcontact= new ArrayList<String>();
    ArrayList<String> finalnumber= new ArrayList<String>();
//    public ArrayList getAddedContacts()
//    {
//        return added_contacts;
//    }
//    public ArrayList getAddedNumbers()
//    {
//        return added_numbers;
//    }
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        getNumber(this.getContentResolver());
        // -- Display mode of the ListView

        ListView listview= getListView();
        //	listview.setChoiceMode(listview.CHOICE_MODE_NONE);
        //	listview.setChoiceMode(listview.CHOICE_MODE_SINGLE);
        listview.setChoiceMode(listview.CHOICE_MODE_MULTIPLE);
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);

        //--	text filtering
        listview.setTextFilterEnabled(true);
        String[] contacts = new String[all_contacts.size()];
        contacts = all_contacts.toArray(contacts);
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_checked, contacts));
    }

    public void onListItemClick(ListView parent, View v,int position,long id){
        CheckedTextView item = (CheckedTextView) v;
        String[] contacts = new String[all_contacts.size()];
        contacts = all_contacts.toArray(contacts);
        String[] numbers = new String[all_numbers.size()];
        numbers = all_numbers.toArray(numbers);
        if( item.isChecked())
        {
            added_contacts.add(contacts[position]);
            added_numbers.add(numbers[position]);
            System.out.println("-------------------------------------------"+added_contacts+"................."+added_numbers);
        }
        if(!item.isChecked())
        {
            added_contacts.remove(contacts[position]);
            added_numbers.remove(numbers[position]);
        }
//        Toast.makeText(this, contacts[position] + " checked : " + item.isChecked(), Toast.LENGTH_SHORT).show();
    }
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_3);
//
//    }
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.activity_main);
////        lv= (ListView) findViewById(R.id.list_contacts);
//
////        getNumber(this.getContentResolver());
//    }
    public void getNumber(ContentResolver cr)
    {
//        lv=(ListView)findViewById(R.id.list_contact);

        Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");
        while (phones.moveToNext())
        {
            String name=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//            System.out.println(".................."+phoneNumber);
            all_contacts.add(name);
            all_numbers.add(phoneNumber);

        }
        phones.close();// close cursor
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,aa);
//        lv.setAdapter(adapter);
        //display contact numbers in the list
    }
    public void buttonOnClickback(View v)
    {
        String[] numbers = new String[added_numbers.size()];
        numbers = added_numbers.toArray(numbers);
        String[] contacts = new String[added_contacts.size()];
        contacts = added_contacts.toArray(contacts);
        String data="array";
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




        try {
//            FileOutputStream fOut = openFileOutput(file,MODE_WORLD_READABLE);

            FileOutputStream fOut = openFileOutput(file,Context.MODE_APPEND);
            for(int i=0;i<contacts.length;i++)
            {
                System.out.println(contacts[i]);
                if(finalcontact.contains(contacts[i]))
                {
                    System.out.println(contacts[i]);
                }
                else
                {

                    fOut.write((contacts[i]+"_"+numbers[i]+"#").getBytes());
                }
            }
            fOut.close();
//            Toast.makeText(getBaseContext(),"file saved",Toast.LENGTH_SHORT).show();
        }

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



        back=(Button)findViewById(R.id.button_back);
        startActivity(new Intent(getApplicationContext(), Activity2.class));
        finish();
    }



@Override
    public void onBackPressed() {
    startActivity(new Intent(getApplicationContext(), Activity2.class));
    finish();

    }
    }
