package com.example.laba2.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Cat implements Parcelable {
    private String name;
    private Breed breed;

    public Cat(String name, Breed breed) {
        this.name = name;
        this.breed = breed;
    }

    protected Cat(Parcel in) {
        name = in.readString();
        breed = in.readParcelable(Breed.class.getClassLoader());
    }

    public static final Parcelable.Creator<Cat> CREATOR = new Creator<Cat>() {
        @Override
        public Cat createFromParcel(Parcel in) {
            return new Cat(in);
        }

        @Override
        public Cat[] newArray(int size) {
            return new Cat[size];
        }
    };

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Breed getBreed() { return breed; }
    public void setBreed(Breed breed) { this.breed = breed; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeParcelable(breed, flags);
    }
}
