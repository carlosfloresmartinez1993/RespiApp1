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

public class Resp_cuadrada_iniciar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resp_cuadrada_iniciar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button resp_cuadrada_iniciar = findViewById(R.id.btn_resp_cuadrada_iniciar);
        resp_cuadrada_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Resp_cuadrada_iniciar.this, Resp_cuadrada_parar.class);
                startActivity(intent);
                finish();
            }
        });

        View imageView31 = findViewById(R.id.regresar_fisioterapia_101);
        imageView31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Resp_cuadrada_iniciar.this, Resp_cuadrada.class);
                startActivity(intent);
                finish();
            }
        });
    }
}