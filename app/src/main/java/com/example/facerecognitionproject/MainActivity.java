package com.example.facerecognitionproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button recognate;
    private Button addFace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        configureViews();
    }

    private void configureViews() {
        addFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddFaceFragment addFaceFragment = new AddFaceFragment();
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .add(R.id.camera_fragment, addFaceFragment)
                        .addToBackStack("addFace")
                        .commit();
            }
        });
    }

    private void findViews() {
        recognate = findViewById(R.id.recognate);
        addFace = findViewById(R.id.add_face);
    }


}