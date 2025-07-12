package com.carlosflores.respiapp1;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.carlosflores.respiapp1.BD.DBManager;

public class Palmopercusion_parar extends AppCompatActivity {

    private Chronometer cronometro;
    private boolean corriendo = false; //para el cronometro
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_palmopercusion_parar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button btn_resp_cuadrada_parar = findViewById(R.id.btn_palmopercusion_parar);
        btn_resp_cuadrada_parar.setOnClickListener(v -> {
            //acceder a los datos del cronometro
            long tiempoTranscurrido = SystemClock.elapsedRealtime() - cronometro.getBase();
            int segundos = (int) (tiempoTranscurrido / 1000) % 60;
            int minutos = (int) ((tiempoTranscurrido / (1000 * 60)) % 60);
            String tiempoFormateado = String.format("%02d:%02d", minutos, segundos);

            //guardar tiempo del cronometro en la base de datos
            DBManager BD = new DBManager(Palmopercusion_parar.this);
            long id = BD.insertar_Palmopercusion(tiempoFormateado);
            if (id>0){
                Toast.makeText( Palmopercusion_parar.this,"REGISTRO GUARDADO ", Toast.LENGTH_LONG).show();
                //parar cronometro
                cronometro.stop();
                Intent intent = new Intent(this, Palmopercusion.class);
                startActivity(intent);
                finish();
                overridePendingTransition(0, 0);
            }else
                Toast.makeText( Palmopercusion_parar.this,"ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();


        });

        View imageView2 = findViewById(R.id.regresar_fisioterapia_112);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Palmopercusion_parar.this, Palmopercusion.class);
                startActivity(intent);
                finish();
            }
        });

    }

    protected void onStart() {
        super.onStart();
        cronometro = findViewById(R.id.cronometro3);
        cronometro.setBase(SystemClock.elapsedRealtime());
        cronometro.start();
        corriendo = true;

    }
}