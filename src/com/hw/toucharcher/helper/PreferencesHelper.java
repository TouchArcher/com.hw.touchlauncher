package com.hw.toucharcher.helper;

import android.content.Context;
import android.content.SharedPreferences;


public class PreferencesHelper {
	public interface KeyList {
		public final String IsFirstRun = "isFirstRun";
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
}