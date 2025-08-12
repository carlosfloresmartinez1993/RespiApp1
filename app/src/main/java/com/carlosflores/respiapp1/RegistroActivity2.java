package com.carlosflores.respiapp1;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.carlosflores.respiapp1.BD.DBManager;
import com.carlosflores.respiapp1.Notificaciones.NotificacionUtils;



public class RegistroActivity2 extends AppCompatActivity {
 EditText editTextEdad, editTextNombre ;
 Button buttonContinuar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro2);
        editTextNombre = findViewById(R.id.editTextNombre);
        editTextEdad = findViewById(R.id.editTextEdad);
        buttonContinuar = findViewById(R.id.buttonContinuar);

        solicitarPermisoNotificaciones();
        verificarYSolicitarPermisoBateria();

        //Verificar que no se ha guardado el registro por vez primera sino manda a otra pantalla
        SharedPreferences prefs = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
        boolean yaRegistrado = prefs.getBoolean("registro_completado", false);

        if (yaRegistrado) {
            // Ya se registró antes → ir directo a la pantalla principal
            startActivity(new Intent(this, Informacion.class));
            finish(); // Para que no vuelva atrás
        }



        buttonContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if (TextUtils.isEmpty(editTextNombre.getText().toString().trim()) || TextUtils.isEmpty(editTextEdad.getText().toString().trim()) ){
                  Toast.makeText( RegistroActivity2.this,"CAMPOS OBLIGARORIOS ", Toast.LENGTH_LONG).show();
                  limpiar();

              }else {

                DBManager dbManager= new DBManager( RegistroActivity2.this);
                long id = dbManager.insertar(editTextNombre.getText().toString(),editTextEdad.getText().toString());
                if (id>0){
                    Toast.makeText( RegistroActivity2.this,"REGISTRO GUARDADO ", Toast.LENGTH_LONG).show();
                    limpiar();

                    //no mostrar la proxima vez q ya se guarda
                    SharedPreferences prefs = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putBoolean("registro_completado", true);
                    editor.apply();  // o commit()


                    // Redirigir después de un pequeño retraso para que el Toast se vea
                    new Handler().postDelayed(() -> {
                        Intent intent = new Intent(RegistroActivity2.this, Informacion.class); // reemplaza MenuPrincipal con tu destino
                        startActivity(intent);
                        finish(); // Opcional: para que no puedan volver con el botón "atrás"
                    }, 0);

                }else {
                    Toast.makeText( RegistroActivity2.this,"ERROR AL GUARDAR REGISTRO  ", Toast.LENGTH_LONG).show();
                }
            }
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        NotificacionUtils.programarProximoSabado(this);
     }
   public  void limpiar(){
       editTextEdad.setText("");
       editTextNombre.setText("");
   }

    private void verificarYSolicitarPermisoBateria() {
        SharedPreferences prefs = getSharedPreferences("prefs_app", MODE_PRIVATE);
        boolean yaPidioPermiso = prefs.getBoolean("permiso_bateria_pedido", false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
            if (!pm.isIgnoringBatteryOptimizations(getPackageName()) && !yaPidioPermiso) {
                Intent intent = new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivity(intent);

                // Guardar que ya se pidió el permiso
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("permiso_bateria_pedido", true);
                editor.apply();
            }
        }
    }
    private void solicitarPermisoNotificaciones() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 1001);
            }
        }
    }

}