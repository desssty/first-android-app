package com.example.laba2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatViewHolder> {

    private List<Cat> catList;
    private Context context;

    public CatAdapter(Context context, List<Cat> catList) {
        this.context = context;
        this.catList = catList;
    }

    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new CatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {
        Cat cat = catList.get(position);
        holder.nameTextView.setText(cat.getName());
        holder.breedTextView.setText(cat.getBreed().getBreedName());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, CatDetailActivity.class);
            intent.putExtra("catObject", catList.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return catList.size();
    }

    static class CatViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView breedTextView;

        public CatViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.productNameTextView);
            breedTextView = itemView.findViewById(R.id.productBreedTextView);
        }
    }
}
