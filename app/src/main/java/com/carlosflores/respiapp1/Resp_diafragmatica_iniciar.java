package com.carlosflores.respiapp1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import pl.droidsonroids.gif.GifImageView;

public class Resp_diafragmatica_iniciar extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resp_diafragmatica_iniciar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button btn_resp_diafragmatica_iniciar = findViewById(R.id.btn_resp_diafragmatica_iniciar);
        btn_resp_diafragmatica_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ir a otra actividad
                Intent intent = new Intent(Resp_diafragmatica_iniciar.this, Resp_diafragmatica_parar.class);
                startActivity(intent);
            }
        });
        View imageView3 = findViewById(R.id.imageView3);
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Resp_diafragmatica_iniciar.this, Resp_diafragmatica.class);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
/*

        btn_resp_diafragmatica_iniciar.setOnClickListener(v -> {
            Intent intent = new Intent(this, Resp_diafragmatica_parar.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
        });
        */
    }

}