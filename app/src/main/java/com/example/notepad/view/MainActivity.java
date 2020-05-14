package com.example.notepad.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.notepad.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> notePad;
    private Context context;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Faça suas anotações!");

        notePad = new ArrayList<>();

        setaAdapterRecyclerView();
        floatingActionButtonCadastrarNote();

    }

    private void setaAdapterRecyclerView() {
        final RecyclerAdapter recyclerAdapter = new RecyclerAdapter(notePad, context);
        recyclerView = findViewById(R.id.recyclerView);

        notePad.add("Ser bilionário");
        notePad.add("Colocar o lixo pra fora");
        notePad.add("Fazer o almoço");
        notePad.add("Arrumar o guarda-roupas");

        setaSwipeDeDeletar(notePad, recyclerAdapter, recyclerView);

        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
