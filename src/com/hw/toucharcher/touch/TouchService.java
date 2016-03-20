package com.hw.toucharcher.touch;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.view.View;

import com.hw.toucharcher.helper.AppHelper;
import com.hw.toucharcher.touch.TouchModel.IModel;
import com.hw.toucharcher.touch.TouchOperator.IOperator;

public class TouchService extends Service implements IOperator, IModel {

	private TouchOperator mOperator;
	private TouchModel mModel;

	@Override
	public void onCreate() {
		super.onCreate();
		mOperator = new TouchOperator(this);
		mModel = new TouchModel(this);

		init();
	}

	private void init() {
		if (!AppHelper.hasSettingFloatPermissions()) {
			mOperator.openFloat(mModel.getFloatPoint());
		}
	}

	@Override
	public void onDestroy() {
		mModel.destory();
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onTouchMove(int x, int y) {
		mOperator.updateFloat(mModel.saveFloatPoint(x, y));
	}

	@Override
	public void onClick(View v) {
		mOperator.openMenu();
	}

}
