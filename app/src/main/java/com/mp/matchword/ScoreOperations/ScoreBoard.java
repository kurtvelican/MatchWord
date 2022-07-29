package com.mp.matchword.ScoreOperations;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.mp.matchword.MainPage;
import com.mp.matchword.R;

import java.util.ArrayList;

public class ScoreBoard extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<ScoreInfo> scoreInfoArrayList;
    ScoreBoardAdapter scoreBoardAdapter;
    FirebaseFirestore firebaseFirestore;
    ProgressDialog progressDialog;
    Button toMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        toMainMenu = findViewById(R.id.toMainMenu);

        toMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ScoreBoard.this, MainPage.class));
                finish();
            }
        });

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Veriler çekiliyor...");
        progressDialog.show();

        recyclerView = findViewById(R.id.score_board);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        firebaseFirestore = FirebaseFirestore.getInstance();

        scoreInfoArrayList = new ArrayList<ScoreInfo>();

        scoreBoardAdapter = new ScoreBoardAdapter(ScoreBoard.this,scoreInfoArrayList);

        recyclerView.setAdapter(scoreBoardAdapter);

        EventChangeListener();
    }

    private void EventChangeListener(){

        firebaseFirestore.collection("Scores").orderBy("Score", Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if(error != null){

                    if(progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Log.e("Database error",error.getMessage());
                    return;
                }

                for(DocumentChange dc : value.getDocumentChanges()){

                    if(dc.getType() == DocumentChange.Type.ADDED){

                        scoreInfoArrayList.add(dc.getDocument().toObject(ScoreInfo.class));
                    }

                    scoreBoardAdapter.notifyDataSetChanged();
                    if(progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                }
            }
        });
    }

}