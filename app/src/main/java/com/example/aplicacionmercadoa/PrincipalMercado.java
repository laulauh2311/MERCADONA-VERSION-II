package com.example.aplicacionmercadoa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PrincipalMercado extends AppCompatActivity {
    private TextView Bienvenida , Distrito;
    private FirebaseAuth mAuth;
    private DatabaseReference mReference;
    private Button CerrarSesion;
    private BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_mercado);
        //MostrarUserInfo();
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new HomePage()).commit();
        }
        mAuth = FirebaseAuth.getInstance();
        mReference = FirebaseDatabase.getInstance().getReference();
        Bienvenida = (TextView) findViewById(R.id.Bienvenida);
        Distrito = (TextView) findViewById(R.id.Distrito2);
        CerrarSesion = (Button) findViewById(R.id.CerrarSesionCliente);
        navigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        navigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        getUserInfo();
    }
    private void getUserInfo() {
        String id = mAuth.getCurrentUser().getUid();
        mReference.child("Clientes").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String Nombre = dataSnapshot.child("Nombres").getValue().toString();
                    String Distritos = dataSnapshot.child("Distrito").getValue().toString();
                    //String Apellidos = dataSnapshot.child("Apellidos").getValue().toString();
                    Bienvenida.setText("Bienvenido(a) " + Nombre);
                    Distrito.setText(Distritos);

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment fragment = null;
                    switch (item.getItemId()){
                        case R.id.Inicio:
                            fragment = new HomePage();
                            break;
                        case R.id.Buscar:
                            fragment = new SearchPage();
                            break;
                        case R.id.Productos:
                            fragment = new ProductPage();
                            break;
                        case R.id.Perfil:
                            fragment = new PerfilPage();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragment).commit();
                    return true;
                }
            };
    public void SanCamilo(View view ){
        startActivity(new Intent(PrincipalMercado.this,SanCamilo.class));
    }
    public void CerrarSesion(View view){
        mAuth.signOut();
        Intent i = new Intent(PrincipalMercado.this , LoginActivity.class);
        startActivity(i);
        finish();
    }
    public void MostrarDatos(View view){
        startActivity(new Intent(PrincipalMercado.this,MostrarDatosActivity.class));
    }
}