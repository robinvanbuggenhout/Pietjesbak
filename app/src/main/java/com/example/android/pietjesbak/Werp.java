package com.example.android.pietjesbak;

import java.util.Random;

public class Werp {
    private int nummer;
    private boolean fout;

    public Werp(){}

    public Werp(int nummer) { this.nummer = nummer;}

    Random random = new Random();

    public void doeWerp(){
        nummer = random.nextInt(6)+1;
    }

    public int krijgWerp(){return nummer}

    public void veranderFout(){
        if(fout){
            fout = false;
        } else {
            fout = true;
        }
    }

    public boolean isFout() {return fout;}

    public int krijgWerpScore() {
        if (nummer == 1) {
            return 100;
        } else if (nummer == 6) {
            return 60;
        } else {
            return nummer;
        }
    }
}
