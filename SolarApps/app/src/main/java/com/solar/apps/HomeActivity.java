package com.solar.apps;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.LayerDrawable;

import android.os.Bundle;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import java.io.UnsupportedEncodingException;




public class HomeActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    private LayerDrawable mCartMenuIcon;
    private MenuItem mSearchMenu, cart;
    private int mCartCount;
    Button search;
    private RecyclerView recyclerView;
    private ProgressDialog mProgressDialog;
    String categoryFile = null;
    public static long countproductoncart = 0;
    RelativeLayout notificationCount1;
    private static final String TAG = "HomeActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                //Log.e(key, "getting push notification");
                if (key.equals("AnotherActivity") && getIntent().getExtras().getString(key).equals("true")) {
                    Intent intent = new Intent(this, AnotherActivity.class);
                    intent.putExtra("message", getIntent().getExtras().getString("message"));
                    startActivity(intent);
                    finish();
                }
            }
        }

        final SharedPreferences sharedPreferences = getSharedPreferences("token", MODE_PRIVATE);
        String tok = sharedPreferences.getString("token", null);
       // if (VegUtils.isBlank(tok)) {
         //   FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            //    @Override
            //    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                //    if (!task.isSuccessful()) {
                //        Log.w(TAG, "getInstanceId failed", task.getException());
                //        return;
                //    }
                    // Get new Instance ID token
                //    String token = task.getResult().getToken();
                //    SharedPreferences.Editor editor = sharedPreferences.edit();
                //    editor.putString("token", token);
                //    editor.commit();
                //    Log.d(TAG, token);
               // }
         //   });
      //  }


        cardViewListers();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,
                toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initViews();
    }


    @Override
    public void onBackPressed() {
        //Log.e(TAG, "onBackPressed");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.home, menu);
       // getMenuInflater().inflate(R.menu.cart, menu);

       // mCartMenuIcon = (LayerDrawable) menu.findItem(R.id.action_cart).getIcon();
       // cart = (MenuItem) menu.findItem(R.id.action_cart);
       // cart.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
       //     @Override
       //     public boolean onMenuItemClick(MenuItem item) {
       //         Intent i = new Intent(HomeActivity.this, CartActivity.class);
        //        startActivity(i);
        //        return false;
       //     }
      //  });
      //  setBadgeCount(this, mCartMenuIcon, String.valueOf(countproductoncart));
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // noinspection SimplifiableIfStatement
      //  if (id == R.id.searchnavigationicon) {
       //     Intent i = new Intent(HomeActivity.this, CartActivity.class);
      //      startActivity(i);
      //      return true;
      //  }

        return super.onOptionsItemSelected(item);
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

      //  if (id == R.id.nav_gallery) {
          //  Intent i = new Intent(HomeActivity.this, CartActivity.class);
          //  startActivity(i);

      //  } else if (id == R.id.nav_order) {



     //   }

     //   else if (id == R.id.logout) {

     //       SharedPreferences sharedPreferences =getSharedPreferences("loginstate", MODE_PRIVATE);
     //       SharedPreferences.Editor editor = sharedPreferences.edit();
     //       editor.putString("islogin", "");
    //        editor.putString("userid", "");
    //        editor.putString("userData", null);
    //        editor.commit();
    //        Toast.makeText(getApplicationContext(),"Log Out Successfully", Toast.LENGTH_LONG).show();
    //        NavigationView view=(drawer.findViewById(R.id.nav_view));
           // view.getMenu().findItem(R.id.logout).setVisible(false);

     //   }

         if (id == R.id.contact_us) {
            Intent i = new Intent(HomeActivity.this, AboutUs.class);
            startActivity(i);
        }

     //   else if (id == R.id.writetous) {
      //     initChatSDK();
      //      Intent i = new Intent("com.solar.apps.chat.SplashActivity");
      //      startActivity(i);
      //  }


        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    // RECYCLE VIEW
    private void initViews() {
      //  recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
     //   recyclerView.setHasFixedSize(true);
     //   RecyclerView.LayoutManager layoutManager = new GridLayoutManager(
     //           getApplicationContext(), 1);
     //   recyclerView.setLayoutManager(layoutManager);

     //   Categorys cate = new Categorys(HomeActivity.this);
    //    HomeCategoryAdapter adapter = new HomeCategoryAdapter(
    //            getApplicationContext(),
      //          cate.getCategoryList(readCategoryFile()));
      //  recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //Log.e(TAG, "onResume");
        if(mCartMenuIcon !=null){
            ///setBadgeCount(this, mCartMenuIcon, String.valueOf(countproductoncart));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView view=(drawer.findViewById(R.id.nav_view));

        SharedPreferences sharedPreferences = getSharedPreferences("loginstate", Context.MODE_PRIVATE);
        if (sharedPreferences.getString("islogin", "").equals("1")) {
            //view.getMenu().findItem(R.id.logout).setVisible(true);
        }else{
           // view.getMenu().findItem(R.id.logout).setVisible(false);
        }
    }











    private void storeCatalog(byte[] bytes) throws UnsupportedEncodingException {
        String catalog = new String(bytes, "UTF-8");
        SharedPreferences sharedPreferences =getSharedPreferences("loginstate", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("catalog", catalog);
        editor.commit();
    }

    private String getCatalog() {
        SharedPreferences sharedPreferences = getSharedPreferences("loginstate", MODE_PRIVATE);
        String catalog = sharedPreferences.getString("catalog", null);
        return catalog;
    }


    private void cardViewListers(){

        CardView card_ble = (CardView) findViewById(R.id.ble);
        CardView card_wifi = (CardView) findViewById(R.id.wifi);
        CardView card_dashbaord = (CardView) findViewById(R.id.dashboard);
        CardView card_profile = (CardView) findViewById(R.id.profile);

        card_ble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BleActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("Name","Bluetooth");
                getApplicationContext().startActivity(intent);
            }
        });

        card_wifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WifiActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("Name","Wifi");
                getApplicationContext().startActivity(intent);
            }
        });

        card_dashbaord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DashBoardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("Name","DashBoard");
                getApplicationContext().startActivity(intent);
            }
        });



    }
}
