package com.hw.toucharcher.util;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AppOpsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Binder;
import android.os.Build;
import android.provider.Contacts;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.hw.toucharcher.helper.LogHelper;

/**
 * @author hzb
 * @since2014年9月1日 下午4:00:00
 */

public class PhoneUtil {

	private static String TAG = "PhoneUtil";

	private static String SanXing = "samsung";

	private static String Nubia = "nubia";

	private static String ZhongXing = "zhongxing";

	private static String Meizu = "Meizu";
	
	private static String ZTE = "ZTE";
	
	private static String VIVO = "vivo";
	
	private static String OPPO = "OPPO";
	
	private static String COOLPAD = "Coolpad";

	private static String SonyEricsson = "Sony Ericsson";

	public static boolean isSanXingOrNubia() {
		boolean flag = false;

		try {
			int version = 3;
			Class<android.os.Build.VERSION> build_version_class = android.os.Build.VERSION.class;
			// 取得 android 版本
			java.lang.reflect.Field field = build_version_class.getField("SDK_INT");
			version = (Integer) field.get(new android.os.Build.VERSION());

			Class<android.os.Build> build_class = android.os.Build.class;
			// 取得牌子
			java.lang.reflect.Field manu_field = build_class.getField("MANUFACTURER");
			String manufacturer = (String) manu_field.get(new android.os.Build());

			if (manufacturer.equalsIgnoreCase(SanXing) || manufacturer.equalsIgnoreCase(Nubia) | manufacturer.equalsIgnoreCase(ZhongXing)) {
				flag = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	public static boolean isNubia() {
		boolean flag = false;

		try {
			int version = 3;
			Class<android.os.Build.VERSION> build_version_class = android.os.Build.VERSION.class;
			// 取得 android 版本
			java.lang.reflect.Field field = build_version_class.getField("SDK_INT");
			version = (Integer) field.get(new android.os.Build.VERSION());

			Class<android.os.Build> build_class = android.os.Build.class;
			// 取得牌子
			java.lang.reflect.Field manu_field = build_class.getField("MANUFACTURER");
			String manufacturer = (String) manu_field.get(new android.os.Build());

			if (manufacturer.equalsIgnoreCase(Nubia) | manufacturer.equalsIgnoreCase(ZhongXing)) {
				flag = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	public static boolean isMeizu() {
		boolean flag = false;

		try {
			Class<android.os.Build> build_class = android.os.Build.class;
			// 取得牌子
			java.lang.reflect.Field manu_field = build_class.getField("MANUFACTURER");
			String manufacturer = (String) manu_field.get(new android.os.Build());

			if (manufacturer.equalsIgnoreCase(Meizu)) {
				flag = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	public static boolean isSonyEricsson() {
		boolean flag = false;

		try {
			Class<android.os.Build> build_class = android.os.Build.class;
			// 取得牌子
			java.lang.reflect.Field manu_field = build_class.getField("MANUFACTURER");
			String manufacturer = (String) manu_field.get(new android.os.Build());

			if (manufacturer.equalsIgnoreCase(SonyEricsson)) {
				flag = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	public static boolean isVIVO() {
		boolean flag = false;
		if (Build.BRAND.equalsIgnoreCase(VIVO)) {
			flag = true;
		}
		return flag;
	}
	
	public static boolean isCOOLPAD() {
		boolean flag = false;
		if (Build.MODEL.contains(COOLPAD)) {
			flag = true;
		}
		return flag;
	}
	
	public static boolean isOPPO() {
		boolean flag = false;
		if (Build.BRAND.equalsIgnoreCase(OPPO)) {
			flag = true;
		}
		return flag;
	}
	
	public static boolean isZTE() {
		boolean flag = false;
		if (Build.BRAND.equalsIgnoreCase(ZTE)) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 获取系统rom型号
	 * 
	 * @param propName
	 * @return
	 */
	public static String getSystemProperty(String propName) {
		String line;
		BufferedReader input = null;
		try {
			Process p = Runtime.getRuntime().exec("getprop " + propName);
			input = new BufferedReader(new InputStreamReader(p.getInputStream()), 1024);
			line = input.readLine();
			input.close();
		} catch (IOException ex) {
			LogHelper.e("xiaoyulong", "Unable to read sysprop " + propName, ex);
			return null;
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					LogHelper.e("xiaoyulong", "Exception while closing InputStream", e);
				}
			}
		}
		LogHelper.d("xiaoyulong", "本机系统为：" + line);
		return line;
	}

	/**
	 * 判断是否是MIUI
	 * 
	 * @return
	 */
	public static boolean isMIUI() {
		return !TextUtils.isEmpty(getSystemProperty("ro.miui.ui.version.name"));
	}

	/**
	 * 判断是否是EmotionUI_3.0 [ro.build.version.emui]: [EmotionUI_3.0]
	 * 
	 * @return
	 */
	public static boolean isEmotionUI_3() {

		String rom = getSystemProperty("ro.build.version.emui");
		if (!TextUtils.isEmpty(rom)) {
			return rom.contains("EmotionUI_3");
		}
		return false;
	}

	/**
	 * 判断是否是华为的EmotionUI [ro.build.version.emui]: [EmotionUI_2.0]
	 * 
	 * @return
	 */
	public static boolean isEmotionUI() {
		return !TextUtils.isEmpty(getSystemProperty("ro.build.version.emui"));
	}

	public static String getPhoneInfo() {

		int version = 0;
		try {
			Class<android.os.Build.VERSION> build_version_class = android.os.Build.VERSION.class;
			// 取得 android 版本
			java.lang.reflect.Field field = build_version_class.getField("SDK_INT");
			version = (Integer) field.get(new android.os.Build.VERSION());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String manufacturer = "unknow";
		Class<android.os.Build> build_class = android.os.Build.class;
		try {
			// 取得牌子
			java.lang.reflect.Field manu_field = build_class.getField("MANUFACTURER");
			manufacturer = (String) manu_field.get(new android.os.Build());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String model = "unknow";
		try {
			// 取得型號
			java.lang.reflect.Field field2 = build_class.getField("MODEL");
			model = (String) field2.get(new android.os.Build());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String device = "unknow";
		try {
			// 模組號碼
			java.lang.reflect.Field device_field = build_class.getField("DEVICE");
			device = (String) device_field.get(new android.os.Build());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String info = "品牌:[" + manufacturer + "] 型號:[" + model + "] SDK版本:[" + version + "] 模組號碼:[" + device + "]";

		// LogHelper.d(TAG, info);
		return info;
	}

	/*	*//**
	 * 判断map中打开系统默认桌面选择框的Activity
	 * 
	 * @param map
	 * @return 打开系统默认桌面选择框的Activity的名称
	 */
	/*
	 * public static Intent getHomeActivityName(Context context ,Map<String,
	 * String[]> map) { Set<Entry<String, String[]>> entries = map.entrySet();
	 * for (Entry<String, String[]> entry : entries) {
	 * if(!TextUtils.isEmpty(getSystemProperty(entry.getKey()))){ return
	 * getIntent(context, entry.getValue()); } } return
	 * getDefaultIntent(context); }
	 */

	private static Intent getIntent(Context context, String[] names) {
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		ComponentName cn = new ComponentName(names[0], names[1]);
		intent.setComponent(cn);

		return intent;
	}

	/*
	 * private static Intent getDefaultIntent(Context act){ ComponentName
	 * componentName = new ComponentName(act, NoActivity.class);
	 * act.getPackageManager().setComponentEnabledSetting(componentName,
	 * PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
	 * PackageManager.DONT_KILL_APP); Intent intent = new Intent();
	 * intent.setAction(Intent.ACTION_MAIN);
	 * intent.addCategory(Intent.CATEGORY_HOME); act.startActivity(intent);
	 * act.getPackageManager().setComponentEnabledSetting(componentName,
	 * PackageManager.COMPONENT_ENABLED_STATE_DEFAULT,
	 * PackageManager.DONT_KILL_APP); return null; }
	 */

	/**
	 * 获取手机串号(IMEI)
	 * 
	 * @param context
	 * @return
	 */
	public static String getIMEI(Context context) {
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		String imei = tm.getDeviceId();
		if (imei == null) {
			return "";
		} else {
			return imei;
		}
	}

	/* *//**
	 * 把IMEI用一大整数代替
	 * 
	 * @param imei
	 * @return
	 */
	public static String imeiToBigInteger(String imei) {
		BigInteger result = new BigInteger("0");
		try {
			BigInteger n = new BigInteger("16");
			String md5 = new MD5Util().getMD5ofStr(imei);
			int size = md5.length();
			for (int i = 0; i < size; i++) {
				BigInteger a = new BigInteger("" + md5.charAt(i), 16);
				BigInteger b = n.pow(size - 1 - i);
				result = result.add(a.multiply(b));
			}
			return result.toString();
		} catch (Exception e) {
		}
		return result.toString();
	}

	/**
	 * 获取手机型号
	 * 
	 * @return
	 */
	public static String getPhoneModel() {
		return Build.MODEL;
	}

	/**
	 * 获取系统版本，如1.5,2.1
	 * 
	 * @return　SDK版本号
	 */
	public static String getSDK() {
		return Build.VERSION.RELEASE;
	}

	public static Intent resloveIntent(String className, Intent data, PackageManager packageManager) throws NameNotFoundException {
		if (className.equals("com.android.contacts.activities.DialtactsActivity")) {
			data = new Intent();
			data.setAction(Intent.ACTION_DIAL);
		} else if (className.equals("com.android.mms.ui.ConversationList")) {
			Intent tempIntent = new Intent();
			tempIntent.setAction(Intent.ACTION_VIEW);
			tempIntent.setData(Contacts.People.CONTENT_URI);
			ComponentName cName = resolveActivity(packageManager, tempIntent);
			//
			data = new Intent(Intent.ACTION_MAIN);
			data.addCategory(Intent.CATEGORY_DEFAULT);
			data.setType("vnd.android-dir/mms-sms");
			ComponentName cn = resolveActivity(packageManager, data);
			if (cName != null && cn != null && cName.getClassName().equals(cn.getClassName())) {
				data = new Intent(Intent.ACTION_MAIN);
				data.addCategory(Intent.CATEGORY_DEFAULT);
				data.setClassName("com.android.contacts", "com.android.mms.ui.ConversationList");
			}
			LogHelper.d("cjy", "resloveIntent mms ="+data.toUri(0));
		} else if (className.equals("com.android.camera.ImageGallery")) {
			data = new Intent();
			data.setAction(Intent.ACTION_VIEW);
			data.setType("image/*");
			data.addCategory(Intent.CATEGORY_DEFAULT);
		} else if (className.equals("com.android.camera.CameraActivity")) {
			data = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
		}
		return data;
	}

	public static ComponentName resolveActivity(PackageManager packageManager, Intent data) throws NameNotFoundException {
		ComponentName cn = null;
		// 通过查询，获得所有ResolveInfo对象.
		List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(data, PackageManager.MATCH_DEFAULT_ONLY);
		ResolveInfo bestMatch = packageManager.resolveActivity(data, PackageManager.MATCH_DEFAULT_ONLY);
		if (resolveInfos != null && resolveInfos.size() > 0) {
			for (ResolveInfo resolveInfo : resolveInfos) {
				if (bestMatch != null && resolveInfo.activityInfo.name.equals(bestMatch.activityInfo.name)) {
					cn = new ComponentName(bestMatch.activityInfo.packageName, bestMatch.activityInfo.name);
				}
			}
			if (cn == null) {
				cn = new ComponentName(resolveInfos.get(0).activityInfo.packageName, resolveInfos.get(0).activityInfo.name);
			}
		} else {
			if (bestMatch != null) {
				cn = new ComponentName(bestMatch.activityInfo.packageName, bestMatch.activityInfo.name);
			}
		}
		return cn;
	}

	/**
	 * 获取手机虚拟键高度
	 * 
	 * @param context
	 * @return
	 */
	public static int getVirtualKeyHeight(Context context, int screenHeight, int visibleHeight) {
		if (context instanceof Activity) {
			Activity activity = (Activity) context;
			int frameHeight = activity.getWallpaperDesiredMinimumHeight();
			int height = frameHeight - screenHeight;
			if (PhoneUtil.isMeizu()) {
				height = frameHeight - visibleHeight;
			}
			return height;
		} else {
			LogHelper.d("cjy", "context is not Activity;return 0");
			return 0;
		}
	}

	public static boolean isSanXing() {
		boolean flag = false;
		try {
			Class<android.os.Build> build_class = android.os.Build.class;
			java.lang.reflect.Field manu_field = build_class.getField("MANUFACTURER");
			String manufacturer = (String) manu_field.get(new android.os.Build());
			if (manufacturer.equalsIgnoreCase(SanXing)) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 获取手机号
	 * 
	 * @param context
	 * @return
	 */
	public static String getPhoneNum(Context context) {
		TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return telephonyManager.getLine1Number();
	}

	/**
	 * 判断MIUI的悬浮窗权限 SDK要大于或等于19
	 * 
	 * @param context
	 * @return
	 * @throws Exception
	 */
	public static boolean isMiuiFloatWindowOpAllowed(Context context) throws Exception {
		// AppOpsManager.OP_SYSTEM_ALERT_WINDOW = 24
		return checkOp(context, 24);
	}

	@SuppressLint("NewApi")
	public static boolean checkOp(Context context, int op) throws Exception {
		final int version = Build.VERSION.SDK_INT;
		if (version >= 19) {
			AppOpsManager manager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);

			Class clazz = AppOpsManager.class;
			Method dispatchMethod = clazz.getMethod("checkOp", new Class[] { int.class, int.class, String.class });
			int mode = (Integer) dispatchMethod.invoke(manager, new Object[] { op, Binder.getCallingUid(), context.getApplicationContext().getPackageName() });
			if (AppOpsManager.MODE_ALLOWED == mode) {
				return true;
			} else {
				return false;
			}

		}
		return false;
	}

	/**
	 * 判断网络是否连接
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetworkConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
			if (mNetworkInfo != null) {
				return mNetworkInfo.isAvailable();
			}
		}
		return false;
	}
	/**
	 * 判读有无魅族悬浮窗权限设置
	 * @param c
	 * @return
	 */
	public static boolean hasMeizuSecActivity(Context c){
		if (isMeizu()){
			Intent intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
			intent.putExtra("packageName", c.getPackageName());
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			
			List<ResolveInfo> list = c.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
			if (!list.isEmpty())
				return true;
		}
		
		return false;
	}

}
