package com.hw.toucharcher;

import com.hw.toucharcher.helper.AppConfigure;
import com.hw.toucharcher.helper.UmengDataReportHelper;

import android.app.Application;
import android.content.Context;

public class ArcherApplication extends Application {

	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		AppConfigure.cApplication = this;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		UmengDataReportHelper.init();
	}
}
