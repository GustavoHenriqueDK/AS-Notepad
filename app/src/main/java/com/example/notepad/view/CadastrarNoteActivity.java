package com.example.notepad.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.notepad.R;
import com.example.notepad.controller.CadastrarNoteController;

import java.util.ArrayList;
import java.util.List;

public class CadastrarNoteActivity extends AppCompatActivity {

    private EditText editText;
    private List<String> listaDeNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_note);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Insira uma anotação!");
        findViews();

        listaDeNotes = new ArrayList<>();

        cliqueDoBotaoDeSalvar();

    }

    private void cliqueDoBotaoDeSalvar() {
        Button button = findViewById(R.id.buttonCadastrar);
        final Switch mSwitchSair = findViewById(R.id.switchSairAutomaticamente);
        final Switch mSwitchLimparCampo = findViewById(R.id.switchLimparCampo);

        final CadastrarNoteController cadastrarNoteController = new CadastrarNoteController();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cadastrarNoteController.editTextNaoEstaVazio(editText)) {

                    //TODO: SALVAR OPÇÃO DO SWITCH NO DB

                    cadastrarNoteController.adicionaNote(listaDeNotes, editText.getText().toString());

                    Toast.makeText(CadastrarNoteActivity.this, "Nota salva", Toast.LENGTH_SHORT).show();

                    if (cadastrarNoteController.switchDeSaiDaTelarPressionado(mSwitchSair)) {
                        finish();
                    }

                    if (cadastrarNoteController.switchDeLimparCampoPressionado(mSwitchLimparCampo)) {
                        editText.setText("");
                    }

                } else {
                    Toast.makeText(CadastrarNoteActivity.this, "O campo não pode estar vazio", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void defineAlertDialogDeEditText() {
        CadastrarNoteController cadastrarNoteController = new CadastrarNoteController();

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

    private void findViews() {
        editText = findViewById(R.id.editTextAddNote);
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

