package com.example.android.pietjesbak;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterActivity extends AppCompatActivity {

    // Aanmaken van variabelen
    private EditText userEmail, userPassword, userPassword2, userName;
    private Button regBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Register scherm tonen
        setContentView(R.layout.activity_register);

        // De velden van de layout in een variabele zetten
        userEmail = findViewById(R.id.regMail);
        userPassword = findViewById(R.id.regPassword);
        userPassword2 = findViewById(R.id.regPassword2);
        userName = findViewById(R.id.regName);
        regBtn = findViewById(R.id.regBtn);
        mAuth = FirebaseAuth.getInstance();

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regBtn.setVisibility(View.INVISIBLE);

                // De waarde vanuit de ingevulde velden halen
                final String email = userEmail.getText().toString();
                final String password = userPassword.getText().toString();
                final String password2 = userPassword2.getText().toString();
                final String name = userName.getText().toString();

                // Check of alle velden zijn ingevuld
                // Trim() -> de onnodige spaties voor en achter de ingevulde tekst wegdoen
                if (email.trim().isEmpty() || name.trim().isEmpty() || password.trim().isEmpty() || password2.trim().isEmpty() || !password.equals(password2)){

                    //Display error message: all fields must be filled
                    showMessage("Please verifiy all fields");
                }

                else {
                    //Everything is ok and all fields are filled
                    //CreateUserAccount method will try to create the user if the email is valid
                    CreateUserAccount(email,name,password);
                }



            }
        });

    }

    private void CreateUserAccount(String email, final String name, String password) {
        //This method create user account with email and password

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            //user account created succesfully
                            showMessage("Account created");

                            //update profile information
                            updateUserInfo(name, mAuth.getCurrentUser());
                            //getCurrentUser is methode die gegevens/data van ingelogde gebruiker opvraagd

                        }

                        else {
                            //account creation failed
                            showMessage("Account creation failed" + task.getException().getMessage());
                        }
                    }
                });

    }

    private void updateUserInfo(String name, FirebaseUser currentUser) {
        UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build();

        currentUser.updateProfile(profileUpdate)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // user info updated succesfully
                            showMessage("Register Complete");
                            updateUI();
                        }
                    }
                });

    }

    // Verder gaan naar het scherm om de namen in te vullen
    private void updateUI() {
        Intent SecondActivity = new  Intent(getApplicationContext(),SecondActivity.class);
        startActivity(SecondActivity);
        finish();
    }

    //Method to show error message
    private void showMessage(String message) {

        Toast.makeText(getApplicationContext(),message, Toast.LENGTH_LONG).show();

    }
}
