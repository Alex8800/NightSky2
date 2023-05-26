package com.example.nightsky;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

public class StarChart_Max extends AppCompatActivity {

    Button NE, NV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star_chart__max);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        NE = findViewById(R.id.ne);
        NV = findViewById(R.id.nv);

        NE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StarChart_Max.this, NE_star_chart.class);
                startActivity(intent);
            }
        });

        NV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StarChart_Max.this, NV_star_chart.class);
                startActivity(intent);
            }
        });

    }

}
