package com.example.reproductornorbertoguijosa;

import android.media.AudioAttributes;
import android.media.SoundPool;
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

    private SoundPool soundPool;
    private Button btnTeleport;
    private Button btnFood;
    private Button btnLevelUp;
    private Button btnVillager;
    private int cargados = 0;


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
        btnTeleport = findViewById(R.id.buttonTeleport);
        btnFood = findViewById(R.id.buttonFood);
        btnLevelUp = findViewById(R.id.buttonLevelUp);
        btnVillager = findViewById(R.id.buttonVillager);

        exoPlayer = new ExoPlayer.Builder(this).build();
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.musica);
        exoPlayer.setMediaItem(MediaItem.fromUri(uri));
        exoPlayer.prepare();

        AudioAttributes atributos = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(4)
                .setAudioAttributes(atributos)
                .build();

        int idEfecto1Teleport = soundPool.load(this, R.raw.teleport, 1);
        int idEfecto2Food = soundPool.load(this, R.raw.food, 2);
        int idEfecto3LevelUp = soundPool.load(this, R.raw.level_up, 3);
        int idEfecto4Villager = soundPool.load(this, R.raw.villager, 4);

        soundPool.setOnLoadCompleteListener((pool, idSonido, estado) -> {
            if (estado == 0) {
                cargados++;
                if (cargados == 4) {
                    btnTeleport.setEnabled(true);
                    btnFood.setEnabled(true);
                    btnLevelUp.setEnabled(true);
                    btnVillager.setEnabled(true);
                }
            }
        });

        btnTeleport.setOnClickListener(v -> soundPool.play(idEfecto1Teleport,
                1f, 1f, 1, 0, 1f));
        btnFood.setOnClickListener(v -> soundPool.play(idEfecto2Food, 1f,
                1f, 1, 0, 1f));
        btnLevelUp.setOnClickListener(v -> soundPool.play(idEfecto3LevelUp,
                1f, 1f, 1, 0, 1f));
        btnVillager.setOnClickListener(v -> soundPool.play(idEfecto4Villager,
                1f, 1f, 1, 0, 1f));

        exoPlayer.addListener(new Player.Listener() {
            @Override
            public void onPlaybackStateChanged(int estado) {

                if (estado == Player.STATE_READY){
                    long duracion = exoPlayer.getDuration();
                    sb.setMax((int)duracion);
                    tvDuracionTotal.setText(formatear(duracion));
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

        btnPlay.setOnClickListener(v -> {
            if (exoPlayer.getPlaybackState() == Player.STATE_ENDED) {
                exoPlayer.seekTo(0);
            }
            exoPlayer.play();

        });
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
        soundPool.release();
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