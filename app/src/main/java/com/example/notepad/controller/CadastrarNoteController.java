package com.example.notepad.controller;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CadastrarNoteController {

    private static List<String> listaDeNotes = new ArrayList<>();
    private Context context;

    public CadastrarNoteController (Context context) {
        this.context = context;
    }

    public boolean editTextNoteTemNCaracteres(int tamanhoDeCaracteresEditText, EditText editTextNote) {
        if (editTextNote.getText().toString().length() > tamanhoDeCaracteresEditText) {
            return true;
        }
        return false;
    }

    public boolean editTextNaoEstaVazio(EditText editTextNote) {
        if (editTextNote.getText().toString().trim().equals("")) {
            return false;
        }
        return true;
    }

    public boolean switchDeSaiDaTelarPressionado(Switch mSwitch) {
        if (mSwitch.isChecked()) {
            return true;
        }
        return false;
    }

    public boolean switchDeLimparCampoPressionado(Switch mSwitch) {
        if (mSwitch.isChecked()) {
            return true;
        }
        return false;
    }


    //Save and return
    public void adicionaNote(String itemASerAdicionado) {
        listaDeNotes.add(itemASerAdicionado);
    }

    public void removeNote(int position) {
        listaDeNotes.remove(position);
    }

    public boolean listaEstaVazia() {
        if (listaDeNotes.isEmpty()) {
            return true;
        }
        return false;
    }

    public List<String> getListaDeNotes() {
        return listaDeNotes;
    }

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