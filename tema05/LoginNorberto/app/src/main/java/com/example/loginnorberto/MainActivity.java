package com.example.loginnorberto;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText tCorreo;
    private EditText tContrasenia;
    private Button bContinuar;
    private TextView tvMensaje;
    private Switch sRecordar;

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
        tCorreo= findViewById(R.id.etCorreo);
        tContrasenia = findViewById(R.id.etContrasenia);
        bContinuar = findViewById(R.id.bContinuar);
        sRecordar = findViewById(R.id.sRecordar);
        tvMensaje = findViewById(R.id.tvMensaje);

        bContinuar.setOnClickListener(v ->{
            String correo = tCorreo.getText().toString();
            String contrasenia = tContrasenia.getText().toString();
            Boolean recordar = sRecordar.isChecked();

            if (correo.equals("norberto@correo.com")&& contrasenia.equals("1234")){
                tvMensaje.setTextColor(Color.GREEN);
                if (recordar){
                    tvMensaje.setText("Usuario y contraseña correctos. Almacenados para siguientes accesos");
                }else {
                    tvMensaje.setText("Usuario y contraseña correctos");
                }
            }else{
                tvMensaje.setTextColor(Color.RED);
                tvMensaje.setText("Usuario y/o contraseña incorrectos");
            }
        });
    }

}