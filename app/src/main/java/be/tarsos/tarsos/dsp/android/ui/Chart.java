package be.tarsos.tarsos.dsp.android.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

/**
 * Created by Umatharsini on 21/09/2017.
 */

public class Chart extends ActionBarActivity {

    //private RelativeLayout mainLayout;
    private RelativeLayout mainLayout;
    private LineChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);


        //Create line chart
        mChart = new LineChart(this);

        //add to main layout
        mainLayout.addView(mChart, 1000, 500);

       //customize line chart
        mChart.setDescription("Pitch");
        mChart.setNoDataTextDescription("No data at the moment");

        //enable value highllighting
        mChart.setHighlightPerTapEnabled(true);

        //enable touch gesture
        mChart.setTouchEnabled(true);

        //enable scaling and draging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setDrawGridBackground(false);

        //enable pinch  zoom to avoid scaling x and y axis seperately
        mChart.setPinchZoom(true);

        //alternativ backgrounfd color
        mChart.setBackgroundColor(Color.LTGRAY);

        //work on data
        LineData data = new LineData();
        data.setValueTextColor(Color.WHITE);

        //add data to line chart
        mChart.setData(data);

        //get legend object
        Legend l = mChart.getLegend();

        //customixe legend
        l.setForm(Legend.LegendForm.LINE);
        l.setTextColor(Color.WHITE);

        XAxis xl =  mChart.getXAxis();
        xl.setTextColor(Color.WHITE);
        xl.setDrawGridLines(false);
        xl.setAvoidFirstLastClipping(true);

        YAxis yl = mChart.getAxisLeft();
        yl.setTextColor(Color.WHITE);
        yl.setAxisMaxValue(1200f);
        yl.setDrawGridLines(true);


        YAxis y2 = mChart.getAxisRight();
        y2.setEnabled(false);

    }

    @Override
    protected void onResume(){
        super.onResume();
        //simulate realtime data addtion

        new Thread(new Runnable() {
            @Override
            public void run() {
                //add 100 entries
                for (int i = 0; i<100; i++){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            addEntry();//chart is notified of update addentry method

                        }
                    });

                    //pause between adds
                    try {
                        Thread.sleep(600);
                    }catch (InterruptedException e){
                        //manage error
                    }
                }
            }
        });
    }



    //create method to add entry to the line chart
    private  void  addEntry(){
        LineData data = mChart.getData();

        if (data != null){
            LineDataSet set = (LineDataSet) data.getDataSetByIndex(0);

            if (set== null){
                //creation if null
                set = createSet();
                data.addDataSet(set);
            }

            //add a new random values
            data.addXValue("");
            data.addEntry(new Entry((float) (Math.random()*75)+60f, set.getEntryCount()), 0);

            //notify chart dat have chaneged
            mChart.notifyDataSetChanged();;

            //limit no of visible entries
            mChart.setVisibleXRange(6,0);

            //mscroll to the LAst entry
            mChart.moveViewToX(data.getXValCount()-7);
        }
    }

    //method to createSet
    private LineDataSet createSet(){
        LineDataSet set = new LineDataSet(null, "SPL Db");
        set.setDrawCubic(true);
        set.setCubicIntensity(0.2f);
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setColor(ColorTemplate.getHoloBlue());
        set.setLineWidth(2f);
        set.setCircleSize(4f);
        set.setFillAlpha(65);
        set.setFillColor(ColorTemplate.getHoloBlue());
        set.setHighLightColor(Color.rgb(244,117,177));
        set.setValueTextColor(Color.WHITE);
        set.setValueTextSize(10f);

        return set;
    }
}
