package com.example.aplicacionmercadoa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegistrodeTiendas extends AppCompatActivity {
    private Spinner mercado , distritomercado , rubromercado;
    private EditText NombreVendedor , Apellidos , CorreoElectronico , TelefonoCelular , DNI , Contraseña , NombreTienda;
    private Button registrarVendedor;
    private String Nombre ="";
    private String ApellidosVendedor ="";
    private String Correo ="";
    private String TelefonoVendedor ="";
    private String ContraseñaVendedor ="";
    private String Dni="";
    private String NombreTiendaVendedor ="";
    private String Mercado = "";
    private String Distrito ="";
    private String Rubro = "";
    FirebaseAuth mAuth;
    DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private  String UserId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrode_tiendas);
        mAuth = FirebaseAuth.getInstance();
        mercado = (Spinner) findViewById(R.id.Mercado);
        distritomercado = (Spinner)findViewById(R.id.DistritoMercado);
        rubromercado = (Spinner) findViewById(R.id.RubroMercado);
        NombreVendedor = (EditText) findViewById(R.id.NombreVendedor);
        Apellidos = (EditText) findViewById(R.id.ApellidosVendedor);
        CorreoElectronico = (EditText) findViewById(R.id.CorreoElectronico);
        TelefonoCelular = (EditText) findViewById(R.id.TelefonoVendedor);
        DNI = (EditText) findViewById(R.id.DNIVendedor);
        Contraseña = (EditText) findViewById(R.id.ContraseñaVendedor);
        NombreTienda = (EditText) findViewById(R.id.NombreTienda);
        registrarVendedor = (Button) findViewById(R.id.RegistrarVendedor);
        //mAuth = FirebaseAuth.getInstance();
        mFirebaseInstance = FirebaseDatabase.getInstance();
        //mFirebaseDatabase = mFirebaseInstance.getReference("Vendedores");
        mFirebaseDatabase = mFirebaseInstance.getReference();
        UserId = mFirebaseDatabase.push().getKey();
        // Spiner para seleccion de Mercado
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Mercados,android.R.layout.simple_selectable_list_item);
        mercado.setAdapter(adapter);
        // Spiner para seleccion de Distrito
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.Distritos,android.R.layout.simple_selectable_list_item);
        distritomercado.setAdapter(adapter1);
        // Spiner para seleccion de Rubro
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.Rubros,android.R.layout.simple_selectable_list_item);
        rubromercado.setAdapter(adapter2);
        // Boton para Registrar Vendedores
        registrarVendedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Nombre = NombreVendedor.getText().toString();
                ApellidosVendedor = Apellidos.getText().toString();
                Correo = CorreoElectronico.getText().toString();
                TelefonoVendedor = TelefonoCelular.getText().toString();
                ContraseñaVendedor = Contraseña.getText().toString();
                Dni = DNI.getText().toString();
                NombreTiendaVendedor = NombreTienda.getText().toString();
                Mercado = mercado.getSelectedItem().toString();
                Distrito = distritomercado.getSelectedItem().toString();
                Rubro = rubromercado.getSelectedItem().toString();
                if(!Nombre.isEmpty() && !ApellidosVendedor.isEmpty() && !Correo.isEmpty() && !TelefonoVendedor.isEmpty() && !ContraseñaVendedor.isEmpty() && !Dni.isEmpty() &&
                        !NombreTiendaVendedor.isEmpty()){
                    if(Contraseña.length() >= 6 && Dni.length() == 8 && TelefonoVendedor.length() == 9){
                        //RegistrarVendor();
                        insertData(view);
                        Toast.makeText(RegistrodeTiendas.this,"Registro de Tienda Exitoso",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(RegistrodeTiendas.this , LoginActivity.class);
                        startActivity(i);
                        finish();
                    }else{
                        Toast.makeText(RegistrodeTiendas.this,"La contraseña debe Tener mas de 6 caracteres o verifique su DNI o verifique su Telefono Celular",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(RegistrodeTiendas.this,"Debe Llenar todos los Campos",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /*public void RegistrarVendor(){
        mAuth.createUserWithEmailAndPassword(Correo, ContraseñaVendedor).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Map<String,Object> map = new HashMap<>();
                    map.put("Nombres" , Nombre);
                    map.put("Apellidos" , ApellidosVendedor);
                    map.put("Correo Electronico" , Correo);
                    map.put("Telefono Celular" , TelefonoVendedor);
                    map.put("Contraseña" , ContraseñaVendedor);
                    map.put("DNI" , Dni);
                    map.put("Nombre de Tienda" , NombreTiendaVendedor);
                    map.put("Mercado" , Mercado);
                    map.put("Ubicacion de Tienda" , Distrito);
                    map.put("Rubro" , Rubro);
                    String id = mAuth.getCurrentUser().getUid();
                    mFirebaseDatabase.child("Vendedores").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if(task2.isSuccessful()){
                                //startActivity(new Intent(RegistrodeClientes.this,Principal.class));
                                //Intent i = new Intent(RegistrodeClientes.this , Principal.class);
                                //startActivity(i);
                                //finish();
                            }else{
                                Toast.makeText(RegistrodeTiendas.this,"No se pudieron crear los datos correctamente",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(RegistrodeTiendas.this,"No se pudo registrar usuario",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }*/

    public void addStore(String Nombre, String ApellidosVendedor, String Correo, String TelefonoVendedor, String ContraseñaVendedor, String Dni, String NombreTiendaVendedor, String Mercado, String Distrito, String Rubro){
        Vendedor vendedor = new Vendedor(Nombre, ApellidosVendedor,Correo, TelefonoVendedor, ContraseñaVendedor, Dni, NombreTiendaVendedor, Mercado, Distrito, Rubro);
        mAuth.createUserWithEmailAndPassword(Correo,ContraseñaVendedor).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
              if(task.isSuccessful()){

              }
            }
        });
        mFirebaseDatabase.child("Vendedores").child(UserId).setValue(vendedor);
    }
    private void insertData (View view){
        addStore(Nombre.trim(),ApellidosVendedor.trim(),Correo.trim(),TelefonoVendedor.trim(),ContraseñaVendedor.trim(),Dni.trim(),NombreTiendaVendedor.trim(),Mercado.trim(),Distrito.trim(), Rubro.trim());
    }
    // Metodo que registra Vendedor
   /* public void registerStore() {
        mAuth.createUserWithEmailAndPassword(Correo, ContraseñaVendedor).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("Nombres", Nombre);
                    map.put("Apellidos", ApellidosVendedor);
                    map.put("Correo Electronico", Correo);
                    map.put("Telefono Celular", TelefonoVendedor);
                    map.put("Contraseña", Contraseña);
                    map.put("DNI", Dni);
                    map.put("Nombre de Tienda", NombreTiendaVendedor);
                    map.put("Mercado", Mercado);
                    map.put("Ubicacion de Tienda", Distrito);
                    map.put("Rubro de Tienda", Rubro);
                    String id = mAuth.getCurrentUser().getUid();
                    mDataReference.child("Vendedores").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if (task2.isSuccessful()) {

                            } else {
                                Toast.makeText(RegistrodeTiendas.this, "No se pudieron crear los datos correctamente", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(RegistrodeTiendas.this, "No se pudo registrar vendedor", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }*/
}