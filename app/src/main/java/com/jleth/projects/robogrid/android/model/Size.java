package com.jleth.projects.robogrid.android.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Size implements Parcelable {

    private final int height;
    private final int width;

    public Size(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }


    protected Size(Parcel in) {
        height = in.readInt();
        width = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(height);
        dest.writeInt(width);
    }

    @SuppressWarnings("unused")
    public static final Creator<Size> CREATOR = new Creator<Size>() {
        @Override
        public Size createFromParcel(Parcel in) {
            return new Size(in);
        }

        @Override
        public Size[] newArray(int size) {
            return new Size[size];
        }
    };
}