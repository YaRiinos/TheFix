package com.sourcey.theFixApp.items;

import android.os.Parcel;
import android.os.Parcelable;

public class categoryType implements Parcelable{
    String type;

    public categoryType() {}

    public categoryType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "categoryType{" +
                "type='" + type + '\'' +
                '}';
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(type);
    }

    public static final Parcelable.Creator<categoryType> CREATOR
            = new Parcelable.Creator<categoryType>() {
        public categoryType createFromParcel(Parcel in) {
            return new categoryType(in);
        }

        public categoryType[] newArray(int size) {
            return new categoryType[size];
        }
    };

    private categoryType(Parcel in) {
        type = in.readString();
    }

}
