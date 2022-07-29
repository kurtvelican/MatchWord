package com.mp.matchword.GameOperations;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.mp.matchword.MainPage;
import com.mp.matchword.R;


public class SelectLevel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_level);
    }

    public void back_clicked (View view) {
        Intent intent = new Intent(SelectLevel.this, MainPage.class);
        startActivity(intent);
        finish();
    }

    public void EasytoGameScreen (View view){
        Intent intent = new Intent(SelectLevel.this, GameScreen.class);
        intent.putExtra("zorluk",1);
        startActivity(intent);
        finish();
    }

    public void NormaltoGameScreen (View view){
        Intent intent = new Intent(SelectLevel.this, GameScreen.class);
        intent.putExtra("zorluk",2);
        startActivity(intent);
        finish();
    }

    public void HardtoGameScreen (View view){
        Intent intent = new Intent(SelectLevel.this, GameScreen.class);
        intent.putExtra("zorluk",3);
        startActivity(intent);
        finish();
    }
}