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

    }
    protected void onStart() {
        super.onStart();
        Button btn_resp_diafragmatica_parar = findViewById(R.id.btn_respiracion_diafragmatica_parar);
        //redirigir a pantalla de terminar
        btn_resp_diafragmatica_parar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //parar audios
                onDestroy();
                // Ir a otra actividad
                Intent intent = new Intent(Resp_diafragmatica_parar.this, Resp_diafragmatica.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }
}