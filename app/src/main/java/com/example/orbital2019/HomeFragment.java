package com.example.orbital2019;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class HomeFragment extends Fragment {

    Button beginnerButton;
    Button intermediateButton;
    Button moreStuffButton;
        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            beginnerButton = (Button) view.findViewById(R.id.beginner_button);
            intermediateButton = (Button) view.findViewById(R.id.intermediate_button);
            moreStuffButton = (Button) view.findViewById(R.id.more_stuff_button);


            /// Add your stuff here
            beginnerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });


            intermediateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });

        }

        public HomeFragment() {
            // Required empty public constructor
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            return inflater.inflate(R.layout.activity_home, container, false);

        }


    }
