package com.hw.toucharcher.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hw.toucharcher.R;

public class CommonTitleBar extends RelativeLayout implements OnGlobalLayoutListener {
	private final TextView mTitle;
	private final View mLeftView;
	private final View mRightView;

	public CommonTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CommonTitleBar, defStyleAttr, 0);

		Drawable drawable = a.getDrawable(R.styleable.CommonTitleBar_ctb_left_src);
		CharSequence text = a.getText(R.styleable.CommonTitleBar_ctb_left_text);

		if (drawable != null) {
			ImageView view = new ImageView(context);
			view.setScaleType(ScaleType.CENTER);
			view.setImageDrawable(drawable);
			mLeftView = view;
		} else if (text != null) {
			TextView view = new TextView(context);
			view.setSingleLine();
			view.setEllipsize(TruncateAt.END);
			view.setGravity(Gravity.CENTER);

			view.setText(text);
			view.setTextColor(a.getColor(R.styleable.CommonTitleBar_ctb_left_textcolor, view.getCurrentTextColor()));

			float dimension = a.getDimension(R.styleable.CommonTitleBar_ctb_left_textsize, -1);
			if (dimension != -1)
				view.setTextSize(TypedValue.COMPLEX_UNIT_PX, dimension);
			mLeftView = view;
		} else {
			mLeftView = new View(context);
		}

		drawable = a.getDrawable(R.styleable.CommonTitleBar_ctb_right_src);
		text = a.getText(R.styleable.CommonTitleBar_ctb_right_text);

		if (drawable != null) {
			ImageView view = new ImageView(context);
			view.setScaleType(ScaleType.CENTER);
			view.setImageDrawable(drawable);

			mRightView = view;
		} else if (text != null) {
			TextView view = new TextView(context);
			view.setSingleLine();
			view.setEllipsize(TruncateAt.END);
			view.setGravity(Gravity.CENTER);

			view.setText(a.getText(R.styleable.CommonTitleBar_ctb_right_text));
			view.setTextColor(a.getColor(R.styleable.CommonTitleBar_ctb_right_textcolor, view.getCurrentTextColor()));

			float dimension = a.getDimension(R.styleable.CommonTitleBar_ctb_right_textsize, -1);
			if (dimension != -1)
				view.setTextSize(TypedValue.COMPLEX_UNIT_PX, dimension);

			mRightView = view;
		} else {
			mRightView = new View(context);
		}

		mTitle = new TextView(context);
		mTitle.setSingleLine();
		mTitle.setEllipsize(TruncateAt.END);
		mTitle.setGravity(Gravity.CENTER);
		mTitle.setText(a.getText(R.styleable.CommonTitleBar_ctb_title_text));
		mTitle.setTextColor(a.getColor(R.styleable.CommonTitleBar_ctb_title_textcolor, mTitle.getCurrentTextColor()));

		float dimension = a.getDimension(R.styleable.CommonTitleBar_ctb_title_textsize, -1);
		if (dimension != -1)
			mTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, dimension);

		a.recycle();

		mLeftView.setId(R.id.common_title_left);
		mRightView.setId(R.id.common_title_right);
		mTitle.setId(R.id.common_title_text);

		getViewTreeObserver().addOnGlobalLayoutListener(this);
	}

	public CommonTitleBar(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CommonTitleBar(Context context) {
		this(context, null);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onGlobalLayout() {
		int height = getHeight();
		if (height != 0) {
			getViewTreeObserver().removeGlobalOnLayoutListener(this);

			int w = (int) (getWidth() * 0.2f);

			LayoutParams params = new LayoutParams(w, height);
			addView(mLeftView, params);

			params = new LayoutParams(w, height);
			params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			addView(mRightView, params);

			params = new LayoutParams(LayoutParams.MATCH_PARENT, height);
			params.addRule(RelativeLayout.RIGHT_OF, R.id.common_title_left);
			params.addRule(RelativeLayout.LEFT_OF, R.id.common_title_right);
			addView(mTitle, params);
		}
	}

	public TextView getCtb_title() {
		return mTitle;
	}

	public View getCtb_left() {
		return mLeftView;
	}

	public View getCtb_right() {
		return mRightView;
	}

	public void addLeftClickListener(OnClickListener listener) {
		mLeftView.setOnClickListener(listener);
	}

	public void addTitleClickListener(OnClickListener listener) {
		mTitle.setOnClickListener(listener);
	}

	public void addRightClickListener(OnClickListener listener) {
		mRightView.setOnClickListener(listener);
	}

	public void addAllClickListener(OnClickListener listener) {
		mLeftView.setOnClickListener(listener);
		mRightView.setOnClickListener(listener);
		mTitle.setOnClickListener(listener);
	}

	public void setTitle(String title) {
		mTitle.setText(title);
	}
}
