package com.beta1.androidtest1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class  ActivityAuth extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void sendLogin(View view){
        Intent intent = new Intent(this,Products.class);
        startActivity(intent);
    }

    public void sendSingUp(View view){
        Intent intent = new Intent(this,SignUp.class);
        startActivity(intent);
    }



}