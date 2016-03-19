package com.hw.toucharcher.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;

public class PhoneHelper {
	public enum PhoneBrands {
		UnKnow, SanXing, Nubia, ZhongXing, Meizu, ZTE, VIVO, OPPO, Coolpad, SonyEricsson, EmotionUI_3, EmotionUI, MIUI
	}

	private static void initHelper() {
		String manufacturer = Build.MANUFACTURER;
		if (manufacturer.equalsIgnoreCase(PhoneBrands.SanXing.name())) {
			AppConfigure.cPhoneBrands = PhoneBrands.SanXing;
		} else if (manufacturer.equalsIgnoreCase(PhoneBrands.Nubia.name())) {
			AppConfigure.cPhoneBrands = PhoneBrands.Nubia;
		} else if (manufacturer.equalsIgnoreCase(PhoneBrands.ZhongXing.name())) {
			AppConfigure.cPhoneBrands = PhoneBrands.ZhongXing;
		} else if (manufacturer.equalsIgnoreCase(PhoneBrands.Meizu.name())) {
			AppConfigure.cPhoneBrands = PhoneBrands.Meizu;
		} else if (manufacturer.equalsIgnoreCase(PhoneBrands.SonyEricsson.name())) {
			AppConfigure.cPhoneBrands = PhoneBrands.SonyEricsson;
		} else if (Build.BRAND.equalsIgnoreCase(PhoneBrands.VIVO.name())) {
			AppConfigure.cPhoneBrands = PhoneBrands.VIVO;
		} else if (Build.BRAND.equalsIgnoreCase(PhoneBrands.OPPO.name())) {
			AppConfigure.cPhoneBrands = PhoneBrands.OPPO;
		} else if (Build.BRAND.equalsIgnoreCase(PhoneBrands.ZTE.name())) {
			AppConfigure.cPhoneBrands = PhoneBrands.ZTE;
		} else if (Build.MODEL.equalsIgnoreCase(PhoneBrands.Coolpad.name())) {
			AppConfigure.cPhoneBrands = PhoneBrands.Coolpad;
		} else {
			String rom = getSystemProperty("ro.build.version.emui");
			if (rom != null && rom.length() > 0) {
				if (rom.contains("EmotionUI_3")) {
					AppConfigure.cPhoneBrands = PhoneBrands.EmotionUI_3;
				} else {
					AppConfigure.cPhoneBrands = PhoneBrands.EmotionUI;
				}
				return;
			}

			rom = getSystemProperty("ro.miui.ui.version.name");
			if (rom != null && rom.length() > 0) {
				AppConfigure.cPhoneBrands = PhoneBrands.MIUI;
				return;
			}

			AppConfigure.cPhoneBrands = PhoneBrands.UnKnow;
		}
	}

	public static PhoneBrands getPhoneBrands() {
		if (AppConfigure.cPhoneBrands == null) {
			initHelper();
		}

		return AppConfigure.cPhoneBrands;
	}

	/**
	 * 获取系统rom型号
	 * 
	 * @param propName
	 * @return
	 */
	public static String getSystemProperty(String propName) {
		String line = null;
		BufferedReader input = null;
		try {
			Process p = Runtime.getRuntime().exec("getprop " + propName);
			input = new BufferedReader(new InputStreamReader(p.getInputStream()), 1024);
			line = input.readLine();
		} catch (IOException ex) {
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
				}
			}
		}
		return line;
	}

	/**
	 * 判读有无魅族悬浮窗权限设置
	 * 
	 * @return
	 */
	public static boolean hasMeizuSecActivity() {
		if (getPhoneBrands() == PhoneBrands.Meizu) {
			Intent intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
			intent.putExtra("packageName", AppConfigure.PackageName);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

			List<ResolveInfo> list = AppConfigure.cApplication.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
			return !(list == null || list.isEmpty());
		}

		return false;
	}

}
