package com.example.orbital2019.intermediate;

import android.app.ActionBar;
import android.graphics.Movie;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.example.orbital2019.R;
import com.example.orbital2019.intermediate.model.UserDetails;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.List;

public class IntermediateActivity extends AppCompatActivity {


    private List<UserDetails> userDetailsList = new ArrayList<UserDetails>();
    private RecyclerView recyclerView;
    private DetailsAdapter detailsAdapter;

    private EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermediate);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.details_recycler_view);
        searchEditText = findViewById(R.id.search_edit_text);

        detailsAdapter = new DetailsAdapter(userDetailsList);


        recyclerView.setHasFixedSize(false);


        // vertical RecyclerView
        // keep movie_list_row.xml width to `match_parent`
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        // horizontal RecyclerView
        // keep movie_list_row.xml width to `wrap_content`
        // RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(mLayoutManager);

        // adding inbuilt divider line
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        // adding custom divider line with padding 16dp
        // recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(detailsAdapter);

        // row click listener
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {

            }
        });


        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!s.toString().isEmpty())
                    searchUserData(s.toString());

            }
        });


        loadUserData();
    }


    // Set back button to finish activity
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }


    private void loadUserData() {


        FirebaseFirestore fs = FirebaseFirestore.getInstance();


        fs.collection(UserDetails.userDetailsKey).orderBy(UserDetails.matriculationNumberKey).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {


                List<DocumentSnapshot> documents = task.getResult().getDocuments();

                // clean up the list to prevent double copies
                userDetailsList.removeAll(userDetailsList);

                for (DocumentSnapshot document : documents) {

                    if (document.contains(UserDetails.matriculationNumberKey) && document.contains(UserDetails.nameKey)) {

                        String matriculationNumber = (String) document.get(UserDetails.matriculationNumberKey);
                        String name = (String) document.get(UserDetails.nameKey);

                        UserDetails details = new UserDetails(matriculationNumber, name);

                        userDetailsList.add(details);
                    }
                }

                detailsAdapter.notifyDataSetChanged();
            }
        });


    }


    private void searchUserData(String filter) {


        FirebaseFirestore fs = FirebaseFirestore.getInstance();


        fs.collection(UserDetails.userDetailsKey).whereGreaterThan(UserDetails.nameKey, filter)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {


                List<DocumentSnapshot> documents = task.getResult().getDocuments();

                // clean up the list to prevent double copies
                userDetailsList.removeAll(userDetailsList);

                for (DocumentSnapshot document : documents) {

                    if (document.contains(UserDetails.matriculationNumberKey) && document.contains(UserDetails.nameKey)) {

                        String matriculationNumber = (String) document.get(UserDetails.matriculationNumberKey);
                        String name = (String) document.get(UserDetails.nameKey);

                        UserDetails details = new UserDetails(matriculationNumber, name);

                        userDetailsList.add(details);
                    }
                }

                detailsAdapter.notifyDataSetChanged();
            }
        });


    }
}