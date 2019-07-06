package com.example.transall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Optionactivity extends AppCompatActivity {

    Button textrecognition,objectdetection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optionactivity);

        textrecognition = (Button)findViewById(R.id.text_recognize_btn);
        objectdetection = (Button)findViewById(R.id.object_detect_btn);

        textrecognition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Optionactivity.this,MainActivity.class);
                startActivity(i);
            }
        });

        objectdetection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Optionactivity.this,"Work in Progress",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
