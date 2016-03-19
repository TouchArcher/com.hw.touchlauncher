package com.hw.toucharcher.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import com.hw.toucharcher.R;
import com.hw.toucharcher.helper.AppConfigure;
import com.hw.toucharcher.helper.AppHelper;
import com.hw.toucharcher.operator.ArcherOperator;
import com.hw.toucharcher.operator.ArcherOperator.IOperator;

public class ArcherActivity extends LifecycleActivity implements IOperator {
	private ArcherOperator mOperator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mOperator = new ArcherOperator(this);
		AppHelper.setTranslucentStatus(this, true);
	}

	@Override
	public void onCreateContentView(int layoutID, View v, LayoutParams p) {
		if (v == null) {
			setContentView(layoutID);
		} else {
			if (p == null) {
				setContentView(v);
			} else {
				setContentView(v, p);
			}
		}
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.id_open_desktop:
			AppHelper.clearDefaultDesktop(this);
			AppHelper.openDesktopSettings();
			break;
		case R.id.id_open_development:
			AppHelper.openDevelopmentSettings();

			v.postDelayed(new Runnable() {

				@Override
				public void run() {
					Intent dialog = new Intent(AppConfigure.cApplication, GuideWindowActivity.class);
					dialog.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(dialog);
				}
			}, GuideWindowActivity.DelayMillis);
			break;
		case R.id.id_open_apppermissions:
			AppHelper.openAppPermissionsSetting();

			v.postDelayed(new Runnable() {

				@Override
				public void run() {
					Intent dialog = new Intent(AppConfigure.cApplication, GuideWindowActivity.class);
					dialog.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(dialog);
				}
			}, GuideWindowActivity.DelayMillis);
			break;
		}

	}
}
