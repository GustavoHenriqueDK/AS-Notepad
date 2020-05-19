package com.example.notepad.database.asynctask.cominterface;

import android.content.Context;
import android.os.AsyncTask;

import com.example.notepad.database.dao.ConsultasDAO;
import com.example.notepad.database.mRoomDatabase;
import com.example.notepad.model.Notepad;

import java.util.List;

public class AsyncTaskDelete extends AsyncTask<Void, Integer, Void> {

    private ConsultasDAO consultasDAO;
    private Notepad notepad;
    private Context context;

    public AsyncTaskDelete(Notepad notepad) {
        this.notepad = notepad;

        mRoomDatabase roomDatabase = mRoomDatabase.getDatabase(context);
        consultasDAO = roomDatabase.consultasDAO();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        //Método que vai rodar a task em segundo plano.
        consultasDAO.deleteDAO(notepad);
        return null;
    }
}
