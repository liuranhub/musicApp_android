package com.music.view.impl;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.music.musicapp.R;
import com.music.util.impl.InitUtil;
import com.music.util.impl.MusicBase;
import com.music.util.impl.MusicInformation;
import com.music.view.inter.MusicRowRelative;

abstract public class MusicRowRelativeAbstract implements MusicRowRelative {

	/**
	 * id ��ǰ�е�Music ��localId
	 * */
	private Integer id = null;
	/**
	 * musicInf ���ֵ���Ϣ
	 * */
	private MusicInformation musicInf = null;
	/**
	 * activity ������ʱ��Activity
	 * */
	private Activity activity = null;
	/**
	 * musicRela ���ɵ�RelativeLayout View���
	 * */
	private RelativeLayout musicRela = null;
	/**
	 * musicRelaLayout ���ɵĲ����ļ�
	 * */
	private LayoutParams musicRelaLayout = null;

	protected RelativeLayout getMusicRela() {
		return musicRela;
	}

	protected LayoutParams getMusicRelaLayout() {
		return musicRelaLayout;
	}

	protected MusicInformation getMusicInf() {
		return musicInf;
	}

	protected void setMusicInf(MusicInformation musicInf) {
		this.musicInf = musicInf;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	protected Activity getActivity() {
		return activity;
	}

	protected void setActivity(Activity activity) {
		this.activity = activity;
	}

	protected void setMusicRela(RelativeLayout musicRela) {
		this.musicRela = musicRela;
	}

	protected void setMusicRelaLayout(LayoutParams musicRelaLayout) {
		this.musicRelaLayout = musicRelaLayout;
	}

	/**
	 * ����MusicRowRelative ,<br>
	 * ���е����඼����������������������һ��RelativeLayout,Ĭ�ϼ̳�MusicRowRelativeAbstract��<br>
	 * ���״ε���getMusicView����getMusicLayout�����������������д���.����Ҳ������д��������Զ����ʼ��<br>
	 * ��ʼ������:<br>
	 * 1.musicInf ,��ȡMusicInformation��Ϣ<br>
	 * 2.����musicRela�����ñ�Ҫ����<br>
	 * 3.����musicRelaLayout�����ñ�Ҫ����<br>
	 * 4.ִ��create����<br>
	 * create�����п�������Լ���Ҫ��ʾ�����,�����趨һ��RelativeLayout�Ĵ�С������дinitMusic����,
	 * ������initMusic�б���ִ��һ��create����
	 * 
	 * */
	protected void initMusic() {

		musicInf = MusicBase.createMusic().getMusicList().get(id - 1);

		/**
		 * ������ǺϷ���Id���׳��쳣
		 * */
		if (musicInf == null) {
			throw (new IllegalArgumentException());
		}

		musicRela = new RelativeLayout(activity.getBaseContext());
		musicRela
				.setBackgroundResource(R.drawable.list_context_background_text);

		musicRela.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				musicRela
						.setBackgroundResource(R.drawable.list_context_background_text_click);
			}
		});

		musicRelaLayout = new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT);

		musicRela.setFocusable(true);

		musicRelaLayout.setMargins(0, 0, 0, 0);
		musicRelaLayout.height = InitUtil.getTextheight();

		create();
	}

	/**
	 * create:������������Ҫ������Views<br>
	 * create���������������б���ʵ�ֵķ���,��������initMusic���Զ�����,<br>
	 * �����д��initMusic����Ҳ�������create�ķ����ĵ���,���򲻻ᴴ��create�е�View
	 * */
	abstract protected void create();

	/**
	 * @return View
	 * */
	public View getMusicView() {
		if (getMusicRela() == null) {
			initMusic();
		}
		return getMusicRela();
	}

	/**
	 * @return LayoutParams
	 * */
	public LayoutParams getMusicLayout() {
		if (getMusicRelaLayout() == null) {
			initMusic();
		}
		return getMusicRelaLayout();
	}

}
