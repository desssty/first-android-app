package com.example.laba2.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.laba2.R;

import java.util.Arrays;
import java.util.List;
import com.example.laba2.adapter.ItemAdapter;

public class FirstFragment extends Fragment {

    public interface OnFragmentSendDataListener {
        void onSendData(String data);
    }

    private OnFragmentSendDataListener fragmentSendDataListener;
    private RecyclerView recyclerView;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentSendDataListener) {
            fragmentSendDataListener = (OnFragmentSendDataListener) context;
        } else {
            throw new ClassCastException(context + " must implement OnFragmentSendDataListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<String> items = Arrays.asList("Элемент 1", "Элемент 2", "Элемент 3", "Элемент 4", "Элемент 5", "Элемент 6", "Элемент 7", "Элемент 8", "Элемент 9", "Элемент 10");

        ItemAdapter adapter = new ItemAdapter(items, item -> {
            if (fragmentSendDataListener != null) {
                fragmentSendDataListener.onSendData(item);
            }
        });

        recyclerView.setAdapter(adapter);

        return view;
    }
}
