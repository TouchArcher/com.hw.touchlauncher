package com.hw.toucharcher.activity;

import java.util.ArrayList;

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
}
