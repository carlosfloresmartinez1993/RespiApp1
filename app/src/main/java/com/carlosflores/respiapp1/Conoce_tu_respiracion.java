package com.carlosflores.respiapp1;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Conoce_tu_respiracion extends AppCompatActivity {
    private boolean isPaused = false; // Estado para saber si se pausó
    private MediaPlayer mediaPlayer;
    private int currentAudioResId = -1; // para saber si se repite el mismo audio

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_conoce_tu_respiracion);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //codigo mio
        configurarReproductor(R.id.respiracion_saludable, R.raw.respiracionsaludable);
        configurarReproductor(R.id.frecuencia_resoiratoria, R.raw.frecuenciarespiratoria);
        configurarReproductor(R.id.afecta_pulmones, R.raw.afectapulmones);
        configurarReproductor(R.id.cuidados_preventivos, R.raw.cuidadospreventivos);
        configurarReproductor(R.id.consultar_medico, R.raw.consultarmedico);
        ImageView btn_regresar = findViewById(R.id.regesar);

        //redirigir si aprieto regresar
        btn_regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //parar audios que se esten reproducioendo
                if (mediaPlayer != null) {
                    mediaPlayer.release();
                    mediaPlayer = null;
                }
                // Ir a otra actividad
                Intent intent = new Intent(Conoce_tu_respiracion.this, MenuPrincipal.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void configurarReproductor(int imageViewId, int audioResId) {
        ImageView imageView = findViewById(imageViewId);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reproducirAudio(audioResId);
            }
        });
    }

    private void reproducirAudio(int audioResId) {
        if (mediaPlayer != null && currentAudioResId == audioResId) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                isPaused = true;
            } else {
                mediaPlayer.start(); // Continúa desde donde se pausó
                isPaused = false;
            }
        } else {
            if (mediaPlayer != null) {
                mediaPlayer.release();
            }
            mediaPlayer = MediaPlayer.create(this, audioResId);
            currentAudioResId = audioResId;
            mediaPlayer.start();
            isPaused = false;

            mediaPlayer.setOnCompletionListener(mp -> {
                mediaPlayer.release();
                mediaPlayer = null;
                currentAudioResId = -1;
                isPaused = false;
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}