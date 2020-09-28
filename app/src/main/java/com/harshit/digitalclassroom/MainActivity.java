package com.harshit.digitalclassroom;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.harshit.digitalclassroom.fragment.Homepage;
import com.harshit.digitalclassroom.utils.SharedPreferenceValue;
import com.infideap.drawerbehavior.Advance3DDrawerLayout;

import java.util.Objects;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    TextView selectViewDefault;
    SwipeRefreshLayout swipeRefreshLayout;
    int checkedItem = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setActionAndDrawable();

        //Initilize
        selectViewDefault = findViewById(R.id.typeSelect);


        if(SharedPreferenceValue.getDefaultView(getApplicationContext()).equals("0"))
            checkedItem =0;
        else
            checkedItem = 1;




        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light)
        );

//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                swipeRefreshLayout.setRefreshing(true);
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.container, new Homepage(getApplicationContext(),MainActivity.this))
//                        .addToBackStack(null)
//                        .commit();
//            }
//
//        });

        final String option[] = {"Student", "Teacher"};



        selectViewDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder selectDefaultViewDialogue = new AlertDialog.Builder(MainActivity.this);
                selectDefaultViewDialogue.setTitle("Select Default View")
                        .setSingleChoiceItems(option, checkedItem, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                Toast.makeText(getApplicationContext(), String.valueOf(i), Toast.LENGTH_LONG).show();

                            }
                        })
                        .setPositiveButton("SET", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .create().show();
                ;


            }
        });


//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        Fragment fragment = new Homepage(getApplicationContext(), MainActivity.this);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();

    }

    //set action and drawable
    private void setActionAndDrawable() {
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (Advance3DDrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((Advance3DDrawerLayout) drawer).setViewRotation(GravityCompat.START, 15);
        //((Advance3DDrawerLayout) drawer).setRadius(GravityCompat.START, 25);
        ((Advance3DDrawerLayout) drawer).setViewScale(GravityCompat.START, 0.9f); //set height scale for main view (0f to 1f)
        ((Advance3DDrawerLayout) drawer).setViewElevation(GravityCompat.START, 20); //set main view elevation when drawer open (dimension)
        ((Advance3DDrawerLayout) drawer).setViewScrimColor(GravityCompat.START, Color.TRANSPARENT); //set drawer overlay coloe (color)
        ((Advance3DDrawerLayout) drawer).setDrawerElevation(25f); //set drawer elevation (dimension)
        ((Advance3DDrawerLayout) drawer).setContrastThreshold(3); //set maximum of contrast ratio between white text and background color.
        ((Advance3DDrawerLayout) drawer).setRadius(GravityCompat.START, 25); //set end container's corner radius (dimension)

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectFragment = new Homepage(getApplicationContext(), MainActivity.this);
            switch (item.getItemId()) {
                case R.id.nav_home:
                    selectFragment = new Homepage(getApplicationContext(), MainActivity.this);
                    break;
//                case R.id.nav_create_task:
//                    selectFragment = new CreateTask(getApplicationContext(), MainActivity.this);
//                    break;

                case R.id.account:
                    selectFragment = new Homepage();
                    break;
            }
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, selectFragment)
                    .addToBackStack(null)
                    .commit();
            return true;
        }
    };



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.nav_profile :
                SharedPreferenceValue.updatToken(getApplicationContext(),"");
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                break;

            case R.id.nav_logout :
                SharedPreferenceValue.updatToken(getApplicationContext(),"");
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                break;


        }
        return false;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        } else {
            new AlertDialog.Builder(MainActivity.this)
                    .setMessage("Do You Want to exit")
                    .setIcon(R.drawable.logo)
                    .setTitle(R.string.app_name)
                    .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                            System.exit(0);

                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    })
                    .create().show();
        }
    }

}
