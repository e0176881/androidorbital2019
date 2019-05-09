package com.example.orbital2019;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;


import com.example.orbital2019.intermediate.auth.LoginFragment;
import com.example.orbital2019.intermediate.auth.RegisterFragment;
import com.google.firebase.auth.FirebaseAuth;




public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private boolean isStartup = true;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        findViewById(R.id.tabs).setVisibility(View.GONE);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAuth = FirebaseAuth.getInstance();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);



        if(mAuth.getCurrentUser()!=null) {
            System.out.println(mAuth.getCurrentUser().getEmail());
            Menu navMenuLogIn = navigationView.getMenu();
            navMenuLogIn.findItem(R.id.nav_login).setVisible(false);
            navMenuLogIn.findItem(R.id.nav_register).setVisible(false);
        }
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
            Intent myIntent = new Intent(HomeActivity.this, HomeActivity.class);
            HomeActivity.this.startActivity(myIntent);
        } else if (id == R.id.nav_intro) {
            fragment = new HomeFragment();
        } else if (id == R.id.nav_register) {
            fragment = new RegisterFragment();

        } else if (id == R.id.nav_login) {
            fragment = new LoginFragment();

        } else if (id == R.id.nav_tabs) {
            Intent myIntent = new Intent(HomeActivity.this, MainActivity.class);
            HomeActivity.this.startActivity(myIntent);

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
