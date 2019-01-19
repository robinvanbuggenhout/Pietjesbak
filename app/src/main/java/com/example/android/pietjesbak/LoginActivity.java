package com.example.android.pietjesbak;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText userMail, userPassword;
    private Button btnLogin, btnLogin2;
    private FirebaseAuth mAuth;
    private Intent SecondActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userMail = findViewById(R.id.logMail);
        userPassword = findViewById(R.id.logPassword);
        btnLogin = findViewById(R.id.logBtn);
        btnLogin2 = findViewById(R.id.logBtn2);
        mAuth = FirebaseAuth.getInstance();
        SecondActivity = new Intent(this, com.example.android.pietjesbak.SecondActivity.class);

        btnLogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent registerActivity = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(registerActivity);
                finish();

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnLogin.setVisibility(View.INVISIBLE);

                final String mail = userMail.getText().toString();
                final String password = userPassword.getText().toString();

                if (mail.isEmpty() || password.isEmpty()){
                    showMessage("Please Verify All Fields");
                    btnLogin.setVisibility(View.VISIBLE);
                }

                else {
                    signIn(mail, password);
                }

            }
        });


    }

    private void signIn(String mail, String password) {

        mAuth.signInWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()) {
                    btnLogin.setVisibility(View.VISIBLE);
                    updateUI();

                }
                else {
                    showMessage(task.getException().getMessage());
                    btnLogin.setVisibility(View.VISIBLE);
                }

            }
        });

    }

    private void updateUI() {

        startActivity(SecondActivity);
        finish();

    }

    private void showMessage(String text) {

        Toast.makeText(getApplicationContext(),text, Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null){
            //user is already connected
            //redirect him to mainactivty
            showMessage("User recognized");
            updateUI();
        }

    }
}
