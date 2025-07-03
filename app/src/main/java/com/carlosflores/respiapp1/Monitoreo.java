package com.carlosflores.respiapp1;

import android.Manifest;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Monitoreo extends AppCompatActivity {
    private static int MICROPHONE_PERMISSION_CODE = 200;
    private boolean estaGrabando = false;
    private String rutaArchivo;
    MediaRecorder grabador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_monitoreo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //codigo mio
        ImageView btn_regresar = findViewById(R.id.regresar_monitoreo);
        Button btnGrabar = findViewById(R.id.btnGrabar);
        Button btnEnviar = findViewById(R.id.btnEnviar);

        //redirigir si aprieto regresar
        btn_regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ir a otra actividad
                Intent intent = new Intent(Monitoreo.this, MenuPrincipal.class);
                startActivity(intent);
            }
        });

        btnGrabar.setOnClickListener(v -> {
            if (!estaGrabando) {
                iniciarGrabacion(v);
                btnGrabar.setText("Detener");
                estaGrabando = true;
            } else {
                detenerGrabacion(v);
                btnGrabar.setText("Grabar");
                estaGrabando = false;
            }
        });

        btnEnviar.setOnClickListener(v -> {
            enviarGrabacion();
        });

        if (existeMicrofono()) {
            permisoMicrofono();
        }
    }

    public void iniciarGrabacion(View v) {
        try {
            grabador = new MediaRecorder();
            grabador.setAudioSource(MediaRecorder.AudioSource.MIC);
            grabador.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            grabador.setOutputFile(rutaGrabacion());
            grabador.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            grabador.prepare();
            grabador.start();
            Toast.makeText(this,"Grabando",Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void detenerGrabacion(View v) {
        grabador.stop();
        grabador.release();
        grabador = null;
        Toast.makeText(this,"Grabacion finalizada",Toast.LENGTH_LONG).show();
    }

    public void enviarGrabacion() {
        if(rutaArchivo == null) {
            Toast.makeText(this,"No hay grabacion disponible",Toast.LENGTH_SHORT).show();
            return;
        }

        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            Toast.makeText(this,"Archivo no encontrado",Toast.LENGTH_SHORT).show();
            return;
        }

        Uri uri = androidx.core.content.FileProvider.getUriForFile(
                this,
                getPackageName() + ".fileprovider",
                archivo
        );

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("audio/*");
        intent.putExtra(Intent.EXTRA_STREAM,uri);
        intent.setPackage("com.whatsapp");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        try {
            startActivity(intent);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "Whatsapp no esta instalado",Toast.LENGTH_SHORT).show();
        }
    }

    private boolean existeMicrofono() {
        if(this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE)){
            return true;
        } else {
            return false;
        }
    }

    private void permisoMicrofono() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.RECORD_AUDIO}, MICROPHONE_PERMISSION_CODE);
        }
    }

    private String rutaGrabacion() {
        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File directorioGrabar = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmm_ss").format(new Date());
        String nombreArch = "Grabacion_" + timeStamp + ".mp3";
        File file = new File(directorioGrabar, nombreArch);
        rutaArchivo = file.getAbsolutePath();
        return file.getPath();
    }
}