package com.jleth.projects.robogrid.android.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Location implements Parcelable {

    private final int x;
    private final int y;
    private final int direction;

    public Location(int x, int y, @Direction.Constant int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public static boolean isLocationValid(Location location) {
        return location != null && location.x >= 0 && location.y >= 0 && location.direction > Direction.UNKNOWN;
    }

    @Direction.Constant
    public int getDirection() {
        return direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (x != location.x) return false;
        if (y != location.y) return false;
        return direction == location.direction;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + direction;
        return result;
    }

    protected Location(Parcel in) {
        x = in.readInt();
        y = in.readInt();
        direction = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(x);
        dest.writeInt(y);
        dest.writeInt(direction);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Location> CREATOR = new Parcelable.Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };
}