package com.example.laba2;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CatalogActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CatAdapter adapter;
    private List<Cat> catList;
    private Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        recyclerView = findViewById(R.id.catalogRecyclerView);
        buttonBack = findViewById(R.id.buttonBack);

        buttonBack.setText(R.string.button_return);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        catList = new ArrayList<>();
        catList.add(new Cat("Барсик", new Breed("Сиамская")));
        catList.add(new Cat("Мурзик", new Breed("Персидская")));
        catList.add(new Cat("Снежок", new Breed("Британская")));
        catList.add(new Cat("Лютик", new Breed("Беспородный")));

        adapter = new CatAdapter(this, catList);
        recyclerView.setAdapter(adapter);

        buttonBack.setOnClickListener(v -> finish());
    }
}
