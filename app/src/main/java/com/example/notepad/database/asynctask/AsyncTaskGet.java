package com.example.notepad.database.asynctask;

import android.os.AsyncTask;
import android.provider.ContactsContract;

import com.example.notepad.database.dao.ConsultasDAO;
import com.example.notepad.model.Notepad;
import java.util.List;

public class  AsyncTaskGet extends AsyncTask<Void, Integer, List<Notepad>> {

    private ConsultasDAO consultasDAO;
    private QuandoBuscarNotes quandoBuscarNotes;
    private Notepad notepad;

    public AsyncTaskGet(ConsultasDAO consultasDAO, QuandoBuscarNotes quandoBuscarNotes) {
        this.consultasDAO = consultasDAO;
        this.quandoBuscarNotes = quandoBuscarNotes;
    }

    @Override
    protected List<Notepad> doInBackground(Void... voids) {
        return consultasDAO.getNotepad();
    }

    @Override
    protected void onPostExecute(List<Notepad> notepads) {
        super.onPostExecute(notepads);
        quandoBuscarNotes.quandoBuscar(notepads);
    }

    public interface QuandoBuscarNotes {
        void quandoBuscar(List<Notepad> notepads);
    }

}
