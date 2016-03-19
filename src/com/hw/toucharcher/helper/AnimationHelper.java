package com.hw.toucharcher.helper;

import com.hw.toucharcher.R;

import android.app.Activity;

public class AnimationHelper {
	public static void activityPopIn(Activity a) {
		a.overridePendingTransition(R.anim.pop_in, R.anim.none_anim);
	}

	public static void activityPopOut(Activity a) {
		a.overridePendingTransition(R.anim.none_anim, R.anim.pop_out);
	}
}
