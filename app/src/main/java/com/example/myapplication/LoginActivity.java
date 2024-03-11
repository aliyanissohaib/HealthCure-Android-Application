package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
String[] items= {"Doctor","Patient","Lab Assistant","Admin"};
AutoCompleteTextView autoCompleteTxt;

ArrayAdapter<String> adapterItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        autoCompleteTxt = findViewById(R.id.auto_checked_type);
        adapterItems = new ArrayAdapter<String>(this,R.layout.usertype,items);
        autoCompleteTxt.setAdapter(adapterItems);

//        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String item = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getApplicationContext(),"Item: "+item,Toast.LENGTH_SHORT);
//
//            }
//
//        });

    }
}