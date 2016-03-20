package com.hw.toucharcher.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.hw.toucharcher.R;

public class TouchView extends ImageView {
	public interface OnTouchMoveListener {
		void onTouchMove(int x, int y);
	}

	private OnTouchMoveListener mMoveListener;
	private float mX;
	private float mY;

	public TouchView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public TouchView(Context context, AttributeSet attrs) {
		super(context, attrs, 0);
	}

	public TouchView(Context context) {
		super(context, null);
	}

	private void init() {
		setImageResource(R.drawable.ic_launcher);
	}

	public void setOnTouchMoveListener(OnTouchMoveListener listener) {
		mMoveListener = listener;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		boolean b = super.onTouchEvent(event);
		if (mMoveListener != null) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				mX = event.getX();
				mY = event.getY() + getHeight() * 0.5f;
				break;
			case MotionEvent.ACTION_MOVE:
				mMoveListener.onTouchMove((int) (event.getRawX() - mX), (int) (event.getRawY() - mY));
				break;
			}
			b = true;
		}

		return b;
	}
}
