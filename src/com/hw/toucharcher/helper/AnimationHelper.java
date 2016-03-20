package com.hw.toucharcher.helper;

import android.app.Activity;
import android.view.View;

import com.hw.toucharcher.R;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.PropertyValuesHolder;

public class AnimationHelper {
	public static void activityPopIn(Activity a) {
		a.overridePendingTransition(R.anim.pop_in, R.anim.none_anim);
	}

	public static void activityPopOut(Activity a) {
		a.overridePendingTransition(R.anim.none_anim, R.anim.pop_out);
	}

	public static void floatMenuOpenAnim(View v) {
		ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(v, PropertyValuesHolder.ofFloat("scaleX", 0.8f, 1f),
				PropertyValuesHolder.ofFloat("scaleY", 0.8f, 1f), PropertyValuesHolder.ofFloat("alpha", 0f, 1f));
		animator.setDuration(100);
		animator.start();
	}

	public static void floatMenuCloseAnim(View v, AnimatorListener listener) {
		ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(v, PropertyValuesHolder.ofFloat("scaleX", 1f, 0.8f),
				PropertyValuesHolder.ofFloat("scaleY", 1f, 0.8f), PropertyValuesHolder.ofFloat("alpha", 1f, 0f));
		animator.addListener(listener);
		animator.setDuration(100);
		animator.start();
	}
}
