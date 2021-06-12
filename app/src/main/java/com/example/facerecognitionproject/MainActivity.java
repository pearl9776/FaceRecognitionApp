package com.example.facerecognitionproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button recognate;
    private Button addFace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = this.getWindow().getDecorView();
        decorView.setSystemUiVisibility(0);
        setContentView(R.layout.activity_main);

        findViews();
        configureViews();
    }

    private void configureViews() {
        addFace.setOnClickListener(v -> {
            AddFaceFragment addFaceFragment = new AddFaceFragment();
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.camera_fragment, addFaceFragment)
                    .addToBackStack("addFace")
                    .commit();
        });

        recognate.setOnClickListener(v-> {
            Toast.makeText(this, "This part is not ready!", Toast.LENGTH_LONG).show();
        });
    }

    private void findViews() {
        recognate = findViewById(R.id.recognate);
        addFace = findViewById(R.id.add_face);
    }

    @Override
    public void onResume() {
        super.onResume();
        View decorView = this.getWindow().getDecorView();
        decorView.setSystemUiVisibility(0);
    }

}
