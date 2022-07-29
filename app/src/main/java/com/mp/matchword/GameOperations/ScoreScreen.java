package com.mp.matchword.GameOperations;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mp.matchword.MainPage;
import com.mp.matchword.R;

import java.util.HashMap;

public class ScoreScreen extends AppCompatActivity {

    TextView dogruSayisiText,dogruText,yanlisSayisiText,yanlisText;
    CounterSingleton counterSingleton;
    Button retryButton,mainMenuButton;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_screen);

        counterSingleton = CounterSingleton.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        retryButton = findViewById(R.id.retry_button);
        mainMenuButton = findViewById(R.id.mainmenu_button);
        dogruText = findViewById(R.id.dogruText);
        dogruSayisiText = findViewById(R.id.dogruSayisiText);
        yanlisText = findViewById(R.id.yanlisText);
        yanlisSayisiText = findViewById(R.id.yanlisSayisiText);

        DocumentReference documentReference = firebaseFirestore.collection("Scores").document(firebaseUser.getEmail());
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot != null) {

                    HashMap scores;
                    scores = (HashMap) documentSnapshot.getData();
                    String point = String.valueOf(scores.get("Score"));
                    int pointSayi = Integer.valueOf(point) + (counterSingleton.dogruSayisi * 20)
                            - (counterSingleton.yanlisSayisi * 5);

                    scores.put("Score",pointSayi);

                    documentReference.update(scores);

                }
            }
        });

        dogruSayisiText.setText(String.valueOf(counterSingleton.dogruSayisi));

        yanlisSayisiText.setText(String.valueOf(counterSingleton.yanlisSayisi));

        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counterSingleton.sayac = 0;
                counterSingleton.dogruSayisi = 0;
                counterSingleton.yanlisSayisi = 0;
                startActivity(new Intent(ScoreScreen.this,SelectLevel.class));
                finish();
            }
        });

        mainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counterSingleton.sayac = 0;
                counterSingleton.dogruSayisi = 0;
                counterSingleton.yanlisSayisi = 0;
                startActivity(new Intent(ScoreScreen.this, MainPage.class));
                finish();
            }
        });


    }
}