package com.example.laba2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.laba2.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDatabaseAdapter {
    private UserDatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public UserDatabaseAdapter(Context context) {
        dbHelper = new UserDatabaseHelper(context.getApplicationContext());
    }

    public UserDatabaseAdapter open() {
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        Cursor cursor = database.query(UserDatabaseHelper.TABLE,
                null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(UserDatabaseHelper.COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(UserDatabaseHelper.COLUMN_NAME));
            String bday = cursor.getString(cursor.getColumnIndexOrThrow(UserDatabaseHelper.COLUMN_BDAY));
            String gender = cursor.getString(cursor.getColumnIndexOrThrow(UserDatabaseHelper.COLUMN_GENDER));

            users.add(new User(id, name, bday, gender));
        }
        cursor.close();
        return users;
    }

    public long insert(User user) {
        ContentValues cv = new ContentValues();
        cv.put(UserDatabaseHelper.COLUMN_NAME, user.getName());
        cv.put(UserDatabaseHelper.COLUMN_BDAY, user.getBday());
        cv.put(UserDatabaseHelper.COLUMN_GENDER, user.getGender());
        return database.insert(UserDatabaseHelper.TABLE, null, cv);
    }

    public int update(User user) {
        ContentValues cv = new ContentValues();
        cv.put(UserDatabaseHelper.COLUMN_NAME, user.getName());
        cv.put(UserDatabaseHelper.COLUMN_BDAY, user.getBday());
        cv.put(UserDatabaseHelper.COLUMN_GENDER, user.getGender());
        return database.update(
                UserDatabaseHelper.TABLE,
                cv,
                UserDatabaseHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
    }

    public int delete(long id) {
        return database.delete(
                UserDatabaseHelper.TABLE,
                UserDatabaseHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public User getUser(long id) {
        Cursor cursor = database.query(
                UserDatabaseHelper.TABLE,
                null,
                UserDatabaseHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)},
                null, null, null);

        User user = null;
        if (cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(UserDatabaseHelper.COLUMN_NAME));
            String bday = cursor.getString(cursor.getColumnIndexOrThrow(UserDatabaseHelper.COLUMN_BDAY));
            String gender = cursor.getString(cursor.getColumnIndexOrThrow(UserDatabaseHelper.COLUMN_GENDER));
            user = new User(id, name, bday, gender);
        }
        cursor.close();
        return user;
    }
}
