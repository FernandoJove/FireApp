package com.beta1.androidtest1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class SignUp extends AppCompatActivity {

    private EditText txt_nombreuser,txt_passworduser;
    private Button btnsignup;

    private String nombre="";
    private String password="";

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        txt_nombreuser = (EditText) findViewById(R.id.txt_nameuser);
        txt_passworduser = (EditText) findViewById(R.id.txt_passworduser);
        btnsignup = (Button) findViewById(R.id.btn_signup);

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre= txt_nombreuser.getText().toString();
                password= txt_passworduser.getText().toString();

                if(!nombre.isEmpty() && !password.isEmpty()){
                    if(password.length() >= 6){
                        registerUser();
                        Toast.makeText(SignUp.this,"Usuario registrado",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(SignUp.this,Products.class));
                    }else {
                        Toast.makeText(SignUp.this,"El password tiene que ser de 6 a mas carácteres",Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(SignUp.this,"Falta completar los campos",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void registerUser(){
        mAuth.createUserWithEmailAndPassword(nombre,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    Map<String,Object> map = new HashMap<>();
                    map.put("name",nombre);
                    map.put("password",password);
                    String id = mAuth.getCurrentUser().getUid();
                    mDatabase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                        if(task2.isSuccessful()){
                            startActivity(new Intent(SignUp.this,Products.class));
                        }else{
                            Toast.makeText(SignUp.this,"No se pudo registrar los datos correctamente",Toast.LENGTH_LONG).show();
                        }
                        }
                    });
                }else{
                    Toast.makeText(SignUp.this,"No se pudo registrar los datos",Toast.LENGTH_LONG).show();
                }
            }
        });
        
    }


}