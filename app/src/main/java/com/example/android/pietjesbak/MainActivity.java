package com.example.android.pietjesbak;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DECLARATIE
        //Speler 1
        TextView naamSpeler1 = (TextView) findViewById(R.id.naamSpeler1);
        TextView puntenSpeler1 = (TextView) findViewById(R.id.puntenSpeler1);
        TextView beurtSpeler1 = (TextView) findViewById(R.id.beurtSpeler1);
        TextView worpSpeler1 = (TextView) findViewById(R.id.worpSpeler1);

        //Speler 2
        TextView naamSpeler2 = (TextView) findViewById(R.id.naamSpeler2);
        TextView puntenSpeler2 = (TextView) findViewById(R.id.puntenSpeler2);
        TextView beurtSpeler2 = (TextView) findViewById(R.id.beurtSpeler2);
        TextView worpSpeler2 = (TextView) findViewById(R.id.worpSpeler2);

        //Buttons
        Button buttonPass = (Button) findViewById(R.id.button_pass);
        Button buttonWerp = (Button) findViewById(R.id.button_werp);

        //Dobbelstenen
        CheckBox dobbelsteen1 = (CheckBox) findViewById(R.id.dobbelsteen1);
        CheckBox dobbelsteen2 = (CheckBox) findViewById(R.id.dobbelsteen2);
        CheckBox dobbelsteen3 = (CheckBox) findViewById(R.id.dobbelsteen3);

    }
}
