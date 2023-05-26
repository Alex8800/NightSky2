package com.example.nightsky;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

public class DisplayStarGraph extends AppCompatActivity {

    DatabaseHelper db;
    StarData star;
    Calendar calendar = Calendar.getInstance();
    Dialog myDialogLogin;

    EditText emag, date;
    TextView tv;

    String[] axisData = {"28.05.2018","06.01.2019","04.09.2019", "13.04.2020", "10.12.2020"};
    float[] yAxisData = {(float)10.70, (float)7.20,(float)10.70,(float)7.20,(float)10.70};

    List yAxisValues = new ArrayList();
    List axisValues = new ArrayList();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_star_graph);
      //  Toolbar toolbar = findViewById(R.id.toolbar);
      //  setSupportActionBar(toolbar);
        db = new DatabaseHelper(this);
        emag = findViewById(R.id.etMagnitude);
        date = findViewById(R.id.Date);
        tv = findViewById(R.id.tv);

        String numeStea="";
        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            numeStea = extras.getString("stea");
        }

        tv.setText(numeStea);




        star = db.getData_StarTable(numeStea);

        float magMin = Float.parseFloat(star.magMin);
        float magMax = Float.parseFloat(star.magMax);
        int periodMin = Integer.parseInt(star.periodMin),
                periodMax = Integer.parseInt(star.periodMax);
        String startDate = star.description, finalDate = "";


        yAxisData[0] = yAxisData[2] = yAxisData[4] = magMin;
        yAxisData[1] = yAxisData[3] = magMax;
        Line line = new Line(yAxisValues).setColor(Color.parseColor("#9C27B0"));


        axisData[0] = startDate;
        for(int i = 1; i<5; ++i)
        {
            if(i%2 == 1)
                finalDate = addData(startDate, periodMax);
            else finalDate = addData(startDate, periodMin);

            startDate = finalDate;
            axisData[i] = finalDate;
        }





        LineChartView lineChartView = findViewById(R.id.chart);


        for(int i = 0; i < axisData.length; ++i)
        {
            axisValues.add(i, new AxisValue(i).setLabel(axisData[i]));
        }

        for (int i = 0; i < yAxisData.length; i++){
            yAxisValues.add(new PointValue(i, yAxisData[i]));
        }


        List lines = new ArrayList();
        lines.add(line);

        LineChartData data = new LineChartData();
        data.setLines(lines);

        lineChartView.setLineChartData(data);

        Axis axis = new Axis();
        axis.setValues(axisValues);
        data.setAxisXBottom(axis);
        axis.setTextColor(Color.parseColor("#9C27B0"));
        axis.setName("Time");



        Axis yAxis = new Axis();
        data.setAxisYLeft(yAxis);
        yAxis.setTextColor(Color.parseColor("#9C27B0"));
        yAxis.setName("Magnitude");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = emag.getText().toString();
                String dateof = date.getText().toString();
                if(text.equals("") || dateof.equals(""))
                    Toast.makeText(DisplayStarGraph.this, "Please write your recording", Toast.LENGTH_LONG).show();
                else{
                    String name = star.starName;
                    boolean res = db.addHistory(name, text+", in " + dateof);
                    if(res == true)
                        Toast.makeText(DisplayStarGraph.this, "Observation recorded", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    String addData(String startData, int toAdd)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try{
            c.setTime(sdf.parse(startData));
        } catch(ParseException e){
            e.printStackTrace();
        }

        c.add(Calendar.DAY_OF_MONTH, toAdd);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        String output = sdf1.format(c.getTime());
        return output;
    }

}
