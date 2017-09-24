package be.tarsos.tarsos.dsp.android.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import static android.R.color.holo_blue_bright;
import static android.R.color.holo_blue_light;

/**
 * Created by Umatharsini on 23/09/2017.
 */

public class LineChartActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linechart);

        Intent intent = getIntent();
        float [] arrPitch2 = intent.getFloatArrayExtra("pitch-array");
        LineChart lineChart = (LineChart) findViewById(R.id.chart);

        ArrayList<Integer> colors = new ArrayList<Integer>();

        ArrayList<Entry> entries = new ArrayList<Entry>();

        for (int x = 0; x<arrPitch2.length; x++)
        {
            entries.add(new Entry(arrPitch2[x], x));
            if (arrPitch2[x] >300)
                colors.add(this.getResources().getColor(R.color.red));
            if (arrPitch2[x] == -1)
                colors.add(this.getResources().getColor(R.color.purple));
            else if (arrPitch2[x] == 0)
                colors.add(this.getResources().getColor(R.color.blue));
            else if (arrPitch2[x] <=300)
                colors.add(this.getResources().getColor(R.color.green));
        }

        LineDataSet dataset = new LineDataSet(entries, "Pitch");

        ArrayList<String> labels = new ArrayList<String>();

        for (int x = 0; x<arrPitch2.length; x++)
        {
            labels.add(" " + x);
        }



        LineData data = new LineData(labels, dataset);
        dataset.setColor(Color.rgb(41,163,163)); //
        dataset.setCircleColors(colors);
        dataset.setDrawCubic(true);
        dataset.setDrawFilled(false);
        //dataset.setCircleColor(Color.BLACK);
        //dataset.setHighLightColor(Color.rgb(244,117,177));
        lineChart.setData(data);
        lineChart.animateY(5000);


    }
}
