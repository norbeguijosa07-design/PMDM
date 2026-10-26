package com.example.listanorberto;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
public class AdaptadorVideojuegos extends RecyclerView.Adapter<AdaptadorVideojuegos.ViewHolder> {
    // La lista de datos que va a pintar
    private final ArrayList<VideoJuegos> videojuego;
    public AdaptadorVideojuegos(ArrayList<VideoJuegos> videojuego) {
        this.videojuego = videojuego;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View fila = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fila_videojuego, parent, false);
        return new ViewHolder(fila);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VideoJuegos videojuegos = videojuego.get(position);
        holder.tvTitulo.setText(videojuegos.getTitulo());
        holder.tvSubtitulo.setText(videojuegos.getPlataforma());
        holder.ivImagen.setImageResource(videojuegos.getImagen());

        holder.ivImagen.setImageResource(videojuegos.getImagen());

        holder.itemView.setOnClickListener(v ->
                Toast.makeText(v.getContext(),
                        videojuegos.getTitulo(),
                        Toast.LENGTH_SHORT).show());
    }

    @Override
    public int getItemCount() {
        return videojuego.size();
    }

    // El ViewHolder guarda las vistas de UNA fila
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImagen;
        TextView tvTitulo;
        TextView tvSubtitulo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImagen = itemView.findViewById(R.id.ivImagen);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvSubtitulo = itemView.findViewById(R.id.tvSubtitulo);
        }
    }
}