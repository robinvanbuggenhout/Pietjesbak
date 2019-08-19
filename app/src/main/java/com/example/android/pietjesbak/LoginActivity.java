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

    // Aanmaken van variabelen
    private EditText userMail, userPassword;
    private Button btnLogin, btnLogin2;
    private FirebaseAuth mAuth;
    private Intent SecondActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Login scherm tonen
        setContentView(R.layout.activity_login);

        // De velden van de layout in een variabele zetten
        userMail = findViewById(R.id.logMail);
        userPassword = findViewById(R.id.logPassword);
        btnLogin = findViewById(R.id.logBtn);
        btnLogin2 = findViewById(R.id.logBtn2);

        mAuth = FirebaseAuth.getInstance();
        SecondActivity = new Intent(this, com.example.android.pietjesbak.SecondActivity.class);

        // Indien je nog geen account hebt ga je naar het registreerscherm
        btnLogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerActivity = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(registerActivity);
                finish();

            }
        });

        // Als je op de login knop klikt
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // De waarde vanuit de ingevulde velden halen
                // Set a variable to final, to prevent it from being overridden/modified
                final String mail = userMail.getText().toString().trim();
                final String password = userPassword.getText().toString().trim();

                // Check of alle velden zijn ingevuld
                // Trim() -> de onnodige spaties voor en achter de ingevulde tekst wegdoen
                if (mail.trim().isEmpty() || password.trim().isEmpty()){
                    showMessage("Please Verify All Fields");
                }

                // Indien alle velden zijn ingevuld gebeurt er een sign in
                else {
                    signIn(mail, password);
                }

            }
        });


    }

    private void signIn(String mail, String password) {

        mAuth.signInWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            //signInWithEmailAndPassword neemt email en paswoord en valideert deze. daarna logt hij de gebruiker in via deze methode.
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                // Indien het inloggen gelukt is gaat de app door naar het volgende scherm
                if(task.isSuccessful()) {
                    updateUI();
                }

                // Indien het niet gelukt is stuurt hij een error message
                else {
                    showMessage(task.getException().getMessage());
                }

            }
        });

    }

    // Verder gaan naar het scherm om de namen in te vullen
    private void updateUI() {
        startActivity(SecondActivity);
        finish();
    }

    private void showMessage(String text) {

        Toast.makeText(getApplicationContext(),text, Toast.LENGTH_LONG).show();

    }
}
