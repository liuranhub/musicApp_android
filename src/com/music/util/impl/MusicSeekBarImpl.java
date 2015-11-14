package com.music.util.impl;

import com.music.util.inter.MusicSeekBar;

import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MusicSeekBarImpl implements MusicSeekBar {

	/**
	 * �����ⲿ���ݹ�����seekBar
	 * */
	private SeekBar seekBar = null;

	/**
	 * ����seekBar�ļ������ͷ�������Ը����Լ�����Ҫ�����Լ��ļ���
	 * */
	private OnSeekBarChangeListener listener = null;

	/**
	 * ����seekBar�ĵ�ǰλ�ã�<br>
	 * ��ΪseekBar������û��һ��С�ı�Ͷ�music�����޸ģ�music����ͨ�������������ı仯�������޸ĵ�<br>
	 * ע��������ѭ�������ķ�ʽ���˲����ĸı䣬�������ܻ�ӡ�����ʣ�Ҫ���������������changed
	 * */
	private int currentPosition = 0;

	/**
	 * �����ⲿ���ж�MusicSeekBar���еĲ���ֵ�Ƿ��иı�<br>
	 * ÿ��touch���ĺ��changed����Ϊtrue<br>
	 * ÿ�μ�⵽�ı䲢ִ����ز������changed���ó�Ϊfalse
	 * */
	private boolean changed = false;
	
	private MusicBase music = MusicBase.createMusic() ;
	
	public MusicSeekBarImpl(SeekBar seekBar, int max) {
		createSeekBar(seekBar, max);
	}

	public MusicSeekBarImpl(SeekBar seekBar) {
		createSeekBar(seekBar);
	}

	/**
	 * @throws IllegalArgumentException
	 *             ����ڴ���seekBar��ʱ��û�д��ݲ������������׳��쳣
	 * */
	public SeekBar createSeekBar(SeekBar seekBar, int max) {
		if (seekBar == null) {
			throw (new IllegalArgumentException());
		}
		this.seekBar = seekBar;
		return createSeekBar(max);
	}

	/**
	 * @throws IllegalArgumentException
	 *             ���������seekBar�����ֵ����ôseekBarĬ�ϵ����ֵ��100
	 * */
	public SeekBar createSeekBar(SeekBar seekBar) {
		return createSeekBar(seekBar, 100);
	}

	private SeekBar createSeekBar(int musicTime) {
		seekBar.setMax(musicTime);
		return createSeekBar();
	}

	/**
	 * ����seekBar��������Ǳض��ᱻ����
	 * */
	private SeekBar createSeekBar() {
		initSeekBar();
		return seekBar;
	}

	private void initSeekBar() {
		listener = new MusicSeekBarOnChangeListener();
		seekBar.setOnSeekBarChangeListener(listener);
	}

	public void resetSeekBar(int musicTime) {
		seekBar.setMax(musicTime);
	}

	public int getProgress() {
		return seekBar.getProgress();
	}

	public boolean isChanged() {
		return changed;
	}

	public void setChanged(boolean changed) {
		this.changed = changed;
	}

	public SeekBar getSeekBar() {
		return seekBar;
	}

	/**
	 * �ص㣺��Ĺ���Ҫ�����ܵĵ�һ����ˣ�music���ȵ��޸��ӳٵ�����<br>
	 * ��seekBar�ļ�������֪ͨ�ķ�ʽ�޸�music����Ϣ
	 * */
	public void setProgress(int currentPosition) {
		if (currentPosition < 0) {
			currentPosition = 0;
		} else if (currentPosition > seekBar.getMax()) {
			currentPosition = seekBar.getMax();
		} else {
			seekBar.setProgress(currentPosition);
		}
	}

	/**
	 * ��һ��Ĭ�ϵ�listener���ͷ��˳���һ���Լ���һ���Զ���ļ�������
	 * */
	public void changeListener(OnSeekBarChangeListener listener) {
		if (listener == null) {
			return;
		}
		this.listener = listener;
	}

	public int getCurrentPosition() {
		return currentPosition;
	}

	@SuppressWarnings("unused")
	private void setCurrentPosition(int currentPosition) {
	}

	/**
	 * �ص㣬�������ľ������ݻ�û����д
	 * */
	private class MusicSeekBarOnChangeListener implements
			OnSeekBarChangeListener {

		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			if(fromUser){
				music.getPlayer().seekTo(progress);
			}
		}

		public void onStartTrackingTouch(SeekBar seekBar) {
			System.out.println("start");
		}

		public void onStopTrackingTouch(SeekBar seekBar) {
			System.out.println("stop");
		}
	}
}
