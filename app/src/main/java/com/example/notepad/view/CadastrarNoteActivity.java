package com.example.notepad.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.notepad.R;
import com.example.notepad.controller.CadastrarNoteController;

public class CadastrarNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_note);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Insira uma anotação!");

    }

    private void defineAlertDialogDeEditText() {
        CadastrarNoteController cadastrarNoteController = new CadastrarNoteController();
        EditText editText = findViewById(R.id.editTextAddNote);

        if (cadastrarNoteController.editTextNoteTemNCaracteres(100, editText)) {

            AlertDialog.Builder builder = new AlertDialog.Builder(CadastrarNoteActivity.this);
            builder.setMessage(R.string.alertDialogMensagem)
                    .setPositiveButton(R.string.alertDialogSim, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    })
                    .setNegativeButton(R.string.alertDialogNao, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder.create();
            alert11.show();
        } else {
            finish();
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        defineAlertDialogDeEditText();
        return true;
    }

    @Override
    public void onBackPressed() {
        defineAlertDialogDeEditText();
    }
}

