package com.jleth.projects.robogrid.android.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

public class NumberPickerNonEditable extends NumberPicker {
    public NumberPickerNonEditable(Context context) {
        super(context);
        init();
    }

    public NumberPickerNonEditable(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NumberPickerNonEditable(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public NumberPickerNonEditable(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    protected void init() {
        setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
    }


    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        super.addView(child, params);
    }
}
