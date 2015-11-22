package com.jleth.projects.robogrid.android.ui.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

public class DefaultButton extends AppCompatButton {
	public DefaultButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public DefaultButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public DefaultButton(Context context) {
		super(context);
		init();
	}

	private void init() {
	}
}
