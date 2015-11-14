package com.music.view.impl;


import android.app.Activity;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class MusicRowRealtivePlayer extends MusicRowRealtiveBase{

	public MusicRowRealtivePlayer(Activity activity, Integer id) {
		super(activity, id);
	}
	
	@Override
	protected void createSecondlButton() {
		ImageView contralImage = new ImageView(getActivity().getBaseContext());
		
		LayoutParams contralImageLayout = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		contralImageLayout.addRule(RelativeLayout.CENTER_IN_PARENT);
		contralImageLayout.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		contralImageLayout.width = 25;
		contralImageLayout.height = 20;
		contralImageLayout.setMargins(10, 0, 30, 0);

		getMusicRela().addView(contralImage, contralImageLayout);
	}

}
