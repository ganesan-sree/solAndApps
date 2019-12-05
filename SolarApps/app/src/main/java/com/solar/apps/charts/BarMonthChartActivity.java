package com.solar.apps.charts;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.loopj.android.http.RequestParams;
import com.solar.apps.R;
import com.solar.apps.utility.HttpUtils;

import java.util.ArrayList;

public class BarMonthChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);
        BarChart barChart = findViewById(R.id.barchart);

        readCategoryFireBaseDatabase();

        BarDataSet barDataSet = new BarDataSet(getData(), "Inducesmile");
        barDataSet.setBarBorderWidth(0.9f);
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData barData = new BarData(barDataSet);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        final String[] months = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun"};
        IndexAxisValueFormatter formatter = new IndexAxisValueFormatter(months);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(formatter);
        barChart.setData(barData);
        barChart.setFitBars(true);
        barChart.animateXY(5000, 5000);
        barChart.invalidate();




    }


    private void readCategoryFireBaseDatabase() {
        FireBaseService f = new FireBaseService();
        try {
            f.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private ArrayList getData(){
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 30f));
        entries.add(new BarEntry(1f, 80f));
        entries.add(new BarEntry(2f, 60f));
        entries.add(new BarEntry(3f, 50f));
        entries.add(new BarEntry(4f, 70f));
        entries.add(new BarEntry(5f, 60f));
        return entries;
    }

    private class FireBaseService extends AsyncTask<String, Void, String> {

        private ProgressDialog mProgressDialog;
        String category = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mProgressDialog = new ProgressDialog(BarMonthChartActivity.this);
            mProgressDialog.show();
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage(getString(R.string.loading));
        }

        @Override
        protected String doInBackground(String... params) {
           // final UserService userService = new UserService();
            String output = null;
            try {
                HttpUtils.invokeWS(new RequestParams());
                Thread.sleep(5);
                if (output != null) {
                   // category = output.getRawBody();
                    try {
                       //(category.getBytes());
                        //Log.e("FileLoading","loading using database"+category.getBytes());
                    } catch (Exception e) {
                        Log.e("Error writing file", e.getMessage());
                    }
                }
            } catch (Exception e) {
                Log.e("error parsing ", "category json file", e);
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            // Log.e(TAG, "CAlling category done!");
            mProgressDialog.dismiss();
        }
    }
}

