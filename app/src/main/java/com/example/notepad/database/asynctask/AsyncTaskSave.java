package com.example.notepad.database.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.RoomDatabase;

import com.example.notepad.database.dao.ConsultasDAO;
import com.example.notepad.database.dao.mRoomDatabase;
import com.example.notepad.model.Notepad;

public class AsyncTaskSave extends AsyncTask<Void, Integer, Void> {

    private ConsultasDAO consultasDAO;
    private Notepad notepad;
    private QuandoSalvarListener quandoSalvarListener;
    private Context context;

    public AsyncTaskSave(Notepad notepad, QuandoSalvarListener quandoSalvarListener) {
        this.notepad = notepad;
        this.quandoSalvarListener = quandoSalvarListener;

        mRoomDatabase roomDatabase = mRoomDatabase.getDatabase(context);
        consultasDAO = roomDatabase.consultasDAO();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        //Método que vai rodar a task em segundo plano.
        consultasDAO.insertDAO(notepad);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        //Interface genérica responsável por aplicar o onPostExecute;
        quandoSalvarListener.quandoSalvar();
        super.onPostExecute(aVoid);
    }

    public interface QuandoSalvarListener {
        void quandoSalvar();
    }

}
