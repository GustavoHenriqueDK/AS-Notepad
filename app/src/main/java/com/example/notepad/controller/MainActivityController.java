package com.example.notepad.controller;

import android.widget.TextView;

import java.util.Random;

public class MainActivityController {

    public void setaTextoAleatoriamente(TextView textview) {
        Random random = new Random();
        int num = random.nextInt(5) +1;

        if (num == 1) {
            textview.setText("Está na hora de anotar coisas interessantes!");
        }
        if (num == 2) {
            textview.setText("Aww, nada anotado! Que bom, não?");
        }
        if (num == 3) {
            textview.setText("Nada por aqui... Está tão vazio...");
        }
        if (num == 4) {
            textview.setText("Um total de 0 anotações?!");
        }
        if (num == 5) {
            textview.setText("Eu sei que há algo importante para anotar...");
        }

    }

}
