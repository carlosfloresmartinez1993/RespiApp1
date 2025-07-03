package com.carlosflores.respiapp1;

import static com.carlosflores.respiapp1.R.*;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Resp_diafragmatica extends AppCompatActivity {
    private boolean isPaused = false; // Estado para saber si se pausó
    private MediaPlayer mediaPlayer;
    private int currentAudioResId = -1; // para saber si se repite el mismo audio

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resp_diafragmatica);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        WebView webView = findViewById(id.video_resp_diafragmatica);
        webView.setWebChromeClient(new WebChromeClient()); // Necesario para reproducir video
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);

// Incrustar el video usando el ID de YouTube
        String html = "<iframe width=\"100%\" height=\"100%\" " +
                "src=\"https://www.youtube.com/embed/TuPaMCsnxes\" " +
                "frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" " +
                "allowfullscreen></iframe>";

        webView.loadData(html, "text/html", "utf-8");

        configurarReproductor(id.play_e_resp_diafragmatica, raw.e_respiraciondiafragmatica);


        //botones para iniciar ejercicio
        Button btn_resp_diafragmatica_iniciar = findViewById(R.id.btn_resp_diafragmatica);
        btn_resp_diafragmatica_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null) {
                    mediaPlayer.release();
                    mediaPlayer = null;
                }
                Intent intent= new Intent(Resp_diafragmatica.this, Resp_diafragmatica_iniciar.class);
                startActivity(intent);
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
    @Override
    protected void onStart() {
        super.onStart();


        ImageView btn_regresar = findViewById(R.id.regresar_fisioterapia1);
        btn_regresar.setOnClickListener(v -> {
            //parar audios
            onDestroy();
            Intent intent = new Intent(this, Fisioterapia.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
        });
    }
}