package com.example.carshareprototype;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.awt.font.TextAttribute;

public class ProfilePage extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;
    String user_id;
    DatabaseReference reference;
    TextView t13,t14;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        auth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            Intent myintent = new Intent(ProfilePage.this,MainActivity.class);
            startActivity(myintent);
            finish();
        }
        else
        {
            user_id = user.getUid();
            reference = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String email = snapshot.child("email").getValue(String.class);
                    String name = snapshot.child("name").getValue(String.class);
                    t13 = findViewById(R.id.textView13);
                    t14 = findViewById(R.id.textView14);
                    t13.setText(email);
                    t14.setText(name);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
    }
}