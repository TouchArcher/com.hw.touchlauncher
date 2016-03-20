package com.hw.toucharcher.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;

public class PreferencesHelper {
	public interface KeyList {
		public final String IsFirstRun = "isFirstRun";
		public final String FloatX = "float_x";
		public final String FloatY = "float_y";
	}

	public final static SharedPreferences pMainPreferences;

	static {
		pMainPreferences = AppConfigure.cApplication.getSharedPreferences(AppConfigure.PackageName + "_config", Context.MODE_PRIVATE);
	}

	public static SharedPreferences createSharedPreferences(String name, int mode) {
		return AppConfigure.cApplication.getSharedPreferences(name, mode);
	}

	/**
	 * 是否是第一次运行
	 * 
	 * @return
	 */
	public static boolean isFirstRunLauncher() {
		if (AppConfigure.isFirstRun == null) {
			AppConfigure.isFirstRun = pMainPreferences.getBoolean(KeyList.IsFirstRun, false);
		}

		return AppConfigure.isFirstRun;
	}

	public static Point getLocalFloatPoint() {
		Point point = new Point();
		point.x = pMainPreferences.getInt(KeyList.FloatX, 0);
		point.y = pMainPreferences.getInt(KeyList.FloatY, (int) (AppHelper.getScreenHeight() * 0.5f));

		return point;
	}

	public static void setLocalFloatPoint(Point p) {
		pMainPreferences.edit().putInt(KeyList.FloatX, p.x).putInt(KeyList.FloatY, p.y).commit();
	}
}