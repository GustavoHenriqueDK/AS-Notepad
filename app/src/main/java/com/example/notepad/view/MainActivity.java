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
import com.example.notepad.controller.MainActivityController;
import com.example.notepad.database.asynctask.cominterface.AsyncTaskDelete;
import com.example.notepad.database.asynctask.cominterface.AsyncTaskGet;
import com.example.notepad.model.Notepad;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private RecyclerView recyclerView;
    private CadastrarNoteController cadastrarNoteController;
    private MainActivityController mainActivityController;
    private static RecyclerAdapter recyclerAdapter;
    public List<Notepad> notepadList = new ArrayList<>();

    private ConstraintLayout constraintLayoutLista;
    private ConstraintLayout constraintLayoutTextView;
    private TextView textView;
    private ImageView imageView;

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
        setaSwipeDeDeletar();
        //verificaSeListaEstaVazia();

    }

    //TODO
    private void verificaSeListaEstaVazia() {
        if (cadastrarNoteController.listaEstaVazia()) {
            constraintLayoutLista.setVisibility(View.GONE);
            constraintLayoutTextView.setVisibility(View.VISIBLE);
            iniciaAnimacaoDosComponentes();
            mainActivityController.setaTextoAleatoriamente(textView);
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
        recyclerAdapter = new RecyclerAdapter(notepadList, context);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        recyclerAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(recyclerAdapter);
        setaSwipeDeDeletar();
    }

    @Override
    protected void onResume() {
        super.onResume();

        cadastrarNoteController.pegaNoteNoBancoDeDados(new AsyncTaskGet.QuandoBuscarNotes() {
            @Override
            public void quandoBuscar(List<Notepad> notepads) {

                notepadList = new ArrayList<>(notepads);

                recyclerAdapter.setNotes(notepadList);

                recyclerAdapter.notifyDataSetChanged();
            }
        });
    }

    private void setaSwipeDeDeletar() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Notepad notepad;
                notepad = notepadList.remove(viewHolder.getAdapterPosition());

                cadastrarNoteController.deletaNoteNoBancoDeDados(notepad);
                recyclerAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(recyclerAdapter);
            }
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

    //TODO
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.limparTudo:
                if (cadastrarNoteController.pegaNoteNoBancoDeDadosSemInterface().size() >= 1) {
                    if (cadastrarNoteController.pegaNoteNoBancoDeDadosSemInterface().size() >= 10) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage(R.string.alertDialogMensagemExcluir)
                                .setPositiveButton(R.string.alertDialogSim, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        cadastrarNoteController.pegaNoteNoBancoDeDadosSemInterface().clear();

                                        recyclerView.getRecycledViewPool().clear();
                                        recyclerAdapter.notifyDataSetChanged();

                                        //   verificaSeListaEstaVazia();

                                    }
                                })
                                .setNegativeButton(R.string.alertDialogNao, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                        AlertDialog alert11 = builder.create();
                        alert11.show();
                    } else {
                        cadastrarNoteController.getListaDeNotes().clear();
                    }
                    recyclerAdapter.notifyDataSetChanged();
                    //  verificaSeListaEstaVazia();
                } else {
                    Toast.makeText(context, "Tudo limpo!", Toast.LENGTH_SHORT).show();
                }
        }
        return super.onOptionsItemSelected(item);
    }
}