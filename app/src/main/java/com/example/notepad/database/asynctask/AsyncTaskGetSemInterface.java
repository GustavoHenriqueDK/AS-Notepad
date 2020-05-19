package com.example.notepad.database.asynctask;

import android.os.AsyncTask;

import com.example.notepad.database.dao.ConsultasDAO;
import com.example.notepad.model.Notepad;

import java.util.List;

public class  AsyncTaskGetSemInterface extends AsyncTask<Void, Integer, List<Notepad>> {

    private ConsultasDAO consultasDAO;
    private Notepad notepad;

    public AsyncTaskGetSemInterface(ConsultasDAO consultasDAO) {
        this.consultasDAO = consultasDAO;
    }

    @Override
    protected List<Notepad> doInBackground(Void... voids) {
        return consultasDAO.getNotepad();
    }
}
