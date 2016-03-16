package com.hw.toucharcher.base;

import java.util.ArrayList;

import com.hw.toucharcher.R;

import android.os.Bundle;

public class LifecycleActivity extends UmengAnalyticsActivity {
	public interface LifecycleCallback {
		public void run(LifecycleActivity a);
	}
	
	private ArrayList<LifecycleCallback> mLifecycleCallback;

	public void setLifecycleCallback(LifecycleCallback c) {
		if (mLifecycleCallback == null) {
			mLifecycleCallback = new ArrayList<LifecycleCallback>();
		}

		mLifecycleCallback.add(c);
	}

	@Override
	protected void onDestroy() {
		if (mLifecycleCallback != null) {
			for (LifecycleCallback r : mLifecycleCallback) {
				r.run(this);
			}
		}
		super.onDestroy();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// 添加淡入动画
		overridePendingTransition(R.anim.pop_in, R.anim.none_anim);
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void finish() {
		// 添加淡出动画
		overridePendingTransition(R.anim.none_anim, R.anim.pop_out);
		super.finish();
	}
	
}
