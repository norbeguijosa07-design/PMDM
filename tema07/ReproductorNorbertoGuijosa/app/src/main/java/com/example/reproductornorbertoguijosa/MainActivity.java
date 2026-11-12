package com.example.reproductornorbertoguijosa;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Player;
import androidx.media3.exoplayer.ExoPlayer;

public class MainActivity extends AppCompatActivity {

    private ExoPlayer exoPlayer;
    private Button btnPlay;
    private Button btnPausa;


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

        exoPlayer = new ExoPlayer.Builder(this).build();
        Uri uri = Uri.parse("android.resource://"
                + getPackageName() + "/" + R.raw.musica);
        exoPlayer.setMediaItem(MediaItem.fromUri(uri));
        exoPlayer.prepare();

        exoPlayer.addListener(new Player.Listener() {
            @Override
            public void onPlaybackStateChanged(int estado) {
                if (estado == Player.STATE_ENDED) {
                    long duracion = exoPlayer.getDuration();

                }
            }
        });
        btnPlay.setOnClickListener(v -> exoPlayer.play());
        btnPausa.setOnClickListener(v -> exoPlayer.pause());

    }
}