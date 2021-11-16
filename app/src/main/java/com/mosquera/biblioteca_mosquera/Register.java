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
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText name,email,password;
    private LinearLayout layoutBlock;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        name = findViewById(R.id.name_register);
        email = findViewById(R.id.email_register);
        password = findViewById(R.id.password_register);
        layoutBlock = findViewById(R.id.layout_block_login);
    }




    public void registerNewUser(View view) {



        String emailText = email.getText().toString().trim();
        String passwordText = password.getText().toString().trim();
        String nameText = name.getText().toString().trim();

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

        if(nameText.isEmpty()){
            name.setError("El nombre es obligatorio");
            name.requestFocus();
            return;
        }


        layoutBlock.setVisibility(view.VISIBLE);
        name.setFocusable(false);
        email.setFocusable(false);
        password.setFocusable(false);


    mAuth.createUserWithEmailAndPassword(emailText,passwordText).addOnCompleteListener(new OnCompleteListener<AuthResult>() {




        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {

            layoutBlock.setVisibility(view.INVISIBLE);
            name.setFocusableInTouchMode(true);
            email.setFocusableInTouchMode(true);
            password.setFocusableInTouchMode(true);

            if(task.isSuccessful()){
                User user = new User(nameText,emailText);
                FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_SHORT).show();
                            Intent intent= new Intent(Register.this, navigation_home.class);
                            startActivity(intent);
                        } else{
                            Toast.makeText(getApplicationContext(), "Error en el registro", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else{
                layoutBlock.setVisibility(view.INVISIBLE);
                Toast.makeText(getApplicationContext(), "Error en el registro", Toast.LENGTH_SHORT).show();
            }

        }
    });


    }
}