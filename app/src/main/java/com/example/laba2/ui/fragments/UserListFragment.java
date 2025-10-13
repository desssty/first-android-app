package com.example.laba2.ui.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.laba2.R;
import com.example.laba2.model.User;
import com.example.laba2.database.UserDatabaseAdapter;
import com.example.laba2.adapter.UserAdapter;

import java.util.List;

public class UserListFragment extends Fragment {

    private UserDatabaseAdapter dbAdapter;
    private UserAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        Button buttonAdd = view.findViewById(R.id.buttonAdd);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new UserAdapter(List.of(), user -> openEditFragment(user));
        recyclerView.setAdapter(adapter);

        buttonAdd.setOnClickListener(v -> openEditFragment(null));

        return view;
    }

    private void loadUsers() {
        dbAdapter = new UserDatabaseAdapter(getContext()).open();
        List<User> users = dbAdapter.getUsers();
        dbAdapter.close();
        adapter.updateUsers(users);
    }

    private void openEditFragment(User user) {
        UserEditFragment nextFrag = new UserEditFragment();
        Bundle args = new Bundle();
        if (user != null) {
            args.putParcelable("user", user);
        }
        nextFrag.setArguments(args);
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, nextFrag, "editFragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadUsers();
    }
}
