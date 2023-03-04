package com.example.carshareprototype;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.ui.AppBarConfiguration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainScreenActivity extends AppCompatActivity {



    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        auth = FirebaseAuth.getInstance();

    }


    public void ListRide(View v)
    {
        Intent i = new Intent(MainScreenActivity.this, ride.class);
        startActivity(i);
    }

    public void SearchListings(View v)
    {
        Intent i = new Intent(MainScreenActivity.this, ListingsPage.class);
        startActivity(i);
    }

    public void ProfileView(View v)
    {
        Intent i = new Intent(MainScreenActivity.this, ProfilePage.class);
        startActivity(i);
    }

    public void open_signout(View v)
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user!=null)
        {
            auth.signOut();

            Intent thisintent = new Intent (MainScreenActivity.this, MainActivity.class);
            startActivity(thisintent);
            finish();
        }
        else{
            Toast.makeText(getApplicationContext(), "User is already logged out", Toast.LENGTH_SHORT).show();
        }
    }



}