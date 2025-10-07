package com.example.laba2.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.laba2.R;

public class SecondActivity extends AppCompatActivity {
    private long startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        startTime = System.currentTimeMillis();

        Button buttonBack = findViewById(R.id.button2);
        buttonBack.setText(R.string.button_return);

        buttonBack.setOnClickListener(v -> {
            long timeSpent = System.currentTimeMillis() - startTime;
            Intent resultIntent = new Intent();
            resultIntent.putExtra("time_spent", timeSpent);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
