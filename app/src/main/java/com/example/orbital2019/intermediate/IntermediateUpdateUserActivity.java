package com.example.orbital2019.intermediate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.orbital2019.R;
import com.example.orbital2019.intermediate.model.UserDetails;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.List;

public class IntermediateUpdateUserActivity extends AppCompatActivity {


    private List<UserDetails> userDetailsList = new ArrayList<UserDetails>();
    private ListView listView;
    private DetailsAdapter detailsAdapter;

    private EditText name;
    private TextView matricNo;
    private Button updateButton, deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermediateupdateuser);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Update/Delete Users");


        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        final UserDetails userDetails = (UserDetails) bundle.getSerializable("userDetails");

        name = findViewById(R.id.updateName);
        name.setText(userDetails.getName());
        matricNo = findViewById(R.id.viewUserMatric);
        matricNo.setText(userDetails.getMatriculationNumber());

        updateButton = findViewById(R.id.buttonUpdateUser);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                userDetails.name = name.getText().toString();
                userDetails.updateEntry();
                Toast.makeText(getApplicationContext(), "User updated successfully!", Toast.LENGTH_LONG).show();
                // finish();
                Intent intent = new Intent(getApplicationContext(), IntermediateActivity.class);

                startActivityForResult(intent, 0); //looks like it will only update the view

            }
        });

        deleteButton = findViewById(R.id.buttonDeleteUser);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                userDetails.deleteEntry();
                Toast.makeText(getApplicationContext(), "User deleted successfully!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), IntermediateActivity.class);

                startActivityForResult(intent, 0); //looks like it will only update the view

            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}
