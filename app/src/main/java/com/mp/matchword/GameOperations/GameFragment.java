package com.mp.matchword.GameOperations;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mp.matchword.R;

import java.util.HashMap;
import java.util.Random;

public class GameFragment extends Fragment {

    private FirebaseFirestore db;
    CounterSingleton counterSingleton;

    Button question,answer1,answer2,answer3,answer4,sorusayac;
    Drawable greenbutton , redbutton , button_bg;

    Random r = new Random();
    int low = 1;
    int high = 20;

    int low2 = 1;
    int high2 = 4;

    int finalResult2;

    public GameFragment() {
        // Required empty public constructor
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_game, container, false);

        sorusayac = getActivity().findViewById(R.id.soruSayac);
        db = FirebaseFirestore.getInstance();
        counterSingleton = CounterSingleton.getInstance();
        question = view.findViewById(R.id.question);
        answer1 = view.findViewById(R.id.retry_button);
        answer2 = view.findViewById(R.id.answer2);
        answer3 = view.findViewById(R.id.answer3);
        answer4 = view.findViewById(R.id.answer4);

        greenbutton = getActivity().getDrawable(R.drawable.greenbutton);
        redbutton = getActivity().getDrawable(R.drawable.redbutton);
        button_bg = getActivity().getDrawable(R.drawable.button_bg);

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int kontrol = getActivity().getIntent().getIntExtra("zorluk",0);

        if (kontrol == 1) {

            int [] questionArray = new int[5];
            questionArray[0] = r.nextInt(high2 - low2 + 1) + low2;
            questionArray[1] = questionArray[0] + r.nextInt(high2 - low2 + 1) + low2;
            questionArray[2] = questionArray[1] + r.nextInt(high2 - low2 + 1) + low2;
            questionArray[3] = questionArray[2] + r.nextInt(high2 - low2 + 1) + low2;
            questionArray[4] = questionArray[3] + r.nextInt(high2 - low2 + 1) + low2;

            for (int i = 0;i < 5; i++){System.out.println(questionArray[i]);}

            DocumentReference documentReference = db.collection("Easy Words").document("nrT4hvQGokbqj7xNCixm")
                    .collection("english").document("qeZwC9LfZxpQxjMQPHNc");
            documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot != null) {

                        /*int [] questionArray = new int[5];

                        int result = r.nextInt(high - low + 1) + low;
                        HashMap words;
                        words = (HashMap) documentSnapshot.getData();
                        int i;

                        for(i = 0; i < 5; i++ ) {

                            questionArray[i] =r.nextInt(high - low + 1) + low;

                                while(questionArray[i] == result)
                                {
                                    result =r.nextInt(high - low + 1) + low;
                                }
                        }*/





                        HashMap words;
                        words = (HashMap) documentSnapshot.getData();


                        int result = questionArray[counterSingleton.sayac];

                        finalResult2 = result;

                        question.setText(words.get(String.valueOf(finalResult2)).toString());
                        sorusayac.setText(String.valueOf(counterSingleton.sayac + 1));

                        answer1.setBackground(button_bg);
                        answer2.setBackground(button_bg);
                        answer3.setBackground(button_bg);
                        answer4.setBackground(button_bg);

                        //cevaplar

                        DocumentReference documentReference1 = db.collection("Easy Words").document("nrT4hvQGokbqj7xNCixm")
                                .collection("turkish").document("4jONcyedrZ3Jx0Fy3Bi2");
                        documentReference1.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot != null) {

                                    int result2 = r.nextInt(high - low + 1) + low;
                                    int result3 = r.nextInt(high - low + 1) + low;
                                    int result4 = r.nextInt(high - low + 1) + low;
                                    int result5 = r.nextInt(high - low + 1) + low;

                                    HashMap words;
                                    words = (HashMap) documentSnapshot.getData();

                                    int finalResult = finalResult2;


                                    while (result2 == finalResult || result2 == result3 || result2 == result4 || result2 == result5) {
                                        result2 = r.nextInt(high - low + 1) + low;
                                    }
                                    while (result3 == result2 || result3 == finalResult || result3 == result4 || result3 == result5) {
                                        result3 = r.nextInt(high - low + 1) + low;
                                    }
                                    while (result4 == result3 || result4 == result2 || result4 == finalResult || result4 == result5) {
                                        result4 = r.nextInt(high - low + 1) + low;
                                    }

                                    int low = 1;
                                    int high = 4;
                                    int cevap = r.nextInt(high - low + 1) + low;

                                    if (cevap == 1) {
                                        answer1.setText(words.get(String.valueOf(finalResult)).toString());
                                        answer2.setText(words.get(String.valueOf(result3)).toString());
                                        answer3.setText(words.get(String.valueOf(result4)).toString());
                                        answer4.setText(words.get(String.valueOf(result5)).toString());
                                    } else if (cevap == 2) {
                                        answer1.setText(words.get(String.valueOf(result2)).toString());
                                        answer2.setText(words.get(String.valueOf(finalResult)).toString());
                                        answer3.setText(words.get(String.valueOf(result4)).toString());
                                        answer4.setText(words.get(String.valueOf(result5)).toString());
                                    } else if (cevap == 3) {
                                        answer1.setText(words.get(String.valueOf(result2)).toString());
                                        answer2.setText(words.get(String.valueOf(result3)).toString());
                                        answer3.setText(words.get(String.valueOf(finalResult)).toString());
                                        answer4.setText(words.get(String.valueOf(result5)).toString());
                                    } else {
                                        answer1.setText(words.get(String.valueOf(result2)).toString());
                                        answer2.setText(words.get(String.valueOf(result3)).toString());
                                        answer3.setText(words.get(String.valueOf(result4)).toString());
                                        answer4.setText(words.get(String.valueOf(finalResult)).toString());
                                    }

                                    HashMap finalWords = words;

                                    answer1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {


                                            if (answer1.getText() == finalWords.get(String.valueOf(finalResult2))) {
                                                counterSingleton.dogruSayisi++;
                                                answer1.setBackground(greenbutton);
                                                if(counterSingleton.sayac == 4){
                                                    counterSingleton.sayac = 0;
                                                    startActivity(new Intent(getActivity(),ScoreScreen.class));
                                                    getActivity().finish();
                                                }
                                                counterSingleton.sayac++;
                                            } else {
                                                counterSingleton.yanlisSayisi++;
                                                answer1.setBackground(redbutton);
                                                if(counterSingleton.sayac == 4){
                                                    counterSingleton.sayac = 0;
                                                    startActivity(new Intent(getActivity(),ScoreScreen.class));
                                                    getActivity().finish();
                                                }
                                                counterSingleton.sayac++;
                                            }
                                            onViewCreated(view,savedInstanceState);
                                        }
                                    });

                                    answer2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            if (answer2.getText() == finalWords.get(String.valueOf(finalResult2))) {
                                                counterSingleton.dogruSayisi++;
                                                answer2.setBackground(greenbutton);
                                                if(counterSingleton.sayac == 4){
                                                    counterSingleton.sayac = 0;
                                                    startActivity(new Intent(getActivity(),ScoreScreen.class));
                                                    getActivity().finish();
                                                }
                                                counterSingleton.sayac++;
                                            } else {
                                                counterSingleton.yanlisSayisi++;
                                                answer2.setBackground(redbutton);
                                                if(counterSingleton.sayac == 4){
                                                    counterSingleton.sayac = 0;
                                                    startActivity(new Intent(getActivity(),ScoreScreen.class));
                                                    getActivity().finish();
                                                }
                                                counterSingleton.sayac++;
                                            }
                                            onViewCreated(view,savedInstanceState);
                                        }
                                    });

                                    answer3.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            if (answer3.getText() == finalWords.get(String.valueOf(finalResult2))) {
                                                counterSingleton.dogruSayisi++;
                                                answer3.setBackground(greenbutton);
                                                if(counterSingleton.sayac == 4){
                                                    counterSingleton.sayac = 0;
                                                    startActivity(new Intent(getActivity(),ScoreScreen.class));
                                                    getActivity().finish();
                                                }
                                                counterSingleton.sayac++;
                                            } else {
                                                counterSingleton.yanlisSayisi++;
                                                answer3.setBackground(redbutton);
                                                if(counterSingleton.sayac == 4){
                                                    counterSingleton.sayac = 0;
                                                    startActivity(new Intent(getActivity(),ScoreScreen.class));
                                                    getActivity().finish();
                                                }
                                                counterSingleton.sayac++;
                                            }
                                            onViewCreated(view,savedInstanceState);
                                        }
                                    });

                                    answer4.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            if (answer4.getText() == finalWords.get(String.valueOf(finalResult2))) {
                                                counterSingleton.dogruSayisi++;
                                                answer4.setBackground(greenbutton);
                                                if(counterSingleton.sayac == 4){
                                                    counterSingleton.sayac = 0;
                                                    startActivity(new Intent(getActivity(),ScoreScreen.class));
                                                    getActivity().finish();
                                                }
                                                counterSingleton.sayac++;
                                            } else {
                                                counterSingleton.yanlisSayisi++;
                                                answer4.setBackground(redbutton);
                                                if(counterSingleton.sayac == 4){
                                                    counterSingleton.sayac = 0;
                                                    startActivity(new Intent(getActivity(),ScoreScreen.class));
                                                    getActivity().finish();
                                                }
                                                counterSingleton.sayac++;
                                            }
                                            onViewCreated(view,savedInstanceState);
                                        }
                                    });
                                }
                            }


                        });
                    }

                }
            });

            //diğer işlemler

        } else if (kontrol == 2) {

            int [] questionArray = new int[5];
            questionArray[0] = r.nextInt(high2 - low2 + 1) + low2;
            questionArray[1] = questionArray[0] + r.nextInt(high2 - low2 + 1) + low2;
            questionArray[2] = questionArray[1] + r.nextInt(high2 - low2 + 1) + low2;
            questionArray[3] = questionArray[2] + r.nextInt(high2 - low2 + 1) + low2;
            questionArray[4] = questionArray[3] + r.nextInt(high2 - low2 + 1) + low2;

            DocumentReference documentReference = db.collection("Medium Words").document("0KCl5VFDtnkg7SFN5Ah8")
                    .collection("english").document("tSNqtpO0qBtI772ZelNM");

            documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot != null) {



                        HashMap words;
                        words = (HashMap) documentSnapshot.getData();

                        int result = questionArray[counterSingleton.sayac];
                        finalResult2 = result;

                        question.setText(words.get(String.valueOf(finalResult2)).toString());
                        sorusayac.setText(String.valueOf(counterSingleton.sayac + 1));

                        answer1.setBackground(button_bg);
                        answer2.setBackground(button_bg);
                        answer3.setBackground(button_bg);
                        answer4.setBackground(button_bg);

                        //cevaplar

                        DocumentReference documentReference1 = db.collection("Medium Words").document("0KCl5VFDtnkg7SFN5Ah8")
                                .collection("turkish").document("LYu5n6B1EsaR7MucoME2");
                        documentReference1.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot != null) {

                                    int result2 = r.nextInt(high - low + 1) + low;
                                    int result3 = r.nextInt(high - low + 1) + low;
                                    int result4 = r.nextInt(high - low + 1) + low;
                                    int result5 = r.nextInt(high - low + 1) + low;
                                    HashMap words;
                                    words = (HashMap) documentSnapshot.getData();

                                    int finalResult = finalResult2;


                                    while (result2 == finalResult || result2 == result3 || result2 == result4 || result2 == result5) {
                                        result2 = r.nextInt(high - low + 1) + low;
                                    }
                                    while (result3 == result2 || result3 == finalResult || result3 == result4 || result3 == result5) {
                                        result3 = r.nextInt(high - low + 1) + low;
                                    }
                                    while (result4 == result3 || result4 == result2 || result4 == finalResult || result4 == result5) {
                                        result4 = r.nextInt(high - low + 1) + low;
                                    }

                                    int low = 1;
                                    int high = 4;
                                    int cevap = r.nextInt(high - low + 1) + low;

                                    if (cevap == 1) {
                                        answer1.setText(words.get(String.valueOf(finalResult)).toString());
                                        answer2.setText(words.get(String.valueOf(result3)).toString());
                                        answer3.setText(words.get(String.valueOf(result4)).toString());
                                        answer4.setText(words.get(String.valueOf(result5)).toString());
                                    } else if (cevap == 2) {
                                        answer1.setText(words.get(String.valueOf(result2)).toString());
                                        answer2.setText(words.get(String.valueOf(finalResult)).toString());
                                        answer3.setText(words.get(String.valueOf(result4)).toString());
                                        answer4.setText(words.get(String.valueOf(result5)).toString());
                                    } else if (cevap == 3) {
                                        answer1.setText(words.get(String.valueOf(result2)).toString());
                                        answer2.setText(words.get(String.valueOf(result3)).toString());
                                        answer3.setText(words.get(String.valueOf(finalResult)).toString());
                                        answer4.setText(words.get(String.valueOf(result5)).toString());
                                    } else {
                                        answer1.setText(words.get(String.valueOf(result2)).toString());
                                        answer2.setText(words.get(String.valueOf(result3)).toString());
                                        answer3.setText(words.get(String.valueOf(result4)).toString());
                                        answer4.setText(words.get(String.valueOf(finalResult)).toString());
                                    }

                                    HashMap finalWords = words;

                                    answer1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {


                                            if (answer1.getText() == finalWords.get(String.valueOf(finalResult2))) {
                                                counterSingleton.dogruSayisi++;
                                                answer1.setBackground(greenbutton);
                                                if(counterSingleton.sayac == 4){
                                                    counterSingleton.sayac = 0;
                                                    startActivity(new Intent(getActivity(),ScoreScreen.class));
                                                    getActivity().finish();
                                                }
                                                counterSingleton.sayac++;
                                            } else {
                                                counterSingleton.yanlisSayisi++;
                                                answer1.setBackground(redbutton);
                                                if(counterSingleton.sayac == 4){
                                                    counterSingleton.sayac = 0;
                                                    startActivity(new Intent(getActivity(),ScoreScreen.class));
                                                    getActivity().finish();
                                                }
                                                counterSingleton.sayac++;
                                            }
                                            onViewCreated(view,savedInstanceState);
                                        }
                                    });

                                    answer2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            if (answer2.getText() == finalWords.get(String.valueOf(finalResult2))) {
                                                counterSingleton.dogruSayisi++;
                                                answer2.setBackground(greenbutton);
                                                if(counterSingleton.sayac == 4){
                                                    counterSingleton.sayac = 0;
                                                    startActivity(new Intent(getActivity(),ScoreScreen.class));
                                                    getActivity().finish();
                                                }
                                                counterSingleton.sayac++;
                                            } else {
                                                counterSingleton.yanlisSayisi++;
                                                answer2.setBackground(redbutton);
                                                if(counterSingleton.sayac == 4){
                                                    counterSingleton.sayac = 0;
                                                    startActivity(new Intent(getActivity(),ScoreScreen.class));
                                                    getActivity().finish();
                                                }
                                                counterSingleton.sayac++;
                                            }
                                            onViewCreated(view,savedInstanceState);
                                        }
                                    });

                                    answer3.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            if (answer3.getText() == finalWords.get(String.valueOf(finalResult2))) {
                                                counterSingleton.dogruSayisi++;
                                                answer3.setBackground(greenbutton);
                                                if(counterSingleton.sayac == 4){
                                                    counterSingleton.sayac = 0;
                                                    startActivity(new Intent(getActivity(),ScoreScreen.class));
                                                    getActivity().finish();
                                                }
                                                counterSingleton.sayac++;
                                            } else {
                                                counterSingleton.yanlisSayisi++;
                                                answer3.setBackground(redbutton);
                                                if(counterSingleton.sayac == 4){
                                                    counterSingleton.sayac = 0;
                                                    startActivity(new Intent(getActivity(),ScoreScreen.class));
                                                    getActivity().finish();
                                                }
                                                counterSingleton.sayac++;
                                            }
                                            onViewCreated(view,savedInstanceState);
                                        }
                                    });

                                    answer4.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            if (answer4.getText() == finalWords.get(String.valueOf(finalResult2))) {
                                                counterSingleton.dogruSayisi++;
                                                answer4.setBackground(greenbutton);
                                                if(counterSingleton.sayac == 4){
                                                    counterSingleton.sayac = 0;
                                                    startActivity(new Intent(getActivity(),ScoreScreen.class));
                                                    getActivity().finish();
                                                }
                                                counterSingleton.sayac++;
                                            } else {
                                                counterSingleton.yanlisSayisi++;
                                                answer4.setBackground(redbutton);
                                                if(counterSingleton.sayac == 4){
                                                    counterSingleton.sayac = 0;
                                                    startActivity(new Intent(getActivity(),ScoreScreen.class));
                                                    getActivity().finish();
                                                }
                                                counterSingleton.sayac++;
                                            }
                                            onViewCreated(view,savedInstanceState);
                                        }
                                    });
                                }
                            }
                        });
                    }
                }
            });

            //diğer işlemler
        } else {

            int [] questionArray = new int[5];
            questionArray[0] = r.nextInt(high2 - low2 + 1) + low2;
            questionArray[1] = questionArray[0] + r.nextInt(high2 - low2 + 1) + low2;
            questionArray[2] = questionArray[1] + r.nextInt(high2 - low2 + 1) + low2;
            questionArray[3] = questionArray[2] + r.nextInt(high2 - low2 + 1) + low2;
            questionArray[4] = questionArray[3] + r.nextInt(high2 - low2 + 1) + low2;

            DocumentReference documentReference = db.collection("Hard Words").document("WteLqGzTIZJQDaBFmjH2")
                    .collection("english").document("ubLVV8ZcAn4SsPgl4YqV");

            documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot != null) {

                        HashMap words;
                        words = (HashMap) documentSnapshot.getData();

                        int result = questionArray[counterSingleton.sayac];
                        finalResult2 = result;

                        question.setText(words.get(String.valueOf(finalResult2)).toString());
                        sorusayac.setText(String.valueOf(counterSingleton.sayac + 1));

                        answer1.setBackground(button_bg);
                        answer2.setBackground(button_bg);
                        answer3.setBackground(button_bg);
                        answer4.setBackground(button_bg);

                        //cevaplar

                        DocumentReference documentReference1 = db.collection("Hard Words").document("WteLqGzTIZJQDaBFmjH2")
                                .collection("turkish").document("9wDLtFSTTkDdQvDjkJyh");
                        documentReference1.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot != null) {

                                    int result2 = r.nextInt(high - low + 1) + low;
                                    int result3 = r.nextInt(high - low + 1) + low;
                                    int result4 = r.nextInt(high - low + 1) + low;
                                    int result5 = r.nextInt(high - low + 1) + low;
                                    HashMap words;
                                    words = (HashMap) documentSnapshot.getData();

                                    int finalResult = finalResult2;


                                    while (result2 == finalResult || result2 == result3 || result2 == result4 || result2 == result5) {
                                        result2 = r.nextInt(high - low + 1) + low;
                                    }
                                    while (result3 == result2 || result3 == finalResult || result3 == result4 || result3 == result5) {
                                        result3 = r.nextInt(high - low + 1) + low;
                                    }
                                    while (result4 == result3 || result4 == result2 || result4 == finalResult || result4 == result5) {
                                        result4 = r.nextInt(high - low + 1) + low;
                                    }

                                    int low = 1;
                                    int high = 4;
                                    int cevap = r.nextInt(high - low + 1) + low;

                                    if (cevap == 1) {
                                        answer1.setText(words.get(String.valueOf(finalResult)).toString());
                                        answer2.setText(words.get(String.valueOf(result3)).toString());
                                        answer3.setText(words.get(String.valueOf(result4)).toString());
                                        answer4.setText(words.get(String.valueOf(result5)).toString());
                                    } else if (cevap == 2) {
                                        answer1.setText(words.get(String.valueOf(result2)).toString());
                                        answer2.setText(words.get(String.valueOf(finalResult)).toString());
                                        answer3.setText(words.get(String.valueOf(result4)).toString());
                                        answer4.setText(words.get(String.valueOf(result5)).toString());
                                    } else if (cevap == 3) {
                                        answer1.setText(words.get(String.valueOf(result2)).toString());
                                        answer2.setText(words.get(String.valueOf(result3)).toString());
                                        answer3.setText(words.get(String.valueOf(finalResult)).toString());
                                        answer4.setText(words.get(String.valueOf(result5)).toString());
                                    } else {
                                        answer1.setText(words.get(String.valueOf(result2)).toString());
                                        answer2.setText(words.get(String.valueOf(result3)).toString());
                                        answer3.setText(words.get(String.valueOf(result4)).toString());
                                        answer4.setText(words.get(String.valueOf(finalResult)).toString());
                                    }

                                    HashMap finalWords = words;

                                    answer1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {


                                            if (answer1.getText() == finalWords.get(String.valueOf(finalResult2))) {
                                                counterSingleton.dogruSayisi++;
                                                answer1.setBackground(greenbutton);
                                                if(counterSingleton.sayac == 4){
                                                    counterSingleton.sayac = 0;
                                                    startActivity(new Intent(getActivity(),ScoreScreen.class));
                                                    getActivity().finish();
                                                }
                                                counterSingleton.sayac++;
                                            } else {
                                                counterSingleton.yanlisSayisi++;
                                                answer1.setBackground(redbutton);
                                                if(counterSingleton.sayac == 4){
                                                    counterSingleton.sayac = 0;
                                                    startActivity(new Intent(getActivity(),ScoreScreen.class));
                                                    getActivity().finish();
                                                }
                                                counterSingleton.sayac++;
                                            }
                                            onViewCreated(view,savedInstanceState);
                                        }
                                    });

                                    answer2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            if (answer2.getText() == finalWords.get(String.valueOf(finalResult2))) {
                                                counterSingleton.dogruSayisi++;
                                                answer2.setBackground(greenbutton);
                                                if(counterSingleton.sayac == 4){
                                                    counterSingleton.sayac = 0;
                                                    startActivity(new Intent(getActivity(),ScoreScreen.class));
                                                    getActivity().finish();
                                                }
                                                counterSingleton.sayac++;
                                            } else {
                                                counterSingleton.yanlisSayisi++;
                                                answer2.setBackground(redbutton);
                                                if(counterSingleton.sayac == 4){
                                                    counterSingleton.sayac = 0;
                                                    startActivity(new Intent(getActivity(),ScoreScreen.class));
                                                    getActivity().finish();
                                                }
                                                counterSingleton.sayac++;
                                            }
                                            onViewCreated(view,savedInstanceState);
                                        }
                                    });

                                    answer3.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            if (answer3.getText() == finalWords.get(String.valueOf(finalResult2))) {
                                                counterSingleton.dogruSayisi++;
                                                answer3.setBackground(greenbutton);
                                                if(counterSingleton.sayac == 4){
                                                    counterSingleton.sayac = 0;
                                                    startActivity(new Intent(getActivity(),ScoreScreen.class));
                                                    getActivity().finish();
                                                }
                                                counterSingleton.sayac++;
                                            } else {
                                                counterSingleton.yanlisSayisi++;
                                                answer3.setBackground(redbutton);
                                                if(counterSingleton.sayac == 4){
                                                    counterSingleton.sayac = 0;
                                                    startActivity(new Intent(getActivity(),ScoreScreen.class));
                                                    getActivity().finish();
                                                }
                                                counterSingleton.sayac++;
                                            }
                                            onViewCreated(view,savedInstanceState);
                                        }
                                    });

                                    answer4.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            if (answer4.getText() == finalWords.get(String.valueOf(finalResult2))) {
                                                counterSingleton.dogruSayisi++;
                                                answer4.setBackground(greenbutton);
                                                if(counterSingleton.sayac == 4){
                                                    counterSingleton.sayac = 0;
                                                    startActivity(new Intent(getActivity(),ScoreScreen.class));
                                                    getActivity().finish();
                                                }
                                                counterSingleton.sayac++;
                                            } else {
                                                counterSingleton.yanlisSayisi++;
                                                answer4.setBackground(redbutton);
                                                if(counterSingleton.sayac == 4){
                                                    counterSingleton.sayac = 0;
                                                    startActivity(new Intent(getActivity(),ScoreScreen.class));
                                                    getActivity().finish();
                                                }
                                                counterSingleton.sayac++;
                                            }
                                            onViewCreated(view,savedInstanceState);
                                        }
                                    });
                                }
                            }
                        });
                    }
                }
            });

        }

    }
}