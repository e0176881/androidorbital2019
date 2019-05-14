package com.example.orbital2019.intermediate.model;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Modules {

    public String collectionName = "Modules";

    public String codeKey = "code";

    public String remarksKey = "remarks";
    public String marksKey = "marks";


    public String code;
    public String remarks;
    public String userDocumentId;
    public String marks;


    public Modules(String code , String remarks, String marks, String userDocumentId){

        this.code = code;
        this.remarks = remarks;
        this.marks = marks;
        this.userDocumentId = userDocumentId;
    }



    // call to update entry In database.
    public void updateEntry () {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String,Object> data = new HashMap<>();

        data.put(codeKey,code);
        data.put(remarksKey,remarks);
        data.put(marksKey,marks);

        db.collection(UserDetails.userDetailsKey).document(userDocumentId).collection(collectionName).document(code).update(data);
    }


    // call to create entry In database.
    public void createEntry () {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String,Object> data = new HashMap<>();

        data.put(codeKey,code);
        data.put(remarksKey,remarks);
        data.put(marksKey,marks);


        db.collection(UserDetails.userDetailsKey).document(userDocumentId).collection(collectionName).document(code).set(data);
    }

    public void deleteEntry() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection(UserDetails.userDetailsKey).document(userDocumentId).collection(collectionName).document(code).delete();
    }

}
