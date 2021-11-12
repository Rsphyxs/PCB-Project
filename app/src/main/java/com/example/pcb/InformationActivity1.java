package com.example.pcb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class InformationActivity1 extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_EMAIL = "email_user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information1);

        ImageButton nextActivity = findViewById(R.id.nextbutton);
        nextActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.nextbutton) {
            String dataEmail = getIntent().getStringExtra(EXTRA_EMAIL);
            Intent moveIntent = new Intent(InformationActivity1.this, InformationActivity2.class);
            moveIntent.putExtra(InformationActivity2.EXTRA_EMAIL, dataEmail);
            startActivity(moveIntent);
        }
    }
}