package com.music.util.impl;

import com.music.util.inter.MusicSeekBar;

import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MusicSeekBarImpl implements MusicSeekBar {

	/**
	 * 保存外部传递过来的seekBar
	 * */
	private SeekBar seekBar = null;

	/**
	 * 保存seekBar的监听，客服代码可以更具自己的需要设置自己的监听
	 * */
	private OnSeekBarChangeListener listener = null;

	/**
	 * 保存seekBar的当前位置，<br>
	 * 因为seekBar不可能没有一点小改变就对music进行修改，music，是通过检查这个参数的变化来进行修改的<br>
	 * 注：不能以循环遍历的方式检测此参数的改变，这样可能会印象到音质，要检测下面的这个变量changed
	 * */
	private int currentPosition = 0;

	/**
	 * 用着外部类判断MusicSeekBar类中的参数值是否有改变<br>
	 * 每次touch更改后把changed设置为true<br>
	 * 每次检测到改变并执行相关操作后把changed设置成为false
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
	 *             如果在创建seekBar的时候没有传递参数进来将会抛出异常
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
	 *             如果不设置seekBar的最大值，那么seekBar默认的最大值是100
	 * */
	public SeekBar createSeekBar(SeekBar seekBar) {
		return createSeekBar(seekBar, 100);
	}

	private SeekBar createSeekBar(int musicTime) {
		seekBar.setMax(musicTime);
		return createSeekBar();
	}

	/**
	 * 创建seekBar这个方法是必定会被调用
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
	 * 重点：类的功能要尽可能的单一，因此，music进度的修改延迟到后面<br>
	 * 在seekBar的监听中以通知的方式修改music的信息
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
	 * 有一个默认的listener，客服端程序一个自己穿一个自定义的监听过来
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
	 * 重点，这个里面的具体类容还没有书写
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
