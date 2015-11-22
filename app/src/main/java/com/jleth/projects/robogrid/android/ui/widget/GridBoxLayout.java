package com.jleth.projects.robogrid.android.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * ViewGroup that draws square grids and places children inside one of the squared
 */
public class GridBoxLayout extends ViewGroup {
    private int mColumnCount;
    private int mRowCount;

    public GridBoxLayout(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public GridBoxLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    public GridBoxLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public GridBoxLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize, heightSize;

        //Get the width based on the measure specs
        widthSize = getDefaultSize(0, widthMeasureSpec);

        //Get the height based on measure specs
        heightSize = getDefaultSize(0, heightMeasureSpec);

        int majorDimension = Math.min(widthSize, heightSize);
        //Measure all child views
        int blockDimension = majorDimension / mColumnCount;
        int blockSpec = MeasureSpec.makeMeasureSpec(blockDimension,
                MeasureSpec.EXACTLY);
        measureChildren(blockSpec, blockSpec);

        //MUST call this to save our own dimensions
        setMeasuredDimension(majorDimension, majorDimension);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        //Let the framework do its thing
        super.dispatchDraw(canvas);

//        //Draw the grid lines
//        for (int i = 0; i <= getWidth(); i += (getWidth() / mColumnCount)) {
//            canvas.drawLine(i, 0, i, getHeight(), mGridPaint);
//        }
//        for (int i = 0; i <= getHeight(); i += (getHeight() / mColumnCount)) {
//            canvas.drawLine(0, i, getWidth(), i, mGridPaint);
//        }
    }
}
