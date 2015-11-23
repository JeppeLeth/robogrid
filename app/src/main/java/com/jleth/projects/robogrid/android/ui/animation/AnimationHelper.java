package com.jleth.projects.robogrid.android.ui.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

import com.jleth.projects.robogrid.android.R;

/**
 * Helper class for making specific animations in view classes
 */
public class AnimationHelper {

    private AnimationHelper() {
    }

    public static void startAnimateRevealIntroContent(final View root, final Animator.AnimatorListener listener) {
        final View startBtn = root.findViewById(R.id.startBtn);
        final View pick1 = root.findViewById(R.id.picker1Area);
        final View pick2 = root.findViewById(R.id.picker2Area);
        final View headline = root.findViewById(R.id.headline);
        final View image = root.findViewById(R.id.image);
        root.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                root.getViewTreeObserver().removeOnPreDrawListener(this);
                AnimatorSet animAll = createRevealAnimation(headline, image, startBtn, pick1, pick2);
                animAll.addListener(listener);
                animAll.start();
                return true;
            }
        });
        startBtn.setAlpha(0);
        pick1.setAlpha(0);
        pick2.setAlpha(0);
        image.setAlpha(0);
        image.setScaleX(1f);
        image.setScaleY(1f);
        headline.setAlpha(0);
    }

    public static void startAnimateRevealGridContent(final View root, final Animator.AnimatorListener listener) {
        final View btnPanel = root.findViewById(R.id.buttonPanel);
        final View headline = root.findViewById(R.id.headline);
        final View recyclerView = root.findViewById(R.id.recyclerView);
        final View locationTxt = root.findViewById(R.id.locationTxt);
        root.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                root.getViewTreeObserver().removeOnPreDrawListener(this);
                AnimatorSet animAll = createRevealAnimation(headline, btnPanel, recyclerView, locationTxt);
                animAll.addListener(listener);
                animAll.start();
                return true;
            }
        });
        btnPanel.setAlpha(0);
        headline.setAlpha(0);
        recyclerView.setAlpha(0);
        locationTxt.setAlpha(0);
    }

    @NonNull
    private static AnimatorSet createRevealAnimation(View headline, View btnPanel, View recyclerView, View locationTxt) {
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

        Animator anim2_1 = ObjectAnimator.ofFloat(btnPanel, View.ALPHA, 0, 1);
        Animator anim2_2 = ObjectAnimator.ofFloat(btnPanel, View.TRANSLATION_Y, btnPanel.getHeight() / 4, 0);
        setBatchTiming(800, 0, anim2_1, anim2_2);
        animAll
                .play(anim2_1)
                .with(anim2_2)
                .after(anim1_1);

        Animator anim3_1 = ObjectAnimator.ofFloat(recyclerView, View.ALPHA, 0, 1);
        Animator anim3_2 = ObjectAnimator.ofFloat(locationTxt, View.ALPHA, 0, 1);
        setBatchTiming(1800, 0, anim3_1, anim3_2);
        animAll.play(anim3_1)
                .with(anim3_2)
                .after(anim1_1);
        return animAll;
    }

    public static Animator createShakeAnimation(View target) {
        return ObjectAnimator.ofFloat(target, View.TRANSLATION_X, 0, 25, -25, 25, -25, 15, -15, 6, -6, 0);
    }

    public static void startAnimateDisappearGridContent(View root, Animator.AnimatorListener listener) {
        final View btnPanel = root.findViewById(R.id.buttonPanel);
        final View headline = root.findViewById(R.id.headline);
        final View recyclerView = root.findViewById(R.id.recyclerView);
        final View locationTxt = root.findViewById(R.id.locationTxt);
        AnimatorSet animAll = createDisappearAnim(btnPanel, recyclerView, locationTxt, headline);

        animAll.addListener(listener);

        animAll.start();
    }

    public static void startAnimateDisappearIntroContent(View root, Animator.AnimatorListener listener) {
        View startBtn = root.findViewById(R.id.startBtn);
        View pick1 = root.findViewById(R.id.picker1Area);
        View pick2 = root.findViewById(R.id.picker2Area);
        View headline = root.findViewById(R.id.headline);
        View image = root.findViewById(R.id.image);
        AnimatorSet animAll = createDisappearAnim(startBtn, pick1, pick2, image, headline);

        animAll.addListener(listener);

        animAll.start();
    }

    @NonNull
    private static AnimatorSet createDisappearAnim(View btnPanel, View recyclerView, View locationTxt, View headline) {
        AnimatorSet animAll = new AnimatorSet();

        Animator anim1_1 = ObjectAnimator.ofFloat(headline, View.ALPHA, 1, 0);
        Animator anim1_2 = ObjectAnimator.ofFloat(btnPanel, View.ALPHA, 1, 0);
        Animator anim1_3 = ObjectAnimator.ofFloat(btnPanel, View.ALPHA, 1, 0);
        Animator anim1_4 = ObjectAnimator.ofFloat(recyclerView, View.ALPHA, 1, 0);
        setBatchTiming(400, 0, anim1_1, anim1_2, anim1_3, anim1_4, anim1_4);
        animAll.play(anim1_1)
                .with(anim1_2)
                .with(anim1_3)
                .with(anim1_4);


        Interpolator interpolator2 = new DecelerateInterpolator();
        Animator anim2_1 = ObjectAnimator.ofFloat(locationTxt, View.ALPHA, 1, 0);
        Animator anim2_2 = ObjectAnimator.ofFloat(locationTxt, View.SCALE_X, 1f, 2f);
        Animator anim2_3 = ObjectAnimator.ofFloat(locationTxt, View.SCALE_Y, 1f, 2f);
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
    private static AnimatorSet createDisappearAnim(View startBtn, View pick1, View pick2, View image, View headline) {
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
    private static AnimatorSet createRevealAnimation(View headline, View image, View startBtn, View pick1, View pick2) {
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

    private static void setBatchTiming(long millis, long startDelay, Animator... anims) {
        for (Animator a : anims) {
            a.setDuration(millis);
            a.setStartDelay(startDelay);
        }
    }
}
