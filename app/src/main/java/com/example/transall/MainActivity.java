package com.example.transall;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.common.modeldownload.FirebaseModelDownloadConditions;
import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateLanguage;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslator;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslatorOptions;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button capture,convert,translate;
    ImageView img_view;
    TextView text_output;
    Bitmap bmp;
    private static final int ResquestCode = 1;
    Spinner selectLang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        capture = (Button)findViewById(R.id.capture_btn);
        convert = (Button)findViewById(R.id.convert_btn);
        translate = (Button)findViewById(R.id.translate_btn);
        img_view = (ImageView)findViewById(R.id.img_view);
        text_output = (TextView)findViewById(R.id.text_output);
        selectLang = (Spinner)findViewById(R.id.select_lang);

        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text_output.setText("");
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,ResquestCode);
            }
        });

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(bmp);
                FirebaseVisionTextRecognizer  detector = FirebaseVision.getInstance().getOnDeviceTextRecognizer();
                detector.processImage(image).addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
                    @Override
                    public void onSuccess(FirebaseVisionText firebaseVisionText) {
                            ProcessText(firebaseVisionText);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this,"Try Again",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String language = selectLang.getSelectedItem().toString();

                switch (language)
                {
                    case "Hindi":
                    {
                        FirebaseTranslatorOptions options =
                                new FirebaseTranslatorOptions.Builder()
                                        .setSourceLanguage(FirebaseTranslateLanguage.EN)
                                        .setTargetLanguage(FirebaseTranslateLanguage.HI)
                                        .build();
                        TranslateText(options);
                        break;
                    }
                    case "Gujarati":
                    {
                        FirebaseTranslatorOptions options =
                                new FirebaseTranslatorOptions.Builder()
                                        .setSourceLanguage(FirebaseTranslateLanguage.EN)
                                        .setTargetLanguage(FirebaseTranslateLanguage.GU)
                                        .build();
                        TranslateText(options);
                        break;
                    }
                    case "Marathi":
                    {
                        FirebaseTranslatorOptions options =
                                new FirebaseTranslatorOptions.Builder()
                                        .setSourceLanguage(FirebaseTranslateLanguage.EN)
                                        .setTargetLanguage(FirebaseTranslateLanguage.MR)
                                        .build();
                        TranslateText(options);
                        break;
                    }
                    case "Tamil":
                    {
                        FirebaseTranslatorOptions options =
                                new FirebaseTranslatorOptions.Builder()
                                        .setSourceLanguage(FirebaseTranslateLanguage.EN)
                                        .setTargetLanguage(FirebaseTranslateLanguage.TA)
                                        .build();
                        TranslateText(options);
                        break;
                    }
                    case "Portuguese":
                    {
                        FirebaseTranslatorOptions options =
                                new FirebaseTranslatorOptions.Builder()
                                        .setSourceLanguage(FirebaseTranslateLanguage.EN)
                                        .setTargetLanguage(FirebaseTranslateLanguage.PT)
                                        .build();
                        TranslateText(options);
                        break;
                    }
                    case "French":
                    {
                        FirebaseTranslatorOptions options =
                                new FirebaseTranslatorOptions.Builder()
                                        .setSourceLanguage(FirebaseTranslateLanguage.EN)
                                        .setTargetLanguage(FirebaseTranslateLanguage.FR)
                                        .build();
                        TranslateText(options);
                        break;
                    }
                    case "Chinese":
                    {
                        FirebaseTranslatorOptions options =
                                new FirebaseTranslatorOptions.Builder()
                                        .setSourceLanguage(FirebaseTranslateLanguage.EN)
                                        .setTargetLanguage(FirebaseTranslateLanguage.ZH)
                                        .build();
                        TranslateText(options);
                        break;
                    }
                    case "Japanese":
                    {
                        FirebaseTranslatorOptions options =
                                new FirebaseTranslatorOptions.Builder()
                                        .setSourceLanguage(FirebaseTranslateLanguage.EN)
                                        .setTargetLanguage(FirebaseTranslateLanguage.JA)
                                        .build();
                        TranslateText(options);
                        break;
                    }
                    case "Spanish":
                    {
                        FirebaseTranslatorOptions options =
                                new FirebaseTranslatorOptions.Builder()
                                        .setSourceLanguage(FirebaseTranslateLanguage.EN)
                                        .setTargetLanguage(FirebaseTranslateLanguage.ES)
                                        .build();
                        TranslateText(options);
                        break;
                    }
                    case "Italian":
                    {
                        FirebaseTranslatorOptions options =
                                new FirebaseTranslatorOptions.Builder()
                                        .setSourceLanguage(FirebaseTranslateLanguage.EN)
                                        .setTargetLanguage(FirebaseTranslateLanguage.IT)
                                        .build();
                        TranslateText(options);
                        break;
                    }
                    default:
                        Toast.makeText(MainActivity.this,"Select the language to convert",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void TranslateText(FirebaseTranslatorOptions options)
    {
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Downloading Language Module");
        progressDialog.setMessage("Please wait till process is completed...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        final String text = text_output.getText().toString();
        // Create an English-German translator:
        final FirebaseTranslator englishTranslator =
                FirebaseNaturalLanguage.getInstance().getTranslator(options);

        FirebaseModelDownloadConditions conditions = new FirebaseModelDownloadConditions.Builder().build();
        englishTranslator.downloadModelIfNeeded(conditions)
                .addOnSuccessListener(
                        new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void v) {
                                progressDialog.cancel();
                                englishTranslator.translate(text)
                                        .addOnSuccessListener(
                                                new OnSuccessListener<String>() {
                                                    @Override
                                                    public void onSuccess(@NonNull String translatedText) {
                                                        text_output.setText(translatedText);
                                                    }
                                                })
                                        .addOnFailureListener(
                                                new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(MainActivity.this,"Text Not Converted",Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                            }
                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity.this,"Module Not Present",Toast.LENGTH_SHORT).show();
                            }
                        });
    }

    private void ProcessText(FirebaseVisionText firebaseVisionText) {
        List<FirebaseVisionText.TextBlock> block = firebaseVisionText.getTextBlocks();
        if(block.size() == 0)
        {
            Toast.makeText(MainActivity.this,"Image Not Found",Toast.LENGTH_SHORT).show();
        }
        else{
            String output = "";
            text_output.setText("");
            for(FirebaseVisionText.TextBlock b : firebaseVisionText.getTextBlocks())
            {
                for(int i = 0;i<block.size();i++)
                {
                    List<FirebaseVisionText.Line> line = block.get(i).getLines();
                    for(int j = 0;j<line.size();j++)
                    {
                        List<FirebaseVisionText.Element> element = line.get(j).getElements();
                        for(FirebaseVisionText.Element e : element)
                        {
                            output = output + e.getText() + " ";
                        }
                        output = output + "\n";
                    }
                }
            }
            text_output.setText(output);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ResquestCode && resultCode == RESULT_OK)
        {
            Bundle bundle = data.getExtras();
            bmp = (Bitmap) bundle.get("data");
            img_view.setImageBitmap(bmp);

        }
    }
}
