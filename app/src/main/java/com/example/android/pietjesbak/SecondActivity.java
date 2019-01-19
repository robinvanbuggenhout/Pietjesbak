package com.example.android.pietjesbak;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    //Buttons
    Button btn;

    // EditText
    EditText et;

    // String
    String st;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btn = findViewById(R.id.button);

        et = findViewById(R.id.edittext);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SecondActivity.this,MainActivity.class);
                st = et.getText().toString();
                i.putExtra("value",st);
                startActivity(i);
                finish();
            }
        });


    }
}
