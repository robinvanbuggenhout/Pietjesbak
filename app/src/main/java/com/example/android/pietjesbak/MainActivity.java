package com.example.android.pietjesbak;

import android.content.Intent;
import android.graphics.Color;
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

import static android.graphics.Color.argb;

public class MainActivity extends AppCompatActivity {

    private Button button;

    //DECLARATIE
    //Speler 1
    TextView puntenSpeler1, beurtAantal;
    //worpSpeler1, worp2Speler1, worp3Speler1;

    //Speler 2
    TextView puntenSpeler2;
    //worpSpeler2, worp2Speler2, worp3Speler2;

    //Namen ingeven en meenemen naar de MainActivity
    TextView tv, tv2;
    String st, st2;

    //Nodige gegevens
    int numberOfRolls = 3;
    int punten = 0;
    boolean beurtSpeler1 = true;
    boolean beurtSpeler2 = false;
    boolean dobbelsteen1Vast = false;
    boolean dobbelsteen2Vast = false;
    boolean dobbelsteen3Vast = false;

    TextView strepen1, strepen2;


    int strepenx1 = 5;
    int strepenx2 = 5;

    //Random nummer
    Random rand = new Random();
    int number, number2, number3;

    TextView myText, myText2, myText3;



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
        //worpSpeler1 = (TextView) findViewById(R.id.t_aantal);
        //worp2Speler1 = (TextView) findViewById(R.id.t_aantal2);
        //worp3Speler1 = (TextView) findViewById(R.id.t_aantal3);

        //Speler 2
        puntenSpeler2 = (TextView) findViewById(R.id.score2);
        //worpSpeler2 = (TextView) findViewById(R.id.t_aantal);
        //worp2Speler2 = (TextView) findViewById(R.id.t_aantal2);
        //worp3Speler2 = (TextView) findViewById(R.id.t_aantal3);

        strepen1 = (TextView) findViewById(R.id.streepjes1);
        strepen2 = (TextView) findViewById(R.id.streepjes2);

        puntenSpeler1.setText("score " + st);
        puntenSpeler2.setText("score " + st2);

        button = (Button) findViewById(R.id.pass);
    }

    public void vastzetten(View view) {
        myText = (TextView) findViewById(R.id.t_aantal);
        if (dobbelsteen1Vast == false) {

            dobbelsteen1Vast = true;

            //Achtergrondkleur aanpassen
            myText.setTextColor(argb(255,85,128,246));


        }
        else {
            dobbelsteen1Vast = false;
            myText.setTextColor(argb(255,0,0,0));
        }
    }

    public void vastzetten2 (View view) {
        myText2 = (TextView) findViewById(R.id.t_aantal2);
        if (dobbelsteen2Vast == false) {

            dobbelsteen2Vast = true;

            //Achtergrondkleur aanpassen
            myText2.setTextColor(argb(255,85,128,246));


      }
      else {
          dobbelsteen2Vast = false;
          myText2.setTextColor(argb(255,0,0,0));
      }
    }

    public void vastzetten3 (View view) {
        myText3 = (TextView) findViewById(R.id.t_aantal3);
        if (dobbelsteen3Vast == false) {

            dobbelsteen3Vast = true;
            myText3.setTextColor(argb(255,85,128,246));
          //Achtergrondkleur aanpassen


    }
    else {
          dobbelsteen3Vast = false;
          myText3.setTextColor(argb(255,0,0,0));
    }
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

        // https://android--examples.blogspot.com/2015/01/textview-onclick-event-in-android.html
        // klik op dobbelsteen 1 -> dobbelsteen1Vast = true
        // klik op dobbelsteen 2 -> dobbelsteen2Vast = true
        // klik op dobbelsteen 3 -> dobbelsteen3Vast = true

        /*Random rand = new Random();
        int number = rand.nextInt(1) + 1;
        int number2 = rand.nextInt(1) + 1;
        int number3 = rand.nextInt(1) + 1;*/

        /*number = rand.nextInt(1) + 1;
        number2 = rand.nextInt(1) + 1;
        number3 = rand.nextInt(1) + 1;*/

        myText = (TextView) findViewById(R.id.t_aantal);
        myText2 = (TextView) findViewById(R.id.t_aantal2);
        myText3 = (TextView) findViewById(R.id.t_aantal3);


        if (dobbelsteen1Vast == true && dobbelsteen2Vast == false && dobbelsteen3Vast == false){
          //Gooien met dobbelsteen 2 en 3
          number2 = rand.nextInt(6) + 1;
          number3 = rand.nextInt(6) + 1;
        }

        else if (dobbelsteen2Vast == true && dobbelsteen1Vast == false && dobbelsteen3Vast == false) {
          //Gooien met dobbelsteen 1 en 3
          number = rand.nextInt(6) + 1;
          number3 = rand.nextInt(6) + 1;
        }

        else if (dobbelsteen3Vast == true && dobbelsteen1Vast == false && dobbelsteen2Vast == false) {
          //Gooien met dobbelsteen 1 en 2
          number = rand.nextInt(6) + 1;
          number2 = rand.nextInt(6) + 1;
        }

        else if (dobbelsteen1Vast == true && dobbelsteen2Vast == true && dobbelsteen3Vast == false) {
          //Gooien met dobbelsteen 3
          number3 = rand.nextInt(6) + 1;
        }

        else if (dobbelsteen1Vast == true && dobbelsteen3Vast == true && dobbelsteen2Vast == false) {
          //Gooien met dobbelsteen 2
          number2 = rand.nextInt(6) + 1;
        }

        else if (dobbelsteen2Vast == true && dobbelsteen3Vast == true && dobbelsteen1Vast == false) {
          //Gooien met dobbelsteen 1
          number = rand.nextInt(6) + 1;
        }

        else {
          //Als er geen enkele dobbelsteen vast staat dan moet je moet alle dobbelstenen gooien
          number = rand.nextInt(6) + 1;
          number2 = rand.nextInt(6) + 1;
          number3 = rand.nextInt(6) + 1;
        }

        //Waarde van de dobbelstenen
        String myString = String.valueOf(number);
        String myString2 = String.valueOf(number2);
        String myString3 = String.valueOf(number3);

        //De text in de layout zetten
        myText.setText(myString);
        myText2.setText(myString2);
        myText3.setText(myString3);


        //GOOIEN: eerste keer met drie dobbelstenen, nadien kan je kiezen met welke dobbelstenen je verder gooit
        //  -> Je moet dobbelstenen kunnen 'vast' zetten
        //3 random getallen laten genereren
        /*Random rand = new Random();
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

        //De text in de layout zetten
        myText.setText(myString);
        myText2.setText(myString2);
        myText3.setText(myString3);*/

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
                    if (beurtSpeler1 == true) {
                        String puntjesSpeler1 = String.valueOf(punten);
                        puntenSpeler1.setText(puntjesSpeler1);

                        strepenx1--;
                        String strepenScherm1 = String.valueOf(strepenx1);
                        strepen1.setText(strepenScherm1);

                        beurtSpeler1 = true;
                        beurtSpeler2 = false;

                        numberOfRolls = 3;

                    }

                    else if (beurtSpeler2 == true) {
                        String puntjesSpeler2 = String.valueOf(punten);
                        puntenSpeler2.setText(puntjesSpeler2);

                        strepenx2--;
                        String strepenScherm2 = String.valueOf(strepenx1);
                        strepen2.setText(strepenScherm2);

                        beurtSpeler1 = true;
                        beurtSpeler2 = false;

                        numberOfRolls = 3;

                    }

                    break;

                case 2:
                    //Zand van 2 = 6
                    if (beurtSpeler1 == true) {
                        String puntjesSpeler1 = String.valueOf(punten);
                        puntenSpeler1.setText(puntjesSpeler1);

                        strepenx1--;
                        String strepenScherm1 = String.valueOf(strepenx1);
                        strepen1.setText(strepenScherm1);

                        beurtSpeler1 = true;
                        beurtSpeler2 = false;

                        numberOfRolls = 3;

                    }

                    else if (beurtSpeler2 == true) {
                        String puntjesSpeler2 = String.valueOf(punten);
                        puntenSpeler2.setText(puntjesSpeler2);

                        strepenx2--;
                        String strepenScherm2 = String.valueOf(strepenx1);
                        strepen2.setText(strepenScherm2);

                        beurtSpeler1 = true;
                        beurtSpeler2 = false;

                        numberOfRolls = 3;

                    }

                    break;

                case 3:
                    //Zand van 3 = 9
                    if (beurtSpeler1 == true) {
                        puntenSpeler1.setText("Zand van 3");
                    }

                    else if (beurtSpeler2 == true) {
                        puntenSpeler2.setText("Zand van 3");
                    }

                    break;

                case 4:
                    //Zand van 4 = 12
                    if (beurtSpeler1 == true) {
                        puntenSpeler1.setText("Zand van 4");
                    }

                    else if (beurtSpeler2 == true) {
                        puntenSpeler2.setText("Zand van 4");
                    }
                    break;

                case 5:
                    //Zand van 5 = 15
                    if (beurtSpeler1 == true) {
                        puntenSpeler1.setText("Zand van 5");
                    }

                    else if (beurtSpeler2 == true) {
                        puntenSpeler2.setText("Zand van 5");
                    }
                    break;

                case 6:
                    //Zand van 6 = 180
                    if (beurtSpeler1 == true) {
                        puntenSpeler1.setText("Zand van 6");
                    }

                    else if (beurtSpeler2 == true) {
                        puntenSpeler2.setText("Zand van 6");
                    }
                    break;

            }
        }
        else if (number == 6 && number2 == 5 && number3 == 4 || number == 5 && number2 == 4 && number3 == 6 || number == 4 && number2 == 6 && number3 == 5) {
            //soixante-neuf
            if (beurtSpeler1 == true) {
                puntenSpeler1.setText("soixante-neuf");
            }

            else if (beurtSpeler2 == true) {
                puntenSpeler2.setText("soixante-neuf");
            }
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
                numberOfRolls = 3;
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

                //STREEPJES: je begint met vijf streepjes, indien je een ronde wint mag je een streepje wegdoen
                //  -> Streepje aftrekken van de winnaar en nadien het totaal terug tonen
                //de speler met de hoogste score mag een streepje wegdoen

                int punten1 = Integer.parseInt((String) puntenSpeler1.getText());
                int punten2 = Integer.parseInt((String) puntenSpeler2.getText());

                if (punten1 > punten2) {

                    //Verminder de strepen van speler 1
                    strepenx1--;
                    String strepenScherm1 = String.valueOf(strepenx1);
                    strepen1.setText(strepenScherm1);

                }

                else {

                    //Verminder de strepen van speler 2
                    strepenx2--;
                    String strepenScherm2 = String.valueOf(strepenx2);
                    strepen2.setText(strepenScherm2);

                }

                if (strepenx1 == 0) {
                    openDialog();
                }

                if (strepenx2 == 0) {
                    openDialog();
                }

                numberOfRolls = 3;
                beurtSpeler1 = true;
                beurtSpeler2 = false;

            } else {

                tv.setTypeface(tv.getTypeface(), Typeface.ITALIC);
                tv2.setTypeface(tv2.getTypeface(), Typeface.BOLD_ITALIC);

            }
        }

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

                    int punten1 = Integer.parseInt((String) puntenSpeler1.getText());
                    int punten2 = Integer.parseInt((String) puntenSpeler2.getText());

                    if (punten1 > punten2) {

                        //Verminder de strepen van speler 1
                        strepenx1--;
                        String strepenScherm1 = String.valueOf(strepenx1);
                        strepen1.setText(strepenScherm1);

                    }

                    else {

                        //Verminder de strepen van speler 2
                        strepenx2--;
                        String strepenScherm2 = String.valueOf(strepenx2);
                        strepen2.setText(strepenScherm2);

                    }

                    if (strepenx1 == 0) {
                        openDialog();
                    }

                    if (strepenx2 == 0) {
                        openDialog();
                    }

                    tv.setTypeface(tv.getTypeface(), Typeface.ITALIC);
                    tv2.setTypeface(tv2.getTypeface(), Typeface.BOLD_ITALIC);
                    numberOfRolls = 3;
                    beurtSpeler1 = true;
                    beurtSpeler2 = false;

                }
            }
        });
    }
}
