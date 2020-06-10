package com.example.aplicacionmercadoa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class PerfilPage extends Fragment {
    private Button cerrarsesion;
    private EditText NombredePerfil;
    private FirebaseAuth mAuth;
    private DatabaseReference mReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.perfil,container,false);
        /*final EditText NombredePerfil = (EditText)view.findViewById(R.id.NombrePerfil);
        final EditText ApellidodePerfil = (EditText)view.findViewById(R.id.ApellidosPerfil);
        final EditText CorreodePerfil = (EditText)view.findViewById(R.id.CorreoPerfil);
        final EditText TelefonoPerfil = (EditText)view.findViewById(R.id.TelefonoPerfil);
        final EditText DistritodePerfil = (EditText)view.findViewById(R.id.DistritoPerfil);*/
        return inflater.inflate(R.layout.perfil,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //MostrarUserInfo();
    }
}
