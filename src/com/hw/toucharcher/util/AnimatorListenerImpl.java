package com.hw.toucharcher.util;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.Animator.AnimatorListener;

public class AnimatorListenerImpl implements AnimatorListener {

	@Override
	public void onAnimationCancel(Animator arg0) {
		onAnimationEnd(arg0);
	}

	@Override
	public void onAnimationEnd(Animator arg0) {
	}

	@Override
	public void onAnimationRepeat(Animator arg0) {
	}

	@Override
	public void onAnimationStart(Animator arg0) {
	}

}
