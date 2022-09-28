package com.example.loginauthentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Login extends AppCompatActivity {
    private EditText loginEmail,loginPassword;
    private Button LoginBtn;
    private FirebaseAuth Auth;
    private TextView signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginEmail=findViewById(R.id.et_L_Email);
        loginPassword=findViewById(R.id.et_L_Password);
        LoginBtn=findViewById(R.id.btn_L_Login);
        signup=findViewById(R.id.tv_signup);
        Auth=FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sign_up=new Intent(Login.this,Register.class);
                startActivity(sign_up);
            }
        });

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String EMAIL=loginEmail.getText().toString();
                String PASSWORD=loginPassword.getText().toString();
                UserLogin(EMAIL,PASSWORD);
            }
        });

    }

    private void UserLogin(String loginEmail,String loginPassword) {
       Auth.signInWithEmailAndPassword(loginEmail, loginPassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
           @Override
           public void onSuccess(AuthResult authResult) {
               Toast.makeText(Login.this,"Login successful",Toast.LENGTH_SHORT).show();
               startActivity(new Intent(Login.this,MainActivity.class));
               finish();

           }
       });

        }
    }

