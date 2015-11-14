package com.music.view.impl;

import com.music.activity.OrderMusicActivity;
import com.music.musicapp.R;
import com.music.util.impl.InitUtil;
import com.music.util.impl.MusicBase;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

/**
 * 本地歌单行
 * */
public class OrderListRowRelative extends MusicRowRelativeAbstract {

	private String orderName = null;

	public OrderListRowRelative(Activity activity, String orderName) {
		this.orderName = orderName;
		setActivity(activity);
	}

	protected void initMusic() {

		setMusicRela(new RelativeLayout(getActivity().getBaseContext()));
		
		getMusicRela().setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(getActivity().getBaseContext(),
						OrderMusicActivity.class);
				intent.putExtra("ordername", orderName);
				getActivity().startActivity(intent);
			}
		});

		getMusicRela().setOnLongClickListener(new OnLongClickListener() {
			public boolean onLongClick(View v) {
				return false;
			}
		});

		setMusicRelaLayout(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT));

		getMusicRela().setFocusable(true);
		getMusicRela().setBackgroundResource(
				R.drawable.list_context_background_text);

		getMusicRelaLayout().setMargins(0, 0, 0, 0);
		getMusicRelaLayout().height = InitUtil.getTextheight() + 10;

		create();

	}

	protected void create() {
		createFirstButton();
		createText();
		createSecondlButton();
		createTextCount();
	}

	protected void createSecondlButton() {
		return;
	}

	protected void createFirstButton() {
		/**
		 * addImage
		 * */
		ImageView addImage = new ImageView(getActivity().getBaseContext());
		addImage.setOnLongClickListener(new OnLongClickListener() {
			public boolean onLongClick(View v) {
				AlertDialog.Builder builder = new Builder(getActivity());
				builder.setItems(
						getActivity().getResources().getStringArray(
								R.array.order_lsit_contral),
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								if (which == 0) {
									MusicBase.createMusic().getOrderMap()
											.deleteElementFromMap(orderName);
									getActivity()
											.sendBroadcast(
													new Intent()
															.setAction("updata_order_list"));
								} else {
									Builder builder2 = new Builder(
											getActivity());
									final EditText input = new EditText(
											getActivity());
									input.setHeight(40);
									builder2.setView(input)
											.setNegativeButton("取消", null)
											.setTitle("修改歌单名")
											.setPositiveButton(
													"确定",
													new DialogInterface.OnClickListener() {
														@Override
														public void onClick(
																DialogInterface dialog,
																int which) {
															String newName = input
																	.getText()
																	.toString();
															MusicBase
																	.createMusic()
																	.getOrderMap()
																	.resetOrderName(
																			orderName,
																			newName);
															getActivity()
																	.sendBroadcast(
																			new Intent()
																					.setAction("updata_order_list"));
														}
													});
									builder2.show();
								}
							}
						});
				builder.show();
				return true;
			}
		});
		addImage.setBackgroundResource(R.drawable.kg_ic_playing_bar_image_default);
		LayoutParams addImageLayout = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		addImageLayout.addRule(RelativeLayout.CENTER_VERTICAL);
		addImageLayout.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		addImageLayout.width = InitUtil.getTextheight() - 10;
		addImageLayout.height = InitUtil.getTextheight() - 10;
		getMusicRela().addView(addImage, addImageLayout);
	}

	protected void createText() {
		/**
		 * musicName
		 * */
		TextView musicName = new TextView(getActivity().getBaseContext());
		musicName.setText(orderName);
		musicName.setTextSize(19);
		musicName.setTextColor(Color.WHITE);
		musicName.setSingleLine();
		musicName.setWidth(370);
		LayoutParams musicNameLayout = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		musicNameLayout.addRule(RelativeLayout.CENTER_VERTICAL);

		/**
		 * centerTextRela文字
		 * */
		RelativeLayout centerTextRela = new RelativeLayout(getActivity()
				.getBaseContext());

		LayoutParams centerTextRelaLayout = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT);
		centerTextRelaLayout.setMargins(130, 0, 0, 0);

		centerTextRela.addView(musicName, musicNameLayout);

		getMusicRela().addView(centerTextRela, centerTextRelaLayout);
	}

	protected void createTextCount() {
		/**
		 * musicName
		 * */
		TextView musicName = new TextView(getActivity().getBaseContext());
		StringBuffer text = new StringBuffer();
		int count = MusicBase.createMusic().getOrderMap().getOrder(orderName)
				.size();
		text.append(count).append("首歌");
		musicName.setText(text);
		musicName.setTextSize(16);
		musicName.setTextColor(Color.WHITE);
		musicName.setSingleLine();
		LayoutParams musicNameLayout = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		musicNameLayout.addRule(RelativeLayout.CENTER_VERTICAL);

		/**
		 * centerTextRela文字
		 * */
		RelativeLayout centerTextRela = new RelativeLayout(getActivity()
				.getBaseContext());

		LayoutParams centerTextRelaLayout = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT);
		centerTextRelaLayout.setMargins(450, 0, 0, 0);

		centerTextRela.addView(musicName, musicNameLayout);

		getMusicRela().addView(centerTextRela, centerTextRelaLayout);
	}

	protected String getOrderName() {
		return orderName;
	}

	public void setOrderPopupWindow(OrderPopupWindow orderPopupWindow) {

	}
}
