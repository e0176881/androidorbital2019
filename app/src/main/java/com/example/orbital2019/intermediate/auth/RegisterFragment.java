package com.example.orbital2019.intermediate.auth;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.orbital2019.HomeFragment;
import com.example.orbital2019.R;
import com.example.orbital2019.intermediate.model.UserDetails;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterFragment extends Fragment  {

    private EditText emailEditText, passwordEditText, matriculationNumberEditText, nameEditText;
    private Button regBtn;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Register");
        mAuth = FirebaseAuth.getInstance();

        //initialise components and look for them according to their IDs
        emailEditText = getActivity().findViewById(R.id.email);
        passwordEditText = getActivity().findViewById(R.id.password);
        matriculationNumberEditText = getActivity().findViewById(R.id.matriculation_number);
        nameEditText = getActivity().findViewById(R.id.name);

        regBtn = getActivity().findViewById(R.id.register);
        progressBar = getActivity().findViewById(R.id.progressBar);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerNewUser();
            }
        });

    }

    private void registerNewUser() {
        progressBar.setVisibility(View.VISIBLE);
        //get the actual String or text that the user type
        final String email, password, matriculationNumber, name;
        email = emailEditText.getText().toString();
        password = passwordEditText.getText().toString();
        matriculationNumber = matriculationNumberEditText.getText().toString();
        name = nameEditText.getText().toString();


        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getActivity().getApplicationContext(), "Please enter email...", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getActivity().getApplicationContext(), "Please enter password!", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(matriculationNumber)) {
            Toast.makeText(getActivity().getApplicationContext(), "Please enter Matriculation Number...", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(getActivity().getApplicationContext(), "Please enter name!", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity().getApplicationContext(), "Registration successful!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);


                            //Create user details if successful.
                            UserDetails currentUser = new UserDetails(matriculationNumber, name);
                            currentUser.createEntry();

                            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                            ft.replace(R.id.content_frame, new HomeFragment());
                            ft.commit();
                        }
                        else {

                            Log.d("testing ", "onComplete: " + task.toString());
                            Toast.makeText(getActivity().getApplicationContext(), "Registration failed!" + task.getException().toString(), Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register, container, false);

        return view;
    }


}
