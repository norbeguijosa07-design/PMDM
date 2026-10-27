package com.example.loginnorberto;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.material.materialswitch.MaterialSwitch;
import android.widget.TextView;
import android.widget.Toast;

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
    private MaterialSwitch sRecordar;
    int contador = 0;
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

            if (correo.isEmpty()){
                tCorreo.setError("Escribe tu correo");
            } else if (contrasenia.isEmpty()) {
                tContrasenia.setError("Escribe la contraseña");
            }else if (correo.equals("norberto@correo.com")&& contrasenia.equals("1234")){
                contador = 0;
                tvMensaje.setTextColor(Color.GREEN);
                Intent intent = new Intent(this, MenuActivity.class);
                intent.putExtra(MenuActivity.EXTRA_CORREO, correo);
                startActivity(intent);
                finish();
            }else{
                tvMensaje.setTextColor(Color.RED);
                contador ++;
                if (contador == 3){
                    bContinuar.setEnabled(false);
                    Toast.makeText(this,"Demasiados intentos",Toast.LENGTH_SHORT).show();
                    tvMensaje.setText("Demasiados intentos");
                }else {
                    tvMensaje.setText("Usuario y/o contraseña incorrectos. Quedan: " + (3-contador) + " intentos");
                }
            }
        });
    }

}