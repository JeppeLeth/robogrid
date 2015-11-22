package com.jleth.projects.robogrid.android.ui.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public class SquareView extends AppCompatImageView {
	public SquareView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public SquareView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public SquareView(Context context) {
		super(context);
		init();
	}

	private void init() {
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);

		int size;
		if(widthMode == MeasureSpec.EXACTLY && widthSize > 0){
			size = widthSize;
		}
		else if(heightMode == MeasureSpec.EXACTLY && heightSize > 0){
			size = heightSize;
		}
		else{
			size = widthSize < heightSize ? widthSize : heightSize;
		}


		int finalMeasureSpec = MeasureSpec.makeMeasureSpec(size, MeasureSpec.EXACTLY);
		super.onMeasure(finalMeasureSpec, finalMeasureSpec);
	}
}
