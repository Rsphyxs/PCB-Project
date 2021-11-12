package com.example.pcb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class InformationActivity2 extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_EMAIL = "email_user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information2);

        ImageButton prevActivity = findViewById(R.id.prevbutton);
        prevActivity.setOnClickListener(this);

        ImageButton nextActivity = findViewById(R.id.nextbutton);
        nextActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.prevbutton){
            Intent moveIntent = new Intent(InformationActivity2.this, InformationActivity1.class);
            startActivity(moveIntent);
        }
        if (v.getId() == R.id.nextbutton) {
            String dataEmail = getIntent().getStringExtra(EXTRA_EMAIL);
            Intent moveIntent = new Intent(InformationActivity2.this, MainMenuActivity.class);
            moveIntent.putExtra(MainMenuActivity.EXTRA_EMAIL, dataEmail);
            startActivity(moveIntent);
        }
    }
}