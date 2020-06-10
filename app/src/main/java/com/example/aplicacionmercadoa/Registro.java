package com.example.aplicacionmercadoa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Registro extends AppCompatActivity {
    private Button Usuarios , Tiendas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        Usuarios = (Button) findViewById(R.id.BotonUsuarios);
        Tiendas = (Button) findViewById(R.id.BotonTiendas);
        Usuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Registro.this , RegistrodeClientes.class);
                startActivity(i);
                //finish();
            }
        });
        Tiendas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Registro.this , RegistrodeTiendas.class);
                startActivity(i);
            }
        });
    }
}