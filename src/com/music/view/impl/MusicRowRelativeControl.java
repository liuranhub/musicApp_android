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
		createButton("����", R.drawable.localmusic_share_x, 50 , new OnClickListener() {
			
			public void onClick(View v) {
				Toast.makeText(getActivity(), "����", Toast.LENGTH_SHORT).show();
				
			}
		} );
		createButton("���", R.drawable.localmusic_addtolist_x, 200 , new OnClickListener() {
			
			public void onClick(View v) {
				Toast.makeText(getActivity(), "���", Toast.LENGTH_SHORT).show();
				
			}
		});
		createButton("������Ϣ", R.drawable.localmusic_inf_x, 350 , new OnClickListener() {
			
			public void onClick(View v) {
				Toast.makeText(getActivity(), "������Ϣ", Toast.LENGTH_SHORT).show();
				
			}
		} );
		createButton("ɾ������", R.drawable.localmusic_delete_x, 500 , new OnClickListener() {
			public void onClick(View v) {
				
				deleteRow(getId() , true);
				
				/**
				 * �赥��Ϣ�ĸ��²��ܹ������������
				 * */
//				music.removeMusicById(getId());
				
				Toast.makeText(getActivity(), "ɾ���ɹ�", Toast.LENGTH_SHORT).show();
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
