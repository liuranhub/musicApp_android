package com.music.view.impl;

import android.content.Context;
import android.view.View;
import android.widget.TableRow;

public class NetTableRow extends TableRow {

	public NetTableRow(Context context) {
		super(context);
	}

	public NetTableRow addViews(View view) {
		this.addView(view);
		return this;
	}
}
