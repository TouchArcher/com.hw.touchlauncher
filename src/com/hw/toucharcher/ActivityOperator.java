package com.hw.toucharcher;

import android.view.View;
import android.view.ViewGroup.LayoutParams;

public interface ActivityOperator {
	public View findViewById(int id);
	public void onCreateContentView(int layoutID, View v, LayoutParams p);
}
