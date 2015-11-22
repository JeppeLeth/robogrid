package com.jleth.projects.robogrid.android.data.util;

import android.content.Context;

import com.jleth.projects.robogrid.android.R;
import com.jleth.projects.robogrid.android.model.Direction;

/**
 * Static class for language and translation based operations
 */
public class LocalizationUtil {

    private LocalizationUtil() {
    }

    /**
     * Get the human readable word for a {@link Direction}
     *
     * @param context   context
     * @param direction direction constant
     * @return example 'North'
     */
    public static String convertDirection(Context context, @Direction.Constant int direction) {
        switch (direction) {
            case Direction.NORTH:
                return context.getResources().getString(R.string.direction_north);
            case Direction.SOUTH:
                return context.getResources().getString(R.string.direction_south);
            case Direction.EAST:
                return context.getResources().getString(R.string.direction_east);
            case Direction.WEST:
                return context.getResources().getString(R.string.direction_west);
        }
        return context.getResources().getString(R.string.direction_unknown);
    }
}
