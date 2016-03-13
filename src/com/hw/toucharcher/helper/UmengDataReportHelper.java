package com.hw.toucharcher.helper;

import java.util.HashMap;

import android.content.Context;

import com.umeng.analytics.MobclickAgent;

public class UmengDataReportHelper {
	@SuppressWarnings("deprecation")
	public static void init() {
		MobclickAgent.openActivityDurationTrack(true);
		// 设置统计启动次数的时间间隔,这里设置的是30分钟的毫秒值
		MobclickAgent.setSessionContinueMillis(1800000);
		// 发送策略定义了用户由统计分析SDK产生的数据发送回友盟服务器的频率
		MobclickAgent.updateOnlineConfig(AppConfigure.cApplication);
	}

	public static void onEvent(String eventId) {
		MobclickAgent.onEvent(AppConfigure.cApplication, eventId);
	}

	public static void onEvent(Context context, int resId) {
		MobclickAgent.onEvent(AppConfigure.cApplication, AppConfigure.cApplication.getString(resId));
	}

	public static void onEvent(String eventId, String value) {
		MobclickAgent.onEvent(AppConfigure.cApplication, eventId, value);
	}

	public static void onEvent(String eventId, HashMap<String, String> map) {
		MobclickAgent.onEvent(AppConfigure.cApplication, eventId, map);
	}

	public static void onEventValue(String eventId, HashMap<String, String> map, int duration) {
		MobclickAgent.onEventValue(AppConfigure.cApplication, eventId, map, duration);
	}
}
