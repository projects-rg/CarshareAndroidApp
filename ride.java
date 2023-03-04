package com.example.carshareprototype;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ride extends AppCompatActivity {

    FirebaseAuth auth;
    DatabaseReference ListsDb;

    EditText e1_StartingAddress,e2_Destination,e3_Seats,e4_Phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride);

        auth = FirebaseAuth.getInstance();
        ListsDb = FirebaseDatabase.getInstance().getReference().child("Lists");

        e1_StartingAddress = findViewById(R.id.editTextTextPostalAddress);
        e2_Destination= findViewById(R.id.editTextTextPostalAddress2);
        e3_Seats = findViewById(R.id.editTextNumber);
        e4_Phone = findViewById(R.id.editTextPhone2);


    }


    public void open_findrides(View v)
    {
        String startingAddress = e1_StartingAddress.getText().toString();
        String destination = e2_Destination.getText().toString();
        String seatsAvailable = e3_Seats.getText().toString();
        String contactNumber = e4_Phone.getText().toString();

        if (startingAddress.equals("") || destination.equals("") || seatsAvailable.equals("") || contactNumber.equals("")){
            Toast.makeText(getApplicationContext(),"Enter all fields", Toast.LENGTH_SHORT).show();
        } else {
            Lists lists = new Lists(startingAddress,destination,seatsAvailable,contactNumber);
            FirebaseUser firebaseUser = auth.getCurrentUser();
            ListsDb.child(firebaseUser.getUid()).setValue(lists);
            Intent i = new Intent(ride.this, MainScreenActivity.class);
            startActivity(i);
        }
    }
}