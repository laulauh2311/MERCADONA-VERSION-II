package com.example.aplicacionmercadoa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegistrodeClientes extends AppCompatActivity {
    Spinner distritos;
    private EditText etNombre,etApellidos,etCorreo,etCorreo1,etTelefono,etContraseña,etContraseña1;
    private Button registrar;
    private String Nombre="";
    private String Apellidos="";
    private String Correo ="";
    private String Correo1 ="";
    private String Telefono ="";
    private String Contraseña ="";
    private String Contraseña1 ="";
    private String Distritos="";
    FirebaseAuth mAuth;
    DatabaseReference mDataReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrode_clientes);
        distritos = (Spinner)findViewById(R.id.sp01);
        etNombre = (EditText)findViewById(R.id.Nombre);
        etApellidos = (EditText)findViewById(R.id.Apellidos);
        etCorreo = (EditText)findViewById(R.id.Correo);
        etCorreo1 = (EditText)findViewById(R.id.Correo1);
        etTelefono = (EditText)findViewById(R.id.Telefono);
        etContraseña = (EditText)findViewById(R.id.Contraseña);
        etContraseña1 = (EditText)findViewById(R.id.Contraseña1);
        registrar = (Button)findViewById(R.id.Registrar);

        // Spinner que muestra los valores de un Array
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Distritos,android.R.layout.simple_selectable_list_item);
        distritos.setAdapter(adapter);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Nombre = etNombre.getText().toString();
                Apellidos = etApellidos.getText().toString();
                Correo = etCorreo.getText().toString();
                Correo1 = etCorreo1.getText().toString();
                Telefono = etTelefono.getText().toString();
                Contraseña = etContraseña.getText().toString();
                Contraseña1 = etContraseña1.getText().toString();
                Distritos = distritos.getSelectedItem().toString();
                if(!Nombre.isEmpty() && !Apellidos.isEmpty() && !Correo.isEmpty() && !Telefono.isEmpty() && !Contraseña.isEmpty() && !Contraseña1.isEmpty() && !Correo1.isEmpty()){
                   if(Contraseña.length() >= 6 && Contraseña1.length() >= 6){
                       registerUser();
                       Toast.makeText(RegistrodeClientes.this,"Registro Exitoso",Toast.LENGTH_SHORT).show();
                       Intent i = new Intent(RegistrodeClientes.this , LoginActivity.class);
                       startActivity(i);
                       finish();
                   }else{
                       Toast.makeText(RegistrodeClientes.this,"La contraseña debe Tener mas de 6 caracteres",Toast.LENGTH_SHORT).show();
                   }
                    /*if( Correo == Correo1 && Contraseña == Contraseña1 && Contraseña.length() >= 6 && Contraseña1.length() >= 6){
                        registerUser();
                    }else{
                        Toast.makeText(RegistrodeClientes.this,"El Correo o la Contraseña No Son Iguales",Toast.LENGTH_SHORT).show();
                        Toast.makeText(RegistrodeClientes.this,"La Contraseña Debe Tener Mas de 6 Caracteres",Toast.LENGTH_SHORT).show();
                    }*/
                }else{
                        Toast.makeText(RegistrodeClientes.this,"Debe Llenar todos los Campos",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void registerUser(){
        mAuth.createUserWithEmailAndPassword(Correo, Contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Map<String,Object> map = new HashMap<>();
                    map.put("Nombres", Nombre);
                    map.put("Apellidos", Apellidos);
                    map.put("Correo Electronico", Correo);
                    map.put("Telefono Celular", Telefono);
                    map.put("Contraseña", Contraseña);
                    map.put("Distrito", Distritos);
                    String id = mAuth.getCurrentUser().getUid();
                    mDataReference.child("Clientes").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if(task2.isSuccessful()){
                                //startActivity(new Intent(RegistrodeClientes.this,Principal.class));
                                //Intent i = new Intent(RegistrodeClientes.this , Principal.class);
                                //startActivity(i);
                                //finish();
                            }else{
                                Toast.makeText(RegistrodeClientes.this,"No se pudieron crear los datos correctamente",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(RegistrodeClientes.this,"No se pudo registrar usuario",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}