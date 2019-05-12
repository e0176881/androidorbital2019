package com.example.orbital2019.basic;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.example.orbital2019.R;

public class BasicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Android Basic");

        // choose which screen u want to show first.
        displaySelectedScreen(new RelativeLayout());
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.Relative:
                        fragment = new RelativeLayout();
                        break;
                    case R.id.Linear:
                        fragment = new LinearLayout();
                        break;
                    case R.id.Widgets:
                        fragment = new WidgetsLayout();
                        break;
                }
                return displaySelectedScreen(fragment);
            }
        });
    }

    private boolean displaySelectedScreen(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
    // Set back button to finish activity
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }


}