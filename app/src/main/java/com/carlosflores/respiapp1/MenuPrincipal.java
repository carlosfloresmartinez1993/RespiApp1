package com.carlosflores.respiapp1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MenuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_principal);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //codigo mio
        ImageView imagenBotonRespiracion = findViewById(R.id.respiracion);
        ImageView imagenBotonFisioterapia = findViewById(R.id.fisioterapia);
        ImageView imagenBotonMonitoreo = findViewById(R.id.monitoreo);

        imagenBotonRespiracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ir a otra actividad
                Intent intent = new Intent(MenuPrincipal.this, Conoce_tu_respiracion.class);
                startActivity(intent);
            }
        });

        imagenBotonMonitoreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ir a otra actividad
                Intent intent = new Intent(MenuPrincipal.this, Monitoreo.class);
                startActivity(intent);
            }
        });

        imagenBotonFisioterapia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ir a otra actividad
                Intent intent = new Intent(MenuPrincipal.this, Fisioterapia.class);
                startActivity(intent);
            }
        });





    }
}