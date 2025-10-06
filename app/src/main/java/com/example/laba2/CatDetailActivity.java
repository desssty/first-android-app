package com.example.laba2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CatDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_detail);

        TextView detailText = findViewById(R.id.detailTextView);
        TextView breedText = findViewById(R.id.breedTextView);
        Button buttonBack = findViewById(R.id.button4);

        Cat cat = getIntent().getParcelableExtra("catObject");
        if (cat != null) {
            detailText.setText(getString(R.string.selected_cat, cat.getName()));
            breedText.setText(getString(R.string.selected_breed, cat.getBreed().getBreedName()));
        }

        buttonBack.setText(R.string.button_return);
        buttonBack.setOnClickListener(v -> finish());
    }
}
