package com.example.notepad.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_cliente")
public class Notepad {

    private String anotacaoRealizada;

    public Notepad(){}

    @PrimaryKey(autoGenerate = true)
    public int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Notepad(String anotacaoRealizada) {
        this.anotacaoRealizada = anotacaoRealizada;
    }

    public void setAnotacaoRealizada(String anotacaoRealizada) {
        this.anotacaoRealizada = anotacaoRealizada;
    }

    public String getAnotacaoRealizada() {
        return anotacaoRealizada;
    }

}
