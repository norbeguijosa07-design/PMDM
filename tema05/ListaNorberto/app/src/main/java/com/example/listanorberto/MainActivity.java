package com.example.listanorberto;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // … lo que trae la plantilla (EdgeToEdge, setContentView…): no lo borres
        // 1. Los datos: al menos 8 elementos
        ArrayList<VideoJuegos> videojuegos = new ArrayList<>();
        videojuegos.add(new VideoJuegos("Guild Wars 2", "PC", R.drawable.gw2));
        videojuegos.add(new VideoJuegos("Red Dead Redemption 2", "PlayStation 4", R.drawable.rdr2));
        videojuegos.add(new VideoJuegos("Crash Bandicoot", "PlayStation 1", R.drawable.crashbandicoot));
        videojuegos.add(new VideoJuegos("Spyro the Dragon", "PlayStation 1", R.drawable.spyro));
        videojuegos.add(new VideoJuegos("Destiny 2", "PC, PlayStation 4", R.drawable.destiny));
        videojuegos.add(new VideoJuegos("Grand Theft Auto VI", "PlayStation 5", R.drawable.gta));
        videojuegos.add(new VideoJuegos("Minecraft", "PC", R.drawable.minecraft));
        videojuegos.add(new VideoJuegos("Osu!", "PC", R.drawable.osu));
        videojuegos.add(new VideoJuegos("Among Us", "Multiplataforma", R.drawable.amongus));
        // … y así hasta 8 o más
        // 2. La lista: cómo se colocan las filas y quién las pinta
        RecyclerView rvLista = findViewById(R.id.rvLista);
        rvLista.setLayoutManager(new LinearLayoutManager(this));
        rvLista.setAdapter(new AdaptadorVideojuegos(videojuegos));
    }

}