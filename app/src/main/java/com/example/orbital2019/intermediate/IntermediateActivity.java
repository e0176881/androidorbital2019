package com.example.orbital2019.intermediate;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Movie;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.orbital2019.R;
import com.example.orbital2019.intermediate.model.UserDetails;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class IntermediateActivity extends AppCompatActivity {


    private List<UserDetails> userDetailsList = new ArrayList<UserDetails>();
    private ListView listView;
    private DetailsAdapter detailsAdapter;

    private EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermediate);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("View Users");


        detailsAdapter = new DetailsAdapter(this, userDetailsList);

        listView =  findViewById(R.id.details_list_view);
        searchEditText = findViewById(R.id.search_edit_text);


        listView.setAdapter(detailsAdapter);


        // on click listner
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                UserDetails userDetails = (UserDetails)parent.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), IntermediateUpdateUserActivity.class);
                intent.putExtra("userDetails", (Serializable) userDetails);
                intent.putExtra("magic", "testing magic");
                startActivity(intent);
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
                else
                    loadUserData();
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
    ListenerRegistration registration;

    private void loadUserData() {
        FirebaseFirestore fs = FirebaseFirestore.getInstance();



        registration = fs.collection(UserDetails.userDetailsKey).orderBy(UserDetails.matriculationNumberKey).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {


                List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
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

    @Override
    protected void onDestroy() {

        //Remove Listner
        registration.remove();
        super.onDestroy();
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
