package com.carlosflores.respiapp1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Fisioterapia extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fisioterapia);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
      //codigo mio
        ImageView btn_regresar = findViewById(R.id.regresar_fisioterapia);
        ImageView btn_resp_diafragmatica = findViewById(R.id.resp_diafragmatica);
        ImageView btn_resp_mov_brazos = findViewById(R.id.resp_mov_brazos);
        ImageView btn_resp_pajilla = findViewById(R.id.resp_pajilla);
        ImageView btn_resp_cuadratica = findViewById(R.id.resp_cuadratica);
        ImageView btn_palmopercusion = findViewById(R.id.palmopercusion);

        //redirigir si aprieto regresar
        btn_regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ir a otra actividad
                Intent intent = new Intent(Fisioterapia.this, MenuPrincipal.class);
                startActivity(intent);
            }
        });

        //redirigir si aprieto diafragmatica
        btn_resp_diafragmatica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ir a otra actividad
                Intent intent = new Intent(Fisioterapia.this, Resp_diafragmatica.class);
                startActivity(intent);
            }
        });
        //redirigir para resp mov brazos
        btn_resp_mov_brazos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ir a otra actividad
                Intent intent = new Intent(Fisioterapia.this, Resp_mov_brazos.class);
                startActivity(intent);
            }
        });
        //redirigir para resp con pàjilla
        btn_resp_pajilla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ir a otra actividad
                Intent intent = new Intent(Fisioterapia.this, Resp_popote.class);
                startActivity(intent);
            }
        });

        //redirigir para resp cuadrática
        btn_resp_cuadratica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ir a otra actividad
                Intent intent = new Intent(Fisioterapia.this, Resp_cuadrada.class);
                startActivity(intent);
            }
        });

        //redirigir para palmopercusion
        btn_palmopercusion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ir a otra actividad
                Intent intent = new Intent(Fisioterapia.this, Palmopercusion.class);
                startActivity(intent);
            }
        });

    }
}