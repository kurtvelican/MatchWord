package com.mp.matchword;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mp.matchword.SignOperations.LoginActivity;

public class SplashScreen extends AppCompatActivity {

    ImageView logoTemplate;
    ImageView logoName;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();

        logoTemplate = findViewById(R.id.logoTemplate);
        Animation slide_down = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.uptodown);
        logoTemplate.startAnimation(slide_down);

        logoName = findViewById(R.id.logoName);
        Animation slide_up = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.downtoup);
        logoName.startAnimation(slide_up);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(currentUser != null ){
                    Intent i = new Intent(SplashScreen.this,MainPage.class);
                    startActivity(i);
                    finish();
                }
                else{
                    Intent i = new Intent(SplashScreen.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }

            }
        },5000);

    }
}