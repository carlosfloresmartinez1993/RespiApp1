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

public class Resp_popote_parar extends AppCompatActivity {
    private Chronometer cronometro;
    private boolean corriendo = false; //para el cronometro
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resp_popote_parar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btn_resp_diafragmatica_parar = findViewById(R.id.btn_resp_popote_parar);
        btn_resp_diafragmatica_parar.setOnClickListener(v -> {
            //acceder a los datos del cronometro
            long tiempoTranscurrido = SystemClock.elapsedRealtime() - cronometro.getBase();
            int segundos = (int) (tiempoTranscurrido / 1000) % 60;
            int minutos = (int) ((tiempoTranscurrido / (1000 * 60)) % 60);
            String tiempoFormateado = String.format("%02d:%02d", minutos, segundos);

            //guardar tiempo del cronometro en la base de datos
            DBManager BD = new DBManager(Resp_popote_parar.this);
            long id = BD.insertar_Resp_popote(tiempoFormateado);
            if (id>0){
                Toast.makeText( Resp_popote_parar.this,"REGISTRO GUARDADO ", Toast.LENGTH_LONG).show();
                //parar cronometro
                cronometro.stop();
                Intent intent = new Intent(this, Resp_popote.class);
                startActivity(intent);
                finish();
                overridePendingTransition(0, 0);
            }else
                Toast.makeText( Resp_popote_parar.this,"ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();


        });

        View imageView2 = findViewById(R.id.regresar_fisioterapia_11);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Resp_popote_parar.this, Resp_mov_brazos.class);
                startActivity(intent);
                finish();
            }
        });

    }
    protected void onStart() {
        super.onStart();
        cronometro = findViewById(R.id.cronometro1);
        cronometro.setBase(SystemClock.elapsedRealtime());
        cronometro.start();
        corriendo = true;

    }
}