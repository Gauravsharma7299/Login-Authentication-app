package com.example.loginauthentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    private EditText registerEmail ;
    private EditText registerpPassword;
    private Button RegisterBtn;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerEmail=findViewById(R.id.et_R_Email);
        registerpPassword=findViewById(R.id.et_R_Password);
        RegisterBtn=findViewById(R.id.btn_R_register);
        auth=FirebaseAuth.getInstance();

        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail=registerEmail.getText().toString();
                String pass=registerpPassword.getText().toString();
                if(TextUtils.isEmpty(mail) || TextUtils.isEmpty( pass)) {
                    Toast.makeText(Register.this, "empty credential", Toast.LENGTH_SHORT).show();


                }else if(pass.length()<6){
                    Toast.makeText(Register.this,"Password is too short",Toast.LENGTH_SHORT).show();
                }else{
                    registerUser(mail,pass);
                }
            }
        });


    }

    private void registerUser(String mail, String pass) {
        auth.createUserWithEmailAndPassword(mail, pass).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Register.this,"Registeration successful",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Register.this,Login.class));
                    finish();
                }else{
                    Toast.makeText(Register.this,"Not Successful",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}