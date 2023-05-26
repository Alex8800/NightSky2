package com.example.nightsky;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class History extends AppCompatActivity {


    ListView listView;
    ArrayList<String> arrayList;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        listView = findViewById(R.id.ListView);
        arrayList = new ArrayList<>();
        db = new DatabaseHelper(this);

        populateList();

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);

    }

    void populateList()
    {
        Cursor curs = db.getAllData_History();
        do {
            String name = curs.getString(1);
            String magn = curs.getString(2);

            arrayList.add(name + " seen at magnitude = " + magn);

        } while (curs.moveToNext());

    }

}
