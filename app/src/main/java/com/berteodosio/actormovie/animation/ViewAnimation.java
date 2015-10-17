package com.berteodosio.actormovie.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

/**
 * Created by Bernardo do Amaral Teodosio on 03/10/15.
 */
public class ViewAnimation {

    public static void doContractHorizontalAnimation(View view) {
        final int DURATION = 250;
        final ObjectAnimator animatorX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0f).setDuration(DURATION);
        final ObjectAnimator animatorXBack = ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f).setDuration(DURATION);

        animatorX.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animatorXBack.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        AnimatorSet set = new AnimatorSet();

        set.play(animatorX);
        set.start();
    }

    public static void doLittleBigAnimation(View view) {
        final int DURATION = 250;
        final ObjectAnimator animatorX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0f).setDuration(DURATION);
        final ObjectAnimator animatorXBack = ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f).setDuration(DURATION);
        final ObjectAnimator animatorY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0f).setDuration(DURATION);
        final ObjectAnimator animatorYBack = ObjectAnimator.ofFloat(view, "scaleY", 0f, 1f).setDuration(DURATION);

        animatorX.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animatorXBack.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        animatorY.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animatorYBack.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        AnimatorSet set = new AnimatorSet();

        set.playTogether(animatorX, animatorY);
        set.start();
    }

    public static void doShowWithAlphaAnimation(View view) {
        final int DURATION = 250;
        final ObjectAnimator animatorX = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f).setDuration(DURATION);

        animatorX.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        AnimatorSet set = new AnimatorSet();

        set.play(animatorX);
        set.start();
    }
}
