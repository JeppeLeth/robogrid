package com.jleth.projects.robogrid.android.data.sound;

import android.util.SparseIntArray;

import com.jleth.projects.robogrid.android.R;


/**
 * Sound mappings for the App, mapping an app-wide int identifier with the appropriate
 * sound resource file
 */
public interface Sounds {

    int SOUND_ID_SHAKE = 0;
    int SOUND_ID_BUBBLE_2 = 1;
    int SOUND_ID_BUBBLE_3 = 2;
    int SOUND_ID_DIALOG = 3;
    int SOUND_ACCEPT = 4;
    int SOUND_DECLINE = 5;

    SparseIntArray SOUND_MAP = new SparseIntArray() {{
        put(SOUND_ID_SHAKE, R.raw.shake);
//        put(SOUND_ID_BUBBLE_1, R.raw.bubble_2);
//        put(SOUND_ID_BUBBLE_2, R.raw.bubble_1);
//        put(SOUND_ID_BUBBLE_3, R.raw.bubble_1);
    }};

}
