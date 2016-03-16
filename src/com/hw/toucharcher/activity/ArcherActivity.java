package com.hw.toucharcher.activity;

import android.os.Bundle;

import com.hw.toucharcher.R;
import com.hw.toucharcher.base.LifecycleActivity;

public class ArcherActivity extends LifecycleActivity {
	private float ALPHA_MAX = 100;
	private float ALPHA_MIN = 20;
	private float ALPHA_NORMAL = 30;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.archer_float_setting);
		
	}
}
