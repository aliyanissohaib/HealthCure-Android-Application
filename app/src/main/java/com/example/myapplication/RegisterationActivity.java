package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

import java.util.ArrayList;

public class RegisterationActivity extends AppCompatActivity {
    Spinner spinner;
    ArrayList<String> arrIds = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        spinner = findViewById(R.id.spinner);

       arrIds.add("Doctor");
        arrIds.add("Patient");
        arrIds.add("Lab");
        arrIds.add("Admin");

        ArrayAdapter<String> spinneradapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,arrIds);
        spinner.setAdapter(spinneradapter);

    }
}