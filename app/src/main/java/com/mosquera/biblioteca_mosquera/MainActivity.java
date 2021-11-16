package com.mosquera.biblioteca_mosquera;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText email,password;
    private LinearLayout layoutBlock;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.email_login);
        password = findViewById(R.id.password_login);
        layoutBlock = findViewById(R.id.layout_block_login);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            startActivity(new Intent(MainActivity.this,navigation_home.class));
        }
    }

    public void goRegister(View view) {
        Intent intent= new Intent(MainActivity.this, Register.class);
        startActivity(intent);
    }

    public void logIn(View view) {
        String emailText = email.getText().toString().trim();
        String passwordText = password.getText().toString().trim();


        if(emailText.isEmpty()){
            email.setError("El nombre es obligatorio");
            email.requestFocus();
            return;
        }

        if(passwordText.isEmpty()){
            password.setError("El nombre es obligatorio");
            password.requestFocus();
            return;
        }

        layoutBlock.setVisibility(view.VISIBLE);
        email.setFocusable(false);
        password.setFocusable(false);

        mAuth.signInWithEmailAndPassword(emailText,passwordText).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                email.setFocusableInTouchMode(true);
                password.setFocusableInTouchMode(true);

                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Has iniciado sesión correctamente", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,navigation_home.class));

                }else{
                    layoutBlock.setVisibility(view.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "Error en inciar sesión", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }




}