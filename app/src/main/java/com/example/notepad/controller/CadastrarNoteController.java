package com.example.notepad.controller;

import android.widget.EditText;

public class CadastrarNoteController {

    public boolean editTextNoteTemNCaracteres(int tamanhoDeCaracteresEditText, EditText editTextNote) {
        if (editTextNote.getText().toString().length() > tamanhoDeCaracteresEditText) {
            return true;
        }
        return false;
    }
}
