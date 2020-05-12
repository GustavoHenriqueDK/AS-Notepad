package com.example.notepad.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.example.notepad.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

     List<String> notePad;
    private Context context;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notePad = new ArrayList<>();

        setaAdapterRecyclerView();
    }

    private void setaAdapterRecyclerView() {

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(notePad, context);
        recyclerView = findViewById(R.id.recyclerView);

        notePad.add("Ser bilionário");
        notePad.add("Colocar o lixo pra fora");
        notePad.add("Fazer o almoço");
        notePad.add("Arrumar o guarda-roupas");

        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
