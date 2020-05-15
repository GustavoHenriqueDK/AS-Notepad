package com.example.notepad.controller;

import android.widget.EditText;
import android.widget.Switch;

import java.util.List;

public class CadastrarNoteController {

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

    public void adicionaNote(List<String> listaDeNotes, String itemASerAdicionado) {
        listaDeNotes.add(itemASerAdicionado);
    }

    public List<String> retornaListaDeNotes(List<String> listaDeNotes) {
        return listaDeNotes;
    }
}

