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

public class SignInActivity extends AppCompatActivity {

    //getting values from email and password edit text boxes
    EditText e1_email,e2_password;
    //adding firebase auth to project
    FirebaseAuth auth;
    //progress
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        //will grab values within these given ids
        e1_email = findViewById(R.id.editTextTextEmailAddress);
        e2_password = findViewById(R.id.editTextTextPassword);
        //initialize firebase auth + progress dialog
        auth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(this);

    }

    public void signinUser(View v)
    {
        dialog.setMessage("Please wait. Signing in");
        dialog.show();

        //if the fields are empty it will give a message telling user that the field is empty
        //else it will authenticate user with firebase
        if(e1_email.getText().toString().equals("") || e2_password.getText().toString().equals(""))
        {
            Toast.makeText(getApplicationContext(),"Please make sure the field is not empty",Toast.LENGTH_SHORT).show();
        }
        else
        {
            auth.signInWithEmailAndPassword(e1_email.getText().toString(),e2_password.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                dialog.hide();
                                Toast.makeText(getApplicationContext(), "Successfully signed in", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(SignInActivity.this,MainScreenActivity.class);
                                startActivity(i);
                                finish();
                            }
                            else
                            {
                                dialog.hide();
                                Toast.makeText(getApplicationContext(),"User not found",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }

    }


}