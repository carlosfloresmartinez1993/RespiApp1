package com.carlosflores.respiapp1;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.carlosflores.respiapp1.BD.DBHelper;
import com.carlosflores.respiapp1.BD.DBManager;

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


        buttonContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  DBHelper dbHelper= new DBHelper( RegistroActivity2.this);




                DBManager dbManager= new DBManager( RegistroActivity2.this);
                long id = dbManager.insertar(editTextNombre.getText().toString(),
                        Integer.parseInt(editTextEdad.getText().toString()));
                if (id>0){
                    Toast.makeText( RegistroActivity2.this,"REGISTRO GUARDADO ", Toast.LENGTH_LONG).show();
                    limpiar();

                    // Redirigir después de un pequeño retraso para que el Toast se vea
                    new Handler().postDelayed(() -> {
                        Intent intent = new Intent(RegistroActivity2.this, MenuPrincipal.class); // reemplaza MenuPrincipal con tu destino
                        startActivity(intent);
                        finish(); // Opcional: para que no puedan volver con el botón "atrás"
                    }, 0);

                }else {
                    Toast.makeText( RegistroActivity2.this,"ERROR AL GUARDAR REGISTRO  ", Toast.LENGTH_LONG).show();
                }
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }
   public  void limpiar(){
       editTextEdad.setText("");
       editTextNombre.setText("");
   }
}