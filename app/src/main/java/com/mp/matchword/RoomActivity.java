
package com.mp.matchword;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mp.matchword.databinding.ActivityRoomBinding;

import java.util.HashMap;

public class RoomActivity extends AppCompatActivity {


    private ActivityRoomBinding binding;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    private String userName;
    private User user;

    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRoomBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();


        EditText editCreateRoom = findViewById(R.id.createRoomTxt);
        EditText editEnterRoom = findViewById(R.id.enterRoom);
        Button createRoom = findViewById(R.id.btnCreateRoom);

        editCreateRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {            }
        });

        editEnterRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {            }
        });

        createRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editCreateRoom;
                editCreateRoom = (EditText) findViewById(R.id.createRoomTxt);

                String roomNumber = editCreateRoom.getText().toString().trim();

                if(roomNumber.isEmpty()){
                    editCreateRoom.setError("Oda numarası boş bırakılamaz");
                    editCreateRoom.requestFocus();
                    return;
                }

                if((roomNumber.length() < 2 ) || (roomNumber.length() >5 )){
                    editCreateRoom.setError("Oda numarası en az 2 en fazla 5 basamaklı olmalıdır!");
                    editCreateRoom.requestFocus();
                    return;
                }

                String roomNumberBind = binding.createRoomTxt.getText().toString().trim();
                HashMap<String, Object> roomKey = new HashMap<>();
                roomKey.put("Room Number",roomNumberBind);


                CollectionReference collectionReference =  firebaseFirestore.collection("Rooms").document(roomNumberBind).collection("Kurucu");



                DocumentReference documentReference1 = firebaseFirestore.collection("Easy Words").document("nrT4hvQGokbqj7xNCixm")
                        .collection("turkish").document("4jONcyedrZ3Jx0Fy3Bi2");
                documentReference1.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot != null) {
                             HashMap<String,Object>wordsTurkish = (HashMap) documentSnapshot.getData();
                            firebaseFirestore.collection("Rooms").document(roomNumberBind).collection("Turkish").add(wordsTurkish);
                        }
                    }
                });
                DocumentReference documentReference = firebaseFirestore.collection("Easy Words").document("nrT4hvQGokbqj7xNCixm")
                        .collection("english").document("qeZwC9LfZxpQxjMQPHNc");
                documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot != null) {
                            HashMap<String,Object>wordsEnglish = (HashMap) documentSnapshot.getData();
                            firebaseFirestore.collection("Rooms").document(roomNumberBind).collection("English").add(wordsEnglish);
                        }
                    }
                });




            }
        });
    }
}
