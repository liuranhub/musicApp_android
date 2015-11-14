package com.music.view.impl;

import com.music.musicapp.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class MusicRowRelativeControl extends MusicRowRelativeAbstract {

	public MusicRowRelativeControl(Activity activity, Integer id) {
		setId(id);
		setActivity(activity);
	}

	public void setOrderPopupWindow(OrderPopupWindow orderPopupWindow) {
		return;
	}

	private void createButton(String textStr, int rsid, int mleft , OnClickListener onClick) {
		ImageView image = new ImageView(getActivity().getBaseContext());

		image.setBackgroundResource(rsid);

		LayoutParams imageLayout = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);

		imageLayout.addRule(RelativeLayout.CENTER_HORIZONTAL);

		TextView text = new TextView(getActivity().getBaseContext());
		text.setText(textStr);
		text.setTextSize(12);
		text.setTextColor(Color.WHITE);

		text.setPadding(5, 50, 0, 0);

		LayoutParams textLayout = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);

		textLayout.addRule(RelativeLayout.CENTER_HORIZONTAL);

		RelativeLayout relative = new RelativeLayout(getActivity()
				.getBaseContext());
		relative.setBackgroundColor(Color.BLUE);
		relative.setPadding(0, 10, 0, 10);
		relative.setOnClickListener(onClick);

		LayoutParams relativeLayout = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		relativeLayout.width = 100;

		relative.addView(image, imageLayout);
		relative.addView(text, textLayout);
		relativeLayout.setMargins(mleft, 0, 0, 0);

		getMusicRela().addView(relative, relativeLayout);
	}

	@Override
	protected void create() {
		createButton("分享", R.drawable.localmusic_share_x, 50 , new OnClickListener() {
			
			public void onClick(View v) {
				Toast.makeText(getActivity(), "分享", Toast.LENGTH_SHORT).show();
				
			}
		} );
		createButton("添加", R.drawable.localmusic_addtolist_x, 200 , new OnClickListener() {
			
			public void onClick(View v) {
				Toast.makeText(getActivity(), "添加", Toast.LENGTH_SHORT).show();
				
			}
		});
		createButton("歌曲信息", R.drawable.localmusic_inf_x, 350 , new OnClickListener() {
			
			public void onClick(View v) {
				Toast.makeText(getActivity(), "歌曲信息", Toast.LENGTH_SHORT).show();
				
			}
		} );
		createButton("删除歌曲", R.drawable.localmusic_delete_x, 500 , new OnClickListener() {
			public void onClick(View v) {
				
				deleteRow(getId() , true);
				
				/**
				 * 歌单信息的更新不能够在这里面进行
				 * */
//				music.removeMusicById(getId());
				
				Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_SHORT).show();
			}
		});
		
		getMusicRela().setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				deleteRow(getId() ,false);
			}
		});
		
	}
	
	private void deleteRow(Integer id , boolean  deleteMusic){
		Intent intent = new Intent();
		intent.setAction("remove_d");
		intent.putExtra("id", id);
		System.out.println(id);
		intent.putExtra("deleteMusic", deleteMusic);
		getActivity().sendBroadcast(intent);
	}

}
