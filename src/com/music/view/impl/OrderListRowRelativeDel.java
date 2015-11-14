package com.music.view.impl;

import com.music.musicapp.R;
import com.music.util.impl.InitUtil;
import com.music.util.impl.MusicBase;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class OrderListRowRelativeDel extends OrderListRowRelative {

	public OrderListRowRelativeDel(Activity activity, String orderName) {
		super(activity, orderName);
	}

	@Override
	protected void createSecondlButton() {
		return ;
	}

	@Override
	protected void createFirstButton() {
		/**
		 * addImage
		 * */
		ImageView addImage = new ImageView(getActivity().getBaseContext());

		addImage.setBackgroundResource(R.drawable.order_list_del_x);

		addImage.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				MusicBase.createMusic().getOrderMap()
						.deleteElementFromMap(getOrderName());
				getActivity().sendBroadcast(
						new Intent().setAction("updata_order_list_del"));
			}
		});

		LayoutParams addImageLayout = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		addImageLayout.addRule(RelativeLayout.CENTER_VERTICAL);
		addImageLayout.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		addImageLayout.width = InitUtil.getTextheight() - 20;
		addImageLayout.height = InitUtil.getTextheight() - 20;
		getMusicRela().addView(addImage, addImageLayout);

	}

}
