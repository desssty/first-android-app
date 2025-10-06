package com.example.laba2;

import android.os.Parcel;
import android.os.Parcelable;

public class Breed implements Parcelable {
    private String breedName;

    public Breed(String breedName) {
        this.breedName = breedName;
    }

    protected Breed(Parcel in) {
        breedName = in.readString();
    }

    public static final Creator<Breed> CREATOR = new Creator<Breed>() {
        @Override
        public Breed createFromParcel(Parcel in) {
            return new Breed(in);
        }

        @Override
        public Breed[] newArray(int size) {
            return new Breed[size];
        }
    };

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(breedName);
    }
}
