package com.example.notepad.database.asynctask.seminterface;

import android.content.Context;
import android.os.AsyncTask;

import com.example.notepad.database.dao.ConsultasDAO;
import com.example.notepad.database.mRoomDatabase;
import com.example.notepad.model.Notepad;

import java.util.List;

public class AsyncTaskDeleteSemInterface extends AsyncTask<Void, Integer, Void> {

    private ConsultasDAO consultasDAO;
    private List<Notepad> notepad;
    private Context context;

    public AsyncTaskDeleteSemInterface(List<Notepad> notepad) {
        this.notepad = notepad;

        mRoomDatabase roomDatabase = mRoomDatabase.getDatabase(context);
        consultasDAO = roomDatabase.consultasDAO();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        //Método que vai rodar a task em segundo plano.
        consultasDAO.deleteDAOSemInterface(notepad);
        return null;
    }
}