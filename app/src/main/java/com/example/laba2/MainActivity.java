package com.example.laba2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button buttonTimer;
    private Button buttonList;;
    private Button buttonFragment;

    private final ActivityResultLauncher<Intent> secondActivityLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            long timeSpent = result.getData().getLongExtra("time_spent", 0);
                            String message = getString(R.string.time_message, timeSpent / 1000.0);
                            new AlertDialog.Builder(MainActivity.this)
                                    .setTitle(R.string.second_activity_title)
                                    .setMessage(message)
                                    .setPositiveButton(android.R.string.ok, null)
                                    .show();
                        }
                    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonTimer = findViewById(R.id.buttonTimer);
        buttonList = findViewById(R.id.buttonList);
        buttonFragment = findViewById(R.id.buttonFragment);

        buttonTimer.setText(R.string.button_timer);
        buttonList.setText(R.string.button_list);
        buttonFragment.setText(R.string.button_fragment);

        buttonTimer.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            secondActivityLauncher.launch(intent);
        });

        buttonList.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CatalogActivity.class);
            startActivity(intent);
        });

        buttonFragment.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FragmentActivity.class);
            startActivity(intent);
        });
    }
}
