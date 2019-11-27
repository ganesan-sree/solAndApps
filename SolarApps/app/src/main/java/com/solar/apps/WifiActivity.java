package com.solar.apps;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.LayerDrawable;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.solar.apps.wifi.Devices;

import com.solar.apps.wifi.WifiScanAdapter;
import com.thanosfisherman.wifiutils.WifiUtils;

import androidx.annotation.NonNull;

public class WifiActivity extends AppCompatActivity {

    private FragmentManager fm;
    private List<Devices> dataModels;
    private WifiScanAdapter adapter;
    private ListView listView;
    //private Button btnScan;
    private static String TAG = "WifiActivity";
    private String password = null;
    private LinearLayout linlaHeaderProgress;

    private LayerDrawable mRefreshMenuIcon;
    private MenuItem mSearchMenu, refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        linlaHeaderProgress = (LinearLayout) findViewById(R.id.linlaHeaderProgress);
        listView = (ListView) findViewById(R.id.list);
        // btnScan = (Button) findViewById(R.id.wifiScan);
        linlaHeaderProgress.setVisibility(View.VISIBLE);
        WifiUtils.withContext(getApplicationContext()).scanWifi(this::getScanResults).start();

        // btnScan.setOnClickListener(new View.OnClickListener() {
        //      @Override
        ///    public void onClick(View view) {
        //        try {
        //           awesomeButtonClicked();
        //         }
        ///       catch (Exception e){
        //           Log.d(TAG, e.getMessage());
        //        }
        //   }
        // });
    }

    private void awesomeButtonClicked() {
        //listView.setAdapter(null);
        linlaHeaderProgress.setVisibility(View.VISIBLE);
        WifiUtils.withContext(getApplicationContext()).scanWifi(this::getScanResults).start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_refresh:
                awesomeButtonClicked();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void getScanResults(@NonNull final List<ScanResult> results) {
        if (results.isEmpty()) {
            Log.i(TAG, "SCAN RESULTS IT'S EMPTY");
            return;
        }

        Log.i(TAG, "GOT SCAN RESULTS " + results);

        dataModels = new ArrayList<>();

        for (ScanResult res : results) {
            dataModels.add(new Devices(res.SSID, res.BSSID));
        }

        adapter = new WifiScanAdapter(dataModels, getApplicationContext());
        listView.setAdapter(adapter);
        linlaHeaderProgress.setVisibility(View.GONE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Devices dataModel = dataModels.get(position);
                dataModel.getName();
                showDailog(dataModel);
            }
        });

    }


    void showDailog(Devices dataModel) {
        LayoutInflater li = LayoutInflater.from(WifiActivity.this);
        View promptsView = li.inflate(R.layout.alert_wifi, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(WifiActivity.this, R.style.CustomDialogTheme);
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView.findViewById(R.id.editTextPassword);
        TextView ssidText = (TextView) promptsView.findViewById(R.id.textViewSSID);
        ssidText.setText("Connecting to " + dataModel.getName());
        // TextView security=(TextView)promptsView.findViewById(R.id.textViewSecurity);
        // security.setText("Security for Network is:\n" +d.getCapabilities());

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // get user input and set it to result
                                // edit text
                                Log.d(TAG, "Password is:" + userInput.getText());
                                password = userInput.getText().toString();
                                //  result.setText(userInput.getText());
                                //  connectWiFi(String.valueOf(d.getName()),password,d.capabilities);

                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.home, menu);
        getMenuInflater().inflate(R.menu.refresh, menu);

       // mRefreshMenuIcon = (LayerDrawable) menu.findItem(R.id.action_refresh).getIcon();
        refresh = (MenuItem) menu.findItem(R.id.action_refresh);
       // refresh.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            //@Override
           // public boolean onMenuItemClick(MenuItem item) {
          //      linlaHeaderProgress.setVisibility(View.VISIBLE);
          //      awesomeButtonClicked();
        //        return false;
        //    }
      //  });

        return super.onCreateOptionsMenu(menu);
    }

}
