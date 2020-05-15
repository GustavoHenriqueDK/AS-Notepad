package com.example.notepad.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notepad.R;
import com.example.notepad.controller.CadastrarNoteController;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private RecyclerView recyclerView;
    private CadastrarNoteController cadastrarNoteController;
    private static RecyclerAdapter recyclerAdapter;

    private ConstraintLayout constraintLayoutLista;
    private ConstraintLayout constraintLayoutTextView;
    TextView textView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Faça suas anotações!");
        findViews();

        context = MainActivity.this;
        cadastrarNoteController = new CadastrarNoteController(context);

        setaAdapterRecyclerView();
        floatingActionButtonCadastrarNote();


    }

    private void verificaSeListaEstaVazia() {
        if (cadastrarNoteController.listaEstaVazia()) {
            constraintLayoutLista.setVisibility(View.GONE);
            constraintLayoutTextView.setVisibility(View.VISIBLE);
            iniciaAnimacaoDosComponentes();
            cadastrarNoteController.setaTextoAleatoriamente(textView);
        } else {
            constraintLayoutLista.setVisibility(View.VISIBLE);
            constraintLayoutTextView.setVisibility(View.GONE);
        }
    }

    private void iniciaAnimacaoDosComponentes() {
        Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadein_animation);
        textView.startAnimation(animation);
        imageView.startAnimation(animation);
    }

    private void setaAdapterRecyclerView() {
        recyclerAdapter = new RecyclerAdapter(cadastrarNoteController.getListaDeNotes(), context);
        recyclerView = findViewById(R.id.recyclerView);

        setaSwipeDeDeletar(cadastrarNoteController.getListaDeNotes(), recyclerAdapter, recyclerView);

        cadastrarNoteController.getListaDeNotes().add("bla");
        cadastrarNoteController.getListaDeNotes().add("bla");
        cadastrarNoteController.getListaDeNotes().add("bla");

        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerAdapter.notifyDataSetChanged();

        //Método chamado no onResume e no método "setaSwipeDeDeletar", logo após os itens
        //terem sido arrastados para o delete e o RecyclerView tenha sido atualizado.

        verificaSeListaEstaVazia();

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

                verificaSeListaEstaVazia();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.onoptions_menu, menu);
        return true;
    }

    private void findViews() {
        constraintLayoutLista = findViewById(R.id.ConstraintLayoutLista);
        constraintLayoutTextView = findViewById(R.id.ConstraintLayoutText);

        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.limparTudo:

                //TODO

                for (int i = 0; i < cadastrarNoteController.getListaDeNotes().size(); i++) {
                    cadastrarNoteController.getListaDeNotes().remove(i);
                }

                recyclerAdapter.notifyDataSetChanged();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}