package com.example.notepad.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notepad.R;
import com.example.notepad.controller.CadastrarNoteController;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ListaViewHolder> {

    private static List<String> listaDeNotas = new ArrayList<>();
    Context context;

    public RecyclerAdapter(List<String> listaDeNotas, Context context) {
        this.listaDeNotas = listaDeNotas;
        this.context = context;
    }

    @NonNull
    @Override
    public ListaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_layout, parent, false);
        return new ListaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaViewHolder holder, int position) {
        holder.textNote.setText(listaDeNotas.get(position));
    }

    @Override
    public int getItemCount() {
        return listaDeNotas.size();
    }

    static class ListaViewHolder extends RecyclerView.ViewHolder {

        private TextView textNote;

        public ListaViewHolder(@NonNull final View itemView) {
            super(itemView);
            textNote = itemView.findViewById(R.id.textView);
        }
    }

}
