package com.example.android.pietjesbak;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.hardware.Sensor;
import android.hardware.SensorManager;

import com.example.parsaniahardik.shakedetection.ShakeDetector;

import java.util.Random;

import static android.graphics.Color.argb;

public class MainActivity extends AppCompatActivity {
    //TODO: speler 2 mag maar even veel keren gooien als de eerste speeler
    //TODO: bij een zand mag je twee streepjes wegdoen, bij een soixante-neuf mag je drie streepjes wegdoen





    //Pass-button
    private Button button;

    //DECLARATIE
    //Speler 1
    TextView puntenSpeler1, beurtAantal;

    //Speler 2
    TextView puntenSpeler2;

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

    //Strepen uit de layout halen
    TextView strepen1, strepen2;
    //Twee keer instelen op 5 streepjes
    int strepenx1 = 5;
    int strepenx2 = 5;

    int aantalStrepenWeg = 0;

    //Random nummer
    Random rand = new Random();
    int number, number2, number3;
    //Variabelen om de waarden van de dobbelstenen in te zetten;
    TextView dobbelsteen1, dobbelsteen2, dobbelsteen3;

    //Strepen op het scherm
    String strepenScherm1 = String.valueOf(strepenx1);
    String strepenScherm2 = String.valueOf(strepenx2);


    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private com.example.parsaniahardik.shakedetection.ShakeDetector mShakeDetector;


    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Namen in MainActivity zetten
        tv = findViewById(R.id.textView);
        tv2 = findViewById(R.id.textView2);
        st = getIntent().getExtras().getString("Value");
        st2 = getIntent().getExtras().getString("Value2");
        tv.setText(st);
        tv2.setText(st2);

        //Speler 1 begint dus deze staat in het vet
        tv.setTypeface(tv.getTypeface(), Typeface.BOLD_ITALIC);
        tv2.setTypeface(tv2.getTypeface(), Typeface.ITALIC);

        //Aantal worpen deze beurt
        beurtAantal = (TextView) findViewById(R.id.beurt);

        //Punten speler 1
        puntenSpeler1 = (TextView) findViewById(R.id.score);

        //Punten speler 2
        puntenSpeler2 = (TextView) findViewById(R.id.score2);


        strepen1 = (TextView) findViewById(R.id.streepjes1);
        strepen2 = (TextView) findViewById(R.id.streepjes2);

        puntenSpeler1.setText("score " + st);
        puntenSpeler2.setText("score " + st2);

        button = (Button) findViewById(R.id.pass);

        //Navigatiebalk verbergen
        hideNavigationBar();


        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {
                //Toast.makeText(MainActivity.this, "Shakedvytfyftyf!!!", Toast.LENGTH_SHORT).show();
                //Hier code zetten wat er gebeurt als je schud



                numberOfRolls -= 1;
                beurtAantal.setText("Worpen: " + String.valueOf(numberOfRolls));

                //Iedere als de speler werpt moeten de punten terug van nul beginnen
                punten = 0;

                //Dobbelstenen uit de layout halen
                dobbelsteen1 = (TextView) findViewById(R.id.t_aantal);
                dobbelsteen2 = (TextView) findViewById(R.id.t_aantal2);
                dobbelsteen3 = (TextView) findViewById(R.id.t_aantal3);


                //GOOIEN: eerste keer met drie dobbelstenen, nadien kan je kiezen met welke dobbelstenen je verder gooit
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

                //Indien alle dobbelstenen vast staan wil je niet meer gooien en moet de beurt naar de andere gaan
                else if (dobbelsteen1Vast == true && dobbelsteen2Vast == true && dobbelsteen3Vast == true) {
                    if (beurtSpeler1 == true) {
                        //De kleur veranderen van de speler die aan de beurt is
                        //De speler die aan de beurt is moet in het vet komen
                        tv.setTypeface(tv.getTypeface(), Typeface.ITALIC);
                        tv2.setTypeface(tv2.getTypeface(), Typeface.BOLD_ITALIC);
                        //Van beurt veranderen
                        beurtSpeler1 = false;
                        beurtSpeler2 = true;
                        //De andere speler mag terug drie keer werpen
                        numberOfRolls = 3;
                    } //Einde if beurt is aan speler 1

                    else if (beurtSpeler2 == true){
                        //Punten tellen aangezien de ronde gedaan is
                        int punten1 = Integer.parseInt((String) puntenSpeler1.getText());
                        int punten2 = Integer.parseInt((String) puntenSpeler2.getText());

                        tv.setTypeface(tv.getTypeface(), Typeface.BOLD_ITALIC);
                        tv2.setTypeface(tv2.getTypeface(), Typeface.ITALIC);

                        //Van beurt veranderen
                        beurtSpeler1 = true;
                        beurtSpeler2 = false;
                        //De andere speler mag terug drie keer werpen
                        numberOfRolls = 3;

                        //De punten zijn geteld dus moeten ze vergeleken worden om te zien wie er wint
                        if (punten1 > punten2) {
                            //Strepen verminderen speler 1 en dit tonen op het scherm
                            strepenx1 -= aantalStrepenWeg;
                            strepenScherm1 = String.valueOf(strepenx1);
                            strepen1.setText("Streepjes " + st + ' ' + strepenScherm1);

                        }

                        else {
                            //Strepen verminderen speler 2 en dit tonene op het scherm
                            strepenx2 -= aantalStrepenWeg;
                            strepenScherm2 = String.valueOf(strepenx2);
                            strepen2.setText("Streepjes " + st2 + ' ' + strepenScherm2);
                        }

                    }
                }

                else {
                    //Als er geen enkele dobbelsteen vast staat dan moet je moet alle dobbelstenen gooien
                    number = rand.nextInt(6) + 1;
                    number2 = rand.nextInt(6) + 1;
                    number3 = rand.nextInt(6) + 1;

                }

                    /*if (dobbelsteen1Vast == true && dobbelsteen2Vast == true && dobbelsteen3Vast == true) {

                    }*/

                //Waarde van de dobbelstenen
                String myString = String.valueOf(number);
                String myString2 = String.valueOf(number2);
                String myString3 = String.valueOf(number3);

                //De text in de layout zetten
                dobbelsteen1.setText(myString);
                dobbelsteen2.setText(myString2);
                dobbelsteen3.setText(myString3);

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
                                strepenScherm1 = String.valueOf(strepenx1);
                                strepen1.setText("Streepjes " + st + ' ' + strepenScherm1);

                                beurtSpeler1 = true;
                                beurtSpeler2 = false;

                                numberOfRolls = 3;

                            }

                            else if (beurtSpeler2 == true) {
                                String puntjesSpeler2 = String.valueOf(punten);
                                puntenSpeler2.setText(puntjesSpeler2);

                                strepenx2--;
                                strepenScherm2 = String.valueOf(strepenx2);
                                strepen2.setText("Streepjes " + st2 + ' ' + strepenScherm2);

                                beurtSpeler1 = true;
                                beurtSpeler2 = false;

                                numberOfRolls = 3;

                            }

                            Toast.makeText(MainActivity.this, "Zand van 1", Toast.LENGTH_LONG).show();

                            break;

                        case 2:
                            //Zand van 2 = 6
                            Toast.makeText(MainActivity.this, "Zand van 2", Toast.LENGTH_LONG).show();
                            punten += 6;
                            break;

                        case 3:
                            //Zand van 3 = 9
                            Toast.makeText(MainActivity.this, "Zand van 3", Toast.LENGTH_LONG).show();
                            punten += 9;
                            break;

                        case 4:
                            //Zand van 4 = 12
                            Toast.makeText(MainActivity.this, "Zand van 4", Toast.LENGTH_LONG).show();
                            punten += 12;
                            break;

                        case 5:
                            //Zand van 5 = 15
                            Toast.makeText(MainActivity.this, "Zand van 5", Toast.LENGTH_LONG).show();
                            punten += 15;
                            break;

                        case 6:
                            //Zand van 6 = 180
                            Toast.makeText(MainActivity.this, "Zand van 6", Toast.LENGTH_LONG).show();
                            punten += 180;
                            break;

                    }
                }
                else if (
                                number == 6 && number2 == 5 && number3 == 4 ||
                                number == 5 && number2 == 4 && number3 == 6 ||
                                number == 4 && number2 == 6 && number3 == 5 ||
                                number == 6 && number2 == 4 && number3 == 5 ||
                                number == 4 && number2 == 5 && number3 == 6 ||
                                number == 5 && number2 == 6 && number3 == 4
                ) {
                    //soixante-neuf
                    Toast.makeText(MainActivity.this, "Soixante-neuf", Toast.LENGTH_LONG).show();
                    punten += 69;
                }

                // Gewone punten
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

                //Indien de beurt naar de andere speler gaat: 1. aantal rolls teruzetten naar 3, 2. de speler die aan de beurt is in het vet plaatsen
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

                        //Dobbelstenen staan niet vast
                        dobbelsteen1Vast = false;
                        dobbelsteen2Vast = false;
                        dobbelsteen3Vast = false;
                        dobbelsteen1.setTextColor(argb(255,255,255,255));
                        dobbelsteen2.setTextColor(argb(255,255,255,255));
                        dobbelsteen3.setTextColor(argb(255,255,255,255));

                        //Opmaak veranderen van de namen
                        tv.setTypeface(tv.getTypeface(), Typeface.ITALIC);
                        tv2.setTypeface(tv2.getTypeface(), Typeface.BOLD_ITALIC);

                    } else {

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
                            strepenScherm1 = String.valueOf(strepenx1);
                            strepen1.setText("Streepjes " + st + ' ' + strepenScherm1);

                        }

                        else {

                            //Verminder de strepen van speler 2
                            strepenx2--;
                            strepenScherm2 = String.valueOf(strepenx2);
                            strepen2.setText("Streepjes " + st2 + ' ' + strepenScherm2);

                        }

                        numberOfRolls = 3;
                        beurtSpeler1 = true;
                        beurtSpeler2 = false;

                        //Dobbelstenen staan niet vast
                        dobbelsteen1Vast = false;
                        dobbelsteen2Vast = false;
                        dobbelsteen3Vast = false;
                        dobbelsteen1.setTextColor(argb(255,255,255,255));
                        dobbelsteen2.setTextColor(argb(255,255,255,255));
                        dobbelsteen3.setTextColor(argb(255,255,255,255));

                        tv.setTypeface(tv.getTypeface(), Typeface.BOLD_ITALIC);
                        tv2.setTypeface(tv2.getTypeface(), Typeface.ITALIC);

                    } else {

                        tv.setTypeface(tv.getTypeface(), Typeface.ITALIC);
                        tv2.setTypeface(tv2.getTypeface(), Typeface.BOLD_ITALIC);

                    }
                }

                if (strepenx1 == 0) {
                    Toast.makeText(MainActivity.this, st + " heeft gewonnen!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    startActivity(intent);
                } else if (strepenx2 == 0) {
                    Toast.makeText(MainActivity.this, st2 + " heeft gewonnen!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    startActivity(intent);
                }

            }
        });

    }

    private void hideNavigationBar() {
        this.getWindow().getDecorView()
            .setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        );
    }

    //Dobbelsteen 1 vastzetten
    //Begin vastzetten dobbelstenen
    public void vastzetten(View view) {
        dobbelsteen1 = (TextView) findViewById(R.id.t_aantal);
        if (dobbelsteen1Vast == false) {
            dobbelsteen1Vast = true;
            dobbelsteen1.setTextColor(argb(100,0,0,0));
        }
        else {
            dobbelsteen1Vast = false;
            dobbelsteen1.setTextColor(argb(255,255,255,255));
        }
    }

    //Dobbelsteen 2 vastzetten
    public void vastzetten2 (View view) {
        dobbelsteen2 = (TextView) findViewById(R.id.t_aantal2);
        if (dobbelsteen2Vast == false) {
            dobbelsteen2Vast = true;
            //Achtergrondkleur aanpassen
            dobbelsteen2.setTextColor(argb(100,0,0,0));
        }
        else {
            dobbelsteen2Vast = false;
            dobbelsteen2.setTextColor(argb(255,255,255,255));
        }
    }

    //Dobbelsteen 3 vastzetten
    public void vastzetten3 (View view) {
        dobbelsteen3 = (TextView) findViewById(R.id.t_aantal3);
        if (dobbelsteen3Vast == false) {
            dobbelsteen3Vast = true;
            dobbelsteen3.setTextColor(argb(100,0,0,0));
        }
        else {
            dobbelsteen3Vast = false;
            dobbelsteen3.setTextColor(argb(255,255,255,255));
        }
    }
    //Einde vastzetten dobbelstenen

    //DOBBELSTENEN GOOIEN
    public void generate(View view) {
      //Aantal worpen verminderen iedere keer iemand heeft geworpen
        numberOfRolls -= 1;
        beurtAantal.setText("Worpen: " + String.valueOf(numberOfRolls));

        //Iedere als de speler werpt moeten de punten terug van nul beginnen
        punten = 0;

        //Aantal strepen die weg gedaan mogen worden


        //Dobbelstenen uit de layout halen
        dobbelsteen1 = (TextView) findViewById(R.id.t_aantal);
        dobbelsteen2 = (TextView) findViewById(R.id.t_aantal2);
        dobbelsteen3 = (TextView) findViewById(R.id.t_aantal3);


        //GOOIEN: eerste keer met drie dobbelstenen, nadien kan je kiezen met welke dobbelstenen je verder gooit
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

        //Indien alle dobbelstenen vast staan wil je niet meer gooien en moet de beurt naar de andere gaan
        else if (dobbelsteen1Vast == true && dobbelsteen2Vast == true && dobbelsteen3Vast == true) {
            if (beurtSpeler1 == true) {
                //De kleur veranderen van de speler die aan de beurt is
                //De speler die aan de beurt is moet in het vet komen
                tv.setTypeface(tv.getTypeface(), Typeface.ITALIC);
                tv2.setTypeface(tv2.getTypeface(), Typeface.BOLD_ITALIC);
                //Van beurt veranderen
                beurtSpeler1 = false;
                beurtSpeler2 = true;
                //De andere speler mag terug drie keer werpen
                numberOfRolls = 3;
            } //Einde if beurt is aan speler 1

            else if (beurtSpeler2 == true){
                //Punten tellen aangezien de ronde gedaan is
                int punten1 = Integer.parseInt((String) puntenSpeler1.getText());
                int punten2 = Integer.parseInt((String) puntenSpeler2.getText());

                tv.setTypeface(tv.getTypeface(), Typeface.BOLD_ITALIC);
                tv2.setTypeface(tv2.getTypeface(), Typeface.ITALIC);

                //Van beurt veranderen
                beurtSpeler1 = true;
                beurtSpeler2 = false;
                //De andere speler mag terug drie keer werpen
                numberOfRolls = 3;

                //De punten zijn geteld dus moeten ze vergeleken worden om te zien wie er wint
                if (punten1 > punten2) {
                    //Strepen verminderen speler 1 en dit tonen op het scherm
                    strepenx1 --;
                    strepenScherm1 = String.valueOf(strepenx1);
                    strepen1.setText("Streepjes " + st + ' ' + strepenScherm1);

                }

                else {
                    //Strepen verminderen speler 2 en dit tonene op het scherm
                    strepenx2 --;
                    strepenScherm2 = String.valueOf(strepenx2);
                    strepen2.setText("Streepjes " + st2 + ' ' + strepenScherm2);
                }

            }
        }

        else {
            //Als er geen enkele dobbelsteen vast staat dan moet je moet alle dobbelstenen gooien
            number = rand.nextInt(6) + 1;
            number2 = rand.nextInt(6) + 1;
            number3 = rand.nextInt(6) + 1;

        }

            /*if (dobbelsteen1Vast == true && dobbelsteen2Vast == true && dobbelsteen3Vast == true) {

            }*/

        //Waarde van de dobbelstenen
        String myString = String.valueOf(number);
        String myString2 = String.valueOf(number2);
        String myString3 = String.valueOf(number3);

        //De text in de layout zetten
        dobbelsteen1.setText(myString);
        dobbelsteen2.setText(myString2);
        dobbelsteen3.setText(myString3);

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
                    if (strepenx1 == 5 || strepenx2 == 5) {
                        if (beurtSpeler1 == true) {
                          //Speler verliest
                          //Speler 2 wint
                          strepenx1 = 0;
                          strepenScherm1 = String.valueOf(strepenx1);
                          strepen1.setText("Streepjes " + st + ' ' + strepenScherm1);

                        }

                        else if (beurtSpeler2 == true) {
                          //Speler verliest
                          //Sepeler 1 wint
                          strepenx2 = 0;
                          strepenScherm2 = String.valueOf(strepenx2);
                          strepen2.setText("Streepjes " + st2 + ' ' + strepenScherm2);

                        }

                    }
                    else if (strepenx1 < 5 || strepenx2 < 5) {
                        if (beurtSpeler1 == true) {

                            strepenx1 = 0;
                            strepenScherm1 = String.valueOf(strepenx1);
                            strepen1.setText("Streepjes " + st + ' ' + strepenScherm1);

                        }

                        else if (beurtSpeler2 == true) {

                            strepenx2 = 0;
                            strepenScherm2 = String.valueOf(strepenx2);
                            strepen2.setText("Streepjes " + st2 + ' ' + strepenScherm2);

                        }

                    }

                    // Toast.makeText(MainActivity.this, "Zand van 1", Toast.LENGTH_LONG).show();

                    break;

                case 2:
                    //Zand van 2 = 6

                    Toast.makeText(MainActivity.this, "Zand van 2", Toast.LENGTH_LONG).show();
                    punten += 6;
                    //Je mag twee streepjes wegdoen als je met een zand wint
                    aantalStrepenWeg = 2;
                    break;

                case 3:
                    //Zand van 3 = 9
                    Toast.makeText(MainActivity.this, "Zand van 3", Toast.LENGTH_LONG).show();
                    punten += 9;
                    //Je mag twee streepjes wegdoen als je met een zand wint
                    aantalStrepenWeg = 2;
                    break;

                case 4:
                    //Zand van 4 = 12
                    Toast.makeText(MainActivity.this, "Zand van 4", Toast.LENGTH_LONG).show();
                    punten += 12;
                    //Je mag twee streepjes wegdoen als je met een zand wint
                    aantalStrepenWeg = 2;
                    break;

                case 5:
                    //Zand van 5 = 15
                    Toast.makeText(MainActivity.this, "Zand van 5", Toast.LENGTH_LONG).show();
                    punten += 15;
                    //Je mag twee streepjes wegdoen als je met een zand wint
                    aantalStrepenWeg = 2;
                    break;

                case 6:
                    //Zand van 6 = 180
                    Toast.makeText(MainActivity.this, "Zand van 6", Toast.LENGTH_LONG).show();
                    punten += 180;
                    //Je mag twee streepjes wegdoen als je met een zand wint
                    aantalStrepenWeg = 2;
                    break;

            }
        }
        else if (
                        number == 6 && number2 == 5 && number3 == 4 ||
                        number == 5 && number2 == 4 && number3 == 6 ||
                        number == 4 && number2 == 6 && number3 == 5 ||
                        number == 6 && number2 == 4 && number3 == 5 ||
                        number == 4 && number2 == 5 && number3 == 6 ||
                        number == 5 && number2 == 6 && number3 == 4
        )
        {
            //soixante-neuf
            Toast.makeText(MainActivity.this, "Soixante-neuf", Toast.LENGTH_LONG).show();
            punten += 69;
            //Drie streepjes wegdoen als je wint
            aantalStrepenWeg = 3;
        }



        // Gewone punten
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

            // aantalStrepenWeg = 1;
        }

        if (beurtSpeler1 == true) {

            strepenx1 = strepenx1 - aantalStrepenWeg;
            strepenScherm1 = String.valueOf(strepenx1);
            strepen1.setText("Streepjes " + st + ' ' + strepenScherm1);

        } else if (beurtSpeler2 == true) {

            strepenx2 = strepenx2 - aantalStrepenWeg;
            strepenScherm2 = String.valueOf(strepenx2);
            strepen2.setText("Streepjes " + st2 + ' ' + strepenScherm2);

        }

        //Indien de beurt naar de andere speler gaat: 1. aantal rolls teruzetten naar 3, 2. de speler die aan de beurt is in het vet plaatsen
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

                //Dobbelstenen staan niet vast
                dobbelsteen1Vast = false;
                dobbelsteen2Vast = false;
                dobbelsteen3Vast = false;
                dobbelsteen1.setTextColor(argb(255,255,255,255));
                dobbelsteen2.setTextColor(argb(255,255,255,255));
                dobbelsteen3.setTextColor(argb(255,255,255,255));

                //Opmaak veranderen van de namen
                tv.setTypeface(tv.getTypeface(), Typeface.ITALIC);
                tv2.setTypeface(tv2.getTypeface(), Typeface.BOLD_ITALIC);

            } else {

                tv.setTypeface(tv.getTypeface(), Typeface.BOLD_ITALIC);
                tv2.setTypeface(tv2.getTypeface(), Typeface.ITALIC);

            }

            /*strepenx1 = strepenx1 - aantalStrepenWeg;
            strepenScherm1 = String.valueOf(strepenx1);
            strepen1.setText(strepenScherm1);*/

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
                    strepenScherm1 = String.valueOf(strepenx1);
                    strepen1.setText("Streepjes " + st + ' ' + strepenScherm1);

                }

                else {

                    //Verminder de strepen van speler 2
                    strepenx2--;
                    strepenScherm2 = String.valueOf(strepenx2);
                    strepen2.setText("Streepjes " + st2 + ' ' + strepenScherm2);

                }

                numberOfRolls = 3;
                beurtSpeler1 = true;
                beurtSpeler2 = false;

                //Dobbelstenen staan niet vast
                dobbelsteen1Vast = false;
                dobbelsteen2Vast = false;
                dobbelsteen3Vast = false;
                dobbelsteen1.setTextColor(argb(255,255,255,255));
                dobbelsteen2.setTextColor(argb(255,255,255,255));
                dobbelsteen3.setTextColor(argb(255,255,255,255));

                tv.setTypeface(tv.getTypeface(), Typeface.BOLD_ITALIC);
                tv2.setTypeface(tv2.getTypeface(), Typeface.ITALIC);

            } else {

                tv.setTypeface(tv.getTypeface(), Typeface.ITALIC);
                tv2.setTypeface(tv2.getTypeface(), Typeface.BOLD_ITALIC);

            }
            /*strepenx2 = strepenx2 - aantalStrepenWeg;
            strepenScherm2 = String.valueOf(strepenx2);
            strepen2.setText(strepenScherm2);*/
        }

        //Beurt passen
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //Elke keer je op pas klikt moeten je weer met alle dobbelstenen kunnen gooien en moeten ze aan staan (wit)
              //Dobbelstenen mogen niet vast beginnen
              dobbelsteen1Vast = false;
              dobbelsteen2Vast = false;
              dobbelsteen3Vast = false;

              //Kleur van de dobbelstenen veranderen
              dobbelsteen1.setTextColor(argb(255,255,255,255));
              dobbelsteen2.setTextColor(argb(255,255,255,255));
              dobbelsteen3.setTextColor(argb(255,255,255,255));

              if (beurtSpeler1 == true && beurtSpeler2 == false) {
                if (numberOfRolls == 2) {
                  //De tweede speler mag maar evenveel gooien als de eerste speler
                  numberOfRolls = 1;
                }

                else if (numberOfRolls == 1) {
                  //Beurt veranderen
                  numberOfRolls = 2;

                }

                //Beurt veranderen
                beurtSpeler1 = false;
                beurtSpeler2 = true;

                //De kleuren van de spelers veradnderen
                tv.setTypeface(tv.getTypeface(), Typeface.ITALIC);
                tv2.setTypeface(tv2.getTypeface(), Typeface.BOLD_ITALIC);

              }

              else if (beurtSpeler1 == false && beurtSpeler2 == true) {
                int punten1 = Integer.parseInt((String) puntenSpeler1.getText());
                int punten2 = Integer.parseInt((String) puntenSpeler2.getText());

                if (punten1 > punten2) {

                  //Verminder de strepen van speler 1
                  strepenx1--;
                  strepenScherm1 = String.valueOf(strepenx1);
                  strepen1.setText("Streepjes " + st + ' ' + strepenScherm1);

                }

                else {
                  //Verminder de strepen van speler 2
                  strepenx2--;
                  strepenScherm2 = String.valueOf(strepenx2);
                  strepen2.setText("Streepjes " + st2 + ' ' + strepenScherm2);

                }

                beurtSpeler1 = true;
                beurtSpeler2 = false;
                //De eerste speler mag terug met drie dobbelstenen gooien
                numberOfRolls = 3;

                //De kleuren van de spelers veradnderen
                tv.setTypeface(tv.getTypeface(), Typeface.BOLD_ITALIC);
                tv2.setTypeface(tv2.getTypeface(), Typeface.ITALIC);
              }
            }
        });







        if (strepenx1 <= 0) {
            Toast.makeText(MainActivity.this, st + " heeft gewonnen!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        } else if (strepenx2 <= 0) {
            Toast.makeText(MainActivity.this, st2 + " heeft gewonnen!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }
    }
}
