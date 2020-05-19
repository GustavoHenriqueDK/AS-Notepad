package com.example.notepad.controller;

import android.content.Context;
import android.widget.EditText;
import android.widget.Switch;

import com.example.notepad.database.asynctask.AsyncTaskDeleteSemInterface;
import com.example.notepad.database.asynctask.AsyncTaskGetSemInterface;
import com.example.notepad.database.asynctask.cominterface.AsyncTaskDelete;
import com.example.notepad.database.asynctask.cominterface.AsyncTaskGet;
import com.example.notepad.database.asynctask.cominterface.AsyncTaskSave;
import com.example.notepad.database.asynctask.cominterface.AsyncTaskUpdate;
import com.example.notepad.database.dao.ConsultasDAO;
import com.example.notepad.database.dao.mRoomDatabase;
import com.example.notepad.model.Notepad;

import java.util.ArrayList;
import java.util.List;

public class CadastrarNoteController {

    private static List<Notepad> listaDeNotes = new ArrayList<>();
    private Context context;
    private ConsultasDAO consultasDAO;

    public CadastrarNoteController(Context context) {
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

    //Métodos database sem interface;
    public List<Notepad> pegaNoteNoBancoDeDadosSemInterface() {
        AsyncTaskGetSemInterface asyncTaskGetSemInterface = new AsyncTaskGetSemInterface(consultasDAO);
        asyncTaskGetSemInterface.execute();
        return null;
    }

    public List<Notepad> deletaNoteNoBancoDeDadosSemInterface(Notepad notepad) {
        AsyncTaskDeleteSemInterface asyncTaskDeleteSemInterface = new AsyncTaskDeleteSemInterface(notepad);
        asyncTaskDeleteSemInterface.execute();
        return null;
    }


}