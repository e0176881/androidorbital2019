package com.example.orbital2019.intermediate.auth;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.orbital2019.HomeFragment;
import com.example.orbital2019.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordFragment extends Fragment {

    private EditText emailEditText;
    private Button resetBtn;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Reset Password");
        mAuth = FirebaseAuth.getInstance();

        emailEditText = getActivity().findViewById(R.id.txtemail);

        resetBtn = getActivity().findViewById(R.id.btnresetpassword);
        progressBar = getActivity().findViewById(R.id.resetprogressBar);


        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetUserAccount();
            }
        });


    }

    private void resetUserAccount() {
        progressBar.setVisibility(View.VISIBLE);

        String email;
        email = emailEditText.getText().toString();


        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getActivity().getApplicationContext(), "Please enter email...", Toast.LENGTH_LONG).show();
            return;
        }



        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity().getApplicationContext(), "New password sent to email!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public ResetPasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_resetpassword, container, false);

        return view;
    }


}
