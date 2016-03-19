package com.hw.toucharcher.activity;

import com.hw.toucharcher.helper.AppHelper;

import android.app.Activity;
import android.os.Bundle;

public class ResolveActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AppHelper.clearDefaultDesktop(this);
		AppHelper.openDesktopSettings();
	}
}
