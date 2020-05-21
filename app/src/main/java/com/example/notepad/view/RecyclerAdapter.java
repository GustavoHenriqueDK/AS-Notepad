package com.example.notepad.view;

import android.content.Context;
import android.net.wifi.p2p.WifiP2pManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notepad.R;
import com.example.notepad.controller.CadastrarNoteController;
import com.example.notepad.model.Notepad;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ListaViewHolder> {

    private static List<Notepad> listaDeNotas;
    private Context context;
    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setNotes(List<Notepad> listaDeNotas) {
        this.listaDeNotas = listaDeNotas;
    }

    public RecyclerAdapter(List<Notepad> listaDeNotas, Context context) {
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
        holder.textNote.setText(listaDeNotas.get(position).getAnotacaoRealizada());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaDeNotas.size();
    }

    static class ListaViewHolder extends RecyclerView.ViewHolder {

        private TextView textNote;
        private ConstraintLayout constraintLayout;

        public ListaViewHolder(@NonNull final View itemView) {
            super(itemView);
            textNote = itemView.findViewById(R.id.textView);
            constraintLayout = itemView.findViewById(R.id.constraintLayout);
        }
    }

    public interface OnClickListener {
        void onClick(int position);
    }
}