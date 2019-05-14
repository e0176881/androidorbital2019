package com.example.orbital2019;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.orbital2019.basic.BasicActivity;
import com.example.orbital2019.intermediate.IntermediateActivity;
import com.example.orbital2019.extras.ExtrasActivity;
import com.google.firebase.auth.FirebaseAuth;


public class HomeFragment extends Fragment {

    private FirebaseAuth mAuth;
    TextView userEmailTV;
    Button basicBtn, intermediateBtn, moreStuffBtn;


        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            getActivity().setTitle("Home Screen");
            mAuth = FirebaseAuth.getInstance();



            userEmailTV = view.findViewById(R.id.userEmail);

            basicBtn = view.findViewById(R.id.basic_button);
            intermediateBtn = view.findViewById(R.id.intermediate_button);
            moreStuffBtn = view.findViewById(R.id.more_stuff_button);


            // on logged in
            if(mAuth.getCurrentUser()!=null) {
                userEmailTV.setText("Welcome " + mAuth.getCurrentUser().getEmail());
            }

            // Move to Android Basic on button press
            basicBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(getContext(), BasicActivity.class);

                    startActivityForResult(intent, 0);

                }
            });

            // Move to Android Intermediate on button press
            intermediateBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(getContext(), IntermediateActivity.class);

                    startActivityForResult(intent, 0);

                }
            });

            // Move to more stuffs tap on button press
            moreStuffBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(getContext(), ExtrasActivity.class);

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
