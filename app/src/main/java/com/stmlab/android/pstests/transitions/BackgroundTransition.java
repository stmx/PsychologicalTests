package com.stmlab.android.pstests.transitions;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;

public class BackgroundTransition extends Transition {
    private static final String PROPNAME_BACKGROUND =
            "com.example.android.customtransition:BackgroundTransition:background";
    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }
    private void captureValues(TransitionValues transitionValues) {
        // Get a reference to the view
        View view = transitionValues.view;
        // Store its background property in the values map
        transitionValues.values.put(PROPNAME_BACKGROUND, view.getBackground());
    }

    @Override
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
        if (null == startValues || null == endValues) {
            return null;
        }
        final View view = endValues.view;

        Drawable startBackground =
                (Drawable) startValues.values.get(PROPNAME_BACKGROUND);
        Drawable endBackground =
                (Drawable) endValues.values.get(PROPNAME_BACKGROUND);

        ColorDrawable startColor = (ColorDrawable) startBackground;
        ColorDrawable endColor = (ColorDrawable) endBackground;

        if (startColor.getColor() == endColor.getColor()) {
            return null;
        }
        ValueAnimator animator = ValueAnimator.ofObject(new ArgbEvaluator(),
                startColor.getColor(), endColor.getColor());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        Object value = animation.getAnimatedValue();
                        if (null != value) {
                            view.setBackgroundColor((Integer) value);
                        }
                    }
                });
        return animator;
    }
}
