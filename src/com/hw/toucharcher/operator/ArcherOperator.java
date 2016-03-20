package com.hw.toucharcher.operator;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

import com.hw.toucharcher.ActivityOperator;
import com.hw.toucharcher.R;
import com.hw.toucharcher.helper.AppConfigure;

public class ArcherOperator {
	private final IOperator mOperator;
	private final Context mContext;
	private ViewGroup mMainView;

	public interface IOperator extends ActivityOperator, OnClickListener {

	}

	public ArcherOperator(IOperator operator) {
		mOperator = operator;
		if (operator instanceof Context) {
			mContext = (Context) operator;
		} else {
			mContext = AppConfigure.cApplication;
		}
		mMainView = (ViewGroup) View.inflate(mContext, R.layout.layout_archer, null);
		operator.onCreateContentView(0, mMainView, null);
		mMainView.findViewById(R.id.id_open_apppermissions).setOnClickListener(mOperator);
		mMainView.findViewById(R.id.id_open_development).setOnClickListener(mOperator);
		mMainView.findViewById(R.id.id_open_desktop).setOnClickListener(mOperator);
	}
}
