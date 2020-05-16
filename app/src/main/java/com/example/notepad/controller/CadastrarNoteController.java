package com.example.notepad.controller;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.example.notepad.database.asynctask.AsyncTaskDelete;
import com.example.notepad.database.asynctask.AsyncTaskGet;
import com.example.notepad.database.asynctask.AsyncTaskSave;
import com.example.notepad.database.asynctask.AsyncTaskUpdate;
import com.example.notepad.database.dao.ConsultasDAO;
import com.example.notepad.database.dao.mRoomDatabase;
import com.example.notepad.model.Notepad;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CadastrarNoteController {

    private static List<Notepad> listaDeNotes = new ArrayList<>();
    private Context context;
    private ConsultasDAO consultasDAO;

    public CadastrarNoteController (Context context) {
        this.context = context;

        mRoomDatabase roomDatabase = mRoomDatabase.getDatabase(context);
        consultasDAO = roomDatabase.consultasDAO();
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

        Notepad notepad = new Notepad();
        notepad.setAnotacaoRealizada(itemASerAdicionado);

        listaDeNotes.add(notepad);
    }

    public boolean listaEstaVazia() {
        if (listaDeNotes.isEmpty()) {
            return true;
        }
        return false;
    }

    public List<Notepad> getListaDeNotes() {
        return listaDeNotes;
    }

    //Métodos database

    public void adicionarNoteNoBancoDeDados(Notepad notepad, AsyncTaskSave.QuandoSalvarListener quandoSalvarListener) {
        AsyncTaskSave asyncTaskSave = new AsyncTaskSave(notepad, quandoSalvarListener);
        asyncTaskSave.execute();
    }

    public void deletaNoteNoBancoDeDados(Notepad notepad, AsyncTaskDelete.QuandoDeletarListener quandoDeletarListener) {
        AsyncTaskDelete asyncTaskDelete = new AsyncTaskDelete(notepad, quandoDeletarListener);
        asyncTaskDelete.execute();
    }

    public void atualizaNoteNoBancoDeDados(Notepad notepad, AsyncTaskUpdate.QuandoAlterarListener quandoAlterarListener) {
        AsyncTaskUpdate asyncTaskUpdate = new AsyncTaskUpdate(notepad, quandoAlterarListener);
        asyncTaskUpdate.execute();
    }

    public List<Notepad> pegaNoteNoBancoDeDados(AsyncTaskGet.QuandoBuscarNotes quandoBuscarNotes) {
        AsyncTaskGet asyncTaskGet = new AsyncTaskGet(consultasDAO, quandoBuscarNotes);
        asyncTaskGet.execute();
        return null;
    }


}