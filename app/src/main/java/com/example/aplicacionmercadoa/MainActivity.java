package com.example.aplicacionmercadoa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView imagen;
    private static int splashTimeOut=2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imagen = (ImageView)findViewById(R.id.market);
        Animation anim = AnimationUtils.loadAnimation(this,R.anim.mysplashanimation);
        imagen.startAnimation(anim);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this , LoginActivity.class);
                startActivity(i);
            }
        },splashTimeOut);
    }
}