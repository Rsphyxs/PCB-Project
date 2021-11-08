package com.example.pcb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnSignup;
    private ImageButton btnPrev;
    private Button btnSkip;
    private Button btnSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnSignin = findViewById(R.id.signin);
        btnSignin.setOnClickListener(this);
        btnSignup = findViewById(R.id.signup);
        btnSignup.setOnClickListener(this);
        btnPrev = findViewById(R.id.prevbutton);
        btnPrev.setOnClickListener(this);
        btnSkip = findViewById(R.id.skipfornow);
        btnSkip.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.signup){
            Intent moveIntent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(moveIntent);
        }
        if (v.getId() == R.id.prevbutton){
            Intent moveIntent = new Intent(LoginActivity.this, InformationActivity2.class);
            startActivity(moveIntent);
        }
        if (v.getId() == R.id.skipfornow){
            Intent moveIntent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(moveIntent);
        }
    }
}