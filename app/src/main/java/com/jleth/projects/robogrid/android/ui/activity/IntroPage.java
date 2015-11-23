package com.jleth.projects.robogrid.android.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;

import com.jleth.projects.robogrid.android.R;
import com.jleth.projects.robogrid.android.model.Size;
import com.jleth.projects.robogrid.android.ui.animation.AnimationHelper;

/**
 * Page to display two numbers pickers and a button
 */
public class IntroPage extends RelativeLayout {

    private Listener mListener;
    private Animator mShake;
    private View mImage;
    private boolean blockTouch;
    private NumberPicker mColsPicker;
    private NumberPicker mRowsPicker;

    public interface Listener {
        void onImageClick();

        void onStartClick(@NonNull Size size);
    }

    public IntroPage(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public IntroPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    public IntroPage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public IntroPage(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        LayoutInflater.from(getContext()).inflate(R.layout.page_intro, this, true);

        findViewById(R.id.startBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Listener listener = mListener;
                if (listener != null) {
                    listener.onStartClick(new Size(mRowsPicker.getValue(), mColsPicker.getValue()));
                }
            }
        });

        mRowsPicker = (NumberPicker) findViewById(R.id.rowsPicker);
        mColsPicker = (NumberPicker) findViewById(R.id.colsPicker);

        mRowsPicker.setMinValue(1);
        mRowsPicker.setMaxValue(15);
        mColsPicker.setMinValue(1);
        mColsPicker.setMaxValue(15);

        mImage = findViewById(R.id.image);

        mShake = AnimationHelper.createShakeAnimation(mImage);

        mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Listener listener = mListener;
                if (listener != null) {
                    listener.onImageClick();
                }
            }
        });
    }

    public void setInitialSelection(@Nullable Size initialSelection) {
        NumberPicker rowsPicker = (NumberPicker) findViewById(R.id.rowsPicker);
        NumberPicker colsPicker = (NumberPicker) findViewById(R.id.colsPicker);
        if (initialSelection == null) {
            rowsPicker.setValue(5);
            colsPicker.setValue(5);
        } else {
            rowsPicker.setValue(initialSelection.getHeight());
            colsPicker.setValue(initialSelection.getWidth());
        }

    }

    public void animateRevealContent(@Nullable final Runnable endListener) {
        AnimationHelper.startAnimateRevealIntroContent(this, new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                blockTouch = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                blockTouch = false;
                if (endListener != null) {
                    endListener.run();
                }
            }
        });
    }

    public void animateDisappearContent(@Nullable final Runnable endListener) {
        AnimationHelper.startAnimateDisappearIntroContent(this, new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                blockTouch = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                blockTouch = false;
                if (endListener != null) {
                    endListener.run();
                }
            }
        });
    }

    public void animateAttentionImage() {
        mImage.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        mShake.start();
    }

    public IntroPage setListener(Listener listener) {
        this.mListener = listener;
        return this;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return blockTouch || super.onInterceptTouchEvent(ev);
    }
}
