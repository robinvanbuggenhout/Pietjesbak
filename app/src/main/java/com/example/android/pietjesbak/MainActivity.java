package com.example.android.pietjesbak;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

//SPELREGELS
//GOOIEN: eerste keer met drie dobbelstenen, nadien kan je kiezen met welke dobbelstenen je verder gooit
//  -> Je moet dobbelstenen kunnen 'vast' zetten
//STREEPJES: je begint met vijf streepjes, indien je een ronde wint mag je een streepje wegdoen
//  -> Streepje aftrekken van de winnaar en nadien het totaal terug tonen
//PUNTEN: 1 oog = 100 punten, 6 ogen = 60 punten, 5, 4, 3, 2 ogen = 5, 4, 3, 2 punten
//  -> Optellen met een case-statement. Indien 1 = 100 punten, indien 6 = 60 punten
//  -> Drie apen: 1,1,1, Soixante-neuf: 6,5,4, Zand: driemaal dezelfde waarde
//  -> Extra: indien het gelijk is moet er nogmaals gegooid worden
//  -> Extra: indien 1,1,1 dan wint deze speler automatisch
//PASS: de beurt gaat over naar de andere speler

public class MainActivity extends AppCompatActivity {

    //DECLARATIE
    //Speler 1
    TextView naamSpeler1, puntenSpeler1, beurtSpeler1, worpSpeler1, worp2Speler1, worp3Speler1;

    //Speler 2
    TextView naamSpeler2, puntenSpeler2, beurtSpeler2, worpSpeler2, worp2Speler2, worp3Speler2;

    //Buttons
    Button pas, werp;

    TextView tv;
    String st;

    //Dobbelstenen
    CheckBox dobbelsteen1, dobbelsteen2, dobbelsteen3;

    private RadioGroup radioGroup;
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.textView);
        st = getIntent().getExtras().getString("Value");
        tv.setText(st);

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
        //De beurt gaat naar de andere speler
        pas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }); */
    }



<<<<<<< HEAD

    int ogen = 1;

    //Indien je op 'werp' klikt gebeurt dit
    public void generate(View view) {
        if (ogen == 1) { }


    // int ogen = 1;
=======
    // int ogen = 1;


    //Indien je op 'werp' klikt gebeurt dit
    public void generate(View view) {
>>>>>>> 5e270a3f97ddf50b7f5665ba099a7052d9327ea3

        Random rand = new Random();
        int number = rand.nextInt(6) + 1;
        int number2 = rand.nextInt(6) + 1;
        int number3 = rand.nextInt(6) + 1;

        TextView myText = (TextView) findViewById(R.id.t_aantal);
        TextView myText2 = (TextView) findViewById(R.id.t_aantal2);
        TextView myText3 = (TextView) findViewById(R.id.t_aantal3);

        String myString = String.valueOf(number);
        String myString2 = String.valueOf(number2);
        String myString3 = String.valueOf(number3);

        myText.setText(myString);
        myText2.setText(myString2);
        myText3.setText(myString3);
    }

        /* if (ogen == 1) {
            Random rand = new Random();

            int number = rand.nextInt(6)+1;
            int number2 = rand.nextInt(6)+1;
            int number3 = rand.nextInt(6)+1;

            TextView myText = (TextView)findViewById(R.id.t_aantal1);
            TextView myText2 = (TextView)findViewById(R.id.t_aantal2);
            TextView myText3 = (TextView)findViewById(R.id.t_aantal3);

            TextView myText = (TextView)findViewById(R.id.t_aantal);
            String myString = String.valueOf(number);
            String myString2 = String.valueOf(number2);
            String myString3 = String.valueOf(number3);

            myText.setText(myString);
            myText2.setText(myString2);
            myText3.setText(myString3);

        }
    }*/

    public void checkButton(View view) {

    }*/
}
}
