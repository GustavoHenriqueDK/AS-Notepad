package com.example.notepad.database.asynctask.cominterface;

import android.content.Context;
import android.os.AsyncTask;

import com.example.notepad.database.dao.ConsultasDAO;
import com.example.notepad.database.mRoomDatabase;
import com.example.notepad.model.Notepad;

public class AsyncTaskUpdate extends AsyncTask<Void, Integer, Void> {

    private ConsultasDAO consultasDAO;
    private Notepad notepad;
    private QuandoAlterarListener quandoAlterarListener;
    private Context context;

    public AsyncTaskUpdate(Notepad notepad, QuandoAlterarListener quandoAlterarListener) {
        this.notepad = notepad;
        this.quandoAlterarListener = quandoAlterarListener;

        mRoomDatabase roomDatabase = mRoomDatabase.getDatabase(context);
        consultasDAO = roomDatabase.consultasDAO();

    }

    @Override
    protected Void doInBackground(Void... voids) {
        consultasDAO.updateDAO(notepad);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        quandoAlterarListener.quandoAlterar();
        super.onPostExecute(aVoid);
    }

    public interface QuandoAlterarListener {
        void quandoAlterar();
    }
}
