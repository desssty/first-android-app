package com.example.laba2.ui.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.laba2.R;

public class SettingsFragment extends Fragment {
    public SettingsFragment() {
        super(R.layout.fragment_settings);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Switch switchDarkMode = view.findViewById(R.id.switchDarkMode);
        CheckBox checkBoxNotifications = view.findViewById(R.id.checkBoxNotifications);

        switchDarkMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String message = isChecked ? "Тёмная тема включена" : "Тёмная тема выключена";
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
        });

        checkBoxNotifications.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String message = isChecked ? "Уведомления включены" : "Уведомления выключены";
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
        });
    }
}
