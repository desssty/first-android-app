package com.example.laba2.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class User implements Parcelable {
    private long id;
    private String name;
    private String bday;
    private String gender;

    public User(long id, String name, String bday, String gender) {
        this.id = id;
        this.name = name;
        this.bday = bday;
        this.gender = gender;
    }

    public User(String name, String bday, String gender) {
        this(-1, name, bday, gender);
    }

    protected User(Parcel in) {
        id = in.readLong();
        name = in.readString();
        bday = in.readString();
        gender = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(null);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBday() {
        return bday;
    }

    public String getGender() {
        return gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBday(String bday) {
        this.bday = bday;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeLong(id);
        parcel.writeString(name);
        parcel.writeString(bday);
        parcel.writeString(gender);
    }
}
