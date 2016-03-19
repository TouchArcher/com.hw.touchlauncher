package com.hw.toucharcher.touch;

import com.hw.toucharcher.helper.AppConfigure;

import android.content.Context;

public class TouchOperator {
	private final IOperator mOperator;
	private final Context mContext;
	
	public interface IOperator{
	}

	
	public TouchOperator(IOperator operator) {
		mOperator = operator;
		if (operator instanceof Context){
			mContext = (Context) operator;
		}else {
			mContext = AppConfigure.cApplication;
		}
	}
}
