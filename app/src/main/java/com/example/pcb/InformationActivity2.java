package com.example.pcb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class InformationActivity2 extends AppCompatActivity implements View.OnClickListener {

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
            Intent moveIntent = new Intent(InformationActivity2.this, MainMenuActivity.class);
            startActivity(moveIntent);
        }
    }
}