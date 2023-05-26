package com.example.nightsky;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForTestingOnly extends AppCompatActivity {

    EditText sn, mmi, mma, isv, pmi, pma;
    Button button;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_testing_only);

        sn = findViewById(R.id.starName);
        mmi = findViewById(R.id.MagMin);
        mma = findViewById(R.id.MagMax);
        isv = findViewById(R.id.isVar);
        pmi = findViewById(R.id.periodMin);
        pma = findViewById(R.id.periodMax);

        button = findViewById(R.id.button);
        db = new DatabaseHelper(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/**
                String name = sn.getText().toString(),
                        magMin = mmi.getText().toString(),
                        magMax = mma.getText().toString(),
                        isVal = isv.getText().toString(),
                        periodMin = pmi.getText().toString(),
                        periodMax = pma.getText().toString();
                StarData starData = new StarData(name, magMin, magMax, isVal, periodMin, periodMax, "asta e doar un test");
                boolean rez = db.addStar(starData);
                sn.setText(rez + "");**/

/*                StarData star = db.getData_StarTable("test0");
                sn.setText(star.starName + "fd");
                mmi.setText(star.magMin + "fd");
                mma.setText(star.magMax+ "fd");
                isv.setText(star.isVar+ "fd");
                pmi.setText(star.periodMin+ "fd");
                pma.setText(star.periodMax+ "fd");
*/



            }
        });
    }





}
