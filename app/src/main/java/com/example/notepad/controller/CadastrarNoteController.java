package com.example.notepad.controller;

import android.widget.EditText;
import android.widget.Switch;

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

    public boolean switchDeSairPressionado(Switch mSwitch) {
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
}
