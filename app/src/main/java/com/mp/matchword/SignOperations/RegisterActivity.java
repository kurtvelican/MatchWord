package com.mp.matchword.SignOperations;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mp.matchword.R;
import com.mp.matchword.databinding.ActivityRegisterBinding;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private FirebaseAuth mAuth;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mAuth = FirebaseAuth.getInstance();

        EditText editUserName = findViewById(R.id.inputUserName);
        EditText editEmail = findViewById(R.id.inputEmail);
        EditText editPassword = findViewById(R.id.inputPassword);
        EditText editConfirmPassword = findViewById(R.id.inputConfirmPassword);
        Button signUpButton = findViewById(R.id.signInButton);


        TextView toSignInpage = findViewById(R.id.alreadyHaveAccount);
        toSignInpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });

        editUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterValues();
            }
        });

        editEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterValues();
            }
        });

        editPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterValues();
            }
        });

        editConfirmPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterValues();
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText editUserName,editEmail,editPassword,editConfirmPassword;
                editUserName = (EditText) findViewById(R.id.inputUserName);
                editEmail = (EditText)  findViewById(R.id.inputEmail);
                editPassword = (EditText)  findViewById(R.id.inputPassword);
                editConfirmPassword = (EditText)  findViewById(R.id.inputConfirmPassword);

                String userName = editUserName.getText().toString().trim();
                String email = binding.inputEmail.getText().toString().trim();
                String password = binding.inputPassword.getText().toString().trim();
                String confirmPassword = editConfirmPassword.getText().toString().trim();

                if(userName.isEmpty()){
                    editUserName.setError("User name is required");
                    editUserName.requestFocus();
                    return;
                }

                if(email.isEmpty()){
                    editEmail.setError("Email is required");
                    editEmail.requestFocus();
                    return;
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    editEmail.setError("Please enter valid e-mail");
                    editEmail.requestFocus();
                    return;
                }

                if(password.isEmpty()){
                    editPassword.setError("Password is required");
                    editPassword.requestFocus();
                    return;
                }

                if(password.length() < 6){
                    editPassword.setError("Enter at least 6 characters for password");
                    editPassword.requestFocus();
                    return;
                }

                if(confirmPassword.isEmpty()){
                    editConfirmPassword.setError("Confirm password is required");
                    editConfirmPassword.requestFocus();
                    return;
                }

                if(!confirmPassword.matches(password)){
                    editConfirmPassword.setError("Confirm password is not same your password");
                    editConfirmPassword.requestFocus();
                    return;
                }

                String userNameBind = binding.inputUserName.getText().toString().trim();
                String emailBind = binding.inputEmail.getText().toString().trim();
                String passwordBİnd = binding.inputPassword.getText().toString().trim();
                ProgressBar editProgressBar = findViewById(R.id.progressBar);
                editProgressBar.setVisibility(View.VISIBLE);

                mAuth.createUserWithEmailAndPassword(emailBind, passwordBİnd)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(RegisterActivity.this,"User sign up succesfully!",Toast.LENGTH_LONG).show();
                                editProgressBar.setVisibility(View.GONE);
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                finish();

                                HashMap<String,Object> user = new HashMap<>();
                                user.put("User Name",userNameBind);
                                user.put("Email",emailBind);
                                user.put("Password",passwordBİnd);


                                firebaseFirestore = FirebaseFirestore.getInstance();
                                firebaseFirestore.collection("Users").document(emailBind).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(RegisterActivity.this,"User not saved database!",Toast.LENGTH_LONG).show();
                                    }
                                });

                                HashMap<String,Object> scores = new HashMap<>();
                                scores.put("UserName",userNameBind);
                                scores.put("Score", 0);

                                firebaseFirestore.collection("Scores").document(emailBind).set(scores).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(RegisterActivity.this,"Scores not saved database!",Toast.LENGTH_LONG).show();
                                    }
                                });

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(RegisterActivity.this,"Failed to sign up! Try again!",Toast.LENGTH_LONG).show();
                                editProgressBar.setVisibility(View.GONE);
                            }
                        });


            }
        });
    }

    private void enterValues(){

        EditText editUserName,editEmail,editPassword,editConfirmPassword;
        editUserName = (EditText) findViewById(R.id.inputUserName);
        editEmail = (EditText)  findViewById(R.id.inputEmail);
        editPassword = (EditText)  findViewById(R.id.inputPassword);
        editConfirmPassword = (EditText)  findViewById(R.id.inputConfirmPassword);

        String userName = editUserName.getText().toString().trim();
        String email = binding.inputEmail.getText().toString().trim();
        String password = binding.inputPassword.getText().toString().trim();
        String confirmPassword = editConfirmPassword.getText().toString().trim();

        if(userName.isEmpty()){
            editUserName.setError("User name is required");
            editUserName.requestFocus();
            return;
        }

        if(email.isEmpty()){
            editEmail.setError("Email is required");
            editEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editEmail.setError("Please enter valid e-mail");
            editEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editPassword.setError("Password is required");
            editPassword.requestFocus();
            return;
        }

        if(password.length() < 6){
            editPassword.setError("Enter at least 6 characters for password");
            editPassword.requestFocus();
            return;
        }

        if(confirmPassword.isEmpty()){
            editConfirmPassword.setError("Confirm password is required");
            editConfirmPassword.requestFocus();
            return;
        }

        if(!confirmPassword.matches(password)){
            editConfirmPassword.setError("Confirm password is not same your password");
            editConfirmPassword.requestFocus();
            return;
        }

    }

}