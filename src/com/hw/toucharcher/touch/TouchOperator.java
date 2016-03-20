package com.hw.toucharcher.touch;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.ViewParent;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

import com.hw.toucharcher.R;
import com.hw.toucharcher.helper.AnimationHelper;
import com.hw.toucharcher.helper.AppConfigure;
import com.hw.toucharcher.helper.AppHelper;
import com.hw.toucharcher.touch.TouchView.OnTouchMoveListener;
import com.hw.toucharcher.util.AnimatorListenerImpl;
import com.nineoldandroids.animation.Animator;

public class TouchOperator implements OnTouchListener {
	private final IOperator mOperator;
	private final Context mContext;
	private final WindowManager wm;
	private final LayoutParams mFloatParams;
	private View mMainView;
	private View mMenuView;
	private LayoutParams mMenuParams;
	private View mMenuContent;

	public interface IOperator extends OnTouchMoveListener, OnClickListener {
	}

	public TouchOperator(IOperator operator) {
		mOperator = operator;
		if (operator instanceof Context) {
			mContext = (Context) operator;
		} else {
			mContext = AppConfigure.cApplication;
		}

		wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);

		mMainView = View.inflate(mContext, R.layout.float_view, null);
		TouchView touchView = (TouchView) mMainView.findViewById(R.id.touch_main);
		touchView.setOnTouchMoveListener(mOperator);
		touchView.setOnClickListener(mOperator);

		Resources resources = touchView.getResources();

		mFloatParams = new LayoutParams();
		mFloatParams.type = LayoutParams.TYPE_SYSTEM_ALERT;
		mFloatParams.gravity = Gravity.TOP | Gravity.LEFT;
		mFloatParams.format = PixelFormat.RGBA_8888;
		mFloatParams.flags = LayoutParams.FLAG_NOT_FOCUSABLE | LayoutParams.FLAG_NOT_TOUCH_MODAL;
		mFloatParams.width = resources.getDimensionPixelSize(R.dimen.d_float_width);
		mFloatParams.height = resources.getDimensionPixelSize(R.dimen.d_float_height);
	}

	public void openFloat(Point p) {
		if (mMenuView != null && mMenuView.getParent() != null) {
			AnimationHelper.floatMenuCloseAnim(mMenuContent, new AnimatorListenerImpl() {
				@Override
				public void onAnimationEnd(Animator arg0) {
					if (mMenuView.getParent() != null) {
						wm.removeViewImmediate(mMenuView);
					}
				}
			});
		}

		ViewParent parent = mMainView.getParent();
		if (parent == null) {
			if (p != null) {
				mFloatParams.x = p.x;
				mFloatParams.y = p.y;
			}

			wm.addView(mMainView, mFloatParams);
		}
	}

	public void updateFloat(Point p) {
		ViewParent parent = mMainView.getParent();
		if (parent != null) {
			mFloatParams.x = p.x;
			mFloatParams.y = p.y;

			wm.updateViewLayout(mMainView, mFloatParams);
		}
	}

	public void openMenu() {
		if (mMenuView == null) {
			mMenuView = View.inflate(mContext, R.layout.float_menu, null);
			mMenuView.setOnTouchListener(this);
			mMenuView.setOnKeyListener(new OnKeyListener() {
				
				@Override
				public boolean onKey(View v, int keyCode, KeyEvent event) {
					if (keyCode == KeyEvent.KEYCODE_BACK){
						openFloat(null);
						return true;
					}
					
					return false;
				}
			});

			mMenuContent = mMenuView.findViewById(R.id.touch_menu);
			android.widget.FrameLayout.LayoutParams params = new android.widget.FrameLayout.LayoutParams((int) (AppHelper.getScreenWidth() * 0.8f),
					(int) (AppHelper.getScreenHeight() * 0.5f), Gravity.CENTER);
			mMenuContent.setLayoutParams(params);

			mMenuParams = new LayoutParams();
			mMenuParams.type = LayoutParams.TYPE_SYSTEM_ALERT;
			mMenuParams.gravity = Gravity.TOP | Gravity.LEFT;
			mMenuParams.format = PixelFormat.RGBA_8888;
			mMenuParams.flags = LayoutParams.FLAG_NOT_FOCUSABLE;
			mMenuParams.width = LayoutParams.MATCH_PARENT;
			mMenuParams.height = LayoutParams.MATCH_PARENT;
		}

		if (mMainView.getParent() != null) {
			wm.removeViewImmediate(mMainView);
		}

		ViewParent parent = mMenuView.getParent();
		if (parent == null) {
			wm.addView(mMenuView, mMenuParams);
		}
		AnimationHelper.floatMenuOpenAnim(mMenuContent);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN && AppHelper.isOutSideView(mMenuContent, event.getX(), event.getY())) {
			openFloat(null);
		}
		return false;
	}
}
