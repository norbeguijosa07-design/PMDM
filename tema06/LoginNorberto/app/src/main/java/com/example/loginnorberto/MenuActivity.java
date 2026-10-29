package com.example.loginnorberto;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;

public class MenuActivity extends AppCompatActivity {
    private Button btCerrarSesion;
    private TextView tvSaludoUsuario;
    private MaterialToolbar toolbar;
    public static final String EXTRA_CORREO = "correo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String correo = getIntent().getStringExtra(EXTRA_CORREO);
        btCerrarSesion = findViewById(R.id.cerrarSesion);
        tvSaludoUsuario = findViewById(R.id.saludoUsuario);
        tvSaludoUsuario.setText(getString(R.string.saludo_correo, correo));

        btCerrarSesion.setOnClickListener(v ->{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            return insets;
        });
    }
}