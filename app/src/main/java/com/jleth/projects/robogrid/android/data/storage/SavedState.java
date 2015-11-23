package com.jleth.projects.robogrid.android.data.storage;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

public class SavedState extends View.BaseSavedState {
    private Bundle stateToSave;

    public SavedState(Parcelable superState) {
      super(superState);
    }

    private SavedState(Parcel in) {
      super(in);
      this.stateToSave = in.readBundle();
    }

    public Bundle getBundle() {
        return stateToSave;
    }

    public void setBundle(Bundle stateToSave) {
        this.stateToSave = stateToSave;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
      super.writeToParcel(out, flags);
      out.writeBundle(this.stateToSave);
    }

    //required field that makes Parcelables from a Parcel
    public static final Creator<SavedState> CREATOR =
        new Creator<SavedState>() {
          public SavedState createFromParcel(Parcel in) {
            return new SavedState(in);
          }
          public SavedState[] newArray(int size) {
            return new SavedState[size];
          }
    };
  }