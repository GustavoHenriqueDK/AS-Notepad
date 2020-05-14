package com.example.notepad.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.example.notepad.R;

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

    }

    private void setaAdapterRecyclerView() {


        final RecyclerAdapter recyclerAdapter = new RecyclerAdapter(notePad, context);
        recyclerView = findViewById(R.id.recyclerView);

        notePad.add("Ser bilionário");
        notePad.add("Colocar o lixo pra fora");
        notePad.add("Fazer o almoço");
        notePad.add("Arrumar o guarda-roupas");

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


        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter.notifyDataSetChanged();
    }



}
