package com.databox.onlinebookshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class registration extends AppCompatActivity {

    private EditText textName , textEmail , textPassword , textRePassword;
    private Button buttonSignUp , buttonAleadyHvAccount;
    ProgressBar progressBar;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        textName =(EditText) findViewById(R.id.txtFullName);
        textEmail = (EditText) findViewById(R.id.txtEmailSignUp);
        textPassword = (EditText) findViewById(R.id.txtPasswordSignUp);
        textRePassword = (EditText) findViewById(R.id.txtRePasswordSignUp);
        buttonSignUp = (Button) findViewById(R.id.buttonSignUp);
        buttonAleadyHvAccount = (Button) findViewById(R.id.alreadyAccount);
        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = textEmail.getText().toString().trim();
                String password = textPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    textEmail.setError("Email is Required!");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    textPassword.setError("Password is Required");
                    return;
                }

                if (password.length() < 7 ) {
                    textPassword.setError("Password must be at least 7 characters");
                    return;
                }

                if (password.length() <7){
                    textRePassword.setError("Password must be at least 7 Characters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                // register use in firebase
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else {
                            Toast.makeText(registration.this,"Error !"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        buttonAleadyHvAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),login.class));
            }
        });

    }
}