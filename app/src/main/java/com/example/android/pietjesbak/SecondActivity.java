package com.example.android.pietjesbak;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    //Buttons
    Button btn;

    // EditText
    EditText et, et2;

    // String
    String st, st2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btn = findViewById(R.id.button2);

        et = findViewById(R.id.edittext);
        et2 = findViewById(R.id.edittext2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SecondActivity.this,MainActivity.class);
                st = et.getText().toString();
                st2 = et2.getText().toString();
                i.putExtra("Value",st);
                i.putExtra("Value2",st2);
                startActivity(i);
                finish();
            }
        });


    }

    public void logout(View view) {
      Intent intent = new Intent(SecondActivity.this, LoginActivity.class);
      startActivity(intent);
    }

}
