package com.example.notepad.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.notepad.R;
import com.example.notepad.controller.CadastrarNoteController;
import com.example.notepad.database.asynctask.cominterface.AsyncTaskSave;
import com.example.notepad.model.Notepad;
import com.example.notepad.model.NotepadActivityConstantes;

public class CadastrarNoteActivity extends AppCompatActivity implements NotepadActivityConstantes {

    private EditText editText;
    private Context context;
    private CadastrarNoteController cadastrarNoteController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_note);
        setTitle("Insira uma anotação!");
        findViews();

        context = CadastrarNoteActivity.this;
        cadastrarNoteController = new CadastrarNoteController(context);
    }

    private void salvaTextoNoBancoDeDados() {
        //TODO: SALVAR OPÇÃO DO SWITCH NO SHAREDPREFERENCES

        final Switch mSwitchSair = findViewById(R.id.switchSairAutomaticamente);
        final Switch mSwitchLimparCampo = findViewById(R.id.switchLimparCampo);

        CadastrarNoteController cadastrarNoteControllerDataBase = new CadastrarNoteController(context);

        Notepad notepad = new Notepad();
        notepad.setAnotacaoRealizada(editText.getText().toString());

        cadastrarNoteControllerDataBase.adicionarNoteNoBancoDeDados(notepad, new AsyncTaskSave.QuandoSalvarListener() {
            @Override
            public void quandoSalvar() {
                if (cadastrarNoteControllerDataBase.switchDeSairDaTelaPressionado(mSwitchSair)) {
                    finish();
                }

                if (cadastrarNoteControllerDataBase.switchDeLimparCampoPressionado(mSwitchLimparCampo)) {
                    editText.setText("");
                }
            }
        });
    }

    private void defineAlertDialogDeEditText() {
        CadastrarNoteController cadastrarNoteController = new CadastrarNoteController(context);

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

    private void setaInformacaoDoNotepad(Notepad notepad) {
        if (notepad != null) {
            notepad.setAnotacaoRealizada(editText.getText().toString());
        }
    }

    private void setaInformacaDoEditText(Notepad notepad) {
        editText.setText(notepad.getAnotacaoRealizada());
    }

    private void defineUIDeEdicao() {

        Intent intent = getIntent();
        Notepad notepad = null;

        if (intent.hasExtra())
    }

    private void findViews() {
        editText = findViewById(R.id.editTextAddNote);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.salvarNote:
                if (cadastrarNoteController.editTextNaoEstaVazio(editText)) {
                    salvaTextoNoBancoDeDados();
                    Toast.makeText(CadastrarNoteActivity.this, "Nota salva", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CadastrarNoteActivity.this, "O campo não pode estar vazio", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.onoptions_menu_cadastro, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        defineAlertDialogDeEditText();
    }
}

