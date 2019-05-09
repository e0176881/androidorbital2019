package com.example.orbital2019;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.support.v4.app.*;
import android.widget.FrameLayout;

import com.example.orbital2019.intermediate.auth.LoginFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private boolean isStartup = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        ViewPager pager = (ViewPager) findViewById(R.id.viewpager);

        tabpageAdapter tabPagerAdapter = new tabpageAdapter(getSupportFragmentManager());
        pager.setAdapter(tabPagerAdapter);

        tabLayout.setupWithViewPager(pager);



        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();
        Fragment fragment = null;
        Bundle bundle = new Bundle();
        if(isStartup) {
            ((FrameLayout) findViewById(R.id.content_frame)).removeAllViews();
            isStartup = false;
        }
        if (id == R.id.nav_home) {
            Intent myIntent = new Intent(MainActivity.this, HomeActivity.class);
            MainActivity.this.startActivity(myIntent);
        } else if (id == R.id.nav_intro) {
            fragment = new HomeFragment();
        } else if (id == R.id.nav_login) {
            fragment = new LoginFragment();
        } else if (id == R.id.nav_logout) {
            Intent myIntent = new Intent(MainActivity.this, MainActivity.class);
            MainActivity.this.startActivity(myIntent);
    }
        if (fragment != null) {

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
