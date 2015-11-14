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
	 * id 当前行的Music 的localId
	 * */
	private Integer id = null;
	/**
	 * musicInf 音乐的信息
	 * */
	private MusicInformation musicInf = null;
	/**
	 * activity 穿件类时的Activity
	 * */
	private Activity activity = null;
	/**
	 * musicRela 生成的RelativeLayout View组件
	 * */
	private RelativeLayout musicRela = null;
	/**
	 * musicRelaLayout 生成的布局文件
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
	 * 创建MusicRowRelative ,<br>
	 * 所有的子类都必须调用这个方法才能生成一个RelativeLayout,默认继承MusicRowRelativeAbstract并<br>
	 * 在首次调用getMusicView或者getMusicLayout都会调用这个方法进行创建.子类也可以重写这个方法自定义初始化<br>
	 * 初始化工作:<br>
	 * 1.musicInf ,获取MusicInformation信息<br>
	 * 2.创建musicRela并设置必要参数<br>
	 * 3.创建musicRelaLayout并设置必要参数<br>
	 * 4.执行create方法<br>
	 * create方法中可以添加自己需要显示的组件,重新设定一个RelativeLayout的大小必须重写initMusic方法,
	 * 并且在initMusic中必须执行一个create方法
	 * 
	 * */
	protected void initMusic() {

		musicInf = MusicBase.createMusic().getMusicList().get(id - 1);

		/**
		 * 如果不是合法的Id会抛出异常
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
	 * create:创建子类所需要创建的Views<br>
	 * create方法在所有子类中必须实现的方法,方法会在initMusic中自动调用,<br>
	 * 如果重写了initMusic方法也必须加入create的方法的调用,否则不会创建create中得View
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
