package com.example.aplicacionmercadoa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MostrarDatosActivity extends AppCompatActivity {
    private EditText NombredePerfil , ApellidodePerfil , CorreodePerfil , TelefonoPerfil , DistritodePerfil;
    private FirebaseAuth mAuth;
    private DatabaseReference mReference;
    private Button actualizar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_datos);
        NombredePerfil = (EditText)findViewById(R.id.NombrePerfil);
        ApellidodePerfil = (EditText)findViewById(R.id.ApellidosPerfil);
        CorreodePerfil = (EditText)findViewById(R.id.CorreoPerfil);
        TelefonoPerfil = (EditText)findViewById(R.id.TelefonoPerfil);
        DistritodePerfil = (EditText)findViewById(R.id.DistritoPerfil);
        actualizar = (Button)findViewById(R.id.ActualizarPerfil1);
        mAuth = FirebaseAuth.getInstance();
        mReference = FirebaseDatabase.getInstance().getReference();
        MostrarUserInfo();
    }
    private void MostrarUserInfo() {
        String id = mAuth.getCurrentUser().getUid();
        mReference.child("Clientes").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String Nombre = dataSnapshot.child("Nombres").getValue().toString();
                    String Apellidos = dataSnapshot.child("Apellidos").getValue().toString();
                    String CorreoElectronico = dataSnapshot.child("Correo Electronico").getValue().toString();
                    String TelefonoCelular = dataSnapshot.child("Telefono Celular").getValue().toString();
                    String DistritosPerfil = dataSnapshot.child("Distrito").getValue().toString();
                    NombredePerfil.setText(Nombre);
                    ApellidodePerfil.setText(Apellidos);
                    CorreodePerfil.setText(CorreoElectronico);
                    TelefonoPerfil.setText(TelefonoCelular);
                    DistritodePerfil.setText(DistritosPerfil);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void ActualizarRegistros(){
        
    }

}