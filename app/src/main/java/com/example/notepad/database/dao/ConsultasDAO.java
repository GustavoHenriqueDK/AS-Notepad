package com.example.notepad.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.notepad.model.Notepad;
import java.util.List;

@Dao
public interface ConsultasDAO {

    @Insert
    void insertDAO(Notepad notepad);

    @Delete
    void deleteDAO(Notepad notepad);

    @Update
    void updateDAO(Notepad notepad);

    @Query("SELECT * FROM table_notepad")
    List<Notepad> getNotepad();

    @Delete
    void deleteDAOSemInterface(List<Notepad> notepad);
}
