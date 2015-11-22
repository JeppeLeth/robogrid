package com.jleth.projects.robogrid.android.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
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
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;

import com.jleth.projects.robogrid.android.R;
import com.jleth.projects.robogrid.android.model.Size;

/**
 * Page to display two numbers pickers and a button
 */
public class IntroPage extends RelativeLayout {

    private Listener mListener;
    private Animator mShake;
    private View mImage;
    private boolean blockTouch;
    private View mStartBtn;
    private View mPick1;
    private View mPick2;
    private View mHeadline;
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

        mStartBtn = findViewById(R.id.startBtn);
        mPick1 = findViewById(R.id.picker1Area);
        mPick2 = findViewById(R.id.picker2Area);
        mHeadline = findViewById(R.id.headline);
        mImage = findViewById(R.id.image);

        mShake = ObjectAnimator.ofFloat(mImage, View.TRANSLATION_X, 0, 25, -25, 25, -25, 15, -15, 6, -6, 0);

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
        getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                getViewTreeObserver().removeOnPreDrawListener(this);
                AnimatorSet animAll = createRevealAnimation(mHeadline, mImage, mStartBtn, mPick1, mPick2);
                animAll.addListener(new AnimatorListenerAdapter() {
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
                animAll.start();
                return true;
            }
        });
        mStartBtn.setAlpha(0);
        mPick1.setAlpha(0);
        mPick2.setAlpha(0);
        mImage.setAlpha(0);
        mImage.setScaleX(1f);
        mImage.setScaleY(1f);
        mHeadline.setAlpha(0);
    }

    public void animateDisappearContent(@Nullable final Runnable endListener) {
        AnimatorSet animAll = createDisappearAnim(mStartBtn, mPick1, mPick2, mImage, mHeadline);

        animAll.addListener(new AnimatorListenerAdapter() {

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

        animAll.start();
    }

    @NonNull
    private AnimatorSet createDisappearAnim(View startBtn, View pick1, View pick2, View image, View headline) {
        AnimatorSet animAll = new AnimatorSet();

        Animator anim1_1 = ObjectAnimator.ofFloat(headline, View.ALPHA, 1, 0);
        Animator anim1_2 = ObjectAnimator.ofFloat(startBtn, View.ALPHA, 1, 0);
        Animator anim1_3 = ObjectAnimator.ofFloat(startBtn, View.ALPHA, 1, 0);
        Animator anim1_4 = ObjectAnimator.ofFloat(pick1, View.ALPHA, 1, 0);
        Animator anim1_5 = ObjectAnimator.ofFloat(pick2, View.ALPHA, 1, 0);
        setBatchTiming(400, 0, anim1_1, anim1_2, anim1_3, anim1_4, anim1_5);
        animAll.play(anim1_1)
                .with(anim1_2)
                .with(anim1_3)
                .with(anim1_4)
                .with(anim1_5);


        Interpolator interpolator2 = new DecelerateInterpolator();
        Animator anim2_1 = ObjectAnimator.ofFloat(image, View.ALPHA, 1, 0);
        Animator anim2_2 = ObjectAnimator.ofFloat(image, View.SCALE_X, 1f, 2f);
        Animator anim2_3 = ObjectAnimator.ofFloat(image, View.SCALE_Y, 1f, 2f);
        anim2_1.setInterpolator(interpolator2);
        anim2_2.setInterpolator(interpolator2);
        anim2_3.setInterpolator(interpolator2);
        setBatchTiming(800, 0, anim2_1, anim2_2, anim2_3);
        animAll
                .play(anim2_1)
                .with(anim2_2)
                .with(anim2_3)
                .after(anim1_1);
        return animAll;
    }

    @NonNull
    private AnimatorSet createRevealAnimation(View headline, View image, View startBtn, View pick1, View pick2) {
        AnimatorSet animAll = new AnimatorSet();

        Animator anim1_1 = ObjectAnimator.ofFloat(headline, View.ALPHA, 0, 1, 1);
        Animator anim1_2 = ObjectAnimator.ofFloat(headline, View.TRANSLATION_Y, -headline.getBottom(), 60, 0);
        Animator anim1_3 = ObjectAnimator.ofFloat(headline, View.SCALE_X, 0.1f, 0.475f, 1);
        Animator anim1_4 = ObjectAnimator.ofFloat(headline, View.SCALE_Y, 0.1f, 0.475f, 1);
        setBatchTiming(1000, 0, anim1_1, anim1_2, anim1_3, anim1_4);
        animAll
                .play(anim1_1)
                .with(anim1_2)
                .with(anim1_3)
                .with(anim1_4);

        Animator anim2_1 = ObjectAnimator.ofFloat(image, View.ALPHA, 0, 1);
        Animator anim2_2 = ObjectAnimator.ofFloat(image, View.TRANSLATION_Y, image.getHeight() / 4, 0);
        setBatchTiming(800, 0, anim2_1, anim2_2);
        animAll
                .play(anim2_1)
                .with(anim2_2)
                .after(anim1_1);

        Animator anim3_1 = ObjectAnimator.ofFloat(startBtn, View.ALPHA, 0, 1);
        Animator anim3_2 = ObjectAnimator.ofFloat(startBtn, View.ALPHA, 0, 1);
        Animator anim3_3 = ObjectAnimator.ofFloat(pick1, View.ALPHA, 0, 1);
        Animator anim3_4 = ObjectAnimator.ofFloat(pick2, View.ALPHA, 0, 1);
        setBatchTiming(1800, 0, anim3_1, anim3_2, anim3_3, anim3_4);
        animAll.play(anim3_1)
                .with(anim3_2)
                .with(anim3_3)
                .with(anim3_4)
                .after(anim1_1);
        return animAll;
    }

    private void setBatchTiming(long millis, long startDelay, Animator... anims) {
        for (Animator a : anims) {
            a.setDuration(millis);
            a.setStartDelay(startDelay);
        }
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
