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

}