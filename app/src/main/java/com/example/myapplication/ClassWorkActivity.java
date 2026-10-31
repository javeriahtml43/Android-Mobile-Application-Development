package com.example.myapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class ClassWorkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classwork);

        String[] countries = {"Pakistan", "India", "China", "USA", "Canada", "Japan"};

        // ListView mein list dikhana
        ListView listView = findViewById(R.id.listView);
        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, countries);
        listView.setAdapter(listAdapter);

        // AutoComplete mein suggestions
        AutoCompleteTextView autoCountry = findViewById(R.id.autoCountry);
        ArrayAdapter<String> autoAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_dropdown_item_1line, countries);
        autoCountry.setAdapter(autoAdapter);
    }
}