package com.example.transall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.hololo.tutorial.library.Step;
import com.hololo.tutorial.library.TutorialActivity;

public class OnBoardActivity extends TutorialActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addFragment(new Step.Builder().setTitle("Text Detection")
                .setContent("Recognize and extract text from images")
                .setBackgroundColor(Color.parseColor("#22D1B3"))
                .setDrawable(R.drawable.textrecognition)
                .setSummary("Continue to explore more")
                .build());

        addFragment(new Step.Builder().setTitle("Translate")
                .setContent("Translate text from one language to another")
                .setBackgroundColor(Color.parseColor("#22D1B3"))
                .setDrawable(R.drawable.ondevicetranslate)
                .setSummary("Continue to explore more")
                .build());

        addFragment(new Step.Builder().setTitle("Object Detection")
                .setContent("Identify objects, locations, activities, animal species, products")
                .setBackgroundColor(Color.parseColor("#22D1B3"))
                .setDrawable(R.drawable.imagelabelling)
                .setSummary("Finish to use features")
                .build());
    }

    @Override
    public void currentFragmentPosition(int position) {
    }

    @Override
    public void finishTutorial() {
        Intent i = new Intent(OnBoardActivity.this,Optionactivity.class);
        startActivity(i);
        finish();
    }
}
