package com.example.pcb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class InformationActivity1 extends AppCompatActivity implements View.OnClickListener {

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
            Intent moveIntent = new Intent(InformationActivity1.this, InformationActivity2.class);
            startActivity(moveIntent);
        }
    }
}