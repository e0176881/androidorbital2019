package com.example.orbital2019;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.orbital2019.intermediate.IntermediateActivity;
import com.example.orbital2019.tabs.TabsActivity;
import com.google.firebase.auth.FirebaseAuth;


public class HomeFragment extends Fragment {

    private FirebaseAuth mAuth;
    TextView userEmailTV;
    Button beginnerBtn, intermediateBtn, moreStuffBtn;


        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            getActivity().setTitle("Home Screen");
            mAuth = FirebaseAuth.getInstance();



            userEmailTV = view.findViewById(R.id.userEmail);

            beginnerBtn = view.findViewById(R.id.beginner_button);
            intermediateBtn = view.findViewById(R.id.intermediate_button);
            moreStuffBtn = view.findViewById(R.id.more_stuff_button);


            // on logged in
            if(mAuth.getCurrentUser()!=null) {
                userEmailTV.setText("Welcome " + mAuth.getCurrentUser().getEmail());
            }



            // Move to DetailList
            intermediateBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(getContext(), IntermediateActivity.class);

                    startActivityForResult(intent, 0);

                }
            });

            moreStuffBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(getContext(), TabsActivity.class);

                    startActivityForResult(intent, 0);

                }
            });


        }

        public HomeFragment() {
            // Required empty public constructor
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            return inflater.inflate(R.layout.fragment_home, container, false);

        }


    }
