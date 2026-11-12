package com.example.reproductornorbertoguijosa;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Player;
import androidx.media3.exoplayer.ExoPlayer;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ExoPlayer exoPlayer;
    private Button btnPlay;
    private Button btnPausa;

    private SeekBar sb;

    private TextView tvDuracionTotal;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private TextView tvActual;
    private boolean arrastrando;


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

        btnPausa = findViewById(R.id.buttonPause);
        btnPlay = findViewById(R.id.buttonPlay);
        sb = findViewById(R.id.seekBar);
        tvDuracionTotal = findViewById(R.id.textViewDuracionTotal);
        tvActual = findViewById(R.id.textViewTiempo);

        exoPlayer = new ExoPlayer.Builder(this).build();
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.musica);
        exoPlayer.setMediaItem(MediaItem.fromUri(uri));
        exoPlayer.prepare();

        exoPlayer.addListener(new Player.Listener() {
            @Override
            public void onPlaybackStateChanged(int estado) {

                if (estado == Player.STATE_READY){
                    long duracion = exoPlayer.getDuration();
                    sb.setMax((int)duracion);
                    tvDuracionTotal.setText(formatear(duracion));

                }
                if (estado == Player.STATE_ENDED) {
                    exoPlayer.seekTo(0);
                    exoPlayer.play();
                }
            }
        });


        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onStartTrackingTouch(SeekBar barra) {
                arrastrando = true;

            }
            @Override
            public void onProgressChanged(SeekBar barra, int progreso, boolean delUsuario) {
                if (delUsuario) tvActual.setText(formatear(progreso));
            }
            @Override
            public void onStopTrackingTouch(SeekBar barra) {
                exoPlayer.seekTo(barra.getProgress());
                arrastrando = false;
            }
        });

        btnPlay.setOnClickListener(v -> exoPlayer.play());
        btnPausa.setOnClickListener(v -> exoPlayer.pause());

    }

    @Override
    protected void onStart() {
        super.onStart();
        handler.post(actualizar);
    }
    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(actualizar);
        exoPlayer.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        exoPlayer.release();
    }
    private String formatear(long ms) {
        long segundos = ms / 1000;
        return String.format(Locale.getDefault(),
                "%02d:%02d", segundos / 60, segundos % 60);
    }
    final Runnable actualizar = new Runnable() {
        @Override
        public void run() {
            if (!arrastrando) {
            long posicion = exoPlayer.getCurrentPosition();
            sb.setProgress((int) posicion);
            tvActual.setText(formatear(posicion));
            }
            handler.postDelayed(this, 500);
        }
    };
}