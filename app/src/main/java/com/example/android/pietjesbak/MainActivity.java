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
//
//GOOIEN: eerste keer met drie dobbelstenen, nadien kan je kiezen met welke dobbelstenen je verder gooit
//  -> Je moet dobbelstenen kunnen 'vast' zetten
//STREEPJES: je begint met vijf streepjes, indien je een ronde wint mag je een streepje wegdoen
//  -> Streepje aftrekken van de winnaar en nadien het totaal terug tonen

//PASS: de beurt gaat over naar de andere speler

public class MainActivity extends AppCompatActivity {

    private Button button;

    //DECLARATIE
    //Speler 1
    TextView puntenSpeler1;
    TextView beurtAantal;
    TextView worpSpeler1;
    TextView worp2Speler1;
    TextView worp3Speler1;

    //Speler 2
    TextView puntenSpeler2, worpSpeler2, worp2Speler2, worp3Speler2;

    //Namen ingeven en meenemen naar de MainActivity
    TextView tv, tv2;
    String st, st2;

    //Nodige gegevens
    int numberOfRolls = 3;
    int punten = 0;
    boolean beurtSpeler1 = true;

    boolean volgende = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.textView);
        tv2 = findViewById(R.id.textView2);
        st = getIntent().getExtras().getString("Value");
        st2 = getIntent().getExtras().getString("Value2");
        tv.setText(st);
        tv2.setText(st2);

        //Speler 1
        // naamSpeler1 = (TextView) findViewById(R.id.naamSpeler1);
        puntenSpeler1 = (TextView) findViewById(R.id.score);
        beurtAantal = (TextView) findViewById(R.id.beurt);
        worpSpeler1 = (TextView) findViewById(R.id.t_aantal);
        worp2Speler1 = (TextView) findViewById(R.id.t_aantal2);
        worp3Speler1 = (TextView) findViewById(R.id.t_aantal3);

        //Speler 2
        // naamSpeler2 = (TextView) findViewById(R.id.naamSpeler2);
        puntenSpeler2 = (TextView) findViewById(R.id.score2);
        //beurtSpeler2 = (TextView) findViewById(R.id.beurt);
        worpSpeler2 = (TextView) findViewById(R.id.t_aantal);
        worp2Speler2 = (TextView) findViewById(R.id.t_aantal2);
        worp3Speler2 = (TextView) findViewById(R.id.t_aantal3);

        button = (Button) findViewById(R.id.pass);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });


    }

    public void openDialog() {
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }

    //Indien je op 'werp' klikt gebeurt dit
    public void generate(View view) {
        //Aantal worpen verminderen
        //  -> TODO: Indien het op nul komt dan moet de beurt naar de andere gaan
        numberOfRolls -= 1;
        beurtAantal.setText(String.valueOf(numberOfRolls));

        //Indien het aantal rolls op 0 komt gaat het terug nr drie
        // if (beurtSpeler1==true) {

            /*if (numberOfRolls == 0) {
                volgende = false;
            }

            if (volgende == true) {

                //tv.setTypeface(tv.getTypeface(), Typeface.BOLD);
                tv2.setTypeface(tv.getTypeface(), Typeface.REGULAR);

                numberOfRolls = 4;

            } else if (volgende == false) {
                tv.setTypeface(tv.getTypeface(), Typeface.REGULAR);
                tv2.setTypeface(tv.getTypeface(), Typeface.BOLD);

                numberOfRolls = 4;
            }*/
        // }

        //Score berekenen: aparte klasse om de score te berekenen
        berekenScore();

        //Dialoog voor bevesteging om te passen

        /*
            if (numberOfRolls == 0) {
                openDialog();
            }
        */

        //3 random getallen laten genereren
        Random rand = new Random();
        int number = rand.nextInt(6) + 1;
        int number2 = rand.nextInt(6) + 1;
        int number3 = rand.nextInt(6) + 1;

        TextView myText = (TextView) findViewById(R.id.t_aantal);
        TextView myText2 = (TextView) findViewById(R.id.t_aantal2);
        TextView myText3 = (TextView) findViewById(R.id.t_aantal3);

        //Waarde van de dobbelstenen
        String myString = String.valueOf(number);
        String myString2 = String.valueOf(number2);
        String myString3 = String.valueOf(number3);

        myText.setText(myString);
        myText2.setText(myString2);
        myText3.setText(myString3);

        /*if (number == 1) {

            punten += 100;

        } else if (number == 2) {

            punten += 2;

        } else if (number == 3) {

            punten += 3;

        } else if (number == 4) {

            punten += 4;

        } else if (number == 5) {

            punten += 5;

        } else {

            punten += 6;

        }*/
        switch(number) {
            case 1:
                punten += 100;
                break;
            case 2:
                punten += 2;
                break;
            case 3:
                punten +=  3;
                break;
            case 4:
                punten +=  4;
                break;
            case 5:
                punten += 5;
                break;
            case 6:
                punten += 60;
                break;

        }

        String puntjesSpeler1 = String.valueOf(punten);
        puntenSpeler1.setText(puntjesSpeler1);

    }


    //PUNTEN: 1 oog = 100 punten, 6 ogen = 60 punten, 5, 4, 3, 2 ogen = 5, 4, 3, 2 punten
    //  -> Optellen met een case-statement. Indien 1 = 100 punten, indien 6 = 60 punten
    //  -> Drie apen: 1,1,1, Soixante-neuf: 6,5,4, Zand: driemaal dezelfde waarde
    //  -> Extra: indien het gelijk is moet er nogmaals gegooid worden
    //  -> Extra: indien 1,1,1 dan wint deze speler automatisch
    private void berekenScore() {
      //Score berekenen
      //Dobbelsteen 1
      /*

      switch(number) {
        case 1:
          punten += 100;
        break;
        case 2:
          punten += 2;
        break;
        case 3:
          punten +=  3;
        break;
        case 4:
          punten +=  4;
        break;
        case 5:
          punten += 5;
        break;
        case 6:
          punten += 60;
        break;

      }

      //Dobbelsteen 2
      switch(number2) {
        case 1:
          punten += 100;
        break;
        case 2:
          punten += 2;
        break;
        case 3:
          punten += 3;
        break;
        case 4:
          punten += 4;
        break;
        case 5:
          punten += 5;
        break;
        case 6:
          punten += 60;
        break;
      }

      //Dobbelsteen 3
      switch(number3) {
        case 1:
          punten += 100;
        break;
        case 2:
          punten += 2;
        break;
        case 3:
          punten += 3;
        break;
        case 4:
          punten += 4;
        break;
        case 5:
          punten += 5;
        break;
        case 6:
          punten += 60;
        break;
      }

      */

      //Punten tonen op het scherm
      //puntenSpeler1.setText(String.valueOf(punten));

    }

    //Als je op pass klikt moet je eerst weten welke speler momenteel aan de beurt is en moet die veranderen naar de andere speler
    public void pass(View view) {

        if (beurtSpeler1==true){
            beurtSpeler1 = false;
        }
        else {
            beurtSpeler1 = true;
        }

    }
}
