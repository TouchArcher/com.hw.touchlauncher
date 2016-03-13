package com.hw.toucharcher.helper;

import android.util.Log;

public class LogHelper {
	private static final String TAG = "ArcherLog";

	public static boolean isDebug = true;

	public static void d(String msg) {
		if (isDebug) {
			Log.d(TAG, msg);
		}
	}

	public static void d(String tag, String msg) {
		if (isDebug) {
			Log.d(tag, msg);
		}
	}

	public static void e(String msg) {
		if (isDebug) {
			Log.e(TAG, msg);
		}
	}

	public static void e(String tag, String msg) {
		if (isDebug) {
			Log.e(tag, msg);
		}
	}

	public static void v(String tag, String msg) {
		if (isDebug) {
			Log.v(tag, msg);
		}
	}

	public static void e(String tag, String msg, Exception ex) {
		if (isDebug) {
			Log.e(tag, msg, ex);
		}
	}

	public static void w(String tag, String msg) {
		if (isDebug) {
			Log.w(tag, msg);
		}
	}

	public static void w(String tag, String msg, Exception ex) {
		if (isDebug) {
			Log.w(tag, msg, ex);
		}
	}

	public static void w(String tag, Exception ex) {
		if (isDebug) {
			Log.w(tag, ex);
		}
	}

	public static void i(String tag, String msg) {
		if (isDebug) {
			Log.i(tag, msg);
		}
	}
}
