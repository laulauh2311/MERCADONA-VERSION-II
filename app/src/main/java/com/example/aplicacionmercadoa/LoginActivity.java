package com.example.aplicacionmercadoa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.chip.Chip;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private Button registro , Login;
    private EditText Usuario , Contraseña;
    private String NombreUsuario ="";
    private String ContraseñaUsuario="";
    private FirebaseAuth mAuth;
    private CheckBox SoyVendedor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        registro = (Button) findViewById(R.id.Registrate);
        Usuario = (EditText) findViewById(R.id.username);
        Contraseña = (EditText) findViewById(R.id.password);
        Login = (Button) findViewById(R.id.login);
        mAuth = FirebaseAuth.getInstance();
        SoyVendedor = (CheckBox) findViewById(R.id.SoyVendedor);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NombreUsuario = Usuario.getText().toString();
                ContraseñaUsuario = Contraseña.getText().toString();
                if(!NombreUsuario.isEmpty() && !ContraseñaUsuario.isEmpty()){
                    //loginUser();
                    if (SoyVendedor.isChecked()){
                        loginStore();
                    }else{
                        loginUser();
                    }
                }else{
                    Toast.makeText(LoginActivity.this,"Debe completar todos los campos",Toast.LENGTH_SHORT).show();
                }
            }
        });
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this , Registro.class);
                startActivity(i);
            }
        });
    }
    private void loginUser() {
        mAuth.signInWithEmailAndPassword(NombreUsuario, ContraseñaUsuario).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent i = new Intent(LoginActivity.this , PrincipalMercado.class);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this,"No se pudo inicar sesion compruebe los datos o registrese",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loginStore() {
        mAuth.signInWithEmailAndPassword(NombreUsuario, ContraseñaUsuario).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent i = new Intent(LoginActivity.this , PrincipalVendedor.class);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this,"No se pudo inicar sesion compruebe los datos o registrese",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser() != null){
            Intent i = new Intent(LoginActivity.this , PrincipalMercado.class);
            startActivity(i);
            finish();
        }
    }
}