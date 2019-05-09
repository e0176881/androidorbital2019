package com.example.orbital2019.intermediate.model;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class UserDetails {

    // Database Keys
    public static final String userDetailsKey = "UserDetails";
    public static final String nameKey = "Name";
    public static final String matriculationNumberKey = "MatriculationNumber";


    private String matriculationNumber;
    public String name;


    public UserDetails(String matriculationNumber , String name){

        this.matriculationNumber = matriculationNumber;
        this.name = name;

    }

    // call to update entry In database.
    public void updateEntry () {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String,Object> data = new HashMap<>();

        data.put(nameKey,name);
        data.put(matriculationNumberKey,matriculationNumber);


        db.collection(userDetailsKey).document(matriculationNumberKey).update(data);
    }


    // call to create entry In database.
    public void createEntry () {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String,Object> data = new HashMap<>();

        data.put(nameKey,name);
        data.put(matriculationNumberKey,matriculationNumber);


        db.collection(userDetailsKey).document(matriculationNumber).set(data);
    }



}
