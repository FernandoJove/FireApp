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
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private EditText mnombre_txt, mpassword_txt;
    private Button mbtnsignin;
    private String nombre_ ="";
    private String password_ ="";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mnombre_txt = (EditText) findViewById(R.id.user_text);
        mpassword_txt = (EditText) findViewById(R.id.password_text);
        mbtnsignin = (Button) findViewById(R.id.button_singin);

        mbtnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre_ = mnombre_txt.getText().toString();
                password_ =mpassword_txt.getText().toString();

                if(!nombre_.isEmpty() && !password_.isEmpty()){

                    if(password_.length() >= 6){

                          loginUser();
                          Toast.makeText(Login.this,"Bienvenido Usuario",Toast.LENGTH_LONG).show();
                          startActivity(new Intent(Login.this,Products.class));
                    }else {
                        Toast.makeText(Login.this,"El password tiene que ser de 6 a mas carácteres",Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(Login.this,"Falta completar los campos",Toast.LENGTH_LONG).show();
                }
            }

        });


    }

    private void loginUser() {
        mAuth.signInWithEmailAndPassword(mnombre_txt.getText().toString(), mpassword_txt.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();
                            String email = user.getEmail();
                            String name = user.getDisplayName();
                            Toast.makeText(Login.this, "Name : " + name +"\n" + "Email : " + email,
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Login.this,  "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();


                        }

                    }
                });
    }



    public void back(View view){
        Intent intent = new Intent(this,ActivityAuth.class);
        startActivity(intent);
    }
}