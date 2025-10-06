package com.example.laba2;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;

public class FragmentActivity extends AppCompatActivity implements FirstFragment.OnFragmentSendDataListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        Button buttonBack = findViewById(R.id.buttonBack2);
        buttonBack.setOnClickListener(v -> finish());
    }

    @Override
    public void onSendData(String data) {
        SecondFragment secondFragment = (SecondFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView2);

        if (secondFragment != null) {
            secondFragment.setSelectedItem(data);
        }
    }
}
