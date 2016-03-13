package com.hw.toucharcher.helper;

import java.io.File;
import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Application;
import android.app.KeyguardManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Rect;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

public class AppHelper {
	public static boolean isMainProcess() {
		if (AppConfigure.isMainProcess != null) {
			return AppConfigure.isMainProcess;
		}

		String processName = "";
		int pid = android.os.Process.myPid();
		ActivityManager am = (ActivityManager) AppConfigure.cApplication.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningAppProcessInfo> infos = am.getRunningAppProcesses();
		if (infos != null && infos.size() > 0) {
			for (RunningAppProcessInfo appProcess : infos) {
				if (appProcess.pid == pid) {
					processName = appProcess.processName;
				}
			}
		}

		AppConfigure.isMainProcess = AppConfigure.PackageName.equals(processName);
		return AppConfigure.isMainProcess;
	}

	/**
	 * 获取当前版本号
	 * 
	 * @return versionCode
	 */
	public static int getCurVersion() {
		if (AppConfigure.cVersionCode != null) {
			return AppConfigure.cVersionCode;
		}

		try {
			PackageInfo pinfo;
			pinfo = AppConfigure.cApplication.getPackageManager().getPackageInfo(AppConfigure.PackageName, PackageManager.GET_CONFIGURATIONS);
			AppConfigure.cVersionCode = pinfo.versionCode;
			AppConfigure.cVersionName = pinfo.versionName;

			return AppConfigure.cVersionCode;
		} catch (NameNotFoundException e) {
			// ignore
			return -1;
		}
	}

	/**
	 * 获取当前版本号
	 * 
	 * @return versionCode
	 */
	public static String getCurVersionName() {
		if (AppConfigure.cVersionName != null) {
			return AppConfigure.cVersionName;
		}

		try {
			PackageInfo pinfo;
			pinfo = AppConfigure.cApplication.getPackageManager().getPackageInfo(AppConfigure.PackageName, PackageManager.GET_CONFIGURATIONS);
			AppConfigure.cVersionCode = pinfo.versionCode;
			AppConfigure.cVersionName = pinfo.versionName;

			return AppConfigure.cVersionName;
		} catch (NameNotFoundException e) {
			// ignore
			return "";
		}
	}

	/**
	 * 应用是否被隐藏<在后台运行>
	 * 
	 * @return
	 */
	public static boolean IsRunOnBackground() {
		ActivityManager activityManager = (ActivityManager) AppConfigure.cApplication.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> tasksInfo = activityManager.getRunningTasks(1);
		return AppConfigure.PackageName.equals(tasksInfo.get(0).topActivity.getPackageName());
	}

	/**
	 * 判断是否锁屏
	 * 
	 * @return true 锁屏状态， false 非锁屏状态
	 */
	public static boolean isScreenOff() {
		KeyguardManager mKeyguardManager = (KeyguardManager) AppConfigure.cApplication.getSystemService(Activity.KEYGUARD_SERVICE);
		return mKeyguardManager.inKeyguardRestrictedInputMode();
	}

	/**
	 * 获取状态栏高度
	 * 
	 * @param a
	 * @return > 0 success; <= 0 fail
	 */
	public static int getStatusHeight(Activity a) {
		if (AppConfigure.cStatusHeight != null) {
			return AppConfigure.cStatusHeight;
		}
		Window window = a.getWindow();

		int statusHeight = 0;
		LayoutParams attributes = window.getAttributes();
		if ((attributes.flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) == WindowManager.LayoutParams.FLAG_FULLSCREEN) {
			Rect localRect = new Rect();
			window.getDecorView().getWindowVisibleDisplayFrame(localRect);
			statusHeight = localRect.top;
		}

		if (0 == statusHeight) {
			Class<?> localClass;
			try {
				localClass = Class.forName("com.android.internal.R$dimen");
				Object localObject = localClass.newInstance();
				int id = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
				statusHeight = a.getResources().getDimensionPixelSize(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (statusHeight > 0) {
			AppConfigure.cStatusHeight = statusHeight;
		}
		return statusHeight;
	}

	/**
	 * IMEI
	 * 
	 * @return
	 */
	public static String getIMEI() {
		if (AppConfigure.cIMEI == null) {
			AppConfigure.cIMEI = ((TelephonyManager) AppConfigure.cApplication.getSystemService(Application.TELEPHONY_SERVICE)).getDeviceId();
		}
		return AppConfigure.cIMEI;
	}

	/**
	 * mac
	 * 
	 * @return
	 */
	public static String getMacAddress() {
		if (AppConfigure.cMac == null) {
			AppConfigure.cMac = ((WifiManager) AppConfigure.cApplication.getSystemService(Context.WIFI_SERVICE)).getConnectionInfo().getMacAddress();
		}
		return AppConfigure.cMac;
	}

	/**
	 * 获取屏幕宽度
	 * 
	 * @return
	 */
	public static int getScreenWidth() {
		if (AppConfigure.cScreenWidth == null) {
			WindowManager wm = (WindowManager) AppConfigure.cApplication.getSystemService(Context.WINDOW_SERVICE);
			DisplayMetrics dm = new DisplayMetrics();
			wm.getDefaultDisplay().getMetrics(dm);
			AppConfigure.cScreenWidth = dm.widthPixels;
			AppConfigure.cScreenHeight = dm.heightPixels;
		}

		return AppConfigure.cScreenWidth;
	}

	/**
	 * 获取屏幕高度
	 * 
	 * @return
	 */
	public static int getScreenHeight() {
		if (AppConfigure.cScreenHeight == null) {
			WindowManager wm = (WindowManager) AppConfigure.cApplication.getSystemService(Context.WINDOW_SERVICE);
			DisplayMetrics dm = new DisplayMetrics();
			wm.getDefaultDisplay().getMetrics(dm);
			AppConfigure.cScreenWidth = dm.widthPixels;
			AppConfigure.cScreenHeight = dm.heightPixels;
		}

		return AppConfigure.cScreenHeight;
	}

	/**
	 * 判断手机是否ROOT
	 */
	public static boolean isRoot() {
		boolean root = false;
		try {
			if (new File("/system/bin/su").exists() || new File("/system/xbin/su").exists()) {
				root = true;
			}
		} catch (Exception e) {
		}
		return root;
	}

	/**
	 * 监测是否开启悬浮菜单
	 * 
	 * @return
	 */
	public static boolean isNotificationEnabled() {
		String ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";
		final String flat = Settings.Secure.getString(AppConfigure.cApplication.getContentResolver(), ENABLED_NOTIFICATION_LISTENERS);
		if (flat == null || flat.length() == 0) {
			final String[] cmns = flat.split(":");
			for (int i = 0; i < cmns.length; i++) {
				final ComponentName cn = ComponentName.unflattenFromString(cmns[i]);
				if (cn == null) {
					continue;
				}
				if (AppConfigure.PackageName.equals(cn.getPackageName())) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 获取对应屏幕的DP尺寸
	 * 
	 * @param value
	 * @return
	 */
	public static float getUnitDIP(float px) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, AppConfigure.cApplication.getResources().getDisplayMetrics());
	}

	/**
	 * 设置Activity亮度
	 * 
	 * @param tmpInt
	 */
	public static void setActivityBrightness(Activity a, int screenBrightness) {
		Window window = a.getWindow();
		WindowManager.LayoutParams lp = window.getAttributes();
		if (10 <= screenBrightness && screenBrightness <= 255) {
			lp.screenBrightness = screenBrightness / (float) 255;
		}
		window.setAttributes(lp);
	}

	/**
	 * 保存亮度值
	 * 
	 * @param cr
	 * @param mode
	 * @param brightness
	 * @return
	 */
	public static boolean saveBrightnessSetting(ContentResolver cr, int brightness) {
		boolean b = false;
		b = b || Settings.System.putInt(cr, Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
		b = b || Settings.System.putInt(cr, Settings.System.SCREEN_BRIGHTNESS, brightness);

		Uri uri = android.provider.Settings.System.getUriFor("screen_brightness");
		cr.notifyChange(uri, null);
		return b;
	}

	/**
	 * 判断触摸坐标是否在view外边
	 * 
	 * @param view
	 * @param ev
	 * @return
	 */
	public static boolean isOutSideView(View view, float x, float y) {
		int[] location = new int[2];
		view.getLocationOnScreen(location);
		int left = location[0];
		int top = location[1];
		return x < left || x > (left + view.getWidth()) || y < top || y > (top + view.getHeight());
	}
}
