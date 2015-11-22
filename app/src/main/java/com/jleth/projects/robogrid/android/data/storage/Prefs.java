package com.jleth.projects.robogrid.android.data.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jleth.projects.robogrid.android.model.Size;

/**
 * Storage for simple data
 */
public class Prefs {

    private final SharedPreferences pref;
    private static Prefs sPrefs;


    public static Prefs getInstance(Context context) {
        if (sPrefs == null) {
            sPrefs = new Prefs(context.getSharedPreferences("ROBOGRID_PREFS", Context.MODE_PRIVATE));
        }
        return sPrefs;
    }

    private Prefs(SharedPreferences pref) {
        this.pref = pref;
    }


    public void saveLastGridSize(@NonNull Size size) {
        pref.edit()
                .putInt("PREF_LAST_GRID_HEIGHT", size.getHeight())
                .putInt("PREF_LAST_GRID_WIDTH", size.getWidth())
                .apply();
    }

    @Nullable
    public Size getLastGridSize() {
        int h = pref.getInt("PREF_LAST_GRID_HEIGHT", -1);
        int w = pref.getInt("PREF_LAST_GRID_WIDTH", -1);
        if (h < 0 || w < 0) {
            return null;
        }
        return new Size(h, w);
    }
}
