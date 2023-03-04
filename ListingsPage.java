package com.example.carshareprototype;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListingsPage extends AppCompatActivity {


    //ListView myListView;
    //List<Lists> listingList;

    FirebaseAuth auth;
    FirebaseUser user;
    String user_id;

    DatabaseReference listsDbRef;

    TextView t19, t20, t21, t22;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listings_page);


        auth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        user_id = user.getUid();

        auth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            Intent myintent = new Intent(ListingsPage.this, MainActivity.class);
            startActivity(myintent);
            finish();
        } else {
            user_id = user.getUid();
            listsDbRef = FirebaseDatabase.getInstance().getReference().child("Lists").child(user_id);
            listsDbRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String startingAddress = snapshot.child("startingAddress").getValue(String.class);
                    String destination = snapshot.child("destination").getValue(String.class);
                    String seatsAvailable = snapshot.child("seatsAvailable").getValue(String.class);
                    ;
                    String contactNumber = snapshot.child("contactNumber").getValue(String.class);
                    ;

                    t19 = findViewById(R.id.textView19);
                    t20 = findViewById(R.id.textView20);
                    t21 = findViewById(R.id.textView21);
                    t22 = findViewById(R.id.textView22);
                    t19.setText(startingAddress);
                    t20.setText(destination);
                    t21.setText(seatsAvailable);
                    t22.setText(contactNumber);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }

    }


    public void GetLocation(View v)
    {
        Intent i = new Intent(ListingsPage.this, MapsActivity.class);
        startActivity(i);
    }
}