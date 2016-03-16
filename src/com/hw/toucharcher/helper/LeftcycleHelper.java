package com.hw.toucharcher.helper;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import com.hw.toucharcher.base.LifecycleActivity;
import com.hw.toucharcher.base.LifecycleActivity.LifecycleCallback;

public class LeftcycleHelper {
	/**
	 * 创建工作线程handler
	 * 
	 * @param name
	 * @return
	 */
	public static Handler createBackgroundHandler(LifecycleActivity a) {
		HandlerThread thread = new HandlerThread("Thread-hid" + a.hashCode());
		thread.start();
		final Handler handler = new Handler(thread.getLooper());
		a.setLifecycleCallback(new LifecycleCallback() {

			@Override
			public void run(LifecycleActivity a) {
				try {
					handler.removeCallbacksAndMessages(null);
					Looper looper = handler.getLooper();
					looper.getThread().interrupt();
					looper.quit();
				} catch (Exception e) {
				}
			}
		});
		return handler;
	}

	public static void registerReceiver(LifecycleActivity a, final BroadcastReceiver receiver, IntentFilter filter) {
		a.registerReceiver(receiver, filter);
		a.setLifecycleCallback(new LifecycleCallback() {

			@Override
			public void run(LifecycleActivity a) {
				a.unregisterReceiver(receiver);
			}
		});
	}

	public static boolean bindService(LifecycleActivity a, Intent service, final ServiceConnection conn, int flags) {
		boolean bindService = a.bindService(service, conn, flags);
		a.setLifecycleCallback(new LifecycleCallback() {

			@Override
			public void run(LifecycleActivity a) {
				a.unbindService(conn);
			}
		});
		return bindService;
	}
}
