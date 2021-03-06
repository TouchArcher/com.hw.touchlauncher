package com.hw.toucharcher.helper;

import com.hw.toucharcher.helper.PhoneHelper.PhoneBrands;

import android.app.Application;

public class AppConfigure {
	public final static String PackageName = "com.hw.toucharcher";
	public static Application cApplication;
	
	protected static PhoneBrands cPhoneBrands;
	protected static Boolean isMainProcess;
	protected static Integer cVersionCode;
	protected static String cVersionName;
	protected static String cProcessName;
	protected static Integer cStatusHeight;
	protected static Integer cScreenHeight;
	protected static Integer cScreenWidth;
	protected static String cIMEI;
	protected static String cMac;
	protected static Boolean isFirstRun;
	protected static String cPhoneNum;
}
