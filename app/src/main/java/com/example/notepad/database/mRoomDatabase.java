package com.example.notepad.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.notepad.database.dao.ConsultasDAO;
import com.example.notepad.model.Notepad;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Notepad.class}, version = 1, exportSchema = false)

public abstract class mRoomDatabase extends androidx.room.RoomDatabase {

    public abstract ConsultasDAO consultasDAO();

    private static final int NUMBER_OF_THREADS = 4;

    public Notepad notepad;

    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static volatile mRoomDatabase INSTANCE;

    public static mRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (mRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            mRoomDatabase.class, "notepad_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static mRoomDatabase.Callback sRoomDatabaseCallback = new mRoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {

                ConsultasDAO consultasDAO = INSTANCE.consultasDAO();

                Notepad notepad = new Notepad();
                notepad.setAnotacaoRealizada("Anote tuudo! Arraste para deletar.");
                consultasDAO.insertDAO(notepad);

            });
        }
    };
}