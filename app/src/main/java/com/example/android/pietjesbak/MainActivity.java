package com.example.android.pietjesbak;

import android.content.Intent;
import android.graphics.Typeface;
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
    TextView puntenSpeler1, beurtAantal, worpSpeler1, worp2Speler1, worp3Speler1;

    //Speler 2
    TextView puntenSpeler2, worpSpeler2, worp2Speler2, worp3Speler2;

    //Namen ingeven en meenemen naar de MainActivity
    TextView tv, tv2;
    String st, st2;

    //Nodige gegevens
    int numberOfRolls = 3;
    int punten = 0;
    boolean beurtSpeler1 = true;
    boolean beurtSpeler2 = false;

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

        tv.setTypeface(tv.getTypeface(), Typeface.BOLD);

        beurtAantal = (TextView) findViewById(R.id.beurt);

        //Speler 1
        puntenSpeler1 = (TextView) findViewById(R.id.score);
        worpSpeler1 = (TextView) findViewById(R.id.t_aantal);
        worp2Speler1 = (TextView) findViewById(R.id.t_aantal2);
        worp3Speler1 = (TextView) findViewById(R.id.t_aantal3);

        //Speler 2
        puntenSpeler2 = (TextView) findViewById(R.id.score2);
        worpSpeler2 = (TextView) findViewById(R.id.t_aantal);
        worp2Speler2 = (TextView) findViewById(R.id.t_aantal2);
        worp3Speler2 = (TextView) findViewById(R.id.t_aantal3);

        puntenSpeler1.setText("score " + st);
        puntenSpeler2.setText("score " + st2);

        button = (Button) findViewById(R.id.pass);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
                if (beurtSpeler1 == true) {
                    tv.setTypeface(tv.getTypeface(), Typeface.BOLD_ITALIC);
                    tv2.setTypeface(tv2.getTypeface(), Typeface.ITALIC);
                    numberOfRolls = 3;
                    beurtSpeler1 = false;
                    beurtSpeler2 = true;

                }

                else {
                    tv.setTypeface(tv.getTypeface(), Typeface.ITALIC);
                    tv2.setTypeface(tv2.getTypeface(), Typeface.BOLD_ITALIC);
                    numberOfRolls = 3;
                    beurtSpeler1 = true;
                    beurtSpeler2 = false;

                }
            }
        });


    }

    public void openDialog() {
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }


    public void generate(View view) {
        //Aantal worpen verminderen
        numberOfRolls -= 1;
        beurtAantal.setText(String.valueOf(numberOfRolls));

        //Iedere als de speler werpt moeten de punten terug van nul beginnen
        punten = 0;

         //de speler met de hoogste score mag een streepje wegdoen
        /*if (//Punten speler 1 > Punten speler 2) {
            //Verminder de streepjes van speler 1
        }
        else{
            //Verminder de streepjes van speler 2
        }*/

        //als een speler geen streepjes meer over heeft dan dan is deze de winnaar
        /*if (//streepjes speler 1 == 0){
            //Speler 1 is winnaar
        }
        else if (//streepjes speler 2 == 0){
            //Speler 2 is winnaar
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

        //PUNTEN: 1 oog = 100 punten, 6 ogen = 60 punten, 5, 4, 3, 2 ogen = 5, 4, 3, 2 punten
        //  -> Optellen met een case-statement. Indien 1 = 100 punten, indien 6 = 60 punten
        //  -> Drie apen: 1,1,1, Soixante-neuf: 6,5,4, Zand: driemaal dezelfde waarde
        //  -> Extra: indien het gelijk is moet er nogmaals gegooid worden
        //  -> Extra: indien 1,1,1 dan wint deze speler automatisch
        if (number == number2 && number == number3) {
          switch(number) {
            case 1:
              //Zand van 1 = 300
              //Ronde meteen gedaan, speler met zand van 1 wint
              break;

            case 2:
              //Zand van 2 = 6
              break;

            case 3:
              //Zand van 3 = 9
              break;

            case 4:
              //Zand van 4 = 12
              break;

            case 5:
              //Zand van 5 = 15
              break;

            case 6:
              //Zand van 6 = 180
              break;

            }
        }
        else if (number == 6 && number2 == 5 && number3 == 4 || number == 5 && number2 == 4 && number3 == 6 || number == 4 && number2 == 6 && number3 == 5) {
            //soixante-neuf
        }

        else {
          //Dobbelsteen 1
          switch(number) {
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
        }

        //Indien de beurt naar de andere speler gaat: 1. aantal rolls teruzetten naar 4, 2. de speler die aan de beurt is in het vet plaatsen, 3.
        //Indien het aantal rolls op 0 komt gaat het terug nr drie
         if (beurtSpeler1 == true) {
           //Punten tellen bij speler 1
           String puntjesSpeler1 = String.valueOf(punten);
           puntenSpeler1.setText(puntjesSpeler1);

                // BEURT AAN SPELER 1
                if (numberOfRolls == 0) {
                        //aantal rolls terugzetten voor de volgende speler
                        numberOfRolls = 4;
                        beurtSpeler1 = false;
                        beurtSpeler2 = true;
                } else {
                        //Opmaak veranderen van de namen
                        tv.setTypeface(tv.getTypeface(), Typeface.BOLD_ITALIC);
                        tv2.setTypeface(tv2.getTypeface(), Typeface.ITALIC);
                }
        }

        else {
          //Punten tellen bij speler 2
          String puntjesSpeler2 = String.valueOf(punten);
          puntenSpeler2.setText(puntjesSpeler2);

                // BEURT AAN SPELER 2
                if (numberOfRolls == 0) {
                    numberOfRolls = 4;
                    beurtSpeler1 = true;
                    beurtSpeler2 = false;
                } else {
                    tv.setTypeface(tv.getTypeface(), Typeface.ITALIC);
                    tv2.setTypeface(tv2.getTypeface(), Typeface.BOLD_ITALIC);
                }
         }

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
