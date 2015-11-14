package com.music.util.inter;

import android.widget.SeekBar.OnSeekBarChangeListener;

public interface MusicSeekBar {
	
	/**
	 * 重置seekBar的所有数据<br>
	 * 1、seekBar当前位置的坐标<br>
	 * 2、seekBar的最大值<br>
	 * */
	public void resetSeekBar(int musicTime);

	/**
	 * 获取seekBar的当前位置，当前位置和音乐的播放进度保持一致<br>
	 * */
	public int getProgress();

	/**
	 * 设置seekBar的坐标位置<br>
	 * seekBar坐标位置的更更改完成后音乐的进度也相应的进行修改<br>
	 * 是在进度条完全确定后再才修改music的播放进度
	 * */
	public void setProgress(int currentPosition);

	public void changeListener(OnSeekBarChangeListener listener) ;
	
}
