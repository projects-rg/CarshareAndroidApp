package com.example.carshareprototype;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends AppCompatActivity {

    //creating variables to be used throughout the activity
    EditText e1_name,e2_email,e3_phone,e4_password;
    FirebaseAuth auth;
    ProgressDialog dialog;
    DatabaseReference UsersDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);

        //initialization of the variables that were created above
        e1_name = findViewById(R.id.editTextTextPersonName);
        e2_email = findViewById(R.id.editTextTextEmailAddress2);
        e3_phone = findViewById(R.id.editTextPhone);
        e4_password = findViewById(R.id.editTextTextPassword2);
        auth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(this);
        UsersDb = FirebaseDatabase.getInstance().getReference().child("Users");
        FirebaseUser firebaseUser = auth.getCurrentUser();

    }

    //function when user clicks button
    public void signUpUser(View v) {
        dialog.setMessage("Please wait. Registering.");
        dialog.show();

        String name = e1_name.getText().toString();
        String email = e2_email.getText().toString();
        String phone = e3_phone.getText().toString();
        String password = e4_password.getText().toString();


        if (name.equals("") || email.equals("") || phone.equals("") || password.equals("")) {
            Toast.makeText(getApplicationContext(), "Ensure no empty fields", Toast.LENGTH_SHORT).show();
        } else {
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                dialog.hide();
                                Toast.makeText(getApplicationContext(), "Successfully Registered", Toast.LENGTH_SHORT).show();
                                Users users = new Users(name,email,phone,password);
                                FirebaseUser firebaseUser = auth.getCurrentUser();
                                UsersDb.child(firebaseUser.getUid()).setValue(users);
                                setContentView(R.layout.activity_main);
                            } else {
                                dialog.hide();
                                Toast.makeText(getApplicationContext(), "User unable to be registered", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}