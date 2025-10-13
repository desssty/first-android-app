package com.example.laba2.ui.fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.laba2.R;
import com.example.laba2.model.User;
import com.example.laba2.database.UserDatabaseAdapter;

import java.util.Calendar;
import java.util.Locale;

public class UserEditFragment extends Fragment {

    private EditText editName, editBday, editGender;
    private Button buttonSave, buttonDelete, buttonBack;
    private User user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_edit, container, false);

        editName = view.findViewById(R.id.editName);
        editBday = view.findViewById(R.id.editBday);
        editGender = view.findViewById(R.id.editGender);
        buttonSave = view.findViewById(R.id.buttonSave);
        buttonDelete = view.findViewById(R.id.buttonDelete);
        buttonBack = view.findViewById(R.id.buttonBack);
        editGender.setInputType(InputType.TYPE_NULL);
        editGender.setFocusable(false);
        editGender.setOnClickListener(v -> {
            String[] options = {"Male", "Female"};

            new AlertDialog.Builder(getContext())
                    .setTitle("Выберите пол")
                    .setItems(options, (dialog, which) -> {
                        editGender.setText(options[which]);
                    })
                    .show();
        });
        editBday.setInputType(InputType.TYPE_NULL);
        editBday.setFocusable(false);
        editBday.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                    (view1, selectedYear, selectedMonth, selectedDay) -> {
                        String date = String.format(Locale.getDefault(), "%04d-%02d-%02d",
                                selectedYear, selectedMonth + 1, selectedDay);
                        editBday.setText(date);
                    }, year, month, day);

            datePickerDialog.show();
        });
        if (getArguments() != null) {
            user = getArguments().getParcelable("user");
            if (user != null) {
                editName.setText(user.getName());
                editBday.setText(user.getBday());
                editGender.setText(user.getGender());
            }
        }

        buttonSave.setOnClickListener(v -> saveUser());
        buttonDelete.setOnClickListener(v -> deleteUser());
        buttonBack.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStack());

        return view;
    }

    private void saveUser() {
        String name = editName.getText().toString();
        String bday = editBday.getText().toString();
        String gender = editGender.getText().toString();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(bday) || TextUtils.isEmpty(gender)) {
            Toast.makeText(getContext(), "Заполните все поля", Toast.LENGTH_SHORT).show();
            return;
        }

        UserDatabaseAdapter dbAdapter = new UserDatabaseAdapter(getContext()).open();

        if (user == null) {
            dbAdapter.insert(new User(0, name, bday, gender));
            Toast.makeText(getContext(), "Пользователь добавлен", Toast.LENGTH_SHORT).show();
        } else {
            user.setName(name);
            user.setBday(bday);
            user.setGender(gender);
            dbAdapter.update(user);
            Toast.makeText(getContext(), "Изменения сохранены", Toast.LENGTH_SHORT).show();
        }

        dbAdapter.close();
        requireActivity().getSupportFragmentManager().popBackStack();
    }

    private void deleteUser() {
        if (user != null) {
            UserDatabaseAdapter dbAdapter = new UserDatabaseAdapter(getContext()).open();
            dbAdapter.delete(user.getId());
            dbAdapter.close();
            Toast.makeText(getContext(), "Пользователь удалён", Toast.LENGTH_SHORT).show();
        }
        requireActivity().getSupportFragmentManager().popBackStack();
    }
}
