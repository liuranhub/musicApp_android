package com.music.view.impl;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

import com.music.musicapp.R;
import com.music.util.impl.InitUtil;
import com.music.view.fartory.MusicRowRelativeBaseFactory;
import com.music.view.inter.MusicLetterRelative;
import com.music.view.inter.MusicRowRelative;

/**
 * ����һ����ĸ����ĸ��������һ�����и���
 * */
public class MusicLetterRelativeBase implements MusicLetterRelative {

	private Integer letterHeight = null;

	private Activity activity = null;

	private String word = null;

	private List<Integer> list = null;

	private LinearLayout musicLinear = null;

	private LayoutParams musicLinearLayout = null;

	private OrderPopupWindow orderPopupWindow = null;
	
	private LinkedList<MusicRowRelative> rowList = new LinkedList<MusicRowRelative>() ;
	

	/**
	 * @param activity
	 *            ����������������Activity
	 * @param word
	 *            ����ĸ{��:ABC...}
	 * @param list
	 *            ���и������б�,�б��е�������Integer���͵�,����������ֵ�Id
	 * */
	public MusicLetterRelativeBase(Activity activity, String word,
			List<Integer> list) {
		this.activity = activity;
		this.word = word;
		this.list = list;
	}

	/**
	 * ����һ����ĸ�е���������
	 * 
	 * @param word
	 *            ����ĸ,�ɹ��������ݽ�����
	 * @param list
	 *            �����б�,���������ݽ�����
	 * */
	private void initOneLetter(String word, List<Integer> list) {

		letterHeight = 0;

		if (word.length() <= 0) {
			return;
		}

		if (list.size() < 0) {
			return;
		}

		/**
		 * ������ĸ���и�����LinearLayout
		 * */
		musicLinear = new LinearLayout(activity.getBaseContext());
		musicLinear.setId((int) word.charAt(0));
		musicLinear.setOrientation(LinearLayout.VERTICAL);

		musicLinearLayout = new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT);
		musicLinearLayout.setMargins(0, 0, 0, 0);
		/**
		 * ����ĸ����
		 * */
		RelativeLayout wordRela = new RelativeLayout(activity.getBaseContext());
		LayoutParams wordRelaLayout = new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		wordRelaLayout.height = InitUtil.getLetterheight();
		wordRelaLayout.setMargins(10, 0, 10, 0);

		ImageView letterImage = new ImageView(activity.getBaseContext());
		letterImage
				.setBackgroundResource(R.drawable.list_context_background_letter);
		LayoutParams letterImageLayout = new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		/**
		 * ����ĸ
		 * */
		TextView textView = new TextView(activity.getBaseContext());
		textView.setText(word);
		textView.setTextSize(20);
		textView.setTextColor(Color.argb(150, 13, 138, 243));
		LayoutParams textViewLayout = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		textViewLayout.setMargins(30, 0, 0, 0);
		textViewLayout.addRule(RelativeLayout.CENTER_VERTICAL);

		wordRela.addView(letterImage, letterImageLayout);
		wordRela.addView(textView, textViewLayout);

		musicLinear.addView(wordRela, wordRelaLayout);

		/**
		 * ��ʼ����Music��������߶�
		 * */
		for (Integer id : list) {
			MusicRowRelative row = new MusicRowRelativeBaseFactory().create(
					activity, id);
			if (orderPopupWindow != null) {
				row.setOrderPopupWindow(orderPopupWindow);
			}
			musicLinear.addView(row.getMusicView(), row.getMusicLayout());

			rowList.add(row);
			
			letterHeight = letterHeight + InitUtil.getTextheight();
		}

		letterHeight = letterHeight + InitUtil.getLetterheight();

	}

	public void setOrderPopupWindow(OrderPopupWindow orderPopupWindow) {
		this.orderPopupWindow = orderPopupWindow;
		initOneLetter(word, list);
	}

	/**
	 * ��ȡ������View
	 * 
	 * @return musicLinear LinearLayout���͵�View,����������ĸ����ĸ����������ȫ������
	 * */
	public LinearLayout getMusicView() {
		if (musicLinear == null) {
			initOneLetter(word, list);
		}
		return musicLinear;
	}

	/**
	 * ��ȡ�����������ļ�
	 * */
	public LayoutParams getMusicLayout() {
		if (musicLinearLayout == null) {
			initOneLetter(word, list);
		}
		return musicLinearLayout;
	}

	/**
	 * @return letterHeight һ����ĸ����ռ�ĸ߶�,��λ������
	 * */
	public Integer getLetterHeight() {
		return letterHeight;
	}

	@Override
	public void setId(Integer id) {
		
	}

	@Override
	public LinkedList<MusicRowRelative> getRowList() {
		return rowList ;
	}
	
}
