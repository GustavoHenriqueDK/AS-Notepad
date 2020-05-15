package com.example.notepad.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.notepad.R;
import com.example.notepad.controller.CadastrarNoteController;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final List<String> listNotePad = new ArrayList<>();
    private Context context;
    private RecyclerView recyclerView;
    private CadastrarNoteController cadastrarNoteController;
    private static RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Faça suas anotações!");

        cadastrarNoteController = new CadastrarNoteController(context);

        setaAdapterRecyclerView();
        floatingActionButtonCadastrarNote();

    }

    private void setaAdapterRecyclerView() {
        recyclerAdapter = new RecyclerAdapter(cadastrarNoteController.getListaDeNotes(), context);
        recyclerView = findViewById(R.id.recyclerView);

        setaSwipeDeDeletar(cadastrarNoteController.getListaDeNotes(), recyclerAdapter, recyclerView);

        cadastrarNoteController.getListaDeNotes().add("bla");
        cadastrarNoteController.getListaDeNotes().add("bla");

        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(cadastrarNoteController.getListaDeNotes(), context);

        Log.e("retorno na main", String.valueOf(cadastrarNoteController.getListaDeNotes()));

        recyclerAdapter.notifyDataSetChanged();
    }

    private void setaSwipeDeDeletar(final List<String> notePad, final RecyclerAdapter recyclerAdapter, RecyclerView recyclerView) {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                notePad.remove(viewHolder.getAdapterPosition());
                recyclerAdapter.notifyDataSetChanged();
            }
            //Método que adapta a lista após ser modificada.
        }).attachToRecyclerView(recyclerView);
    }

    private void floatingActionButtonCadastrarNote() {
        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastrarNoteActivity.class);
                startActivity(intent);
            }
        });
    }
}