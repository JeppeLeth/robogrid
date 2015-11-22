package com.jleth.projects.robogrid.android.data.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.jleth.projects.robogrid.android.R;
import com.jleth.projects.robogrid.android.model.Direction;
import com.jleth.projects.robogrid.android.model.Location;

public class GridViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private static Drawable sSelected;
    private static int sSelectedColor = -1;

    public GridViewHolder(View itemView) {
        super(itemView); // Must call super() first
        imageView = (ImageView) itemView;
    }

    public void setSelected(boolean selected, Location loc) {
        if (selected) {
            if (loc.getDirection() == Direction.UNKNOWN) {
                imageView.setBackgroundColor(obtainColorFromTheme(imageView.getContext(), R.attr.colorAccent));
                imageView.setImageDrawable(null);
            } else {
                imageView.setRotation(loc.getDirection() * 90);
                imageView.setBackgroundColor(Color.TRANSPARENT);
                imageView.setImageDrawable(getSelectedDrawable());
            }
        } else {
            imageView.setImageDrawable(null);
            imageView.setRotation(0);
            imageView.setBackgroundColor(Color.DKGRAY);
        }
    }

    private Drawable getSelectedDrawable() {
        if (sSelected == null) {
            Drawable d = ContextCompat.getDrawable(imageView.getContext(), R.drawable.robot_sphere_face_skin_51);
            d.setColorFilter(obtainColorFromTheme(imageView.getContext(), R.attr.colorAccent), PorterDuff.Mode.SRC_IN);
            sSelected = d;
        }
        return sSelected;
    }

    @ColorInt
    private int getSelectedColor() {
        if (sSelectedColor == -1) {
            sSelectedColor = obtainColorFromTheme(imageView.getContext(), R.attr.colorPrimary);
        }
        return sSelectedColor;
    }


    @ColorInt
    public static int obtainColorFromTheme(Context context, @AttrRes int attrRes) {
        Resources.Theme theme = context.getTheme();
        TypedArray ta = theme.obtainStyledAttributes(new int[]{attrRes});
        int color = 0;
        try {
            color = ta.getColor(0, color);
        } finally {
            ta.recycle();
        }
        return color;
    }
}