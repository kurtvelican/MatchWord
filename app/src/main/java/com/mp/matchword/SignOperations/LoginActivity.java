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
import com.google.firebase.auth.FirebaseUser;
import com.mp.matchword.MainPage;
import com.mp.matchword.R;
import com.mp.matchword.User;
import com.mp.matchword.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private FirebaseAuth mAuth;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mAuth = FirebaseAuth.getInstance();
        user=User.getInstance();
        TextView toSignUp = findViewById(R.id.textViewSignUp);
        toSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });

        TextView forgotPassword = findViewById(R.id.forgotPassword);
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,ForgotPassword.class));
                finish();
            }
        });

        EditText editEmail = findViewById(R.id.inputEmail);
        EditText editPassword = findViewById(R.id.inputPassword);

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

        Button signInButton = findViewById(R.id.signInButton);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editEmail,editPassword;
                editEmail = (EditText)  findViewById(R.id.inputEmail);
                editPassword = (EditText)  findViewById(R.id.inputPassword);
                String email = editEmail.getText().toString().trim();
                String password = editPassword.getText().toString().trim();

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

                String emailBind = binding.inputEmail.getText().toString().trim();
                String passwordBind = binding.inputPassword.getText().toString().trim();
                ProgressBar editProgressBar = findViewById(R.id.progressBar);
                editProgressBar.setVisibility(View.VISIBLE);


                mAuth.signInWithEmailAndPassword(emailBind,passwordBind).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();



                        startActivity(new Intent(LoginActivity.this, MainPage.class));
                        finish();

                        /*if(user.isEmailVerified()){
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            finish();
                            editProgressBar.setVisibility(View.GONE);
                        }
                        else{
                            user.sendEmailVerification();
                            Toast.makeText(LoginActivity.this,"Check your email to verify your account",Toast.LENGTH_LONG).show();
                            editProgressBar.setVisibility(View.GONE);
                        }*/
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginActivity.this,"Invalid email or password!",Toast.LENGTH_LONG).show();
                        editProgressBar.setVisibility(View.GONE);
                    }
                });

            }
        });

    }

    private void enterValues(){
        EditText editEmail,editPassword;
        editEmail = (EditText)  findViewById(R.id.inputEmail);
        editPassword = (EditText)  findViewById(R.id.inputPassword);
        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();


        if (email.isEmpty()) {
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

    }
}