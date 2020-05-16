package com.example.notepad.database.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import com.example.notepad.database.dao.ConsultasDAO;
import com.example.notepad.database.dao.mRoomDatabase;
import com.example.notepad.model.Notepad;

public class AsyncTaskDelete extends AsyncTask<Void, Integer, Void> {

    private ConsultasDAO consultasDAO;
    private Notepad notepad;
    private QuandoDeletarListener quandoDeletarListener;
    private Context context;

    public AsyncTaskDelete(Notepad notepad, QuandoDeletarListener quandoDeletarListener) {
        this.notepad = notepad;
        this.quandoDeletarListener = quandoDeletarListener;

        mRoomDatabase roomDatabase = mRoomDatabase.getDatabase(context);
        consultasDAO = roomDatabase.consultasDAO();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        //Método que vai rodar a task em segundo plano.
        consultasDAO.deleteDAO(notepad);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        //Interface genérica responsável por aplicar o onPostExecute;
        quandoDeletarListener.quandoDeletar();
        super.onPostExecute(aVoid);
    }

    public interface QuandoDeletarListener {
        void quandoDeletar();
    }

}
