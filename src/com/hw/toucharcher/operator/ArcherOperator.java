package com.hw.toucharcher.operator;

import android.view.View.OnClickListener;

import com.hw.toucharcher.ActivityOperator;
import com.hw.toucharcher.R;

public class ArcherOperator {
	private final IOperator mOperator;

	public interface IOperator extends ActivityOperator, OnClickListener {

	}

	public ArcherOperator(IOperator operator) {
		mOperator = operator;
		operator.onCreateContentView(R.layout.layout_archer, null, null);
		mOperator.findViewById(R.id.id_open_apppermissions).setOnClickListener(mOperator);
		mOperator.findViewById(R.id.id_open_development).setOnClickListener(mOperator);
		mOperator.findViewById(R.id.id_open_desktop).setOnClickListener(mOperator);
	}
}
