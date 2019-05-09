package com.example.orbital2019;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;


public class HomeFragment extends Fragment {

    private FirebaseAuth mAuth;
    TextView textView;
        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            mAuth = FirebaseAuth.getInstance();


            textView = (TextView) getActivity().findViewById(R.id.userEmail);
            if(mAuth.getCurrentUser()!=null) {
                textView.setText("Welcome " + mAuth.getCurrentUser().getEmail());
            }

        }

        public HomeFragment() {
            // Required empty public constructor
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            return inflater.inflate(R.layout.activity_home, container, false);

        }


    }
