package com.jleth.projects.robogrid.android.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Outline;
import android.os.Build;
import android.support.v7.widget.AppCompatImageButton;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;

/**
 * Created by JEP on 11/21/2015.
 */
public class CircleImageButton extends AppCompatImageButton {


    public CircleImageButton(Context context) {
        super(context);
        init();
    }

    public CircleImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setOutlineProvider(new ViewOutlineProvider() {
                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void getOutline(View view, Outline outline) {
                    outline.setOval(view.getLeft(), view.getRight(), view.getTop(), view.getBottom());
                }
            });
        }
    }
}
