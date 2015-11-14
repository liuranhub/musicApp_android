package com.music.view.fartory;

import com.music.musicapp.R;
import com.music.util.impl.InitUtil;
import com.music.view.impl.OrderPopupWindow;

import android.app.Activity;

public class OrderPopupWindowFactory {

	private OrderPopupWindow orderPopupWindow = null;

	public OrderPopupWindow create(Activity activity) {
		orderPopupWindow = new OrderPopupWindow(activity, R.layout.player_order);
		orderPopupWindow.setWidth(InitUtil.getOderPopuwinWidth());
		orderPopupWindow.setHight(InitUtil.getOderPopuwinHeight());
		return orderPopupWindow;
	}
}
