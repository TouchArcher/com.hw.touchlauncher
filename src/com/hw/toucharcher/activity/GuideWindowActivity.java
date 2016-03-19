package com.hw.toucharcher.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.hw.toucharcher.R;
import com.hw.toucharcher.helper.AnimationHelper;

public class GuideWindowActivity extends LifecycleActivity {
	public final static int DelayMillis = 500;

	@Override
	public void finish() {
		AnimationHelper.activityPopOut(this);
		super.finish();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AnimationHelper.activityPopIn(this);
		setContentView(R.layout.layout_guide_window);
		findViewById(R.id.btn_guide_confirm).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}
