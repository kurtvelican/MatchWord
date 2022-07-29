package com.mp.matchword;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.mp.matchword.GameOperations.SelectLevel;
import com.mp.matchword.ScoreOperations.ScoreBoard;
import com.mp.matchword.SignOperations.LoginActivity;

public class MainPage extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void pn_clicked (View view) {
        Intent intent = new Intent(MainPage.this, SelectLevel.class);
        startActivity(intent);
        finish();
    }

    public void sb_clicked (View view) {
        Intent intent = new Intent(MainPage.this, ScoreBoard.class);
        startActivity(intent);
        finish();
    }

    public void so_clicked (View view) {
        firebaseAuth.signOut();
        startActivity(new Intent(MainPage.this, LoginActivity.class));
        finish();
    }

    public void emp_clicked (View view){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Word Match");
        alert.setMessage("Uygulamadan Çıkmak İstediğinize Emin Misiniz?");
        alert.setPositiveButton("Hayır", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.setNegativeButton("Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
            }
        });
        alert.show();
    }
}