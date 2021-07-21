package com.sayam.memo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity implements OnSuccessListener<AuthResult>, OnFailureListener {
    private EditText name;
    private EditText password;
    private EditText flnm;
    private Button done;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name = findViewById(R.id.mail);
        password = findViewById(R.id.pswrd);
        done = findViewById(R.id.done);
        flnm = findViewById(R.id.flnm);
        mAuth = FirebaseAuth.getInstance();

        done.setOnClickListener(v -> {
            String nm = name.getText().toString();
            String pwd = password.getText().toString();

            //validate email | password combination

            mAuth.createUserWithEmailAndPassword(nm, pwd)
                    .addOnSuccessListener(this)
                    .addOnFailureListener(this);

        });
    }

    public void mainactivity() {
        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Succesfully sign up", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(AuthResult authResult) {

        Log.d("tag", "createUserWithEmail:success");
        FirebaseUser user = mAuth.getCurrentUser();
    }

    @Override
    public void onFailure(@NonNull Exception e) {

        Log.w("TAG", "createUserWithEmail:failure", e);
        Toast.makeText(SignUpActivity.this, "Authentication failed.",
                Toast.LENGTH_SHORT).show();
    }
}
