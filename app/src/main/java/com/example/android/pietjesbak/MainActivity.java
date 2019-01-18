package com.example.android.pietjesbak;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //DECLARATIE
    //Speler 1
    TextView naamSpeler1, puntenSpeler1, beurtSpeler1, worpSpeler1, worp2Speler1, worp3Speler1;

    //Speler 2
    TextView naamSpeler2, puntenSpeler2, beurtSpeler2, worpSpeler2, worp2Speler2, worp3Speler2;

    //Buttons
    Button pas, werp;

    //Dobbelstenen
    CheckBox dobbelsteen1, dobbelsteen2, dobbelsteen3;

    private RadioGroup radioGroup;
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Speler 1
        // naamSpeler1 = (TextView) findViewById(R.id.naamSpeler1);
        puntenSpeler1 = (TextView) findViewById(R.id.score);
        beurtSpeler1 = (TextView) findViewById(R.id.beurt);
        worpSpeler1 = (TextView) findViewById(R.id.t_aantal);
        worp2Speler1 = (TextView) findViewById(R.id.t_aantal2);
        worp3Speler1 = (TextView) findViewById(R.id.t_aantal3);

        //Speler 2
        // naamSpeler2 = (TextView) findViewById(R.id.naamSpeler2);
        puntenSpeler2 = (TextView) findViewById(R.id.score2);
        beurtSpeler2 = (TextView) findViewById(R.id.beurt);
        worpSpeler2 = (TextView) findViewById(R.id.t_aantal);
        worp2Speler2 = (TextView) findViewById(R.id.t_aantal2);
        worp3Speler2 = (TextView) findViewById(R.id.t_aantal3);

        //Buttons
        // pas = (Button) findViewById(R.id.pass);
        // werp = (Button) findViewById(R.id.werp);

        //Dobbelstenen
        // dobbelsteen1 = (CheckBox) findViewById(R.id.dobbelsteen1);
        // dobbelsteen2 = (CheckBox) findViewById(R.id.dobbelsteen2);
        // dobbelsteen3 = (CheckBox) findViewById(R.id.dobbelsteen3);

        //Indien je op 'werp' klikt gebeurt dit
        /* werp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }

        });

        //Indien je op 'pass' klikt gebeurt dit
        pas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }); */


    }

    //Indien je op 'werp' klikt gebeurt dit
    public void generate(View view) {
        Random rand = new Random();
        int number = rand.nextInt(6)+1;
        TextView myText = (TextView)findViewById(R.id.t_aantal);
        String myString = String.valueOf(number);
        myText.setText(myString);
    }

    public void checkButton(View view) {
        
    }
}
