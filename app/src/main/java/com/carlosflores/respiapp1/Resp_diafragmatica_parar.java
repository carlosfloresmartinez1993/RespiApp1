package com.carlosflores.respiapp1;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import pl.droidsonroids.gif.GifImageView;

public class Resp_diafragmatica_parar extends AppCompatActivity {

    private Chronometer cronometro;
    private boolean corriendo = false; //para el cronometro

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resp_diafragmatica_parar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btn_resp_diafragmatica_parar = findViewById(R.id.btn_respiracion_diafragmatica_parar);
        btn_resp_diafragmatica_parar.setOnClickListener(v -> {
            cronometro.stop();
            Intent intent = new Intent(this, Resp_diafragmatica.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
        });

        View imageView2 = findViewById(R.id.imageView2);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Resp_diafragmatica_parar.this, Resp_diafragmatica.class);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        cronometro = findViewById(R.id.cronometro);
        cronometro.setBase(SystemClock.elapsedRealtime());
        cronometro.start();
        corriendo = true;

    }
}