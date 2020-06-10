package com.example.aplicacionmercadoa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PrincipalVendedor extends AppCompatActivity {
    private TextView Bienvenida , Tienda;
    private FirebaseAuth mAuth;
    private DatabaseReference mReference;
    private Button cerrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_vendedor);
        mAuth = FirebaseAuth.getInstance();
        mReference = FirebaseDatabase.getInstance().getReference("Vendedores");
        Bienvenida = (TextView) findViewById(R.id.BienvenidaVendedor);
        Tienda = (TextView) findViewById(R.id.Tienda);
        cerrar = (Button) findViewById(R.id.CerrarSesion);
        cerrar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mAuth.signOut();
            Intent i = new Intent(PrincipalVendedor.this , LoginActivity.class);
            startActivity(i);
            finish();
        }
        });
        getStoreInfo();
    }
    private void getStoreInfo() {
        String id = mAuth.getCurrentUser().getUid();
        mReference.child("Vendedores").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String Nombre = dataSnapshot.child("nombre").getValue().toString();
                    String Tiendas = dataSnapshot.child("nombretienda").getValue().toString();
                    //String Apellidos = dataSnapshot.child("Apellidos").getValue().toString();
                    Bienvenida.setText("Bienvenido(a) " + Nombre);
                    Tienda.setText(Tiendas);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}