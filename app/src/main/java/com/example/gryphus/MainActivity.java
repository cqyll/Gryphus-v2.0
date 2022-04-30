package com.example.gryphus;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //removed default action bar, use @toolbar as default action bar

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout); // allows listeners to be set for open/close events.

        // bottom bar functionality

        BottomNavigationView bottomNav = findViewById(R.id.bottom_bar);
        NavigationBarView.OnItemSelectedListener bottomNavListener = new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottom_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new HomeFragment()).addToBackStack(null).commit();
                        break;
                    case R.id.bottom_search:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new SearchFragment()).addToBackStack(null).commit();
                        break;
                    case R.id.bottom_watchlist:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new WatchlistFragment()).addToBackStack(null).commit();
                        break;
                }
                return true;
            }

        };

        bottomNav.setOnItemSelectedListener(bottomNavListener);

        // updates nav drawer header

        NavigationView navigationView = findViewById(R.id.nav_view);

        // handles clicks on navigation drawer
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorWhite));
        toggle.syncState();


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new LoginFragment()).addToBackStack(null).commit();
            navigationView.setCheckedItem(R.id.nav_login);
        }

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_aboutus:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AboutUsFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_login:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new LoginFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SettingsFragment()).addToBackStack(null).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // conditional on `onBackPressed` method to not exit activity if drawer is open. Instead, it will close the drawer
    // used .START as menu is on left side of container
    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else if (!(count == 0)) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }

    }
}
