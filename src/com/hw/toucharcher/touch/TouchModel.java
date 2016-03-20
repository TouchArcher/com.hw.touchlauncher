package com.hw.toucharcher.touch;

import java.lang.ref.WeakReference;

import android.content.Context;
import android.graphics.Point;
import android.os.Handler;

import com.hw.toucharcher.helper.AppConfigure;
import com.hw.toucharcher.helper.AppHelper;
import com.hw.toucharcher.helper.PreferencesHelper;

public class TouchModel implements Runnable {
	private final WeakReference<IModel> mRreference;
	private final Context mContext;
	private final Point mPoint;
	private final Handler mBgHandler;

	public interface IModel {
	}

	public TouchModel(IModel m) {
		mRreference = new WeakReference<IModel>(m);
		mContext = AppConfigure.cApplication;

		mPoint = PreferencesHelper.getLocalFloatPoint();

		mBgHandler = AppHelper.createBackgroundHandler(this);
	}

	public Point getFloatPoint() {
		return mPoint;
	}

	public Point saveFloatPoint(int x, int y) {
		mPoint.set(x, y);
		mBgHandler.removeCallbacks(this);
		mBgHandler.post(this);

		return mPoint;
	}

	@Override
	public void run() {
		PreferencesHelper.setLocalFloatPoint(mPoint);
	}

	public void destory() {
		mBgHandler.removeCallbacksAndMessages(null);
		mBgHandler.getLooper().quit();
	}
}
